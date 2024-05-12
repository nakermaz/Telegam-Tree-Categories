package com.example.telegacategories.TelegaCategories;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TelegaCategoriesBot extends TelegramLongPollingBot {
    private final BotConfig config;

    @Autowired
    public TelegaCategoriesBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken(){
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String memberName = update.getMessage().getFrom().getFirstName();

            switch (messageText){
                case "/start":
                    startBot(chatId, memberName, messageText);
                    break;
                default: startBot(chatId, memberName, messageText);

            }
        }
    }

    public void startBot(long chatId, String userName, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Иди нахуй " + userName);

        try {
            execute(message);
            System.out.println("Отправьте еще раз " + userName + " Сообщения: " + messageText);
        } catch (TelegramApiException e){
            System.out.println(e.getMessage());
        }
    }
}
