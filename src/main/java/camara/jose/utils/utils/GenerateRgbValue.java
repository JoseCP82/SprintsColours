package camara.jose.utils.utils;

import camara.jose.log.Log;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class GenerateRgbValue implements Runnable {

    /**
     * Atributos de clase
     */
    private ThreadStatus threadStatus = new ThreadStatus();
    private int value;
    private VBox vbox;
    private Button btnStartStop;
    private GenerateRgbValue[] generateRgbValue;
    private Chronometer chronometer;
    private String name;
    private Label lblWin;

    /**
     * Constructor parametrizado
     * @param vbox Elemento Vbox de la interfaz grafica
     * @param btnStartStop Elemento Button de la interfaz grafica
     * @param chronometer Objeto de tipo cronometro
     * @param name Nombre del hilo
     * @param lblWin Elemento Label de la interfaz grafica
     */
    public GenerateRgbValue(VBox vbox, Button btnStartStop, Chronometer chronometer, String name, Label lblWin) {
        this.vbox=vbox;
        this.btnStartStop=btnStartStop;
        this.threadStatus.setSuspended(false);
        this.value=0;
        this.chronometer=chronometer;
        this.name=name;
        this.lblWin=lblWin;
    }

    /**
     * Getters y Setters
     */
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

    /**
     * Metodo run del hilo
     */
    @Override
    public void run() {
        int vBoxIndex = this.vbox.getChildren().size()-1;
        int numPaint=0;
        int sleepTime=0;
        while (!this.threadStatus.getSuspended()){
            if(value==255){
                Platform.runLater(() ->{
                    lblWin.setStyle("-fx-text-fill: "+name.toLowerCase());
                    lblWin.setText(name+" colour win the sprint!!!");
                });
                this.chronometer.getThreadStatus().setSuspended(true);
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

    /**
     * Restablece los atributos a su valor por defecto
     */
    private void reset() {
        for (int i=0; i<25; i++) {
            this.vbox.getChildren().get(i).setVisible(false);
        }
        this.value=0;
        this.btnStartStop.setDisable(false);
    }
}
