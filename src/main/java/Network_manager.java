import java.io.*;
import java.net.*;

public class Network_manager {

    public void server() {
        try {

            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();//establishes connection
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String str = dis.readUTF();
            System.out.println("message= " + str);
            ss.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Client
    public void client() {

        try {

            Socket s = new Socket("localhost", 6666);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("Hello Server");
            dout.flush();
            dout.close();
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
