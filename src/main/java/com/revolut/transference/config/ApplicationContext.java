package com.revolut.transference.config;


import com.revolut.transference.util.ComponentScannator;

/**
 * <p>Use this class to access classes annotated with @Component.
 * This class is in charge of creating the application context by calling ComponentScannator to scan packages to look for classes annotated with @Component.</p>
 *
 * @see com.revolut.transference.annotation.Component
 */
public class ApplicationContext {

    private static ComponentScannator componentScannator;
    private static ApplicationContext applicationContext;

    private ApplicationContext() {
        try {
            componentScannator = new ComponentScannator();
            componentScannator.scan("com.revolut.transference");

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }


    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }


    /**
     * Gets the class desired from the context.
     *
     * @param cl  the class you want to look for
     * @param <T> Type of class passed
     * @return an instance of T
     */
    public <T> T getComponent(Class<T> cl) {
        return (T) componentScannator.getComponents().get(cl.getCanonicalName());
    }

    public Object getComponent(String name) {
        return componentScannator.getComponents().get(name);
    }
}
