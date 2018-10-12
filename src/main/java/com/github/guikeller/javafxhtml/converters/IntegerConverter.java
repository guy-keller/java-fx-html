package com.github.guikeller.javafxhtml.converters;

public class IntegerConverter implements Converter<Integer> {

    public IntegerConverter() {
        super();
    }

    @Override
    public Integer convert(Object obj) {
        String value = (String) obj;
        return new Integer(value);
    }

    @Override
    public String asString(Object obj) {
        Integer value = (Integer) obj;
        return Integer.toString(value);
    }

}
