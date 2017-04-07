package classes;

import java.util.Date;

/**
 * Created by danielelisi on 2017-04-02.
 */
public class WirePackets {

    private Integer frameNumber;
    private String ipSource;
    private String ipDestination;
    private Integer portSource = null;
    private Integer portDestination = null;
    private String checksum = null;
    private Integer byteSize;
    private Date date;

    public WirePackets(Integer frameNumber, String ipSource, String ipDestination, Integer portSource, Integer portDestination, String checksum, Integer byteSize, Date date) {
        this.frameNumber = frameNumber;
        this.ipSource = ipSource;
        this.ipDestination = ipDestination;
        this.portSource = portSource;
        this.portDestination = portDestination;
        this.checksum = checksum;
        this.byteSize = byteSize;
        this.date = date;
    }

    public Integer getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(Integer frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getIpSource() {
        return ipSource;
    }

    public void setIpSource(String ipSource) {
        this.ipSource = ipSource;
    }

    public String getIpDestination() {
        return ipDestination;
    }

    public void setIpDestination(String ipDestination) {
        this.ipDestination = ipDestination;
    }

    public Integer getPortSource() {
        return portSource;
    }

    public void setPortSource(Integer portSource) {
        this.portSource = portSource;
    }

    public Integer getPortDestination() {
        return portDestination;
    }

    public void setPortDestination(Integer portDestination) {
        this.portDestination = portDestination;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public Integer getByteSize() {
        return byteSize;
    }

    public void setByteSize(Integer byteSize) {
        this.byteSize = byteSize;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
