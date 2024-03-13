import java.util.LinkedList;

public class Customer extends User{
  private String address;
	private String city;
	private String state;
	private String zip;
	private LinkedList<Order> shippedOrders;
	private LinkedList<Order> unshippedOrders;

	public Customer(String firstName, String lastName, String login, String password, String address, String city, String state, String zip) {
		setFirstName(firstName);
		setLastName(lastName);
		setLogin(login);
		setPassword(password);

		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.shippedOrders = new LinkedList<>(); // LinkedList I guess?
		this.unshippedOrders = new LinkedList<>(); // LinkedList I guess?
	}
	
	// Getters
	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public LinkedList<Order> getShippedOrders() {
		return shippedOrders;
	}

	public LinkedList<Order> getUnshippedOrders() {
		return unshippedOrders;
	}

	// Setters
	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setAddress(String state) {
		this.state = state;
	}

	public void setAddress(String zip) {
		this.zip = zip;
	}

	public void setShippedOrders(LinkedList<Order> shippedOrders) {
		this.shippedOrders = shippedOrders;
	}

	public void setUnshippedOrders(LinkedList<Order> unshippedOrders) {
		this.unshippedOrders = unshippedOrders;
	}

}
