public class HeartBeatThread implements Runnable{


    String username;
    int run = 0;


    @Override
    public void run() {
        while (run==0) {


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
