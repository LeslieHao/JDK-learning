package com.hh.jdk8;

import com.hh.common.type.User;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.*;

/**
 * 流
 * 1.流不是数据结构
 * 2.它没有内部存储,它只是用操作管道从source(数据结构 数组 generate function IO)抓取数据
 * 3.它不会修改自己所封装的数据结构底层的数据. 例如filter 只会过滤后生成一个新的stream 而不是从source 删除元素
 * 4.所有stream 操作必须以lambda 为参数
 * 5.不支持索引访问 你可以访问第一个元素,但无法访问第二个第三个
 * 6.流的操作分为两种:
 *      1.Intermediate: 一个流后面可以跟多个Intermediate 操作,其目的主要是打开流 做出某种程度的映射/过滤等操作
 *        返回一个新流,交给下一个操作使用.这种操作都是惰性化的.
 *      2.一个流只能有一个 terminal 操作，当这个操作执行后，流就被使用“光”了，无法再被操作。所以这必定是流的最后一个操作。
 *        Terminal 操作的执行，才会真正开始流的遍历，并且会生成一个结果，或者一个 side effect。
 * 7.可以是无限的
 * @author HaoHao
 * @date 2018/12/23下午3:56
 */
public class mStream {

    /**
     * 流的构造
     */
    @Test
    public void stream_construct() {
        // 流的构造
        // 基本类型目前只有 int double long
        IntStream intStream = IntStream.of(1, 2, 3);
        DoubleStream doubleStream = DoubleStream.of(1, 2, 3);
        LongStream longStream = LongStream.of(1, 2, 3);

        // 对象类型
        Stream<String> streamObject = Stream.of("a", "b", "c");

        List list = new ArrayList();
        Stream listStream = list.stream();

        // 1-9
        IntStream.range(1, 10).forEach(System.out::print);
        // 1-10
        IntStream.rangeClosed(1, 10).forEach(System.out::print);
    }


    /**
     * 流转换为其他数据结构
     */
    @Test
    public void stream_convert() {
        // !! Stream 只能用一次 !!
        Stream<String> stream = Stream.of("a", "b", "c");
        // 转成数组
        String[] objects = stream.toArray(String[]::new);
        // 转成list
        List<String> list = stream.collect(Collectors.toList());
        // 转成String
        String string = stream.collect(Collectors.joining());
    }

    /**
     * 流的方法
     */
    @Test
    public void stream_method() {
        // !! Stream 只能用一次 !!
        Stream<String> stream = Stream.of("a", "b", "c");

        // map, 转换成大写 1对1的映射,每个输入元素转换成另外一个元素
        List<String> collect = stream.map(String::toUpperCase)
                .collect(Collectors.toList());

        // flatMap,二维的,一对多
        Stream<List<String>> streams = Stream.of(
                Arrays.asList("h", "e", "l", "l", "o", "!"),
                Arrays.asList(" ", "it's", "me")
        );
        // 将多个stream 放到同一个stream 中
        String streamWords = streams.flatMap(Collection::stream).collect(Collectors.joining());

        // filter
        Stream<String> streamFilter = Stream.of("I", "fuck", "love", "fuck", "u");
        String filterWord = streamFilter.filter(s -> !s.equals("fuck")).collect(Collectors.joining());

        // foreach, 但是流的foreach 不能修改自己包含的本地变量值,也不能用break 或者 return 结束循环
        Stream<String> lyric = Stream.of("hello", " ", "from", " ", "the", " ", "other", " ", "side", "!");
        lyric.forEach(System.out::print);

        // peek 可以在一个流中 执行两次
        String collect1 = Stream.of("p", "e", "e", "k")
                .filter(s -> s.equals("p") || s.equals("k"))
                .peek(s -> System.out.println("过滤:" + s))
                .map(String::toUpperCase)
                .peek(s -> System.out.println("map:" + s))
                .collect(Collectors.joining());

        // findFirst 返回第一个元素的 optional
        //Optional<String> first = stream.findFirst();

        // match
        List<User> list = new ArrayList<>();
        list.add(new User("Jack", 18));
        list.add(new User("Tom", 19));
        list.add(new User("Bob", 21));
        list.add(new User("Jordan", 22));

        // 是否年纪都大于20
        boolean b = list.stream().allMatch(user -> user.getAge() > 20);

        // 是否存在年龄大于20
        boolean b1 = list.stream().anyMatch(user -> user.getAge() > 20);

        // 是否不存在年龄大于20
        boolean b2 = list.stream().noneMatch(user -> user.getAge() > 20);
    }

    /**
     * 生成流
     */
    @Test
    public void stream_generate() {
        // 生成10个随机数
        Supplier<Integer> supplier = () -> new Random().nextInt();
        Stream.generate(supplier).limit(10).forEach(integer -> System.out.print(integer + ","));
    }

    /**
     * collectors
     */
    @Test
    public void stream_collectors() {
        List<User> list = new ArrayList<>();
        list.add(new User("Jack", 20));
        list.add(new User("Tom", 20));
        list.add(new User("Bob", 21));
        list.add(new User("Jordan", 22));
        list.add(new User("Paul", 22));
        list.add(new User("Paul", 17));
        list.add(new User("Paul", 16));
        list.add(new User("Paul", 15));

        // 按年龄归类
        Map<Integer, List<User>> age = list.stream().collect(Collectors.groupingBy(User::getAge));

        // 按成年是否归类
        Map<Boolean, List<User>> adult = list.stream().collect(Collectors.partitioningBy(o -> o.getAge() >= 18));
    }


    /**
     * 并行流,基于Fork—Join 框架
     * Fork—Join框架会将任务分发给线程池中的工作线程。
     * 但是依然是阻塞的,只是将任务分解给多个线程
     */
    @Test
    public void test_parallo(){
        Stream<String> lyric = Stream.of("hello", " ", "from", " ", "the", " ", "other", " ", "side", "!");
        lyric.parallel().forEach(System.out::print);
        System.out.println("end");
    }
}
