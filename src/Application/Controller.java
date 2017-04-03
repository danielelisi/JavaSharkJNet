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
    private TableColumn<WirePackets, String> colDest;
    @FXML
    private TableColumn<WirePackets, Integer> colRetrans;

    // display/get data TableView
    public ObservableList<WirePackets> enteredWirePackets = FXCollections.observableArrayList();

    // "Controller constructor" set up tableview and columns connections
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colDest.setCellValueFactory(new PropertyValueFactory<>("destination"));
        colRetrans.setCellValueFactory(new PropertyValueFactory<>("retransmissions"));

        tableView.getColumns().clear();
        tableView.setItems(enteredWirePackets);
        tableView.getColumns().addAll(colDest, colRetrans);
    }

    // Select .pcap file ActionHandler
    public void chooseFile (ActionEvent event) {
        FileChooser fc = new FileChooser();

        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            filePath.setText(selectedFile.getAbsolutePath());
        } else {
            System.out.println("File is not selected.");
        }
    }

    // Load file button
    public void loadFile (ActionEvent event) {
        enteredWirePackets.add(new WirePackets("192.168.0.1", 5));
    }


}
