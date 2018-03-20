package com.yanzi.common.controller.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.yanzi.common.controller.validator.NotEmpty;

public class LongArrayNotEmptyValidator implements ConstraintValidator<NotEmpty, long[]> {

    @Override
    public void initialize(NotEmpty notNull) {
    }

    @Override
    public boolean isValid(long[] param, ConstraintValidatorContext context) {
        if (param == null || param.length == 0) {
            return false;
        }
        return true;
    }

}
