import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner leser = new Scanner(System.in);

        while (true) {
            System.out.print("Your Message: ");
            String msg = leser.nextLine();
            if (msg.equals("/exit"))
                break;

            try {
                Socket client = new Socket("localhost", 4444);
                DataOutputStream output = new DataOutputStream(client.getOutputStream());

                output.writeUTF(client.getLocalSocketAddress() + " CLIENT A: " + msg);

                DataInputStream input = new DataInputStream(client.getInputStream());
                System.out.println(input.readUTF());

                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        leser.close();
    }

}
