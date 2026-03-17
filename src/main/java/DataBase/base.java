package DataBase;

import java.util.ArrayList;

public interface base {



//    Get elements
    User get(int id);
//    User[] getAll();
    ArrayList<User> getAll();

//    Add
    void add(User user, int id);

//    Remove
    void remove(int id);


}
