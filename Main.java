/**
 * Main class
 * 
 * @author Abdullah Mohammad
 * @author Chahid Bagdouri
 * @author [more names here]
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static HashTable<Customer> customerTable = new HashTable<Customer>(10);
    private static HashTable<Employee> employeeTable = new HashTable<Employee>(10);

    // BSTs
    private static BST<Game> gamesByTitle = new BST<Game>();
    private static BST<Game> gamesByReleaseDate = new BST<Game>();
    // private static BST<Game> gamesByPrice = new BST<Game>(); // UNCOMMENT WHEN IMPLEMENTED
    private static TitleComparator titleCMP = new TitleComparator();
    private static ReleaseDateComparator releaseDateCMP = new ReleaseDateComparator();
    // PriceComparator priceCMP = new PriceComparator(); // UNCOMMENT WHEN IMPLEMENTED

    public static void main(String[] args) {
        createLoginTables();
        createDatabase();

        // System.out.println("Customer Table:");
        // System.err.println(customerTable.toString());
        // System.out.println("Employee Table:");
        // System.err.println(employeeTable.toString());

        System.out.println("Welcome to the Video Games Store!");
        System.out.println("Please login to continue.");
        login();
    }

    public static void createLoginTables() {
        try {
            File file = new File("users.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String firstName = scanner.next();
                String lastName = scanner.next();
                scanner.nextLine(); // clear the buffer

                String username = scanner.nextLine();
                String password = scanner.nextLine();
                String role = scanner.nextLine();

                if (role.equals("customer")) {
                    Customer customer = new Customer(
                        firstName,
                        lastName,
                        username,
                        password
                    );
                    customerTable.add(customer);
                } else if (role.equals("employee")) {
                    Employee employee = new Employee(
                        firstName,
                        lastName,
                        username,
                        password,
                        false
                    );
                    employeeTable.add(employee);
                } else if (role.equals("manager")) {
                    Employee employee = new Employee(
                        firstName,
                        lastName,
                        username,
                        password,
                        true
                    );
                    employeeTable.add(employee);
                } else {
                    System.out.println("Invalid role: " + role);
                }
                
            }
            scanner.close();
        } catch (FileNotFoundException error) {
            System.out.println("An error occurred.");
            error.printStackTrace();
        }
    }

    public static void createDatabase() {
        try {
            File file = new File("database.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String title = scanner.nextLine();
                String developer = scanner.nextLine();
                String id = scanner.nextLine();
                String genre = scanner.nextLine();
                String releaseDateString = scanner.nextLine();
                Date releaseDate = new Date(releaseDateString);
                String summary = scanner.nextLine();
                String priceString = scanner.nextLine();
                double price = Double.parseDouble(priceString.substring(1));
                String stockString = scanner.nextLine();
                int stock = Integer.parseInt(stockString);

                scanner.nextLine(); // skip a line

                Game game = new Game(
                    title,
                    developer,
                    id,
                    genre,
                    releaseDate,
                    summary,
                    price,
                    stock
                );

                // Insert and Reorder BSTs
                BST<Game> temp;

                gamesByTitle.insert(game, titleCMP);
                temp = gamesByTitle;
                gamesByTitle = new BST<>(temp, titleCMP);

                gamesByReleaseDate.insert(game, releaseDateCMP);
                temp = gamesByReleaseDate;
                gamesByReleaseDate = new BST<>(temp, releaseDateCMP);

                // Add gamesByPrice when implemented
            }
            scanner.close();
        } catch (FileNotFoundException error) {
            System.out.println("An error occurred.");
            error.printStackTrace();
        }
    }

    public static void login() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter your username:");
        String username = myScanner.nextLine();
        System.out.println("Please enter your password:");
        String password = myScanner.nextLine();

        Customer tempCustomer = new Customer("", "", username, password);
        boolean isCustomer = (customerTable.find(tempCustomer) != -1);
        if(isCustomer) {
            tempCustomer = customerTable.get(tempCustomer);
            System.out.println("Successfully logged in as " + tempCustomer.getFirstName() + " " + tempCustomer.getLastName());
        }
        else {
            Employee tempEmployee = new Employee("", "", username, password, false);
            boolean isEmployee = (employeeTable.find(tempEmployee) != -1);
            if(isEmployee) {
                tempEmployee = employeeTable.get(tempEmployee);
                System.out.println("Successfully logged in as " + tempEmployee.getFirstName() + " " + tempEmployee.getLastName());
            }
            else {
                System.out.println("Login failed.");
            }
        }

        myScanner.close();
    }
}
