package com.example.shrut.myapk;

/**
 * Created by root1 on 1/10/18.
 */

public class ChatBubble {

    private  String content;
    private boolean isSent;
    private String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public void setSent(boolean sent) {
        isSent = sent;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public boolean isSent() {
        return isSent;
    }

    public ChatBubble(String content, boolean isSent,String timestamp) {
        this.content = content;
        this.isSent = isSent;
        this.timestamp=timestamp;
    }

}
