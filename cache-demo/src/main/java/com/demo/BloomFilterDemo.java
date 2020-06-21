package com.demo;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterDemo {

    private static int size = 5000;
    private static BloomFilter<Integer> intFilter = BloomFilter.create(Funnels.integerFunnel(), size);

    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            intFilter.put(i);
        }
        // 判断5000数字中是否包含200这个数
        if (intFilter.mightContain(20000)) {
            System.out.println("找到了");
        }
    }
}
