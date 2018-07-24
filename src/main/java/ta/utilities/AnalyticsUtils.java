package ta.utilities;

import net.lightbody.bmp.core.har.HarEntry;

import java.util.List;
import java.util.stream.Collectors;

public class AnalyticsUtils {

    private static final String CONSTRUCTION_FORBIDDEN = "AnalyticsUtils - Object construction is forbidden";

    private AnalyticsUtils() {
        throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
    }

    public static List<HarEntry> filterByHostName(List<HarEntry> entries, String hostname) {
        return entries.stream().filter(p -> p.getRequest().getUrl().contains(hostname)).collect(Collectors.toList());
    }
}
