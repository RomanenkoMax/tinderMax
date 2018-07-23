package ua.danit.dao;

import com.google.common.io.CharStreams;
import ua.danit.model.Chat;
import ua.danit.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatDAOtoDB extends AbstractDAOtoDB<Chat> {

    @Override
    public void put(Chat chat) {

        String sql = "INSERT INTO max_chat(time, message, toid, fromid) VALUES(?,?,?,?)";

        try (Connection connection = ConnectionToDB.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setLong(1, chat.getTime());
            statement.setString(2, chat.getMessage());
            statement.setString(3, chat.getToLogin());
            statement.setString(4, chat.getFromLogin());


            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public Chat get(Object chatId) {


        String sql = "SELECT * FROM max_chat WHERE chatid='" + chatId + "'";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rSet = statement.executeQuery();
        ) {
            while (rSet.next()) {

                Long time = rSet.getLong("time");
                String message = rSet.getString("message");
                String toLogin = rSet.getString("tologin");
                String fromLogin = rSet.getString("fromlogin");

                Chat chat = new Chat(toLogin, fromLogin);

                chat.setTime(time);
                chat.setMessage(message);

                return chat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Object chatId) {

        String sql = "DELETE FROM max_chat WHERE chatid=?";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, (Integer) chatId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public HashMap<Long, Chat> getChatByLogins(String fromid, String toid){

        HashMap<Long, Chat> hashMap = new HashMap<>();

        String sql = "SELECT * FROM max_chat WHERE toid='" + toid + "' and fromid='" + fromid + "' ORDER BY time ASC";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rSet = statement.executeQuery();
        ) {
            while (rSet.next()) {

                Long time = rSet.getLong("time");
                String fromLoginDB = rSet.getString("fromid");
                String toLoginDB = rSet.getString("toid");
                String message = rSet.getString("message");

                Chat chat = new Chat(toLoginDB, fromLoginDB);
                chat.setMessage(message);
                chat.setTime(time);

                hashMap.put(time, chat);

            }

            return hashMap;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
}
