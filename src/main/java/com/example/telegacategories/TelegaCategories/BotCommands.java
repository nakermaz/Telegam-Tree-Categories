package com.example.telegacategories.TelegaCategories;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "start bot"),
            new BotCommand("/help", "bot info"),
            new BotCommand("/createCTG", "create category"),
            new BotCommand("/addChildren", "add children")
    );

    String HELP_TEXT = "Это бот для создания дерево категории. " +
            "Вам доступны следующие команды:\n" +
            "/start - запускает работу бота\n" +
            "/help - меню помощи \n" +
            "/createCTG - создание категории \n" +
            "/addChildren - добавление детей к родительской категории \n";


}
