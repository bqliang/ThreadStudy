package interaction;

public class WaitDemoWithBug {
    private String name;

    private synchronized void initName() {
        name = "ABC";
    }

    private synchronized void printName() {
        System.out.println("name is " + name);
    }

    public static void main(String[] args) {
        WaitDemoWithBug demo = new WaitDemoWithBug();
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
