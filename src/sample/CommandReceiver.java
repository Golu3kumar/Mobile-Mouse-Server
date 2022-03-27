package sample;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CommandReceiver extends Thread {
    public static final int PORT = 6666;
    private DeliveryCommand mDeliver;
    private ServerSocket serverSocket;
    public static boolean mQuit = false;
    private InetAddress inetAddress;
    private QRCodeGenerator qrCodeGenerator;

    public interface DeliveryCommand {
        void deliverResult(String command);

        void deliverError(String error);
    }

    public CommandReceiver(DeliveryCommand mDeliver) {
        this.mDeliver = mDeliver;

        try {
            serverSocket = new ServerSocket(PORT);
//            serverSocket.setReuseAddress(true);
            System.out.println("Not error");
        } catch (Exception e) {
            System.out.println("Error in the Sockets");
            serverSocket = null;
            System.out.println(e.getLocalizedMessage());
        }

        generateQR();
    }

    public void quit() {
        mQuit = true;
        try {
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        interrupt();
    }

    @Override
    public void run() {
        try {


            while (true) {

                Socket socket = serverSocket.accept();
                //System.out.println("waiting a socket to accept...");
                //System.out.println("got a socket... socket:" + socket);
                //System.out.println("entering while loop...");
                if (mQuit) {

                    socket.close();
                    return;
                }
                byte[] buf = new byte[1024 * 10];
                InputStream in = socket.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();
                int len;
                while ((len = in.read(buf, 0, buf.length)) != -1) {
                    stringBuilder.append(new String(buf, 0, len));
                }
                String result = stringBuilder.toString();
                if (!result.isEmpty()) {
                    mDeliver.deliverResult(result);
                } else {
                    mDeliver.deliverError("Unknow Error");
                }
                socket.close();

            }

        } catch (IOException e) {
            mDeliver.deliverError("IOError: " + e.toString());
        }

    }

    public void generateQR() {
        try {
            inetAddress = InetAddress.getLocalHost();
            if (inetAddress != null)
//                System.out.println(inetAddress.getHostAddress());
                qrCodeGenerator = new QRCodeGenerator(inetAddress.getHostAddress(), PORT, inetAddress.getHostName());

            qrCodeGenerator.generateQR();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
