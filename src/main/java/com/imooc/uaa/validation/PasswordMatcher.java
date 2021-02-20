package com.imooc.uaa.validation;

import com.imooc.uaa.annotation.PasswordAnno;
import com.imooc.uaa.annotation.PasswordMatch;
import com.imooc.uaa.domain.dto.UserDetails;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.validation
 * @date 2021-02-20 10:57
 * @Copyright © 2018-2019 *******
 */
public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserDetails> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserDetails details, ConstraintValidatorContext constraintValidatorContext) {
        return details.getPassword().equals(details.getPassword2());
    }
}
