package EchoServerHW;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MyEchoClient {
    private DataOutputStream outputStream;
    private Socket socket;
    private DataInputStream inputStream;
    private Scanner scanner;

    public static void main(String[] args) {
        new MyEchoClient();

    }

    public MyEchoClient() {
        scanner = new Scanner(System.in);
        openConnection();
        while (true){
        sendMessage();
        }

    }

    private void openConnection(){
        try {
            socket = new Socket("localhost", 6564);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            String s;
            Client client = new Client(scanner, outputStream);
            Thread clientThread = new Thread(client);
            clientThread.start();

            while (true) {
                s = inputStream.readUTF();
                if (s.equals("/end")) {
                    break;
                }
                System.out.printf("Сервер:  %s%n", s);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        final String s = scanner.nextLine();
        try {
            outputStream.writeUTF("Клиент: "+ s);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
