package com.github.guikeller.javafxhtml.reflection;

import com.github.guikeller.javafxhtml.annotations.FxHtmlField;
import com.github.guikeller.javafxhtml.annotations.FxHtmlInitMethod;
import com.github.guikeller.javafxhtml.converters.Converter;
import javafx.scene.web.WebEngine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLInputElement;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HtmlPopulator {

    private WebEngine engine;

    public HtmlPopulator(WebEngine engine) {
        super();
        this.engine = engine;
    }

    public void populatePage(Object controllerInstance) throws Exception {
        Class<?> controllerClazz = controllerInstance.getClass();
        Method[] methods = controllerClazz.getDeclaredMethods();
        Method methodToInvoke = findInitFxHtmlMethod(methods);
        if (methodToInvoke != null) {
            Object paramInstance = methodToInvoke.invoke(controllerInstance);
            populateView(paramInstance);
        }
    }

    protected Method findInitFxHtmlMethod(Method[] methods) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(FxHtmlInitMethod.class)) {
                return method;
            }
        }
        return null;
    }

    protected void populateView(Object paramInstance) throws Exception {
        if (paramInstance != null) {
            Class<?> paramClazz = paramInstance.getClass();
            Field[] fields = paramClazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(FxHtmlField.class)) {
                    populateFieldWithValue(field, paramInstance);
                }
            }
        }
    }

    protected void populateFieldWithValue(Field field, Object paramInstance) throws Exception {
        FxHtmlField fxHtmlField = field.getAnnotation(FxHtmlField.class);
        Converter<?> converter = instantiateConverter(fxHtmlField);
        Object value = field.get(paramInstance);
        if (value != null) {
            Element element = findElement(fxHtmlField);
            if (element instanceof HTMLInputElement) {
                String htmlValue = converter.asString(value);
                ((HTMLInputElement) element).setValue(htmlValue);
            }
        }
    }

    protected Element findElement(FxHtmlField fxHtmlField) {
        String elementId = fxHtmlField.elementId();
        Document document = this.engine.getDocument();
        return document.getElementById(elementId);
    }

    protected Converter<?> instantiateConverter(FxHtmlField fxHtmlField) throws Exception {
        Class<?> converterClazz = fxHtmlField.converter();
        return (Converter<?>) converterClazz.newInstance();
    }

}
