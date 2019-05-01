package main.controller;

import main.DAO.TicketConnection_imp;
import main.dataTransferObject.ERS_User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SubmitTicketController {
    public static String submit(HttpServletRequest req) {
        HttpSession session = req.getSession();
        TicketConnection_imp ticketDao = new TicketConnection_imp();

        int reimb_type = 0;
        try{
            reimb_type = Integer.parseInt(req.getParameter("type"));
        }catch(NumberFormatException e) {
            System.out.println("Invalid type");
        }
        double reimb_amount = 0.0;
        try{
            reimb_amount = Double.parseDouble(req.getParameter("ticket-amount"));
        }catch(NumberFormatException e) {
            System.out.println("Invalid Amount");
        }
        System.out.println("here: " + req.getParameter("ticket-description"));
        String reimb_description = req.getParameter("ticket-description"); //reference from html
        ticketDao.addTicket(reimb_amount, reimb_description, ((ERS_User)session.getAttribute("User")).getUserID(), reimb_type); //"userID" is the key

        if(((ERS_User)session.getAttribute("User")).getRoleID() == 1001) return "manager.html";
        return "employee.html";
    }
}
