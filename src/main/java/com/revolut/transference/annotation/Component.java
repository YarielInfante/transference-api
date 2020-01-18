package com.revolut.transference.annotation;


import java.lang.annotation.*;

/**
 * Annotate classes with this with you want to be able to inject it later on.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    /**
     * Give the annotated class a name.
     *
     * @return the name of the component.
     */
    String value() default "";
}
