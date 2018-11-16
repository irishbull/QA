package ta.test.impl.seo.validation;

import java.util.Iterator;
import java.util.Set;

import ta.test.impl.seo.validation.stragety.SeoValidationStrategy;

public class SeoValidationContext {

    private Set<SeoValidationStrategy> validationStrategies;

    public SeoValidationContext(Set<SeoValidationStrategy> validationStrategies) {
        this.validationStrategies = validationStrategies;
    }

    public void execute(SeoData seoData) {
        for (Iterator<SeoValidationStrategy> iterator = validationStrategies.iterator(); iterator.hasNext(); ) {
            iterator.next().validate(seoData);
        }
    }
}
