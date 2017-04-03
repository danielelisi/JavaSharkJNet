package Application;

import java.util.Date;

import org.jnetpcap.*;
import org.jnetpcap.packet.*;
import org.jnetpcap.util.PcapPacketArrayList;

/**
 * ACIT 2515 JavaSharkJNet
 *
 * @author Daniele Lisi - A00978006 Set A
 * @date 2017-04-03
 */
public class PcapFile {

    String FileAddress= "";

    public PcapFile (String FileAddress) {
        this.FileAddress = FileAddress;
    }



    public PcapPacketArrayList readOfflineFiles() {
        final StringBuilder ERRBUFF = new StringBuilder();

        Pcap pcapf = Pcap.openOffline(FileAddress, ERRBUFF);

        if (pcapf == null) {
            System.out.println("File not recognized");
        }

        PcapPacketHandler<PcapPacketArrayList> packetHandler = new PcapPacketHandler<PcapPacketArrayList>() {
            @Override
            public void nextPacket(PcapPacket packet, PcapPacketArrayList PacketList) {
                PacketList.add(packet);
            }
        };

        try {
            PcapPacketArrayList packet = new PcapPacketArrayList();
            pcapf.loop(-1, packetHandler, packet);


            return packet;
        } finally {
            pcapf.close();
        }
    }
}
