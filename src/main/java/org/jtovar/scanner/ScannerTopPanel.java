package org.jtovar.scanner;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ScannerTopPanel extends JPanel {

    JButton stopBtn = new JButton("Stop Capture");
    JButton returnBtn = new JButton("Return to Interfaces");

    public ScannerTopPanel() {
        super();
        initComponents();
    }



    private void initComponents() {
        this.add(stopBtn);
        this.add(returnBtn);
    }
    
}
