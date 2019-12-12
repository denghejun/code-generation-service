package com.spike.codegenerationservice.component.peon.abstraction;

public abstract class Peon<T, R> {
    public abstract R build(T food);
}
