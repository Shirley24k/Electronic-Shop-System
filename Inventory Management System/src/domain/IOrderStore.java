package domain;
import java.util.ArrayList;

public interface IOrderStore {
    
	public void loadOrderListFromFile();

	public ArrayList<Order> getOrderList();
	
	public ArrayList <Order> getUserOrders(User user);

    public void saveOrderListToFile();
    
    public String getLastOrderID();
    
    public String generateOrderID();

    public void createOrder(Order newOrder);
    
    public void updateOrder(Order order);
    
    public ArrayList<Order> getPendingOrders(ClientUser client);
    
    public ArrayList <Order> getPaidOrders(User user);
    
    public void displayAnOrder(Order order, IItemStore itemList);
    
    public void displayOrderList(ArrayList <Order> orders, IItemStore itemList);
}
