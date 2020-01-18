package com.revolut.transference.annotation;


import java.lang.annotation.*;

/**
 * This annotation allows you to inject a dependency if the class you want to inject has been annotated with @Component
 *
 * @see Component
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    /**
     * Give the annotated class a name.
     *
     * @return the name of the component.
     */
    String value() default "";

}
