package domain;

public class ClientUser extends User {
    
	private String userID;
    private String name;
    private String email;
	private String contactNum;

    public ClientUser(String userID, String username, String password, String name, String email, String contactNum)
	{
		super(username, password);
		this.userID = userID;
        this.name = name;
        this.email = email;
        this.contactNum = contactNum;
	}

	public String getUserID(){
		return userID;
	}
	
    public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getContactNum() {
		return contactNum;
	}
}
