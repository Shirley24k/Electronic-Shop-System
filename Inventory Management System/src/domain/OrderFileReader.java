package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OrderFileReader implements IFileReader<Order>{

    public ArrayList<Order> readFile(String fileName)
	{
        ArrayList<Order> orderList = new ArrayList<Order>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            
            while ((line = br.readLine()) != null) //keep repeating until the line of text file is null
            {
                String[] orderInfo = line.split("\\|");
                
                String orderID = orderInfo[0];
                String userID = orderInfo[1];
                String phone = orderInfo[2];
                String address = orderInfo[3];
                String status = orderInfo[4];
                String referenceNum = orderInfo[5];

                //Assign ordered item to orderedItems arraylist
                ArrayList<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
                
                for (int i = 6; i < orderInfo.length; i++) {
                    String[] itemData = orderInfo[i].split(":");
                    String itemID = itemData[0];
                    int quantity = Integer.parseInt(itemData[1]);
                    // Create an item object and add it to the list of items
                    OrderedItem item = new OrderedItem(itemID, quantity);
                    orderedItems.add(item);
                }
                Order anOrder = new Order(orderID, userID, phone, address, status, referenceNum, orderedItems);
                orderList.add(anOrder);
                
            }
            
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return orderList;
	}
}
