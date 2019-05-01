package main.DAO;

import main.dataTransferObject.ERS_Ticket;

import java.sql.Date;
import java.util.List;

/*used to connect to the DB*/
public interface TicketConnection {
    List<ERS_Ticket> getSubmittedTicketForManager();
    List<ERS_Ticket> getTicketForEmployee(int userID);
    List<ERS_Ticket> getResolvedTicketsForManager();
    int setTicketStatus(int status, int ticketID);
    int addTicket(double amount, String description, int author, int type);
}
