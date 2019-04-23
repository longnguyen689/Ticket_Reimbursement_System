package main.DAO;

import main.utility.DBUtility; //user defined
import main.dataTransferObject.ERS_Ticket;

import java.sql.*;
import java.util.List;

public class TicketConnection_imp implements TicketConnection {
    private Connection con;
    @Override
    public ERS_Ticket getSubmittedTicket(int amount, String description, Date submittedDate, int status, String author) {
        con = DBUtility.getInstance();
        ERS_Ticket ticket = null;
        /*need null instead of an object (with new keyword) to avoid getting a ticket
         of null values, cuz we dont want to parse a ticket of null values.*/

        try {
            PreparedStatement prep = con.prepareStatement("select * from ERS_REIMBURSEMENT");
            ResultSet res = prep.executeQuery();
            if( res.next() )
                ticket = parseToTicket(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ticket;
    }

    @Override
    public List<ERS_Ticket> getTicketByStatus(int status){
        con = DBUtility.getInstance();
        List<ERS_Ticket> ticketList = null;
        int index = 0;
        try {
            PreparedStatement prep = con.prepareStatement("select * from ERS_REIMBURSEMENT where REIMB_STATUS_ID =? ");
            prep.setString(1, String.valueOf(status));
            ResultSet res = prep.executeQuery();
            while( res.next() ) {
                ticketList.set(index++, parseToTicket(res));
            }
        } catch (SQLException e) {
            System.out.println("Fail to retrieve ticket(s).");
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
    public int setTicketStatus(int status) {
        con = DBUtility.getInstance();
        ERS_Ticket ticket = null;
        int row_affected = 0;
        try {
            PreparedStatement prep = con.prepareStatement("update ERS_REIMBURSEMENT set REIMB_STATUS_ID where REIMB_STATUS_ID = ?");
            prep.setString(1, String.valueOf(status));
            row_affected = prep.executeUpdate();

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
    public int addTicket(int amount, String description, int type, String author) {
        con = DBUtility.getInstance();
        int row_affected = 0;
        try {
            PreparedStatement prep = con.prepareStatement(
                    "insert into ers_reimbursement(reimb_amount, reimb_description," +
                            " reimb_author, reimb_type_id) values(?,?,?,?)");
            prep.setString(1, String.valueOf(amount));
            prep.setString(2, description);
            prep.setString(3, String.valueOf(type));
            prep.setString(4, author);

            row_affected = prep.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Fail to add ticket.");
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
            ticket.setT_ID(result.getInt(1));
            ticket.setT_amount(result.getInt(2));
            ticket.setT_submitted(result.getDate(3));
            ticket.setT_resolved(result.getDate(4));
            ticket.setT_description(result.getString(5));
            ticket.setT_author(result.getInt(6));
            ticket.setT_resolver(result.getInt(7));
            ticket.setT_statusID(result.getInt(8));
            ticket.setT_typeID(result.getInt(9));
        } catch (SQLException e) {
            System.out.println("Failed to parse.");
            e.printStackTrace();
        }
        return ticket;
    }
}
