package main.DAO;

import main.dataTransferObject.ERS_User;
/*used to connect to the DB*/
public interface UserConnection {
    ERS_User getUserByUsernameAndPassword(String username, String password);

}
