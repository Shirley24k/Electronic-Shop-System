package domain;

import java.util.ArrayList;

public interface IUserStore {

	public void loadUserListFromFile();
	
    public ArrayList<ClientUser> getUserList();

    public ClientUser searchLoginUser(String username, String password);

    public String generateUserID();

    public void addUser(ClientUser newUser);
    
    public void saveUserListToFile();
   
}
