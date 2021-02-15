package org.jtovar.scanner;

import javax.swing.JPanel;
import javax.swing.JTable;

public class ScannerCaptureView extends JPanel {
    private String[] columnNames = {"Source","Protocol","Packet No."};
    private Object[][] tableData;
    private JTable captureTable;
    
    public ScannerCaptureView() {
        super();
        initComponents();
    }

    private void initComponents() {

    }

    public void startCapture() {
        
    }
}
