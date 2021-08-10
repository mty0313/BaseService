package top.mty.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author mty
 * @Date 2021/8/5 11:16
 * @Description mybatis方法代理
 */
public class MybatisInvocationHandler implements InvocationHandler {

    /**
     * 代理中的真实对象
     */
    private final Object target;

    public MybatisInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 调用前
        System.out.println("before");

        Object result = method.invoke(target, args);

        // 调用后
        System.out.println("after");

        return result;
    }
}
