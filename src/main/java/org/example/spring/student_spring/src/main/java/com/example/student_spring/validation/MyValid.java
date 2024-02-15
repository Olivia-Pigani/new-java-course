package com.example.student_spring.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Target;

@Constraint(validatedBy = MyValidConstraint.class)
public @interface MyValid {


    public String value() default "to";

    public String message() default "must contain 'to'";

    public Class<?>[] group() default {};

    public Class<? extends Payload>[] payload() default {};
}
