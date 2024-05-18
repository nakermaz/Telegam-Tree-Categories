package com.example.telegacategories.TelegaCategories;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "start bot"),
            new BotCommand("/help", "bot info"),
            new BotCommand("/viewTree", "we get a list of categories"),
            new BotCommand("/addElement", "add element to db"),
            new BotCommand("/removeElement", "delete tree category")
    );

    String HELP_TEXT = "Это бот для создания дерево категории. " +
            "Вам доступны следующие команды:\n" +
            "/start - запускает работу бота\n" +
            "/help - меню помощи \n" +
            "/viewTree - получение списка всех категории в структурированном виде\n" +
            "/addElement - добавление элемента в бд \n" +
            "/removeElement - уделение целого элемента дерева из бд \n ";

}
