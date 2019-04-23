package main.dataTransferObject;

import java.sql.Date;

public class ERS_Ticket{
    private int t_ID, t_amount, t_author, t_resolver, t_statusID, t_typeID;
    private Date t_submitted, t_resolved;
    private String t_description;

    public int getT_ID() {
        return t_ID;
    }

    public void setT_ID(int t_ID) {
        this.t_ID = t_ID;
    }

    public int getT_amount() {
        return t_amount;
    }

    public void setT_amount(int t_amount) {
        this.t_amount = t_amount;
    }

    public int getT_author() {
        return t_author;
    }

    public void setT_author(int t_author) {
        this.t_author = t_author;
    }

    public int getT_resolver() {
        return t_resolver;
    }

    public void setT_resolver(int t_resolver) {
        this.t_resolver = t_resolver;
    }

    public int getT_statusID() {
        return t_statusID;
    }

    public void setT_statusID(int t_statusID) {
        this.t_statusID = t_statusID;
    }

    public int getT_typeID() {
        return t_typeID;
    }

    public void setT_typeID(int t_typeID) {
        this.t_typeID = t_typeID;
    }

    public Date getT_submitted() {
        return t_submitted;
    }

    public void setT_submitted(Date t_submitted) {
        this.t_submitted = t_submitted;
    }

    public Date getT_resolved() {
        return t_resolved;
    }

    public void setT_resolved(Date t_resolved) {
        this.t_resolved = t_resolved;
    }

    public String getT_description() {
        return t_description;
    }

    public void setT_description(String t_description) {
        this.t_description = t_description;
    }

    @Override
    public String toString() {
        return "ERS_Ticket{" +
                "t_ID=" + t_ID +
                ", t_amount=" + t_amount +
                ", t_author=" + t_author +
                ", t_resolver=" + t_resolver +
                ", t_statusID=" + t_statusID +
                ", t_typeID=" + t_typeID +
                ", t_submitted=" + t_submitted +
                ", t_resolved=" + t_resolved +
                ", t_description='" + t_description + '\'' +
                '}';
    }
}