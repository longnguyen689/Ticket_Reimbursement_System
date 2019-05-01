package main.DAO;

import main.dataTransferObject.ERS_Ticket;
import main.utility.DBUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketConnection_imp implements TicketConnection {
    private Connection con;
    @Override
    public List<ERS_Ticket> getSubmittedTicketForManager() {
        con = DBUtility.getInstance();
        List<ERS_Ticket> ticketList = null;
        /*need null instead of an object (with new keyword) to avoid getting a ticket
         of null values, cuz we dont want to parse a ticket of null values.*/
        try {
            PreparedStatement prep = con.prepareStatement("select * from ERS_REIMBURSEMENT where REIMB_STATUS_ID =?");
            prep.setString(1, String.valueOf(3)); //this extra loc is necessary, it prevents troll user input. 3 is pending
            ResultSet res = prep.executeQuery();
            ticketList = new ArrayList<>();
            while( res.next() )
                ticketList.add(parseToTicket(res));
        } catch (SQLException e) {
            System.out.println("Fail to get submitted tickets.");
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Fail to close in getSubmittedTicketForManager().");
                e.printStackTrace();
            }
        }
        return ticketList;
    }

    @Override
    public List<ERS_Ticket> getResolvedTicketsForManager(){
        con = DBUtility.getInstance();
        List<ERS_Ticket> ticketList = null;
        try {
            PreparedStatement prep = con.prepareStatement("select * from ERS_REIMBURSEMENT where REIMB_STATUS_ID !=?");
            prep.setString(1,String.valueOf(3)); //this extra loc is necessary, it prevents troll user input. 3 is pending
            ResultSet res = prep.executeQuery();
            ticketList = new ArrayList<>();
            while( res.next() )
                ticketList.add(parseToTicket(res));
        } catch (SQLException e) {
            System.out.println("Fail to get resolved tickets.");
            e.printStackTrace();
        }
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Fail to close in getResolvedTicketsForManager().");
                e.printStackTrace();
            }
        }
        return ticketList;

    }

    @Override
    public List<ERS_Ticket> getTicketForEmployee(int userID){
        con = DBUtility.getInstance();
        List<ERS_Ticket> ticketList = null;
        try {
            PreparedStatement prep = con.prepareStatement("select * from ERS_REIMBURSEMENT where REIMB_AUTHOR=?");
            prep.setString(1, String.valueOf(userID));
            ResultSet res = prep.executeQuery();
            ticketList = new ArrayList<>(); //make new here so that if an exception is thrown, we get null, which means we did'nt reach the db yet
            while( res.next() ) {
                ticketList.add(parseToTicket(res));
            }
        } catch (SQLException e) {
            System.out.println("Fail to retrieve ticket(s) for this employee.");
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Fail to close.");
                e.printStackTrace();
            }
        }
        return ticketList;
    }

    @Override
    public int setTicketStatus(int status, int ticketID) {
        con = DBUtility.getInstance();
        int row_affected = 0;
        try {
            PreparedStatement prep = con.prepareStatement("update ERS_REIMBURSEMENT set REIMB_STATUS_ID=? where REIMB_ID=?");
            prep.setString(1, String.valueOf(status));
            prep.setString(2, String.valueOf(ticketID));
            row_affected = prep.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            System.out.println("Fail to update ticket status.");
            e.printStackTrace();
        }
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Fail to close.");
                e.printStackTrace();
            }
        }
        return row_affected;
    }


    @Override
    public int addTicket(double amount, String description, int authorID, int typeID) {
        con = DBUtility.getInstance();
        int row_affected = 0;
        try {
            PreparedStatement prep = con.prepareStatement(
                    "insert into ers_reimbursement(reimb_amount, reimb_description," +
                            " reimb_author, reimb_type_id) values(?,?,?,?)");
            prep.setString(1, String.valueOf(amount));
            prep.setString(2, description);
            prep.setString(3, String.valueOf(authorID));
            prep.setString(4, String.valueOf(typeID));
            row_affected = prep.executeUpdate();
            con.commit();

        } catch (SQLException e) {
            System.out.println("Fail to add a ticket.");
            e.printStackTrace();
        }
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Fail to close.");
                e.printStackTrace();
            }
        }
        return row_affected;
    }

    private ERS_Ticket parseToTicket(ResultSet result){
        ERS_Ticket ticket = new ERS_Ticket();
        try {
            ticket.setTicketID(result.getInt(1));
            ticket.setTicketAmount(result.getInt(2));
            ticket.setTicketSubmitted(result.getDate(3));
            ticket.setTicketResolved(result.getDate(4));
            ticket.setTicketDescription(result.getString(5));
            ticket.setTicketAuthor(result.getInt(6));
            ticket.setTicketResolver(result.getInt(7));
            ticket.setTicketStatusID(result.getInt(8));
            ticket.setTicketTypeID(result.getInt(9));
        } catch (SQLException e) {
            System.out.println("Failed to parse.");
            e.printStackTrace();
        }
        return ticket;
    }
//    public static void main(String[] args){
        //TicketConnection_imp ticket = new TicketConnection_imp();
        //List<ERS_Ticket> t = ticket.getSubmittedTicketForManager();
        //System.out.println(t);
        //int num = ticket.addTicket(991,"intelliJ2", 2002, 2);
        //authorID is int, type is int
        //System.out.println(num);
//    }

}
