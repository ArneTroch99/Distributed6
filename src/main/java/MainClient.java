import java.io.IOException;

public class MainClient {

    public static void main(String[] args) throws IOException {

        String fileName = "test1.txt";
        GreetClient client = new GreetClient();
        client.startConnection("10.0.13.14", 8888, fileName);
        client.sendMessage("!" + fileName);
        client.receiveInputStream();
        client.stopConnection();

        fileName = "test2.txt";
        client.startConnection("10.0.13.14", 8888, fileName);
        client.sendMessage("!" + fileName);
        client.receiveInputStream();
        client.stopConnection();

        fileName = "test3.txt";
        client.startConnection("10.0.13.14", 8888, fileName);
        client.sendMessage("!" + fileName);
        client.receiveInputStream();
        client.stopConnection();

        fileName = "test4.txt";
        client.startConnection("10.0.13.14", 8888, fileName);
        client.sendMessage("!" + fileName);
        client.receiveInputStream();
        client.stopConnection();

    }
}
