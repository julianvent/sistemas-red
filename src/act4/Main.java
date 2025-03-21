package act4;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        JProgressBar bar1 = new JProgressBar(0, 1000);
        JProgressBar bar2 = new JProgressBar(0, 1000);
        JProgressBar bar3 = new JProgressBar(0, 1000);
        Vector<Racer> places = new Vector<>();

        bar1.setBounds(20, 50, 400, 30);
        bar2.setBounds(20, 100, 400, 30);
        bar3.setBounds(20, 150, 400, 30);

        bar1.setStringPainted(true); bar2.setStringPainted(true); bar3.setStringPainted(true);

        JButton button = new JButton("Start race");
        button.setBounds(20, 200, 100, 30);

        JFrame frame = new JFrame("Bar Racer 2000");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        frame.add(bar1);
        frame.add(bar2);
        frame.add(bar3);
        frame.add(button);

        button.addActionListener(e -> {
           new Racer("Barra 1", bar1, places, button).start();
           new Racer("Barra 2", bar2, places, button).start();
           new Racer("Barra 3", bar3, places, button).start();
           button.setEnabled(false);
        });

        frame.setLayout(null);
        frame.setVisible(true);
    }
}
