package com.unimon.app.model.vo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import lombok.Data;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Role {
	
	public enum UserRole {
		ROLE_SUPER, 
		ROLE_ADMIN, 
		ROLE_USER
	}

	public String value();
	
}
