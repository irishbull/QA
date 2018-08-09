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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ta.utilities.Constants.Tooso.DL;
import static ta.utilities.Constants.Tooso.DP;
import static ta.utilities.Constants.Url.BASE_URL;

public class AnalyticsUtils {

    private static final String CONSTRUCTION_FORBIDDEN = "AnalyticsUtils - Object construction is forbidden";

    private AnalyticsUtils() {
        throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
    }

    /**
     * @param entries to filter
     * @return Pageview requests
     */
    public static List<HarEntry> getPageViewRequests(List<HarEntry> entries) {
        return filterEntries(entries, Constants.Tooso.BASE_URL, Constants.Tooso.Filters.PAGEVIEW);
    }

    /**
     * @param entries to filter
     * @return Pageview detail requests
     */
    public static List<HarEntry> getPageViewDetailRequests(List<HarEntry> entries) {
        return filterEntries(entries, Constants.Tooso.BASE_URL, Constants.Tooso.Filters.PAGEVIEW_DETAIL);
    }

    /**
     * @param entries to filter
     * @return Pageview purchase requests
     */
    public static List<HarEntry> getPageViewPurchaseRequests(List<HarEntry> entries) {
        return filterEntries(entries, Constants.Tooso.BASE_URL, Constants.Tooso.Filters.PAGEVIEW_PURCHASE);
    }

    /**
     * @param entries to filter
     * @return Click on suggested elem requests
     */
    public static List<HarEntry> getClickOnSuggestRequests(List<HarEntry> entries) {
        return filterEntries(entries, Constants.Tooso.BASE_URL, Constants.Tooso.Filters.CLICK_ON_SUGGESTED);
    }

    /**
     * @param entries to filter
     * @return Add to cart requests
     */
    public static List<HarEntry> getAddToCartRequests(List<HarEntry> entries) {
        return filterEntries(entries, Constants.Tooso.BASE_URL, Constants.Tooso.Filters.ADD_TO_CART);
    }

    /**
     * @param entries to filter
     * @return suggest requests
     */
    public static List<HarEntry> getSuggestRequests(List<HarEntry> entries) {
        return filterEntries(entries, Constants.Tooso.PROXY_SUGGEST_BASE_URL, Collections.emptyList());
    }

    /**
     * @param entries to filter
     * @return search requests
     */
    public static List<HarEntry> getSearchRequests(List<HarEntry> entries) {
        return filterEntries(entries, Constants.Tooso.PROXY_SEARCH_BASE_URL, Collections.emptyList());
    }


    private static List<HarEntry> filterEntries(List<HarEntry> entries, String baseUrl, List<String> conditions) {
        return entries.stream().filter(p -> areConditionsVerified(p.getRequest(), baseUrl, conditions)).collect(Collectors.toList());
    }


    private static boolean areConditionsVerified(HarRequest request, String baseUrl, List<String> params) {

        // filter by request method
        if (!Constants.Tooso.METHOD.equalsIgnoreCase(request.getMethod())) {
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
    public static void checkMandatoryValues(String url, JSONObject testData) throws URISyntaxException {

        // request query parameters
        Map<String, String> urlQueryParams = getQueryParams(url);

        // mandatory parameters(dynamically retrieved from json)
        HashMap<String, String> mandatoryAssertEqualsParams = (HashMap<String, String>) testData.get("mandatoryAssertEquals");
        // add common mandatory parameters
        mandatoryAssertEqualsParams.putAll(Constants.Tooso.Common.ASSERT_EQUALS_QUERY_PARAMS);
        assertEquals(urlQueryParams, mandatoryAssertEqualsParams);

        // mandatory parameters that should be not empty (dynamically retrieved from json)
        List<String> mandatoryAssertNotEmptyParams = (List<String>) testData.get("mandatoryAssertNotEmpty");
        // add common mandatory parameters
        mandatoryAssertNotEmptyParams.addAll(Constants.Tooso.Common.ASSERT_NOT_EMPTY_QUERY_PARAMS);
        assertNotEmpty(urlQueryParams, mandatoryAssertNotEmptyParams);

    }


    private static Map<String, String> getQueryParams(String url) throws URISyntaxException {
        List<NameValuePair> urlNameValuePairs = URLEncodedUtils.parse(new URI(url), Charset.forName(Constants.Encode.UTF_8));
        return urlNameValuePairs.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
    }


    private static void assertEquals(Map<String, String> actual, Map<String, String> expected) {

        for (Map.Entry entry : expected.entrySet()) {

            String key = (String) entry.getKey();

            Assert.assertTrue(actual.containsKey(key), "Request should contain the mandatory parameter [" + key + "]:");
            Assert.assertEquals(actual.get(key), entry.getValue(), "Invalid query parameter value [" + key + "]:");
        }


        // dl is mandatory and it should be equal to baseUrl and dp concatenation
        Assert.assertTrue(actual.containsKey(DL), "Request should contain the mandatory parameter [" + DL + "]:");
        String dlExpectedValue = BASE_URL.concat(actual.get(DP));
        Assert.assertEquals(actual.get(DL), dlExpectedValue, "Invalid query parameter value [" + DL + "]:");
    }


    private static void assertNotEmpty(Map<String, String> actual, List<String> notEmptyParams) {

        for (String param : notEmptyParams) {
            Assert.assertTrue(actual.containsKey(param), "Request should contain the parameter [" + param + "]:");
            Assert.assertFalse(actual.get(param).isEmpty(), "Query parameter [" + param + "] value is empty:");
        }
    }
}
