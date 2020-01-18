package com.revolut.transference.util;

import com.revolut.transference.annotation.*;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * It scans packages to look for classes annotated with @Component to instantiate them.
 * If one of the classes has a property annotated with @Autowired it instantiate the class of the property.
 *
 * @see Component
 * @see Autowired
 */
public class ComponentScannator {


    public Map<String, Object> components = new HashMap<>();
    private Map<String, Class> eligibleComponents = new HashMap<>();

    public Map<String, Object> getComponents() {
        return components;
    }

    /**
     * Scans packages for @Component annotated classes.
     *
     * @param packageRoute the root route you want to scan
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void scan(String packageRoute) throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections(packageRoute);
        Set<Class<?>> typesAnnotatedWithComponents = reflections.getTypesAnnotatedWith(Component.class);

        typesAnnotatedWithComponents.forEach(cl -> {
            eligibleComponents.put(cl.getCanonicalName(), cl);
            Type[] genericInterfaces = cl.getGenericInterfaces();
            for (Type type : genericInterfaces) {
                if (type instanceof ParameterizedType) {
                    eligibleComponents.put(((ParameterizedType) type).getRawType().getTypeName(), cl);
                } else {
                    eligibleComponents.put(((Class) type).getCanonicalName(), cl);
                }
            }

        });

        for (Class<?> cl : typesAnnotatedWithComponents) {

            setClass(cl);
        }
    }

    private void setClass(Class cl) throws IllegalAccessException, InstantiationException {
        Field[] declaredFields = cl.getDeclaredFields();
        Object newInstance = cl.newInstance();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Autowired) {
                    Autowired autowired = (Autowired) annotation;

                    Object o;

                    if (!autowired.value().isEmpty()) {
                        o = components.get(autowired.value());

                    } else {
                        o = components.get(field.getType().getCanonicalName());
                    }

                    if (o != null) {
                        field.set(newInstance, o);
                    } else {
                        Class aClass = eligibleComponents.get(field.getType().getCanonicalName());
                        setClass(aClass);

                        if (!autowired.value().isEmpty()) {
                            o = components.get(autowired.value());
                            if (o == null)
                                throw new NullPointerException("Name of component does not exist: " + autowired.value());

                        }

                        o = components.get(field.getType().getCanonicalName());

                        field.set(newInstance, o);
                    }
                }
            }
        }

        Component component = (Component) cl.getAnnotation(Component.class);

        if (!component.value().isEmpty()) {
            components.put(component.value(), newInstance);
        }

        Type[] genericInterfaces = cl.getGenericInterfaces();

        if (genericInterfaces != null && genericInterfaces.length > 0) {

            for (Type type : genericInterfaces) {

                if (type instanceof ParameterizedType) {
                    components.put(((ParameterizedType) type).getRawType().getTypeName(), newInstance);
                } else {
                    components.put(((Class) type).getCanonicalName(), newInstance);
                }
            }
        } else {
            components.put(cl.getCanonicalName(), newInstance);
        }


    }


}
