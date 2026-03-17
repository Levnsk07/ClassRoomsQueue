import DataBase.Queue;
import DataBase.User;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {


        Queue queue = new Queue();
//        queue.getAll().stream().sorted(Comparator.comparing(User::getPlace)).forEach(System.out::println);
        queue.getAll().stream().forEach(System.out::println);


    }
}
