package com.yanzi.common.controller.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.yanzi.common.controller.validator.NotEmpty;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {

    @Override
    public void initialize(NotEmpty notNull) {
    }

    @Override
    public boolean isValid(String param, ConstraintValidatorContext context) {
        return param != null && !param.isEmpty();
    }

}
