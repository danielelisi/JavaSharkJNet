package Examples;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.util.PcapPacketArrayList;

/**
 * Created by danielelisi on 2017-04-03.
 */
public class main {

    public static void main(String[] args) {

        PcapPacketArrayList packetList = new PcapFileReader("wireshark3.pcap").setPacketList();

        // Protocols Init
        int i = 1;
        Ip4 ip4 = new Ip4();
        Ip6 ip6 = new Ip6();
        Tcp tcp = new Tcp();



        byte[] destinatioIp = new byte[4];

        for (PcapPacket item : packetList) {
            System.out.println("Frame " + i);

            System.out.println(item);

//            if (item.hasHeader(ip4)) {
//                System.out.println(item.getHeader(ip4));
//            }
//
//            if (item.hasHeader(ip6)) {
//                System.out.println(item.getHeader(ip6));
//            }


//            if (item.hasHeader(ip4)){
//
//                destinatioIp = ip4.destination();
//                String stringIp = FormatUtils.ip4(destinatioIp);
//
//                //System.out.println("Print IP: \n" + ip4);
//
//                System.out.println(stringIp);
//            }
//
////            System.out.println(item);
            i++;

        }
    }
}