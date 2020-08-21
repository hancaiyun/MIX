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

    private static int defaultLength = 16;

    private static double defaultLoader = 0.75;

    private final Entry<K, V> [] table;

    private int size = 0;

    public HashMap(){
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
        int i = k.hashCode()%m;//得到哈希值取模

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
