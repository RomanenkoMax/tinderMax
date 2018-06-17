package ua.danit.model;

import java.util.UUID;

public class Liked {

    Integer likedId;
    Integer userId;
    Integer chatId;
    String likedLogin;

    public Liked(Integer userId, Integer chatId, String likedLogin) {
        this.userId = userId;
        this.chatId = chatId;
        this.likedLogin = likedLogin;
        this.likedId = genereateLikedId();
    }

    public String getLikedLogin() {
        return likedLogin;
    }

    public void setLikedLogin(String likedLogin) {
        this.likedLogin = likedLogin;
    }

    public Integer getLikedId() {
        return likedId;
    }

    public void setLikedId(Integer likedId) {
        this.likedId = likedId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    private Integer genereateLikedId() {
        UUID uniqueID = UUID.randomUUID();
        String s = uniqueID.toString().replaceAll("[^0-9]", "");
        return Integer.parseInt(s.substring(0, 8));
    }
}
