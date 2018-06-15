package ua.danit.dao;

import ua.danit.model.Liked;
import ua.danit.utils.DataBaseLiked;
import java.util.HashMap;

public class LikedDAO {

    public HashMap<Integer, Liked> base = new DataBaseLiked().getBaseLiked();
    public int id = base.size();

    public void save(Liked liked) {
        base.put(++id, liked);
    }

    public Liked get (Integer id){
        return base.get(id);
    }

    public void update(Integer id, Liked newLiked){
        base.replace(id, newLiked);
    }

    public void delete(Integer id){
        base.remove(id);
    }

}
