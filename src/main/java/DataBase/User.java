package DataBase;

public class User {
    long id;
    String name;
    int place;



    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public User(long id, String name, int place) {
        this.id = id;
        this.name = name;
        this.place = place;
    }


    @Override
    public String toString(){
//        return String.format("Name: %s \t | \t place: %d \t | \t id: %ld",name,place,id);
        return "Name: "+ name +"\t | \t" + "place: " + place +"\t | \t" + "id: " + id;
    }
}
