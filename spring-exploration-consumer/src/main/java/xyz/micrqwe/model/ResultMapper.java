package xyz.micrqwe.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by le on 2017/5/9.
 */
@Getter
@Setter
public class ResultMapper<T> {
    private Integer code;
    private String msg;
    private boolean raw;
    private T data;

    public ResultMapper() {

    }

    public ResultMapper(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isRaw() {
        return raw;
    }

    public void setRaw(boolean raw) {
        this.raw = raw;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
