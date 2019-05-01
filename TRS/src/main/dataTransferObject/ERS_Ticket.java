package main.dataTransferObject;

import java.sql.Date;

public class ERS_Ticket{
    private int ticketID,  ticketAuthor, ticketResolver, ticketStatusID, ticketTypeID;
    private double ticketAmount;
    private Date ticketSubmitted, ticketResolved;
    private String ticketDescription;

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public double getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(double ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public int getTicketAuthor() {
        return ticketAuthor;
    }

    public void setTicketAuthor(int ticketAuthor) {
        this.ticketAuthor = ticketAuthor;
    }

    public int getticketResolver() {
        return ticketResolver;
    }

    public void setTicketResolver(int ticketResolver) {
        this.ticketResolver = ticketResolver;
    }

    public int getTicketStatusID() {
        return ticketStatusID;
    }

    public void setTicketStatusID(int ticketStatusID) {
        this.ticketStatusID = ticketStatusID;
    }

    public int getTicketTypeID() {
        return ticketTypeID;
    }

    public void setTicketTypeID(int ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }

    public Date getticketSubmitted() {
        return ticketSubmitted;
    }

    public void setTicketSubmitted(Date ticketSubmitted) {
        this.ticketSubmitted = ticketSubmitted;
    }

    public Date getTicketResolved() {
        return ticketResolved;
    }

    public void setTicketResolved(Date ticketResolved) {
        this.ticketResolved = ticketResolved;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    @Override
    public String toString() {
        return "ERS_Ticket{" +
                "ticketID=" + ticketID +
                ", ticketAmount=" + ticketAmount +
                ", ticketSubmitted=" + ticketSubmitted +
                ", ticketDescription='" + ticketDescription + '\'' +
                ", ticketAuthor=" + ticketAuthor +
                ", ticketResolved=" + ticketResolved +
                ", ticketResolver=" + ticketResolver +
                ", ticketStatusID=" + ticketStatusID +
                ", ticketTypeID=" + ticketTypeID +
                '}';
    }
}