package domain;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserFileReader implements IFileReader<ClientUser> {
    
    public ArrayList<ClientUser> readFile(String fileName) {
        
        ArrayList<ClientUser> userList = new ArrayList<ClientUser>();
        // Read file and populate userList
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split("\\|");
                if (userInfo.length == 6) { // Ensure correct number of fields
                    String userID = userInfo[0].trim();
                    String username = userInfo[1].trim();
                    String password = userInfo[2].trim();
                    String name = userInfo[3].trim();
                    String email = userInfo[4].trim();
                    String contactNum = userInfo[5].trim();
                
	                // Create ClientUser object and add to userList
	                ClientUser user = new ClientUser(userID, username, password, name, email, contactNum);
	                userList.add(user);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return userList;
    }
}
