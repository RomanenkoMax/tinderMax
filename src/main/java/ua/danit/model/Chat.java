package ua.danit.model;

import java.util.UUID;

public class Chat {

    private Integer chatId;
    private Long time;
    private String message;
    private String toLogin;
    private String fromLogin;

    public Chat(String toLogin, String fromLogin) {
        this.chatId = generateChatId();
        this.time = System.currentTimeMillis();
        this.message = "hi bebe, nice to speak with you";
        this.toLogin = toLogin;
        this.fromLogin = fromLogin;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToLogin() {
        return toLogin;
    }

    public void setToLogin(String toLogin) {
        this.toLogin = toLogin;
    }

    public String getFromLogin() {
        return fromLogin;
    }

    public void setFromLogin(String fromLogin) {
        this.fromLogin = fromLogin;
    }

    private Integer generateChatId() {
        UUID uniqueID = UUID.randomUUID();
        String s = uniqueID.toString().replaceAll("[^0-9]", "");
        return Integer.parseInt(s.substring(0, 8));
    }

}
