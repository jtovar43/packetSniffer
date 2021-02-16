package org.jtovar.scanner;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;

import org.pcap4j.core.PcapNativeException;
public class ScannerCaptureView extends JPanel {
    static String[] columnNames = { "<html><h3>Source<h3></html>", "<html><h3>Destination<h3></html>",
            "<html><h3>Port/Protocol<h3></html>", "<html><h3>Packet No.<h3></html>", "<html><h3>Payload<h3></html>" };
    static DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    static JTable captureTable;

    public ScannerCaptureView() throws PcapNativeException {
        super(new GridLayout());
        initComponents();
    }

    private void initComponents() throws PcapNativeException {
        captureTable = new JTable(tableModel);
        this.add(new JScrollPane(captureTable));
    }
}
