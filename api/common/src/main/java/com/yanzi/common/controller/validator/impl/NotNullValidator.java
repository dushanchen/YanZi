package com.yanzi.common.controller.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.yanzi.common.controller.validator.NotNull;

public class NotNullValidator implements ConstraintValidator<NotNull, Object> {

    @Override
    public void initialize(NotNull notNull) {
    }

    @Override
    public boolean isValid(Object param, ConstraintValidatorContext context) {
        return param != null;
    }
}
