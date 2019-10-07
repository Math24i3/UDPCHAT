import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ReadThread extends  Thread{

    private BufferedReader reader;
    private Socket socket;
    private Client client;
    public ReadThread(Socket socket, Client client) {

        this.socket = socket;
        this.client = client;

        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

        }catch (IOException e){
            System.out.println("Cannot get the input stream: "+ e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true){
            try {
                String response = reader.readLine();                                                        //Prints a response from a client if ther is a response
                System.out.println(response);

                if (client.getUserName()!= null){
                    //System.out.println(client.getUserName() + ": ");

                }
            } catch (IOException e) {
                System.out.println("Cannot read the message from the server: " + e.getMessage());
                break;

            }
        }
    }
}
