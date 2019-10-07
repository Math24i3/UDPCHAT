public class HeartBeatThread extends Thread{


    String username;

    public HeartBeatThread(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        while (true) {


            try {
                System.out.println(username + " is still connected");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(username + " is not connected anymore");
            }
        }
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
