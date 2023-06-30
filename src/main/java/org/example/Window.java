package org.example;

import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Telegram API Project");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(Constants.PANEL);
        this.setVisible(true);
    }
}
