package src;

import Control.TelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    //    private static final String DB_URL = "jdbc:sqlite:src/main/java/src/User.db";
    private static final String DB_URL = "jdbc:sqlite:User.db";

    public static void main(String[] args) throws TelegramApiException {

        String NAME = "";
        String API = "";

            // Reading setting for bot from file
        try (BufferedReader br = new BufferedReader(new FileReader("keys.txt"))) {
            NAME = br.readLine();
            API = br.readLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

            // Creating and starting bot
        TelegramBot bot = new TelegramBot(new Queue(DB_URL),NAME,API);
        TelegramBotsApi telegramBotsApi = null;

        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        telegramBotsApi.registerBot(bot);
    }
}

