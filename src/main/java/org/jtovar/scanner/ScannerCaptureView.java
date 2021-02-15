package org.jtovar.scanner;

import java.io.EOFException;
import java.net.Inet4Address;
import java.util.concurrent.TimeoutException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.namednumber.IpNumber;

public class ScannerCaptureView extends JPanel {
    private String[] columnNames = { "<html><h3>Source<h3></html>", "<html><h3>Destination<h3></html>","<html><h3>Port/Protocol<h3></html>", "<html><h3>Packet No.<h3></html>" };
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable captureTable;
    private int packetNum = 1;
    static boolean capturing = true;
    PcapNetworkInterface listenInterface;

    public ScannerCaptureView() throws PcapNativeException {
        super(new GridLayout());
        initComponents();
        try {
            startCapture();
        } catch (EOFException e) {
            JOptionPane.showMessageDialog(this,"Fatal - EOFException");
            e.printStackTrace();
        } catch (NotOpenException e) {
            JOptionPane.showMessageDialog(this,"Fatal - NotOpenException");
            e.printStackTrace();
        }
    }

    private void initComponents() throws PcapNativeException {
        captureTable = new JTable(tableModel);
        this.add(new JScrollPane(captureTable));
    }

    public void startCapture() throws PcapNativeException, EOFException, NotOpenException {
        listenInterface = Pcaps.getDevByName(StartScreen.selectedNIC);
        PromiscuousMode mode = PromiscuousMode.PROMISCUOUS;
        int timeout = 1500;
        int snapLen = 65536;
        PcapHandle handle = listenInterface.openLive(snapLen, mode, timeout);
        while (capturing) {
            Packet packet;
            try {
                packet = handle.getNextPacketEx();
            } catch (TimeoutException e) {
                continue;
            }
            IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
            if (ipV4Packet!=null) {
                Inet4Address srcAddr = ipV4Packet.getHeader().getSrcAddr();
                Inet4Address dstAddr = ipV4Packet.getHeader().getDstAddr();
                IpNumber protocol = ipV4Packet.getHeader().getProtocol();
                Object [] newRow = {srcAddr.getHostAddress(),dstAddr.getHostAddress(),protocol,packetNum};
                tableModel.addRow(newRow);
                tableModel.fireTableDataChanged();
                this.repaint();
                packetNum++;
            }
        }
        handle.close();
    }
}
