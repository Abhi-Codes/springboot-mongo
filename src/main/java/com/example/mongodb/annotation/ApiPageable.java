package com.example.mongodb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
		@ApiImplicitParam(name = "page", dataTypeClass = Integer.class, paramType = "query", defaultValue = "1", value = "Page number"),
		@ApiImplicitParam(name = "size", dataTypeClass = Integer.class, paramType = "query", defaultValue = "20", value = "Records per page."),
		@ApiImplicitParam(name = "sort", allowMultiple = true, dataTypeClass = String.class, paramType = "query", value = "Sorting criteria. Format: 'property(,asc|desc)'.") })
public @interface ApiPageable {
}