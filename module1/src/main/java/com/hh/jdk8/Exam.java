package com.hh.jdk8;

import org.junit.Test;

/**
 * n 重循环
 * @author HaoHao
 * @date 2019-04-2314:08
 */
public class Exam {

    @Test
    public void test() {
        int[] arr = new int[3];
        arr[0] = 2;
        arr[1] = 2;
        arr[2] = 2;
        loop(arr);
    }

    // 进位器 输入[2,2] 看成十进制的十位和个位 遇到2进1
    // O(n^2)
    void loop(int a[]) {
        // 循环次数
        int sum = 1;
        int n = a.length;
        // 打印数组
        int b[] = new int[n];
        for (int i = 0; i < n; i++) {
            sum *= a[i];
            // 第一行都是0
            b[i] = 0;
        }
        for (int i = 0; i < sum; i++) {
            System.out.print("[");
            // 打印这一行
            for (int j = 0; j < n; j++) {
                if (j <n-1) {
                    System.out.print(b[j] + ",");
                } else {
                    System.out.print(b[j]);
                }
            }
            System.out.println("]");
            b[n - 1]++;//前一位增1
            for (int j = n - 1; j > 0; j--) {
                // 是否需要进位
                if (b[j] >= a[j]) {
                    b[j] = 0;
                    b[j - 1]++;
                }
            }
        }
    }

}
