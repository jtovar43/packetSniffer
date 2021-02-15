package org.jtovar.scanner;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScannerTopPanel extends JPanel implements ActionListener {

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

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source.equals(stopBtn)) {
            ScannerCaptureView.capturing = false;
        }
    }
    
}
