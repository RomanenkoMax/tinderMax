package ua.danit.model;

import java.util.UUID;

public class Chat {

    private Integer chatId;
    private Long time;
    String messageIn;
    String messageOut;

    public Chat() {
        chatId = generateChatId();
        time = System.currentTimeMillis();
        messageOut = "Hi, how are you";
        messageIn = "Hi, bebe";
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getMessageIn() {
        return messageIn;
    }

    public void setMessageIn(String messageIn) {
        this.messageIn = messageIn;
    }

    public String getMessageOut() {
        return messageOut;
    }

    public void setMessageOut(String messageOut) {
        this.messageOut = messageOut;
    }

    private Integer generateChatId() {
        UUID uniqueID = UUID.randomUUID();
        String s = uniqueID.toString().replaceAll("[^0-9]", "");
        return Integer.parseInt(s.substring(0, 8));
    }

}
