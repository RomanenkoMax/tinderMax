package ua.danit.model;

public class User {
    private String name;
    private  String photo;
    private Integer id;

    public User(String name, String photo, Integer id) {
        this.name = name;
        this.photo = photo;
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
