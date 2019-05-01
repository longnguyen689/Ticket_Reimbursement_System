package main.DAO;

import main.dataTransferObject.ERS_User;
import main.utility.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserConnection_imp implements UserConnection {
    private Connection con;

    @Override
    public ERS_User getUserByUsernameAndPassword(String username, String password) {
        con = DBUtility.getInstance();
        ERS_User user = null;
        try {
            PreparedStatement prep = con.prepareStatement("select * from ERS_USERS where ERS_USERNAME=? and ERS_PASSWORD=?");
            //assign values (to be shown in table) to place holders
            prep.setString(1, username);
            prep.setString(2, password);
            ResultSet res = prep.executeQuery();
            if( res.next() ) //.next() returns bool
                user = parseToUser(res);
        } catch (SQLException e) {
            System.out.println("Unable to connect to the database."+ e);
        }
        return user;
    }

    private ERS_User parseToUser(ResultSet resultSet) {
        ERS_User user = new ERS_User();
        try {
            user.setUserID(resultSet.getInt(1));
            user.setUserName(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setFirstName(resultSet.getString(4));
            user.setLastName(resultSet.getString(5));
            user.setEmail(resultSet.getString(6));
            user.setRoleID(resultSet.getInt(7));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        UserConnection_imp obj = new UserConnection_imp();
        System.out.println(obj.getUserByUsernameAndPassword("ldn", "password"));
        int index  = 0;
        int x = 0;
        while( index < 8){
            System.out.println("index" + index++);
            //x++;
        }
    }

}
