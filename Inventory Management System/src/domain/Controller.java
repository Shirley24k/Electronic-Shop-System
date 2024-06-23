package domain;

import java.util.ArrayList;

public class Controller {

    private IUserStore userlist;
    private IOrderStore orderlist;
    private IItemStore itemlist;
    private Administrator admin;

    public Controller(IUserStore userList, IOrderStore orderList, IItemStore itemList, Administrator admin){
        this.userlist = userList;
        this.orderlist = orderList;
        this.itemlist = itemList;
        this.admin = admin;
    }
 
    public User searchLoginUser(String username, String password){
    	if (admin.getUsername().equals(username) && admin.getPassword().equals(password))
    		return admin;
    	else
    		return userlist.searchLoginUser(username, password);
    }

    public void addUser(String username, String password, String name, String email, String contactNum){
        String userID = userlist.generateUserID();
        ClientUser newUser = new ClientUser(userID, username, password, name, email, contactNum);
        userlist.addUser(newUser);
    }

    public void createItem(String name, String type, double price, int quantity){
        String itemID = itemlist.generateItemID();
        Item newItem = new Item(itemID, name, type, price, quantity);
        itemlist.createItem(newItem);
    }
    
    public void displayItem(Item item) {
    	itemlist.displayItem(item);
    }

    public void displayItemList() {
        itemlist.displayItemList();
    }

    public Item searchItem(String itemID){
        return itemlist.searchItem(itemID);
    }
    
    public void updateItemQuantity(Item item, int newQuantity) {
    	item.setQuantity(newQuantity);
        itemlist.updateItem(item);
    }
    
    public void updateItemPrice(Item item, double newPrice) {
    	item.setPrice(newPrice);
        itemlist.updateItem(item);
    }
    
    //Delete Item
    public void deleteItem(Item item){
        itemlist.deleteItem(item);
    }
    
    public Order searchOrder(String orderID, ArrayList<Order> searchList){
        Order theOrder = null;
        for(Order order:searchList)
        {
            if (order.getOrderID().equals(orderID))
                theOrder = order;
        }
        return theOrder;
    }
    
    public ArrayList <Order> getOrderList(){
    	return orderlist.getOrderList();
    }
   
    public void createOrder(String userID,  String phone, String address, ArrayList<OrderedItem> orderItems){
        String orderID = orderlist.generateOrderID();
        Order newOrder = new Order(orderID, userID, phone, address, orderItems);
        orderlist.createOrder(newOrder);
        //Amendment have made in consoleUI
        itemlist.saveItemListToFile();
    }
    
    public void displayAnOrder(Order order) {
    	orderlist.displayAnOrder(order, itemlist);
    }
    
    public void displayOrders(ArrayList<Order> orders){
        orderlist.displayOrderList(orders, itemlist);
    }
    
    public ArrayList <Order> getUserOrders(User user) {
    	return orderlist.getUserOrders(user);
    }
    
    public ArrayList<Order> getPendingOrders(ClientUser client){
        return orderlist.getPendingOrders(client);
    }

    public ArrayList<Order> getPaidOrders(User user){
        return orderlist.getPaidOrders(user);
    }

    public void updateOrderStatus(Order order, String newStatus){
        order.setOrderStatus(newStatus);
        orderlist.updateOrder(order);
    }

    public void updateOrderItemQuantity(Order order, Item item, OrderedItem orderedItem, int newQuantity) {
    	int oldQuantity = orderedItem.getQuantity();
    	orderedItem.setQuantity(newQuantity);
    	orderlist.updateOrder(order);
    	int newStock = item.getQuantity() + oldQuantity - newQuantity;
    	item.setQuantity(newStock);
    	itemlist.saveItemListToFile();
    }
    
    public void updateOrderPhone(Order order, String newPhone) {
    	order.setPhone(newPhone);
    	orderlist.updateOrder(order);
    }
    
    public void updateOrderAddress(Order order, String address) {
    	order.setAddress(address);
    	orderlist.updateOrder(order);
    }

    public void printInvoice(Order order){
        System.out.println();
        System.out.println("Invoice");
		System.out.println("-".repeat(90));
		System.out.println("Order ID: " + order.getOrderID());
        System.out.println("User ID: " + order.getUserID());
        System.out.println("Phone: " + order.getPhone());
        System.out.println("Address: " + order.getAddress());
		System.out.println("-".repeat(90));
        System.out.println("Order Items");
		System.out.println();
        ArrayList<OrderedItem> orderedItemList = order.getOrderItemList();
		
		double grandTotal = 0;

		System.out.printf("%-20s %-40s %-10s %-10s %-10s\n", "Item ID", "Description", "Price", "Quantity", "Total");
		
		for (OrderedItem anOrderedItem : orderedItemList)
		{
            String itemID = anOrderedItem.getItemID();
            Item item = searchItem(itemID);
			double price = item.getPrice();
			int quantity = anOrderedItem.getQuantity();
			double total = price*quantity;
			grandTotal += total;
			System.out.printf("%-20s %-40s %-10.2f %-10d %-10.2f\n",itemID,item.getName(),price, quantity, total);
		}
		
		System.out.println();
		System.out.println("-".repeat(90));
		System.out.printf("Grand Total %71s %.2f\n", "RM",grandTotal);
		System.out.println("-".repeat(90));
    }
    
    public void makePayment(Order order, String referenceNum){
    	order.setReferenceNum(referenceNum);
    	order.setOrderStatus("PREPARING");
    	orderlist.saveOrderListToFile();
    }

    public void printReceipt(Order order){
        System.out.println();
        System.out.println("Receipt");
		System.out.println("-".repeat(90));
		System.out.println("Order ID: " + order.getOrderID());
        System.out.println("User ID: " + order.getUserID());
        System.out.println("Phone: " + order.getPhone());
        System.out.println("Address: " + order.getAddress());
        System.out.println("Reference Number: " + order.getReferenceNum());
		System.out.println("-".repeat(90));
        System.out.println("Order Items");
		System.out.println();
        ArrayList<OrderedItem> orderedItemList = order.getOrderItemList();
		
		double grandTotal = 0;

		System.out.printf("%-20s %-40s %-10s %-10s %-10s\n", "Item ID", "Description", "Price", "Quantity", "Total");
		
		for (OrderedItem anOrderedItem : orderedItemList)
		{
            String itemID = anOrderedItem.getItemID();
            Item item = searchItem(itemID);
			double price = item.getPrice();
			int quantity = anOrderedItem.getQuantity();
			double total = price*quantity;
			grandTotal += total;
			System.out.printf("%-20s %-40s %-10.2f %-10d %-10.2f\n",itemID,item.getName(),price, quantity, total);
		}
		
		System.out.println();
		System.out.println("-".repeat(90));
		System.out.printf("Grand Total %71s %.2f%n", "RM",grandTotal);
		System.out.println("-".repeat(90));
    }

    public void printSalesReport(User user){
        ArrayList<Order> paidOrderList = orderlist.getPaidOrders(user);
        System.out.println("Sales Report");
        System.out.println("-".repeat(90));
        System.out.printf("%-20s %-35s %15s\n","Item ID","Description", "Quantity sold");
        ArrayList<Item> itemList = itemlist.getItemList();
        for (Item item : itemList){
            String itemID = item.getItemID();
            int salesQty = 0;
            for (Order paidOrder : paidOrderList){
                ArrayList<OrderedItem> orderedItems = paidOrder.getOrderItemList();
                for (OrderedItem anOrderedItem : orderedItems){
                    if(anOrderedItem.getItemID().equals(itemID)){
                        //salesQty++;
                    	salesQty += anOrderedItem.getQuantity();
                    }
                }
            }
            System.out.printf("%-20s %-35s %15d\n",itemID,item.getName(), salesQty);
        }
        System.out.println();
    }

    public void printInventoryReport(){
        String restock;
        System.out.println("Inventory Report");
        System.out.println("-".repeat(90));
        System.out.printf("%-20s %-35s %15s %15s\n","Item ID","Description", "Current Quantity", "Restock Alert");
        ArrayList<Item> itemList = itemlist.getItemList();
        for (Item item : itemList){
            if(item.getQuantity() < 20){
                restock = "Restock";
            }
            else{
                restock = "-";
            }

            System.out.printf("%-10s %-35s %20d %20s\n",item.getItemID(), item.getName(), item.getQuantity(), restock);
        }
        System.out.println();
    }

}
