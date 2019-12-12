package com.spike.codegenerationservice.component.coolie.abstraction;

import com.spike.codegenerationservice.model.DataTable;
import com.squareup.javapoet.MethodSpec;

public abstract class MethodSpecPeon extends Peon<DataTable, MethodSpec> {
    protected static final String PARAMETER_NAME_ENTITY = "entity";
    protected static final String ANNOTATION_MEMBER_VALUE = "value";
    protected static final String ANNOTATION_MEMBER_VALUE_FORMAT = "$S";
}
