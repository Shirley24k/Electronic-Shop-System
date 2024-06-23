package app;

import domain.*;

public class App {
    
    public static void main(String[] args) {
    	IFileReader <Item> itemReader = new ItemFileReader();
    	IFileWriter <Item> itemWriter = new ItemFileWriter();
    	ItemList itemlist = new ItemList(itemReader, itemWriter);
    	
    	IFileReader <ClientUser> userReader = new UserFileReader();
    	IFileWriter <ClientUser> userWriter = new UserFileWriter();
        UserList userlist = new UserList(userReader, userWriter);
        
        IFileReader <Order> orderReader = new OrderFileReader();
        IFileWriter <Order> orderWriter = new OrderFileWriter();
        OrderList orderlist = new OrderList(orderReader, orderWriter);
        
        Administrator admin = new Administrator();
        Controller controller = new Controller(userlist, orderlist, itemlist, admin);
        ConsoleUI userInterface = new ConsoleUI(controller);
        

        userInterface.start();
    }
}
