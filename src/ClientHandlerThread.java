import java.io.*;
import java.net.Socket;

public class ClientHandlerThread extends Thread {

    private Socket socket;
    private Server server;
    private PrintWriter writer;


    //Constructor for the thread
    public ClientHandlerThread(Socket socket, Server server){                                               //Construcktor for client handler (is used in Server), to manage messages to be send to clients)
        this.socket = socket;
        this.server = server;

    }


    //This code gets executed when the thread starts
    @Override
    public void run() {                                                                                     //Thread run method
        try {
            InputStream input = socket.getInputStream();                                                    //get input from socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));                       //Read from the inputstream

            OutputStream output = socket.getOutputStream();                                                 //Output
            writer = new PrintWriter(output, true);

            printUsers();                                                                                   //Prints the users

            String username = reader.readLine();
            server.addUserName(username);

            String serverMessage = "New User is connected. Please say hello to: "+ username + ".\n";
            server.broadCast(serverMessage, this);

            String clientMessage;
            do {
                clientMessage = reader.readLine();
                serverMessage = username + ": " + clientMessage;
                server.broadCast(serverMessage, this);                                              //gets the message and broadcast it to clients

            }while (!clientMessage.equals("exit"));                                                         //Terminates the socket
                server.removeUser(username, this);
                socket.close();

                serverMessage = username + ", has quitted.";
                server.broadCast(serverMessage, this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //_______________________________________________________________________
    //PrintUsers
    void printUsers(){                                                                                         //Prints the users who arte connected to the server
        if (server.hasUsers()){
            writer.println("Users connected: " + server.getUsernames());
        } else {
            writer.println("Currently no other users.");
        }
    }

    void sendMessage(String message){
        writer.println(message);
    }




}
