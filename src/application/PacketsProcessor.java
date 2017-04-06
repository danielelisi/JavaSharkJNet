package application;

import classes.PcapFileReader;
import classes.StreamFlows;
import org.jnetpcap.util.PcapPacketArrayList;

/**
 * ACIT 2515 JavaSharkJNet
 *
 * @author Daniele Lisi - A00978006 Set A
 * @date 2017-04-06
 */
public class PacketsProcessor {

    private String filePath;

    public PacketsProcessor(String filepath) {
        this.filePath = filepath;
    }






    PcapPacketArrayList packetList = new PcapFileReader(filePath).loadPacketList();
    StreamFlows streams = new StreamFlows(packetList);

}
