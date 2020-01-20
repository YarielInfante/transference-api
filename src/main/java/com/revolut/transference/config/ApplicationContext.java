package com.revolut.transference.config;


import com.revolut.transference.service.ICustomerService;
import com.revolut.transference.service.ITopUpService;
import com.revolut.transference.service.ITransferService;
import com.revolut.transference.service.implentation.CustomerService;
import com.revolut.transference.service.implentation.TopUpService;
import com.revolut.transference.service.implentation.TransferService;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is in charge of creating the application context by creating instance of services.</p>
 *
 * @see com.revolut.transference.annotation.Component
 */
public class ApplicationContext {

    private static ApplicationContext applicationContext;
    private static Map<String, Object> components = new HashMap<>();

    private ApplicationContext() {
        components.put(ICustomerService.class.getCanonicalName(), new CustomerService());
        components.put(ITopUpService.class.getCanonicalName(), new TopUpService());
        components.put(ITransferService.class.getCanonicalName(), new TransferService());

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
        return (T) components.get(cl.getCanonicalName());
    }

    public Object getComponent(String name) {
        return components.get(name);
    }
}
