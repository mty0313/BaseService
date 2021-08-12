package top.mty.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;

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

    public static R ok(Object... data) {
        R r = new R();
        List<Object> dataList = new ArrayList<>(Arrays.asList(data));
        r.put("data", dataList);
        return r;
    }

    /**
     * 包装List数据为前端适合的格式
     * @param records
     * @param req
     * @param total
     * @return
     */
    public static R listDataWrapper(List<?> records, Object req, Long total) {

        Map<String, Object> data = new HashMap<>();

        data.put("total", total);

        Class<?> clazz = req.getClass();

        Field[] fields = clazz.getDeclaredFields();

        int current = -1;

        int pageSize = -1;

        try {
            for (Field field: fields) {
                if (field.getName().equals("pageIndex")) {
                    Method m = req.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    current = (Integer) m.invoke(req);
                }
                if (field.getName().equals("pageSize")) {
                    Method m = req.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    pageSize =  (Integer) m.invoke(req);
                }
            }
        } catch (Exception e) {
            return R.error();
        }

        if (current > -1 && pageSize > -1) {
            data.put("total", total);
            data.put("size", pageSize);
            data.put("current", current);
            data.put("pages", total / pageSize + 1);
            data.put("records", records);
        }

        return R.ok(data);
    }

    public static String getMethodName(String fieldName) {
        byte[] items = fieldName.getBytes(StandardCharsets.UTF_8);
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }





}
