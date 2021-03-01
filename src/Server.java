import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Server {

    private ServerSocket server;

    public Server (int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void runListener1() {
        while (true) {      //loop waiting for client
            try {
                System.out.println("Waiting for Client at port..." + server.getLocalPort());
                Socket client = server.accept();                                        // (1) client annehmen
                DataInputStream input = new DataInputStream(client.getInputStream());   //daten vom client (als inputstream) empfangen
                System.out.println(input.readUTF());

                DataOutputStream output = new DataOutputStream(client.getOutputStream());
                output.writeUTF("Server: Message received");

                client.close();
            } catch (Exception e) {
                e.printStackTrace();
                break;      //bei fehler loop unterbrechen
            }
        }
    }

    public static void main(String[] args) {

        Server s = new Server(4444);
        s.runListener1();
    }
}