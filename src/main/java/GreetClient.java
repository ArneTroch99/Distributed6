import java.io.*;
import java.net.Socket;

public class GreetClient {


    private Socket clientSocket;
    private PrintWriter out_print;    //Output of tcp socket
    private InputStream in;        //Input of tcp socket
    private OutputStream out;        //File output

    public void startConnection(String ip, int port, String fileName) throws IOException {
        clientSocket = new Socket(ip, port);
        out_print = new PrintWriter(clientSocket.getOutputStream(), true);
        in = clientSocket.getInputStream();
        out = new FileOutputStream(fileName);
    }

    public void sendMessage(String msg) throws IOException {
        out_print.println(msg);
    }

    public void receiveInputStream() {

        byte[] bytes = new byte[16 * 1024];

        int count;
        try {
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopConnection() throws IOException {
        out_print.close();
        in.close();
        out.close();
        clientSocket.close();
    }
}
