package org.example.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 基于Guava实现的布隆过滤器
 *
 *
 */
public class MethodOneGuava {


    /**
     * 预计药插入的数据量
     */
    private static int size = 100000;


    /**
     * 期望的误判率
     */
    private static double fpp = 0.001;


    /**
     * 创建布隆过滤器
     */
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),size,fpp);


    public static void main(String[] args) {
        // 插入10W的样本数据
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        int count = 0;
        for (int i = size; i < (2 * size); i++) {
            if(bloomFilter.mightContain(i)) {
                count++;
                System.out.println(i + "误判了");
            }
        }
        System.out.println("数据总量为： " + size + "误判数量为: " + count);
    }

}
