package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ItemFileReader implements IFileReader<Item>{

    public ArrayList<Item> readFile(String fileName) {
        
        ArrayList<Item> itemList = new ArrayList<Item>();
        // Read file and populate itemList
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] itemInfo = line.split("\\|");
                if (itemInfo.length == 5) { // Ensure correct number of fields
                    String itemID = itemInfo[0].trim();
                    String itemName = itemInfo[1].trim();
                    String itemType = itemInfo[2].trim();
                    double itemPrice = Double.parseDouble(itemInfo[3].trim());
                    int itemQty = Integer.parseInt(itemInfo[4].trim());
                
	                // Create ClientUser object and add to userList
	                Item item = new Item(itemID, itemName, itemType, itemPrice, itemQty);
	                itemList.add(item);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        } 
        
        return itemList;
    }
}
