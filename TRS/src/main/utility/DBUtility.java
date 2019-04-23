package main.utility;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtility {
    private static BasicDataSource connectionPool = new BasicDataSource();

    /*jdbc:oracle:thin:@ is telling Oracle driver that this is my endpoint connection.
    * Service name: ORCL
    * Port#: 1521
    */
    static {
        connectionPool.setDriverClassName("oracle.jdbc.driver.OracleDriver"); //driver that I injected into pom.xml
        //endpoint starts from [project, .com]
        connectionPool.setUrl("jdbc:oracle:thin:@project1.c60jn8ke2mdi.us-east-2.rds.amazonaws.com:1521:ORCL");
        connectionPool.setUsername("project1");
        connectionPool.setPassword("thienlong689");
        connectionPool.setMaxTotal(100);
        connectionPool.setDefaultAutoCommit(false);
    }

    public static Connection getInstance() {
        try {
            return connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Unable to connect, please try again later");
        }
        return null;
    }
}