package com.example.telegacategories.TelegaCategories;

import com.example.telegacategories.TelegaCategories.service.CategoryService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TelegaCategoriesBot extends TelegramLongPollingBot implements BotCommands {
    private final BotConfig config;
    private final CategoryService categoryService;

    @Autowired
    public TelegaCategoriesBot(BotConfig config, CategoryService categoryService) {
        this.config = config;
        this.categoryService = categoryService;
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e ){
            System.out.println(e.getMessage());
        }
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
    public void onUpdateReceived(@NotNull Update update) {
        long chatId = 0;
        long userId = 0;
        String userName = null;
        String receivedMessage;

        if (update.hasMessage()){
            chatId = update.getMessage().getChatId();
            userId = update.getMessage().getFrom().getId();
            userName = update.getMessage().getFrom().getFirstName();

            if (update.getMessage().hasText()){
                receivedMessage = update.getMessage().getText();
                botAnswerUtils(receivedMessage, chatId, userName);
            }
        } else if (update.hasCallbackQuery()){
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userId = update.getCallbackQuery().getFrom().getId();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            receivedMessage = update.getCallbackQuery().getData();

            botAnswerUtils(receivedMessage, chatId, userName);
        }
    }

    private void botAnswerUtils(String receivedMessage, long chatId, String username){

        switch(receivedMessage){
            case "/start":
                startBot(chatId, username);
                break;
            case "/help":
                sendHelpText(chatId, HELP_TEXT);
                break;
            case "/createCTG":
                createCTG(chatId, username);
                break;
            default: break;
        }

    }

    public void startBot(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Привет! " + userName + " Я TelegaCategories.");
        message.setReplyMarkup(Buttons.inlineMarkup());

        try {
            execute(message);
            System.out.println("Ответ отправлен");
        } catch (TelegramApiException e){
            System.out.println(e.getMessage());
        }
    }

    public void sendHelpText(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        try {
            execute(message);
            System.out.println("Ответ отправлен");
        } catch (TelegramApiException e){
            System.out.println(e.getMessage());
        }
    }

    public void createCTG(long chatId, String username){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Хорошо, " + username + "вот данные дерева: \n"
                + categoryService.getCategories());

        try {
            execute(message);
            System.out.println("деверо отправлен");
        } catch (TelegramApiException e){
            System.out.println(e.getMessage());
        }
    }
}
