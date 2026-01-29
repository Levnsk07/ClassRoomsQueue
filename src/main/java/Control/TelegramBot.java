package Control;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import src.Queue;
import src.User;

import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {

    Queue queue;
    String NAME;
    String API;

    public TelegramBot(Queue queue, String NAME, String API) {
        this.queue = queue;
        this.NAME = NAME;
        this.API = API;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getChatId() + "\t" + update.getMessage().getFrom().getUserName() + "\t" + update.getMessage().getText());
        checkText(update);
    }

    private void checkText(Update update) {
        switch (update.getMessage().getText()) {
            case "Show queue":
                String text = queue.toString();
                if (text == "") {
                    sendTextMessage("Now Queue is empty now", update.getMessage().getChatId());
                    break;
                }
                sendTextMessage("Now Queue is: \n" + text, update.getMessage().getChatId());
                break;
            case "/start":
                sendInLineKeyboard(update.getMessage().getChatId());
                break;

            case "Remove from queue":
                User first = queue.getFirst();
                User removable = queue.removeUserWithId(update.getMessage().getChatId());
                if (first.getTelegramID() == removable.getTelegramID()) {
                    User newFirst = queue.getFirst();
                    sendTextMessage("Now is your turn", newFirst.getTelegramID());
                    System.out.println("new first");
                }
                sendTextMessage("Sure, now queue is:\n" + queue.toString(), update.getMessage().getChatId());
                break;

            case "Add me in queue":
                if (queue.addInQueue(update.getMessage().getFrom().getId(), update.getMessage().getFrom().getUserName())) {
                    sendTextMessage("Sure, now queue is:\n" + queue.toString(), update.getMessage().getChatId());
                } else {
                    sendTextMessage("Nope, you already in queue", update.getMessage().getChatId());
                }
                break;
        }

    }

    public void sendInLineKeyboard(long chatID) {

        SendMessage message = new SendMessage();
        message.setChatId(chatID);
        message.setText("This is start message for spawning keyboard");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        row.add("Show queue");
        row.add("Add me in queue");
        row.add("Remove from queue");

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);

        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendTextMessage(String text, long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return NAME;
    }

    @Override
    public String getBotToken() {
        return API;
    }
}
