package top.mty.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author mty
 * @Date 2021/4/19
 * @Description response
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private static final Object SUCCESS = 0;

    public R() {
        this.put("code", SUCCESS);
    }

    public Object getData() {
        return this.get("data");
    }

    public String getMsg() {
        return this.get("msg") == null ? null : String.valueOf(this.get("msg"));
    }

    public Object getCode() {
        return this.get("code");
    }

    public static R error() {
        return error(1, "An error occurred.");
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok() {
        R r = new R();
        r.put("msg", "success");
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.put("data", data);
        return r;
    }





}
