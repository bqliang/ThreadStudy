package interaction;

public class WaitDemoSolution {
    private String name;

    private synchronized void initName() {
        name = "ABC";
        // notify()
        notifyAll();
    }

    private synchronized void printName() {
        while (name == null) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("name is " + name);
    }

    public static void main(String[] args) {
        WaitDemoSolution demo = new WaitDemoSolution();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);  // 模拟耗时操作
            } catch (InterruptedException e) {
            }
            demo.initName();
        });
        thread.start();

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);  // 模拟耗时操作
            } catch (InterruptedException e) {
            }
            demo.printName();
        });
        thread2.start();
    }
}
