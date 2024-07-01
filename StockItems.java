import java.util.ArrayList;
import java.util.Scanner;

/**
 * Abstract Class Name: StockItems
 * Purpose: This abstract class implements the Stockable interface and provides a base implementation for items that
 * can be stocked.
 * It manages the common properties and behaviors of stockable items such as item number, artist name, price, and
 * stock amount.
 * Author: Arnav Singh Sethi
 * Version: ver1.5.0
 */
abstract public class StockItems implements Stockable
{
    protected final String ARTIST_NAME;
    protected final int ITEM_NUMBER;
    protected static ArrayList<Stockable> items = new ArrayList<>();
    protected static ArrayList<Integer> itemNumbers = new ArrayList<>();
    protected final String ITEM_TYPE;
    protected Double price;
    protected int stockAmount;

    /**
     * Constructs a new StockItems object with the specified details.
     * @param ITEM_NUMBER the unique identifier for the item.
     * @param price the price of the item.
     * @param ARTIST_NAME the name of the artist associated with the item.
     * @param stockAmount the initial stock amount of the item.
     * @param itemType the type of the item.
     */
    public StockItems(int ITEM_NUMBER, Double price, String ARTIST_NAME, int stockAmount, String itemType)
    {
        itemNumbers.add(ITEM_NUMBER);
        this.ITEM_NUMBER = ITEM_NUMBER;
        this.ARTIST_NAME = ARTIST_NAME;
        this.stockAmount = stockAmount;
        this.price = price;
        this.ITEM_TYPE = itemType;
    }

    /**
     * Adds a new item to the store's inventory.
     * @param sc The scanner object for reading user input.
     * @param choice The choice indicating the type of item (record or t-shirt) to be added.
     */
    protected static void addItem(Scanner sc, int choice)
    {
        String userMessage = "Enter item number";
        while (true)
        {
            int itemNumber = MusicStore.getIntegerInput(sc, userMessage);
            // Check if item number is unique
            if (MusicStore.notInclusive(itemNumber, StockItems.itemNumbers))
            {
                userMessage = "Enter price (positive decimal)";
                Double price = MusicStore.getDoubleInput(sc, userMessage);
                userMessage = "Enter artist name (alphabets and whitespaces allowed)";
                String artistName = MusicStore.getStringInput(sc, userMessage);
                userMessage = "Enter in Stock amount (positive integer)";
                int stockAmount = MusicStore.getIntegerInput(sc, userMessage);
                if (choice == 4)
                {
                    while (true)
                    {
                        userMessage = """
                        Enter a number to choose the size of the Tshirt
                        1. SMALL
                        2. MEDIUM
                        3. LARGE
                        4. XLARGE
                        """;
                        int sizeChoice = MusicStore.getIntegerInput(sc, userMessage);
                        String[] sizes = {"SMALL", "MEDIUM", "LARGE", "XLARGE"};
                        // Continue the loop if invalid size choice
                        if (!(sizeChoice>=1 && sizeChoice<=4))
                        {
                            System.out.println("\033[0;31m" + " !! Out of range choice for tshirt size !!" +
                                    "\033[0m");
                            continue;
                        }
                        TShirt tShirtObject = new TShirt(itemNumber, price, artistName, stockAmount,
                                TShirtSize.valueOf(sizes[sizeChoice-1]));
                        break;
                    }
                }
                else
                {
                    // Create a new record object
                    userMessage = "Enter the Album Name of the Record";
                    String albumName = MusicStore.getStringInput(sc, userMessage);
                    Record record = new Record(itemNumber, price, artistName, stockAmount, albumName);
                }
                System.out.println("\033[1;32m"+"Item added to inventory..."+"\033[0m");
                break;
            }
            else
            {
                System.out.println("\033[0;31m" + " !! There is already an item with that item number !!" + "\033[0m");
            }
        }
    }

    /**
     * Adds the specified amount of stock to the item.
     *
     * @param amount the amount of stock to add.
     */
    public void addStock(int amount)
    {
        this.stockAmount = stockAmount + amount;
    }

    /**
     * Retrieves the name of the artist associated with the item.
     *
     * @return the artist name as a String.
     */
    public String getARTIST_NAME()
    {
        return ARTIST_NAME;
    }

    /**
     * Retrieves the item number of the stockable item.
     *
     * @return the item number.
     */
    public int getITEM_NUMBER()
    {
        return ITEM_NUMBER;
    }

    /**
     * Retrieves a list of item numbers of all stockable items.
     *
     * @return an ArrayList of item numbers.
     */
    public static ArrayList<Integer> getItemNumbers()
    {
        return itemNumbers;
    }

    /**
     * Retrieves a list of all stockable items.
     *
     * @return an ArrayList of Stockable items.
     */
    public static ArrayList<Stockable> getItems()
    {
        return items;
    }

    /**
     * Retrieves the type of the stockable item.
     *
     * @return the item type as a String.
     */
    public String getITEM_TYPE()
    {
        return ITEM_TYPE;
    }

    /**
     * Retrieves the price of the stockable item.
     *
     * @return the price of the item as a Double.
     */
    public Double getPrice()
    {
        return price;
    }

    /**
     * Retrieves the current stock amount of the stockable item.
     *
     * @return the stock amount as an int.
     */
    public int getStockAmount()
    {
        return stockAmount;
    }

    /**
     * Removes the specified amount of stock from the item.
     *
     * @param amount the amount of stock to remove.
     */
    public void removeStock(int amount)
    {
        if (stockAmount-amount >= 0)
        {
            this.stockAmount = stockAmount - amount;
        }
        else
        {
            // Set stock to 0 if removal amount exceeds current stock
            System.out.println("not enough quantity to remove in stock. Setting stock to 0");
            setStock(0);
        }
    }

    /**
     * Sets the list of item numbers for all stockable items.
     *
     * @param itemNumbers an ArrayList of item numbers.
     */
    public static void setItemNumbers(ArrayList<Integer> itemNumbers)
    {
        int flag = 0;
        for (int itemNum : itemNumbers)
        {
            if (!MusicStore.notInclusive(itemNum,itemNumbers))
            {
                // Exit loop if duplicate item number is found
                System.out.println("There can't be another item with the same itemNumber");
                flag=1;
                break;
            }
        }
        if (flag == 0)
        {
            StockItems.itemNumbers = itemNumbers;
        }
    }

    /**
     * Sets the list of all stockable items.
     *
     * @param items an ArrayList of Stockable items.
     */
    public static void setItems(ArrayList<Stockable> items)
    {
        int flag = 0;
        for (Stockable item : items)
        {
            if (!MusicStore.notInclusive(item.getITEM_NUMBER(),itemNumbers))
            {
                System.out.println("There can't be another item with the same item number");
                flag=1;
                break;
            }
        }
        if (flag == 0)
        {
            // Set item numbers if no duplicates
            StockItems.items = items;
        }
    }

    /**
     * Sets the price of the stockable item.
     *
     * @param price the new price.
     */
    public void setPrice(Double price)
    {
        if (price>=0)
        {
            this.price = price;
        }
        else
        {
            System.out.println("Price can't be negative!");
        }
    }

    /**
     * Sets the stock amount of the item.
     *
     * @param stockAmount the new stock amount.
     */
    public void setStock(int stockAmount)
    {
        if (stockAmount >= 0)
        {
            this.stockAmount = stockAmount;
        }
        else
        {
            System.out.println("Price can't be negative!");
        }
    }

    /**
     * Returns a string representation of the stockable item, including its type, item number, artist name, price, and
     * stock amount.
     * @return a string representation of the stockable item.
     */
    @Override
    public String toString()
    {
        return "Item Type: "+ ITEM_TYPE +"\nItem Number "+ITEM_NUMBER + ".\nArtist Name : "+ ARTIST_NAME + "\nprice : "
                + price + "\nstock Amount : " + stockAmount + "\n";
    }
}
