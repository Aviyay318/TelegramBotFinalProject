package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class TelegramBot  extends TelegramLongPollingBot {
    private List<Long> chatIds;
    private String [] api;
    private ApiManager apiManager;

    public TelegramBot(String [] api){
        this.chatIds = new ArrayList<>();
        this.api = api;
        this.apiManager = new ApiManager();
    }
    @Override
    public String getBotUsername() {
        return "AYRD_Telegram_Bot";
    }

    @Override
    public String getBotToken() {
        return "6210516288:AAFlfGhiWl-Q6z4KADzVToIdLhKK_H7qQqM";
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage= new SendMessage();
        long chatId= getChatID(update);
        sendMessage.setChatId(chatId);
        if (!this.chatIds.contains(chatId)){
            this.chatIds.add(chatId);
            sendMessage.setText("Choose Api: ");
            InlineKeyboardButton sunday= new InlineKeyboardButton(api[0]);
            sunday.setCallbackData(api[0]);
            InlineKeyboardButton monday = new InlineKeyboardButton(api[1]);
            monday.setCallbackData(api[1]);
            InlineKeyboardButton tuesday= new InlineKeyboardButton(api[2]);
            tuesday.setCallbackData(api[2]);
            List<InlineKeyboardButton> topRow = Arrays.asList(sunday,monday, tuesday);
            List<List<InlineKeyboardButton>> keyboard= Arrays.asList(topRow);
            InlineKeyboardMarkup inlineKeyboardMarkup= new InlineKeyboardMarkup();
            inlineKeyboardMarkup.setKeyboard(keyboard);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        } else {
            if(update.getCallbackQuery().getData().equals("Cats")){
                sendMessage.setText(this.apiManager.catsFactApi());
            } else if (update.getCallbackQuery().getData().equals("Jokes")){
//                InlineKeyboardButton programming= new InlineKeyboardButton("Programming");
//                programming.setCallbackData("Programming");
//                InlineKeyboardButton misc = new InlineKeyboardButton("Misc");
//                misc.setCallbackData("Misc");
//                InlineKeyboardButton dark= new InlineKeyboardButton("Dark");
//                dark.setCallbackData("Dark");
//                InlineKeyboardButton pun= new InlineKeyboardButton("Pun");
//                pun.setCallbackData("Pun");
//                InlineKeyboardButton spooky = new InlineKeyboardButton("Spooky");
//                spooky.setCallbackData("Spooky");
//                InlineKeyboardButton christmas = new InlineKeyboardButton("Christmas");
//                christmas.setCallbackData("Christmas");
//                InlineKeyboardButton any = new InlineKeyboardButton("Any");
//                any.setCallbackData("Any");
//                List<InlineKeyboardButton> topRow = Arrays.asList(programming,misc, dark,pun,spooky,christmas,any);
//                List<List<InlineKeyboardButton>> keyboard= Arrays.asList(topRow);
//                InlineKeyboardMarkup inlineKeyboardMarkup= new InlineKeyboardMarkup();
//                inlineKeyboardMarkup.setKeyboard(keyboard);
//                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                sendMessage.setText(this.apiManager.jokeApi("Any"));
            }else {
                sendMessage.setText(this.apiManager.numberApi());
            }
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }


    }
    public long getChatID(Update update){
        long update1=0;
        if (update.getMessage()!=null){
            update1=update.getMessage().getChatId();
        }else {
            update1=update.getCallbackQuery().getMessage().getChatId();
        }
        return update1;
    }
}
