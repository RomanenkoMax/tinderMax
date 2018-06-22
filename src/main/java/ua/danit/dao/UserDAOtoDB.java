package ua.danit.dao;

import ua.danit.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOtoDB extends AbstractDAOtoDB<User> {
    @Override
    public void put(User user) {

        String sql = "INSERT INTO public.user(name, photo, id, login, password) VALUES(?,?,?,?,?)";

        try (Connection connection = ConnectionToDB.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(3, user.getId());
            statement.setString(1, user.getName());
            statement.setString(2, user.getPhoto());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {

        String sql = "UPDATE public.user SET photo=?, name=?, id=?, password=? WHERE login=?";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(3, user.getId());
            statement.setString(1, user.getPhoto());
            statement.setString(2, user.getName());
            statement.setString(5, user.getLogin());
            statement.setString(4, user.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Override
    public User get(Object login) {


        String sql = "SELECT * FROM public.user WHERE login='" + login + "'";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rSet = statement.executeQuery();
        ) {
            while (rSet.next()) {
                Integer id = rSet.getInt("id");
                String password = rSet.getString("password");
                String name = rSet.getString("name");
                String photo = rSet.getString("photo");

                User user = new User(name, photo, (String) login, password);
                user.setId(id);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Object login) {

        String sql = "DELETE FROM public.user WHERE login=?";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, (String) login);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAll() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM public.user";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rSet = statement.executeQuery()) {
            while (rSet.next()) {
                String name = rSet.getString("name");
                String photo = rSet.getString("photo");
                Integer id = rSet.getInt("id");
                String login = rSet.getString("login");
                String password = rSet.getString("password");

                User user = new User(name, photo, login, password);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getById(Object id) {


        String sql = "SELECT DISTINCT * FROM public.user WHERE id='" + id + "'";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rSet = statement.executeQuery()
        ) {
            while (rSet.next()) {

                String password = rSet.getString("password");
                String name = rSet.getString("name");
                String photo = rSet.getString("photo");
                String login = rSet.getString("login");

                User user = new User(name, photo, login, password);
                user.setId((Integer) id);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
