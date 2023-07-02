package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Telegram Bot Manager");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(Constants.PANEL);
        this.setVisible(true);

    }
}
