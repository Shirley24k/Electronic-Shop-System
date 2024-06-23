package domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OrderFileWriter implements IFileWriter<Order> {

    public void writeFile(String fileName, ArrayList<Order> orderList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){

            for (Order order : orderList) {
                StringBuilder sb = new StringBuilder();
                sb.append(order.getOrderID()).append("|");
                sb.append(order.getUserID()).append("|");
                sb.append(order.getPhone()).append("|");
                sb.append(order.getAddress()).append("|");
                sb.append(order.getOrderStatus()).append("|");
                sb.append(order.getReferenceNum()).append("|");

                // Append ordered items
                ArrayList<OrderedItem> orderedItems = order.getOrderItemList();
                for (int i = 0; i < orderedItems.size(); i++) {
                    OrderedItem item = orderedItems.get(i);
                    sb.append(item.getItemID()).append(":").append(item.getQuantity());
                    if (i < orderedItems.size() - 1) {
                        sb.append("|");
                    }
                }

                bw.write(sb.toString());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

