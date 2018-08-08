package ta.utilities;

import net.lightbody.bmp.core.har.HarEntry;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
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

    public static List<HarEntry> getPageViewRequests(List<HarEntry> entries) {
        return entries.stream().filter(p -> isPageViewRequest(p.getRequest().getUrl())).collect(Collectors.toList());
    }

    public static List<HarEntry> getSuggestRequests(List<HarEntry> entries) {
        return entries.stream().filter(p -> isSuggestRequest(p.getRequest().getUrl())).collect(Collectors.toList());
    }

    private static boolean isPageViewRequest(String url) {
        return url.contains(Constants.Tooso.HOSTNAME)
                && url.contains(Constants.Tooso.PAGEVIEW_FILTER);
    }

    private static boolean isSuggestRequest(String url) {
        return url.contains(Constants.Tooso.HOSTNAME)
                && url.contains(Constants.Tooso.SUGGEST_EC_FILTER);
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
        List<String> mandatoryAssertNotEmptyParams = (List<String>)testData.get("mandatoryAssertNotEmpty");
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
        Assert.assertTrue(actual.containsKey(DL), "Request should contain the mandatory parameter [" + DL + "]:" );
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
