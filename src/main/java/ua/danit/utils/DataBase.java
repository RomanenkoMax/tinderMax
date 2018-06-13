package ua.danit.utils;

import ua.danit.model.User;

import java.util.HashMap;

public class DataBase {

    public HashMap<Integer, User> getFromBase() {
        return new HashMap<Integer, User>(){{
            put(1, new User("Monica", "https://images.pexels.com/photos/160699/girl-dandelion-yellow-flowers-160699.jpeg?auto=compress&cs=tinysrgb&h=350", 1));
            put(2, new User("Jessy", "https://images.pexels.com/photos/415829/pexels-photo-415829.jpeg?auto=compress&cs=tinysrgb&h=350", 2));
            put(3, new User("Marta", "https://images.pexels.com/photos/720116/pexels-photo-720116.jpeg?auto=compress&cs=tinysrgb&h=350", 3));
            put(4, new User("Helen", "https://images.pexels.com/photos/264172/pexels-photo-264172.jpeg?auto=compress&cs=tinysrgb&h=350", 4));
            put(5, new User("Klara", "https://images.pexels.com/photos/573299/pexels-photo-573299.jpeg?auto=compress&cs=tinysrgb&h=350", 5));
        }};
    }


}
