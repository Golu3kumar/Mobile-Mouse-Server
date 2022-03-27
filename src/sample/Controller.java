package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView qrImage;
    @FXML
    private Label status;
    @FXML
    private Circle status_symbol;
    @FXML
    private Label ipAddressLbl;
    @FXML
    private Label portNumberLbl;

    @FXML
    public void changeStatusToConnected() {
        status.setText("Connected!");
    }
    @FXML
    public void changeStatusToReadyToConnect() {
        status.setText("Ready to connect...");
    }


    public Controller() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        status = new Label();
        Image img = new Image("C:\\Users\\golu\\Desktop\\Mobile Mouse\\src\\sample\\res\\ip.jpg");
        qrImage.setImage(img);
    }
}
