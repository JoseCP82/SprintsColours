package camara.jose.utils.utils;

public class GenerateRgbValue implements Runnable {

    private ThreadStatus threadStatus = new ThreadStatus();
    private int value=0;

    public GenerateRgbValue() { this.threadStatus.setSuspended(false); }

    public int getValue() { return this.value; }

    public ThreadStatus getThreadStatus() {
        return this.threadStatus;
    }

    public void setThreadStatus(ThreadStatus threadStatus) {
        this.threadStatus = threadStatus;
    }

    @Override
    public void run() {
        while (value<=255 || !this.threadStatus.getSuspended()){
            if(value>255){
                this.threadStatus.setSuspended(true);
            }
            try {
                Thread.sleep(RandomNumber.randomNumber(1,100));
                this.value++;
                this.threadStatus.continueThread();
            } catch (InterruptedException e) {
               //e.printStackTrace();
                System.exit(0);
            }
        }
    }
}
