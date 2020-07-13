package com.hh.jdk8.container;

import com.hh.common.type.User;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author HaoHao
 * @date 2019-05-06 15:21
 */
public class mHashMap {

    /**
     * 扰动函数
     * 对散列值进行优化
     * 这里的key.hashCode() 是调用的key 值类型自己重写的hash函数. 理论上是一个int 值.2^32 次方的空间,基本不会出现碰撞
     * 但是一个40 亿长度的数组内存是放不下的.所以源码的获取数组下标是 tab[i = (n - 1) & hash] 数组长度-1 然后和hash 取与运算
     * 这也正好对应了HashMap 数组长度为什么是2的整数次幂. 因为这样数组长度n-1 正好相当于一个低位掩码 与hash值取与后只会截取到低位
     * 但是这样的只取低位的话,碰撞问题会很严重
     * 所以对hash 值右移16位 把高位降下来. 自己的高位区和低位区 做异或运算 加大随机性.
     */
    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * put 方法
     * @param hash
     * @param key
     * @param value
     * @param onlyIfAbsent
     * @param evict
     * @return
     */
    //final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
    //               boolean evict) {
    //    HashMap.Node<K,V>[] tab; HashMap.Node<K,V> p; int n, i;
    //    if ((tab = table) == null || (n = tab.length) == 0)
    //        // 插入第一个 resize 默认16
    //        n = (tab = resize()).length;
    //    if ((p = tab[i = (n - 1) & hash]) == null)
    //        // 对应下标位置头结点为空 直接放置一个新的节点
    //        tab[i] = newNode(hash, key, value, null);
    //    else {
    //        HashMap.Node<K,V> e; K k;
    //        if (p.hash == hash &&
    //                ((k = p.key) == key || (key != null && key.equals(k)))) {
    //            // key 相等
    //            e = p;
    //        } else if (p instanceof HashMap.TreeNode)
    //            e = ((HashMap.TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
    //        else {
    //            for (int binCount = 0; ; ++binCount) {
    //                if ((e = p.next) == null) {
    //                    p.next = newNode(hash, key, value, null);
    //                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
    //                        treeifyBin(tab, hash);
    //                    break;
    //                }
    //                if (e.hash == hash &&
    //                        ((k = e.key) == key || (key != null && key.equals(k))))
    //                    break;
    //                p = e;
    //            }
    //        }
    //        if (e != null) { // existing mapping for key
    //            V oldValue = e.value;
    //            if (!onlyIfAbsent || oldValue == null)
    //                e.value = value;
    //            afterNodeAccess(e);
    //            return oldValue;
    //        }
    //    }
    //    ++modCount;
    //    if (++size > threshold)
    //        resize();
    //    afterNodeInsertion(evict);
    //    return null;
    //}



    @Test
    public void put() {
        HashMap<User, Integer> map = new HashMap<>();
        // 链表插入第九个的时候树化
        for (int i = 0; i < 9; i++) {
            map.put(new User(), i);
        }
    }


}
