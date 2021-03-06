package classes;

import org.jnetpcap.packet.JFlow;
import org.jnetpcap.packet.JFlowKey;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.util.PcapPacketArrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by danielelisi on 2017-04-05.
 */

// NOT IN USE
public class StreamFlows {

    public static ArrayList<JFlow> getFlows(PcapPacketArrayList packetsList) {

        final Map<JFlowKey, JFlow> STREAM_FLOWS = new HashMap<>();
        for (PcapPacket packet : packetsList) {

            final JFlowKey PACKET_KEY = packet.getState().getFlowKey();

            JFlow flow = STREAM_FLOWS.get(PACKET_KEY);
            if (flow == null) {
                STREAM_FLOWS.put(PACKET_KEY, flow = new JFlow(PACKET_KEY));
            }
            flow.add(new PcapPacket(packet));
        }

        return new ArrayList<>(STREAM_FLOWS.values());
    }
}
