package DataBase;

public class User {
    long id;
    String name;
    int place;



    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(long id, String name, int place) {
        this.id = id;
        this.name = name;
        this.place = place;
    }
}
