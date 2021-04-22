package com.jeanboy.app.performance;

/**
 * Created by jeanboy on 2021/4/15 15:40.
 */
public class Test {
    public static void main(String[] args) {
        // 0, 1, 2
        System.out.println(multiple(2, 2));
    }

    public static int multiple(int x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        int result = x;
        for (int i = 0; i < n - 1; i++) {
            result *= x;
        }
        return result;
    }
}
