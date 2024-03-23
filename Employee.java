/**
 * Employee.java
 * Represents an employee who is a user of the system.
 * 
 * @author Abdullah Mohammad
 */
public class Employee extends User {
    private boolean isManager;


    /**
     * Constructor for the Employee class
     * 
     * @param firstName
     * @param lastName
     * @param login
     * @param password
     * @param isManager
     * @postcondition a new Employee object is created with the given values
     */
    public Employee(String firstName, String lastName, String login, String password, boolean isManager) {
        setFirstName(firstName);
        setLastName(lastName);
        setLogin(login);
        setPassword(password);

        this.isManager = isManager;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }


}
