package com.example.telegacategories.TelegaCategories;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class Buttons {
    private static final InlineKeyboardButton START_BUTTON = new InlineKeyboardButton("Start");
    private static final InlineKeyboardButton HELP_BUTTON = new InlineKeyboardButton("Help");
    private static final InlineKeyboardButton CREATE_CTG_BUTTON = new InlineKeyboardButton("View Tree");
    private static final InlineKeyboardButton ADD_CHILDREN_BUTTON = new InlineKeyboardButton("Add Element");

    public static InlineKeyboardMarkup inlineMarkup(){
        START_BUTTON.setCallbackData("/start");
        HELP_BUTTON.setCallbackData("/help");

        CREATE_CTG_BUTTON.setCallbackData("/viewTree");

        List<InlineKeyboardButton> rowInline = List.of(START_BUTTON, HELP_BUTTON);
        List<InlineKeyboardButton> rowInline2 = List.of(CREATE_CTG_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline, rowInline2);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }


}
