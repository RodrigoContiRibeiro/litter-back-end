package com.litter.litter.model;

import java.time.LocalDateTime;

public class MessageFirebase {
    private String content;
    private Long userID;
    private Long groupID;
    private String datetime;

    public MessageFirebase() {
    }

    public MessageFirebase(String content, Long userID, Long groupID, String datetime) {
        this.content = content;
        this.userID = userID;
        this.groupID = groupID;
        this.datetime = datetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getGroupID() {
        return groupID;
    }

    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
