public class Employee extends User {
    public Employee(String firstName, String lastName, String login, String password, boolean isManager) {
        setFirstName(firstName);
        setLastName(lastName);
        setLogin(login);
        setPassword(password);

        this.isManager = isManager; // ??
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }

    public void setFirstName(String firstName) {

    }
}
