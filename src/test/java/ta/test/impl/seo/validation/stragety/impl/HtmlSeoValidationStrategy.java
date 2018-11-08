package ta.test.impl.seo.validation.stragety.impl;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import ta.test.impl.seo.validation.SeoData;
import ta.test.impl.seo.validation.SeoValidationType;
import ta.test.impl.seo.validation.stragety.SeoValidationStrategy;

import static ta.utilities.constants.Constants.Url.BASE_URL;

public enum HtmlSeoValidationStrategy implements SeoValidationStrategy {

    TITLE (SeoValidationType.TITLE) {
        @Override
        public <T extends SeoData> void validate(T data) {
            String title = data.getDocument().title();
            String expectedTitle = data.getExpectedData().get("title").toString();
            Assert.assertNotEquals(title, "", "TITLE validation failed. Found empty title");
            Assert.assertEquals(title, expectedTitle,  "TITLE validation failed. Title");
            //Assert.assertNotEquals(title.trim(), "Leroy Merlin", "TITLE validation failed. Title should not be equal to Leroy Merlin");
        }
    },

    HEADER1 (SeoValidationType.HEADER1) {
        @Override
        public <T extends SeoData> void validate(T data) {
            Elements headings = data.getDocument().getElementsByTag("h1");
            Assert.assertEquals(headings.size(), 1, "HEADER1 validation failed. Tag <h1> should be unique. Number of <h1> tags");
            Assert.assertEquals(headings.get(0).text(), data.getExpectedData().get("h1").toString(), "HEADER1 validation failed. <h1> text");
        }
    },

    META_DESCRIPTION (SeoValidationType.META_DESCRIPTION) {
        @Override
        public <T extends SeoData> void validate(T data) {
            Elements metaDescriptions = data.getDocument().select("meta[name=\"description\"]");
            Assert.assertEquals(metaDescriptions.size(), 1, "META_DESCRIPTION validation failed. Meta description should be unique. Number of meta description");
            String metaDescription =  metaDescriptions.get(0).attr("content");
            String expectedMetaDescription = data.getExpectedData().get("metaDescription").toString();
            Assert.assertNotEquals(metaDescription, expectedMetaDescription, String.format("META_DESCRIPTION validation failed. Expected[%s] but found[%s]", expectedMetaDescription, metaDescription));
        }
    },

    REL_CANONICAL (SeoValidationType.REL_CANONICAL) {
        @Override
        public <T extends SeoData> void validate(T data) {
            Elements canonicals = data.getDocument().select("link[rel=\"canonical\"]");
            Assert.assertTrue(canonicals.size() <= 1, "REL_CANONICAL validation failed. REL_CANONICAL is unique");
            Assert.assertEquals(canonicals.get(0).attr("href"), BASE_URL + data.getExpectedData().get("pagePath").toString(), "REL_CANONICAL validation failed. Canonical href value");
        }
    },

    ANCHOR (SeoValidationType.ANCHOR) {
        @Override
        public <T extends SeoData> void validate(T data) {
            Elements anchors = data.getDocument().getElementsByTag("a");
            for(Element anchor : anchors) {
                String href = anchor.attr("href");
                Assert.assertFalse(href.contains("undefined"), String.format("ANCHOR validation failed. Href contains the value 'undefined' -> [%s]", anchor));
                Assert.assertFalse(href.startsWith("/"), String.format("ANCHOR validation failed: href value starts with '/' -> [%s]", anchor));
            }
        }
    },

    NOINDEX (SeoValidationType.NOINDEX) {
        @Override
        public <T extends SeoData> void validate(T data) {
            Elements noindex  = data.getDocument().getElementsByTag("noindex");
            Assert.assertEquals(noindex.size(), 0, "NOINDEX validation failed. Number of tag <noindex>");
            Elements meta = data.getDocument().select("meta[content=\"noindex\"]");
            Assert.assertEquals(meta.size(), 0, "Number of tag <meta content=\"noindex\">");
        }
    },

    IMAGE (SeoValidationType.IMAGE) {
        @Override
        public <T extends SeoData> void validate(T data) {
            //TODO
        }
    };

    private static final Logger logger = LoggerFactory.getLogger(HtmlSeoValidationStrategy.class);
    private SeoValidationType seoValidationType;

    HtmlSeoValidationStrategy(SeoValidationType seoValidationType){
        this.seoValidationType = seoValidationType;
    }

    public SeoValidationType getValidationType(){
        return seoValidationType;
    }

}
