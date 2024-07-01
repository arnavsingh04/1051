import java.util.ArrayList;

/**
 * Class Name : Tshirt
 * Purpose : This class represents a T-shirt item in the music store's inventory.
 * Author: Arnav Singh Sethi
 * Version: ver1.2.0
 */
public class TShirt extends StockItems
{
    private final TShirtSize SIZE;
    private static ArrayList<TShirt> tShirts = new ArrayList<>();

    /**
     * Constructor to create a new Tshirt object.
     * @param itemNumber The item number of the T-shirt.
     * @param price The price of the T-shirt.
     * @param artistName The name of the artist featured on the T-shirt.
     * @param stockAmount The stock amount of the T-shirt.
     * @param size The size of the T-shirt.
     */
    public TShirt(int itemNumber, Double price, String artistName, int stockAmount, TShirtSize size)
    {
        super(itemNumber, price, artistName, stockAmount,"Tshirt");
        this.SIZE = size;
        tShirts.add(this);
        items.add(this);
    }

    /**
     * Gets the size of the T-shirt.
     * @return The size of the T-shirt.
     */
    public TShirtSize getSIZE()
    {
        return SIZE;
    }

    /**
     * Gets the list of all T-shirts.
     * @return The list of all T-shirts.
     */
    public static ArrayList<TShirt> getShirts()
    {
        return tShirts;
    }

    /**
     * Sets the list of all T-shirts.
     * @param tShirts The list of T-shirts to be set.
     */
    public static void setTShirts(ArrayList<TShirt> tShirts)
    {
        int flag = 0;
        for (TShirt tShirt : tShirts)
        {
            if (!MusicStore.notInclusive(tShirt.getITEM_NUMBER(),itemNumbers))
            {
                System.out.println("There can't be another tshirt with the same item number");
                flag = 1;
            }
        }
        if (flag == 0)
        {
            TShirt.tShirts = tShirts;
        }
    }

    /**
     * Provides a string representation of the T-shirt.
     * @return A string representation of the T-shirt, including the size.
     */
    @Override
    public String toString()
    {
        return "\033[0;36m"+SIZE.toString()+ " "+ ITEM_TYPE + " (Item No. "+ITEM_NUMBER + ") - "+ "designed by : "+ ARTIST_NAME +
                " - (" + price + "$ each, stock Amt : " + stockAmount+ ")"+ "\033[0m";
    }
}
