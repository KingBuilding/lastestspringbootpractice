package com.test.demo.nospring;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 金🗡
 * @date 2020/4/10 18:47
 * @description: 测试分布式追踪号在线城池类的透传
 * @see TransmittableThreadLocal
 */
public class TrackTransmitInThread {

    private static ThreadLocal local = new InheritableThreadLocal();
    static ExecutorService executors = Executors.newFixedThreadPool(4);
    static TransmittableThreadLocal<String> parent = new TransmittableThreadLocal();


    /**
     * 测试InheritableThreadLocal 传参以及线程池的情况
     *
     * @throws InterruptedException
     */
    @Test
    public void testInheritableThreadLocal() throws InterruptedException {
        local.set("我是父线程的参数");
        while (true) {
            Runnable runnable = () -> {
                System.out.println("子线程:" + local.get() + Thread.currentThread().getName());
                local.remove();//当前子线程清空属性
            };
            executors.execute(runnable);
            TimeUnit.SECONDS.sleep(1);
        }

    }

    /**
     * 测试TransmittableThreadLocal传参以及线程池的情况
     *
     * @throws InterruptedException
     */
    @Test
    public static void testTransmittableThreadLocal() throws InterruptedException {
        parent.set("value-set-in-parent");
        while (true) {
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName() + ";" + parent.get());
                parent.remove();
            };
            // 额外的处理，生成修饰了的对象ttlRunnable
            Runnable ttlRunnable = TtlRunnable.get(task);
            executors.submit(ttlRunnable);
            TimeUnit.SECONDS.sleep(1);

        }
    }

    /**
     * Agent 代理
     * 测试TransmittableThreadLocal传参以及线程池的情况
     *
     * @throws InterruptedException
     */
    @Test
    public void testAgentTransmittableThreadLocal() throws InterruptedException {
        parent.set("value-set-in-parent");
        while (true) {
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName() + ";" + parent.get());
                parent.remove();
            };
            // 额外的处理，生成修饰了的对象ttlRunnable
            executors.submit(task);
            TimeUnit.SECONDS.sleep(1);

        }
    }

    public static void main(String[] args) throws InterruptedException {
        parent.set("value-set-in-parent");
        while (true) {
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName() + ";" + parent.get());
                parent.remove();
            };
            // 额外的处理，生成修饰了的对象ttlRunnable
            executors.submit(task);
            TimeUnit.SECONDS.sleep(1);
        }


    }


}
