package com.imooc.uaa.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.exception
 * @date 2021-02-20 11:22
 * @Copyright © 2018-2019 *******
 */
@ControllerAdvice
public class ExceptionHandler implements ProblemHandling {
    @Override
    public boolean isCausalChainsEnabled() {
        return false;//是否返回异常trace
    }
}
