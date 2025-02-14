public class Main {
    public static void main(String[] args) {
        FiveThread five = new FiveThread("1 al 5");
        AEThread ae = new AEThread();

        five.start();

        Thread aeThread = new Thread(ae);
        aeThread.setName("A a la E");
        aeThread.start();
    }
}
