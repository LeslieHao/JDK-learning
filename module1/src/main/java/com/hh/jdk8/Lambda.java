package com.hh.jdk8;

import java.util.Optional;

/**
 * @author HaoHao
 * @date 2018/10/28下午10:47
 */
public class Lambda {

    // @FunctionalInterface
    // 函数式接口
    // Java 中的lambda 表达式可以理解为将一段代码赋值给一个变量, Java 中所有的lambda 都是接口类型
    // 作用: 1.代码更加简洁
    Runnable runnable = ()
            -> System.out.println("Hi function");


    Action action =
            System.out::println;


    private static void me(Action a, String s) {
        a.say(s);
    }


    public static void test() {
        me(System.out::println, "Hello lambda");
    }

    @FunctionalInterface
    interface Action {
        void say(String s);
    }


    String str;

    Optional<String> opt = Optional.ofNullable(str);

    String getStr() {
        // 存在做操作
        opt.ifPresent(System.out::println);
        // 不存在 通过函数产生
        return opt.orElseGet(() -> "get val");
        // 不存在返回默认值
//        return opt.orElse("不存在");
    }


    static String getUser(User user) {
        Optional<User> opt = Optional.ofNullable(user);
        return opt.map(u -> user.getName()).map(String::toLowerCase)
                .orElse(null);

        // 相当于
//        if (user == null) {
//            return null;
//        } else {
//            if (user.getName() != null) {
//                return user.getName().toLowerCase();
//            } else {
//                return null;
//            }
//        }
    }

    public static void main(String[] args) {
        System.out.println(getUser(null));
        System.out.println(getUser(new User()));
        System.out.println(getUser(new User().setName("Jack")));
    }


    static class User {

        String name;

        String age;

        public String getName() {
            return name;
        }

        public User setName(String name) {
            this.name = name;
            return this;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

}
