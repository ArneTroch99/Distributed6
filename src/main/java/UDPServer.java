import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    private int port;;

    public UDPServer(int port){
        this.port = port;
    }

    public void runServer() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(port);
            byte[] receive = new byte[65535];

            DatagramPacket packet;
            while(true){
                packet = new DatagramPacket(receive, receive.length);

                datagramSocket.receive(packet);

                InetAddress ip = packet.getAddress();

                System.out.println("Received data: " + data(receive) + " from " + ip.toString());

                if (data(receive).toString().equals("hello")){
                    String message = "Fist My Ass";
                    DatagramPacket send = new DatagramPacket(message.getBytes(), message.getBytes().length, ip, 8888);
                    datagramSocket.send(send);
                    break;
                }
            }
        }catch (Exception e){
            System.out.println("Error");
        }
    }

    private static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }

}
