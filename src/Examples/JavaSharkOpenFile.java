package Examples;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

import java.util.Date;

/**
 * Created by danielelisi on 2017-03-28.
 */
public class JavaSharkOpenFile {

    public static  void main(String[] args) {
        //Setup error buffer and file name
        final StringBuilder ERRBUFF = new StringBuilder();
        final String FILE = "wireshark.pcap";

        System.out.printf("Opening file for reading: %s%n", FILE);

        Pcap pcapFile = Pcap.openOffline(FILE, ERRBUFF);

        if (pcapFile == null) {
            System.err.printf("Error while opening captured file: " + ERRBUFF.toString());
            return;
        }

        //Create PacketHandler which will receive packets from libcap loop
        PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {

            @Override
            public void nextPacket(PcapPacket packet, String userString) {
                System.out.printf("Received at %s -captured length = %d -original length = %d -User message %s\n",
                        new Date(packet.getCaptureHeader().timestampInMillis()),
                        packet.getCaptureHeader().caplen(),
                        packet.getCaptureHeader().wirelen(),
                        userString
                );
            }
        };

        try {
            pcapFile.loop(10,jpacketHandler,"JnetPcap test");
        } finally {
            pcapFile.close();
        }
    }
}
