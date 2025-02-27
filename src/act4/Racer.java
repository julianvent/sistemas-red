package act4;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Racer extends Thread {
    private JProgressBar progressBar;
    private ArrayList<Racer> places;
    private JButton startButton;
    private static int position = 0;
    public static final StringBuilder podium = new StringBuilder("Podium:\n");

    public Racer(String name, JProgressBar progressBar, ArrayList<Racer> places, JButton startButton) {
        super(name);
        this.progressBar = progressBar;
        this.places = places;
        this.startButton = startButton;
        progressBar.setStringPainted(true);
        progressBar.setValue(0);
    }

    public void run() {
        Random rand = new Random();
        places.add(this);

        while (progressBar.getValue() < progressBar.getMaximum()) {
            progressBar.setValue(progressBar.getValue() + rand.nextInt(15) + 10);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(this.getName());

        synchronized (podium) {
            podium.append(++position).append("° place is ").append(this.getName()).append("\n");
        }

        places.remove(this);

        if (places.isEmpty()) {
            startButton.setEnabled(true);
            JOptionPane.showMessageDialog(null, podium);
            restartRace();
        }
    }

    private static void restartRace() {
        synchronized (podium) {
            podium.setLength(0);
            podium.append("Podium:\n");
            position = 0;
        }
    }
}
