/**
 * Order.java
 * Order class that represents an order in the system.
 * 
 * @author Abdullah Mohammad
 */

import java.util.Comparator;

public class Order {
    private int orderID;
    private Customer customer;
    private Date datePlaced; // date format: "MM/DD/YYYY"
    private LinkedList<Game> orderContents;
    private int shippingSpeed;
    private int priority;


    /**
     * Constructor for the Order class
     * 
     * @param orderID
     * @param customer
     * @param datePlaced
     * @param orderContents
     * @param shippingSpeed
     * @param priority
     * @postcondition a new Order object is created with the given values
     */
    public Order(int orderID, Customer customer, Date datePlaced, LinkedList<Game> orderContents, int shippingSpeed, int priority) {
        this.orderID = orderID;
        this.customer = customer;
        this.datePlaced = datePlaced;
        this.orderContents = orderContents;
        this.shippingSpeed = shippingSpeed;
        this.priority = priority;
    }

    /**** ACCESSORS ****/

    /**
     * Returns the order ID of the order.
     *
     * @return the order ID of the order
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Returns the customer of the order.
     *
     * @return the customer of the order
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns the datePlaced of the order.
     *
     * @return the datePlaced of the order
     */
    public Date getDate() {
        return datePlaced;
    }

    /**
     * Returns the order contents.
     *
     * @return the order contents
     */
    public LinkedList<Game> getOrderContents() {
        return orderContents;
    }

    /**
     * Returns the shipping speed of the order.
     *
     * @return the shipping speed of the order
     */
    public int getShippingSpeed() {
        return shippingSpeed;
    }

    /**
     * Returns the priority of the order.
     *
     * @return the priority of the order
     */
    public int getPriority() {
        return priority;
    }


    /**** MUTATORS ****/

    /**
     * Sets the order ID of the order.
     *
     * @param orderID the order ID of the order
     * @postcondition the order ID of the order is set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Sets the customer of the order.
     *
     * @param customer the customer of the order
     * @postcondition the customer of the order is set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Sets the datePlaced of the order.
     *
     * @param datePlaced the datePlaced of the order
     * @postcondition the datePlaced of the order is set
     */
    public void setDate(Date datePlaced) {
        this.datePlaced = datePlaced;
    }

    /**
     * Sets the order contents.
     *
     * @param orderContents the order contents
     * @postcondition the order contents are set
     */
    public void setOrderContents(LinkedList<Game> orderContents) {
        this.orderContents = orderContents;
    }

    /**
     * Sets the shipping speed of the order.
     *
     * @param shippingSpeed the shipping speed of the order
     * @postcondition the shipping speed of the order is set
     */
    public void setShippingSpeed(int shippingSpeed) {
        this.shippingSpeed = shippingSpeed;
    }

    /**
     * Sets the priority of the order.
     *
     * @param priority the priority of the order
     * @postcondition the priority of the order is set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

} // end of Order class

class priorityComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return Integer.compare(o1.getPriority(), o2.getPriority());
    }
} // end of OrderComparator class

class orderPlacedComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
} // end of OrderComparator class


/*
 * Possible other comparators:
 * 
 * - orderIDComparator: compares two orders by their order ID
 * - customerComparator: compares two orders by their customer
 * - orderContentsComparator: compares two orders by their contents
 * - shippingSpeedComparator: compares two orders by their shipping speed
 * - orderTotalComparator: compares two orders by their total price
 */