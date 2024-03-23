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
     * Sets the login of the user.
     *
     * @param login the login of the user
     * @postcondition the login of the user is set
     */
    public void setLogin(String login) {
        this.login = login;
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
     * Returns a consistent hash code for each User.
     * 
     * @return the hash code for the User
     */
    @Override
    public int hashCode() {
        int sum = 0;
        for (int i = 0; i < login.length(); i++) {
            sum += login.charAt(i);
        }
        return sum;
    }
}
