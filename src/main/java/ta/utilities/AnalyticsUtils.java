package ta.utilities;

import net.lightbody.bmp.core.har.HarEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.HashMap;
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
        return url.contains(ReadPropertiesFile.getProperty("tooso.hostname"))
            && url.contains(ReadPropertiesFile.getProperty("tooso.pageview.type"));
    }

    private static boolean isSuggestRequest(String url) {
        return url.contains(ReadPropertiesFile.getProperty("tooso.hostname"))
            && url.contains(ReadPropertiesFile.getProperty("tooso.suggest.ec"));
    }

    /**
     * check mandatory values consistency
     */
    public static void checkMandatoryValues(Map<String, String> actualValuesMap, HashMap<String, String> mandatoryValues) {

        for (Map.Entry entry : mandatoryValues.entrySet()) {
            String key = (String) entry.getKey();

            if (Objects.nonNull(actualValuesMap.get(key))) {
                logger.info("Parameter {}",(String) entry.getKey());
                Assert.assertEquals(entry.getValue(), actualValuesMap.get(key),
                    "Query param " + entry.getKey());

            } else {
                logger.error("Parameter {} not found", entry.getKey());
                Assert.fail("Parameter not found");
            }
        }
    }

    /**
     * verify not empty values condition
     */
    public static void checkNotEmptyValues(Map<String, String> actualValuesMap, HashMap<String, String> notEmptyValues) {

        for (Map.Entry entry : notEmptyValues.entrySet()) {
            String key = (String) entry.getKey();

            if (Objects.nonNull(actualValuesMap.get(key))) {

                Assert.assertTrue(!actualValuesMap.get(key).isEmpty());

            } else {
                logger.error("Parameter {} not found", entry.getKey());
                Assert.fail("Parameter not found");
            }
        }
    }
}
