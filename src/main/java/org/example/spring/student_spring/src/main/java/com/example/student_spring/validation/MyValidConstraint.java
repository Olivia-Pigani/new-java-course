package com.example.student_spring.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MyValidConstraint implements ConstraintValidator<MyValid,String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }

    @Override
    public void initialize(MyValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
