import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.time.format.*;

/**
 * Class Name : MusicStore
 * Purpose : This class simulates the working of a MusicStore from creating items like Records and T-shirts to creating
 * customers and placing orders. The class manages the entire working of the store including keeping track of inventory
 * and making user experience easy.
 * @author  : Arnav Singh Sethi
 * @version : ver1.4
 */
public class MusicStore
{
    /**
     * Validates that a string contains only alphabets and whitespaces.
     * @param inputUser The input string to be validated.
     * @return true if the string is valid, false otherwise.
     */
    private static boolean checkString(String inputUser)
    {
        for (int i = 0; i < inputUser.length(); i++)
        {
            char c = inputUser.charAt(i);
            // Check if the character is not a letter and not a space
            if (!(Character.isLetter(c) || Character.isWhitespace(c)))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Prompts the user for a double input and validates it.
     * @param sc The scanner object for reading user input.
     * @param userMessage The message to display to the user.
     * @return The validated double input.
     */
    public static Double getDoubleInput(Scanner sc, String userMessage)
    {
        double doubleVar;
        while (true)
        {
            System.out.println(userMessage);
            // using try catch to detect non-double value
            try
            {
                doubleVar = Double.parseDouble(sc.nextLine().trim());
                if (doubleVar < 0)
                {
                    System.out.println("\033[0;31m" + " !! input must be a positive value !!"+"\033[0m");
                    continue;
                }
                break;
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("\033[0;31m" + " !! input must be a Double value !!"+"\033[0m");
            }
        }
        return doubleVar;
    }

    /**
     * Prompts the user for an integer input and validates it.
     * @param sc The scanner object for reading user input.
     * @param userMessage The message to display to the user.
     * @return The validated integer input.
     */
    public static Integer getIntegerInput(Scanner sc, String userMessage)
    {
        int intVar;
        while (true)
        {
            System.out.println(userMessage);
            // using try catch to detect non-integer value
            try
            {
                intVar = Integer.parseInt(sc.nextLine().trim());
                if (intVar < 0)
                {
                    System.out.println("\033[0;31m" + " !! input must be a positive value !!"+"\033[0m");
                    continue;
                }
                break;
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("\033[0;31m" + " !! input must be a Integer value !!" +"\033[0m");
            }
        }
        return intVar;
    }

    /**
     * Prompts the user for a string input and validates it.
     * @param sc The scanner object for reading user input.
     * @param userMessage The message to display to the user.
     * @return The validated string input.
     */
    public static String getStringInput(Scanner sc, String userMessage)
    {
        String strVar;
        while (true)
        {
            System.out.println(userMessage);
            strVar = sc.nextLine().trim();
            // using self defined method checkString to make sure input is a string
            if (!checkString(strVar))
            {
                System.out.println("\033[0;31m" +" !! Input may only contain alphabetical values and whitespaces " +
                        "!!"+"\033[0m");
                continue;
            }
            break;

        }
        return strVar;
    }

    /**
     * Prompts the user for a date input and validates it.
     * @param sc The scanner object for reading user input.
     * @param dateFormatter The formatter for processing date inputs.
     * @return The validated date.
     */
    public static LocalDate getValidDate(Scanner sc, DateTimeFormatter dateFormatter)
    {
        LocalDate date;
        while (true)
        {
            System.out.println("Enter a date in the format (yyyy-MM-dd): ");
            String inputDate = sc.nextLine().trim();
            // using try except to validate date by printing error message if dateFormatter received invalid
            // value format to convert
            try
            {
                date = LocalDate.parse(inputDate, dateFormatter);
            }
            catch (DateTimeParseException e)
            {
                System.out.println("\033[0;31m" + " !! Invalid Date format. !!"+"\033[0m");
                continue;
            }
            break;
        }
        return date;
    }

    /**
     * Finds the index of an item number in the list.
     * @param itNum The item number to find.
     * @param itemNumbers The list of item numbers.
     * @return The index of the item number, or -1 if not found.
     */
    public static int indexOf(int itNum, ArrayList<Integer> itemNumbers)
    {
        for (int i = 0; i < itemNumbers.size(); i += 1)
        {
            if (itemNumbers.get(i) == itNum)
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * This main method interacts with the user and displays the menu to the user
     * It then calls the appropriate functions to perform the operation user wants to do
     */
    public static void main(String[] args)
    {
        Scanner sc = new Scanner (System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.print("\033[0;33m" +"\nWelcome to the Music Store VIBE-AWAY"+"\033[0m");
        while (true)
        {
            System.out.println("\nHit enter for menu..");
            String input = sc.nextLine();
            if (input.isEmpty())
            {
                // displaying the main menu to the user
                System.out.println("=======================================================");
                System.out.println("""
                        1. Add a new customer.
                        2. Display a list of all customers.
                        3. Add a new record to the store inventory.
                        4. Add a new t-shirt to the store inventory.
                        5. Display a list of all items and their current stock levels.
                        6. Update the stock level of an item in the inventory
                        7. Enter and store the details of a new customer order. The order must belong to
                        an existing store customer, and must be for an item that is in stock.
                        8. Output a list of all orders placed in the current month.
                        9. Exit the program.""");
                System.out.print("=======================================================\n");
                int choice = getIntegerInput(sc, "Enter choice:");
                // checking the correct number corresponding to the choice
                if (choice == 1)
                {
                    String userMessage = "Enter customer Name (alphabets and spaces allowed)";
                    String customerName = getStringInput(sc, userMessage);
                    Customer.addCustomer(customerName);
                }

                else if (choice == 2)
                {
                    if (Customer.customers.size() == 0)
                    {
                        System.out.println("\033[0;31m" + " !! There are no customers added to the music store !!"+
                                "\033[0m");
                        continue;
                    }
                    System.out.println("These are the following customers :");
                    for (Customer i: Customer.customers)
                    {
                        System.out.println(i);
                    }
                }

                else if (choice == 3 || choice == 4)
                {
                    StockItems.addItem(sc,choice);
                }

                else if (choice == 5)
                {
                    if (StockItems.items.size()==0)
                    {
                        System.out.println("\033[0;31m" + " !! There are currently no items in the inventory !!" +
                                "\033[0m");
                        continue;
                    }
                    System.out.println("These are the following items -:");
                    for(Stockable i: StockItems.items)
                    {
                        System.out.println(i);
                    }
                }

                else if (choice == 6)
                {
                    if (StockItems.items.size()==0)
                    {
                        System.out.println("\033[0;31m" + " !! There are currently no items in the inventory !!" +
                                "\033[0m");
                        continue;
                    }
                    String userMessage = "Enter Item number to edit";
                    int itNum = getIntegerInput(sc, userMessage);
                    if (!notInclusive(itNum,StockItems.itemNumbers))
                    {
                        int index = indexOf(itNum, StockItems.itemNumbers);
                        updateQuantities(StockItems.items,sc, index);
                    }
                    else
                    {
                        System.out.println("\033[0;31m" + " !! Item number not found in the inventory !!"+"\033[0m");
                    }
                }

                else if (choice == 7)
                {
                    // getting user inputs to call the cashier function to take order from the order class
                    if (Customer.customers.size()==0 || StockItems.items.size()==0)
                    {
                        System.out.println("\033[0;31m"+" !! Make sure there are items and customers added to the store" +
                                " !! \033[0m");
                        continue;
                    }

                    System.out.println("These are the following customers :");
                    for (Customer i: Customer.customers)
                    {
                        System.out.println(i.getCUSTOMER_NUMBER()+". "+i.getCUSTOMER_NAME());
                    }

                    String userMessage = "Enter customer id of customer placing the order from the customers above";
                    int custId;
                    while (true)
                    {
                        custId = getIntegerInput(sc, userMessage);

                        if (notInclusive(custId, Customer.customerNumbers))
                        {
                            System.out.println("\033[0;31m"+ "!! Customer Doesn't exist !!"+"\033[0m");
                            continue;
                        }
                        break;
                    }
                    int purchaseTotal = 0;
                    Orders.TotalOrders += 1;
                    int orderId = Orders.TotalOrders;
                    ArrayList<ArrayList<Integer>> shoppingList = new ArrayList<>();
                    Orders.cashier(sc, shoppingList, orderId, purchaseTotal, custId, dateFormatter);
                }

                else if (choice == 8)
                {
                    // checking if the date corresponds to the current month
                    int flag = 0;
                    LocalDate currentDate = LocalDate.now();
                    Month currentMonth = currentDate.getMonth();
                    int currentYear = currentDate.getYear();
                    for (int i = 0; i<Orders.orderDates.size(); i+=1)
                    {
                        Month month = Orders.orderDates.get(i).getMonth();
                        int year = Orders.orderDates.get(i).getYear();
                        if ((currentMonth.equals(month)) && (currentYear == year))
                        {
                            System.out.println(Orders.purchases.get(i));
                            flag = 1;
                        }
                    }
                    if (flag == 0)
                    {
                        System.out.println("No items bought this month!");
                    }
                }

                else if (choice == 9)
                {
                    System.out.println("Exiting the Music Store...");
                    break;
                }

                else
                {
                    System.out.println("\033[0;31m"+ " !! Choice not in options !!" + "\033[0m");
                }
            }
            else
            {
                System.out.print("Just hit enter to go to menu...");
            }
        }
    }

    /**
     * Checks if an item number is not included in the list.
     * @param itemNumber The item number to check.
     * @param itemNumbers The list of item numbers.
     * @return true if the item number is not included, false otherwise.
     */
    public static boolean notInclusive(int itemNumber, ArrayList<Integer> itemNumbers)
    {
        for (int i : itemNumbers)
        {
            if (i == itemNumber)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This method Updates the stock quantities of items in the inventory.
     * @param items A list of items.
     * @param sc A scanner object for reading user input.
     * @param index The index of the item in items list that is to be updated.
     */
    private static void updateQuantities(ArrayList<Stockable> items, Scanner sc, int index)
    {
        String userMessage = """
                Enter choice
                1. Add item quantity
                2. Reduce item quantity
                3. Set item Quantity
                4. Cancel""";
        int choiceRec = getIntegerInput(sc,userMessage);
        // calling the appropriate method from the items class to add or remove stock based on user input
        if (choiceRec == 1)
        {
            userMessage = "Enter amount to add";
            int amount = getIntegerInput(sc,userMessage);;
            items.get(index).addStock(amount);
            System.out.println("\033[1;32m"+"Inventory Updated..."+ "\033[0m");
        }
        else if (choiceRec == 2)
        {
            userMessage = "Enter amount to remove";
            int amount = getIntegerInput(sc,userMessage);;
            items.get(index).removeStock(amount);
            System.out.println("\033[1;32m"+"Inventory Updated..."+ "\033[0m");
        }
        else if (choiceRec == 3)
        {
            userMessage = "Enter amount to set";
            int amount = getIntegerInput(sc,userMessage);
            items.get(index).setStock(amount);
            System.out.println("\033[1;32m"+"Inventory Updated..."+ "\033[0m");
        }
        else if (choiceRec == 4)
        {
            System.out.println("\033[1;32m"+"item quantities not updated..."+ "\033[0m");
        }
        else
        {
            System.out.println("\033[0;31m"+ " !! Choice not in option... !!" + "\033[0m");
        }
    }
}