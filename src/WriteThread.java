import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread {

    PrintWriter writer;
    Socket socket;
    Client client;

    public WriteThread(Socket socket, Client client ) {
        this.socket = socket;
        this.client = client;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

        }catch (IOException e){
            System.out.println("Error getting output stream: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        Scanner userInput = new Scanner(System.in);
        String userName;
        System.out.println("Please enter a username, so other users can see who they are chatting with :)");            //Add username


        userName = userInput.nextLine();

        client.setUserName(userName);
        writer.println(userName);

        String text;
        System.out.println("Welcome to the chat, "+userName + "!");
        System.out.println("_______________________________________");

        writer.println(userName);
        do {
            text = userInput.nextLine();                                                                                //Prints the message
            writer.println(text);

        }while (!text.equals("exit"));

        if (text.equals("exit")){
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Cannot write to the server: " + e.getMessage());

            }
        }
    }
}
