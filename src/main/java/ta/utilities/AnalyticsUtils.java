package ta.utilities;

import net.lightbody.bmp.core.har.HarEntry;

import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class AnalyticsUtils {

    private static final Logger logger = LoggerFactory.getLogger(AnalyticsUtils.class);
    private static final String CONSTRUCTION_FORBIDDEN = "AnalyticsUtils - Object construction is forbidden";

    private AnalyticsUtils() {
        throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
    }

    public static List<HarEntry> getPageViewRequests(List<HarEntry> entries) {
        return entries.stream().filter(p -> isPageViewRequest(p.getRequest().getUrl())).collect(Collectors.toList());
    }

    private static boolean isPageViewRequest(String url) {
        return url.contains(ReadPropertiesFile.getProperty("tooso.hostname")) &&
                url.contains(ReadPropertiesFile.getProperty("tooso.pageview.type"));
    }


    /**
     * check if actual values are equal to expected values
     */
    public static void checkQueryParams(List<NameValuePair> actualNameValuePairs, Map<String, String> expectedValuesMap) {

        for (NameValuePair pair : actualNameValuePairs) {
            logger.info("query param [{}]={}", pair.getName(), pair.getValue());
            String expectedValue = expectedValuesMap.get(pair.getName());
            if (Objects.nonNull(expectedValue)) {
                logger.info("Assert expected[{}] = actual[{}]", pair.getValue(), expectedValue);
                Assert.assertEquals(pair.getValue(), expectedValue, "Query param " + pair.getName());
            }
        }
    }
}
