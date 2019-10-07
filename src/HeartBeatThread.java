import java.io.IOException;
import java.net.Socket;

public class HeartBeatThread extends Thread{


    String username;
    Socket socket;

    public HeartBeatThread(String username, Socket socket) {
        this.username = username;
        this.socket = socket;

    }

    @Override
    public void run() {
        try {
            while (true) {

                if (socket.isClosed()){
                    System.out.println(username + " is not connected anymore");
                    break;
                } else{
                    try {
                        System.out.println(username + " is still connected");
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        System.out.println(username + " is not connected anymore");
                        break;
                    }
                }

            }
        } catch (Exception e) {
        e.printStackTrace();
    }
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
