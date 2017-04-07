package application;


import classes.PcapFileReader;
import classes.StreamFlows;
import classes.WirePackets;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
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
import org.jnetpcap.util.PcapPacketArrayList;

import java.io.File;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
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

    // display/get data TableView
    public ObservableList<WirePackets> enteredWirePackets = FXCollections.observableArrayList();

    // "Controller constructor" set up tableview and columns connections
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colServer.setCellValueFactory(new PropertyValueFactory<>("destination"));
        colPackets.setCellValueFactory(new PropertyValueFactory<>("retransmissions"));


        tableView.getColumns().clear();
        tableView.setItems(enteredWirePackets);
        tableView.getColumns().addAll(colServer, colPackets, colTime);
    }

    // Select .pcap file ActionHandler
    public void chooseFile (ActionEvent event) {
        FileChooser fc = new FileChooser();

        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            filePath.setText(selectedFile.getAbsolutePath());
            PcapPacketArrayList packets  = FileReader.getArray(selectedFile.getAbsolutePath());
        } else {
            filePath.setText("File not selected.");
        }
    }

    // Load file button
    public void loadFile (ActionEvent event) {

        PacketsProcessor processor = new PacketsProcessor(filePath.getText());
        ArrayList<PacketInfo> packetsList = processor.getInfoList();

        for (PacketInfo packet : packetsList) {

        }

    }
}
