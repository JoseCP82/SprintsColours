package camara.jose.utils.utils;

public class ThreadStatus {
    private boolean suspended; //false -> hilo corriendo  // true -> hilo parado

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
