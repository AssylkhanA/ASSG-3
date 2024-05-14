import java.util.LinkedList;

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int capacity = 11;
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[capacity];
    }

    public MyHashTable(int capacity) {
        this.capacity = capacity;
        chainArray = new HashNode[capacity];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = chainArray[index];
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = head;
        chainArray[index] = newNode;

        if ((1.0 * size) / capacity >= 0.7) {
            resize();
        }
    }

    private void resize() {
        LinkedList<HashNode<K, V>> tempNodes = new LinkedList<>();
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                tempNodes.add(node);
                node = node.next;
            }
        }

        capacity = 2 * capacity;
        chainArray = new HashNode[capacity];
        size = 0;

        for (HashNode<K, V> node : tempNodes) {
            put(node.key, node.value);
        }
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }

        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        size--;

        if (prev != null) {
            prev.next = head.next;
        } else {
            chainArray[index] = head.next;
        }

        return head.value;
    }

    public boolean contains(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (node.value.equals(value)) {
                    return node.key;
                }
                node = node.next;
            }
        }
        return null;
    }

    public void printChainLengths() {
        for (int i = 0; i < capacity; i++) {
            int count = 0;
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                count++;
                head = head.next;
            }
            System.out.println("Chain " + i + ": " + count + " elements");
        }
    }
}
