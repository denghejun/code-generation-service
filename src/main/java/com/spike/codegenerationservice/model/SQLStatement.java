package com.spike.codegenerationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SQLStatement {
    public final String C;
    public final String R;
    public final String U;
    public final String D;
    public final String R_BY_ID;
}
