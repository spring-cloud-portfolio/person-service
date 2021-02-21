package com.doroshenko.serhey.person.core.annotation;

import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Sql("classpath:fixture/core/no_op.sql")
public @interface NoOpSql {
}
