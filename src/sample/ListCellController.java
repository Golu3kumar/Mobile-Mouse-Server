package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class ListCellController extends ListCell<HistoryDevices> {

    @FXML
    private ImageView icon;
    @FXML
    private Label deviceNameLbl;
    @FXML
    private Label lastConnectedLbl;
    @FXML
    private Label ipAddressLbl;
    @FXML
    private AnchorPane pane;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(HistoryDevices historyDevices, boolean b) {
        super.updateItem(historyDevices, b);

        if (b || historyDevices == null){
            setText(null);
            setGraphic(null);
        }else {

            if (fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass().getResource("ListCell.fxml"));
                fxmlLoader.setController(this);

                try{
                    fxmlLoader.load();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            deviceNameLbl.setText(historyDevices.getDeviceName());
            lastConnectedLbl.setText(historyDevices.getLastConnected());
            ipAddressLbl.setText(historyDevices.getIpAddress());
            icon.setImage(new Image("C:\\Users\\golu\\Desktop\\Mobile Mouse\\src\\sample\\res\\android_icon.png"));

            setText(null);
            setGraphic(pane);
        }
    }
}
