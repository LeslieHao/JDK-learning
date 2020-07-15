package com.hh.base;

import com.hh.common.type.User;
import org.junit.Test;

import java.util.Optional;

/**
 * @author HaoHao
 * @date 2018/12/22下午5:31
 */
public class mOptional {

    @Test
    public void optional() {
        User u1 = getUser();
        User u2 = new User();
        // 不论u1 是否为空,都会执行create 方法,但是u1 不为空仍返回u1
        Optional.ofNullable(u1).orElse(createNewUser());

        // 如果为空执行方法,不为空直接返回u1
        Optional.ofNullable(u1)
                .orElseGet(this::createNewUser);

        // 如果存在执行方法
        Optional.ofNullable(u1)
                .ifPresent(user -> System.out.println(user.getName()));

        // 如果为空抛出异常
        Optional.ofNullable(u1)
                .orElseThrow(RuntimeException::new);

        // 如果u1存在,且u1.getName不为空,返回,否则返回JACK
        Optional.ofNullable(u1)
                .flatMap(user -> Optional.ofNullable(user.getName())).orElse("JACK");

        byte[] bytes = Optional.ofNullable(u1)
                .map(User::getName)
                .map(String::getBytes)
                .orElse(null);

        String s1 = Optional.ofNullable(u1)
                .flatMap(user -> Optional.ofNullable(user.getName()))
                .map(s -> s)
                .orElse("");
    }


    private User createNewUser() {
        System.out.println("create new user");
        return new User("new",1);
    }

    private User getUser() {
        return new User("Tom", 1);
    }

}
