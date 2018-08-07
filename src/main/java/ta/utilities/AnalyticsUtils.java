package ta.utilities;

import net.lightbody.bmp.core.har.HarEntry;

import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * @param actual map containing the actual values to check
     * @param expected map containing the expected values
     */
    public static void checkMandatoryValues(Map<String, String> actual, Map<String, String> expected) {

        for (Map.Entry entry : expected.entrySet()) {

            String key = (String) entry.getKey();

            Assert.assertTrue(actual.containsKey(key), "Request should contain the mandatory parameter [" + key + "]:");
            Assert.assertEquals(actual.get(key), entry.getValue(), "Invalid query parameter value [" + key + "]:");
        }
    }


    /**
     *
     * @param actual map containing the actual values to check
     * @param notEmptyParams list of parameters name to check
     */
    public static void checkNotEmptyValues(Map<String, String> actual, List<String> notEmptyParams) {

        for (String param : notEmptyParams) {
            Assert.assertTrue(actual.containsKey(param), "Request should contain the parameter [" + param + "]:");
            Assert.assertFalse(actual.get(param).isEmpty(), "Query parameter [" + param + "] value is empty:");
        }
    }
}
