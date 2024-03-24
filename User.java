/**
 * User.java
 * Abstract class for a user of the system.
 * 
 * @author Abdullah Mohammad
 */
public class User {
    String firstName;
    String lastName;
    String username;
    String password;

    /**
     * Constructs a User object with the specified details.
     * 
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param username     the username of the user
     * @param password  the password of the user
     * @postcondition a new User object is created with the given values
     */
    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    /**** ACCESSORS ****/

    /**
     * Returns the first name of the user.
     *
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the user.
     *
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**** MUTATORS ****/

    /**
     * Sets the first name of the user.
     *
     * @param firstName the first name of the user
     * @postcondition the first name of the user is set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName the last name of the user
     * @postcondition the last name of the user is set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username of the user
     * @postcondition the username of the user is set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password of the user
     * @postcondition the password of the user is set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**** Additional Operations ****/

    /**
     * Returns a string representation of the User.
     * 
     * @return a string representation of the User
     */
    @Override
    public String toString() {
        return (
            "Name: " + firstName + " " + lastName + "\n" + 
            "Username: " + username + "\n"
            );
    }

    /**
     * Returns a consistent hash code for each User.
     * 
     * @return the hash code for the User
     */
    @Override
    public int hashCode() {
        int sum = 0;
        for (int i = 0; i < username.length(); i++) {
            sum += username.charAt(i);
        }
        for (int i = 0; i < password.length(); i++) {
            sum += password.charAt(i);
        }
        return sum;
    }
}