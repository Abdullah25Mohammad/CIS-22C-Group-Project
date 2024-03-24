/**
 * Customer.java
 * Customer class that represents a customer in the system.
 * 
 * @author Abdullah Mohammad
 */
import java.util.NoSuchElementException;

public class Customer extends User {
	private String address;
	private String city;
	private String state;
	private String zip;
	private LinkedList<Order> shippedOrders;
	private LinkedList<Order> unshippedOrders;

	/**
	 * Constructs a Customer object with the specified details.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @postcondition a new Customer object is created with the given values
	 */
	public Customer(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);

		this.shippedOrders = new LinkedList<>();
		this.unshippedOrders = new LinkedList<>();
	}

	/**
	 * Constructs a Customer object with the specified details.
	 *
	 * @param firstName the first name of the customer
	 * @param lastName  the last name of the customer
	 * @param username     the username of the customer
	 * @param password  the password of the customer
	 * @param address   the address of the customer
	 * @param city      the city of the customer
	 * @param state     the state of the customer
	 * @param zip       the zip code of the customer
	 * @postcondition a new Customer object is created with the given values
	 */
	public Customer(String firstName, String lastName, String username, String password, String address, String city, String state, String zip) {
		super(firstName, lastName, username, password);

		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	/**** ACCESSORS ****/

	/**
	 * Returns the address of the customer.
	 *
	 * @return the address of the customer
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Returns the city of the customer.
	 *
	 * @return the city of the customer
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Returns the state of the customer.
	 *
	 * @return the state of the customer
	 */
	public String getState() {
		return state;
	}

	/**
	 * Returns the zip code of the customer.
	 *
	 * @return the zip code of the customer
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Returns the list of shipped orders for the customer.
	 *
	 * @return the list of shipped orders for the customer
	 */
	public LinkedList<Order> getShippedOrders() {
		return shippedOrders;
	}

	/**
	 * Returns the list of unshipped orders for the customer.
	 *
	 * @return the list of unshipped orders for the customer
	 */
	public LinkedList<Order> getUnshippedOrders() {
		return unshippedOrders;
	}


	/**** MUTATORS ****/

	/**
	 * Sets the address of the customer.
	 *
	 * @param address the address to set
	 * @postcondition the address of the customer is set to the given value
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Sets the city of the customer.
	 *
	 * @param city the city to set
	 * @postcondition the city of the customer is set to the given value
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Sets the state of the customer.
	 *
	 * @param state the state to set
	 * @postcondition the state of the customer is set to the given value
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Sets the zip code of the customer.
	 *
	 * @param zip the zip code to set
	 * @postcondition the zip code of the customer is set to the given value
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * Sets the list of shipped orders for the customer.
	 *
	 * @param shippedOrders the list of shipped orders to set
	 * @postcondition the list of shipped orders for the customer is set to the given value
	 */
	public void setShippedOrders(LinkedList<Order> shippedOrders) {
		this.shippedOrders = shippedOrders;
	}

	/**
	 * Sets the list of unshipped orders for the customer.
	 *
	 * @param unshippedOrders the list of unshipped orders to set
	 * @postcondition the list of unshipped orders for the customer is set to the given value
	 */
	public void setUnshippedOrders(LinkedList<Order> unshippedOrders) {
		this.unshippedOrders = unshippedOrders;
	}

	/**
	 * Adds a shipped order to the list of shipped orders for the customer.
	 *
	 * @param order the order to add
	 * @postcondition the order is added to the list of shipped orders for the customer
	 */
	public void addShippedOrder(Order order) {
		shippedOrders.addLast(order);
	}

	/**
	 * Adds an unshipped order to the list of unshipped orders for the customer.
	 *
	 * @param order the order to add
	 * @postcondition the order is added to the list of unshipped orders for the customer
	 */
	public void addUnshippedOrder(Order order) {
		unshippedOrders.addLast(order);
	}

	/**
	 * Removes a shipped order from the list of shipped orders for the customer.
	 *
	 * @param order the order to remove
	 * @throws NoSuchElementException if the order is not found in the list of shipped orders
	 * @postcondition the order is removed from the list of shipped orders for the customer
	 */
	public void removeShippedOrder(Order order) throws NoSuchElementException {
		int index = shippedOrders.findIndex(order);
		if (index != -1) {
			shippedOrders.advanceIteratorToIndex(index);
			shippedOrders.removeIterator();
		} else {
			throw new NoSuchElementException("removeShippedOrder(): Order not found");
		}
	}

	/**
	 * Removes an unshipped order from the list of unshipped orders for the customer.
	 *
	 * @param order the order to remove
	 * @throws NoSuchElementException if the order is not found in the list of unshipped orders
	 * @postcondition the order is removed from the list of unshipped orders for the customer
	 */
	public void removeUnshippedOrder(Order order) throws NoSuchElementException {
		int index = unshippedOrders.findIndex(order);
		if (index != -1) {
			unshippedOrders.advanceIteratorToIndex(index);
			unshippedOrders.removeIterator();
		} else {
			throw new NoSuchElementException("removeUnshippedOrder(): Order not found");
		}
	}

	/**** ADDITIONAL METHODS ****/

	/**
	 * Returns a string representation of the Customer.
	 *
	 * @return a string representation of the Customer
	 */
	@Override
	public String toString() {
		return super.toString() + "Customer" + "\n";
	}
}
