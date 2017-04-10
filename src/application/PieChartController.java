package application;

import classes.PacketInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Toshiba on 2017/4/9.
 */
public class PieChartController implements Initializable {

    private ArrayList<PacketInfo> packetsList = ShareableData.getInstance().getDataTable().getInfoList();
    private Integer tcp = 0;
    private Integer udp = 0;


    @FXML
    Parent root;

    @FXML
    private PieChart chart;

    @FXML
    private Label tcpNum;
    @FXML
    private Label ndpNum;


    /**
     * When this controller loads (via fxml), we need to set up and display the pie chart. The challenge here is
     * that our saved data is stored in DataItem objects in an arraylist - but we need PieChart.Data objects in an
     * ObservableList. The simplest thing we can do is to iterate over the arraylist and save each dataitem into
     * a new piechart.data object.
     */

    public void initialize(URL location, ResourceBundle resources) {





        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (PacketInfo packet : packetsList){
            tcp = tcp + packet.countTcp();
            udp = udp + packet.countUdp();
        }
        ndpNum.setText(udp.toString());
        tcpNum.setText(tcp.toString());



        pieChartData.add(new PieChart.Data("UDP", udp));
        pieChartData.add(new PieChart.Data("TCP", tcp));

        chart.setTitle("Categories");
        chart.setData(pieChartData);
    }


}
