package org.jtovar.scanner;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ScannerViewController extends JPanel implements ActionListener {

    private ScannerTopPanel top;

    public ScannerViewController() {
        super();
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        top = new ScannerTopPanel();
        this.add(top, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
    }

}
