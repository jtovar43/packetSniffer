package org.jtovar.scanner;

import java.awt.Color;

import java.io.EOFException;
import java.net.Inet4Address;
import java.util.concurrent.TimeoutException;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.UdpPacket;
import org.pcap4j.packet.namednumber.IpNumber;
import org.pcap4j.packet.namednumber.TcpPort;
import org.pcap4j.packet.namednumber.UdpPort;

public class PacketCapture implements Runnable {

    public PacketCapture() {
        super();
    }

    private int packetNum = 1;
    static boolean capturing = false;
    PcapNetworkInterface listenInterface;
    
    public void run() {
        try {
            listenInterface = Pcaps.getDevByName(StartScreen.selectedNIC);
        } catch (PcapNativeException e1) {
            e1.printStackTrace();
        }
        PromiscuousMode mode = PromiscuousMode.PROMISCUOUS;
        int timeout = 500;
        Color udpColor = new Color(207,226,243);
        Color tcpColor = new Color(233,253,204);
        int snapLen = 65536;
        PcapHandle handle;
        try {
            handle = listenInterface.openLive(snapLen, mode, timeout);
        } catch (PcapNativeException e1) {
            e1.printStackTrace();
            return;
        }
        while (capturing) {
            Packet packet;
            try {
                packet = handle.getNextPacketEx();
            } catch (TimeoutException e) {
                continue;
            } catch (EOFException e) {
                e.printStackTrace();
                return;
            } catch (PcapNativeException e) {
                e.printStackTrace();
                return;
            } catch (NotOpenException e) {
                e.printStackTrace();
                return;
            }
            IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
            Inet4Address srcAddr = ipV4Packet.getHeader().getSrcAddr();
            Inet4Address dstAddr = ipV4Packet.getHeader().getDstAddr();
            IpNumber protocol = ipV4Packet.getHeader().getProtocol();
            Object srcPort = "";
            Object dstPort = "";
            if (packet.contains(TcpPacket.class)) {
                ScannerCaptureView.captureTable.setBackground(tcpColor);
                srcPort = (TcpPort)packet.get(TcpPacket.class).getHeader().getSrcPort();
                dstPort = (TcpPort)packet.get(TcpPacket.class).getHeader().getDstPort();
            } else if (packet.contains(UdpPacket.class)) {
                ScannerCaptureView.captureTable.setBackground(udpColor);
                srcPort = (UdpPort)packet.get(UdpPacket.class).getHeader().getSrcPort();
                dstPort = (UdpPort)packet.get(UdpPacket.class).getHeader().getDstPort();
            }
            Object [] newRow = {srcAddr.getHostAddress()+": "+srcPort,dstAddr.getHostAddress()+": "+dstPort,protocol,packetNum,ipV4Packet.getPayload()};
            ScannerCaptureView.tableModel.insertRow(0,newRow);
            ScannerCaptureView.tableModel.fireTableDataChanged();
            ScannerViewController.captureView.revalidate();
            ScannerViewController.captureView.repaint();
            packetNum++;
        }
        handle.close();

    }
}
