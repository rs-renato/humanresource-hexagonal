package br.com.hrs.core.config.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

public class HrsCoreSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes
                .fromMap(annotationMetadata.getAnnotationAttributes(EnableHrsCore.class.getName(), false));

        boolean loadMockRepository = attributes.getBoolean("loadMockRepository");
        boolean loadValidators = attributes.getBoolean("loadValidators");

        // create scanner and disable default filters (that is the 'false' argument)
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);

        // add include filters which matches all the classes
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*UseCase")));

        if (loadValidators){
            provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*Validator")));
        }

        if (loadMockRepository){
            provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*Repository")));
        }

        // get matching classes defined in the package
        final Set<BeanDefinition> classes = provider.findCandidateComponents("br.com.hrs.core");

        return readBeanDefinition(classes);
    }


    private String[] readBeanDefinition(Set<BeanDefinition> classes){

        Iterator<BeanDefinition> beans = classes.iterator();

        String[] imports = new String[classes.size()];

        // this is how you can load the class type from BeanDefinition instance
        for (int i = 0; i < imports.length ; i++) {
            imports[i] = beans.next().getBeanClassName();
        }

        return imports;
    }
}
