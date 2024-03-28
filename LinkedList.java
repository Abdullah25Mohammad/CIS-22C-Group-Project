/**
 * Defines a doubly-linked list class
 * Based on starter code from De Anza College CIS 22C, Jennifer Parrish
 * @author Jacob L. Johnston
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
     * @postcondition A new empty LinkedList is created
     */
    public LinkedList() {
        first = null;
        last = null;
        iterator = null;
        length = 0;
    }

    /**
     * Converts the given array into a LinkedList
     *
     * @param array the array of values to insert into this LinkedList
     * @postcondition a new List object, which is an identical,
     * but separate, copy of the LinkedList original
     */
    public LinkedList(T[] array) {
        this(); // Initializes LinkedList with default values
        // (Calls the default constructor)
        if (array != null) {
            for (T element : array) {
                addLast(element);
            }
        }
    }

    /**
     * Instantiates a new LinkedList by copying another List
     *
     * @param original the LinkedList to copy
     * @postcondition a new List object, which is an identical,
     */
    public LinkedList(LinkedList<T> original) {
        this();
        if (original != null) {
            Node current = original.first;
            while (current != null) {
                this.addLast(current.data); // Add the first node from original list
                current = current.next; // Traverse
            }
        }
    }

    /**** ACCESSORS ****/

    /**
     * Returns the value stored in the first node
     *
     * @return the value stored at node first
     * @throws NoSuchElementException if the list is empty
     * @precondition the list is not empty
     */
    public T getFirst() throws NoSuchElementException {
        if (first == null) {
            throw new NoSuchElementException("getFirst() is invalid to call. Cannot perform on an empty list.");
        }
        return first.data;
    }

    /**
     * Returns the value stored in the last node
     *
     * @return the value stored in the node last
     * @throws NoSuchElementException if the list is empty.
     * @precondition the list is not empty.
     */
    public T getLast() throws NoSuchElementException {
        if (last == null) { // i was using length == 0 before
            throw new NoSuchElementException("Error: getLast(). List is empty.");
        }
        return last.data;
    }

    /**
     * Returns the data stored in the iterator node
     *
     * @return the data stored in the iterator node
     * @precondition iterator != null
     * @throw NullPointerException if iterator is null
     */
    public T getIterator() throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("Error: getIterator(). Iterator is null.");
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
     * @postcondition a node is added to the linked list
     */
    public void addFirst(T data) {
        if (length == 0) {
            first = last = new Node(data);
        } else {
            Node node = new Node(data);
            node.next = first;
            first.prev = node;
            first = node;
        }
        length++;
    }

    /**
     * Creates a new last element
     *
     * @param data the data to insert at the end of the LinkedList
     * @postcondition a new node containing data is created and
     * inserted at the end of the LinkedList.
     */
    public void addLast(T data) {
        if (length == 0) {
            first = last = new Node(data);
        } else {
            Node node = new Node(data);
            node.prev = last;
            last.next = node;
            last = node;
        }
        length++;
    }

    /**
     * Inserts a new element after the iterator
     *
     * @param data the data to insert
     * @throws NullPointerException if iterator is null
     * @precondition iterator != null
     */
    public void addIterator(T data) throws NullPointerException {
        if (offEnd()) {
            throw new NullPointerException("addIterator: iterator is null");
        } else if (iterator == last) {
            addLast(data);
        } else {
            Node newNode = new Node(data);
            newNode.next = iterator.next;
            newNode.prev = iterator;
            iterator.next.prev = newNode;
            iterator.next = newNode;
            length++;
        }
    }

    /**
     * removes the element at the front of the LinkedList
     *
     * @throws NoSuchElementException If the list is empty.
     * @precondition The list is not empty.
     * @postcondition The first element of the list is removed. If the list had only one element, it is now empty.
     */
    public void removeFirst() throws NoSuchElementException {
        if (length == 0 || first == null) {
            throw new NoSuchElementException("removeFirst() is invalid to call. Cannot perform on an empty list.");
        } else if (length == 1) {
            first = last = null;
        } else {
//            if (iterator == first) {
//                iterator = null;
//            }
            first = first.next;
//            first.prev = null;
        }
        length--;
    }

    /**
     * removes the element at the end of the LinkedList
     *
     * @throws NoSuchElementException If the list is empty.
     * @precondition The list is not empty.
     * @postcondition The last element of the list is removed. IF the list had only one element, it is now empty.
     */
    public void removeLast() throws NoSuchElementException {
        if (length == 0) {
            throw new NoSuchElementException("removeLast() is invalid to call. Cannot perform on an empty list.");
        } else if (length == 1) {
            first = last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        length--;
    }

    /**
     * removes the element referenced by the iterator
     *
     * @throws NullPointerException if the iterator is null
     * @precondition iterator not offEnd
     * @postcondition the node at iterator's current position is deleted
     */
    public void removeIterator() throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("removeIterator: iterator is at the end of the list");
        }
        if (iterator == first) {
            removeFirst();
        } else if (iterator == last) {
            removeLast();
        } else {
            iterator.prev.next = iterator.next;
            iterator.next.prev = iterator.prev;
            length--;
        }
        iterator = null;
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
     * @throws NullPointerException if the iterator is off end
     * @precondition the iterator is not off end
     * @postcondition the iterator is assigned to the next node in the list
     */
    public void advanceIterator() throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("Error: iterator is currently off end!");
        }
        iterator = iterator.next;
    }

    /**
     * Moves the iterator one node towards the first
     *
     * @throws NullPointerException if the iterator is off end
     * @precondition the iterator is not off end
     * @postcondition the iterator is assigned to its previous node
     */
    public void reverseIterator() throws NullPointerException {
        if (iterator == null) {
            throw new NullPointerException("Error: iterator is currently off end!");
        } else {
            iterator = iterator.prev;
        }
    }

    /**** ADDITIONAL OPERATIONS ****/

    /**
     * Re-sets LinkedList to empty as if the
     * default constructor had just been called
     */
    public void clear() {
        iterator = first = last = null;
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
            result.append(temp.data + "\n");
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
        } else if (!(obj instanceof LinkedList)) { // If comparing LinkedList to a non-LinkedList (or a null)
            return false;
        } else if (this.length != ((LinkedList<T>) obj).length) {
            return false;
        } else {
            LinkedList<T> Llist = (LinkedList<T>) obj; // Cast obj to LinkedList so the compiler doesn't think
            // obj is an object.
            if (length != Llist.length) {
                return false;
            } else {
                Node temp1 = this.first;
                Node temp2 = Llist.first;
                while (temp1 != null && temp2 != null) {
                    if (temp1.data == null && temp2.data == null) {
                        temp1 = temp1.next;
                        temp2 = temp2.next;
                    } else if (temp1.data == null || temp2.data == null) {
                        return false;
                    } else if (!temp1.data.equals(temp2.data)) {
                        return false;
                    } else {
                        temp1 = temp1.next;
                        temp2 = temp2.next;
                    }
                }
                return temp1 == null && temp2 == null;
            }
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
            throw new IllegalArgumentException("Error: spinList(). numMoves < 0");
        } else if (numMoves == 0) {
            return;
        } else if (this.isEmpty()) {
            return;
        } else {
            for (int i = 0; i < numMoves; i++) {
                T temp = this.getLast();
                if (temp != null) {
                    this.removeLast();
                    this.addFirst(temp);
                }
            }
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
        LinkedList<T> newList = new LinkedList<T>();

        // If both lists are empty, return an empty list
        if ((this.isEmpty() && (list == null || list.isEmpty()))) {
            return newList;
        }

        // If 'this' list is empty but 'list' is not, return a copy of 'list'
        if (this.isEmpty() && !list.isEmpty()) {
            return new LinkedList<T>(list);
        }

        // If 'list' is empty but 'this' list is not, return a copy of 'this' list
        if (!this.isEmpty() && (list == null || list.isEmpty())) {
            return new LinkedList<T>(this);
        }

        // If neither list is empty, interleave the elements
        Node currentThis = this.first;
        Node currentList = list.first;
        while (currentThis != null || currentList != null) {
            if (currentThis != null) {
                newList.addLast(currentThis.data);
                currentThis = currentThis.next;
            }
            if (currentList != null) {
                newList.addLast(currentList.data);
                currentList = currentList.next;
            }
        }
        return newList;
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
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
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
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Error: advanceIteratorToIndex(): index is out of bounds");
        }
        positionIterator();
        for (int i = 0; i < index; i++) {
            advanceIterator();
        }
    }
}
