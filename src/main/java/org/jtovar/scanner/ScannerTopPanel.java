package org.jtovar.scanner;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class ScannerTopPanel extends JPanel implements ActionListener {

    JButton returnBtn = new JButton("Return to Interfaces");
    JButton toggleCaptureBtn = new JButton("Start Capture");

    public ScannerTopPanel() {
        super();
        initComponents();
    }

    private void initComponents() {
        toggleCaptureBtn.addActionListener(this);
        returnBtn.addActionListener(this);
        toggleCaptureBtn.setForeground(Color.GREEN);
        this.add(toggleCaptureBtn);
        this.add(returnBtn);
    }

    public void actionPerformed(ActionEvent event) {
        PacketCapture sniffer = new PacketCapture();
        Thread sniffThread = new Thread(sniffer);
        Object source = event.getSource();
        if (source.equals(toggleCaptureBtn)) {
            if (PacketCapture.capturing) {
                toggleCaptureBtn.setText("Start Capture");
                PacketCapture.capturing = false;
                toggleCaptureBtn.setForeground(Color.GREEN);
            } else {
                toggleCaptureBtn.setText("Stop Capture");
                PacketCapture.capturing = true;
                toggleCaptureBtn.setForeground(Color.RED);
                this.revalidate();
                this.repaint();
                sniffThread.start();
            }
        }
    }
    
}
