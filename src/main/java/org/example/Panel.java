package org.example;

import org.checkerframework.checker.units.qual.C;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Panel extends JPanel {
    private int x;
    private int y;
    private int width;
    private int height;

    private Font font;

    private JLabel title;

    private JLabel totalRequestsNumber;

    private JLabel totalUsersNumber;
    private JLabel mostActiveUserName;
    private JLabel mostPopularActivityName;
    private BufferedImage background;
    private List<JCheckBox> checkBoxes;
    private int counterToThree;
    private boolean canSelectMore;

    private TelegramBotsApi botsApi;

    private boolean isBotInitialized;
    private JLabel apiChooserLabel;
    private List<String> selectedApis;

    private JCheckBox catFactsApi;
    private JCheckBox jokesApi;
    private JCheckBox numbersApi;
    private JCheckBox activitiesApi;
    private JCheckBox randomDogApi;

    private JButton setApi;

    public Panel(int x, int y, int width, int height){
        this.x=x;
        this.y = y;
        this.width=width;
        this.height=height;
        this.setBounds(this.x,this.y,this.width,this.height);
        this.setLayout(null);
        this.checkBoxes = new ArrayList<>();
        this.counterToThree=0;
        this.canSelectMore=true;

        setNewFont();

        setTitle();
        setBlueTitle();

        setStatisticsLabel();
        createBackground();

        totalRequestsNumberLabel();
        totalRequestsLabel();

        totalUsersNumberLabel();
        totalUsersLabel();

        mostActiveUserNameLabel();
        mostActiveUserLabel();

        mostPopularActivityNameLabel();
        mostPopularActivityLabel();

        historyTitle();
        historyActivityArea();

        addApiChooserLabel();
        this.selectedApis = new ArrayList<>();

        catFactsCheckBox();
        jokesCheckBox();
        numbersCheckBox();
        activitiesCheckBox();
        randomDogCheckBox();
        setButton();



    }

    private void setNewFont(){
        try {
            this.font = Font.createFont(Font.TRUETYPE_FONT, new File("res/lilgrotesk-bold.otf")).deriveFont(40f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

    }
    //TODO: This is the general title = telegram bot manager.
    private void setTitle(){
        this.title = new JLabel("Telegram Bot Manager");
        this.title.setBounds(Constants.TITLE_X,Constants.TITLE_Y,Constants.TITLE_WIDTH,Constants.TITLE_HEIGHT);
        this.title.setFont(this.font.deriveFont(50f));
        this.title.setForeground(Color.white);
        this.title.setOpaque(false);
        this.add(this.title);
        this.title.setVisible(true);
    }
    //TODO: Added blue outline to the JLabel to make it look cooler idk
    private void setBlueTitle(){
        JLabel blue = new JLabel("Telegram Bot Manager");
        blue.setBounds(Constants.TITLE_X+1,Constants.TITLE_Y,Constants.TITLE_WIDTH,Constants.TITLE_HEIGHT);
        blue.setFont(this.font.deriveFont(50.1f));
        blue.setForeground(new Color(75,212,255));
        blue.setOpaque(false);
        this.add(blue);
        blue.setVisible(true);
    }

    //TODO: Changed setTitleLabel to setStatisticsLabel since we have a more general title label
    private void setStatisticsLabel(){
        JLabel statisticsLabel=new JLabel("Statistics");
        statisticsLabel.setBounds(Constants.STATISTICS_X,Constants.STATISTICS_Y,Constants.STATISTICS_WIDTH,Constants.STATISTICS_HEIGHT);
        statisticsLabel.setFont(this.font.deriveFont(30f));
        statisticsLabel.setOpaque(false);
        this.add(statisticsLabel);
        statisticsLabel.setVisible(true);
    }

    private void totalRequestsNumberLabel(){
        this.totalRequestsNumber=new JLabel("0");
        this.totalRequestsNumber.setBounds(Constants.FIRST_NUMBER_X,Constants.FIRST_NUMBER_Y,Constants.FIRST_NUMBER_WIDTH,Constants.FIRST_NUMBER_HEIGHT);
        this.totalRequestsNumber.setFont(this.font.deriveFont(Constants.FONT_SIZE));
        this.totalRequestsNumber.setOpaque(false);
        this.add(this.totalRequestsNumber);
        this.totalRequestsNumber.setVisible(true);
    }

    private void totalRequestsLabel(){
        JLabel totalRequests=new JLabel("Total Requests From The Bot: ");
        totalRequests.setBounds(Constants.REQUEST_LABEL_X,Constants.REQUEST_LABEL_Y,Constants.REQUEST_LABEL_WIDTH+10,Constants.REQUEST_LABEL_HEIGHT);
        totalRequests.setFont(this.font.deriveFont(Constants.FONT_SIZE));
        totalRequests.setOpaque(false);
        this.add(totalRequests);
        totalRequests.setVisible(true);
    }

    private void totalUsersNumberLabel(){
        this.totalUsersNumber=new JLabel("0");
        this.totalUsersNumber.setBounds(this.totalRequestsNumber.getX(),this.totalRequestsNumber.getY()+Constants.Y_LABEL_SPACING,this.totalRequestsNumber.getWidth(),this.totalRequestsNumber.getHeight());
        this.totalUsersNumber.setFont(this.font.deriveFont(Constants.FONT_SIZE));
        this.totalUsersNumber.setOpaque(false);
        this.add(this.totalUsersNumber);
        this.totalUsersNumber.setVisible(true);

    }

    private void totalUsersLabel(){
        JLabel totalUsers=new JLabel("Total Users Used The Bot: ");
        totalUsers.setBounds(this.totalUsersNumber.getX()-260,this.totalUsersNumber.getY(),this.totalUsersNumber.getWidth()+Constants.SPACING,this.totalUsersNumber.getHeight());
        totalUsers.setFont(this.font.deriveFont(Constants.FONT_SIZE));
        totalUsers.setOpaque(false);
        this.add(totalUsers);
        totalUsers.setVisible(true);
    }
    private void mostActiveUserNameLabel(){
        this.mostActiveUserName=new JLabel("");
        this.mostActiveUserName.setBounds(this.totalUsersNumber.getX(),this.totalUsersNumber.getY()+Constants.Y_LABEL_SPACING,this.totalUsersNumber.getWidth(),this.totalUsersNumber.getHeight());
        //this.mostActiveUserName.setFont(this.font.deriveFont(Constants.FONT_SIZE));
        this.mostActiveUserName.setFont(new Font("arial",Font.BOLD,(int) Constants.FONT_SIZE));
        this.mostActiveUserName.setOpaque(false);
        this.add(this.mostActiveUserName);
        this.mostActiveUserName.setVisible(true);

    }
    private void mostActiveUserLabel(){
        JLabel mostActiveUser=new JLabel("Most Active User: ");
        mostActiveUser.setBounds(this.totalUsersNumber.getX()-260,this.totalUsersNumber.getY()+Constants.Y_LABEL_SPACING,this.totalUsersNumber.getWidth(),this.totalUsersNumber.getHeight());
        mostActiveUser.setFont(this.font.deriveFont(Constants.FONT_SIZE));
        mostActiveUser.setOpaque(false);
        this.add(mostActiveUser);
        mostActiveUser.setVisible(true);

    }
    private void mostPopularActivityNameLabel(){
        this.mostPopularActivityName=new JLabel();
        this.mostPopularActivityName.setBounds(this.mostActiveUserName.getX(),this.mostActiveUserName.getY()+Constants.Y_LABEL_SPACING,this.mostActiveUserName.getWidth(),this.mostActiveUserName.getHeight());
        this.mostPopularActivityName.setFont(this.font.deriveFont(Constants.FONT_SIZE));
        this.mostPopularActivityName.setOpaque(false);
        this.add(this.mostPopularActivityName);
        this.mostPopularActivityName.setVisible(true);
    }
    private void mostPopularActivityLabel(){
        JLabel mostPopularActivity=new JLabel("Most Popular API: ");
        mostPopularActivity.setBounds(this.mostPopularActivityName.getX()-260,this.mostPopularActivityName.getY(),this.mostPopularActivityName.getWidth(),this.mostPopularActivityName.getHeight());
        mostPopularActivity.setFont(this.font.deriveFont(Constants.FONT_SIZE));
        mostPopularActivity.setOpaque(false);

        this.add(mostPopularActivity);
        mostPopularActivity.setVisible(true);
    }


    private void historyTitle(){
        JLabel historyTitle= new JLabel("Activity History:");
        historyTitle.setBounds(Constants.HISTORY_TITLE_X,Constants.HISTORY_TITLE_Y,Constants.HISTORY_TITLE_WIDTH,Constants.HISTORY_TITLE_HEIGHT);
        historyTitle.setFont(this.font.deriveFont(30f));
        historyTitle.setOpaque(false);
        this.add(historyTitle);
        historyTitle.setVisible(true);

    }

    private void historyActivityArea(){
        JTextArea historyActivity= new JTextArea();
        historyActivity.setBounds(Constants.HISTORY_TITLE_X,Constants.HISTORY_TITLE_Y+80,Constants.HISTORY_TITLE_WIDTH+150,Constants.HISTORY_TITLE_HEIGHT+500);
        historyActivity.setFont(this.font.deriveFont(Constants.FONT_SIZE));
        historyActivity.setOpaque(false);
        this.add(historyActivity);
        historyActivity.setVisible(true);
    }

    private void addApiChooserLabel(){
        this.apiChooserLabel = new JLabel("Choose API:");
        this.apiChooserLabel.setBounds(Constants.API_CHOOSER_LABEL_X,Constants.API_CHOOSER_LABEL_Y,Constants.API_CHOOSER_LABEL_WIDTH,Constants.API_CHOOSER_LABEL_HEIGHT);
        this.apiChooserLabel.setFont(this.font.deriveFont(30f));
        this.apiChooserLabel.setOpaque(false);
        this.add(this.apiChooserLabel);
        this.apiChooserLabel.setVisible(true);
    }

    public void setTotalRequestsNumberText(String text){
        this.totalRequestsNumber.setText(text);
    }
    public void setTotalUsersNumberText(String text){
        this.totalUsersNumber.setText(text);
    }
    public void setMostActiveUserNameText(String text){
        this.mostActiveUserName.setText(text);
    }
    public void setMostPopularActivityName(String text){
        this.mostPopularActivityName.setText(text);
    }


    private void createBackground(){
        try {
            this.background= ImageIO.read(new File("res/backGround.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addApiOption() {
        JLabel jLabel = new JLabel("Choose Api: ");



    }
    private void catFactsCheckBox(){
        this.catFactsApi=new JCheckBox("Cat Facts API");
        this.catFactsApi.setFont(this.font.deriveFont(18f));
        this.catFactsApi.setForeground(Color.BLACK);
        this.catFactsApi.setBounds(Constants.CHECKBOX_X,Constants.CHECKBOX_Y,250,50);
        this.catFactsApi.setBorderPainted(false);
        this.catFactsApi.setContentAreaFilled(false);
        this.catFactsApi.addActionListener(e -> {
            checkCondition(this.catFactsApi,this.catFactsApi.getName());
        });
        this.add(this.catFactsApi);
        this.catFactsApi.setVisible(true);
    }

    private void jokesCheckBox(){
        this.jokesApi=new JCheckBox("Jokes API");
        this.jokesApi.setFont(this.font.deriveFont(18f));
        this.jokesApi.setForeground(Color.BLACK);
        this.jokesApi.setBounds(Constants.CHECKBOX_X,Constants.CHECKBOX_Y+50,250,50);
        this.jokesApi.setBorderPainted(false);
        this.jokesApi.setContentAreaFilled(false);
        this.jokesApi.addActionListener(e -> {
            checkCondition(this.jokesApi,this.jokesApi.getName());
        });
        this.add(this.jokesApi);
        this.jokesApi.setVisible(true);
    }

    private void numbersCheckBox(){
        this.numbersApi=new JCheckBox("Numbers API");
        this.numbersApi.setFont(this.font.deriveFont(18f));
        this.numbersApi.setForeground(Color.BLACK);
        this.numbersApi.setBounds(Constants.CHECKBOX_X,Constants.CHECKBOX_Y+100,250,50);
        this.numbersApi.setBorderPainted(false);
        this.numbersApi.setContentAreaFilled(false);
        this.numbersApi.addActionListener(e -> {
            checkCondition(this.numbersApi,this.numbersApi.getName());
        });
        this.add(this.numbersApi);
        this.numbersApi.setVisible(true);
    }

    private void activitiesCheckBox(){
        this.activitiesApi=new JCheckBox("Activities API");
        this.activitiesApi.setFont(this.font.deriveFont(18f));
        this.activitiesApi.setForeground(Color.BLACK);
        this.activitiesApi.setBounds(Constants.CHECKBOX_X,Constants.CHECKBOX_Y+150,250,50);
        this.activitiesApi.setBorderPainted(false);
        this.activitiesApi.setContentAreaFilled(false);
        this.activitiesApi.addActionListener(e -> {
            checkCondition(this.activitiesApi,this.activitiesApi.getName());
        });
        this.add(this.activitiesApi);
        this.activitiesApi.setVisible(true);
    }
    private void randomDogCheckBox(){
        this.randomDogApi=new JCheckBox("Random Dog Images API");
        this.randomDogApi.setFont(this.font.deriveFont(18f));
        this.randomDogApi.setForeground(Color.BLACK);
        this.randomDogApi.setBounds(Constants.CHECKBOX_X,Constants.CHECKBOX_Y+200,250,50);
        this.randomDogApi.setBorderPainted(false);
        this.randomDogApi.setContentAreaFilled(false);
        this.randomDogApi.addActionListener(e -> {
            checkCondition(this.randomDogApi,this.randomDogApi.getName());
        });
        this.add(this.randomDogApi);
        this.randomDogApi.setVisible(true);
    }

    private void setButton() {
        this.setApi = new JButton("Set API!");
        this.setApi.setBounds(this.randomDogApi.getX()+125,this.randomDogApi.getY()+60,100,60);
        this.setApi.setFont(this.font.deriveFont(18f));
        this.setApi.setForeground(Color.BLACK);
        this.add(this.setApi);
        this.setApi.addActionListener(event -> {
            initializeBot();
            this.isBotInitialized = true;

        });
    }





    private void checkOptions(){
        new Thread(()->{
            int i =0;
            while (true) {
                actionPerformed();
            }
        }).start();
    }

    private void mergeJCheckBox(JCheckBox catFactsApi,JCheckBox jokesApi,JCheckBox numbersApi,JCheckBox activitiesApi,JCheckBox randomDogApi){
        this.checkBoxes= Arrays.asList(catFactsApi,jokesApi,numbersApi,activitiesApi,randomDogApi);
    }

    private void checkCondition(JCheckBox checkBox,String name){
        if (checkBox.isSelected()){
            this.counterToThree++;
            this.selectedApis.add(name);
            if (this.counterToThree==4){
                this.canSelectMore = false;
                checkBox.setSelected(false);
                System.out.println("fucking");
            }
        }
        if (!checkBox.isSelected()){
            this.counterToThree--;
        }
    }

    private void actionPerformed() {
        if (this.catFactsApi.isSelected()) {
            selectedApis.add("Cat Facts");
        }
        if (this.jokesApi.isSelected()) {
            selectedApis.add("Jokes");
        }
        if (this.numbersApi.isSelected()) {
            selectedApis.add("Numbers");
        }
        if (this.activitiesApi.isSelected()) {
            selectedApis.add("Activities");
        }
        if (this.randomDogApi.isSelected()) {
            selectedApis.add("Random Dog Image");
        }

        if (selectedApis.size() >= 3) {
            String message = selectedApis.stream()
                    .collect(Collectors.joining("\n", "", "\n-----------------\nTotal: " + selectedApis.size()));
            JOptionPane.showMessageDialog(this, message);

        }

    }

    private List<String> getSelectedApis() {
        return this.selectedApis;
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        //graphics2D.setFont(this.font);
        graphics2D.drawImage(this.background,0,0,this.background.getWidth()-1000,this.background.getHeight()-1000,null);
        graphics2D.setPaint(new Color(255,255,255,150));
        RoundRectangle2D historyActivityBack = new RoundRectangle2D.Double(Constants.HISTORY_TITLE_X-10,Constants.HISTORY_TITLE_Y,Constants.HISTORY_TITLE_WIDTH+150,Constants.HISTORY_TITLE_HEIGHT+630,50,50);
        graphics2D.fill(historyActivityBack);
        RoundRectangle2D statisticsBack = new RoundRectangle2D.Double(Constants.STATISTICS_X-10,Constants.STATISTICS_Y+25,Constants.STATISTICS_WIDTH-140,Constants.STATISTICS_HEIGHT+180,50,50);
        graphics2D.fill(statisticsBack);
        RoundRectangle2D apiChooserBack = new RoundRectangle2D.Double(Constants.API_CHOOSER_LABEL_X-10,Constants.API_CHOOSER_LABEL_Y+25,Constants.API_CHOOSER_LABEL_WIDTH+210,Constants.API_CHOOSER_LABEL_HEIGHT+210,50,50);
        graphics2D.fill(apiChooserBack);
    }
}
