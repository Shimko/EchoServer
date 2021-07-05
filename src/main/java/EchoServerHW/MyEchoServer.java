package EchoServerHW;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyEchoServer {
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;
    Socket socket = null;

    public static void main(String[] args) {
        new MyEchoServer();
    }
    public MyEchoServer(){
        scanner = new Scanner(System.in);
        serverChat();
        while (true){
            sendMessage();
        }
    }
    public void serverChat(){

        try(ServerSocket serverSocket = new ServerSocket(6564)) {
            System.out.println("Сервер запущен ожидаем подключение клинета ");
            socket = serverSocket.accept();
            System.out.println(" Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream((socket.getOutputStream()));
            String msg;
            Client client= new Client(scanner, out);
            Thread clientThread = new Thread(client);
            clientThread.start();

            while (true) {
                msg = in.readUTF();

                if (msg.equals("/end")) {
                    break;
                }
                System.out.printf("Сервер: %s%n",  msg);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void sendMessage() {
        final String s = scanner.nextLine();
        try {
            out.writeUTF("Сервер: "+ s);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}