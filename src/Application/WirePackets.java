package Application;

/**
 * Created by danielelisi on 2017-04-02.
 */
public class WirePackets {

    private String destination;
    private Integer retransmissions;

    public WirePackets(String destination, Integer retransmissions) {
        this.destination = destination;
        this.retransmissions = retransmissions;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getRetransmissions() {
        return retransmissions;
    }

    public void setRetransmissions(Integer retransmissions) {
        this.retransmissions = retransmissions;
    }
}
