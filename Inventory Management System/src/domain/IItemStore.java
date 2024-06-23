package domain;

import java.util.ArrayList;

public interface IItemStore {

	public void loadItemListFromFile();
	
    public ArrayList<Item> getItemList();
    
    public void saveItemListToFile();
    
    public String getLastItemID();

    public String generateItemID();

    public void createItem(Item newItem);
    
    public void updateItem(Item item);

    public Item searchItem(String itemID);

    public void deleteItem(Item item);
    
    public void displayItem(Item item);
    
    public void displayItemList();
}
