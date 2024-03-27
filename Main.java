/**
 * Main class
 * 
 * @author Abdullah Mohammad
 * @author Chahid Bagdouri
 * @author Jacob L. Johnston
 * @author Hari Prakash
 * @author Michael Chen
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner myScanner = new Scanner(System.in);

    // HashTables
    private static HashTable<Customer> customerTable = new HashTable<Customer>(10);
    private static HashTable<Employee> employeeTable = new HashTable<Employee>(10);

    // BSTs
    private static BST<Game> gamesByTitle = new BST<Game>();
    private static BST<Game> gamesByDeveloper = new BST<Game>();
    private static BST<Game> gamesByPrice = new BST<Game>();
    private static BST<Order> orderByID = new BST<>();
    private static BST<Order> orderByName = new BST<>();

    private static TitleComparator titleCMP = new TitleComparator();
    private static DeveloperComparator developerCMP = new DeveloperComparator();
    private static PriceComparator priceCMP = new PriceComparator();


    private static Heap<Order> unshippedOrders = new Heap<>(new ArrayList<>(), new PriorityComparator());
    private static Heap<Order> shippedOrders = new Heap<>(new ArrayList<>(), new PriorityComparator());


    /**
     * main method
     * 
     * @author Abdullah Mohammad
     * @author Chahid Bagdouri
     * @author Jacob L. Johnston
     */
    public static void main(String[] args) {
        createLoginTables();
        createDatabase();
        createOrders();

        // System.out.println("Customer Table:");
        // System.err.println(customerTable.toString());
        // System.out.println("Employee Table:");
        // System.err.println(employeeTable.toString());

        System.out.println("Welcome to the Video Games Store!");
        System.out.println("Would you like to login as a Customer, Employee, or Manager?");
        int choice = 0;
        while (true) {
            try {
                System.out.println("Type '1' for Customer, '2' for Employee, and '3' for Manager.");
                choice = Integer.parseInt(myScanner.nextLine());
                if (choice >= 1 && choice <= 3) break;
                System.out.println("Invalid choice. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
//        System.out.println(); // newline

        while (choice < 1 || choice > 3) {
            System.out.println("Please try again.");
            System.out.println("Type '1' for Customer, '2' for Employee, and '3' for Manager.");
            choice = Integer.parseInt(myScanner.nextLine());
//            System.out.println(); // newline
        }

        switch (choice) {
            case 1:
                Customer tempCustomer = loginAsCustomer();
                customerTable.add(tempCustomer);
                break;
            case 2:
                Employee tempEmployee = loginAsEmployee();
                employeeTable.add(tempEmployee);
                EmployeeOptions(tempEmployee);
                break;
            case 3:
                Employee manager = loginAsManager();
                managerOptions(manager);
                break;
            default:
                System.out.println("Invalid choice. Exiting the program.");
                break;
        }
    }

    /**** Technical Setup ****/

    /**
     * Reads the Users.txt file and creates the Customer and Employee objects
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
                String platformsString = scanner.nextLine();
                ArrayList<String> platforms = new ArrayList<String>();
                for (String platform : platformsString.split(", ")) {
                    platforms.add(platform);
                }
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
                    platforms,
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
     * Reads the orders.txt file and creates the Order objects
     *
     * @author Michael Chen
     */
    public static void createOrders() {
        try {
            File file = new File("orders.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                int orderID = Integer.parseInt(scanner.nextLine());
                String username = scanner.nextLine();
                String password = scanner.nextLine();
                Customer cus = customerTable.get(new Customer("", "", username, password));
                int numProducts = Integer.parseInt(scanner.nextLine());
                LinkedList<Game> products = new LinkedList<>();
                for(int i = 0; i < numProducts; i++)
                {
                    String productTitle = scanner.nextLine();
                    Game tempGame = gamesByTitle.search(new Game(productTitle, ""), titleCMP);
                    products.addLast(tempGame);
                }

                Date datePlaced = new Date(scanner.nextLine());
                int shippingSpeed = Integer.parseInt(scanner.nextLine());

                Order newOrder = new Order(orderID, cus, datePlaced, products, shippingSpeed);
                cus.addUnshippedOrder(newOrder);
                orderByID.insert(newOrder, new IDComparator());
                orderByName.insert(newOrder, new NameComparator());
                unshippedOrders.insert(newOrder);

            }
            scanner.close();
        } catch (FileNotFoundException error) {
            System.out.println("An error occurred. in createOrders");
            error.printStackTrace();
        }
    }

    /**** Customer Methods ****/

    /**
     * Logs in as a Customer
     * 
     * @author Chahid Bagdouri
     * @return logged in Customer object
     */
    public static Customer loginAsCustomer() {
        System.out.println("\nPlease select one of the following options by typing in the corresponding number:");
        System.out.println("1. Make a new account");
        System.out.println("2. Login to an existing account.");
        System.out.println("3. Continue as a Guest.");
        int choice = Integer.parseInt(myScanner.nextLine());
//        System.out.println(); // newline

        while(choice != 1 && choice != 2 && choice != 3) {
            System.out.println("\nPlease try again.");
            System.out.println("\nPlease select one of the following options by typing in the corresponding number:");
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
        System.out.print("\nPlease enter your first name: ");
        String firstName = myScanner.next();
        System.out.print("Please enter your last name: ");
        String lastName = myScanner.next();
        myScanner.nextLine(); // Clear the buffer after reading the last name

        System.out.print("Please enter a username: ");
        String username = myScanner.next();


        System.out.print("Please enter a password: ");
        String password = myScanner.next();
        myScanner.nextLine(); // Clear the buffer after reading the password
//        System.out.println(); // newline

        Customer tempCustomer = new Customer(firstName, lastName, username, password);

        if((customerTable.find(tempCustomer) != -1)) {
            System.out.println("\nThe user with the information that you provided already exists.");
            tempCustomer = customerTable.get(tempCustomer);
            System.out.println("Successfully logged in as existing user " + tempCustomer.getFirstName() + " " + tempCustomer.getLastName() + ".");
        } else {
            customerTable.add(tempCustomer);
            System.out.println("Successfully created account for and logged in as " + tempCustomer.getFirstName() + " " + tempCustomer.getLastName() + ".");
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
//        System.out.println(); // newline
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
        myScanner.nextLine(); // clear the buffer
//        System.out.println(); // newline

        Customer tempCustomer = new Customer("", "", username, password);

        if((customerTable.find(tempCustomer) != -1)) {
            tempCustomer = customerTable.get(tempCustomer);
            System.out.println("Successfully logged in as " + tempCustomer.getName() + ".");
//            System.out.println(); // newline
        }

        else {
            System.out.println("Username and password do not match.");
            System.out.println("Would you like to try again or make a new account?");
            System.out.println("Type '1' to try again or '2' for new account.");
            int choice = Integer.parseInt(myScanner.nextLine());
//            System.out.println(); // newline

            while(choice != 1 && choice != 2) {
                System.out.println("Please try again.");
                System.out.println("Type '1' to try again or '2' for new account.");
                myScanner.nextLine(); // clear the buffer
                choice = Integer.parseInt(myScanner.nextLine());
//                System.out.println(); // newline
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
     * @author Abdullah Mohammad
     * @author Jacob L. Johnston
     * @param tempCustomer
     * @param isGuest
     */
    public static void CustomerOptions(Customer tempCustomer, boolean isGuest) {
        while (true) {
            System.out.println("\nPlease select one of the following options by typing in the corresponding number:");
            System.out.println("1. Search for a product.");
            System.out.println("2. List Database of Products.");
            if (!isGuest) {
                System.out.println("3. Place an Order.");
                System.out.println("4. View Purchases.");
            }
            System.out.println("-1. Exit the program.");

            String input = myScanner.nextLine().trim();
            int choice1;

            try {
                choice1 = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue; // Go back to the beginning of the loop and prompt again
            }

            switch (choice1) {
                case 1:
                    SearchForGame(tempCustomer, isGuest);
                    break;
                case 2:
                    DisplayGameDatabase();
                    break;
                case 3:
                    if (!isGuest) {
                        CustomerPlaceOrder(tempCustomer);
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;
                case 4:
                    if (!isGuest) {
                        ViewPurchases(tempCustomer);
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;
                case -1:
                    exitProgram();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    /**
     * Asks the user what game/developer to search for, and then performs the search
     * 
     * @author Abdullah Mohammad
     * @author Chahid Bagdouri
     * @param tempCustomer
     * @param isGuest
     */
    private static void SearchForGame(Customer tempCustomer, boolean isGuest) {
        int choice = 0;

        System.out.println("\nHow would you like to search for a game?");

        do {
            System.out.println("\nPlease select one of the following options by typing in the corresponding number:");
            System.out.println("1. Search by game title.");
            System.out.println("2. Search by game developer name.");
            System.out.println("3. Go back to main menu.");
            System.out.println("-1. Exit the program.");

            choice = Integer.parseInt(myScanner.nextLine());
//            System.out.println(); // newline

            if(choice != 1 && choice != 2 && choice != 3 && choice != -1) {
                System.out.println("Invalid input. Please try again.");
            }
        } while(choice != 1 && choice != 2 && choice != 3 && choice != -1);

        if((choice == 1) || (choice == 2)) {
            System.out.println("\nPlease enter the name of the game/developer you would like to search for:");
            String search = myScanner.nextLine();
//            System.out.println(); // newline

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
                System.out.println("\n" + searchResults.toString());
            }
        }

        else if(choice == 3) {
            return;
        }
        else if(choice == -1) {
            exitProgram();
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
            System.out.println("\nPlease select one of the following options by typing in the corresponding number:");
            System.out.println("1. Display games by title.");
            System.out.println("2. Display games by developer.");
            System.out.println("3. Display games by price.");
            System.out.println("-1. Exit the program.");

            choice = Integer.parseInt(myScanner.nextLine());
//            System.out.println(); // newline

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
            exitProgram();
        }

    }

    /**
     * Asks the user what game to order, and then orders it for the user
     * 
     * @author Chahid Bagdouri
     * @author Abdullah Mohammad
     * @param tempCustomer
     */
    private static void CustomerPlaceOrder(Customer tempCustomer) {
        System.out.print("\nWhat would you like to order?: ");
        String gameTitle = myScanner.nextLine();
//        System.out.println(); // newline
        Game result = gamesByTitle.search(new Game(gameTitle, ""), titleCMP);

        if(result == null) {
            System.out.println("Game not found.");
            return;
        } else {
            System.out.println(result);
        }

        LinkedList<Game> orderContents = new LinkedList<Game>();
        orderContents.addLast(result);

        int choice = -1;
        do {
            System.out.println("\nWhat type of shipping would you like?");
            System.out.println("\nPlease select one of the following options by typing in the corresponding number:");
            System.out.println("1. Overnight Shipping.");
            System.out.println("2. Rush Shipping.");
            System.out.println("3. Standard Shipping.");
            System.out.println("4. Cancel order and go back to main menu.");
            System.out.println("-1. Exit the program.");

            choice = Integer.parseInt(myScanner.nextLine());

            Order newOrder = new Order(
                tempCustomer,
                new Date(),
                orderContents,
                0
            );
            if(choice == 1) {
                newOrder.setShippingSpeed(1);
            }
            else if (choice == 2) {
                newOrder.setShippingSpeed(2);
            }
            else if (choice == 3) {
                newOrder.setShippingSpeed(3);
            }
            else if(choice == 4) {
                return;
            }
            else if(choice == -1) {
                exitProgram();
            }
            else {
                System.out.println("Invalid input. Please try again.");
                break;
            }

            tempCustomer.addUnshippedOrder(newOrder);
            unshippedOrders.insert(newOrder);
            orderByID.insert(newOrder, new IDComparator());
            orderByName.insert(newOrder, new NameComparator());

//            System.out.println(); // newline
            System.out.println("Order placed!");
            updateOrderFile(newOrder);
            
        } while(choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != -1);
    }

    /**
     * Displays the game database
     * 
     * @author Chahid Bagdouri
     * @author Abdullah Mohammad
     * @param tempCustomer
     */
    private static void ViewPurchases(Customer tempCustomer) {
        int choice = 0;

        do {
            System.out.println("\nPlease select one of the following options by typing in the corresponding number:");
            System.out.println("1. View shipped orders.");
            System.out.println("2. View unshipped orders.");
            System.out.println("3. Go back to main menu.");
            System.out.println("-1. Exit the program.");

            choice = Integer.parseInt(myScanner.nextLine());
//            System.out.println(); // newline

            if(choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != -1) {
                System.out.println("Invalid input. Please try again.");
            }
        // } while(choice != -1);
        } while(choice != 1 && choice != 2 && choice != 3 && choice != -1);
        
        if(choice == 1) {
            System.out.println(tempCustomer.getShippedOrders());
        }
        else if(choice == 2) {
            System.out.println(tempCustomer.getUnshippedOrders());
        }
        else if(choice == 3) {
            CustomerOptions(tempCustomer, false);
        }
        else if(choice == -1) {
            exitProgram();
        }

    }

    /**** Employee Methods ****/

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
//            System.out.println(); // newline
            System.out.println("Please enter your password:");
            String password = myScanner.nextLine();
//            System.out.println(); // newline

            tempEmployee = new Employee("", "", username, password, false);
            isEmployee = (employeeTable.find(tempEmployee) != -1);
            
            if(isEmployee) {
                tempEmployee = employeeTable.get(tempEmployee);
                System.out.println("Successfully logged in as " + tempEmployee.getFirstName() + " " + tempEmployee.getLastName() + ".");
//                System.out.println(); // newline
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
            System.out.println("\nPlease select one of the following options by typing in the corresponding number:");
            System.out.println("1. Search for a Order.");
            System.out.println("2. View Order with Highest Priority");
            System.out.println("3. View all Orders");
            System.out.println("4. Ship a order.");
            System.out.println("-1. Exit the program.");

            choice = Integer.parseInt(myScanner.nextLine());
//            System.out.println(); // newline

            if(choice == 1) {
                choice = searchForOrder();
            }
            else if (choice == 2) {
                choice = viewOrder(false);
            }
            else if (choice == 3) {
                choice = viewOrder(true);
            }
            else if (choice == 4) {
                shipOrder();
            }

            if (choice == -1) {
                return;
            }
        } while(choice != 1);
    }

    /**
     * Asks the user what order to search for, and then performs the search
     *
     * @author Michael Chen
     * @author Abdullah Mohammad
     */
    private static int searchForOrder() {
        int choice = 0;


        System.out.println("How would you like to search for a order?");

        do {
            System.out.println("\nPlease select one of the following options by typing in the corresponding number:");
            System.out.println("1. Search by Order ID.");
            System.out.println("2. Search by customer first and last name");
            System.out.println("3. Go back to main menu.");
            System.out.println("-1. Exit the program.");

            choice = Integer.parseInt(myScanner.nextLine());
//            System.out.println(); // newline

            if(choice != 1 && choice != 2 && choice != 3 && choice != -1) {
                System.out.println("Invalid input. Please try again.");
            }
        } while(choice != 1 && choice != 2 && choice != 3 && choice != -1);

        if(choice == 1)
        {
            System.out.println("Please enter the Order ID you are looking for: ");
            int ID = Integer.parseInt(myScanner.nextLine());
//            System.out.println(); // newline


            Order target = orderByID.search(new Order(ID), new IDComparator());
            if( target != null) {
                System.out.println(target + "\n");
            }
            else {
                System.out.println("No results found.");
            }
        }
        if(choice == 2)
        {
            System.out.println("Please enter the Customer you are looking for: ");
            String firstName = myScanner.next();
            String lastName = myScanner.next();
//            System.out.println(); // newline
            myScanner.nextLine(); // clear the buffer
            
            Order target = orderByName.search(
                new Order(
                    0, 
                    new Customer(
                        firstName, 
                        lastName, 
                        "", 
                        ""
                    ), 
                    new Date(), 
                    new LinkedList<Game>(), 
                    0
                ), 
                new NameComparator()
            );

            if( target != null) {
                System.out.println(target + "\n");
            }
            else {
                System.out.println("No results found.");
            }
        }
        if(choice == 3){
            return 0;
        }
        if(choice == 4)
        {
            return -1;
        }
        return 0;
    }

    /**
     * Displays most prioritized/all order(s)
     *
     * @author Michael Chen
     * @param all whether to display most prioritized or all orders
     */
    private static int viewOrder(boolean all) {
        if(all)
        {
            ArrayList<Order> temp = unshippedOrders.sort();
            System.out.println(temp);
        }
        else
        {
            System.out.println(unshippedOrders.getMax());
        }
//        System.out.println(); // newline
        return 0;
    }

    /**
     * Ships an order
     * 
     * @author Michael Chen
     * @author Hari Prakash
     * @return 0
     */
    private static int shipOrder() {
        try {
            if (unshippedOrders.getHeapSize() == 0) { 
                System.out.println("No orders to ship!");
                return 0;
            }
    
            Order shippedOrder = unshippedOrders.getMax();
    
            unshippedOrders.remove(1);
            shippedOrders.insert(shippedOrder);
    
            Customer customer = shippedOrder.getCustomer();
            customer.removeUnshippedOrder(shippedOrder);
            customer.addShippedOrder(shippedOrder);

            orderByID.remove(shippedOrder, new IDComparator());
            orderByName.remove(shippedOrder, new NameComparator());
    
            System.out.println("Order " + shippedOrder.getOrderID() + " shipped!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No more orders to ship.");
        }
        return 0;
    }
    

    /**** Manager Methods ****/

    /**
     * Handles the manager login process.
     * Asks for username and password, verifies the credentials, and returns a logged-in manager.
     *
     * @author Jacob L. Johnston
     * @return manager The logged-in manager for whom the options are displayed
     */
    public static Employee loginAsManager() {
        boolean isManager = false;
        Employee manager = null;
        while (!isManager) {
            System.out.println("Please enter your username:");
            String username = myScanner.nextLine();
            System.out.println("Please enter your password:");
            String password = myScanner.nextLine();
            manager = new Employee("", "", username, password, true);
            isManager = employeeTable.find(manager) != -1 && manager.isManager();
            if (isManager) {
                System.out.println("Successfully logged in as manager " + manager.getFirstName());
            } else {
                System.out.println("Login failed. Please try again.");
            }
        }
        return manager;
    }

    /**
     * Presents/handles the manager's operational options.
     * Allows managers to update the products catalogue and view orders.
     *
     * @author Jacob L. Johnston
     * @param manager The logged-in manager for whom the options are displayed.
     */
    public static void managerOptions(Employee manager) {
        int choice;
        do {
            System.out.println("Manager Options:");
            System.out.println("1. Update Products Catalogue");
            System.out.println("2. View Orders");
            System.out.println("-1. Exit");
            choice = Integer.parseInt(myScanner.nextLine());

            switch (choice) {
                case 1:
                    updateProductsCatalogue();
                    break;
                case 2:
                    System.out.println("View Orders:");
                    System.out.println("1. View all unshipped orders");
                    System.out.println("2. View order with highest priority");
                    int viewChoice = Integer.parseInt(myScanner.nextLine());
                    if (viewChoice == 2) {
                        viewOrder(false); // Only view the highest priority order
                    } else {
                        viewOrder(true); // View all unshipped orders
                    }
                    // viewOrder(viewChoice != 2); // Shorter version of the 5 lines above, but much less readable
                    break;
            }
        } while (choice != -1);
    }

    /**
     * Displays and handles the options for updating the products catalogue.
     * Allows adding new products, updating existing products, and removing products.
     *
     * @author Jacob L. Johnston
     */
    public static void updateProductsCatalogue() {
        int choice;
        do {
            System.out.println("Update Products Catalogue: ");
            System.out.println("1. Add New Product");
            System.out.println("2. Update Existing Product");
            System.out.println("3. Remove Product");
            System.out.println("-1. Back to Manager Options");
            choice = Integer.parseInt(myScanner.nextLine());

            switch (choice) {
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    updateExistingProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
            }
        } while (choice != -1);
    }

    /**
     * Adds a new product to the catalogue.
     * Guides the user through the process of entering product details and inserts the product into the BST.
     *
     * @author Jacob L. Johnston
     */
    private static void addNewProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adding a new product.");

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Developer: ");
        String developer = scanner.nextLine();

        System.out.print("ID: ");
        String id = scanner.nextLine();

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Release Date (yyyy-mm-dd): ");
        String dateString = scanner.nextLine();
        Date releaseDate = new Date(dateString); // Do we have a comparator for this?

        System.out.print("Summary: ");
        String summary = scanner.nextLine();

        System.out.print("Platforms (comma-separated): ");
        String platformsString = scanner.nextLine();

        // Create an ArrayList from split string
        String[] platformsArray = platformsString.split(",");
        ArrayList<String> platforms = new ArrayList<>();
        for (String platform : platformsArray) {
            platforms.add(platform.trim()); // leading or trailing spaces
        }

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        Game newGame = new Game(title, developer, id, genre, releaseDate, summary, platforms, price, stock);

        // Seperate BSTs
        gamesByTitle.insert(newGame, titleCMP);
        gamesByDeveloper.insert(newGame, developerCMP);

        System.out.println("New product added successfully.");
        scanner.close();
    }

    /**
     * Updates the price or stock of an existing product in the catalogue.
     * Searches for the product by key, allows the user to update its details, and updates the BST.
     *
     * @author Jacob L. Johnston
     */
    private static void updateExistingProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Update an existing product.");

        System.out.println("Enter the title of the product to update: ");
        String title = scanner.nextLine();

        Game game = gamesByTitle.search(new Game(title, ""), new TitleComparator());

        scanner.close();

        if (game == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Current information: " + game);
        System.out.print("New price (leave blank to keep current): ");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) {
            double price = Double.parseDouble(priceInput);
            game.setPrice(price);
        }

        System.out.print("New stock (leave blank to keep current): ");
        String stockInput = scanner.nextLine();
        if (!stockInput.isEmpty()) {
            int stock = Integer.parseInt(stockInput);
            game.setStock(stock);
        }

        System.out.println("Product updated successfully.");
    }

    /**
     * Removes a product from the catalogue.
     * Searches for the product by key and removes it from the BST if found.
     *
     * @author Jacob L. Johnston
     */
    private static void removeProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Removing a product.");

        System.out.print("Enter the title of the product to remove: ");
        String title = scanner.nextLine();

        scanner.close();

        Game game = gamesByTitle.search(new Game(title, ""), new TitleComparator());
        if (game == null) {
            System.out.println("Product not found.");
            return;
        }

        // Separate BST's
        gamesByTitle.remove(game, titleCMP);
        gamesByDeveloper.remove(game, developerCMP);

        System.out.println("Product removed successfully.");
    }

    /**
     * Updates the Orders.txt file
     * 
     * @author Hari Prakash
     */
    private static void updateOrderFile(Order newOrder){
        //               001    (orderID)
        //               airi   (username)
        //               1   (password)
        //               3   (numGames)
        //               Halo 3
        //               Minecraft
        //               Undertale
        //               05/01/2005 (datePlaced)
        //               3         (shippingSpeed)
        try {
            FileWriter writer = new FileWriter("orders.txt", true);
            writer.write("\n");
            writer.write(newOrder.getOrderID() + "\n");
            writer.write(newOrder.getCustomer().getUsername() + "\n");
            writer.write(newOrder.getCustomer().getPassword() + "\n");
            writer.write(newOrder.getOrderContents().getLength() + "\n");
    
            newOrder.getOrderContents().positionIterator();
            while (!newOrder.getOrderContents().offEnd()) {
                Game game = newOrder.getOrderContents().getIterator();
                writer.write(game.getTitle() + "\n");
                newOrder.getOrderContents().advanceIterator();
            }
    
            writer.write(newOrder.getDatePlaced().toString() + "\n");
            writer.write(newOrder.getShippingSpeed() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("updateOrderFile(): An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Exits the program
     * 
     * @author Abdullah Mohammad
     */
    private static void exitProgram() {
        System.out.println("Exiting the program.");
        System.exit(0);
    }
}