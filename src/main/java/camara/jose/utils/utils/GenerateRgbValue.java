package camara.jose.utils.utils;

import camara.jose.log.Log;
import javafx.scene.layout.VBox;

public class GenerateRgbValue implements Runnable {

    private ThreadStatus threadStatus = new ThreadStatus();
    private int value=0;
    private VBox vbox;

    public GenerateRgbValue(VBox vbox) {
        this.vbox=vbox;
        this.threadStatus.setSuspended(false);
    }

    public int getValue() { return this.value; }

    public ThreadStatus getThreadStatus() {
        return this.threadStatus;
    }

    public void setThreadStatus(ThreadStatus threadStatus) {
        this.threadStatus = threadStatus;
    }

    @Override
    public void run() {
        int index = 9;
        float max = 0f;
        while (value<=255 || !this.threadStatus.getSuspended()){
            if(value>255){
                this.threadStatus.setSuspended(true);
            }
            else {
                try {
                    Thread.sleep(RandomNumber.randomNumber(1,100));
                    this.value++;
                    if(index>=0){
                        if(value>=max && value<=max+25.5f){
                            this.vbox.getChildren().get(index).setVisible(true);
                            index--;
                            max+=25.5f;
                        }
                    }
                    this.threadStatus.continueThread();
                } catch (InterruptedException e) {
                    Log.warningLogging(e+"");
                    System.exit(0);
                }
            }
        }
    }
}
