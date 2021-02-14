package org.jtovar.GUI;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

    private StartScreen ss = new StartScreen();

    public MainWindow() {
        super("Interface Select");
        this.setSize(1000,710);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(ss);
        this.setVisible(true);
    }
    
}
