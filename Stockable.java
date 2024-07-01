/**
 * Interface Name: Stockable
 * Purpose: This interface represents the contract for stockable items in a store.
 * It defines methods for managing stock levels and retrieving item information.
 * Author: Arnav Singh Sethi
 * Version: ver1.1.0
 */
public interface Stockable
{
    /**
     * Adds the specified amount of stock to the item.
     *
     * @param amount the amount of stock to add.
     */
    void addStock(int amount);

    /**
     * Retrieves the item number of the stockable item.
     *
     * @return the item number.
     */
    int getITEM_NUMBER();

    /**
     * Retrieves the type of the stockable item.
     *
     * @return the item type as a String.
     */
    String getITEM_TYPE();

    /**
     * Retrieves the price of the stockable item.
     *
     * @return the price of the item as a Double.
     */
    Double getPrice();

    /**
     * Retrieves the current stock amount of the stockable item.
     *
     * @return the stock amount as an int.
     */
    int getStockAmount();

    /**
     * Removes the specified amount of stock from the item.
     *
     * @param amount the amount of stock to remove.
     */
    void removeStock(int amount);

    /**
     * Sets the stock amount of the item to the specified value.
     *
     * @param amount the new stock amount.
     */
    void setStock(int amount);
}

