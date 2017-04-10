package application;

import classes.PacketInfo;
import classes.PcapFileReader;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.util.PcapPacketArrayList;

import java.util.ArrayList;
import java.util.Date;

/**
 * ACIT 2515 JavaSharkJNet
 *
 * @author Daniele Lisi - A00978006 Set A
 * @date 2017-04-06
 */
public class PacketsProcessor {

    private String filePath;
    private PcapPacketArrayList packetList;
    private ArrayList<PacketInfo> infoList = new ArrayList<>();

    // Constructor load the ArrayList<PacketInfo> so the controller can retrieve it with a GET method
    public PacketsProcessor(String filepath) {
        this.filePath = filepath;
        setPacketList();
        setInfoList();
    }

    private void setPacketList() {
        packetList = PcapFileReader.loadPacketList(filePath);
    }

    // Create ArrayList of Class PacketInfo which has methods to retrieve data for each packet
    private void setInfoList() {
        for (PcapPacket item : packetList) {
            PacketInfo packet = new PacketInfo(item);
            infoList.add(packet);
        }
    }

    public ArrayList<PacketInfo> getInfoList() {
        return infoList;
    }

    // TEST ONLY Prints Statistics in the terminal
    public void printPacket() {
        // Protocol Initialization
        Ethernet eth = new Ethernet();
        Ip4 ip4 = new Ip4();
        Ip6 ip6 = new Ip6();
        Tcp tcp = new Tcp();

        for (int i = 0; i <= 10; i++) {
            PcapPacket packet = packetList.get(i);
            System.out.println("Frame n." + (i + 1));

            // Check if packets has IP header, if so print source and destination
            if (packet.hasHeader(ip4)) {
                String ipSource = FormatUtils.ip(ip4.source());
                System.out.println("IP Source: " + ipSource);
                String ipDestination = FormatUtils.ip(ip4.destination());
                System.out.println("IP Destination: " + ipDestination);
            }

            if (packet.hasHeader(ip6)) {
                String ipSource = FormatUtils.ip(ip6.source());
                System.out.println("IP Source: " + ipSource);
                String ipDestination = FormatUtils.ip(ip6.destination());
                System.out.println("IP Destination: " + ipDestination);
            }

            // Check if packets has TCP header, if so print port source and destination
            if (packet.hasHeader(tcp)) {

                System.out.println("Port Source: " + tcp.source());
                System.out.println("Port Destination: " + tcp.destination());
                System.out.println("Checksum :" + tcp.checksumDescription());
            }

            // Print Packet Size
            System.out.println("Packet Size: " + packet.getCaptureHeader().caplen() + " bytes");

            // Print Date
            Date packetDate = new Date(packet.getCaptureHeader().timestampInMillis());
            System.out.println(packetDate);




            System.out.println();

        }


    }

}
