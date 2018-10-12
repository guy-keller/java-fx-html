package com.github.guikeller.javafxhtml;

import com.github.guikeller.javafxhtml.reflection.HtmlAnalyser;
import javafx.scene.web.WebEngine;
import org.springframework.context.ApplicationContext;

/**
 * JavaFX Html Bridge class for those using Java and Spring
 */
public class SpringJavaFxHtml extends CoreJavaFxHtml {

    private ApplicationContext context;

    /**
     * JavaFX Html Bridge class
     * @param webEngine JavaFX WebEngine
     * @param context Spring ApplicationContext
     */
    public SpringJavaFxHtml(WebEngine webEngine, ApplicationContext context) {
        super(webEngine);
        this.context = context;
    }

    /**
     * Invokes the given controller method
     * @param clazzName Controller fully qualified clazz name (eg: com.my.app.FooController.class)
     * @param methodName Method in the controller to be invoked (eg: saveFooInfo)
     * @return Object (recommended String formatted in JSON)
     */
    @Override
    public Object invokeController(String clazzName, String methodName) {
        try {
            Class<?> clazz = Class.forName(clazzName);
            Object springManagedControllerInstance = this.context.getBean(clazz);
            HtmlAnalyser htmlAnalyser = new HtmlAnalyser(super.webEngine);
            return htmlAnalyser.invokeController(springManagedControllerInstance, methodName);
        } catch (Exception ex) {
            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }

}
