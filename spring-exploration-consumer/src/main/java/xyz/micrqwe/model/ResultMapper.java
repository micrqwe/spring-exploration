package xyz.micrqwe.model;

/**
 * Created by le on 2017/5/9.
 */
public class ResultMapper {
    private Integer code;
    private String msg;
    private boolean raw;
    private Object data;

    public ResultMapper(Integer code, String msg, boolean raw, Object data) {
        this.code = code;
        this.msg = msg;
        this.raw = raw;
        this.data = data;
    }

    public ResultMapper(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultMapper(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResultMapper(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultMapper(Integer code, String msg, boolean raw) {
        this.code = code;
        this.msg = msg;
        this.raw = raw;
    }

    public ResultMapper(Integer code) {
        this.code = code;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
