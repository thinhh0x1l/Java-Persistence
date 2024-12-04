package org.example.springdatajpa2.mix;

import java.util.*;

class Solution {
    private static final int LOWER = 97, PRIME = 16777619;

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        FastMap map = new FastMap();
        for (String s : strs) {
            int hash = hash(s);
            ArrayList<String> list = map.get(hash);
            if (list == null) {
                list = new ArrayList<>();
                ans.add(list);
            }
            list.add(s);
            map.put(hash, list);
        }

        return ans;
    }

    public int hash(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - LOWER]++;
        }

        int hash = 0;
        for (int freq : count) {
            hash ^= (hash << 6) + (hash >>> 3) + PRIME * freq;
        }

        hash ^= (hash << 13);
        hash ^= (hash >>> 7);
        hash ^= (hash << 17);

        return hash;
    }

    static class FastMap {
        private static final int n = 65535;
        private static final ListNode[] values = new ListNode[65536];
        private static final int queue[] = new int[10001];
        private static int size = 0;

        public FastMap() {
            while (size > 0) {
                values[queue[--size]] = null;
            }
        }

        public ArrayList<String> get(int key) {
            ListNode node = values[key & n];
            while (node != null) {
                if (node.key == key) {
                    return node.value;
                }
                node = node.next;
            }

            return null;
        }

        public void put(int key, ArrayList<String> value) {
            int index = key & n;
            ListNode node = values[index];

            if (node == null) {
                values[index] = new ListNode(key, value);
                queue[size++] = index;
                return;
            }

            ListNode current = node;
            while (current != null) {
                if (current.key == key) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }

            ListNode root = new ListNode(key, value);
            root.next = values[index];
            values[index] = root;
            queue[size++] = index;
        }
    }

    static class ListNode {
        private final int key;
        private ArrayList<String> value;
        private ListNode next;

        public ListNode(int key, ArrayList<String> value) {
            this.key = key;
            this.value = value;
        }
    }
}
