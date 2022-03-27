package sample;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.nio.file.Paths;

public class QRCodeGenerator {

    private String IP_ADDRESS;
    private int PORT;
    private String PC_NAME;

    public QRCodeGenerator(String IP_ADDRESS, int PORT, String PC_NAME) {
        this.IP_ADDRESS = IP_ADDRESS;
        this.PORT = PORT;
        this.PC_NAME = PC_NAME;
    }

    public String getIP_ADDRESS() {
        return IP_ADDRESS;
    }

    public void setIP_ADDRESS(String IP_ADDRESS) {
        this.IP_ADDRESS = IP_ADDRESS;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    public void generateQR() {
        String data = IP_ADDRESS + " " + PORT + " " + PC_NAME;
        String path = "C:\\Users\\golu\\Desktop\\Mobile Mouse\\src\\sample\\res\\ip.jpg";
        try {
            
            BitMatrix matrix = new MultiFormatWriter()
                    .encode(data, BarcodeFormat.QR_CODE, 400, 400);

            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPC_NAME() {
        return PC_NAME;
    }

    public void setPC_NAME(String PC_NAME) {
        this.PC_NAME = PC_NAME;
    }
}
