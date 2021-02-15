package org.jtovar.scanner;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.pcap4j.core.PcapNativeException;

public class ScannerViewController extends JPanel implements ActionListener {

    private ScannerTopPanel top;
    private ScannerCaptureView captureView;

    public ScannerViewController() {
        super();
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        top = new ScannerTopPanel();
        try {
            captureView = new ScannerCaptureView();
        } catch (PcapNativeException e) {
            e.printStackTrace();
        }
        this.add(top, BorderLayout.NORTH);
        this.add(captureView, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
    }

}
