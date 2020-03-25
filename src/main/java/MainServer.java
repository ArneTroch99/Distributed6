import java.io.IOException;
import java.util.Scanner;

public class MainServer {

    public static void main(String[] args) throws IOException {
        System.out.println("Giles heeft een kleine, Siemen een gigantische, Arne een wolkenkrabber");

        /*MultiThreadedServer server = new MultiThreadedServer(8888);
        new Thread(server).start();

        while(true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equals("stop"))
                break;
        }

        server.stop();*/

        UDPServer server = new UDPServer(8888);
        server.runServer();

    }
}
