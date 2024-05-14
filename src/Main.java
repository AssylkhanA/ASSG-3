import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Creating and populating the custom hash table
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();
        for (int i = 0; i < 100; i++) {
            int id = i;
            String name = "Name" + id;
            table.put(new MyTestingClass(id, name), "Student" + i);
        }
        table.printChainLengths();

        // Testing retrieval and removal
        MyTestingClass testKey = new MyTestingClass(50, "Name50");
        System.out.println("Value for key (50, 'Name50'): " + table.get(testKey));
        System.out.println("Removing key (50, 'Name50'): " + table.remove(testKey));
        System.out.println("Value for key (50, 'Name50') after removal: " + table.get(testKey));

        // Creating and populating the BST
        BST<Integer, String> bst = new BST<>();
        bst.put(10, "value_10");
        bst.put(5, "value_5");
        bst.put(20, "value_20");
        bst.put(3, "value_3");
        bst.put(7, "value_7");
        bst.put(15, "value_15");
        bst.put(25, "value_25");

        // Iterating and printing the BST elements
        for (BST.BinarTEntry<Integer, String> elem : bst) {
            System.out.println("key: " + elem.getKey() + ", value: " + elem.getValue());
        }

        // Testing BST retrieval
        System.out.println("Value for key 7: " + bst.get(7));
        System.out.println("Value for key 15: " + bst.get(15));
        System.out.println("Value for key 30: " + bst.get(30));
    }
}
