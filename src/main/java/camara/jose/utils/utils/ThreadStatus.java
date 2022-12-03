package camara.jose.utils.utils;

public class ThreadStatus {

    /**
     * Atributos de clase
     */
    private boolean suspended; //false -> hilo corriendo  // true -> hilo parado

    /**
     * Getters y Setters
     */
    public boolean getSuspended() {
        return suspended;
    }

    public synchronized void setSuspended(boolean status) {
        this.suspended=status;
        notifyAll();
    }

    public synchronized void continueThread() throws InterruptedException {
        while(this.suspended) {
            wait();
        }
    }
}
