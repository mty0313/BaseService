package top.mty.proxy;

import java.lang.reflect.Proxy;

/**
 * @Author mty
 * @Date 2021/8/5 11:19
 * @Description 获取代理的工厂
 */
public class ProxyFactory {
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 目标类的类加载
                target.getClass().getInterfaces(), // 代理需要实现的接口
                new MybatisInvocationHandler(target)
        );
    }
}
