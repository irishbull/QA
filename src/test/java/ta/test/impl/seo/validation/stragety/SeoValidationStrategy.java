package ta.test.impl.seo.validation.stragety;

import ta.test.impl.seo.validation.SeoData;
import ta.test.impl.seo.validation.SeoValidationType;

public interface SeoValidationStrategy {
    <T extends SeoData> void validate(T data);
    SeoValidationType getValidationType();
}
