package Application;

/**
 * Created by danielelisi on 2017-04-02.
 */
public class WirePackets {

    private String destination;
    private Integer packets;
    private Integer time;

    public WirePackets(String destination, Integer packets, Integer time) {
        this.destination = destination;
        this.packets = packets;
        this.time = time;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getPackets() {
        return packets;
    }

    public void setPackets(Integer packets) {
        this.packets = packets;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
