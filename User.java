/**
 * User.java
 * Abstract class for a user of the system.
 * 
 * @author Abdullah Mohammad
 */
public class User {
    String firstName;
    String lastName;
    String login;
    String password;

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
     * Returns the login of the user.
     *
     * @return the login of the user
     */
    public String getLogin() {
        return login;
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
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName the last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the login of the user.
     *
     * @param login the login of the user
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
