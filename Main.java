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
    private static Scanner myScanner = new Scanner(System.in);

    // HashTables
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
        System.out.println("Would you like to login as a Customer or Employee?");

        int choice = -1;
        while(choice != 1 && choice != 2) {
            System.out.println("Type '1' for Customer and '2' for Employee.");
            choice = Integer.parseInt(myScanner.nextLine());
            if(choice == 1) {
                loginAsCustomer();
                break;
            }
            if(choice == 2) {
                loginAsEmployee();
                break;
            }        
            else {
                System.out.println("Please try again.");
            }
        }
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
                String role = scanner.next();
                scanner.nextLine(); // clear the buffer

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

    public static void loginAsCustomer() {
        System.out.println("Would you like to make a new account or login to an existing one?");
        System.out.println("Type '1' for new account and '2' for existing account.");
        int choice = Integer.parseInt(myScanner.nextLine());

        while(choice != 1 && choice != 2) {
            System.out.println("Please try again.");
            System.out.println("Type '1' for new account and '2' for existing account.");
            choice = Integer.parseInt(myScanner.nextLine());
        }

        Customer tempCustomer;
        
        if(choice == 1) {
            tempCustomer = loginAsNewCustomer();
        }
        
        else if(choice == 2) {
            tempCustomer = loginAsExistingCustomer();
        }
    }

    public static Customer loginAsNewCustomer() {
        System.out.print("Please enter your first name: ");
        String firstName = myScanner.next();
        System.out.print("Please enter your last name: ");
        String lastName = myScanner.next();
        System.out.print("Please enter a username: ");
        String username = myScanner.next();
        System.out.print("Please enter a password: ");
        String password = myScanner.next();

        Customer tempCustomer = new Customer(firstName, lastName, username, password);

        if((customerTable.find(tempCustomer) != -1)) {
            System.out.println("The user with the information that you provided already exists.");
            tempCustomer = customerTable.get(tempCustomer);
            System.out.println("Successfully logged in as existing user " + tempCustomer.getFirstName() + " " + tempCustomer.getLastName());
        }

        else {  
            customerTable.add(tempCustomer);

            System.out.println("Successfully created account for and logged in as " + tempCustomer.getFirstName() + " " + tempCustomer.getLastName());
        }

        return tempCustomer;
    }
    
    public static Customer loginAsExistingCustomer() {
        System.out.print("Please enter your username: ");
        String username = myScanner.next();
        System.out.print("Please enter your password: ");
        String password = myScanner.next();

        Customer tempCustomer = new Customer("", "", username, password);

        if((customerTable.find(tempCustomer) != -1)) {
            tempCustomer = customerTable.get(tempCustomer);
            System.out.println("Successfully logged in as " + tempCustomer.getFirstName() + " " + tempCustomer.getLastName());
        }

        else {
            System.out.println("Username and password do not match.");
            System.out.println("Would you like to try again or make a new account?");
            System.out.println("Type '1' to try again or '2' for new account.");
            myScanner.nextLine(); // clear the buffer
            int choice = Integer.parseInt(myScanner.nextLine());

            while(choice != 1 && choice != 2) {
                System.out.println("Please try again.");
                System.out.println("Type '1' to try again or '2' for new account.");
                myScanner.nextLine(); // clear the buffer
                choice = Integer.parseInt(myScanner.nextLine());
            }

            if(choice == 1) {
                tempCustomer = loginAsExistingCustomer();
            }
            else if (choice == 2) {
                tempCustomer = loginAsNewCustomer();
            }
        }

        return tempCustomer;
    }
    
    public static void loginAsEmployee() {
        System.out.println("Please enter your username:");
        String username = myScanner.nextLine();
        System.out.println("Please enter your password:");
        String password = myScanner.nextLine();


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
}