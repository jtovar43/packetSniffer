package org.jtovar.scanner;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScannerTopPanel extends JPanel implements ActionListener {

    JButton stopBtn = new JButton("Stop Capture");
    JButton returnBtn = new JButton("Return to Interfaces");
    JButton startBtn = new JButton("Start Capture");
    JProgressBar captureProgressBar = new JProgressBar();

    public ScannerTopPanel() {
        super();
        initComponents();
    }

    private void initComponents() {
        startBtn.addActionListener(this);
        stopBtn.addActionListener(this);
        returnBtn.addActionListener(this);
        this.add(startBtn);
        this.add(stopBtn);
        this.add(returnBtn);
        captureProgressBar.setVisible(false);
        this.add(captureProgressBar);
    }

    public void actionPerformed(ActionEvent event) {
        PacketCapture sniffer = new PacketCapture();
        Thread sniffThread = new Thread(sniffer);

        Object source = event.getSource();
        if (source.equals(stopBtn)) {
            PacketCapture.capturing=false;
        }
        if (source.equals(startBtn)) {
            PacketCapture.capturing = true;
            sniffThread.start();
        }
    }
    
}
