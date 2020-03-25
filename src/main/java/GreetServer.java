import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }

        Socket socket = null;

        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }

        System.out.println("Connection to client established");

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String fileName = in.readLine();
        String[] splitted = fileName.split("!");

        File file = null;
        boolean found = false;

        try {
            file = new File(splitted[1]);
            found = true;
        } catch (Exception e) {
            System.out.println("File was not found!");
            out.println("File was not found!");
        }

        if (found) {
            System.out.println("Sending file " + splitted[1]);
            byte[] bytes = new byte[16 * 1024];
            InputStream in = new FileInputStream(file);
            OutputStream out = socket.getOutputStream();
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }

            System.out.println("File was send");

            out.close();
            in.close();
            socket.close();
        }

    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}