package main.servlet;

import main.DAO.UserConnection_imp;
import main.dataTransferObject.ERS_User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet { //accepts request and then responds

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*req takes parameter from user input entered on the fields from browser (client)*/
        String login_username = req.getParameter("username");
        String login_password = req.getParameter("password");

        /*create a connection with ERS_User in the db to get the username and password*/
        UserConnection_imp con = new UserConnection_imp();
        ERS_User user = con.getUserByUsernameAndPassword("ldn","password");

        /*compare the username and password entered with those in the db*/
        if(user.getUserName().equals(login_username.trim()) && user.getPassword().equals(login_password.trim())){
            req.getRequestDispatcher("/home.html").forward(req, resp); //forward to home page
        }
    }
}
