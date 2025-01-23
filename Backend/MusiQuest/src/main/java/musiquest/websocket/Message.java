package musiquest.websocket;

import lombok.*;

import javax.persistence.*;

import java.util.Date;

/**
 * Message
 */
@Entity
@Table(name="Messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent")
    private Date sent = new Date();

    public Message() {};
    
    /**
     * Constructor for message
     * @param userName
     * @param content
     */
    public Message(String userName, String content) {
        this.userName = userName;
        this.content = content;
    }
    
    /**
     * Get ID of message
     * @return
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Set the ID of message
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the username of user
     * @return String userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the Username
     * @param String userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the contents of the message
     * @return String content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the contents of the message
     * @param String content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Get the date the message was sent
     * @return Date sent
     */
    public Date getSent() {
        return sent;
    }

    /**
     * Sets the Date a message was sent
     * @param Date sent
     */
    public void setSent(Date sent) {
        this.sent = sent;
    }

}
