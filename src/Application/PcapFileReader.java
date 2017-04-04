package Application;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import org.jnetpcap.*;
import org.jnetpcap.packet.*;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.util.PcapPacketArrayList;

/**
 * ACIT 2515 JavaSharkJNet
 *
 * @author Daniele Lisi - A00978006 Set A
 * @date 2017-04-03
 */
public class PcapFileReader {

    private String FileAddress;

    public PcapPacketArrayList packetList;
    public TreeMap<JFlowKey, JFlow> flows = new TreeMap<>();


    public PcapFileReader(String FileAddress) {
        this.FileAddress = FileAddress;
        this.packetList = setPacketList();
//        this.flows = setFlows();
    }



    // Read pcap file and store packets in List
    public PcapPacketArrayList setPacketList() {

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

    /* Set HashMap containing Transmissions streams unique key, JFlow which are iterable
     * to obtain Jpackets
     */
    public TreeMap<JFlowKey, JFlow> setFlows() {

        for (PcapPacket packet : packetList) {

            JFlowKey key = packet.getState().getFlowKey();
            JFlow flow = flows.get(key);

            if (flow == null) {
                flows.put(key, flow = new JFlow(key));
            }

            flow.add(new PcapPacket(packet));
        }

        return flows;
    }

    // Get source Ip address from packets in reversed flow (Server to host)
    public String getServerIp(int idx) {

        LinkedList<String> ipList = new LinkedList<>();
        Ip4 ipObj = new Ip4();

        for (JFlow flow : flows.values()) {
            if (flow.isReversable()){
                JPacket packet = flow.getReverse().get(1);

                String serverIp = FormatUtils.ip(packet.getHeader(ipObj).source());

                ipList.add(serverIp);
            } else {

                System.out.println("Flow not reversable");
            }
        }

        return "";
    }

    public int getPacketsFromServer() {

        return 0;
    }


}
