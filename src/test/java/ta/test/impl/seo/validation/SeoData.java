package ta.test.impl.seo.validation;

import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;

public class SeoData {

    private Document document;
    private boolean isRendered;
    private JSONObject expectedData;


    public SeoData(Document document, boolean isRendered, JSONObject expectedData) {
        this.document = document;
        this.isRendered = isRendered;
        this.expectedData = expectedData;
    }

    public SeoData(Document document, boolean isRendered) {
        this(document, isRendered, null);
    }

    public Document getDocument() {
        return document;
    }

    public boolean isRendered() {
        return isRendered;
    }

    public JSONObject getExpectedData() {
        return expectedData;
    }
}
