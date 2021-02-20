package com.imooc.uaa.validation;

import com.imooc.uaa.annotation.PasswordAnno;
import lombok.val;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.EnglishSequenceData;
import org.passay.IllegalSequenceRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.WhitespaceRule;
import org.passay.spring.SpringMessageResolver;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Retention;
import java.util.Arrays;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.validation
 * @date 2021-02-20 10:15
 * @Copyright © 2018-2019 *******
 */
public class PasswordValidation implements ConstraintValidator<PasswordAnno,String> {
    @Resource
    SpringMessageResolver springMessageResolver;

    @Override
    public void initialize(PasswordAnno constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        val validator=new PasswordValidator(springMessageResolver,Arrays.asList(
            new LengthRule(8,30),
            new CharacterRule(EnglishCharacterData.UpperCase,1),
            new CharacterRule(EnglishCharacterData.LowerCase,1),
            new CharacterRule(EnglishCharacterData.Special,1),
            new IllegalSequenceRule(EnglishSequenceData.Alphabetical,5,false),//5个字母连续的
            new IllegalSequenceRule(EnglishSequenceData.Numerical,5,false),//5个数字连续的
            new IllegalSequenceRule(EnglishSequenceData.USQwerty,5,false),//键盘连续
            new WhitespaceRule()
        ));
        constraintValidatorContext.disableDefaultConstraintViolation();
        val result=validator.validate(new PasswordData(password));
        constraintValidatorContext.buildConstraintViolationWithTemplate(String.join(",",validator.getMessages(result)))
        .addConstraintViolation();//连接异常统一返回
        return result.isValid();
    }
}
