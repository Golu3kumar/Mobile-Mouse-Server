package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import sample.utils.MouseUtil;
import javafx.stage.Stage;
import sample.operation.OperationData;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import sample.operation.OperationDecode;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, Color.LIGHTSKYBLUE);
        primaryStage.setScene(scene);
        Image icon = new Image("C:\\Users\\golu\\Desktop\\Mobile Mouse\\src\\sample\\res\\mouse.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Mobile Mouse Server");
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(700);
//        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest( windowEvent -> {
            stopServer();
            System.exit(0);
        });
    }

    public void stopServer() {
        CommandReceiver.mQuit = true;
        if (receiver != null) {
//            System.out.println("In quit");
            receiver.quit();
            CommandReceiver.mQuit = false;
        }
    }


    static CommandReceiver receiver = null;
    private static final float MOVE_RATIO = 0.17f;
    public static void main(String[] args) {

        startServer();
//        new Thread(new WaitThread()).start();
        launch(args);

    }

    public static void startServer() {
        receiver = new CommandReceiver(new CommandReceiver.DeliveryCommand() {

            @Override
            public void deliverResult(String command) {
                parseCommand(command);
                //System.out.println("received: " + OperationDecode.decode(command));

            }

            @Override
            public void deliverError(String error) {
                // TODO Auto-generated method stub
                System.out.println("Error: " + error);
            }
        });
        receiver.start();
        MouseUtil.moveCenter();
    }

    public static void parseCommand(String command) {
        OperationData operation = OperationDecode.decode(command);
        if (operation == null)
            return;
        System.out.println(operation);
        switch (operation.getOperationKind()) {
            case OperationData.OPERATION_MOVE:
                //System.out.println("in Operation");
                int moveX = (int) (operation.getMoveX() * MOVE_RATIO);
                int moveY = (int) (operation.getMoveY() * MOVE_RATIO);
                MouseUtil.moveMouse(moveX, moveY);
                break;
            case OperationData.OPERATION_CLICK_DOWN:
                MouseUtil.leftClickDown(true);
                break;
            case OperationData.OPERATION_CLICK_UP:
                MouseUtil.leftClickDown(false);
                break;
            case OperationData.OPERATION_RIGHT_CLICK:
                MouseUtil.rightClick();
                break;
            case OperationData.OPERATION_TYPE_TEXT:
                MouseUtil.typeText(operation.getInputStr());
                break;
            case OperationData.OPERATION_DEL_TEXT:
                MouseUtil.delText();
                break;
            default:
                break;
        }
    }
}
