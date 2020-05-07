package com.nicehancy.mix.common.utils;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.expression.ExpressionLanguageGroovyImpl;

/**
 * 参数校验工具类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/5/5 18:57
 **/
@Slf4j
public class VerifyUtil {

    private static Validator validator = new Validator();

    static {
        validator.getExpressionLanguageRegistry().registerExpressionLanguage("groovy",
            new ExpressionLanguageGroovyImpl());
    }

    private VerifyUtil() {
    }

    /**
     * 1、请求参数非空、格式验证，请求对象
     *
     * @param object 请求校验参数
     */
    public static void validateObject(Object object) throws RuntimeException {
        List<ConstraintViolation> list = validator.validate(object);
        if (null != list && !list.isEmpty()) {
            throw new RuntimeException(list.get(0).getMessage());
        }
    }
}
