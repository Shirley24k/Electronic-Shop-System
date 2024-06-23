package domain;

public class OrderedItem {
    private String itemID;
    private int quantity;

    public OrderedItem(String itemID, int quantity){
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public String getItemID(){
        return itemID;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
}
