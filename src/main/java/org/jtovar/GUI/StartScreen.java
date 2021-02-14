package org.jtovar.GUI;

import java.io.EOFException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;

public class StartScreen extends JPanel {

    private JScrollPane nicList = new JScrollPane();

    public StartScreen() {
        super();
        this.add(nicList);
    }

    public List<String> getNicList() throws PcapNativeException {
        List<String> interfaces = new ArrayList<String>();
        for (PcapNetworkInterface nic : Pcaps.findAllDevs()) {
            interfaces.add(nic.getName());
        }
        return interfaces;
    }
}
