package com.athome.cloudOrder.HandJava;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class MyLb implements LoadBalancer{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        //自旋操作
        //内部主要利用unsafe操作内存地址 current当前线程的值（也是期望值），会与内存中的该地址的值比较看是否相同，
        // 判断是否有其它线程修改此值, 没有进行更新返回true
        do{
            current = this.atomicInteger.get();
            next = current == Integer.MAX_VALUE ? 0 : current + 1;

        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("***********next"+next);
        return next;
    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size(); //取余数操作
        return serviceInstances.get(index);
    }
}
