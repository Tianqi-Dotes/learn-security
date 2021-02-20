package com.imooc.uaa.annotation;

import com.imooc.uaa.validation.PasswordValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.annotation
 * @date 2021-02-20 10:16
 * @Copyright © 2018-2019 *******
 */
@Target({ElementType.ANNOTATION_TYPE,ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidation.class)
public @interface PasswordAnno {
    String message() default "invalid password";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
