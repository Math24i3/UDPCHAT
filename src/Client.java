import javax.imageio.IIOException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    //Main for running client
    public static void main(String []args){
        Client client = new Client("Localhost");
        client.runClient();
    }

    //________________________________________________________________________________

    private String hostName;
    private int port = 9898;
    private String userName;

    public Client(String hostName) {                                                                                //Constructor
        this.hostName = hostName;
    }

    public void runClient(){                                                                                        //Run client method
        try {
            Socket socket = new Socket(hostName, port);                                                             //Socket for the client
            new ReadThread(socket, this).start();                                                             //Readthread and writethread starts, so it can listen and write at the same time
            new WriteThread(socket, this).start();

        } catch (UnknownHostException e) {
            System.out.println("You are not connected to the chat-server. it might be down, or the port is not correct.");
        } catch (IOException e) {
            System.out.println("I/O error: "+e.getMessage());
        }
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
