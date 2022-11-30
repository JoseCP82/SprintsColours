package camara.jose.utils.utils;

import camara.jose.log.Log;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import static java.lang.System.exit;

public class GenerateRgbValue implements Runnable {

    private ThreadStatus threadStatus = new ThreadStatus();
    private int value=0;
    private VBox vbox;
    private Button btnStartStop;

    public GenerateRgbValue(VBox vbox, Button btnStartStop) {
        this.vbox=vbox;
        this.btnStartStop=btnStartStop;
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
        int vBoxIndex = this.vbox.getChildren().size()-1;
        int numPaint=0;
        while (!this.threadStatus.getSuspended()){
            if(value==255){
                this.btnStartStop.setDisable(true);
                this.threadStatus.setSuspended(true);
            }
            else {
                try {
                    Thread.sleep(RandomNumber.randomNumber(1, 120));
                    this.value++;
                    numPaint=Math.round((value*this.vbox.getChildren().size())/255);
                    if(vBoxIndex-numPaint>=0){
                        this.vbox.getChildren().get(vBoxIndex-numPaint).setVisible(true);
                    }
                    this.threadStatus.continueThread();
                } catch (InterruptedException e) {
                    Log.warningLogging(e + "");
                    reset();
                }
            }
        }
    }

    private void reset() {
        for (int i=0; i<10; i++) {
            this.vbox.getChildren().get(i).setVisible(false);
        }
        this.value=0;
        this.btnStartStop.setDisable(false);
    }
}
