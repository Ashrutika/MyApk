package com.example.shrut.myapk;

/**
 * Created by root1 on 1/10/18.
 */

public class ChatBubble {

    private  String content;
    private boolean isSent;

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

    public ChatBubble(String content, boolean isSent) {
        this.content = content;
        this.isSent = isSent;
    }

}
