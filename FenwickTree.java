// Fenwick //

public class FenwickTree {
    private int[] tree;
    private int size;

    public FenwickTree(int n) {
        size = n;
        tree = new int[n + 1];
    }

    public void update(int i, int value) {
        i++; 
        while (i <= size) {
            tree[i] += value;
            i += (i & -i);
        }
    }
    public int query(int i) {
        i++;
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= (i & -i);
        }
        return sum;
    }


    public int rangeQuery(int l, int r) {
        return query(r) - query(l - 1);
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, -1, 6, 5, 4, -3, 3, 7, 2, 3};

        FenwickTree ft = new FenwickTree(arr.length);

        for (int i = 0; i < arr.length; i++) {
            ft.update(i, arr[i]);
        }

        System.out.println("Sum of first 5 elements (0 to 4): " + ft.query(4));  
        System.out.println("Sum from index 3 to 8: " + ft.rangeQuery(3, 8));       
    }
}
