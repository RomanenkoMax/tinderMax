package ua.danit.dao;

import ua.danit.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOtoDB extends AbstractDAOtoDB<User> {
    @Override
    public void put(User user) {

        String sql = "INSERT INTO max_users(login, pass, photo) VALUES(?,?,?)";

        try (Connection connection = ConnectionToDB.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhoto());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void delete(Object id) {

        String sql = "DELETE FROM max_users WHERE login=?";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, (Integer) id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAll() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM max_users";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rSet = statement.executeQuery()) {
            while (rSet.next()) {
                String login = rSet.getString("login");
                String photo = rSet.getString("photo");
                int id = rSet.getInt("id");
                String password = rSet.getString("pass");
                Boolean liked = rSet.getBoolean("liked");


                User user = new User(login, password, photo);
                user.setId(id);
                user.setLiked(liked);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getById(int id) {


        String sql = "SELECT DISTINCT * FROM max_users WHERE id='" + id + "'";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rSet = statement.executeQuery()
        ) {
            while (rSet.next()) {

                String password = rSet.getString("pass");
                String login = rSet.getString("login");
                String photo = rSet.getString("photo");
                Boolean liked = rSet.getBoolean("liked");

                User user = new User(login, password, photo);
                user.setId(id);
                user.setLiked(liked);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public User getUserByLoginAndPassword(String login, String password) {
        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM max_users WHERE login=? AND pass=?");
        ) {
            statement.setString(1, login);
            statement.setString(2, password);

            statement.execute();

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("pass");
        String photo = resultSet.getString("photo");
        Boolean liked = resultSet.getBoolean("liked");


        User user = new User(login, password, photo);
        user.setId(id);
        user.setLiked(liked);

        return user;
    }

    public void setLiked(int userId, boolean like) {

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE max_users SET liked=? WHERE id=?");
        ) {
            statement.setBoolean(1, like);
            statement.setInt(2, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User getNotLikedUser() {
        try (
                Connection connection = ConnectionToDB.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM max_users WHERE liked IS NULL LIMIT 1");

            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> getLikedUsers() {
        List<User> users = new ArrayList<>();

        try (
                Connection connection = ConnectionToDB.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM max_users WHERE liked IS TRUE");

            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void updateLike() {

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE max_users SET liked=NULL")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
