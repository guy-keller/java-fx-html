package com.github.guikeller.javafxhtml.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Specifies a JavaFX Html Bridge Controller Method
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface FxControllerMethod {

}
