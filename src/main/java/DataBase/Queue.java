package DataBase;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Queue implements base {

    String base;
    private Connection conn;

    public Queue() { // constructor without file
        base = createTable();
        try {
            this.conn = DriverManager.getConnection(base);
        } catch (Exception e) {
            System.out.println("Error Connect Table " + e);
        }
    }

    public Queue(String base) { // constructor with existed file
        try {
            this.conn = DriverManager.getConnection(base);
        } catch (Exception e) {
            System.out.println("Error Connect Table " + e);
        }
        this.base = base;
    }

    private static String createTable() {
        String url = "jdbc:sqlite:User.db";
        String sql = "CREATE TABLE IF NOT EXISTS user(id intager, place intager UNIQUE, name line)";

        File file = new File("User.db");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error Create File " + e);
                throw new RuntimeException(e);
            }
        }

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);

        } catch (Exception e) {
            System.out.println("Error Create Table " + e);
        }
        return url;
    }


    @Override
    public User get(int id) {
        return null;
    }


    public User getByPlace(int id) {
        String sql = " SELECT * FROM user WHERE place=?";
        User user;

        String com = "INSERT INTO User(Id,Name) VALUES (?,?)";
//        if (findUserInQueue(id) != null) return false;

        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id); // Put place in sql code

            ResultSet resultSet = preparedStatement.executeQuery(); // get result comnad


            user = new User(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("place"));

        } catch (Exception e) {
            System.out.println("Pizdes suka otkat \n");
            System.out.println(e);
            throw new RuntimeException(e);
        }


        return user;
    }

    @Override
//    public User[] getAll() {
public ArrayList<User> getAll(){
        sortByPlace();
        String sql = "SELECT * FROM user;";
        ArrayList<User> users = new ArrayList<User>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getInt("place")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        User[] res = (User[]) users.toArray();
//        return res;
        return users;
    }

    @Override
    public void add(User user, int id) {
        String sql = "INSERT INTO user (id, place, name) VALUES (?, ?, ?);";

    }

    @Override
    public void remove(int id) {

    }


    public void sortByPlace(){

        String sql = "SELECT * FROM user ORDER BY place;";
        try {
            conn.prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
