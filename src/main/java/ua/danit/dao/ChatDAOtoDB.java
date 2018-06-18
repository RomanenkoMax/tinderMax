package ua.danit.dao;

import com.google.common.io.CharStreams;
import ua.danit.model.Chat;
import ua.danit.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatDAOtoDB extends AbstractDAOtoDB<Chat>{

    @Override
    public void put(Chat chat) {

        String sql = "INSERT INTO public.chat(chatId, time, messageIn, messageOut) VALUES(?,?,?,?)";

        try (Connection connection = ConnectionToDB.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, chat.getChatId());
            statement.setLong(2, chat.getTime());
            statement.setString(3, chat.getMessageIn());
            statement.setString(4, chat.getMessageOut());


            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Chat chat) {

        String sql = "UPDATE public.chat SET time=?, messageIn=?, messageOut=? WHERE chatId=?";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(4, chat.getChatId());
            statement.setLong(1, chat.getTime());
            statement.setString(2, chat.getMessageIn());
            statement.setString(3, chat.getMessageOut());


            statement.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();

        }


    }

    @Override
    public Chat get(Object chatId) {

        Chat chat = new Chat();

        String sql = "SELECT * FROM public.chat WHERE chatid='" + chatId + "'";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rSet = statement.executeQuery();
        ) {
            while (rSet.next()) {
                chat.setChatId(rSet.getInt("chatid"));
                chat.setTime(rSet.getLong("time"));
                chat.setMessageIn(rSet.getString("messagein"));
                chat.setMessageOut(rSet.getString("messageout"));

                return chat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Object chatId) {

        String sql = "DELETE FROM public.chat WHERE chatid=?";

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
}
