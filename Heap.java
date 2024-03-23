/**
 * Heap.java
 * @author Abdullah Mohammad
 * @author Chahid Bagdouri
 * CIS 22C, Lab 19
 */
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<T> {
    private int heapSize;
    private ArrayList<T> heap;
    private Comparator<T> cmp;

    /**Constructors/

    /**
     * Constructor for the Heap class.
     * Sets heapSize to data size, stores parameters, inserts null at heap
     * element 0, and calls buildHeap().
     * @param data an unordered ArrayList, where element 0 is not used.
     * @param comparator that determines organization of heap
     * based on priority.
     */
    public Heap(ArrayList<T> data, Comparator<T> cmp) {
        this.heapSize = data.size() + 1;
        this.heap = data; // unheapified
        this.cmp = cmp;
        buildHeap();
    }

    /**Mutators*/

    /**
     * Converts an ArrayList into a valid max heap. Called by constructor.
     * Calls helper method heapify.
     */
    public void buildHeap() {
        /*
        n = Heap_size(A)
        for (i = floor(n/2) down to 1) //start at floor(n/2); we can ignore leaf nodes
            Max-Heapify(A, i) //call Max-Heapify helper function
        */
        for(int i = heapSize/2; i >= 1; i--) {
            heapify(i);
        }
    }

    /**
     * Helper method to buildHeap, remove, and sort.
     * Bubbles an element down to its proper location within the heap.
     * @param index an index in the heap
     */
    private void heapify(int index) {
        /*
        l = left(i) //get the index of the left child of A[i] and store as l
        r = right(i) //get the index of the right child of A[i] and store r
        //Check if l is off the end of the array (heap) AND compare A[i] to its left child
        if (l <= Heap_size(A) AND A[l] > A[i])
            index_of_max = l //update index_of_max if left is bigger

        //Check if r is off the end of the array (heap) AND compare A[r] to current max value
        if (r <= Heap_size(A) AND A[r] > A[index_of_max]
        index_of_max = r //update index_of_max if right is bigger
        
        if (i != index_of_max) //if A[i] was not bigger than its two children
            A[i]<--->A[index_of_max] //swap, so now A[i] stored at A[index_of_max]
            Max-Heapify(A, index_of_max) //recursively move through tree until restore heap property
         */
        
        int l = getLeft(index);
        int r = getRight(index);
        int indexOfMax = index;
        
        if((l < heapSize) && (cmp.compare(getElement(l), getElement(indexOfMax)) > 0)) {
            indexOfMax = l;
        }
        if((r < heapSize) && (cmp.compare(getElement(r), getElement(indexOfMax)) > 0)) {
            indexOfMax = r;
        }
        
        if(index != indexOfMax) {
            // Swap
            T temp = getElement(index);
            heap.set(index - 1, getElement(indexOfMax));
            heap.set(indexOfMax - 1, temp);
            heapify(indexOfMax);
        }
    }

    /**
     * Inserts the given data into heap.
     * Calls helper method heapIncreaseKey.
     * @param key the data to insert
     */
    public void insert(T key) {
        /*
        Heap_size(A)++ //adding a new value to the heap
        A[Heap_size(A)] = –∞ //make space at end of array for new value
        HeapIncreaseKey(A, Heap_size(A), key) //start at the last index, i=Heap_size(A)
        */
        
        heapSize++;
        heap.add(key);
        heapIncreaseKey(heapSize - 1, key);

        for(int i = heapSize/2; i >= 1; i--) {
            heapify(i);
        }
    }

    /**
     * Helper method for insert.
     * Bubbles an element up to its proper location
     * @param index the current index of the key
     * @param key the data
     */
    private void heapIncreaseKey(int index, T key) {
        /*
        if(key > A[i])
            A[i] = key //write over existing value at i with key

            while (i > 1 AND A[Parent(i)] < A[i])
                //while the parent is smaller and you are not at the root node
                A[i] <--> A[Parent(i)] //Swap parent and child
                i = Parent(i) //keep track of current index of the key
        */
        
        if(cmp.compare(key, getElement(index)) > 0) {
            heap.set(index - 1, key);
            while(index > 1 && cmp.compare(getElement(getParent(index)), getElement(index)) < 0) {
                T temp = getElement(index);
                heap.set(index - 1, getElement(getParent(index)));
                heap.set(getParent(index) - 1, temp);
                index = getParent(index);
            }
        }        
    }

    /**
     * Removes the element at the specified index.
     * Calls helper method heapify
     * @param index the index of the element to remove
     */
    public void remove(int index) {
        heapSize--;
        heap.remove(index - 1);      
        for(int i = heapSize/2; i >= 1; i--) {
            heapify(i);
        }
    }

    /**Accessors*/

    /**
     * Returns the heap size (current number of elements)
     * @return the size of the heap
     */
    public int getHeapSize() {
        return heapSize - 1;
    }

    /**
     * Returns the location (index) of the
     * left child of the element stored at index.
     * @param index the current index
     * @return the index of the left child.
     * @precondition 0 < index <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public int getLeft(int index) throws IndexOutOfBoundsException {
        if (index <= 0 || index > getHeapSize()) {
            throw new IndexOutOfBoundsException("getLeft: index is out of bounds");
        }
        return 2 * index;
    }

    /**
     * Returns the location (index) of the right child of the element
     * stored at index.
     * @param index the current index
     * @return the index of the right child
     * @precondition 0 < i <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public int getRight(int index) throws IndexOutOfBoundsException {
        if (index <= 0 || index > getHeapSize()) {
            throw new IndexOutOfBoundsException("getRight: index is out of bounds");
        }
        return 2 * index + 1;
    }

    /**
     * Returns the location (index) of the
     * parent of the element stored at index.
     * @param index the current index
     * @return the index of the parent
     * @precondition 1 < i <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public int getParent(int index) throws IndexOutOfBoundsException {
        if (index <= 1 || index > getHeapSize()) {
            throw new IndexOutOfBoundsException("getParent(): index is out of bounds");
        }
        return index / 2;
    }

    /**
     * Returns the maximum element (highest priority)
     * @return the max value
     */
    public T getMax() {
        return heap.get(0);
    }

    /**
     * Returns the element at a specific index.
     * @param index an index in the heap.
     * @return the data at the index.
     * @precondition 0 < i <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public T getElement(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > heapSize) {
            throw new IndexOutOfBoundsException("getElement(): index is out of bounds");
        }
        return heap.get(index - 1);
    }

    /**Additional Operations*/

    /**
     * Creates a String of all elements in the heap, separated by ", ".
     * @return a String of all elements in the heap, separated by ", ".
     */
    @Override
    public String toString() {
        return heap.toString().substring(1, heap.toString().length()-1);
    }

    /**
     * Uses the heap sort algorithm to sort the heap into ascending order.
     * Calls helper method heapify.
     * @return an ArrayList of sorted elements
     * @postcondition heap remains a valid heap
     */
    public ArrayList<T> sort() {
        /*
        n = Heap_size(A);

        for (i = n down to 2)
            A[1] <--> A[i] //swap
            Heap_size(A)-- //consider your heap to be one smaller
            Max-Heapify(A,1) //restore max heap property
        */

        ArrayList<T> originalHeap = new ArrayList<T>(this.heap);

        int n = heapSize;
        
        for(int i = n - 1; i >= 2; i--) {
            T temp = getElement(i);
            heap.set(i - 1, getElement(1));
            heap.set(1 - 1, temp);
            heapSize--;
            heapify(1);
        }

        ArrayList<T> sortedHeap = new ArrayList<T>(heap);

        this.heap = new ArrayList<T>(originalHeap);

        return sortedHeap;
    }
}