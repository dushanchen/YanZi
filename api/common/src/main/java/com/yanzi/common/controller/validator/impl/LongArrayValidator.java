package com.yanzi.common.controller.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.yanzi.common.controller.validator.Array;

public class LongArrayValidator implements ConstraintValidator<Array, long[]> {

    @Override
    public void initialize(Array array) {
    }

    @Override
    public boolean isValid(long[] param, ConstraintValidatorContext context) {
        return true;
    }

}
