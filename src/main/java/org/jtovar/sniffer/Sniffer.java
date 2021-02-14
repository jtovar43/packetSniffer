package org.jtovar.sniffer;

import java.io.EOFException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;

public class Sniffer {

    public static void main(String[] args)
            throws PcapNativeException, EOFException, TimeoutException, NotOpenException {

        try {
            InetAddress addr = InetAddress.getByName("104.38.62.131");
            PcapNetworkInterface nif = Pcaps.getDevByAddress(addr);
            int snapLen = 65536;
            PromiscuousMode mode = PromiscuousMode.PROMISCUOUS;
            int timeout = 10;
            PcapHandle handle = nif.openLive(snapLen, mode, timeout);
            Packet packet = handle.getNextPacketEx();
            handle.close();
            IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
            Inet4Address srcAddr = ipV4Packet.getHeader().getSrcAddr();
            System.out.println(srcAddr);
        } catch (UnknownHostException e) {
            System.err.println("Unknown Host");
        }
    }
}
