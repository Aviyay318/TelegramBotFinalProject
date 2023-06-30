package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TelegramBot  extends TelegramLongPollingBot {
    private Map<Long,String> chatIds;
    private HashMap<Long,Integer> mostUserP;
    private String [] api;
    private ApiManager apiManager;
    private HashMap<String,Integer> counterMap;
    private int counter;
    private Panel panel;

    public TelegramBot(String [] api,Panel panel){
        this.chatIds = new HashMap<>();
        this.api = api;
        this.apiManager = new ApiManager();
        this.counterMap = new HashMap<>();
        this.counterMap.put("general",0);
        this.counterMap.put("Cats",0);
        this.counterMap.put("Jokes",0);
        this.counterMap.put("Activities",0);
        this.counterMap.put("Numbers",0);
        this.counterMap.put("RandomDog",0);
        this.mostUserP = new HashMap<>();
       // this.counterMap.put("general",0);
this.panel = panel;
        update();


        this.counter = 0;
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
       int counter = 0;
        SendMessage sendMessage= new SendMessage();
        long chatId= getChatID(update);
       // System.out.println(update.getMessage().getFrom().getFirstName() );
        sendMessage.setChatId(chatId);
        int sum;
        try {
             sum = this.mostUserP.get(chatId) + 1;
        }catch (Exception e){
            sum =0;
        }

        this.mostUserP.put(chatId,sum);
        if (!this.chatIds.containsKey(chatId)){
            counter = this.counterMap.get("general")+1;
            this.counterMap.put("general",counter);
            this.chatIds.put(chatId,update.getMessage().getFrom().getFirstName() );
            sendMessage.setText("Choose Api: ");
            List<String> apiList = Arrays.asList(api);
            List<InlineKeyboardButton> buttons = IntStream.range(0, 5)
                    .mapToObj(i -> {
                        InlineKeyboardButton button = new InlineKeyboardButton(apiList.get(i));
                        button.setCallbackData(apiList.get(i));
                        return button;
                    })
                    .collect(Collectors.toList());

            List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
            keyboard.add(buttons);
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            inlineKeyboardMarkup.setKeyboard(keyboard);

            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            sendPhoto.setReplyMarkup(inlineKeyboardMarkup);

        } else {
            if(update.getCallbackQuery().getData().equals("Cats")){
                counter = this.counterMap.get("Cats")+1;
                this.counterMap.put("Cats",counter);
                sendMessage.setText(this.apiManager.catsFactApi());
            } else if (update.getCallbackQuery().getData().equals("Jokes")){
                counter = this.counterMap.get("Jokes")+1;
                this.counterMap.put("Jokes",counter);
                sendMessage.setText(this.apiManager.jokeApi("Any"));
            } else if (update.getCallbackQuery().getData().equals("Activities")){
                counter = this.counterMap.get("Activities")+1;
                this.counterMap.put("Activities",counter);
                sendMessage.setText(this.apiManager.activitiesApi());
            }else if (update.getCallbackQuery().getData().equals("RandomDog")){
                counter = this.counterMap.get("RandomDog")+1;
                this.counterMap.put("RandomDog",counter);
                this.apiManager.dogApi();
                File file = new File("res/dog/randomDog.jpg");
                InputFile randomAhhDog = new InputFile(file);
                sendPhoto.setPhoto(randomAhhDog);
            }
            else {
                counter = this.counterMap.get("Numbers")+1;
                this.counterMap.put("Numbers",counter);
                sendMessage.setText(this.apiManager.numberApi());
            }
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        System.out.println(this.counterMap);
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
    public void update(){
        new Thread(()->{
            while (true){
                this.panel.setTotalRequestsNumberText(String.valueOf(this.counterMap.values().stream().reduce(Integer::sum).orElse(0)));
                this.panel.setMostPopularActivityName(set());
                this.panel.setTotalUsersNumberText(String.valueOf(this.chatIds.size()));
                this.panel.setMostActiveUserNameText(this.chatIds.get(get()));
            }

            }).start();


    }
    private  Long get(){
        return new HashMap<>(this.mostUserP).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(Long.valueOf(0));
    }
    private String set(){
        if(this.counterMap.values().stream().reduce(Integer::sum).orElse(0)==0){
            return  "No activity found";
        } else {
            return counterMap.entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("No activity found");
        }
    }
}
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