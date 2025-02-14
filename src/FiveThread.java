import java.util.Random;

public class FiveThread extends Thread {

    FiveThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " en ejecucion");
        try {
            printFive();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printFive() throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            Random random = new Random();
            int miliseconds = random.nextInt(2001);

            Thread.sleep(miliseconds);
            System.out.println(i + " zzz: " + (double)miliseconds / 1000);
        }
    }
}
