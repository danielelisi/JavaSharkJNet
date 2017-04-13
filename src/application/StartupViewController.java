package application;


import classes.PacketInfo;
import classes.WirePackets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class StartupViewController implements Initializable {

    // Elements References

    static public String fileaddress;


    @FXML
    private Button selectButton;
    @FXML
    private Button loadButton;
    @FXML
    public TextField filePath;
    @FXML
    private TableView<WirePackets> tableView;
    @FXML
    private TableColumn<WirePackets, Integer> colFrame;
    @FXML
    private TableColumn<WirePackets, String> colIpSource;
    @FXML
    private TableColumn<WirePackets, String> colIpDestination;
    @FXML
    private TableColumn<WirePackets, Integer> colPortSource;
    @FXML
    private TableColumn<WirePackets, Integer> colPortDestination;
    @FXML
    private TableColumn<WirePackets, String> colChecksum;
    @FXML
    private TableColumn<WirePackets, Integer> colPacketSize;
    @FXML
    private TableColumn<WirePackets, Date> colDate;

    // display/get data TableView
    public ObservableList<WirePackets> enteredWirePackets = FXCollections.observableArrayList();

    // "StartupViewController constructor" set up tableview and columns connections
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colFrame.setCellValueFactory(new PropertyValueFactory<>("frameNumber"));
        colIpSource.setCellValueFactory(new PropertyValueFactory<>("ipSource"));
        colIpDestination.setCellValueFactory(new PropertyValueFactory<>("ipDestination"));
        colPortSource.setCellValueFactory(new PropertyValueFactory<>("portSource"));
        colPortDestination.setCellValueFactory(new PropertyValueFactory<>("portDestination"));
        colChecksum.setCellValueFactory(new PropertyValueFactory<>("checksum"));
        colPacketSize.setCellValueFactory(new PropertyValueFactory<>("byteSize"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));



        tableView.getColumns().clear();
        tableView.setItems(enteredWirePackets);
        tableView.getColumns().addAll(colFrame, colIpSource, colIpDestination, colPortSource, colPortDestination, colChecksum, colPacketSize, colDate);
    }

    // Select .pcap file ActionHandler
    public void chooseFile (ActionEvent event) {
        FileChooser fc = new FileChooser();

        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            filePath.setText(selectedFile.getAbsolutePath());
        } else {
            filePath.setText("File not selected.");
        }
        fileaddress = filePath.getText();
    }

    // Load file button
    public void loadFile (ActionEvent event) {

        enteredWirePackets.clear();
        PacketsProcessor processor = new PacketsProcessor(filePath.getText());
        // This ArrayList stores PacketInfo objects so the controller can loop trough it and retrieve values to construct WirePackets
        ArrayList<PacketInfo> packetsList = processor.getInfoList();

        int frameNumber = 1;
        // Iterate and create WirePackets objects for the GUI
        for (PacketInfo packet : packetsList) {
            enteredWirePackets.add(new WirePackets(
                    frameNumber,
                    packet.getIpSource(),
                    packet.getIpDestination(),
                    packet.getPortSource(),
                    packet.getPortDestination(),
                    packet.getChecksum(),
                    packet.getPacketSize(),
                    packet.getDate()));

            frameNumber++;
        }
    }

    //Chart button
    public void Chart (ActionEvent event)throws Exception{
        Stage chartStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Piechart.fxml"));
        chartStage.setTitle("JavaShark");
        chartStage.setScene(new Scene(root));
        chartStage.show();
    }



}
