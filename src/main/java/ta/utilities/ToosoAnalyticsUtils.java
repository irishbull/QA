package ta.utilities;

import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import ta.utilities.constants.Constants;
import ta.utilities.constants.ToosoConstants;

import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.ToosoConstants.CID;
import static ta.utilities.constants.ToosoConstants.DL;
import static ta.utilities.constants.ToosoConstants.DR;
import static ta.utilities.constants.ToosoConstants.RequestType;
import static ta.utilities.constants.ToosoConstants.UID;


public class ToosoAnalyticsUtils {

    private static final Logger logger = LoggerFactory.getLogger(ToosoAnalyticsUtils.class);
    private static final String CONSTRUCTION_FORBIDDEN = "ToosoAnalyticsUtils - Object construction is forbidden";

    private static final String MISSING_MANDATORY_PARAM_MESSAGE = "Parameter [%s]: mandatory but NOT found \n";
    private static final String INVALID_PARAM_VALUE_MESSAGE = "Parameter [%s]: expected [%s] but found [%s] \n";
    private static final String INVALID_PARAM_Q_VALUE_MESSAGE = "Parameter q[%s]: expected [%s] but found [%s] \n";
    private static final String EMPTY_VALUE_PARAM_MESSAGE = "Parameter [%s]: empty value \n";


    /**
     *
     * @param entries
     * @param type
     * @return
     */
    public static List<HarEntry> retrieveEntriesOfType(List<HarEntry> entries, RequestType type) {

        List<HarEntry> result = new ArrayList<>();

        switch (type) {

            case SUGGEST:
                result.addAll(filterEntries(entries, ToosoConstants.PROXY_SUGGEST_BASE_URL, Collections.emptyList()));
                // sort (Url Alphabetical Order)
                result.sort((e1, e2) -> e1.getRequest().getUrl().compareTo(e2.getRequest().getUrl()));
                break;

            case SEARCH:
                result.addAll(filterEntries(entries, ToosoConstants.PROXY_SEARCH_BASE_URL, Collections.emptyList()));
                // sort (Url Alphabetical Order)
                result.sort((e1, e2) -> e1.getRequest().getUrl().compareTo(e2.getRequest().getUrl()));
                break;

            case PAGEVIEW:
                result.addAll(filterEntries(entries, ToosoConstants.BASE_URL, ToosoConstants.Filters.PAGEVIEW));
                break;

            case PAGEVIEW_DETAIL:
                result.addAll(filterEntries(entries, ToosoConstants.BASE_URL, ToosoConstants.Filters.PAGEVIEW_DETAIL));
                break;

            case PAGEVIEW_PURCHASE:
                result.addAll(filterEntries(entries, ToosoConstants.BASE_URL, ToosoConstants.Filters.PAGEVIEW_PURCHASE));
                break;

            case CLICK_AFTER_SEARCH:
                result.addAll(filterEntries(entries, ToosoConstants.BASE_URL, ToosoConstants.Filters.CLICK_AFTER_SEARCH));
                break;

            case CLICK_ON_SUGGESTED:
                result.addAll(filterEntries(entries, ToosoConstants.BASE_URL, ToosoConstants.Filters.CLICK_ON_SUGGESTED));
                break;

            case ADD_TO_CART:
                result.addAll(filterEntries(entries, ToosoConstants.BASE_URL, ToosoConstants.Filters.ADD_TO_CART));
                break;

            case REMOVE_FROM_CART:
                result.addAll(filterEntries(entries, ToosoConstants.BASE_URL, ToosoConstants.Filters.REMOVE_FROM_CART));
                break;

            default:
                break;
        }

        return result;
    }


    private static List<HarEntry> filterEntries(List<HarEntry> entries, String baseUrl, List<String> conditions) {
        return entries.stream().filter(p -> areConditionsVerified(p.getRequest(), baseUrl, conditions)).collect(Collectors.toList());
    }


    private static boolean areConditionsVerified(HarRequest request, String baseUrl, List<String> params) {

        // filter by request method
        if (!ToosoConstants.METHOD.equalsIgnoreCase(request.getMethod())) {
            return false;
        }

        String url = request.getUrl();

        // filter by url hostname
        if (!url.contains(baseUrl)) {
            return false;
        }

        // filter by url params
        for (String param : params) {
            if (!url.contains(param)) {
                return false;
            }
        }

        return true;
    }


    /**
     *
     * @param url
     * @param testData
     * @throws URISyntaxException
     */
    @SuppressWarnings("unchecked")
    public static List<String> queryParamsValidation(String url, JSONObject testData, RequestType type) throws URISyntaxException {

        // query parameters to validate
        final Map<String, String> urlQueryParams = getQueryParams(url);

        // map containing parameters <name, value> for EQUALITY check
        Map<String, String> expectedEqual = new HashMap<>();

        // list containing parameters <name> that should be NOT EMPTY
        List<String> expectedNotEmpty = new ArrayList<>();

        List<String> errorMessages = new ArrayList<>();

        // add common parameters according request type
        switch (type) {

            case SUGGEST:
            case SEARCH:
                expectedEqual.putAll(ToosoConstants.SearchAndSuggest.Common.ASSERT_EQUALS_QUERY_PARAMS);
                expectedNotEmpty.addAll(ToosoConstants.SearchAndSuggest.Common.ASSERT_NOT_EMPTY_QUERY_PARAMS);
                break;

            // Tooso Analytics
            default:
                expectedEqual.putAll(ToosoConstants.Analytics.Common.ASSERT_EQUALS_QUERY_PARAMS);
                expectedNotEmpty.addAll(ToosoConstants.Analytics.Common.ASSERT_NOT_EMPTY_QUERY_PARAMS);
                break;
        }

        // add parameters (dynamically retrieved from json) for EQUALITY check
        expectedEqual.putAll((HashMap<String, String>) testData.get("expectedEqual"));

        // add parameters(dynamically retrieved from json) that should be NOT EMPTY
        expectedNotEmpty.addAll((List<String>) testData.get("expectedNotEmpty"));

        // map containing <name, pattern> (dynamically retrieved from json)
        // the pattern is used to create a Matcher object that can match parameter value against the regular expression
        Map<String, String> paramsPatterns = Objects.nonNull(testData.get("expectedMatch")) ? (HashMap<String, String>) testData.get("expectedMatch") : Collections.emptyMap();


        // equality check
        errorMessages.addAll(equalityCheck(urlQueryParams, expectedEqual));

        // not empty check
        errorMessages.addAll(notEmptyCheck(urlQueryParams, expectedNotEmpty));

        // pattern check
        errorMessages.addAll(patternCheck(urlQueryParams, paramsPatterns));

        return errorMessages;
    }


    /**
     *
     * @param url
     * @param index
     * @param word
     * @throws URISyntaxException
     */
    public static List<String> paramQValidation(String url, int index, String word) throws URISyntaxException {

        // request query parameters
        Map<String, String> urlQueryParams = getQueryParams(url);

        List<String> errorMessages = new ArrayList<>();

        String actualQ = urlQueryParams.get("q");
        if (actualQ.isEmpty()) {
            String errorMessage = String.format(MISSING_MANDATORY_PARAM_MESSAGE, "q");
            errorMessages.add(errorMessage);
            logger.error(errorMessage);

        } else if (!actualQ.equals(word.substring(0, index))) {
            String errorMessage = String.format(INVALID_PARAM_Q_VALUE_MESSAGE, String.valueOf(index), word.substring(0, index), actualQ);
            errorMessages.add(errorMessage);
            logger.info(errorMessage);
        }

        return errorMessages;
    }


    private static Map<String, String> getQueryParams(String url) throws URISyntaxException {
        List<NameValuePair> urlNameValuePairs = URLEncodedUtils.parse(new URI(url), Charset.forName(Constants.Encode.UTF_8));
        return urlNameValuePairs.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
    }


    private static List<String> equalityCheck(Map<String, String> actual, Map<String, String> expected) {

        String key;
        String expectedValue;

        List<String> errorMessages = new ArrayList<>();

        for (Map.Entry entry : expected.entrySet()) {

            key = (String) entry.getKey();

            if (!actual.containsKey(key)) {
                String errorMessage = String.format(MISSING_MANDATORY_PARAM_MESSAGE, key);
                errorMessages.add(errorMessage);
                logger.error(String.format(MISSING_MANDATORY_PARAM_MESSAGE, key));
                continue;
            }

            switch (key) {
                // dr expected value should be equal to baseUrl and json dr concatenation when json dr is not empty
                case DR:
                    expectedValue = entry.getValue().toString().isEmpty() ? "" : BASE_URL.concat(entry.getValue().toString());
                    break;
                // dl expected value should be equal to baseUrl and json dl
                case DL:
                    expectedValue = BASE_URL.concat(entry.getValue().toString());
                    break;
                // cid expected value should be equal to cid value stored in the cookie _ta
                case CID:
                    expectedValue = CookiesUtils.getCidValueFromCookieTA();
                    break;
                case UID:
                    // TODO  get uid from sessionStorage when user is logged-in otherwise get uid from local storage
                    expectedValue = LocalStorage.getUid();
                    break;
                default:
                    expectedValue = entry.getValue().toString();
            }

            if (!expectedValue.equals(actual.get(key))) {
                String errorMessage = String.format(INVALID_PARAM_VALUE_MESSAGE, key, expectedValue, actual.get(key));
                errorMessages.add(errorMessage);
                logger.error(errorMessage);
            }
        }

        return errorMessages;
    }


    private static List<String> notEmptyCheck(Map<String, String> actual, List<String> notEmptyParams) {

        List<String> errorMessages = new ArrayList<>();

        for (String param : notEmptyParams) {

            if (!actual.containsKey(param)) {
                String errorMessage = String.format(MISSING_MANDATORY_PARAM_MESSAGE, param);
                errorMessages.add(errorMessage);
                logger.error(String.format(MISSING_MANDATORY_PARAM_MESSAGE, param));
                continue;
            }

            if (actual.get(param).isEmpty()) {
                String errorMessage = String.format(EMPTY_VALUE_PARAM_MESSAGE, param);
                errorMessages.add(errorMessage);
                logger.error(errorMessage);
            }
        }

        return errorMessages;
    }


    private static List<String> patternCheck(Map<String, String> actual, Map<String, String> paramsPatterns) {

        List<String> errorMessages = new ArrayList<>();

        String key;
        String pattern;

        for (Map.Entry entry : paramsPatterns.entrySet()) {

            key = entry.getKey().toString();
            pattern = entry.getValue().toString();

            if (!actual.containsKey(key)) {
                String errorMessage = String.format(MISSING_MANDATORY_PARAM_MESSAGE, key);
                errorMessages.add(errorMessage);
                continue;
            }

            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(actual.get(key));

            logger.info("Validate the parameter [{} = {}] against the regular expression [{}]  ", key, actual.get(key), pattern);

            boolean match = m.matches();

            if (!match) {
                String errrorMessage = String.format("The regular expression [%s] does NOT match the parameter [%s = %s]", pattern, key, actual.get(key));
                errorMessages.add(errrorMessage);
            }
        }

        return errorMessages;
    }


    private ToosoAnalyticsUtils() {
        throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
    }
}
