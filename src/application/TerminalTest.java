package application;

import java.util.ArrayList;

/**
 * Created by danielelisi on 2017-04-06.
 */
public class TerminalTest {

    public static void main(String[] args) {

        PacketsProcessor processor = new PacketsProcessor("pcap_files/test.pcap");
        //processor.printFlows();
        //processor.printPacket();

        ArrayList<PacketInfo> infoList = processor.getInfoList();

        int i = 1;
        for (PacketInfo packet : infoList) {


            System.out.println("Frame " + i);
            System.out.println(packet.getIpSource());
            i++;
        }

    }
}
