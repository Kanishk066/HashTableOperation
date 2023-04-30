package com.bl.hashtables;
import java.util.*;
class MyMapNode<K, V> {
    K key;
    V value;
    MyMapNode<K, V> next;
    public MyMapNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
class MyHashMap<K, V> {
     LinkedList<MyMapNode<K, V>> buckets;
     int size;

    public MyHashMap() {
        buckets = new LinkedList<MyMapNode<K, V>>();
        size = 0;
        for (int i = 0; i < 10; i++) {
            buckets.add(null);
        }
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return hashCode % buckets.size();
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        MyMapNode<K, V> head = buckets.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        head = buckets.get(bucketIndex);
        MyMapNode<K, V> newNode = new MyMapNode<K, V>(key, value);
        newNode.next = head;
        buckets.set(bucketIndex, newNode);
        size++;
    }
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        MyMapNode<K, V> head = buckets.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }
    public int getSize() {
        return size;
    }
}
public class HashTableOperation {
    public static void main(String[] args) {
        System.out.println("Welcome to the Hash Table Program");
        String sentence = "To be or not to be";
        MyHashMap<String, Integer> wordFrequencyMap = new MyHashMap<String, Integer>();
        String[] words = sentence.split(" ");
        for (String word : words) {
            Integer frequency = wordFrequencyMap.get(word);
            if (frequency == null) {
                wordFrequencyMap.put(word, 1);
            } else {
                wordFrequencyMap.put(word, frequency + 1);
            }
        }
        for (String word : words) {
            System.out.println(word + " : " + wordFrequencyMap.get(word));
        }
    }
}
