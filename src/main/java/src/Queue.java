package src;

import java.sql.*;

public class Queue {

    Connection connection;

    public Queue(String connect_URL) {
        try {
            connection = DriverManager.getConnection(connect_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public User findUserInQueue(long telegramID) {
        User user = new User();
        String com = "SELECT id,name from User WHERE id=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(com);
            preparedStatement.setLong(1, telegramID);
            ResultSet res = preparedStatement.executeQuery();
            long id = res.getLong("id");
            if (id == 0) {
                return null;
            }
            user.setTelegramID(id);
            user.setName(res.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return user;
    }

    public boolean addInQueue(long id, String name) {
        String com = "INSERT INTO User(Id,Name) VALUES (?,?)";
        if (findUserInQueue(id) != null) return false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(com); 
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public User getFirst() {
        String com = "SELECT id,name FROM User LIMIT 1";
        User user = new User();
        try {
            System.out.println(toString());
            PreparedStatement preparedStatement = connection.prepareStatement(com);
            ResultSet res = preparedStatement.executeQuery();

            user.setTelegramID(res.getLong("id"));
            user.setName(res.getString("name"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    public User removeUserWithId(Long id) {
        String com = "DELETE FROM User WHERE id=?";
        User user = findUserInQueue(id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(com);     // получает состояние, вставляет команду и на месте ? ставить данные
            preparedStatement.setLong(1, id);
            int res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    @Override
    public String toString() {
        String sql = "SELECT Name FROM User";
        String result = "";
        int count = 1;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("Name");
                    result += count + ". "  + name + "\n";
                    count++;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
