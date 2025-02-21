package act3;

import javax.swing.*;

public class CounterThread extends Thread {
    private int value;
    private boolean paused;
    private JLabel valueLabel;

    CounterThread() {
        valueLabel = new JLabel("0");
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (paused) {
                    try {
                        System.out.println("pause");
                        this.wait();
                        System.out.println("resume");
                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                        return;
                    }
                }
            }

            add();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
                return;
            }
        }
    }

    private void add() {
        valueLabel.setText("" + ++value);
    }

    public boolean isPaused() {
        return paused;
    }

    public synchronized void pause() {
        paused = true;
    }

    public synchronized void resume() {
        paused = false;
        this.notify();
    }

    @Override
    public void interrupt() {
        super.interrupt();
        paused = true;
    }

    public JLabel getValueLabel() {
        return valueLabel;
    }
}
