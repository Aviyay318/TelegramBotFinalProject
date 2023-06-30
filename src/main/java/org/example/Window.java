package org.example;

import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setTitle("Statistics");
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Schlong");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new Panel(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.setVisible(true);
    }
}
