import java.io.IOException;

public class MainClient {

    public static void main(String[] args) throws Exception {

        /*int amount = 10;

        GreetClient[] clients = new GreetClient[amount];
        String[] fileNames = new String[amount];

        for (int i = 0; i < amount; i++) {
            clients[i] = new GreetClient();
            fileNames[i] = "test" + i + ".txt";
        }

        for (int i = 0; i < amount; i++) {
            clients[i].startConnection("10.0.13.14", 8888, fileNames[i]);
        }

        for (int i = 0; i < amount; i++) {
            clients[i].sendMessage("!" + fileNames[i]);
            clients[i].receiveInputStream();
        }

        for (GreetClient client : clients
        ) {
            client.stopConnection();
        }*/

	UDPClient client = new UDPClient();
	client.createSocket(8888);
	client.sendString("!test1.txt", 8888, "10.0.13.14");	
	client.receiveFile("test1.txt");
    }
}
