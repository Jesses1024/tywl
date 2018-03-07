package com.puxintech.tywl.resolver;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultPageRequest {

	int page() default 0;

	int size() default 10;

	String sortBy() default "";

	boolean ascending() default true;
}
