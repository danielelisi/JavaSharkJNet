package Examples;

import org.jnetpcap.*;
import org.jnetpcap.packet.*;
import org.jnetpcap.util.PcapPacketArrayList;

/**
 * ACIT 2515 JavaSharkJNet
 *
 * @author Daniele Lisi - A00978006 Set A
 * @date 2017-04-03
 */

public class PcapFileReader {

    private String FileAddress;

    public PcapFileReader(String FileAddress) {
        this.FileAddress = FileAddress;

    }


    // Read pcap file and return PcapPacketArray
    public PcapPacketArrayList setPacketList() {
        final StringBuilder ERRBUFF = new StringBuilder();
        Pcap pcapFile = Pcap.openOffline(FileAddress, ERRBUFF);

        if (pcapFile == null) {
            System.out.println("File not selected");
        }

        PcapPacketHandler<PcapPacketArrayList> packetHandler = new PcapPacketHandler<PcapPacketArrayList>() {
            @Override
            public void nextPacket(PcapPacket packet, PcapPacketArrayList PacketList) {
                PacketList.add(packet);
            }
        };

        try {
            PcapPacketArrayList packetList = new PcapPacketArrayList();
            pcapFile.loop(-1, packetHandler, packetList);

            return packetList;
        } finally {
            pcapFile.close();
        }
    }
}
