package main.DAO;

import main.dataTransferObject.ERS_Ticket;

import java.sql.Date;
import java.util.List;

/*used to connect to the DB*/
public interface TicketConnection {
    ERS_Ticket getSubmittedTicket(int amount, String description, Date submittedDate, int status, String author);
    List<ERS_Ticket> getTicketByStatus(int status);
    int setTicketStatus(int status);
    int addTicket(int amount, String description, int type, String author);
}
