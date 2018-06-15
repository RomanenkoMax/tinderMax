package ua.danit.utils;

import ua.danit.model.Liked;

import java.util.HashMap;

public class DataBaseLiked {

    HashMap<Integer, Liked> likedBase = new HashMap<>();

       public HashMap<Integer, Liked> getBaseLiked(){

           return likedBase;
       }

}
