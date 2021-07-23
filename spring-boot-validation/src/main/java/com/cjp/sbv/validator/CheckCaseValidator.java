package com.cjp.sbv.validator;

import com.cjp.sbv.annotation.CheckCase;
import com.cjp.sbv.entity.CaseMode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {
    private CaseMode caseMode;

    @Override
    public void initialize(CheckCase checkCase) {
        this.caseMode = checkCase.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        if (caseMode == CaseMode.UPPER) return value.equals(value.toUpperCase());
        return value.equals(value.toLowerCase());
    }
}