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

    /**
     * put
     * @param k    k
     * @param v    v
     * @return     v
     */
    V put(K k, V v);

    /**
     * get
     * @param k    k
     * @return     v
     */
    V get(K k);

    /**
     * size
     * @return  size
     */
    int size();

    interface Entry<K, V>{

        /**
         * getKey
         * @return  k
         */
        K getKey();

        /**
         * getValue
         * @return  v
         */
        V getValue();
    }
}
