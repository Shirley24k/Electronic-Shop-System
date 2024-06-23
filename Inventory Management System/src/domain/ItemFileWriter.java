package domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ItemFileWriter implements IFileWriter<Item>{
    public void writeFile(String fileName, ArrayList<Item> itemList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Item item : itemList) {
                // Write user information to the file
                bw.write(item.getItemID() + "|" + item.getName() + "|" + item.getType() + "|" + item.getPrice() + "|" + item.getQuantity());
                bw.newLine(); // Add newline character after each user
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
