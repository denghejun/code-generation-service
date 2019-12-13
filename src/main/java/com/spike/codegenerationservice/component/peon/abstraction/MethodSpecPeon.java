package com.spike.codegenerationservice.component.peon.abstraction;

import com.spike.codegenerationservice.model.DataColumn;
import com.spike.codegenerationservice.model.DataTable;
import com.squareup.javapoet.*;
import lombok.var;

public abstract class MethodSpecPeon extends Peon<DataTable, MethodSpec> {
    protected static final String PARAMETER_NAME_ENTITY = "entity";
    protected static final String ANNOTATION_MEMBER_VALUE = "value";
    protected static final String ANNOTATION_MEMBER_VALUE_FORMAT = "$S";

    protected TypeName ofListParameterType(Class<?> clazz) {
        ClassName entityClass = ClassName.get(clazz);
        ClassName list = ClassName.get("java.util", "List");
        ClassName arrayList = ClassName.get("java.util", "ArrayList");
        return ParameterizedTypeName.get(list, entityClass);
    }

    protected void populateParametersBaseOnPrimaryColumns(DataTable table, MethodSpec.Builder builder) {
        var primaryColumns = table.getPrimaryColumns();
        for (DataColumn c : primaryColumns) {
            builder.addParameter(ParameterSpec.builder(c.getClazz(), c.getName())
                    .build());
        }
    }
}
