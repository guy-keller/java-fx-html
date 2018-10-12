package com.github.guikeller.javafxhtml.reflection;

import com.github.guikeller.javafxhtml.annotations.FxControllerMethod;
import com.github.guikeller.javafxhtml.annotations.FxHtmlField;
import com.github.guikeller.javafxhtml.converters.Converter;
import javafx.scene.web.WebEngine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLInputElement;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HtmlAnalyser {

    private WebEngine engine;

    public HtmlAnalyser(WebEngine engine) {
        super();
        this.engine = engine;
    }

    public Object invokeController(Object controllerInstance, String methodName) throws Exception {
        Class<?> controllerClazz = controllerInstance.getClass();
        Method[] controllerMethods = controllerClazz.getDeclaredMethods();
        Method clazzMethod = findMethod(methodName, controllerMethods);

        Object methodParam = getMethodParam(clazzMethod);
        Object populatedParam = populateParam(methodParam);
        return clazzMethod.invoke(controllerInstance, populatedParam);
    }

    protected Method findMethod(String methodName, Method[] methods) {
        for (Method clazzMethod : methods) {
            clazzMethod.setAccessible(true);
            String clazzMethodName = clazzMethod.getName();
            if (clazzMethodName.equals(methodName) && clazzMethod.isAnnotationPresent(FxControllerMethod.class)) {
                return clazzMethod;
            }
        }
        throw new IllegalArgumentException("Method not found: " + methodName);
    }

    protected Object getMethodParam(Method method) throws Exception {
        Class<?> parameterType = method.getParameterTypes()[0];
        return parameterType.newInstance();
    }

    protected Object populateParam(Object paramInstance) throws Exception {
        Class<?> paramClazz = paramInstance.getClass();
        Field[] fields = paramClazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(FxHtmlField.class)) {
                populateField(field, paramInstance);
            }
        }
        return paramInstance;
    }

    protected void populateField(Field field, Object paramInstance) throws Exception {
        FxHtmlField fxHtmlField = field.getAnnotation(FxHtmlField.class);
        Object fieldValue = getValueFromWebEngine(fxHtmlField.elementId());
        if (fieldValue != null) {
            Converter<?> converter = instantiateConverter(fxHtmlField);
            Object convertedValue = converter.convert(fieldValue);
            field.set(paramInstance, convertedValue);
        }
    }

    protected Converter<?> instantiateConverter(FxHtmlField fxHtmlField) throws Exception {
        Class<?> converterClazz = fxHtmlField.converter();
        return (Converter<?>) converterClazz.newInstance();
    }

    protected Object getValueFromWebEngine(String elementId) {
        Document document = engine.getDocument();
        Element element = document.getElementById(elementId);
        if (element != null && element instanceof HTMLInputElement) {
            return ((HTMLInputElement) element).getValue();
        }
        return null;
    }

}
