package com.ing.mwchapter.palindrome;

import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ToOptionalArgumentConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) {
        assertEquals(Optional.class, targetType, "Can only convert to String");

        if (source == null) {
            return Optional.empty();
        }

        return Optional.of(source);
    }

}
