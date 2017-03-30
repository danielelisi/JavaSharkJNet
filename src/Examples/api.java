package Examples;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;

/**
 * Created by danielelisi on 2017-03-29.
 */
public class api {

    public static void main(String[] args){

        final String WFILE = "wireshark.pcap";
        final StringBuilder ERROR_BUFFER = new StringBuilder();

        final Pcap pcapFile = Pcap.openOffline(WFILE, ERROR_BUFFER);

        // Read first 10 packets from wireshark file
        pcapFile.loop(10, new JPacketHandler<StringBuilder>() {

            final Tcp TCP = new Tcp();
            final Http HTTP = new Http();




        });

    }
}
