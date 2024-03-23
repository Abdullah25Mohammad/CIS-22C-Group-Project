/**
 * The Queue class definition
 * @author Abdullah Mohammad
 * CIS 22C, Lab 5
 * @param <T> the generic data stored in the Queue
 */
import java.util.NoSuchElementException;

public class Queue<T> implements Q<T> {
    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private int size;
    private Node front;
    private Node end;

    /****CONSTRUCTORS****/

    /**
     * Default constructor for the Queue class
     * @postcondition a new Queue object with all fields
     * assigned default values
     */
    public Queue() {
        front = end = null;
        size = 0;
    }

    /**
     * Converts an array into a Queue
     * @param array the array to copy into
     * the Queue
     */
    public Queue(T[] array) {
        front = end = null;
        size = 0;

        if (array == null) {
            return;
        }
        for (T element : array) {
            enqueue(element);
        }
    }

    /**
     * Copy constructor for the Queue class
     * Makes a deep copy of the parameter
     * @param original the Queue to copy
     * @postcondition a new Queue object which is identical
     */
    public Queue(Queue<T> original) {
        front = end = null;
        size = 0;

        if (original == null) {
            return;
        }

        Node temp = original.front;
        while (temp != null) {
            enqueue(temp.data);
            temp = temp.next;
        }
    }

    /****ACCESSORS****/

    /**
     * Returns the value stored at the front
     * of the Queue
     * @return the value at the front of the queue
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T getFront() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("getFront(): Queue is empty. Cannot getFront.");
        }
        return front.data;
    }

    /**
     * Returns the size of the Queue
     * @return the size from 0 to n
     */
    public int getSize() {
        return size;
    }

    /**
     * Determines whether a Queue is empty
     * @return whether the Queue contains no elements
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /****MUTATORS****/

    /**
     * Inserts a new value at the end of the Queue
     *
     * @param data the new data to insert
     * @postcondition first element is equal to data
     */
    public void enqueue(T data) {
        if (size == 0) {
            front = end = new Node(data);
        } else {
            end.next = new Node(data);
            end = end.next;
        }
        size++;
    }

    /**
     * Removes the front element in the Queue
     * @precondition queue must not empty
     * @throws NoSuchElementException when
     * the precondition is violated
     * @postcondition front element has been removed
     */
    public void dequeue() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("dequeue(): Queue is empty. Cannot dequeue.");
        } else if (size == 1) {
            front = end = null;
        } else {
            front = front.next;
        }
        size--;
    }

    /****ADDITONAL OPERATIONS****/

    /**
     * Returns the values stored in the Queue
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Queue values
     */
    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        Node temp = front;
        while (temp != null) {
            result.append(temp.data + " ");
            temp = temp.next;
        }
        return result.toString() + "\n";
    }

    /**
     * Determines whether two Queues contain
     * the same values in the same order
     * @param obj the Object to compare to this
     * @return whether obj and this are equal
     */
    @SuppressWarnings("unchecked") // good practice to remove warning here
    @Override public boolean equals(Object obj)  {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (!(obj instanceof Queue)) {
            return false;
        } else if (((Queue<T>)obj).size != this.size) {
            return false;
        }

        Node temp1 = front;
        Node temp2 = ((Queue<T>)obj).front;

        while (temp1 != null && temp2 != null) {
            if (!temp1.data.equals(temp2.data)) {
                return false;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return true;
        
    }
}
