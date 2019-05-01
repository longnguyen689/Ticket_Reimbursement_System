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


public class ManagerTicketController {
    private Gson gson = new Gson();
    /*connect to DAO and update ticket status*/
    public static String resolve(HttpServletRequest req) {
        HttpSession session = req.getSession();
        TicketConnection_imp ticket = new TicketConnection_imp();

        if(req.getParameter("approve-button")!= null) {
             ticket.setTicketStatus(1, Integer.parseInt(req.getParameter("approve-button")));
        }
        else {
           ticket.setTicketStatus(2, Integer.parseInt( req.getParameter("deny-button")));
        }
        return "manager.html";
    }

    /*connect to DAO and show all tickets of all employees for manager view*/
    public void managerViewsTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        HttpSession s = req.getSession();

        TicketConnection_imp con = new TicketConnection_imp();
        List<ERS_Ticket> ticketList = con.getSubmittedTicketForManager();

        String data = this.gson.toJson(ticketList);
        writer.print(data);
    }
    /*connect to DAO and show resolved tickets*/
    public void getResolvedTickets(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        HttpSession s = req.getSession();

        TicketConnection_imp con = new TicketConnection_imp();
        List<ERS_Ticket> ticketList = con.getResolvedTicketsForManager();

        String data = this.gson.toJson(ticketList);
        writer.print(data);
    }
}