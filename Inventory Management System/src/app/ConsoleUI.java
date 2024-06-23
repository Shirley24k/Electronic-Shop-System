package app;

import java.util.ArrayList;
import java.util.Scanner;

import domain.Administrator;
import domain.ClientUser;
import domain.Controller;
import domain.Item;
import domain.Order;
import domain.OrderedItem;
import domain.User;

public class ConsoleUI {

    private Scanner input;
    private Controller controller;
    private String skip;
    
    public ConsoleUI(Controller controller){
        this.controller = controller;
        input = new Scanner(System.in);
    }

    public void start(){
    	User user;
        do{
            user = getLoginUser();
            if (user == null) {
                System.out.println("Invalid username or password.");
            }
            else if (user instanceof Administrator){
            	int option;
                //Display administrator main menu
                do{
                    System.out.println("----------Admin Menu------------");
                    System.out.println("1. Create user profile");
                    System.out.println("2. Manage item");
                    System.out.println("3. View order");
                    System.out.println("4. Generate report");
                    System.out.println("5. Exit");
                    System.out.print("Enter option: ");
                    option = input.nextInt();
                    skip = input.nextLine();
                    switch(option)
                    {
                        case 1:
                            createProfile();
                            break;
                        case 2:
                            manageItem();
                            break;
                        case 3:
                            viewOrders(user);
                            break;
                        case 4:
                            generateReport(user);
                            break;
                        case 5:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid option.");
                            break;
                    }
                }while(option != 5);
                
            }
            else
            {
            	int option;
                do
                {
                    //Print client main menu
                    System.out.println("----------Client User Menu------------");
                    System.out.println("1. View item list");
                    System.out.println("2. View order");
                    System.out.println("3. Exit");
                    System.out.print("Enter option: ");
                    option = input.nextInt();
                    skip = input.nextLine();
                    switch(option)
                    {
                        case 1:
                            displayItemList((ClientUser) user);
                            break;
                        case 2:
                            viewOrders(user);
                            break;
                        case 3:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid option.");
                            break;
                    }
                }while(option != 3);
            }
        }while(user == null);
        
        input.close();
    }

    public User getLoginUser(){
    	System.out.println("----------Login------------");
    	System.out.print("Enter username: ");
    	String username = input.nextLine();
    	System.out.print("Enter password: ");
    	String password = input.nextLine();

    	return controller.searchLoginUser(username, password);
    }

    public void createProfile(){
        System.out.println("----------Create User Profile------------");
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        System.out.print("Enter name: ");
        String name = input.nextLine();
        System.out.print("Enter email: ");
        String email = input.nextLine();
        System.out.print("Enter contact number: ");
        String contactNum = input.nextLine();
        System.out.print("Confirm your action (Y/N): ");
        char confirm = input.nextLine().toUpperCase().charAt(0);
        if (confirm == 'Y'){
            controller.addUser(username, password, name, email, contactNum);
            System.out.println("User profile is created successfully.");
        }
        else{
            System.out.println("Press any key to exit.");
            skip = input.nextLine();
        }
    }

    //Create item
    public void createItem(){
        System.out.println("----------Create Item------------");
        System.out.print("Enter item name: ");
        String name = input.nextLine();
        System.out.print("Enter product type: ");
        String type = input.nextLine();
        System.out.print("Enter item price: ");
        double price = input.nextDouble();
        System.out.print("Enter item quantity: ");
        int quantity = input.nextInt();
        System.out.print("Confirm your action (Y/N): ");
        skip = input.nextLine();
        char confirm = input.nextLine().toUpperCase().charAt(0);
        if (confirm == 'Y'){
            controller.createItem(name, type, price, quantity);
            System.out.println("Item is created successfully.");
        }
        else{
            System.out.println("Press any key to exit.");
            skip = input.nextLine();
        }
        
    }

    public void displayItemList(ClientUser user){
        controller.displayItemList();
        System.out.println("Do you want to: ");
        System.out.println("1. Create order");
        System.out.println("2. Exit");
        System.out.print("Enter option: ");
        int option = input.nextInt();
        skip = input.nextLine();
        switch (option) {
            case 1:
                createOrder(user);
                break;
            case 2:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }

    //Update Item Details
    public void updateItem() {
        System.out.print("Enter item ID: ");
        String itemID = input.nextLine();
        Item item = controller.searchItem(itemID);
        if (item == null) {
        	System.out.println("No item found");
        	return;
        }
        else {
        	controller.displayItem(item);
        }
        System.out.println("Do you want to update:");
        System.out.println("1. Item Quantity");
        System.out.println("2. Item Price");
        System.out.println("3. Exit");
        System.out.print("Enter choice: ");
        int choice = input.nextInt();
        skip = input.nextLine();
        
        switch (choice) {
        case 1: // Update item quantity
            System.out.print("Enter new item quantity: ");
            int newQuantity = input.nextInt();
            // Check valid quantity
            if (newQuantity >= 0) {
                controller.updateItemQuantity(item, newQuantity);
                System.out.println("Item quantity updated successfully.");
            } else {
                System.out.println("Invalid quantity.");
            }
            break;
        case 2: // Update item price
            System.out.print("Enter new item price: ");
            double newPrice = input.nextDouble();
            // Check valid price
            if (newPrice >= 0) {
                controller.updateItemPrice(item, newPrice);
                System.out.println("Item price updated successfully.");
            } else {
                System.out.println("Invalid price.");
            }
            break;
        case 3:
            System.out.println("Exiting...");
            break;
        default:
            System.out.println("Invalid choice.");
            break;
        }
    
    }

    public void deleteItem(){
        System.out.print("Enter item ID: ");
        String itemID = input.nextLine();
        Item item = controller.searchItem(itemID);
        if (item == null) {
        	System.out.println("No item found");
        	return;
        }
        else {
        	controller.displayItem(item);
        }
        
        System.out.print("Confirm your action? (Y/N): ");
        char confirm = input.nextLine().toUpperCase().charAt(0);
        if (confirm == 'Y'){
            controller.deleteItem(item);
            System.out.println("Item is deleted successfully.");
        }
        else{
            System.out.println("Press any key to exit.");
            skip = input.nextLine();
        }
    }

    public void manageItem(){
        System.out.println("----------Manage Item Menu--------");
        System.out.println("1. Create Item");
        System.out.println("2. Display Item List");
        System.out.println("3. Update Item");
        System.out.println("4. Delete Item");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
        int choice = input.nextInt();
        skip = input.nextLine();

        switch(choice)
        {
            case 1:
                createItem();
                break;
            case 2:
                controller.displayItemList();
                break;
            case 3:
                updateItem();
                break;
            case 4:
                deleteItem();
                break;
            case 5:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }

    public void createOrder(ClientUser client) {
    	char confirm;
        String itemID;
        ArrayList<OrderedItem> orderedItemList = new ArrayList<OrderedItem>();
		
		do{
			Item item = null;
			do
			{
				System.out.print("Enter Item ID: ");
				itemID = input.nextLine();
				item  = controller.searchItem(itemID);

				if (item == null)
					System.out.println("Invalid item ID. Please re-enter.");
			}while(item == null);
			
			boolean validQuantity = false;
			do
			{
                int stockQty = item.getQuantity();
                
                if (stockQty <= 0) {
                	System.out.printf("Sorry, item unavailable. Current stock: %d\n", stockQty);
                	break;
                }

				System.out.print("Enter quantity: ");
				int quantity = input.nextInt();
				skip = input.nextLine();
				
                //Validate quantity entered
				if (quantity > 0 && quantity <= stockQty)
				{
					validQuantity = true;
					boolean orderedItemExist = false;
					
					for (OrderedItem orderItem : orderedItemList) {
						if (orderItem.getItemID().equals(item.getItemID())) {
							orderItem.setQuantity(orderItem.getQuantity() + quantity);
							orderedItemExist = true;
							break;
						}
					}
					
					if (!orderedItemExist) {
						OrderedItem newOrderedItem = new OrderedItem(itemID, quantity);
						orderedItemList.add(newOrderedItem);
					}

                    int currentQty = item.getQuantity() - quantity;
					item.setQuantity(currentQty);
				}
				else if (quantity > stockQty)
					System.out.println("Sorry, stock insufficient. Quantity available: " + stockQty);
				
				else
					System.out.println("Invalid quantity. Please enter again.");

			}while(!validQuantity);
			
			System.out.print("Do you want to add more item? (Y/N): ");
			confirm = input.nextLine().toUpperCase().charAt(0);
            
		}while(confirm == 'Y');
		
		//get delivery details
		System.out.println("Fill in delivery details.");
		System.out.print("Enter contact number: ");
		String phone = input.nextLine();
		System.out.print("Enter delivery address: ");
		String address = input.nextLine();

		//create order and store into orders array list
		System.out.print("Confirm the order (Y/N): ");
		confirm = input.nextLine().toUpperCase().charAt(0);
        if (confirm == 'Y'){
			controller.createOrder(client.getUserID(), phone, address, orderedItemList);
			System.out.println("Order created succesful.");
		}
		else {
			//Reset the quantity in item
			for (OrderedItem orderedItem:orderedItemList) {
				Item item = controller.searchItem(orderedItem.getItemID());
				item.setQuantity(item.getQuantity() + orderedItem.getQuantity());
			}
			System.out.println("Order created unsuccesful.");
			
		}
	}

    public void viewOrders(User user) {
    	
    	ArrayList <Order> userOrders = controller.getUserOrders(user);
    	if (userOrders.isEmpty()) {
    		System.out.println("No order found\n");
    		return;
    	}
    	else {
    		controller.displayOrders(userOrders);
    	}

        if(user instanceof Administrator){
            System.out.println("Do you want to:");
            System.out.println("1. Update Order");
            System.out.println("2. Exit");
            System.out.print("Enter option: ");
            int option = input.nextInt();
            skip = input.nextLine();
            System.out.println();
            switch(option)
            {
                case 1:
                    updateOrder(user);
                    break;
                case 2:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
        else{
            System.out.println("Do you want to:");
            System.out.println("1. Update Order");
            System.out.println("2. Make Payment");
            System.out.println("3. Exit");
            System.out.print("Enter option: ");
            int option = input.nextInt();
            skip = input.nextLine();
            switch(option)
            {
                case 1:
                	updateOrder(user);
                    break;
                case 2:
                    makePayment((ClientUser) user);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } 
	}

    public void updateOrder(User user){
        
        if (user instanceof Administrator){
        	ArrayList<Order> allPaidOrders = controller.getPaidOrders(user);
            if (allPaidOrders.size() == 0) {
            	System.out.println("No order found");
            	return;
            }else {
            	controller.displayOrders(allPaidOrders);
            }
            System.out.print("Enter order ID: ");
            String orderID = input.nextLine();
            Order orderToUpdate = controller.searchOrder(orderID, allPaidOrders);
            if(orderToUpdate == null){
                System.out.println("Invalid order ID.");
            }
            else{
            	System.out.println("\nStatus choice: ");
                System.out.println("SHIPPED");
                System.out.println("DELIVERED");
                System.out.println("CANCELLED");

                System.out.print("Enter new status: ");
                String newStatus = input.nextLine().toUpperCase();

                System.out.print("Confirm your action (Y/N): ");
                char confirm = input.nextLine().toUpperCase().charAt(0);
                if (confirm == 'Y'){
                	controller.updateOrderStatus(orderToUpdate,newStatus);
                    System.out.println("Updated successfully.");
                 }
                else{
                    System.out.println("Updated unsuccessfully.");
                }
            }
        }
        else{
        	ArrayList<Order> pendingOrders = controller.getPendingOrders((ClientUser) user);
        	if (pendingOrders.size() == 0) {
        		System.out.println("No order found");
        		return;
        	}else {
        		controller.displayOrders(pendingOrders);
        		System.out.print("Enter order ID: ");
                String orderID = input.nextLine();
                Order orderToUpdate = controller.searchOrder(orderID, pendingOrders);
                if(orderToUpdate == null){
                    System.out.println("Invalid order ID.");
                }
                else {
                	System.out.println("Do you want to update:");
                    System.out.println("1. Item Quantity");
                    System.out.println("2. Phone Number");
                    System.out.println("3. Shipping Address");
                    System.out.println("4. Exit");
                    System.out.print("Enter choice: ");  
                    
                    int choice = input.nextInt();
                    skip = input.nextLine();
                    switch(choice){
                    case 1: 
                    	System.out.print("Enter Item ID: ");
                        String itemID = input.nextLine();
                        OrderedItem orderedItem = orderToUpdate.searchOrderedItem(itemID);
                        
                        if(orderedItem == null){
                            System.out.println("Invalid item ID.");
                        }
                        else{
                            Item item = controller.searchItem(itemID);
                            boolean validQuantity = false;
                            do
                            {
                                int stockQty = item.getQuantity();
                
                                System.out.print("Enter quantity: ");
                                int newQuantity = input.nextInt();
                                skip = input.nextLine();
                                
                                //Validate quantity entered
                                if (newQuantity > 0 && newQuantity <= (stockQty + orderedItem.getQuantity())){
                                    validQuantity = true;
                                    controller.updateOrderItemQuantity(orderToUpdate, item, orderedItem, newQuantity);
                                    System.out.println("Item quantity updated successfully.");
                                }
                                
                                else if (newQuantity > (stockQty + orderedItem.getQuantity()))
                                    System.out.println("Sorry, stock insufficient. Quantity available: " + (stockQty + orderedItem.getQuantity()));
                                
                                else
                                    System.out.println("Invalid quantity. Please enter again.");
                            }while(!validQuantity);
                        }
                        break; 
                    
                    case 2:
                        System.out.print("Enter new phone number: ");
                        String newPhone = input.nextLine();
                        controller.updateOrderPhone(orderToUpdate, newPhone);
                        System.out.println("Phone number updated successfully.");
                        break;
                    case 3:
                        System.out.print("Enter new delivery address: ");
                        String newAddress = input.nextLine();
                        controller.updateOrderAddress(orderToUpdate, newAddress);
                        System.out.println("Delivery address updated successfully.");
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                    }
                }
        	}
        }
    }
    

    public void makePayment(ClientUser clientUser){
    	
    	ArrayList<Order> pendingOrdersForPayment = controller.getPendingOrders(clientUser);
        controller.displayOrders(pendingOrdersForPayment);
        if (pendingOrdersForPayment.size() == 0) {
        	System.out.println("No order found");
        	return;
        }
        else {
        	System.out.print("Enter order ID: ");
            String pendingOrderID = input.nextLine();
            Order orderToPay = controller.searchOrder(pendingOrderID, pendingOrdersForPayment);
            if(orderToPay == null){
                System.out.println("Invalid order ID.");
            }
            else {
            	controller.printInvoice(orderToPay);
                
                System.out.print("Enter reference number: ");
                String referenceNum = input.nextLine();
                controller.makePayment(orderToPay, referenceNum);
                System.out.println("Thank you. Payment received.");
                
                controller.printReceipt(orderToPay);
            }
        }
    }

    public void generateReport(User user){
        System.out.println("Do you want to: ");
        System.out.println("1. Inventory Report");
        System.out.println("2. Sales Report");
        System.out.println("3. Exit");
        System.out.print("Enter option: ");
        int option = input.nextInt();
        switch(option){
            case 1:
                controller.printInventoryReport();
                break;
            case 2:
                controller.printSalesReport(user);
                break;
            case 3: 
            	System.out.println("Exiting...");
            	break;
            default:
            	System.out.println("Invalid option.");
                break;
        }
    }
}
