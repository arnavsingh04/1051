import java.util.ArrayList;

/**
 * Class Name : Customer
 * Purpose : This class represents a customer in the music store.
 * Author: Arnav Singh Sethi
 * Version: ver1.1.0
 */
public class Customer
{
    private final String CUSTOMER_NAME;
    private final int CUSTOMER_NUMBER;
    protected static ArrayList<Integer> customerNumbers = new ArrayList<>();
    protected static ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Orders> PURCHASES;
    protected static int totalCustomers = 0;

    /**
     * Constructor to create a new Customer object with a list of purchases.
     * @param customerNumber The unique number assigned to the customer.
     * @param customerName The name of the customer.
     * @param purchases The list of purchases made by the customer.
     */
    public Customer(int customerNumber, String customerName, ArrayList<Orders> purchases)
    {
        customerNumbers.add(customerNumber);
        this.CUSTOMER_NUMBER = customerNumber;
        this.CUSTOMER_NAME = customerName;
        this.PURCHASES = purchases;
        customers.add(this);
    }

    /**
     * Constructor to create a new Customer object without a list of purchases.
     * @param customerNumber The unique number assigned to the customer.
     * @param customerName The name of the customer.
     */
    public Customer(int customerNumber, String customerName)
    {
        customerNumbers.add(customerNumber);
        this.CUSTOMER_NUMBER = customerNumber;
        this.CUSTOMER_NAME = customerName;
        this.PURCHASES = new ArrayList<>();
        customers.add(this);
    }

    /**
     * Adds a new customer to the store.
     * @param customerName The name of the customer to be added.
     */
    protected static void addCustomer(String customerName)
    {
        // creating new customer object
        Customer.totalCustomers +=1;
        int customerNumber = Customer.totalCustomers;
        Customer nameCustomer = new Customer(customerNumber, customerName);
        System.out.println(nameCustomer+"\033[0;36m"+" has been added..."+"\033[0m");
    }

    /**
     * Adds a new purchase order for an existing customer.
     * @param customerId The customer ID of the customer making the purchase.
     * @param newOrder The new order to be added for the customer.
     */
    protected static void addCustomerPurchase(int customerId, Orders newOrder)
    {
        for (int i = 0; i < Customer.customerNumbers.size(); i += 1)
        {
            if (Customer.customerNumbers.get(i) == customerId)
            {
                Customer.customers.get(i).addPurchase(newOrder);
            }
        }
    }

    /**
     * Adds a purchase to the customer's list of purchases.
     * @param purchase The purchase to be added.
     */
    protected void addPurchase(Orders purchase)
    {
        this.PURCHASES.add(purchase);
    }

    /**
     * Gets the customer's name.
     * @return The name of the customer.
     */
    public String getCUSTOMER_NAME()
    {
        return CUSTOMER_NAME;
    }

    /**
     * Gets the customer's number.
     * @return The unique number assigned to the customer.
     */
    public int getCUSTOMER_NUMBER()
    {
        return CUSTOMER_NUMBER;
    }

    /**
     * Gets the list of all customer numbers.
     * @return The list of all customer numbers.
     */
    public static ArrayList<Integer> getCustomerNumbers()
    {
        return customerNumbers;
    }

    /**
     * Gets the list of all customers.
     * @return The list of all customers.
     */
    public static ArrayList<Customer> getCustomers()
    {
        return customers;
    }

    /**
     * Gets the list of purchases made by the customer.
     * @return The list of purchases made by the customer.
     */
    public ArrayList<Orders> getPURCHASES()
    {
        return PURCHASES;
    }

    /**
     * Gets the total number of customers.
     * @return The total number of customers.
     */
    public static int getTotalCustomers()
    {
        return totalCustomers;
    }

    /**
     * Sets the list of all customer numbers.
     * @param customerNumbers The list of customer numbers to be set.
     */
    public static void setCustomerNumbers(ArrayList<Integer> customerNumbers)
    {
        int flag = 0;
        for (int i : customerNumbers)
        {
            for (int j : customerNumbers)
            {
                // setting flag to 1 if two different indexes have the same customer number
                if ((i == j && customerNumbers.indexOf(i) != customerNumbers.indexOf(j)) || j < 0)
                {
                    flag = 1;
                    break;
                }
            }
        }
        if (flag==0)
        {
            Customer.customerNumbers = customerNumbers;
        }
        else
        {
            System.out.println("Make sure no two customers have the same customer number and all customer " +
                    "numbers are +ve");
        }
    }

    /**
     * Sets the list of all customers.
     * @param customers The list of customers to be set.
     */
    public static void setCustomers(ArrayList<Customer> customers)
    {
        int flag = 0;
        for (Customer k : customers)
        {
            for (Customer l : customers)
            {
                // setting flag to 1 if two different indexes have the same customer number
                if ((k.CUSTOMER_NUMBER == l.CUSTOMER_NUMBER && customers.indexOf(k) != customers.indexOf(l)))
                {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1)
            {
                break;
            }
        }
        if (flag == 1)
        {
            System.out.println("There can't be two customers with the same customer number");
        }
        else
        {
            Customer.customers = customers;
        }
    }

    /**
     * Sets the total number of customers.
     * @param totalCustomers The total number of customers to be set.
     */
    public static void setTotalCustomers(int totalCustomers)
    {
        if (totalCustomers >= 0 && customers.size() == totalCustomers)
        {
            Customer.totalCustomers = totalCustomers;
        }
        else
        {
            System.out.println("total customers must be equal to the no. of customers that are created");
        }
    }

    /**
     * Provides a string representation of the customer.
     * @return A string representation of the customer, including the customer number and name.
     */
    @Override
    public String toString()
    {
        return "\033[0;36m"+"Customer name: "+CUSTOMER_NAME + " [Customer id: " + CUSTOMER_NUMBER + "]"+ "\033[0m";
    }
}
