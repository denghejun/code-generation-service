## JDK 1.8 is required
## Brief Instruction Guide

This project will generate CRUD DAO repository automatically based on JDBI SQL Object.

#### 1. How To Execute
These parameters below are all optional, you can pass them into maven.
```
<properties>
        <java.version>1.8</java.version>
        <tables>T_POLICY_PRD_LIAB_PARAM,T_POLICY_PROD_LIAB</tables>
        <jdbcDriver>oracle.jdbc.OracleDriver</jdbcDriver>
        <jdbcUrl>jdbc:oracle:thin:@10.137.195.24:31236/xe</jdbcUrl>
        <jdbcUser>system</jdbcUser>
        <jdbcPassword>oracle</jdbcPassword>
        <packageName>com.generation.model</packageName>
</properties>
```

e.g.
```
./mvnw clean install spring-boot:run -Dtables=MyCustomTableNameOnly
```


#### javapoet
https://github.com/square/javapoet

#### JDBI SQL Objects
http://jdbi.org/#_sql_objects

#### Else
 In spring boot application, all configuration value in `application.yml` can also be passed into.
 e.g.
 ```
./mvnw clean install spring-boot:run -Dmanagement.endpoint.restart.enabled=false
```
