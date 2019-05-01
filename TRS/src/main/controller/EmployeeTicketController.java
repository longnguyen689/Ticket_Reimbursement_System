package main.controller;

import com.google.gson.Gson;
import main.DAO.TicketConnection_imp;
import main.dataTransferObject.ERS_Ticket;
import main.dataTransferObject.ERS_User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EmployeeTicketController {
    private Gson gson = new Gson();
    /*show tickets of an employee based on userID*/
    public void getTicketsForEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        HttpSession s = req.getSession();

        int user_id = ( (ERS_User) s.getAttribute("User")).getUserID();

        TicketConnection_imp con = new TicketConnection_imp();
        List<ERS_Ticket> ticketList = con.getTicketForEmployee(user_id);
        //ticketList.toString();
        String data = this.gson.toJson(ticketList);
        //data = "{\"ticket\": " + data + "}";
        writer.print(data);
    }

}
