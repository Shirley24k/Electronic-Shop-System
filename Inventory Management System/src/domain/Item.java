package domain;

public class Item {
    private String itemID;
    private String name;
    private String type;
    private double price;
    private int quantity;

    public Item(String itemID, String name, String type, double price, int quantity)
    {
        this.itemID = itemID;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemID(){
        return itemID;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
