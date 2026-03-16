package DataBase;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Queue implements base {

    String base;
    private Connection conn;

    public Queue() {
        base = createTable();
        try {
            this.conn = DriverManager.getConnection(base);
        } catch (Exception e) {
            System.out.println("Error Connect Table " + e);
        }
    }

    public Queue(String base) {
        try {
            this.conn = DriverManager.getConnection(base);
        } catch (Exception e) {
            System.out.println("Error Connect Table " + e);
        }
        this.base = base;
    }

    private static String createTable() {
        String url = "jdbc:sqlite:User.db";
        String sql = "CREATE TABLE IF NOT EXISTS user(id intager, place intager, name line)";

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

    @Override
    public User[] getAll() {
        return new User[0];
    }

    @Override
    public void add(User user, int id) {

    }

    @Override
    public void remove(int id) {

    }
}
