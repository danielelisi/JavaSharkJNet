package examples;

import application.PacketsProcessor;
import classes.PacketInfo;

import java.util.ArrayList;

/**
 * Created by danielelisi on 2017-04-06.
 */

// Main Class for TESTING ONLY not in use to run the program
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
