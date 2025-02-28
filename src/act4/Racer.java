package act4;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Racer extends Thread {
    private final JProgressBar progressBar;
    private final Vector<Racer> places;
    private final JButton startButton;
    private static int position = 0;
    private static final StringBuilder podium = new StringBuilder();

    public Racer(String name, JProgressBar progressBar, Vector<Racer> places, JButton startButton) {
        super(name);
        this.progressBar = progressBar;
        this.places = places;
        this.startButton = startButton;
        progressBar.setValue(0);
    }

    @Override
    public void run() {
        Random rand = new Random();

        places.add(this);

        while (progressBar.getValue() < progressBar.getMaximum()) {
            progressBar.setValue(progressBar.getValue() + rand.nextInt(16) + 10);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // System.out.println(this.getName());
        updatePodium(this);

        places.remove(this);

        if (places.isEmpty()) {
            startButton.setEnabled(true);
            showPodium();
            restartRace();
        }
    }

    private synchronized void restartRace() {
        podium.setLength(0);
        position = 0;
    }

    private synchronized void updatePodium(Thread racer) {
        podium.append(++position).append("° place is ").append(racer.getName()).append("\n");
    }

    private void showPodium() {
        JOptionPane.showMessageDialog(null, podium, "Podium", JOptionPane.INFORMATION_MESSAGE);
    }
}
