import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Class Name : Orders
 * Purpose : This class represents an order made by a customer in the music store.
 * Author: Arnav Singh Sethi
 * Version: ver1.3.0
 */
public class Orders
{
    private final LocalDate DATE_OF_PURCHASE;
    private final ArrayList<ArrayList<Integer>> ITEMS_BOUGHT;
    protected static ArrayList<LocalDate> orderDates = new ArrayList<>();
    private final int ORDER_ID;
    protected static ArrayList<Orders> purchases = new ArrayList<>();
    private final int PURCHASE_TOTAL;

    protected static int TotalOrders = 0;
    private final int customerId;

    /**
     * Constructor to create a new Orders object.
     * @param ORDERID The unique ID of the order.
     * @param itemsBought The list of items bought in the order.
     * @param dateOfPurchase The date of the purchase.
     * @param purchaseTotal The total amount of the purchase.
     */
    public Orders(int ORDERID, ArrayList<ArrayList<Integer>> itemsBought, LocalDate dateOfPurchase, int purchaseTotal,
                  int customerId)
    {
        this.ORDER_ID = ORDERID;
        this.ITEMS_BOUGHT = itemsBought;
        this.DATE_OF_PURCHASE = dateOfPurchase;
        this.PURCHASE_TOTAL = purchaseTotal;
        purchases.add(this);
        orderDates.add(dateOfPurchase);
        this.customerId = customerId;
    }

    /**
     * Handles the cashier operations for processing a purchase.
     * @param sc The scanner object for reading user input.
     * @param shoppingList The list of items being purchased.
     * @param orderId The ID of the order being processed.
     * @param purchaseTotal The total amount of the purchase.
     * @param customerId The customer ID of the customer making the purchase.
     * @param dateFormatter The formatter for processing date inputs.
     */
    protected static void cashier(Scanner sc, ArrayList<ArrayList<Integer>> shoppingList, int orderId, int purchaseTotal,
                               int customerId, DateTimeFormatter dateFormatter)
    {
        while (true)
        {
            String userMessage = """
                    1. Add item for purchase
                    2. Browse inventory
                    3. Remove item from cart
                    4. See current items in cart
                    5. End Purchase
                    6. Cancel Order""";
            int choice3 = MusicStore.getIntegerInput(sc, userMessage);
            int index = 0;

            if (choice3 == 1)
            {
                // getting user inputs to add item to cart
                int itemNum;
                userMessage = "Enter item number for purchase";
                while (true)
                {
                    itemNum = MusicStore.getIntegerInput(sc, userMessage);
                    if (MusicStore.notInclusive(itemNum, StockItems.itemNumbers))
                    {
                        System.out.println("\033[0;31m"+" !! Item not in inventory !!"+"\033[0m");
                        continue;
                    }
                    break;
                }

                for (int i = 0; i < StockItems.itemNumbers.size(); i += 1)
                {
                    if (StockItems.itemNumbers.get(i) == itemNum)
                    {
                        index = i;
                    }
                }

                userMessage = "Enter quantity for purchase";
                int quantity = MusicStore.getIntegerInput(sc, userMessage);
                if (quantity==0)
                {
                    System.out.println("\033[0;31m" + " !! Quantity must be more than 0 !!"+"\033[0m");
                    continue;
                }
                if (quantity > StockItems.items.get(index).getStockAmount())
                {
                    System.out.println("\033[0;31m" + " !! Not enough Quantity in Stock !!"+"\033[0m");
                    continue;
                }

                // updating quantity of items in  the objects
                int flag = 0;
                for (ArrayList<Integer> integers : shoppingList)
                {
                    if (integers.get(0) == itemNum)
                    {
                        System.out.println("quantity added to item in Cart");
                        purchaseTotal += quantity * StockItems.items.get(index).getPrice();
                        StockItems.items.get(index).removeStock(quantity);
                        integers.set(1, integers.get(1) + quantity);
                        flag = 1;
                        break;
                    }
                }

                if (flag == 1)
                {
                    continue;
                }

                ArrayList<Integer> subPurchaseList = new ArrayList<>();
                subPurchaseList.add(itemNum);
                subPurchaseList.add(quantity);
                if (StockItems.items.get(index).getITEM_TYPE().equals("Record"))
                {
                    subPurchaseList.add(1);
                }
                else
                {
                    subPurchaseList.add(2);
                }
                shoppingList.add(subPurchaseList);

                purchaseTotal += quantity * StockItems.items.get(index).getPrice();
                StockItems.items.get(index).removeStock(quantity);
                System.out.println("\033[1;32m"+"Item added to shopping list..."+"\033[0m");
            }
            else if (choice3 == 2)
            {
                for (Stockable i : StockItems.items)
                {
                    System.out.println(i);
                }
            }
            else if (choice3 == 3)
            {
                // getting user inputs for item number to remove
                if (shoppingList.size() != 0)
                {
                    userMessage = "Enter item number to remove";
                    int itemNoRemove = MusicStore.getIntegerInput(sc, userMessage);
                    int i = 0;
                    Iterator<ArrayList<Integer>> cartIterator = shoppingList.iterator();
                    int flag=0;
                    while (cartIterator.hasNext())
                    {
                        ArrayList<Integer> subPurchase = cartIterator.next();
                        if (subPurchase.get(0) == itemNoRemove)
                        {
                            flag = 1;
                            for (int j = 0; j < StockItems.itemNumbers.size(); j += 1)
                            {
                                if (StockItems.itemNumbers.get(j) == itemNoRemove)
                                {
                                    index = j;
                                    break;
                                }
                            }
                            userMessage = "Enter Quantity to remove";
                            int removeQuantity = MusicStore.getIntegerInput(sc, userMessage);
                            if (removeQuantity >= subPurchase.get(1))
                            {
                                cartIterator.remove();
                                StockItems.items.get(index).addStock(subPurchase.get(1));
                                purchaseTotal -= removeQuantity*StockItems.items.get(index).getPrice();
                                System.out.println("\033[1;32m" + "All quantity added to cart has been removed..." +
                                        "\033[0m");
                            }
                            else
                            {
                                shoppingList.get(i).set(1, shoppingList.get(i).get(1) - removeQuantity);
                                StockItems.items.get(index).addStock(removeQuantity);
                                System.out.println("\033[1;32m" + "Given Quantity of the item has been removed..." +
                                        "\033[0m");
                                purchaseTotal -= removeQuantity*StockItems.items.get(index).getPrice();
                            }
                            break;
                        }
                        else
                        {
                            i += 1;
                        }
                    }
                    if (flag==0)
                    {
                        System.out.println("\033[0;31m"+"item not found!!"+"\033[0m");
                    }
                }
                else
                {
                    System.out.println("\033[0;31m"+"There are no items in the cart to remove!!"+"\033[0m");
                }
            }
            else if (choice3 == 4)
            {
                // printing a formatted string of the cart
                String tempStr = "Item Number      Quantity\n";
                if (shoppingList.size()==0)
                {
                    System.out.println("\033[0;31m"+"No items in the cart!"+"\033[0m");
                    continue;
                }
                for (ArrayList<Integer> i : shoppingList)
                {
                    if (i.get(2)==1)
                    {
                        tempStr += "Record";
                    }
                    else
                    {
                        tempStr += "Tshirt";
                    }
                    tempStr += " "+i.get(0)+"           "+i.get(1)+"\n";
                }
                System.out.println(tempStr);
            }
            else if (choice3 == 5)
            {
                // creating the final order object
                if (!shoppingList.isEmpty())
                {
                    LocalDate date = MusicStore.getValidDate(sc, dateFormatter);
                    Orders newOrder = new Orders(orderId, shoppingList, date, purchaseTotal, customerId);
                    Customer.addCustomerPurchase(customerId, newOrder);
                    System.out.println("\033[1;32m"+"order has been placed successfully!!"+"\033[0m");
                }
                else
                {
                    System.out.println("\033[0;31m"+"!! Cart is Empty. No orders added. !!"+"\033[0m");
                }
                break;
            }
            else if (choice3 == 6)
            {
                // adding back the removed quantity from the objects
                for (ArrayList<Integer> i : shoppingList)
                {
                    int itemNumTemp = i.get(0);
                    int quantityTemp = i.get(1);
                    int indexTemp = MusicStore.indexOf(itemNumTemp,StockItems.itemNumbers);
                    StockItems.items.get(indexTemp).addStock(quantityTemp);
                }
                System.out.println("\033[1;32m"+"order cancelled..."+"\033[0m");
                break;
            }
            else
            {
                System.out.println("\033[0;31m"+" !! Invalid choice !!"+"\033[0m");
            }
        }
    }

    /**
     * Gets the date of the purchase.
     * @return The date of the purchase.
     */
    public LocalDate getDATE_OF_PURCHASE()
    {
        return DATE_OF_PURCHASE;
    }

    /**
     * Gets the list of items bought in the order.
     * @return The list of items bought.
     */
    public ArrayList<ArrayList<Integer>> getITEMS_BOUGHT()
    {
        return ITEMS_BOUGHT;
    }

    /**
     * Gets the list of all order dates.
     * @return The list of all order dates.
     */
    public static ArrayList<LocalDate> getOrderDates()
    {
        return orderDates;
    }

    /**
     * Gets the unique ID of the order.
     * @return The unique ID of the order.
     */
    public int getORDER_ID()
    {
        return ORDER_ID;
    }

    /**
     * Gets the list of all orders.
     * @return The list of all orders.
     */
    public static ArrayList<Orders> getPurchases()
    {
        return purchases;
    }

    /**
     * Gets the total amount of the purchase.
     * @return The total amount of the purchase.
     */
    public int getPURCHASE_TOTAL()
    {
        return PURCHASE_TOTAL;
    }

    /**
     * Gets the total number of orders.
     * @return The total number of orders.
     */
    public static int getTotalOrders()
    {
        return TotalOrders;
    }

    public static void setOrderDates(ArrayList<LocalDate> orderDates)
    {
        if (orderDates.size() == purchases.size())
        {
            Orders.orderDates = orderDates;
        }
        else
        {
            System.out.println("There must be one date for each order!");
        }
    }

    /**
     * Sets the list of all orders.
     * @param purchases The list of orders to be set.
     */
    public static void setPurchases(ArrayList<Orders> purchases)
    {
        int flag1 = 0;
        for (int i = 0; i < Orders.purchases.size(); i += 1)
        {
            for (int j = 0; j < Orders.purchases.size(); j += 1)
            {
                if (Orders.purchases.get(i).getORDER_ID() == Orders.purchases.get(j).getORDER_ID() && i != j)
                {
                    System.out.println("There can't be another order with the same order number");
                    flag1=1;
                    break;
                }
            }
            if (flag1 == 1)
            {
                break;
            }
        }
        if (flag1 == 0)
        {
            Orders.purchases = purchases;
        }
    }

    /**
     * Sets the total number of orders.
     * @param totalOrders The total number of orders to be set.
     */
    public static void setTotalOrders(int totalOrders)
    {
        if (totalOrders >= 0)
        {
            TotalOrders = totalOrders;
        }
    }

    /**
     * Provides a string representation of the order.
     * @return A string representation of the order, including the order ID, date of purchase, items bought, and total
     * purchase amount.
     */
    @Override
    public String toString()
    {
        // formatting to string to print a customers order details
        String tempStr = "Item Number      Quantity\n";
        for (ArrayList<Integer> i : ITEMS_BOUGHT)
        {
            if (i.get(2)==1)
            {
                tempStr += "Record";
            }
            else
            {
                tempStr += "Tshirt";
            }
            tempStr += " "+i.get(0)+"           "+i.get(1)+"\n";
        }
        return "\033[0;36m"+"order id : " + ORDER_ID + "\ncustomer id : "+ customerId +"\ndate of purchase : " +
                DATE_OF_PURCHASE.toString() + "\nitems bought : \n"+ tempStr + "total purchase amount : " +
                PURCHASE_TOTAL + "\n"+ "\033[0m";
    }
}
