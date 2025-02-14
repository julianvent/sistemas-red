import java.util.Random;

public class AEThread implements Runnable {
    AEThread() {
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " en ejecucion");
        try {
            printAtoE();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void printAtoE() throws InterruptedException {
        for (char c = 'A'; c <= 'E'; c++) {
            // Random random = new Random();
            // int miliseconds = random.nextInt(2001);

            Thread.sleep(2000);
            System.out.println(c + " zzz: " + 2);
        }
    }
}
