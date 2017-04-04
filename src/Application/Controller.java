package Application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.util.PcapPacketArrayList;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // Elements References
    @FXML
    private Button selectButton;
    @FXML
    private Button loadButton;
    @FXML
    private TextField filePath;
    @FXML
    private TableView<WirePackets> tableView;
    @FXML
    private TableColumn<WirePackets, String> colServer;
    @FXML
    private TableColumn<WirePackets, Integer> colPackets;
    @FXML
    private TableColumn<WirePackets, Integer> colTime;

    private String wirefile;

    // display/get data TableView
    public ObservableList<WirePackets> enteredWirePackets = FXCollections.observableArrayList();

    // "Controller constructor" set up tableview and columns connections
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colServer.setCellValueFactory(new PropertyValueFactory<>("destination"));
        colPackets.setCellValueFactory(new PropertyValueFactory<>("retransmissions"));


        tableView.getColumns().clear();
        tableView.setItems(enteredWirePackets);
        tableView.getColumns().addAll(colServer, colPackets,colTime);
    }

    // Select .pcap file ActionHandler
    public void chooseFile (ActionEvent event) {
        FileChooser fc = new FileChooser();

        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            filePath.setText(selectedFile.getAbsolutePath());
            wirefile = selectedFile.getAbsolutePath();
        } else {
            System.out.println("File is not selected.");
        }
    }

    // Load file button
    public void loadFile (ActionEvent event) {

        PcapFileReader packet = new PcapFileReader(wirefile);

        PcapPacketArrayList packetList = packet.setPacketList();

        int i = 1;

        //Protocol
        Ip4 ip = new Ip4();
        String destIp;



        for (PcapPacket item : packetList) {


            if (item.hasHeader(ip)) {
                destIp = FormatUtils.ip(item.getHeader(ip).destination());

                enteredWirePackets.add(new WirePackets(destIp, i));
                i++;

            }

        }
    }

}
