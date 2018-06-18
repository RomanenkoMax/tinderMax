package ua.danit.model;

import java.util.UUID;

public class Liked {

    Integer id;
    Integer userId;
    Integer chatId;
    String likeLogin;

    public Liked(Integer userId, Integer chatId, String likeLogin) {
        this.userId = userId;
        this.chatId = chatId;
        this.likeLogin = likeLogin;
        this.id = generateLikedId();
    }

    public String getLikeLogin() {
        return likeLogin;
    }

    public void setLikeLogin(String likeLogin) {
        this.likeLogin = likeLogin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    private Integer generateLikedId() {
        UUID uniqueID = UUID.randomUUID();
        String s = uniqueID.toString().replaceAll("[^0-9]", "");
        return Integer.parseInt(s.substring(0, 8));
    }
}
