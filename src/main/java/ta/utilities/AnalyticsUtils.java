package ta.utilities;

import net.lightbody.bmp.core.har.HarEntry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class AnalyticsUtils {

    private static final Logger logger = LoggerFactory.getLogger(AnalyticsUtils.class);
    private static final String CONSTRUCTION_FORBIDDEN =
            "AnalyticsUtils - Object construction is forbidden";

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
     * check mandatory values consistency
     */
    public static void checkMandatoryValues(Map<String, String> actualValuesMap, Map<String, String> mandatoryValues) {

        for (Map.Entry entry : mandatoryValues.entrySet()) {
            String key = (String) entry.getKey();

            if (Objects.nonNull(actualValuesMap.get(key))) {
                logger.info("Parameter {}", key);
                Assert.assertEquals(actualValuesMap.get(key), entry.getValue(),
                        "Query param " + key);

            } else {
                logger.error("Parameter {} not found", key);
                Assert.fail("Parameter " + key + " not found");
            }
        }
    }

    /**
     * verify not empty values condition
     */
    public static void checkNotEmptyValues(Map<String, String> actualValuesMap, Map<String, String> notEmptyValues) {

        for (Map.Entry entry : notEmptyValues.entrySet()) {
            String key = (String) entry.getKey();

            if (Objects.nonNull(actualValuesMap.get(key))) {

                Assert.assertTrue(!actualValuesMap.get(key).isEmpty());

            } else {
                logger.error("Parameter {} not found", key);
                Assert.fail("Parameter " + key + " not found");
            }
        }
    }
}
