import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Server {
    //Main for running the server
    public static void main(String [] args) throws IOException {

        Server server = new Server();

        //Execute the run server method();
        server.runServer();
    }

//_________________________________________________________________________


    private int port = 9898;                                                                    //Port.
    private HashMap<String, String> users = new HashMap<>();                                            //List of usernames connected.
    private Set<ClientHandlerThread> userThreads = new HashSet<>();                             //Set of Threads that handles the communication with clients.

    public void runServer(){

        try {
            ServerSocket serverSocket = new ServerSocket(port);                                 //A serversocket.
            System.out.println("Server is listening on port: "+port);

            while (true){
                Socket socket = serverSocket.accept();

                ClientHandlerThread newUser = new ClientHandlerThread(socket, this);      //Creates a clientHandller thread.
                System.out.println("New client is connected: " + socket.getLocalAddress());

                userThreads.add(newUser);                                                       //Adds it to the set of threads.
                newUser.start();                                                                //Starts the thread (Run method in ClientHandlerThread CLASS).

            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    void broadCast(String message, ClientHandlerThread thisUser){                               //Method to broadcast a message(Send message to all connected clients).
        for (ClientHandlerThread user: userThreads) {
            if(user != thisUser){               //To make sure the message does not sent back to the sender.
                user.sendMessage(message);
            }
        }
    }

    void sendMessage(String message, ClientHandlerThread user){
        for (ClientHandlerThread thread: userThreads
             ) {
            if(thread == user){
                thread.sendMessage(message);
            }

        }
    }

    void addUser(String username, InetAddress inetAddress){//Add the username to the list of usernames

        
        users.put(username, inetAddress.toString()+":"+port);
    }

    void removeUser(String username, InetAddress inetAddress, ClientHandlerThread aUser){                                //Removes the username and the thread:
        boolean removed = users.remove(username, inetAddress.toString() );
        if (removed){
            userThreads.remove(aUser);
            System.out.println(username + " - has quitted the chat.");
        }
    }

    //__________________________________________________________________
    //Getters 'n setters
    boolean hasUsers() {
        return !this.users.isEmpty();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public HashMap<String, String> getUsers() {

        return users;
    }

    public void setUsers(HashMap<String, String> userNames) {
        this.users = userNames;
    }

    public Set<ClientHandlerThread> getUserThreads() {
        return userThreads;
    }

    public void setUserThreads(Set<ClientHandlerThread> clientHandlerThreads) {
        this.userThreads = clientHandlerThreads;
    }
}
