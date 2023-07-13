package com.digital.gpt.chat.common.tools;

import com.digital.gpt.chat.common.tools.exception.ApiException;
import com.digital.gpt.chat.common.tools.exception.ParamErrorException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Set;

/**
 * 复制公共库代码，避免报错，忽略。
 *
 * @author lihuagang
 * @date 2023/7/13
 */
public class ValidatorUtils {

    private static final Validator VALIDATOR;

    static {
        VALIDATOR = Validation.byProvider(HibernateValidator.class)
                .configure()
                // 快速失败
                .failFast(true)
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * 示例：ValidatorUtils.validateEntity(user, CreateGroup.class);
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws ApiException 校验不通过，则报ApiException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws ApiException {
        Set<ConstraintViolation<Object>> constraintViolations = VALIDATOR.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            constraintViolations.stream().findFirst()
                    .map(ConstraintViolation::getMessage)
                    .ifPresent(msg -> {
                        throw new ParamErrorException(msg);
                    });
        }
    }

}
