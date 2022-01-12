package com.stelpolvo.wiki.domain;

public class Resp {
    private final Integer code;
    private final String message;
    private final Object data;

    public Resp(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Resp ok(Object data) {
        return new Resp(200, "请求成功", data);
    }

    public static Resp ok(String message, Object data) {
        return new Resp(200, message, data);
    }

    public static Resp error(Object data) {
        return new Resp(200, "请求失败", data);
    }

    public static Resp error(String message, Object data) {
        return new Resp(200, message, data);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}