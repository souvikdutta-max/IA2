// Radix Tree //

import java.util.*;

public class RadixTree {

    class Node {
        String label;
        boolean isWord;
        Map<Character, Node> children;

        Node(String label) {
            this.label = label;
            this.isWord = false;
            this.children = new HashMap<>();
        }
    }

    private Node root;

    public RadixTree() {
        root = new Node("");
    }


    public void insert(String word) {
        Node current = root;
        while (!word.isEmpty()) {
            char firstChar = word.charAt(0);
            Node child = current.children.get(firstChar);

            if (child == null) {
            
                current.children.put(firstChar, new Node(word));
                current.children.get(firstChar).isWord = true;
                return;
            }

            String label = child.label;
            int i = 0;
            while (i < label.length() && i < word.length() && label.charAt(i) == word.charAt(i)) {
                i++;
            }

            if (i == label.length()) {

                current = child;
                word = word.substring(i);
            } else {
            
                String commonPrefix = label.substring(0, i);
                String oldSuffix = label.substring(i);
                String newSuffix = word.substring(i);

                Node splitNode = new Node(commonPrefix);
                current.children.put(firstChar, splitNode);

                splitNode.children.put(oldSuffix.charAt(0), new Node(oldSuffix));
                splitNode.children.get(oldSuffix.charAt(0)).isWord = child.isWord;
                splitNode.children.get(oldSuffix.charAt(0)).children = child.children;

                if (!newSuffix.isEmpty()) {
                    splitNode.children.put(newSuffix.charAt(0), new Node(newSuffix));
                    splitNode.children.get(newSuffix.charAt(0)).isWord = true;
                } else {
                    splitNode.isWord = true;
                }
                return;
            }
        }
        current.isWord = true;
    }

    public boolean search(String word) {
        Node current = root;
        while (!word.isEmpty()) {
            char firstChar = word.charAt(0);
            Node child = current.children.get(firstChar);
            if (child == null) return false;

            String label = child.label;
            if (word.startsWith(label)) {
                word = word.substring(label.length());
                current = child;
            } else {
                return false;
            }
        }
        return current.isWord;
    }

    public static void main(String[] args) {
        RadixTree tree = new RadixTree();
        tree.insert("rom");
        tree.insert("rope");
        tree.insert("rose");
        tree.insert("robot");

        System.out.println(tree.search("rom"));   
        System.out.println(tree.search("rope"));   
        System.out.println(tree.search("ros"));    
        System.out.println(tree.search("robot"));  
        System.out.println(tree.search("rob"));   
    }
}
