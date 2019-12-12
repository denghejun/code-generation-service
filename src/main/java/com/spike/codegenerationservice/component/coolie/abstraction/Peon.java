package com.spike.codegenerationservice.component.coolie.abstraction;

public abstract class Peon<T, R> {
    public abstract R build(T food);
}
