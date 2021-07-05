package EchoServerHW;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyEchoServer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);;
        Socket socket = null;
        try(ServerSocket serverSocket = new ServerSocket(6564)) {
            System.out.println("Сервер запущен ожидаем подключение клинета ");
            socket = serverSocket.accept();
            System.out.println(" Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream((socket.getOutputStream()));
            while (true){
                String str = in.readUTF();
                if(str.equals("/end")){
                    break;
                }
                final String s = scanner.nextLine();
                try {
                    out.writeUTF(s);
                }catch (IOException e){
                    e.printStackTrace();
                }

            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
