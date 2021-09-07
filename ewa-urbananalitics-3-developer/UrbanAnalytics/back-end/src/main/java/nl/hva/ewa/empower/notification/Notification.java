package nl.hva.ewa.empower.notification;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import nl.hva.ewa.empower.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity(name = "Notification")
@Table(name = "notification")
public class Notification {


    @Id
    @GeneratedValue
    @Column(columnDefinition = "notificationId")
    private int notificationId;

    @JsonBackReference(value = "from")
    @OneToOne
    @JoinColumn(name = "fromUserId")
    private User from;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "toUserId")
    private User to;

    private String message;
    private boolean isRead;
    private String hrefUrl;
    private Date sendDate;

    public int getNotificationId() {
        return notificationId;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public Notification() {
    }

    public Notification(User from, User to, String message) {
        super();
        this.from = from;
        this.to = to;
        this.message = message;
        this.isRead = false;
        this.sendDate = new Date();
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setHrefUrl(String hrefUrl) {
        this.hrefUrl = hrefUrl;
    }

    public String getHrefUrl() {
        return hrefUrl;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        returnString.append("Notification:{");
        if (from != null){ returnString.append(from.getFirstName()).append("\n");}
        if (to != null){
            returnString.append(to.getFirstName()).append("\n");}
        returnString.append(message).append("\n");
        returnString.append(isRead);

        return returnString.toString();
    }
}
