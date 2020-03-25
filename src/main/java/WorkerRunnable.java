import java.io.*;
import java.net.Socket;


public class WorkerRunnable implements Runnable{

    private Socket clientSocket;
    private int connectionAmount;
    private MultiThreadedServer server;

    public WorkerRunnable(Socket clientSocket, int connectionAmount, MultiThreadedServer server) {
        this.clientSocket = clientSocket;
        this.connectionAmount = connectionAmount;
        this.server = server;
    }

    public void run() {
        BufferedReader in;
        try {
            System.out.println("New thread, total running: " + server.runningThreads);
            System.out.println("Connection to client established on client address " + clientSocket.getInetAddress() + " on connection " + connectionAmount);

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String input = in.readLine();
            String fileName = input.split("!")[1];

            File file = null;
            boolean found = false;

            System.out.println("Looking for file " + fileName);

            try {
                file = new File(fileName);
                found = true;
            } catch (Exception e) {
                System.out.println("File was not found!");
            }

            if (found) {
                System.out.println("File was found");
                System.out.println("Sending file " + fileName);
                byte[] bytes = new byte[16 * 1024];
                InputStream inputStream = new FileInputStream(file);
                OutputStream out = clientSocket.getOutputStream();
                int count;
                while ((count = inputStream.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                }

                System.out.println("File was send");
                System.out.println("Closing thread, connection " + connectionAmount);
                server.runningThreads = server.runningThreads-1;
                out.close();
                in.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}