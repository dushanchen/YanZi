package com.yanzi.common.controller.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.yanzi.common.controller.validator.impl.LongArrayValidator;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { LongArrayValidator.class })
public @interface Array {
    
    String message() default "{value}字段为空";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String value() default "";
}
