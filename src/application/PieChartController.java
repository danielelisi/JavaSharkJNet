package application;

import classes.PacketInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TreeMap;

/*
 * Set 2A
 * Daniele Lisi
 * Rui Guo
 * Sweeha Arya
 */

/**
 * Created by Toshiba on 2017/4/9.
 */
public class PieChartController implements Initializable {

    private Integer tcp = 0;
    private Integer udp = 0;
    private String time = "";

    @FXML
    Parent root;

    @FXML
    private PieChart chart;
    @FXML
    private LineChart<String, Number> lineChart;

    /**
     * When this controller loads (via fxml), we need to set up and display the pie chart. The challenge here is
     * that our saved data is stored in DataItem objects in an arraylist - but we need PieChart.Data objects in an
     * ObservableList. The simplest thing we can do is to iterate over the arraylist and save each dataitem into
     * a new piechart.data object.
     */

    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<PacketInfo> packetsList = ShareableData.getInstance().getDataTable().getInfoList();

        chart.getData().clear();
        lineChart.getData().clear();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        TreeMap<String, Integer> packetsSecond = new TreeMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        for (PacketInfo packet : packetsList) {
            tcp = tcp + packet.countTcp();
            udp = udp + packet.countUdp();
            time = sdf.format(packet.getDate());
            if (packetsSecond.containsKey(time)) {
                packetsSecond.put(time, packetsSecond.get(time) + 1);
            } else {
                packetsSecond.put(time, 1);
            }
        }
        pieChartData.add(new PieChart.Data(("UDP: " + udp), udp));
        pieChartData.add(new PieChart.Data(("TCP: " + tcp), tcp));
        chart.setData(pieChartData);


        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (String time : packetsSecond.keySet()){
            series.getData().add(new XYChart.Data<>(time, packetsSecond.get(time)));
        }
        lineChart.getData().add(series);
    }
}
