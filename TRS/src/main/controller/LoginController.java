package main.controller;

import main.DAO.UserConnection_imp;
import main.dataTransferObject.ERS_User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//takes request from servlet, do the job, tells servlet what to do
public class LoginController {
    public static String login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String login_username = req.getParameter("username"); //these are from login.html
        String login_password = req.getParameter("password");

        /*create a connection with ERS_User in the db to get the username and password*/
        //later needs to create a separate file called Validate, get user login info from db
        UserConnection_imp con = new UserConnection_imp();
        ERS_User user = con.getUserByUsernameAndPassword(login_username,login_password);

        //save this to pass the userID along. Know what user is logged in to show corresponding info
        HttpSession s = req.getSession();

        /*compare the username and password entered with those in the db*/
        if(user.getUserName().equals(login_username.trim()) && user.getPassword().equals(login_password.trim())) {
            s.setAttribute("User", user); //key and value
            if (user.getRoleID() == 1002)
                return "employee.html";

            return "manager.html";

        }
        else {
            out.println("Username or Password incorrect");
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.include(req, resp);
        }
        return "login.html";
    }
}
