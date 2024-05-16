package projekt.m223.projektM223.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "reservations")
public class Reservation {
    @Id
    private Date date;
    private String since;
    private String until;
    private int room;
    private String comment;
    private String listOfParticipants;

    public Reservation() {
    }

    public Reservation(Date date, String since, String until, int room, String comment, String listOfParticipants) {
        this.date = date;
        this.since = since;
        this.until = until;
        this.room = room;
        this.comment = comment;
        this.listOfParticipants = listOfParticipants;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getListOfParticipants() {
        return listOfParticipants;
    }

    public void setListOfParticipants(String listOfParticipants) {
        this.listOfParticipants = listOfParticipants;
    }
}