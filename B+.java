// B+ //

import java.util.*;

public class BPlusTree {

    private int t; 
    private Node root;

    // Node class
    class Node {
        int n; 
        int[] keys;
        Node[] children;
        boolean leaf;
        Node next; 

        Node(boolean leaf) {
            this.leaf = leaf;
            this.keys = new int[2 * t - 1];
            this.children = new Node[2 * t];
            this.n = 0;
            this.next = null;
        }
    }

    public BPlusTree(int t) {
        this.t = t;
        root = new Node(true);
    }

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(Node node, int key) {
        int i = 0;
        while (i < node.n && key > node.keys[i]) {
            i++;
        }

        if (node.leaf) {
            if (i < node.n && node.keys[i] == key) {
                return true;
            }
            return false;
        } else {
            if (i < node.n && node.keys[i] == key) i++; // Move to next child if equal
            return search(node.children[i], key);
        }
    }

    
    public void insert(int key) {
        Node r = root;
        if (r.n == 2 * t - 1) {
            Node s = new Node(false);
            root = s;
            s.children[0] = r;
            splitChild(s, 0, r);
            insertNonFull(s, key);
        } else {
            insertNonFull(r, key);
        }
    }


    private void insertNonFull(Node node, int key) {
        int i = node.n - 1;

        if (node.leaf) {
          
            while (i >= 0 && node.keys[i] > key) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = key;
            node.n++;
        } else {
     
            while (i >= 0 && node.keys[i] > key) {
                i--;
            }
            i++;
            if (node.children[i].n == 2 * t - 1) {
                splitChild(node, i, node.children[i]);
                if (node.keys[i] < key) {
                    i++;
                }
            }
            insertNonFull(node.children[i], key);
        }
    }

  
    private void splitChild(Node parent, int i, Node y) {
        Node z = new Node(y.leaf);
        z.n = t - 1;

        
        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
        }

        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.children[j] = y.children[j + t];
            }
        }

        y.n = t - 1;

   
        for (int j = parent.n; j >= i + 1; j--) {
            parent.children[j + 1] = parent.children[j];
        }
        parent.children[i + 1] = z;

      
        for (int j = parent.n - 1; j >= i; j--) {
            parent.keys[j + 1] = parent.keys[j];
        }
        parent.keys[i] = y.keys[t - 1];
        parent.n++;


        if (y.leaf) {
            z.next = y.next;
            y.next = z;
        }
    }

    public void printLeaves() {
        Node curr = root;
  
        while (!curr.leaf) {
            curr = curr.children[0];
        }
        System.out.print("Leaves: ");
        while (curr != null) {
            for (int i = 0; i < curr.n; i++) {
                System.out.print(curr.keys[i] + " ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    // Driver code
    public static void main(String[] args) {
        BPlusTree bpt = new BPlusTree(3); )

        int[] keys = {10, 20, 5, 6, 12, 30, 7, 17};

        for (int key : keys) {
            bpt.insert(key);
        }

        bpt.printLeaves(); 

        System.out.println("Search 6: " + bpt.search(6));   // true
        System.out.println("Search 15: " + bpt.search(15)); // false
    }
}
