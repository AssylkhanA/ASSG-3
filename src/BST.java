import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.BinarTEntry<K, V>> {
    private Node root;
    private int size = 0; // For tracking size of tree
    public static class BinarTEntry<K, V> {
        private final K key;
        private final V value;

        public BinarTEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public V getValue() {
            return value;
        }
        public K getKey() {
            return key;
        }
    }

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    // Method - add the element
    public void put(K key, V val) {
        root = put(root, key, val);
    }
    private Node put(Node x, K key, V val) {
        if (x == null) {
            size++; // Increasing size when add element
            return new Node(key, val);
        }
        int compr = key.compareTo(x.key);
        if (compr < 0) {
            x.left = put(x.left, key, val);
        } else if (compr > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        return x;
    }
    // Method - get the value 
    public V get(K key) {
        Node x = get(root, key);
        if (x == null) return null;
        return x.val;
    }
    private Node get(Node x, K key) {
        if (x == null) return null;
        int compr = key.compareTo(x.key);
        if (compr < 0) return get(x.left, key);
        else if (compr > 0) return get(x.right, key);
        else return x;
    }

    // Method - remove element with key
    public void delEl(K key) {
        root = delEl(root, key);
    }
    private Node delEl(Node x, K key) {
        if (x == null) return null;
        int compr = key.compareTo(x.key);
        if (compr < 0) {
            x.left = delEl(x.left, key);
        } else if (compr > 0) {
            x.right = delEl(x.right, key);
        } else {
            if (x.right == null) {
                size--; // Decreasing size when delete element
                return x.left;
            }
            if (x.left == null) {
                size--; //  Decreasing size when delete element
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = delElMin(t.right);
            x.left = t.left;
        }
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    private Node delElMin(Node x) {
        if (x.left == null) return x.right;
        x.left = delElMin(x.left);
        return x;
    }

    // Return size of binary tree
    public int size() {
        return size;
    }

    //method - iterator in-order
    @Override
    public Iterator<BinarTEntry<K, V>> iterator() {
        return new BSTIterator();
    }
    private class BSTIterator implements Iterator<BinarTEntry<K, V>> {
        private Stack<Node> stack = new Stack<>();
        public BSTIterator() {
            pushLeft(root);
        }
        private void pushLeft(Node x) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
        }
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        @Override
        public BinarTEntry<K, V> next() {
            Node x = stack.pop();
            pushLeft(x.right);
            return new BinarTEntry<>(x.key, x.val);
        }
    }
}
