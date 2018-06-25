package ua.danit.utils;

import javax.servlet.http.Cookie;

public class GetLoginFromCookie {

    public String getLogin(Cookie[] cookies, String name){
        for (Cookie c : cookies) {
            if (c.getName().equals(name)){
                return c.getValue();
            }
        }
        return null;
    }
}
