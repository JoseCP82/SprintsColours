package camara.jose.utils.utils;

import camara.jose.log.Log;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class GenerateRgbValue implements Runnable {

    private ThreadStatus threadStatus = new ThreadStatus();
    private int value;
    private VBox vbox;
    private Button btnStartStop;
    private GenerateRgbValue[] generateRgbValue;

    public GenerateRgbValue(VBox vbox, Button btnStartStop) {
        this.vbox=vbox;
        this.btnStartStop=btnStartStop;
        this.threadStatus.setSuspended(false);
        this.value=0;
    }

    public int getValue() { return this.value; }

    public ThreadStatus getThreadStatus() {
        return this.threadStatus;
    }

    public void setThreadStatus(ThreadStatus threadStatus) {
        this.threadStatus = threadStatus;
    }

    public GenerateRgbValue[] getGenerateRgbValue() {
        return generateRgbValue;
    }

    public void setGenerateRgbValue(GenerateRgbValue[] generateRgbValue) {
        this.generateRgbValue = generateRgbValue;
    }

    @Override
    public void run() {
        int vBoxIndex = this.vbox.getChildren().size()-1;
        int numPaint=0;
        int sleepTime=0;
        while (!this.threadStatus.getSuspended()){
            if(value==255){
                this.btnStartStop.setDisable(true);
                for(GenerateRgbValue rgb : generateRgbValue){
                    rgb.threadStatus.setSuspended(true);
                }
            }
            else {
                try {
                    sleepTime=RandomNumber.randomNumber(1, 120);
                    Thread.sleep(sleepTime);
                    this.value++;
                    numPaint= (int) Math.ceil((value*this.vbox.getChildren().size())/255);
                    if(value<245){
                        this.vbox.getChildren().get(vBoxIndex-numPaint).setVisible(true);
                    }
                    if(value==255){
                        this.vbox.getChildren().get(0).setVisible(true);
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
        for (int i=0; i<25; i++) {
            this.vbox.getChildren().get(i).setVisible(false);
        }
        this.value=0;
        this.btnStartStop.setDisable(false);
    }
}
