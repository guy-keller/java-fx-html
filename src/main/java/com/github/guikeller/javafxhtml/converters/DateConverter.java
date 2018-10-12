package com.github.guikeller.javafxhtml.converters;

import java.time.Instant;

public class DateConverter implements Converter<Instant> {

    public DateConverter() {
        super();
    }

    @Override
    public Instant convert(Object obj) {
        String value = (String) obj;
        Long epochSecs = Long.parseLong(value);
        return Instant.ofEpochSecond(epochSecs);
    }

    @Override
    public String asString(Object obj) {
        Instant value = (Instant) obj;
        Long epochSecs = value.getEpochSecond();
        return Long.toString(epochSecs);
    }

}
