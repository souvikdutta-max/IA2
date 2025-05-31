//  Deque with Insert and Delete at Both Ends //

public class Deque {
    private int[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public Deque(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = -1;
        rear = 0;
        size = 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insertFront(int key) {
        if (isFull()) {
            System.out.println("Deque is full. Cannot insert at front.");
            return;
        }
        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            front = (front - 1 + capacity) % capacity;
        }
        arr[front] = key;
        size++;
        System.out.println("Inserted " + key + " at front.");
    }

    public void insertRear(int key) {
        if (isFull()) {
            System.out.println("Deque is full. Cannot insert at rear.");
            return;
        }
        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }
        arr[rear] = key;
        size++;
        System.out.println("Inserted " + key + " at rear.");
    }

    
    public void deleteFront() {
        if (isEmpty()) {
            System.out.println("Deque is empty. Cannot delete from front.");
            return;
        }
        System.out.println("Deleted " + arr[front] + " from front.");
        if (front == rear) {
        
            front = -1;
            rear = 0;
        } else {
            front = (front + 1) % capacity;
        }
        size--;
    }

    
    public void deleteRear() {
        if (isEmpty()) {
            System.out.println("Deque is empty. Cannot delete from rear.");
            return;
        }
        System.out.println("Deleted " + arr[rear] + " from rear.");
        if (front == rear) {
            
            front = -1;
            rear = 0;
        } else {
            rear = (rear - 1 + capacity) % capacity;
        }
        size--;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
            return;
        }
        System.out.print("Deque elements: ");
        int i = front;
        for (int count = 0; count < size; count++) {
            System.out.print(arr[i] + " ");
            i = (i + 1) % capacity;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Deque deque = new Deque(5);

        deque.insertRear(10);
        deque.insertRear(20);
        deque.insertFront(5);
        deque.insertFront(2);
        deque.insertRear(30);

        deque.display();

        deque.deleteFront(); 
        deque.deleteRear();   

        deque.display();

        deque.deleteFront();
        deque.deleteRear();
        deque.deleteFront();

        deque.display();

        deque.deleteFront(); 
    }
}
