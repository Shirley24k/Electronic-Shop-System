package domain;
import java.util.ArrayList;

public class ItemList implements IItemStore{

    private ArrayList<Item> itemList;
    private IFileReader <Item> itemReader;
    private IFileWriter <Item> itemWriter;

    public ItemList(IFileReader <Item> itemReader, IFileWriter <Item> itemWriter){
        itemList = new ArrayList<Item>();
        this.itemReader = itemReader;
        this.itemWriter = itemWriter;
        loadItemListFromFile();
    }

    public void loadItemListFromFile() {
        itemList = itemReader.readFile("item.txt");
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void saveItemListToFile() {
        itemWriter.writeFile("item.txt", itemList);
    }

    public String getLastItemID(){
        int size = itemList.size();
        if (size > 0) {
        	return itemList.get(size-1).getItemID();
        }
        else
        	return "I0"; //Default ID if no items
    }

    public String generateItemID() {
        int lastItemID = Integer.parseInt(getLastItemID().substring(1));
        lastItemID++; // Increment the counter
        return "I" + lastItemID;
    }

    public void createItem(Item newItem){
        itemList.add(newItem);
        saveItemListToFile();
    }

    //Search item using itemID
    public Item searchItem(String itemID){
        Item theSearchItem = null;
        for(Item item:itemList)
        {
            if (item.getItemID().equals(itemID)) {
            	theSearchItem = item;
            	break;
            }  
        }
        return theSearchItem;
    }

    //Update Item Details
    public void updateItem(Item item) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getItemID().equals(item.getItemID())) {
                itemList.set(i, item);
                saveItemListToFile();
                break;
            }
        }
    }

    //Delete Item
    public void deleteItem(Item item){
        itemList.remove(item);
        saveItemListToFile();
    }
    
    public void displayItem(Item item){
        System.out.printf("Item ID: %s\n", item.getItemID());
        System.out.printf("Item name: %s\n", item.getName());
        System.out.printf("Product type: %s\n", item.getType());
        System.out.printf("Item price: %s\n", item.getPrice());
        System.out.printf("Item quantity: %s\n", item.getQuantity());
        System.out.println();
    }
    
    public void displayItemList() {
        for(Item item : itemList){
            displayItem(item);
            System.out.println();
        }
    }
}
