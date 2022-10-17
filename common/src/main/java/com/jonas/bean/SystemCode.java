package com.jonas.bean;

/**
 * @author shenjy
 * @date 2020/8/13
 * @description 系统状态码
 */
public enum  SystemCode implements CodeStatus {
    SUCCESS("2000", "成功"),
    PARAM_ERROR("2001", "参数异常"),
    SERVER_ERROR("2002", "服务端异常"),
    ;

    private final String code;
    private final String message;

    SystemCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
