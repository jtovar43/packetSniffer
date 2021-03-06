package org.jtovar.scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.jtovar.app.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class ScannerTopPanel extends JPanel implements ActionListener {

    ImageIcon startIcon = new ImageIcon("resources/start.png");
    ImageIcon stopIcon = new ImageIcon("resources/stop.png");
    ImageIcon backIcon = new ImageIcon("resources/back.png");
    ImageIcon clearIcon = new ImageIcon("resources/clear.png");

    JButton toggleCaptureBtn = new JButton(startIcon);
    JButton returnBtn = new JButton(backIcon);
    JButton clearBtn = new JButton(clearIcon);

    public ScannerTopPanel() {
        super();
        initComponents();
    }

    private void initComponents() {
        this.setBackground(Color.LIGHT_GRAY);
        toggleCaptureBtn.setOpaque(false);
        toggleCaptureBtn.setContentAreaFilled(false);
        toggleCaptureBtn.setBorderPainted(false);
        toggleCaptureBtn.setFocusPainted(false);

        returnBtn.setOpaque(false);
        returnBtn.setContentAreaFilled(false);
        returnBtn.setBorderPainted(false);
        returnBtn.setFocusPainted(false);

        clearBtn.setOpaque(false);
        clearBtn.setContentAreaFilled(false);
        clearBtn.setBorderPainted(false);
        clearBtn.setFocusPainted(false);

        toggleCaptureBtn.addActionListener(this);
        returnBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        this.add(returnBtn);
        this.add(toggleCaptureBtn);
        this.add(clearBtn);
    }

    public void refreshButtons() {
        if (PacketCapture.capturing) {
            toggleCaptureBtn.setIcon(stopIcon);
        } else {
            toggleCaptureBtn.setIcon(startIcon);
        }
    }

    public void actionPerformed(ActionEvent event) {
        PacketCapture sniffer = new PacketCapture();
        Thread sniffThread = new Thread(sniffer);
        Object source = event.getSource();
        if (source.equals(toggleCaptureBtn)) {
            if (PacketCapture.capturing) {
                PacketCapture.capturing = false;
                
            } else {
                PacketCapture.capturing = true;
                sniffThread.start();
            }
            refreshButtons();
            this.revalidate();
            this.repaint();
        }
        else if (source.equals(returnBtn)) {
            App.window.dispose();
            ScannerCaptureView.tableModel.setRowCount(0);
            new MainWindow();
        }
        else if (source.equals(clearBtn)) {
            ScannerCaptureView.tableModel.setRowCount(0);
            PacketCapture.packetNum = 1;
        }
    }
    
}
