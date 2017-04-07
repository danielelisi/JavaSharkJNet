package classes;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;
import org.jnetpcap.protocol.tcpip.Tcp;

import java.util.Date;

/**
 * Created by danielelisi on 2017-04-06.
 */

// Class create new Packet object and uses GET methods to retrieve singular packet statistic
public class PacketInfo {

    private PcapPacket packet;
    private String ipSource;
    private String ipDestination;
    private Integer portSource;
    private Integer portDestination;
    private String checksum;
    private Integer packetSize;
    private Date date;

    Ip4 ip4 = new Ip4();
    Ip6 ip6 = new Ip6();
    Tcp tcp = new Tcp();

    // Constructor load each PacketInfo object's fields with values
    public PacketInfo(PcapPacket packet) {
        this.packet=packet;

        if(packet.hasHeader(ip4)){
            ipSource = FormatUtils.ip(ip4.source());
            ipDestination = FormatUtils.ip(ip4.destination());
        }
        else if (packet.hasHeader(ip6)) {
            ipSource = FormatUtils.ip(ip6.source());
            ipDestination = FormatUtils.ip(ip6.destination());
        }
        else {
            ipSource = "No IP Header";
            ipDestination = "No IP Header";
        }

        if(packet.hasHeader(tcp)){
            portSource = tcp.source();
            portDestination = tcp.destination();
            checksum = tcp.checksumDescription();
        }
        else {
            portSource = null;
            portDestination = null;
            checksum = "No TCP Header";
        }

        packetSize = packet.getCaptureHeader().caplen();

        date = new Date(packet.getCaptureHeader().timestampInMillis());
    }

    public PcapPacket getPacket() {
        return packet;
    }

    public String getIpSource() {
        return ipSource;
    }

    public String getIpDestination() {
        return ipDestination;
    }

    public Integer getPortSource() {
        return portSource;
    }

    public Integer getPortDestination() {
        return portDestination;
    }

    public String getChecksum() {
        return checksum;
    }

    public Integer getPacketSize() {
        return packetSize;
    }

    public Date getDate() {
        return date;
    }
}