package ta.utilities;

import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ta.utilities.constants.Constants;
import ta.utilities.constants.ToosoConstants;

import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.ToosoConstants.DL;
import static ta.utilities.constants.ToosoConstants.DP;
import static ta.utilities.constants.ToosoConstants.RequestType;


public class ToosoAnalyticsUtils {

    private static final String CONSTRUCTION_FORBIDDEN = "ToosoAnalyticsUtils - Object construction is forbidden";


    /**
     * @param entries to filter
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
    public static void checkMandatoryValues(String url, JSONObject testData, RequestType type) throws URISyntaxException {

        // request query parameters
        final Map<String, String> urlQueryParams = getQueryParams(url);

        // mandatory parameters(dynamically retrieved from json)
        HashMap<String, String> mandatoryAssertEqualsParams = (HashMap<String, String>) testData.get("mandatoryAssertEquals");

        // mandatory parameters that should be NOT empty (dynamically retrieved from json)
        List<String> mandatoryAssertNotEmptyParams = (List<String>) testData.get("mandatoryAssertNotEmpty");

        // add common mandatory parameters according request type
        switch (type) {

            case SUGGEST:
            case SEARCH:
                mandatoryAssertEqualsParams.putAll(ToosoConstants.SearchAndSuggest.Common.ASSERT_EQUALS_QUERY_PARAMS);
                mandatoryAssertNotEmptyParams.addAll(ToosoConstants.SearchAndSuggest.Common.ASSERT_NOT_EMPTY_QUERY_PARAMS);
                break;

            // Analytics
            default:
                mandatoryAssertEqualsParams.putAll(ToosoConstants.Analytics.Common.ASSERT_EQUALS_QUERY_PARAMS);
                mandatoryAssertNotEmptyParams.addAll(ToosoConstants.Analytics.Common.ASSERT_NOT_EMPTY_QUERY_PARAMS);
                break;
        }

        assertEquals(urlQueryParams, mandatoryAssertEqualsParams);

        assertNotEmpty(urlQueryParams, mandatoryAssertNotEmptyParams);

    }


    /**
     *
     * @param url
     * @param index
     * @param word
     * @throws URISyntaxException
     */
    public static void checkSuggestQueryParam(String url, int index, String word) throws URISyntaxException {

        // request query parameters
        Map<String, String> urlQueryParams = getQueryParams(url);

        String actualQ = urlQueryParams.get("q");

        Assert.assertEquals(actualQ, word.substring(0, index), "Suggest request - parameter [q]:");

    }


    private static Map<String, String> getQueryParams(String url) throws URISyntaxException {
        List<NameValuePair> urlNameValuePairs = URLEncodedUtils.parse(new URI(url), Charset.forName(Constants.Encode.UTF_8));
        return urlNameValuePairs.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
    }


    private static void assertEquals(Map<String, String> actual, Map<String, String> expected) {

        for (Map.Entry entry : expected.entrySet()) {

            String key = (String) entry.getKey();

            Assert.assertTrue(actual.containsKey(key), "Request should contain the mandatory parameter [" + key + "]:");

            // dl expected value should be equal to baseUrl and json dl concatenation
            String expectedValue = key.equalsIgnoreCase(DL) ? BASE_URL.concat(entry.getValue().toString()) : entry.getValue().toString();

            Assert.assertEquals(actual.get(key), expectedValue, "Invalid query parameter value [" + key + "]:");
        }
    }


    private static void assertNotEmpty(Map<String, String> actual, List<String> notEmptyParams) {

        for (String param : notEmptyParams) {
            Assert.assertTrue(actual.containsKey(param), "Request should contain the parameter [" + param + "]:");
            Assert.assertFalse(actual.get(param).isEmpty(), "Query parameter [" + param + "] value is empty:");
        }
    }

    private ToosoAnalyticsUtils() {
        throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
    }
}
