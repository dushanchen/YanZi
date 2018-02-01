package com.yanzi.common.controller.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.yanzi.common.controller.validator.NotEmpty;

public class StringArrayNotEmptyValidator implements ConstraintValidator<NotEmpty, String[]> {

    @Override
    public void initialize(NotEmpty notNull) {
    }

    @Override
    public boolean isValid(String[] param, ConstraintValidatorContext context) {
        if (param == null || param.length == 0) {
            return false;
        }
        for (String p : param) {
            if (StringUtils.isEmpty(p)) {
                return false;
            }
        }
        return true;
    }

}
