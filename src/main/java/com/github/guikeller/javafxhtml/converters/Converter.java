package com.github.guikeller.javafxhtml.converters;

/**
 * Common Converter interface - implementations provided are:
 * {@link BooleanConverter}
 * {@link DateConverter}
 * {@link DoubleConverter}
 * {@link IntegerConverter}
 * {@link StringConverter} (default)
 * <p>
 * More info:
 * https://docs.oracle.com/javase/8/javafx/embedded-browser-tutorial/js-commands.htm#JFXWV141
 *
 * @param <T>
 */
public interface Converter<T> {
    /**
     * Contract to convert between Object (normally a String) and T
     *
     * @param obj
     * @return T
     */
    public T convert(Object obj);

    /**
     * Contract to convert between T to its String value
     *
     * @param obj T Object
     * @return String
     */
    public String asString(Object obj);
}
