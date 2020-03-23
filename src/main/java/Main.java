import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Fck you Siemendfgfgddfg");

        System.out.println("Giles heeft een kleine, Siemen een gigantische, Arne nen wolkenkrabber");

        GreetClient client = new GreetClient();
        client.startConnection("localhost", 8888);
        client.sendMessage("Penis");
        client.stopConnection();

    }
}
