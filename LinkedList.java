/**
 * Defines a doubly-linked list class
 * @author Abdullah Mohammad
 * @author Chahid Bagdouri
 */
import java.util.NoSuchElementException;

public class LinkedList<T> {
    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private int length;
    private Node first;
    private Node last;
    private Node iterator;

    /**** CONSTRUCTORS ****/

    /**
     * Instantiates a new LinkedList with default values
     * 
     * @postcondition creates an empty LinkedList
     */
    public LinkedList() {
        first = null;
        last = null;
        length = 0;
    }

    /**
     * Converts the given array into a LinkedList
     * 
     * @param array the array of values to insert into this LinkedList
     * @postcondition creates a LinkedList with identical values to the given array
     */
    public LinkedList(T[] array) {
        first = null;
        last = null;
        length = 0;

        if (array == null || array.length == 0) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            addLast(array[i]);
        }
    }

    /**
     * Instantiates a new LinkedList by copying another List
     * 
     * @param original the LinkedList to copy
     * @postcondition a new List object, which is an identical,
     *                but separate, copy of the LinkedList original
     */
    public LinkedList(LinkedList<T> original) {
        first = null;
        last = null;
        length = 0;

        if (original == null || original.length == 0) {
            return;
        }

        Node temp = original.first;
        while (temp != null) {
            addLast(temp.data);
            temp = temp.next;
            iterator = first;
        }
    }

    /**** ACCESSORS ****/

    /**
     * Returns the value stored in the first node
     * 
     * @precondition list must not be empty and the first node must exist
     * @return the value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException {
        if (length == 0 || first == null) {
            throw new NoSuchElementException("getFirst(): Cannot get from an empty List!");
        }
        return first.data;
    }

    /**
     * Returns the value stored in the last node
     * 
     * @precondition list must not be empty and the last node must exist
     * @return the value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
    public T getLast() throws NoSuchElementException {
        if (length == 0 || last == null) {
            throw new NoSuchElementException("getLast(): Cannot get from an empty List!");
        }
        return last.data;
    }

    /**
     * Returns the data stored in the iterator node
     * 
     * @precondition iterator must not be null
     * @return the data stored in the iterator node
     * @throw NullPointerException when the precondition is violated
     */
    public T getIterator() throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("getIterator(): Iterator is null!");
        }
        return iterator.data;
    }

    /**
     * Returns the current length of the LinkedList
     * 
     * @return the length of the LinkedList from 0 to n
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns whether the LinkedList is currently empty
     * 
     * @return whether the LinkedList is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Returns whether the iterator is offEnd, i.e. null
     * 
     * @return whether the iterator is null
     */
    public boolean offEnd() {
        return iterator == null;
    }

    /**** MUTATORS ****/

    /**
     * Creates a new first element
     * 
     * @param data the data to insert at the front of the LinkedList
     * @postcondition data is at the front of the LinkedList
     */
    public void addFirst(T data) {
        if (length == 0) {
            first = last = new Node(data);
        } else {
            Node temp = new Node(data);
            temp.next = first;
            first.prev = temp;
            first = temp;
        }
        length++;
    }

    /**
     * Creates a new last element
     * 
     * @param data the data to insert at the end of the LinkedList
     * @postcondition data is at the end of the LinkedList
     */
    public void addLast(T data) {
        if (length == 0) {
            first = last = new Node(data);
        } else {
            Node temp = new Node(data);
            temp.prev = last;
            last.next = temp;
            last = temp;
        }
        length++;
    }

    /**
     * Inserts a new element after the iterator
     * 
     * @param data the data to insert
     * @precondition iterator must not be null
     * @throws NullPointerException when the precondition is violated
     */
    public void addIterator(T data) throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("addIterator(): Cannot add to an empty List!");
        } else {
            Node temp = new Node(data);
            if (iterator == last) {
                addLast(data);
            } else {
                temp.next = iterator.next;
                temp.prev = iterator;
                iterator.next.prev = temp;
                iterator.next = temp;
                length++;
            }
        }
    }

    /**
     * removes the element at the front of the LinkedList
     * 
     * @precondition list must not be empty and the first node must exist
     * @postcondition the first node is removed from the LinkedList
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeFirst() throws NoSuchElementException {
        if (length == 0 || first == null) {
            throw new NoSuchElementException("removeFirst(): Cannot remove from an empty List!");
        } else if (length == 1) {
            clear();
        } else {
            first = first.next;
            first.prev = null;
            iterator = first;
            length--;
        }
    }

    /**
     * removes the element at the end of the LinkedList
     * 
     * @precondition list must not be empty and the last node must exist
     * @postcondition the last node is removed from the LinkedList
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException {
        if (length == 0 || last == null) {
            throw new NoSuchElementException("removeLast(): Cannot remove from an empty List!");
        } else if (length == 1) {
            clear();
        } else {
            last = last.prev;
            last.next = null;
            iterator = last;
            length--;
        }
    }

    /**
     * removes the element referenced by the iterator
     * 
     * @precondition iterator must not be null
     * @postcondition the node referenced by the iterator is removed and set to null
     * @throws NullPointerException when the precondition is violated
     */
    public void removeIterator() throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("removeIterator(): Cannot remove from an empty List!");
        } else if (iterator == first) {
            removeFirst();
            iterator = null;
        } else if (iterator == last) {
            removeLast();
            iterator = null;
        } else {
            iterator.prev.next = iterator.next;
            iterator.next.prev = iterator.prev;
            iterator = null;
            length--;
        }
    }

    /**
     * places the iterator at the first node
     * 
     * @postcondition iterator is at the first node
     */
    public void positionIterator() {
        iterator = first;
    }

    /**
     * Moves the iterator one node towards the last
     * 
     * @precondition iterator must not be null
     * @postcondition iterator gets moved up
     * @throws NullPointerException when the precondition is violated
     */
    public void advanceIterator() throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("advanceIterator(): Cannot advance an empty List!");
        }
        iterator = iterator.next;
    }

    /**
     * Moves the iterator one node towards the first
     * 
     * @precondition iterator must not be null
     * @postcondition iterator gets moved down
     * @throws NullPointerException when the precondition is violated
     */
    public void reverseIterator() throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("reverseIterator(): Cannot reverse an empty List!");
        }
        iterator = iterator.prev;
    }

    /**** ADDITIONAL OPERATIONS ****/

    /**
     * Re-sets LinkedList to empty as if the
     * default constructor had just been called
     */
    public void clear() {
        first = null;
        last = null;
        iterator = null;
        length = 0;
    }

    /**
     * Converts the LinkedList to a String, with each value separated by a blank
     * line At the end of the String, place a new line character
     * 
     * @return the LinkedList as a String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node temp = first;
        while (temp != null) {
            result.append(temp.data + " ");
            temp = temp.next;
        }
        return result.toString() + "\n";
    }

    /**
     * Determines whether the given Object is
     * another LinkedList, containing
     * the same data in the same order
     * 
     * @param obj another Object
     * @return whether there is equality
     */
    @SuppressWarnings("unchecked") // good practice to remove warning here
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if ((LinkedList<T>) obj == null) {
            return false;
        } else if (this.length != ((LinkedList<T>) obj).length) {
            return false;
        } else {
            Node temp1 = first;
            Node temp2 = ((LinkedList<T>) obj).first;
            while (temp1 != null && temp2 != null) {
                if (temp1.data == null && temp2.data != null) {
                    return false;
                } else if (temp1.data != null && temp2.data == null) {
                    return false;
                } else if (temp1.data == null && temp2.data == null) {
                    // do nothing
                } else if (!temp1.data.equals(temp2.data)) {
                    return false;
                }
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            return temp1 == null && temp2 == null;
        }
    }

    /** CHALLENGE METHODS */

    /**
     * Moves all nodes in the list towards the end
     * of the list the number of times specified
     * Any node that falls off the end of the list as it
     * moves forward will be placed the front of the list
     * For example: [1, 2, 3, 4, 5], numMoves = 2 -> [4, 5, 1, 2 ,3]
     * For example: [1, 2, 3, 4, 5], numMoves = 4 -> [2, 3, 4, 5, 1]
     * For example: [1, 2, 3, 4, 5], numMoves = 7 -> [4, 5, 1, 2 ,3]
     * 
     * @param numMoves the number of times to move each node.
     * @precondition numMoves >= 0
     * @postcondition iterator position unchanged (i.e. still referencing
     *                the same node in the list, regardless of new location of Node)
     * @throws IllegalArgumentException when numMoves < 0
     */
    public void spinList(int numMoves) throws IllegalArgumentException {
        if (numMoves < 0) {
            throw new IllegalArgumentException("spinList(): numMoves must be >= 0");
        }

        if (length == 0 || length == 1) {
            return;
        }

        if (this.first == null || this.last == null) {
            return;
        }

        for (int i = 0; i < numMoves; i++) {
            Node temp = last;
            last = last.prev;
            last.next = null;
            first.prev = temp;
            temp.next = first;
            temp.prev = null;
            first = temp;
        }
    }

    /**
     * Splices together two LinkedLists to create a third List
     * which contains alternating values from this list
     * and the given parameter
     * For example: [1,2,3] and [4,5,6] -> [1,4,2,5,3,6]
     * For example: [1, 2, 3, 4] and [5, 6] -> [1, 5, 2, 6, 3, 4]
     * For example: [1, 2] and [3, 4, 5, 6] -> [1, 3, 2, 4, 5, 6]
     * 
     * @param list the second LinkedList
     * @return a new LinkedList, which is the result of
     *         interlocking this and list
     * @postcondition this and list are unchanged
     */
    public LinkedList<T> altLists(LinkedList<T> list) {
        if (list == null) {
            return this;
        }

        LinkedList<T> result = new LinkedList<T>();
        Node temp1 = this.first;
        Node temp2 = list.first;
        while (temp1 != null && temp2 != null) {
            result.addLast(temp1.data);
            result.addLast(temp2.data);
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        while (temp1 != null) {
            result.addLast(temp1.data);
            temp1 = temp1.next;
        }

        while (temp2 != null) {
            result.addLast(temp2.data);
            temp2 = temp2.next;
        }

        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Returns each element in the LinkedList along with its
     * numerical position from 1 to n, followed by a newline.
     * @return string with each element along with its numerical position
     */
    public String numberedListString() {
        StringBuilder out = new StringBuilder();
        positionIterator();

        for (int i = 1; i < length + 1; i++) {
            out.append(i + ". " + iterator.data + "");
            advanceIterator();
        }

        return out.toString() + "\n";
        
    }

    /**
     * Searches the LinkedList for a given element's index.
     * @param data the data whose index to locate.
     * @return the index of the data or -1 if the data is not contained
     * in the LinkedList.
     */
    public int findIndex(T data) {
        Node tempIterator = first;        
        for (int i = 0; i < length; i++) {
            if (tempIterator.data.equals(data)) {
                return i;
            }
            tempIterator = tempIterator.next;
        }
        
        return -1;
    }

    /**
     * Advances the iterator to location within the LinkedList
     * specified by the given index.
     * @param index the index at which to place the iterator.
     * @precondition index >= 0,  index < length
     * @throws IndexOutOfBoundsException when index is out of bounds
     */
    public void advanceIteratorToIndex(int index)  throws IndexOutOfBoundsException {
        positionIterator();

        if(index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("advanceIteratorToIndex(): index is out of bounds");
        }

        for (int i = 0; i < index; i++) {
            advanceIterator();
        }
    }
}
