package br.com.hrs.persistence.config.annotation;

import br.com.hrs.persistence.config.HrsDataBaseConfiguration;
import br.com.hrs.persistence.config.HrsJdbcConfiguration;
import br.com.hrs.persistence.config.HrsJpaConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class HrsPersistenceConfigurationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes
                .fromMap(annotationMetadata.getAnnotationAttributes(EnableHrsPersistence.class.getName(), false));

        PersistenceType type = attributes.getEnum("type");

        switch (type){
            case DATASOURCE: return new String[] {HrsDataBaseConfiguration.class.getName()};
            case JPA: return new String[] {HrsJpaConfiguration.class.getName()};
            case JDBC: return new String[] {HrsJdbcConfiguration.class.getName()};
            default: return new String[0];
        }
    }
}
