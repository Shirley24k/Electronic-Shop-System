package domain;

import java.util.ArrayList;

public class Order {
	
	private String orderID;
	private String userID; 
	private ArrayList<OrderedItem> orderItem; 
	private String phone;
	private String address;
	private String status;
	private String referenceNum;

    public Order(String orderID, String userID,  String phone, String address, ArrayList<OrderedItem> orderItem)
	{
		this.orderID = orderID;
		this.userID = userID;
		this.phone = phone;
		this.address = address;
		this.status = "PENDING";
		this.referenceNum = "-";
		this.orderItem = orderItem;
	}

    //For read file
    public Order(String orderID, String userID, String phone, String address, String status, String referenceNum, ArrayList<OrderedItem> orderItem)
	{
		this.orderID = orderID;
		this.userID = userID;
		this.phone = phone;
		this.address = address;
		this.status = status;
		this.referenceNum = referenceNum;
		this.orderItem = orderItem;
	}
	
	public String getOrderID(){
		return orderID;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getOrderStatus() {
		return status;
	}
	
	public String getReferenceNum() {
		return referenceNum;
	}

    public ArrayList<OrderedItem> getOrderItemList() {
		return orderItem;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public void setOrderStatus(String status) {
		this.status = status;
	}
	
	public void setReferenceNum(String referenceNum) {
		this.referenceNum = referenceNum;
	}

    public OrderedItem searchOrderedItem(String itemID)
	{
		OrderedItem theOrderedItem = null;
		
		for (OrderedItem anOrderedItem : orderItem)
		{
			if (anOrderedItem.getItemID().equals(itemID))
			{
				theOrderedItem = anOrderedItem;
				break;
			}
		}
		return theOrderedItem;
	}

}
