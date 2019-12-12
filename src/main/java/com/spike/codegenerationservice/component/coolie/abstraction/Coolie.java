package com.spike.codegenerationservice.component.coolie.abstraction;

public abstract class Coolie<T, R> {
    public abstract R build(T food);
}
