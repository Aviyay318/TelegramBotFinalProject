package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
     new Window();
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot(new String[]{"Cats","Jokes","Numbers","AdviceSlip"},Constants.PANEL));
        }catch (TelegramApiException e){
            throw new RuntimeException();
        }
    }
}