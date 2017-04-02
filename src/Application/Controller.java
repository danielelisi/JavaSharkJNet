package Application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    @FXML
    private Button selectButton;

    @FXML
    private Button loadButton;

    @FXML
    private TextField filePath;

    public void chooseFile (ActionEvent event) {
        FileChooser fc = new FileChooser();

        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            filePath.setText(selectedFile.getAbsolutePath());
        } else {
            System.out.println("File is not selected.");
        }
    }

}
