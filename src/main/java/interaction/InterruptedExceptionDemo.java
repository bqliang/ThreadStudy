package interaction;

public class InterruptedExceptionDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            // create some new files
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                // some cleanup work:
                // delete the files that were created at the beginning
                return; // end the thread
            }
            // process the files that were created at the beginning
        });

        thread.start();

        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
        }
        thread.interrupt();
    }
}