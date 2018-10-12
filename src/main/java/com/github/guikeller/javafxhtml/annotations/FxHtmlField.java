package com.github.guikeller.javafxhtml.annotations;

import com.github.guikeller.javafxhtml.converters.Converter;
import com.github.guikeller.javafxhtml.converters.StringConverter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Specifies that a field in the POJO is displayed / used in the view (HTML)
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface FxHtmlField {

    /**
     * It must match the HTML input id attribute
     *
     * @return String
     */
    String elementId();

    /**
     * What converter to be utilized.
     * By default uses {@link StringConverter}
     *
     * @return Class<? extends Converter<?>>
     */
    Class<? extends Converter<?>> converter() default StringConverter.class;

}
