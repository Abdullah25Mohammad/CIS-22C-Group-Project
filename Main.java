/**
 * Main class
 * 
 * @author Abdullah Mohammad
 * @author Chahid Bagdouri
 * @author [more names here]
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.naming.directory.SearchResult;

public class Main {
    private static Scanner myScanner = new Scanner(System.in);

    // HashTables
    private static HashTable<Customer> customerTable = new HashTable<Customer>(10);
    private static HashTable<Employee> employeeTable = new HashTable<Employee>(10);

    // BSTs
    private static BST<Game> gamesByTitle = new BST<Game>();
    private static BST<Game> gamesByDeveloper = new BST<Game>();
    private static BST<Game> gamesByPrice = new BST<Game>();

    private static TitleComparator titleCMP = new TitleComparator();
    private static DeveloperComparator developerCMP = new DeveloperComparator();
    private static PriceComparator priceCMP = new PriceComparator();

    /**
     * main method
     * 
     * @author Abdullah Mohammad
     * @author Chahid Bagdouri
     */
    public static void main(String[] args) {
        createLoginTables();
        createDatabase();

        // System.out.println("Customer Table:");
        // System.err.println(customerTable.toString());
        // System.out.println("Employee Table:");
        // System.err.println(employeeTable.toString());

        System.out.println("Welcome to the Video Games Store!");
        System.out.println("Would you like to login as a Customer or Employee?");
        System.out.println("Type '1' for Customer and '2' for Employee.");
        int choice = Integer.parseInt(myScanner.nextLine());

        while(choice != 1 && choice != 2) {
            System.out.println("Please try again.");
            System.out.println("Type '1' for Customer and '2' for Employee.");
            choice = Integer.parseInt(myScanner.nextLine());
        }

        if(choice == 1) {
            Customer tempCustomer = loginAsCustomer();
            customerTable.add(tempCustomer);
        }
        else if (choice == 2) {
            employeeTable.add(loginAsEmployee());
        }   
    }

    /**
     * Reads the users.txt file and creates the Customer and Employee objects
     * 
     * @author Abdullah Mohammad
     */
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
    
    /**
     * Reads the database.txt file and creates the Game objects
     * 
     * @author Abdullah Mohammad
     */
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

                gamesByDeveloper.insert(game, developerCMP);
                temp = gamesByDeveloper;
                gamesByDeveloper = new BST<>(temp, developerCMP);

                gamesByPrice.insert(game, priceCMP);
                temp = gamesByPrice;
                gamesByPrice = new BST<>(temp, priceCMP);
            }
            scanner.close();
        } catch (FileNotFoundException error) {
            System.out.println("An error occurred.");
            error.printStackTrace();
        }
    }

    /**
     * Logs in as a Customer
     * 
     * @author Chahid Bagdouri
     * @return logged in Customer object
     */
    public static Customer loginAsCustomer() {
        System.out.println("Please select one of the following options by typing in the corresponding number:");
        System.out.println("1. Make a new account");
        System.out.println("2. Login to an existing account.");
        System.out.println("3. Continue as a Guest.");
        int choice = Integer.parseInt(myScanner.nextLine());

        while(choice != 1 && choice != 2 && choice != 3) {
            System.out.println("Please try again.");
            System.out.println("Please select one of the following options by typing in the corresponding number:");
            System.out.println("1. Make a new account");
            System.out.println("2. Login to an existing account.");
            System.out.println("3. Continue as a Guest.");
            choice = Integer.parseInt(myScanner.nextLine());
        }

        Customer tempCustomer = null;
        
        if (choice == 1) {
            tempCustomer = loginAsNewCustomer();
            CustomerOptions(tempCustomer, false);
        }
        
        else if(choice == 2) {
            tempCustomer = loginAsExistingCustomer();
            CustomerOptions(tempCustomer, false);
        }
        
        else if(choice == 3) {
            tempCustomer = new Customer();
            CustomerOptions(tempCustomer, true);
        }

        return tempCustomer;
    }
    
    /**
     * private helper method for loginAsCustomer()
     * Makes a new Customer account
     * Logs in as the new Customer
     * Appends the Customer's information to Users.txt
     * 
     * @author Chahid Bagdouri
     * @return logged in Customer object
     */
    private static Customer loginAsNewCustomer() {
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

        
        try {
            FileWriter myWriter = new FileWriter("Users.txt", true);
            myWriter.append("\n");
            myWriter.append(tempCustomer.getName() + "\n");
            myWriter.append(tempCustomer.getUsername() + "\n");
            myWriter.append(tempCustomer.getPassword() + "\n");
            myWriter.append("customer\n");

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return tempCustomer;
    }
    
    /**
     * private helper method for loginAsCustomer()
     * Logs in to Existing Customer Account
     * 
     * @author Chahid Bagdouri
     * @return logged in Customer object
     */
    private static Customer loginAsExistingCustomer() {
        System.out.print("Please enter your username: ");
        String username = myScanner.next();
        System.out.print("Please enter your password: ");
        String password = myScanner.next();

        Customer tempCustomer = new Customer("", "", username, password);

        if((customerTable.find(tempCustomer) != -1)) {
            tempCustomer = customerTable.get(tempCustomer);
            System.out.println("Successfully logged in as " + tempCustomer.getName());
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

    /**
     * Displays options to the Customer
     * 
     * @author Chahid Bagdouri
     * @param tempCustomer
     * @param isGuest
     */
    public static void CustomerOptions(Customer tempCustomer, boolean isGuest) {
        int choice1 = 0;

        do {
            System.out.println("Please select one of the following options by typing in the corresponding number:");
            System.out.println("1. Search for a product.");
            System.out.println("2. List Database of Products.");

            if(!isGuest) { // Only show these options if the user is not a guest
                System.out.println("3. Place an Order.");
                System.out.println("4. View Purchases.");
            }

            System.out.println("-1. Exit the program.");

            myScanner.nextLine(); // clear the buffer
            choice1 = Integer.parseInt(myScanner.nextLine());

            if(choice1 == 1) {
                SearchForGame(tempCustomer, isGuest);
            }
            else if (choice1 == 2) {
                DisplayGameDatabase();
            }
            else if (choice1 == 3) {
                CustomerPlaceOrder(tempCustomer);
            }
            else if (choice1 == 4) {
                System.out.println("Not implemented yet.");
            }
            else if (choice1 == -1) {
                System.exit(0);
            }
        } while(choice1 != 1 && choice1 != 2 && choice1 != 3 && choice1 != 4 && choice1 != -1);
    }

    /**
     * Asks the user what game/developer to search for, and then performs the search
     * 
     * @author Chahid Bagdouri
     * @param tempCustomer
     * @param isGuest
     */
    private static void SearchForGame(Customer tempCustomer, boolean isGuest) {
        int choice = 0;

        System.out.println("How would you like to search for a game?");

        do {
            System.out.println("Please select one of the following options by typing in the corresponding number:");
            System.out.println("1. Search by game title.");
            System.out.println("2. Search by game developer name.");
            System.out.println("3. Go back to menu options.");
            System.out.println("-1. Exit the program.");

            choice = Integer.parseInt(myScanner.nextLine());

            if(choice != 1 && choice != 2 && choice != 3 && choice != -1) {
                System.out.println("Invalid input. Please try again.");
            }
        } while(choice != 1 && choice != 2 && choice != 3 && choice != -1);

        if((choice == 1) || (choice == 2)) {
            System.out.println("Please enter the name of the game/developer you would like to search for:");
            String search = myScanner.nextLine();

            LinkedList<Game> searchResults = new LinkedList<Game>();

            if(choice == 1) {
                Game tempGame = new Game(search, "");
                
                Game tempResult = gamesByTitle.search(tempGame, titleCMP);

                while (tempResult != null) {
                    searchResults.addLast(tempResult);
                    gamesByTitle.remove(tempResult, titleCMP);
                    tempResult = gamesByTitle.search(tempGame, titleCMP);
                }
            }
            else if(choice == 2) {
                Game tempGame = new Game("", search);

                Game tempResult = gamesByDeveloper.search(tempGame, developerCMP);

                while (tempResult != null) {
                    searchResults.addLast(tempResult);
                    gamesByDeveloper.remove(tempResult, developerCMP);
                    tempResult = gamesByDeveloper.search(tempGame, developerCMP);
                }
            }

            if(searchResults.isEmpty()) {
                System.out.println("No results found.");
            }
            else {
                // Print out the search results
                System.out.println(searchResults.toString());
            }
        }

        else if(choice == 3) {
            CustomerOptions(tempCustomer, isGuest);
        }
        else if(choice == -1) {
            System.exit(0);
        }
    }

    /**
     * Displays the game database
     * 
     * @author Abdullah Mohammad
     */
    private static void DisplayGameDatabase() {
        int choice = 0;

        do {
            System.out.println("Please select one of the following options by typing in the corresponding number:");
            System.out.println("1. Display games by title.");
            System.out.println("2. Display games by developer.");
            System.out.println("3. Display games by price.");
            System.out.println("-1. Exit the program.");

            choice = Integer.parseInt(myScanner.nextLine());

            if(choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != -1) {
                System.out.println("Invalid input. Please try again.");
            }
        } while(choice != 1 && choice != 2 && choice != 3 && choice != -1);

        if(choice == 1) {
            System.out.println(gamesByTitle.inOrderString());
        }
        else if(choice == 2) {
            System.out.println(gamesByDeveloper.inOrderString());
        }
        else if(choice == 3) {
            System.out.println(gamesByPrice.inOrderString());
        }
        else if(choice == -1) {
            System.exit(0);
        }

    }

    /**
     * Asks the user what game to order, and then orders it for the user
     * 
     * @author Chahid Bagdouri
     * @param tempCustomer
     */
    private static void CustomerPlaceOrder(Customer tempCustomer) {
        System.out.println("What would you like to order?: ");
        String gameTitle = myScanner.nextLine();
        System.out.println(gamesByTitle.search(new Game(gameTitle, ""), titleCMP));

        int choice = -1;
        while(choice != 1 && choice != 2) {
            System.out.println("What type of shipping would you like?");
            System.out.println("Please select one of the following options by typing in the corresponding number:");
            System.out.println("1. Overnight Shipping.");
            System.out.println("2. Rush Shipping.");
            System.out.println("3. Standard Shipping.");

            if(choice == 1) {
                // TODO: Implement
            }
            else if (choice == 2) {
                // TODO: Implement
            }
            else if (choice == 3) {
                // TODO: Implement
            }
            else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }


    /**
     * Logs in as an Employee
     * 
     * @author Chahid Bagdouri
     * @return logged in Employee object
     */
    public static Employee loginAsEmployee() {
        boolean isEmployee = false;
        Employee tempEmployee = null;
        while(!isEmployee) {
            System.out.println("Please enter your username:");
            String username = myScanner.nextLine();
            System.out.println("Please enter your password:");
            String password = myScanner.nextLine();

            tempEmployee = new Employee("", "", username, password, false);
            isEmployee = (employeeTable.find(tempEmployee) != -1);
            
            if(isEmployee) {
                tempEmployee = employeeTable.get(tempEmployee);
                System.out.println("Successfully logged in as " + tempEmployee.getFirstName() + " " + tempEmployee.getLastName());
            }
            else {
                System.out.println("Login failed. Please try again.");
            }
        }
        return tempEmployee;
    }

    /**
     * Displays options to the Employee
     * 
     * @author Chahid Bagdouri
     * @author Michael Chen
     * @param tempEmployee
     */
    public static void EmployeeOptions(Employee tempEmployee) {
        int choice = 0;

        do {
            System.out.println("Please select one of the following options by typing in the corresponding number:");
            System.out.println("1. Search for a product.");
            System.out.println("2. List Database of Products.");
            System.out.println("3. Place an Order.");
            System.out.println("4. View Purchases.");
            System.out.println("-1. Exit the program.");

            choice = Integer.parseInt(myScanner.nextLine());

            if(choice == 1) {
                // TODO: Implement
            }
            else if (choice == 2) {
                // TODO: Implement
            }
            else if (choice == 3) {
                // TODO: Implement
            }
            else if (choice == 4) {
                // TODO: Implement
            }
            else if (choice == -1) {
                System.exit(0);
            }
        } while(choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != -1);
    }
}