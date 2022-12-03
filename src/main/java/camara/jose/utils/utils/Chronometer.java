package camara.jose.utils.utils;

import camara.jose.log.Log;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class Chronometer extends  Thread {

    /**
     * Atributos de clase
     */
    private ThreadStatus threadStatus = new ThreadStatus();
    private String mixingTime;
    private Label lblTime;

    /**
     * Constructor parametrizado
     * @param lblTime Label con marca de tiempo
     */
    public Chronometer(Label lblTime) {
        this.lblTime=lblTime;
        this.threadStatus.setSuspended(false);
    }

    /**
     * Getters y Setters
     */
    public ThreadStatus getThreadStatus() {
        return this.threadStatus;
    }

    public void setThreadStatus(ThreadStatus threadStatus) {
        this.threadStatus = threadStatus;
    }

    public String getMixingTime() {
        return mixingTime;
    }

    /**
     * Metodo run del hilo
     */
    public void run() {
        int seconds=0;
        while (!this.threadStatus.getSuspended()){
            try {
                Thread.sleep(1000);
                calculateMixingTime(++seconds);
                Platform.runLater(() ->{
                    this.lblTime.setText(mixingTime);
                } );
                this.threadStatus.continueThread();
            } catch (InterruptedException e) {
                Log.warningLogging(e + "");
            }
        }
    }

    /**
     * Metodo que calcula horas, minutos y segundos y setea el atributo mixingTime
     * @param seconds Segundos usados para calcular el tiempo
     */
    private void calculateMixingTime(int seconds){
        int hours = seconds / 3600;
        seconds %= 3600;
        int minuts = seconds / 60;
        seconds %= 60;

        String stringHours=String.valueOf(hours);
        if(hours<10) {
            stringHours="0"+hours;
        }
        String stringMinuts=String.valueOf(minuts);
        if(minuts<10){
            stringMinuts="0"+minuts;
        }
        String stringSeconds=String.valueOf(seconds);
        if(seconds<10){
            stringSeconds="0"+seconds;
        }

        this.mixingTime = stringHours+":"+stringMinuts+":"+stringSeconds;
    }
}
