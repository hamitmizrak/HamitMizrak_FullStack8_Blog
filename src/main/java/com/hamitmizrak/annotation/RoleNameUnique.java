package com.hamitmizrak.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint( validatedBy = {RoleNameUniqueValidation.class})
public @interface RoleNameUnique {
    String message() default "{role.name.unique.validation.constraints.NotNull.messagee}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
