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

    TITLE(SeoValidationType.TITLE) {
        @Override
        public <T extends SeoData> void validate(T data) {
            String pageType = data.isRendered() ? "rendered" : "source";
            String title = data.getDocument().title();
            String expectedTitle = data.getExpectedData().get("title").toString();
            Assert.assertNotEquals(title, "", String.format("Page type = %s. TITLE validation failed. Found empty title", pageType));
            Assert.assertEquals(title, expectedTitle, String.format("Page type = %s. TITLE validation failed. Title", pageType));
        }
    },

    HEADER1(SeoValidationType.HEADER1) {
        @Override
        public <T extends SeoData> void validate(T data) {
            String pageType = data.isRendered() ? "rendered" : "source";
            Elements headings = data.getDocument().getElementsByTag("h1");
            Assert.assertEquals(headings.size(), 1, String.format("Page type = %s. HEADER1 validation failed. Tag <h1> should be unique. Number of <h1> tags", pageType));
            Assert.assertEquals(headings.get(0).text(), data.getExpectedData().get("h1").toString(), String.format("Page type = %s. HEADER1 validation failed. <h1> text", pageType));
        }
    },

    META_DESCRIPTION(SeoValidationType.META_DESCRIPTION) {
        @Override
        public <T extends SeoData> void validate(T data) {
            String pageType = data.isRendered() ? "rendered" : "source";
            Elements metaDescriptions = data.getDocument().select("meta[name=\"description\"]");
            Assert.assertEquals(metaDescriptions.size(), 1, String.format("Page type = %s. META_DESCRIPTION validation failed. Meta description should be unique. Number of meta description", pageType));
            String metaDescription = metaDescriptions.get(0).attr("content").trim();
            String expectedMetaDescription = data.getExpectedData().get("metaDescription").toString().trim();
            Assert.assertEquals(metaDescription, expectedMetaDescription, String.format("Page type = %s. META_DESCRIPTION validation failed.", pageType));
        }
    },

    REL_CANONICAL(SeoValidationType.REL_CANONICAL) {
        @Override
        public <T extends SeoData> void validate(T data) {
            String pageType = data.isRendered() ? "rendered" : "source";
            Elements canonicals = data.getDocument().select("link[rel=\"canonical\"]");
            Assert.assertEquals(canonicals.size(),  1, String.format("Page type = %s. REL_CANONICAL validation failed. Rel canonical should be unique. Number of rel_canonical", pageType));
            Assert.assertEquals(canonicals.get(0).attr("href"), BASE_URL + data.getExpectedData().get("pagePath").toString(), String.format("Page type=[%s]. REL_CANONICAL validation failed. Canonical href value", pageType));
        }
    },

    ANCHOR(SeoValidationType.ANCHOR) {
        @Override
        public <T extends SeoData> void validate(T data) {
            String pageType = data.isRendered() ? "rendered" : "source";
            Elements anchors = data.getDocument().getElementsByTag("a");

            boolean areUrlsWellFormed = true;
            boolean doUrlsNotContainUndefined = true;

            // Check malformed urls
            for (Element anchor : anchors) {
                if (anchor.attr("href").startsWith("/http")) {
                    areUrlsWellFormed = false;
                    break;
                }
            }

            // Check undefined values
            for (Element anchor : anchors) {
                if (anchor.attr("href").contains("undefined")) {
                    doUrlsNotContainUndefined = false;
                    break;
                }
            }

            Assert.assertTrue(areUrlsWellFormed && doUrlsNotContainUndefined,
                    String.format("Page type = %s. ANCHOR validation failed. Urls are well formed = %s - Urls do not contain 'undefined' = %s. Anchors are valid", pageType, areUrlsWellFormed, doUrlsNotContainUndefined));
        }
    },

    NOINDEX(SeoValidationType.NOINDEX) {
        @Override
        public <T extends SeoData> void validate(T data) {
            String pageType = data.isRendered() ? "rendered" : "source";
            Elements noindex = data.getDocument().getElementsByTag("noindex");
            Assert.assertEquals(noindex.size(), 0, String.format("Page type = %s. NOINDEX validation failed. Number of tag <noindex>", pageType));
            Elements meta = data.getDocument().select("meta[content=\"noindex\"]");
            Assert.assertEquals(meta.size(), 0, String.format("Page type = %s. Number of tag <meta content=\"noindex\">", pageType));
        }
    },

    IMAGE(SeoValidationType.IMAGE) {
        @Override
        public <T extends SeoData> void validate(T data) {
            //TODO in attesa delle specifiche
        }
    };

    private static final Logger logger = LoggerFactory.getLogger(HtmlSeoValidationStrategy.class);
    private SeoValidationType seoValidationType;

    HtmlSeoValidationStrategy(SeoValidationType seoValidationType) {
        this.seoValidationType = seoValidationType;
    }

    public SeoValidationType getValidationType() {
        return seoValidationType;
    }

}
