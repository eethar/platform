package org.eethar.platform.core.repository.util.qualifier;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 *
 * @author superyass
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD, TYPE, PARAMETER})
public @interface RepositoryType {
    
    RepositoryTypeEnum value() default RepositoryTypeEnum.MONGODB;
    
    public enum RepositoryTypeEnum {
        MONGODB, MYSQL
    }
    
}
