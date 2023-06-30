package org.example;

import org.jvnet.hk2.internal.Collector;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
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


    private JLabel totalRequestsNumber;

    private JLabel totalUsersNumber;
    private JLabel mostActiveUserName;
    private JLabel mostPopularActivityName;
    private BufferedImage background;
    private List<JCheckBox> checkBoxes;
    private int counterToThree;
    private boolean canSelectMore;

    private JCheckBox catFactsApi;
    private JCheckBox jokesApi;
    private JCheckBox numbersApi;
    private JCheckBox activitiesApi;
    private JCheckBox randomDogApi;



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

        titleLabel();
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

       catFactsCheckBox();
       jokesCheckBox();
       numbersCheckBox();
       activitiesCheckBox();
       randomDogCheckBox();




    }



    private void titleLabel(){
        JLabel title=new JLabel("Statistics");
        title.setBounds(Constants.TITLE_X,Constants.TITLE_Y,Constants.TITLE_WIDTH,Constants.TITLE_HEIGHT);
        title.setFont(new Font(Constants.FONT, Font.BOLD,40));
        title.setOpaque(false);
        this.add(title);
        title.setVisible(true);
    }

    private void totalRequestsNumberLabel(){
        this.totalRequestsNumber=new JLabel("0");
        this.totalRequestsNumber.setBounds(Constants.FIRST_NUMBER_X,Constants.FIRST_NUMBER_Y,Constants.FIRST_NUMBER_WIDTH,Constants.FIRST_NUMBER_HEIGHT);
        this.totalRequestsNumber.setFont(new Font("Arial",Font.BOLD,Constants.FONT_SIZE));
        this.totalRequestsNumber.setOpaque(false);
        this.add(this.totalRequestsNumber);
        this.totalRequestsNumber.setVisible(true);
    }

    private void totalRequestsLabel(){
        JLabel totalRequests=new JLabel("Total Requests From The Bot: ");
        totalRequests.setBounds(Constants.REQUEST_LABEL_X,Constants.REQUEST_LABEL_Y,Constants.REQUEST_LABEL_WIDTH+10,Constants.REQUEST_LABEL_HEIGHT);
        totalRequests.setFont(new Font(Constants.FONT,Font.BOLD,Constants.FONT_SIZE));
        totalRequests.setOpaque(false);
        this.add(totalRequests);
        totalRequests.setVisible(true);
    }

    private void totalUsersNumberLabel(){
        this.totalUsersNumber=new JLabel("1");
        this.totalUsersNumber.setBounds(this.totalRequestsNumber.getX(),this.totalRequestsNumber.getY()+Constants.Y_LABEL_SPACING,this.totalRequestsNumber.getWidth(),this.totalRequestsNumber.getHeight());
        this.totalUsersNumber.setFont(new Font("Arial", Font.BOLD,Constants.FONT_SIZE));
        this.totalUsersNumber.setOpaque(false);
        this.add(this.totalUsersNumber);
        this.totalUsersNumber.setVisible(true);

    }

    private void totalUsersLabel(){
        JLabel totalUsers=new JLabel("Total Users Used The Bot: ");
        totalUsers.setBounds(this.totalUsersNumber.getX()-260,this.totalUsersNumber.getY(),this.totalUsersNumber.getWidth()+Constants.SPACING,this.totalUsersNumber.getHeight());
        totalUsers.setFont(new Font(Constants.FONT,Font.BOLD,Constants.FONT_SIZE));
        totalUsers.setOpaque(false);
        this.add(totalUsers);
        totalUsers.setVisible(true);
    }
    private void mostActiveUserNameLabel(){
        this.mostActiveUserName=new JLabel("2");
        this.mostActiveUserName.setBounds(this.totalUsersNumber.getX(),this.totalUsersNumber.getY()+Constants.Y_LABEL_SPACING,this.totalUsersNumber.getWidth(),this.totalUsersNumber.getHeight());
        this.mostActiveUserName.setFont(new Font(Constants.FONT,Font.BOLD,Constants.FONT_SIZE));
        this.mostActiveUserName.setOpaque(false);
        this.add(this.mostActiveUserName);
        this.mostActiveUserName.setVisible(true);

    }
    private void mostActiveUserLabel(){
        JLabel mostActiveUser=new JLabel("Most Active User: ");
        mostActiveUser.setBounds(this.totalUsersNumber.getX()-260,this.totalUsersNumber.getY()+Constants.Y_LABEL_SPACING,this.totalUsersNumber.getWidth(),this.totalUsersNumber.getHeight());
        mostActiveUser.setFont(new Font(Constants.FONT, Font.BOLD,Constants.FONT_SIZE));
        mostActiveUser.setOpaque(false);
        this.add(mostActiveUser);
        mostActiveUser.setVisible(true);

    }
    private void mostPopularActivityNameLabel(){
        this.mostPopularActivityName=new JLabel("3");
        this.mostPopularActivityName.setBounds(this.mostActiveUserName.getX(),this.mostActiveUserName.getY()+Constants.Y_LABEL_SPACING,this.mostActiveUserName.getWidth(),this.mostActiveUserName.getHeight());
        this.mostPopularActivityName.setFont(new Font(Constants.FONT,Font.BOLD,Constants.FONT_SIZE));
        this.mostPopularActivityName.setOpaque(false);
        this.add(this.mostPopularActivityName);
        this.mostPopularActivityName.setVisible(true);
    }
    private void mostPopularActivityLabel(){
        JLabel mostPopularActivity=new JLabel("Most Popular API: ");
        mostPopularActivity.setBounds(this.mostPopularActivityName.getX()-260,this.mostPopularActivityName.getY(),this.mostPopularActivityName.getWidth(),this.mostPopularActivityName.getHeight());
        mostPopularActivity.setFont(new Font(Constants.FONT, Font.BOLD,Constants.FONT_SIZE));
        mostPopularActivity.setOpaque(false);

        this.add(mostPopularActivity);
        mostPopularActivity.setVisible(true);
    }


    private void historyTitle(){
        JLabel historyTitle= new JLabel("Activity History:");
        historyTitle.setBounds(Constants.HISTORY_TITLE_X,Constants.HISTORY_TITLE_Y,Constants.HISTORY_TITLE_WIDTH,Constants.HISTORY_TITLE_HEIGHT);
        historyTitle.setFont(new Font(Constants.FONT,Font.BOLD,25));
        historyTitle.setOpaque(false);
        this.add(historyTitle);
        historyTitle.setVisible(true);

    }

    private void historyActivityArea(){
        JTextArea historyActivity= new JTextArea();
        historyActivity.setBounds(Constants.HISTORY_TITLE_X,Constants.HISTORY_TITLE_Y+100,Constants.HISTORY_TITLE_WIDTH+150,Constants.HISTORY_TITLE_HEIGHT+500);
        historyActivity.setOpaque(false);
        this.add(historyActivity);
        historyActivity.setVisible(true);
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
        public void catFactsCheckBox(){
        this.catFactsApi=new JCheckBox("Cat Facts API");
        this.catFactsApi.setFont(new Font(Constants.FONT,Font.BOLD,18));
        this.catFactsApi.setForeground(Color.WHITE);
        this.catFactsApi.setBounds(Constants.CHECKBOX_X,500,250,250);
        this.catFactsApi.setBorderPainted(false);
        this.catFactsApi.setContentAreaFilled(false);
        this.catFactsApi.addActionListener(e -> {
            checkCondition(this.catFactsApi);
        });
        this.add(this.catFactsApi);
        this.catFactsApi.setVisible(true);
        }

        public void jokesCheckBox(){
        this.jokesApi=new JCheckBox("Jokes API");
        this.jokesApi.setFont(new Font(Constants.FONT,Font.BOLD,18));
        this.jokesApi.setForeground(Color.WHITE);
        this.jokesApi.setBounds(Constants.CHECKBOX_X,550,250,250);
            this.jokesApi.setBorderPainted(false);
            this.jokesApi.setContentAreaFilled(false);
            this.jokesApi.addActionListener(e -> {
                checkCondition(this.jokesApi);
            });
            this.add(this.jokesApi);
            this.jokesApi.setVisible(true);
        }

        public void numbersCheckBox(){
            this.numbersApi=new JCheckBox("Numbers API");
            this.numbersApi.setFont(new Font(Constants.FONT,Font.BOLD,18));
            this.numbersApi.setForeground(Color.WHITE);
            this.numbersApi.setBounds(Constants.CHECKBOX_X,600,250,250);
            this.numbersApi.setBorderPainted(false);
            this.numbersApi.setContentAreaFilled(false);
            this.numbersApi.addActionListener(e -> {
                checkCondition(this.numbersApi);
            });
            this.add(this.numbersApi);
            this.numbersApi.setVisible(true);
        }

        public void activitiesCheckBox(){
            this.activitiesApi=new JCheckBox("Activities API");
            this.activitiesApi.setFont(new Font(Constants.FONT,Font.BOLD,18));
            this.activitiesApi.setForeground(Color.WHITE);
            this.activitiesApi.setBounds(Constants.CHECKBOX_X,650,250,250);
            this.activitiesApi.setBorderPainted(false);
            this.activitiesApi.setContentAreaFilled(false);
            this.activitiesApi.addActionListener(e -> {
                checkCondition(this.activitiesApi);
            });
            this.add(this.activitiesApi);
            this.activitiesApi.setVisible(true);
        }
        public void randomDogCheckBox(){
            this.randomDogApi=new JCheckBox("Activities API");
            this.randomDogApi.setFont(new Font(Constants.FONT,Font.BOLD,18));
            this.randomDogApi.setForeground(Color.WHITE);
            this.randomDogApi.setBounds(Constants.CHECKBOX_X,700,250,250);
            this.randomDogApi.setBorderPainted(false);
            this.randomDogApi.setContentAreaFilled(false);
            this.randomDogApi.addActionListener(e -> {
                checkCondition(this.randomDogApi);
            });
            this.add(this.randomDogApi);
            this.randomDogApi.setVisible(true);
        }

    public void checkOptions(){
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
    public void checkCondition(JCheckBox checkBox){
        if (checkBox.isSelected()){
            this.counterToThree++;
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

    public void actionPerformed() {
        List<String> selectedApis = new ArrayList<>();

        if (this.catFactsApi.isSelected()) {
            selectedApis.add("Cat Facts API");
        }
        if (this.jokesApi.isSelected()) {
            selectedApis.add("Jokes API");
        }
        if (this.numbersApi.isSelected()) {
            selectedApis.add("Numbers API");
        }
        if (this.activitiesApi.isSelected()) {
            selectedApis.add("Activities API");
        }
        if (this.randomDogApi.isSelected()) {
            selectedApis.add("Random Dog Image API");
        }

        if (selectedApis.size() >= 3) {
            String message = selectedApis.stream()
                    .collect(Collectors.joining("\n", "", "\n-----------------\nTotal: " + selectedApis.size()));
            JOptionPane.showMessageDialog(this, message);
        }

}

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(this.background,0,0,this.width,this.height,null);
        Rectangle historyActivityBack = new Rectangle(Constants.HISTORY_TITLE_X,Constants.HISTORY_TITLE_Y+100,Constants.HISTORY_TITLE_WIDTH+150,Constants.HISTORY_TITLE_HEIGHT+500);
        graphics2D.setPaint(new Color(255,255,255,150));
        graphics2D.fill(historyActivityBack);
    }
}
