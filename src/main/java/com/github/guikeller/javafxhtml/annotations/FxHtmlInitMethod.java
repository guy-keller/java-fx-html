package com.github.guikeller.javafxhtml.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Specifies the method utilized to pre-populate the view (HTML)
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface FxHtmlInitMethod {

}
