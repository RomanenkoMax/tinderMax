package ua.danit.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Chat {

    private Long time;
    private String message;
    private String toLogin;
    private String fromLogin;
    private String normalTime;

    public Chat(String toLogin, String fromLogin) {

        this.time = System.currentTimeMillis();
        this.message = "hi bebe, nice to speak with you";
        this.toLogin = toLogin;
        this.fromLogin = fromLogin;
        this.normalTime = beautyTime(time);
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
    private String beautyTime(Long time) {

        Date date = new Date(time);

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH.mm");

        return format.format(date);
    }
}
