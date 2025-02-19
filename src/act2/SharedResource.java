package act2;

public class SharedResource {
    private int data;
    private boolean available = false;

    public synchronized void produce(int value) throws InterruptedException {
        while (available) {
            System.out.println("Disponible... esperando consumidor");
            wait();
        }

        data = value;
        available = true;
        System.out.println("Produced: " + value);
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while (!available) {
            System.out.println("No disponible... esperando productor");
            wait();
        }

        available = false;
        System.out.println("Consumed: " + data);
        notifyAll();
    }
}
