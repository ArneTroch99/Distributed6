import java.io.*;
import java.net.*;

public class GreetClient {
    
	
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void receiveInputStream(){

        InputStream in = null;
        OutputStream out = null;

        try {
            in = clientSocket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
        }

	try {
            out = new FileOutputStream("test.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
        }

        byte[] bytes = new byte[16*1024];

        int count;
        
	try{
		while ((count = in.read(bytes)) > 0) {
            		out.write(bytes, 0, count);
        	}
	}catch(Exception e){e.printStackTrace();}
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
