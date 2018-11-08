package ta.test.impl.seo.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Set;
import ta.test.impl.seo.validation.stragety.SeoValidationStrategy;

public class SeoValidationContext {

    private static final Logger logger = LoggerFactory.getLogger(SeoValidationContext.class);
    private Set<SeoValidationStrategy> validationStrategies;

    public SeoValidationContext(Set<SeoValidationStrategy> validationStrategies){
        this.validationStrategies = validationStrategies;
    }

    public void execute(SeoData seoData) {
        SeoValidationStrategy seoValidationStrategy;
        for (Iterator<SeoValidationStrategy> iterator = validationStrategies.iterator(); iterator.hasNext();) {
            seoValidationStrategy = iterator.next();
            logger.info("{} - Validate [{}]", seoData.isRendered() ?  "Rendered page" : "Source page",  seoValidationStrategy.getValidationType());
            seoValidationStrategy.validate(seoData);
        }
    }
}
