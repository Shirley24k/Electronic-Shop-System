package domain;
import java.util.ArrayList;

public class UserList implements IUserStore{
    private ArrayList<ClientUser> userList;
    private IFileReader <ClientUser> userReader;
    private IFileWriter <ClientUser> userWriter;

    public UserList(IFileReader <ClientUser> userReader, IFileWriter <ClientUser> userWriter){
        userList = new ArrayList<ClientUser>();
        this.userReader = userReader;
        this.userWriter = userWriter;
        loadUserListFromFile();
    }

    public void loadUserListFromFile() {
        userList = userReader.readFile("user.txt");
    }

    public ArrayList<ClientUser> getUserList() {
        return userList;
    }

    public ClientUser searchLoginUser(String username, String password) {
        ClientUser theSearchUser = null;
        for (ClientUser user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                theSearchUser = user;
                break;
            }
        }
        return theSearchUser;
    }

    public String getLastUserID(){
        int size = userList.size();
        if (size > 0) {
        	return userList.get(size-1).getUserID();
        }
        else
        	return "U0"; //Default ID if no users
    }

    public String generateUserID() {
        int lastUserID = Integer.parseInt(getLastUserID().substring(1));
        lastUserID++; // Increment the counter
        return "U" + lastUserID;
    }

    public void addUser(ClientUser newUser){
        userList.add(newUser);
        saveUserListToFile();
    }

    public void saveUserListToFile() {
        userWriter.writeFile("user.txt", userList);
    }
}
