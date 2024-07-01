import java.util.ArrayList;

/**
 * Class Name : Record
 * Purpose : This class represents a record item in the music store's inventory.
 * Author: Arnav Singh Sethi
 * Version: ver1.2.0
 */
public class Record extends StockItems
{
    private final String ALBUM_NAME;
    private static ArrayList<Record> records = new ArrayList<>();

    /**
     * Constructor to create a new Record object.
     * @param itemNumber The item number of the record.
     * @param price The price of the record.
     * @param artistName The name of the artist of the record.
     * @param stockAmount The stock amount of the record.
     * @param albumName The name of the album.
     */
    public Record(int itemNumber, Double price, String artistName, int stockAmount, String albumName)
    {
        super(itemNumber, price, artistName, stockAmount,"Record");
        this.ALBUM_NAME = albumName;
        records.add(this);
        items.add(this);
    }

    /**
     * Gets the album name of the record.
     * @return The album name.
     */
    public String getALBUM_NAME()
    {
        return ALBUM_NAME;
    }

    /**
     * Gets the list of all records.
     * @return The list of all records.
     */
    public static ArrayList<Record> getRecords()
    {
        return records;
    }

    /**
     * Sets the list of all records.
     * @param records The list of records to be set.
     */
    public static void setRecords(ArrayList<Record> records)
    {
        int flag = 0;
        for (Record record : records)
        {
            if (!MusicStore.notInclusive(record.getITEM_NUMBER(),itemNumbers))
            {
                System.out.println("There can't be another record with the same item number");
                flag = 1;
            }
        }
        if (flag == 0)
        {
            Record.records = records;
        }
    }

    /**
     * Provides a string representation of the record.
     * @return A string representation of the record, including the album name.
     */
    @Override
    public String toString()
    {
        return "\033[0;36m"+ITEM_TYPE +" (Item No. "+ITEM_NUMBER + ") - "+ALBUM_NAME+" by "+ ARTIST_NAME + "- ("+ price + "$ each"
                + ", stock Amt : " + stockAmount+")"+ "\033[0m";
    }
}
