package com.nicehancy.mix.common.utils.map;

/**
 * Map
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/20 12:28
 **/
public interface Map<K, V> {

    V put(K k, V v);

    V get(K k);

    int size();

    interface Entry<K, V>{

        K getKey();

        V getValue();
    }
}
