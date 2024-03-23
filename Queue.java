/**
 * The Queue class definition
 *
 * @author Michael
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
        if (array == null)
            return;
        for (T i : array) {
            enqueue(i);
        }
    }

    /**
     * Copy constructor for the Queue class
     * Makes a deep copy of the parameter
     * @param original the Queue to copy
     * @postcondition a new copy of original Queue
     */
    public Queue(Queue<T> original) {
        if (original == null)
            return;
        if (!original.isEmpty()) {
            front = original.front;
            end = original.end;
            size = original.size;
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
        if (isEmpty())
            throw new NoSuchElementException("getFront(): Queue is empty.");
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
        return size == 0;
    }

    /****MUTATORS****/

    /**
     * Inserts a new value at the end of the Queue
     *
     * @param data the new data to insert
     * @postcondition add a value to the end of the queue
     */
    public void enqueue(T data) {

        if (size == 0) {
            front = end = new Node(data);
            size++;
            return;
        }
        size++;
        end.next = new Node(data);
        end = end.next;
    }

    /**
     * Removes the front element in the Queue
     * @precondition queue is not empty
     * @throws NoSuchElementException when
     * the precondition is violated
     * @postcondition removes the first element in the queue
     */
    public void dequeue() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException("dequeue(): Queue is empty.");
        if (size == 1) {
            front = end = null;
            size = 0;
            return;
        }

        front = front.next;
        size--;
        return;
    }

    /****ADDITONAL OPERATIONS****/

    /**
     * Returns the values stored in the Queue
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Queue values
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node temp = front;
        for (int i = 0; i < size; i++) {
            sb.append(temp.data + " ");
            temp = temp.next;
        }
        return sb.toString() + "\n";
    }

    /**
     * Determines whether two Queues contain
     * the same values in the same order
     * @param obj the Object to compare to this
     * @return whether obj and this are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Queue)) {
            return false;
        }
        Queue<T> object = (Queue<T>) obj;

        if (object.size != size)
            return false;

        Node temp = front;
        Node temp1 = object.front;
        while (temp != null) {
            if (!temp.data.equals(temp1.data))
                return false;
            temp = temp.next;
            temp1 = temp1.next;
        }
        return true;
    }
}
