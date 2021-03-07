package com.rgy.dice.util;

import java.util.function.Consumer;

public class GenericBuilder<C> {

    private C instance;

    private GenericBuilder(Class<C> clazz) {
        try {
            instance = clazz.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <C> GenericBuilder<C> build(Class<C> clazz) {
        return new GenericBuilder<>(clazz);
    }

    public GenericBuilder<C> with(Consumer<C> setter) {
        setter.accept(instance);
        return this;
    }

    public C toInstance() {
        return instance;
    }
}
