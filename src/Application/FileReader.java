package Application;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.util.PcapPacketArrayList;

/**
 * ACIT 2515 JavaSharkJNet
 *
 * @author Daniele Lisi - A00978006 Set A
 * @date 2017-04-04
 */


// Reads offline .pcap file and returns a PcapPacketArrayList
public class FileReader {

    public static PcapPacketArrayList getArray(String fileLocation) {

        final StringBuilder ERRBUFF = new StringBuilder();
        Pcap pcapFile = Pcap.openOffline(fileLocation, ERRBUFF);

        if (pcapFile == null) {
            System.out.println("File not selected");
        }

        PcapPacketHandler<PcapPacketArrayList> packetHandler = new PcapPacketHandler<PcapPacketArrayList>() {
            @Override
            public void nextPacket(PcapPacket packet, PcapPacketArrayList packetList) {
                packetList.add(packet);
            }
        };

        try {
            PcapPacketArrayList packetArray = new PcapPacketArrayList();
            pcapFile.loop(-1, packetHandler, packetArray);

            return packetArray;
        } finally {
            pcapFile.close();
        }
    }
}
