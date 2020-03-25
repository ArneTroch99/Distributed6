import sun.misc.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPServer {

    private int port;
    ;

    public UDPServer(int port) {
        this.port = port;
    }

    private static StringBuilder data(byte[] a) {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }

    public void runServer() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(port);
            byte[] receive = new byte[65535];

            DatagramPacket packet;
            while (true) {

                Scanner scanner = new Scanner(System.in);
                if (scanner.nextLine().equals("stop"))
                    break;

                packet = new DatagramPacket(receive, receive.length);
                datagramSocket.receive(packet);
                InetAddress ip = packet.getAddress();

                System.out.println("Received data: " + data(receive) + " from " + ip.toString());
                String fileName = data(receive).toString().split("!")[1];

                System.out.println("Looking for file " + fileName);

                File file = null;
                boolean found = false;

                try {
                    file = new File(fileName);
                    found = true;
                } catch (Exception e) {
                    System.out.println("File was not found!");
                }
                if (found) {
                    System.out.println("File was found");
                    System.out.println("Sending file " + fileName);
                    byte[] fileSend = new byte[65535];
                    InputStream inputStream = new FileInputStream(file);
                    inputStream.read(fileSend, 0, 65535);
                    DatagramPacket send = new DatagramPacket(fileSend, inputStream.toString().getBytes().length, ip, 8888);
                    datagramSocket.send(send);
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}
