package ua.danit.dao;

import ua.danit.model.User;
import ua.danit.utils.DataBaseUser;

import java.util.HashMap;

public class UserDAO {

    public HashMap<Integer, User> base = new DataBaseUser().getBaseUser();
    public int id = base.size();

    public void save(User user) {
        base.put(++id, user);
    }

    public User get (Integer id){
        return base.get(id);
    }

    public void update(Integer id, User newUser){
        base.replace(id, newUser);
    }

    public void delete(Integer id){
        base.remove(id);
    }
}
