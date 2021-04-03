package io.netty;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 就像调用本地方法一样调用远程的方法，面向java中的就是所谓的面向interface开发
 *
 */
public class MyRPCTest {

    /**
     * 模拟客户端 consumer
     */
    @Test
    public void get(){
        Car car = proxyGet(Car.class);// 基于动态代理实现
        car.ooxx("hello");

        Fly fly = proxyGet(Fly.class);
        fly.xxoo("hello");
    }

    public static <T> T proxyGet(final Class<T> interfaceInfo){
    // 实现各个版本的动态代理
        ClassLoader classLoader = interfaceInfo.getClassLoader();
        final Class<?>[] methodInfo = {interfaceInfo};
        return (T)Proxy.newProxyInstance(classLoader, methodInfo, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 如何设置我们的consumer，对于provider的调用过程
                // 1.调用的服务，方法，参数，封装成 message
                String name = interfaceInfo.getName();
                String methodName = method.getName();
                // 2.requestID+message，本地要缓存的
                // 3.连接池，取得连接
                // 4.发送，走IO出去
                //4.如果从IO回来了，怎么将代码执行到这里，睡眠、回调，如何让线程停下来，而且还能继续
                return null;
            }
        });

    }

    interface Fly{
        void xxoo(String msg);
    }

    interface  Car{
        public void ooxx(String msg);
    }
}


