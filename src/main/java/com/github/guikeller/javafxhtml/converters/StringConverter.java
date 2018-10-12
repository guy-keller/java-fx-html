package com.github.guikeller.javafxhtml.converters;

public class StringConverter implements Converter<String> {

    public StringConverter() {
        super();
    }

    @Override
    public String convert(Object obj) {
        return (String) obj;
    }

    @Override
    public String asString(Object obj) {
        return (String) obj;
    }

}
