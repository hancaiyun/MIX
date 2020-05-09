package com.nicehancy.mix.common.constant;

/**
 * <p>
 *      应用相关常量
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/4/11 20:04
 **/
public final class ResultConstant {

    /**
     * 无参构造函数
     */
    private ResultConstant(){
    }

    /** 结果集code值的名称 */
    public static final String RESULT_CODE_NAME = "code";

    /** 结果集message值的名称 */
    public static final String RESULT_MESSAGE_NAME = "msg";

    /** 结果集请求参数值的名称 */
    public static final String RESULT_PARAM_NAME = "params";

    /** 结果集errors值的名称 */
    public static final String RESULT_ERRORS_NAME = "errors";

    /** 结果集data值的名称 */
    public static final String RESULT_DATA_NAME = "data";

    /** 分页总条数值名称 */
    public static final String COUNT = "count";

    /*************************返回编码及页面****************************************/
    /** 通用访问并且处理成功时的编码 */
    public static final String SUCCESS_CODE = "0000";

    /** 通用处理失败时的编码 */
    public static final String DONE_FAILED_CODE = "0001";

    /** 通用访问失败时的编码 */
    public static final String FAILURE_CODE = "1111";

    /** 404的编码 */
    public static final String ERROR_404_CODE = "404";

    /** 404错误页 */
    public static final String ERROR_404_PAGE = "/page/404.html";

    /** 成功页 */
    public static final String SUCCESS_PAGE = "/page/welcome.html";

}
