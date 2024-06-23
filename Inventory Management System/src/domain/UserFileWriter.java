package domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserFileWriter implements IFileWriter<ClientUser>{
    public void writeFile(String fileName, ArrayList<ClientUser> userList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (ClientUser user : userList) {
                // Write user information to the file
                bw.write(user.getUserID() + "|" + user.getUsername() + "|" + user.getPassword() + "|" + user.getName() + "|" + user.getEmail() + "|" + user.getContactNum());
                bw.newLine(); // Add newline character after each user
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

