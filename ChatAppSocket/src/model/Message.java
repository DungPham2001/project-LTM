/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Hi
 */
public class Message implements Serializable{
        private static final long serialVersionUID = 4L;

    private int messId;
    private String fromUser;
    private String message;
    private Date timeSend;
    private int groupId;
    public Message() {
    }

    public Message(int messId, String fromUser, String message, int groupId) {
        this.messId = messId;
        this.fromUser = fromUser;
        this.message = message;
        this.groupId = groupId;
    }

    public Message(int messId, String fromUser, String message, Date timeSend, int groupId) {
        this.messId = messId;
        this.fromUser = fromUser;
        this.message = message;
        this.timeSend = timeSend;
        this.groupId = groupId;
    }

    public int getMessId() {
        return messId;
    }

    public void setMessId(int messId) {
        this.messId = messId;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(Date timeSend) {
        this.timeSend = timeSend;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messId=" + messId +
                ", fromUser=" + fromUser +
                ", message='" + message + '\'' +
                ", timeSend=" + timeSend +
                ", groupId=" + groupId +
                '}';
    }
}