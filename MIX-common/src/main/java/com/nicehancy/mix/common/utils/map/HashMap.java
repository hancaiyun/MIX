package com.nicehancy.mix.common.utils.map;

/**
 * HashMap
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/8/20 12:30
 **/
public class HashMap<K, V> implements Map<K, V> {

    /**
     * 默认大小
     * PS:JDK并不会直接拿用户传进来的数字当做默认容量，而是会进行一番运算，最终得到一个2的幂
     * 例如指定9，实际长度大小为16
     */
    private static int defaultLength = 16;

    /**
     * 负载因子
     * 达到扩容水平之后， 按照负载因子扩容
     */
    private static double defaultLoader = 0.75;

    private final Entry<K, V> [] table;

    private int size = 0;

    public HashMap(){//两个构造函数，门面模式
        this(defaultLength, defaultLoader);
    }

    public HashMap(int length, double loader){
        defaultLength = length;
        defaultLoader = loader;
        table = new Entry[defaultLength];
    }

    @Override
    public V put(K k, V v) {
        size++;
        int index = hash(k);
        Entry<K, V> entry = table[index];
        if(entry == null){
            table[index] = newEntry(k, v, null);
        }else{
            table[index] = newEntry(k, v, entry);
        }
        return table[index].getValue();
    }

    public Entry<K, V> newEntry(K k, V v, Entry<K, V> next){
        return new Entry<>(k, v, next);
    }

    public int hash(K k){
        int m = defaultLength;
        //得到哈希值取模
        int i = k.hashCode()%m;

        return i>=0?1:-i;
    }

    @Override
    public V get(K k) {

        int index = hash(k);
        if(table[index] == null){
            return null;
        }

        return find(k, table[index]);
    }

    public V find(K k, HashMap<K, V>.Entry<K, V> entry){

        if(k ==entry.getK() || k.equals((entry.getK()))){
            return  entry.getV();
        }else{
            if(entry.next != null){
                return find(k, entry.next);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    class Entry<K, V> implements Map.Entry<K, V>{

        K k;
        V v;
        Entry<K, V> next;

        public Entry(K k, V v, Entry<K, V> next){
            this.k = k;
            this.v = v;
            this.next = next;
        }

        public K getK(){
            return k;
        }

        public void setK(K k){
            this.k = k;
        }

        public V getV(){
            return v;
        }

        public void setV(V v){
            this.v = v;
        }

        public Entry<K, V> getNext(){
            return next;
        }

        public void setNext(Entry<K, V> next){
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }


        @Override
        public V getValue() {
            return v;
        }
    }
}
