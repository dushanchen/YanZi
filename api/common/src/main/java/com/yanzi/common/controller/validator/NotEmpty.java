package com.yanzi.common.controller.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.yanzi.common.controller.validator.impl.IntegerArrayNotEmptyValidator;
import com.yanzi.common.controller.validator.impl.LongArrayNotEmptyValidator;
import com.yanzi.common.controller.validator.impl.NotEmptyValidator;
import com.yanzi.common.controller.validator.impl.StringArrayNotEmptyValidator;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { NotEmptyValidator.class, StringArrayNotEmptyValidator.class,
        IntegerArrayNotEmptyValidator.class, LongArrayNotEmptyValidator.class })
public @interface NotEmpty {
    String message() default "1: {value}字段为空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "";
}
