package ua.danit.dao;


import ua.danit.model.Liked;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikedDAOtoDB extends AbstractDAOtoDB<Liked> {


    @Override
    public void put(Liked liked) {

        String sql = "INSERT INTO public.liked(id, userId, chatId, likeLogin) VALUES(?,?,?,?)";

        try (Connection connection = ConnectionToDB.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, liked.getId());
            statement.setInt(2, liked.getUserId());
            statement.setInt(3, liked.getChatId());
            statement.setString(4, liked.getLikeLogin());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Liked liked) {

        String sql = "UPDATE public.liked SET likeLogin=?, userId=?, chatId=?, WHERE id=?";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(2, liked.getUserId());
            statement.setInt(3, liked.getChatId());
            statement.setInt(4, liked.getId());
            statement.setString(1, liked.getLikeLogin());

            statement.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Override
    public Liked get(Object id) {

        String sql = "SELECT * FROM public.liked WHERE id='" + id + "'";

        try (
                Connection        connection  = ConnectionToDB.getConnection();
                PreparedStatement statement  = connection.prepareStatement(sql);
                ResultSet rSet = statement.executeQuery();
        )
        {
            while ( rSet.next() )
            {
                Integer userid = rSet.getInt("userId");
                Integer chatId = rSet.getInt("chatId");
                String likeLogin = rSet.getString("likeLogin");

                Liked liked = new Liked(userid, chatId, likeLogin);
                liked.setId((Integer) id);

                return liked;
            }
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Object id) {

        String sql = "DELETE FROM public.liked WHERE id=?";

        try (
                Connection connection = ConnectionToDB.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, (Integer) id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Integer> getAllByLogin(String login) {

        List<Integer> likeds = new ArrayList<>();


        String sql = "SELECT DISTINCT userid FROM public.liked WHERE likelogin='" + login + "'";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rSet = statement.executeQuery()) {
            while (rSet.next()) {

//                Integer id = rSet.getInt("id");
                Integer userId = rSet.getInt("userid");
//                Integer chatId = rSet.getInt("chatid");
//                String likeLogin = rSet.getString("likelogin");

//                Liked liked = new Liked(userId, chatId, likeLogin);
//                liked.setId(id);
                likeds.add(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likeds;
    }
}
