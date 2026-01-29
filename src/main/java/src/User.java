package src;

public class User {
    long telegramID;
    String name;
    //private Boolean isInQueue = false;

    public User(long telegramID, String name) {
        this.telegramID = telegramID;
        this.name = name;
    }

    public User() {}

    public void setTelegramID(long telegramID) {
        this.telegramID = telegramID;

    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTelegramID() {
        return telegramID;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Id is: "+telegramID +" \t name is: "+ name;
    }
}
