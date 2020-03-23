import java.io.IOException;

public class MainClient{

    public static void main(String[] args) throws IOException {
       
        GreetClient client = new GreetClient();
        client.startConnection("10.0.13.14", 8888);
        client.sendMessage("!benny.json");
	client.receiveInputStream();
        client.stopConnection();

    }
}
