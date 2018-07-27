package ta.utilities;

import net.lightbody.bmp.core.har.HarEntry;
import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.*;
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

                Assert.assertEquals(entry.getValue(), actualValuesMap.get(key),
                    "Query param " + entry.getKey());

            } else {
                logger.error("Parameter {} not founded", entry.getKey());
                Assert.fail("Parameter not founded");
            }
        }
    }

    /**
     * verify not empty values condition
     */
    public static void checkNotEmptyValues(List<NameValuePair> actualNameValuePairs, HashMap<String, String> expectedValuesMap) {

        for (NameValuePair pair : actualNameValuePairs) {
            logger.info("query param [{}]={}", pair.getName(), pair.getValue());
            String expectedValue = expectedValuesMap.get(pair.getName());
            if (Objects.nonNull(expectedValue)) {
                logger.info("Assert expected[{}] = actual[{}]", pair.getValue(), expectedValue);
                Assert.assertEquals(pair.getValue(), expectedValue,
                    "Query param " + pair.getName());
            }
        }
    }

    /**
     * if params are present, check theirs not empty condition
     */
    public static void checkOptionalValues(List<NameValuePair> actualNameValuePairs, HashMap<String, String> expectedValuesMap) {

        for (NameValuePair pair : actualNameValuePairs) {
            logger.info("query param [{}]={}", pair.getName(), pair.getValue());
            String expectedValue = expectedValuesMap.get(pair.getName());
            if (Objects.nonNull(expectedValue)) {
                logger.info("Assert expected[{}] = actual[{}]", pair.getValue(), expectedValue);
                Assert.assertEquals(pair.getValue(), expectedValue,
                    "Query param " + pair.getName());
            }
        }
    }
}
