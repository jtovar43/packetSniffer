package org.jtovar.app;

import javax.swing.JFrame;

import org.jtovar.interfaceselect.*;
import org.pcap4j.core.PcapNativeException;

public class MainWindow extends JFrame {

    private StartScreen ss;

    public MainWindow() {
        super("Interface Select");
        try {
            ss = new StartScreen();
        } catch (PcapNativeException e) {
            System.err.println("Something went wrong");
        }
        this.setSize(500,250);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(ss);
        this.setVisible(true);
    }
    
}
