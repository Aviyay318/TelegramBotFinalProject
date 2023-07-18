package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.swing.*;
import java.io.File;

public class Window extends JFrame {
    private Panel panel;
    private Thread rePainter;
    private File chart;
    public Window() {

        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Telegram Bot Manager");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.chart = new File("res/chart");
        if(this.getDefaultCloseOperation()==EXIT_ON_CLOSE){

        }
        this.panel = new Panel(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.add(this.panel);
        this.rePainter = new Thread(()->{
           while(true){
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }

               repaint();
           }
        });
        this.rePainter.start();
        this.setVisible(true);

    }
}
