package domain;
import java.util.ArrayList;

public class OrderList implements IOrderStore{
    private ArrayList<Order> orderList;
    private IFileReader <Order> orderReader;
    private IFileWriter <Order> orderWriter;

    public OrderList(IFileReader <Order> orderReader, IFileWriter <Order> orderWriter){
        orderList = new ArrayList<Order>();
        this.orderReader = orderReader;
        this.orderWriter = orderWriter;
        loadOrderListFromFile();
    }

    public void loadOrderListFromFile() {
        orderList = orderReader.readFile("order.txt");
    }
    
    public ArrayList<Order> getOrderList(){
    	return orderList;
    }
    
    public ArrayList<Order> getUserOrders(User user) {
    	ArrayList <Order> userOrders = new ArrayList <Order>();
		if (user instanceof Administrator){
			userOrders = orderList;
		}
		else{
			ClientUser clientuser = (ClientUser) user;
			for (Order order : orderList){
				if (order.getUserID().equals(clientuser.getUserID()))
					userOrders.add(order);
			}
		}
		return userOrders;
    }
    
    
    
    public void saveOrderListToFile() {
        orderWriter.writeFile("order.txt", orderList);
    }
    
    public String getLastOrderID(){
        int size = orderList.size();
        if (size > 0) {
        	return orderList.get(size-1).getOrderID();
        }
        else
        	return "O0"; //Default ID if no order
    }
    
    public String generateOrderID() {
        int lastOrderID = Integer.parseInt(getLastOrderID().substring(1));
        lastOrderID++; // Increment the counter
        return "O" + lastOrderID;
    }

    public void createOrder(Order newOrder){
        orderList.add(newOrder);
        saveOrderListToFile();
    }
    
    public void updateOrder(Order order) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderID().equals(order.getOrderID())) {
                orderList.set(i, order);
                saveOrderListToFile();
                break;
            }
        }
    }
    
    //Retrieve pending order for particular user
    public ArrayList<Order> getPendingOrders(ClientUser client){
        ArrayList<Order> pendingOrders = new ArrayList<Order>();
        String clientUserID = client.getUserID();
        int size = orderList.size();
        for (int i = 0; i < size; i++){
            String orderUserID = orderList.get(i).getUserID();
            String orderStatus = orderList.get(i).getOrderStatus();
            if(orderUserID.equals(clientUserID) && orderStatus.equals("PENDING")){
                pendingOrders.add(orderList.get(i));
            }
        }
        return pendingOrders;
    }
    
    public ArrayList <Order> getPaidOrders(User user){
    	ArrayList <Order> paidOrders = new ArrayList <>();
    	
    	if (user instanceof Administrator) {
    		for (Order order : orderList) {
    			String orderStatus = order.getOrderStatus();
    			if (!orderStatus.equals("PENDING")) {
    				paidOrders.add(order);
    			}
    		}
    	}
    	else {
    		ClientUser clientUser = (ClientUser) user;
			String clientUserID = clientUser.getUserID();
			for (Order order : orderList) {
				String orderUserID = order.getUserID();
                String orderStatus = order.getOrderStatus();
                if(orderUserID.equals(clientUserID) && !orderStatus.equals("PENDING")){
                    paidOrders.add(order);
                }
			}
    	}
    	
    	return paidOrders;
    }

    public void displayAnOrder(Order order, IItemStore itemList){
        System.out.printf("Order ID: %s\n", order.getOrderID());
        System.out.printf("User ID: %s\n", order.getUserID());
        System.out.printf("Phone: %s\n", order.getPhone());
        System.out.printf("Address: %s\n", order.getAddress());
        System.out.printf("Order Status: %s\n", order.getOrderStatus());
        System.out.printf("Reference Number: %s\n", order.getReferenceNum());
        System.out.println("Order Items: ");
        ArrayList<OrderedItem> orderedItemsList = order.getOrderItemList();
        for(OrderedItem orderedItem : orderedItemsList){
            
            System.out.printf("Item ID: %s\n",orderedItem.getItemID());
            Item item = itemList.searchItem(orderedItem.getItemID());
            System.out.printf("Item Name: %s\n", item.getName());
            System.out.printf("Item Price: %s\n", item.getPrice());
            System.out.printf("Item Quantity: %s\n", orderedItem.getQuantity());
        }
        System.out.println();
        System.out.println();
    }
    
    public void displayOrderList(ArrayList <Order> orders, IItemStore itemList){
        for (Order order : orders){
            displayAnOrder(order,itemList);
        }
    }
}
