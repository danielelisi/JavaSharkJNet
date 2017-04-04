package Examples;

import Application.PcapFileReader;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.util.PcapPacketArrayList;

/**
 * Created by danielelisi on 2017-04-03.
 */
public class main {

    public static void main(String[] args) {

        PcapFileReader packet = new PcapFileReader("wireshark.pcap");

        PcapPacketArrayList mylist = packet.setPacketList();

        int i = 1;
        Ip4 ip = new Ip4();

        byte[] destinatioIp = new byte[4];

        for (PcapPacket item : mylist) {
            System.out.println("Frame " + i);
            if (item.hasHeader(ip)){
                destinatioIp = item.getHeader(ip).destination();
                String stringIp = FormatUtils.ip(destinatioIp);

                System.out.println(stringIp);
            }

//            System.out.println(item);
            i++;

        }

    }
}
