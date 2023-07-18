package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TelegramBot  extends TelegramLongPollingBot {
    private Map<Long,String> chatIds;
    private List<Message> messageHistory;
    private HashMap<Long,Integer> mostUserP;
    private List<String> api;
    private ApiManager apiManager;
    private HashMap<String,Integer> counterMap;
    private List<String> historyActivities;
    private Thread chartThread;
    private InteractionChart chart;
    private int counter;
    private Panel panel;

    public TelegramBot(List<String> api,Panel panel){
        this.chatIds = new HashMap<>();
        this.api = api;
        this.apiManager = new ApiManager();
        this.counterMap = new HashMap<>();
        this.counterMap.put("general",0);
        this.counterMap.put("Cat Facts",0);
        this.counterMap.put("Jokes",0);
        this.counterMap.put("Activities",0);
        this.counterMap.put("Numbers",0);
        this.counterMap.put("Random Dog",0);
        this.chart = new InteractionChart(this);
        this.chartThread = new Thread(this.chart);
        this.chartThread.start();
        this.mostUserP = new HashMap<>();
        this.messageHistory = new ArrayList<>();

        this.panel = panel;
        update();
        this.counter = 0;
this.historyActivities =new ArrayList<>();
    }
    public void updateApiList(List<String> api){
        this.api = api;
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
        SendPhoto sendPhoto = new SendPhoto();
        long chatId= getChatID(update);
        sendMessage.setChatId(chatId);
        sendPhoto.setChatId(chatId);
//        int sum;
//        try {
//            sum = this.mostUserP.get(chatId) + 1;
//        }catch (Exception e){
//            sum = 0;
//        }
//        long timestamp = update.getMessage().getDate(); // הערך בתוך timestamp באופן Unix timestamp
//        Instant instant = Instant.ofEpochSecond(timestamp); // המרת הערך לאובייקט Instant
//        LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()); // המרת הערך לתאריך מקומי באזור המערכת
//        System.out.println(date); // הדפסת התאריך בפורמט של LocalDateTime
//        String name = update.getMessage().getFrom().getFirstName();
//        LocalDateTime timestamp = LocalDateTime.ofInstant(
//                Instant.ofEpochSecond(update.getMessage().getDate()),
//                ZoneId.systemDefault()
//        );
//        Message message = new Message(chatId, timestamp, name);
//        messageHistory.add(message);
//      //  System.out.println(getRecentInteractions());

//
        //      this.mostUserP.put(chatId,sum);
        if (this.chatIds.containsKey(chatId)){
            int count = this.mostUserP.get(chatId);
            count++;
            this.mostUserP.put(chatId,count);
        }else {
            this.mostUserP.put(chatId,1);
        }
        if (update.getMessage()!=null){
            counter = this.counterMap.get("general")+1;
            this.counterMap.put("general",counter);
            this.chatIds.put(chatId,update.getMessage().getFrom().getFirstName());
            System.out.println(this.chatIds);
             sendMessage.setText("Choose Api: ");

            List<InlineKeyboardButton> buttons = IntStream.range(0, this.api.size())
                    .mapToObj(i -> {
                        InlineKeyboardButton button = new InlineKeyboardButton(this.api.get(i));
                        button.setCallbackData(this.api.get(i));
                        return button;
                    })
                    .collect(Collectors.toList());

            List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
            keyboard.add(buttons);
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            inlineKeyboardMarkup.setKeyboard(keyboard);

//            List<KeyboardButton> buttons = this.api.stream().map(KeyboardButton::new).toList();
//            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//            List<KeyboardRow> keyboardRowsList = new ArrayList<>();
//
//            KeyboardRow keyboardRow = new KeyboardRow();
//            KeyboardRow keyboardRow1 = new KeyboardRow();
//            KeyboardRow keyboardRow2 = new KeyboardRow();
//
//            keyboardRow.add(buttons.get(0));
//            keyboardRow1.add(buttons.get(1));
//            keyboardRow2.add(buttons.get(2));
//
//            keyboardRowsList.add(keyboardRow);
//            keyboardRowsList.add(keyboardRow1);
//            keyboardRowsList.add(keyboardRow2);
//
//            replyKeyboardMarkup.setKeyboard(keyboardRowsList);
//
//            sendMessage.setReplyMarkup(replyKeyboardMarkup);
//            sendPhoto.setReplyMarkup(replyKeyboardMarkup);

            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
        }else {
            if(update.getCallbackQuery().getData().equals("Cat Facts")){
                counter = this.counterMap.get("Cat Facts")+1;
                this.counterMap.put("Cat Facts",counter);
                sendMessage.setText(this.apiManager.catsFactApi());
            } else if (update.getCallbackQuery().getData().equals("Jokes")){
                counter = this.counterMap.get("Jokes")+1;
                this.counterMap.put("Jokes",counter);
                sendMessage.setText(this.apiManager.jokeApi("Any"));
            } else if (update.getCallbackQuery().getData().equals("Activities")){
                counter = this.counterMap.get("Activities")+1;
                this.counterMap.put("Activities",counter);
                sendMessage.setText(this.apiManager.activitiesApi());
            }else if (update.getCallbackQuery().getData().equals("Random Dog")){
                counter = this.counterMap.get("Random Dog")+1;
                this.counterMap.put("Random Dog",counter);
                this.apiManager.dogApi();
                File file = new File("res/dog/randomDog.jpg");
                System.out.println(file.getName());
                InputFile randomAhhDog = new InputFile(file);
                sendPhoto.setPhoto(randomAhhDog);
            }
            else {
                counter = this.counterMap.get("Numbers")+1;
                this.counterMap.put("Numbers",counter);
                sendMessage.setText(this.apiManager.numberApi());
            }

        }
        System.out.println(this.counterMap);

        try {
            execute(sendMessage);
            System.out.println(sendMessage);
            getRecentInteractions("Name: "+this.chatIds.get(chatId)+"\nAPI Used: "+update.getCallbackQuery().getData()+ ", Date: ");


        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        finally {
            try {
                execute(sendPhoto);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
            System.out.println(sendPhoto);
        }


    }
    public List<Message> getRecentInteractions() {
        int endIndex = Math.min(10, messageHistory.size());
        return messageHistory.subList(messageHistory.size() - endIndex, messageHistory.size());
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
                this.panel.setTextHistoryArea(getHistoryText());
            }

        }).start();
    }
    private  Long get(){
        return new HashMap<>(this.mostUserP).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(0L);
    }
    private String set(){
        if(this.counterMap.values().stream().reduce(Integer::sum).orElse(0)==0){
            return  "No activity found";
        } else {
            return counterMap.entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue()).filter(stringIntegerEntry -> !stringIntegerEntry.getKey().equals("general"))
                    .map(Map.Entry::getKey)
                    .orElse("No activity found");
        }
    }
    public void getRecentInteractions(String info) {
        if (this.historyActivities.size() >= 10) {
            this.historyActivities.remove(0);
        }
        this.historyActivities.add(info);
    }








    private String getHistoryText(){
        String text="";
        int index = 1;
        for (String i: this.historyActivities ) {
                text+=index+") "+i+"\n\n";
                index++;

        }
        return text;
    }


    public void initialUsers() {
        this.chatIds.clear();
    }

    public HashMap<String, Integer> getCounterMap() {
        return counterMap;
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