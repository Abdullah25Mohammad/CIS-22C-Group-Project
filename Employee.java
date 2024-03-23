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
        super(firstName, lastName, login, password);

        this.isManager = isManager;
    }

    /**** ACCESSORS ****/

    public boolean isManager() {
        return isManager;
    }

    /**** MUTATORS ****/

    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }


    /**** ADDITIONAL METHODS ****/

    @Override
    public String toString() {
        return super.toString() + "Manager: " + isManager + "\n";
    }



}
