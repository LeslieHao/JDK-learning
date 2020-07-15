package com.hh.base;

import org.junit.Test;

/**
 * @author HaoHao
 * @date 2019-06-26 11:30
 */
public class mString {

    @Test
    public void stringPool() {
        // 字符串常量池存储了字符串常量的引用.对象值实际还是存储在堆中的.

        String a = "abc";
        String b = "a" + new String("bc");
        String c = new String("abc");
        String d = "abc";
        String e = "a" + "b" + "c";
        System.out.println(a == b);
        System.out.println(a == c);
        // intern 方法会将指针指向常量池,如果常量池不存在则添加到常量池
        System.out.println(a == b.intern());
        System.out.println(a == c.intern());
        System.out.println(a == d);
        System.out.println(a == e);

    }
}
