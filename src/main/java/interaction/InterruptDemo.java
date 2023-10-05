package interaction;

public class InterruptDemo {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    if (isInterrupted()) {
                        System.out.println("Interrupted!");
                        // some cleanup work
                        return;
                    }
                    System.out.println(i);
                }
            }
        };
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        thread.interrupt();
    }
}
