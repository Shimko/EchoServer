package EchoServerHW;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Client implements Runnable {
    private Scanner in;
    private DataOutputStream out;


    public Client(Scanner in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {

        String msgFromServer;
        try {
            while (true) {
                msgFromServer = in.nextLine();
                out.writeUTF(msgFromServer);

                if (msgFromServer.equals("/end")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
