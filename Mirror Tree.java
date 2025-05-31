// Mirror Tree Java Code //

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class MirrorTree {

    public static void mirror(TreeNode root) {
        if (root == null) return;

  
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirror(root.left);
        mirror(root.right);
    }


    public static void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
      

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.print("Inorder of original tree: ");
        inorder(root); 
        System.out.println();

        mirror(root);

        System.out.print("Inorder of mirrored tree: ");
        inorder(root); 
        System.out.println();
    }
}
