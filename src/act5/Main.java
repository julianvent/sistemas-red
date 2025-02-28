package act5;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JLabel light1 = new JLabel();
        JLabel light2 = new JLabel();

        light1.setFont(new Font("Arial", Font.PLAIN, 30));
        light2.setFont(new Font("Arial", Font.PLAIN, 30));

        light1.setBounds(200, 50, 400, 30);
        light2.setBounds(200, 150, 400, 30);

        JFrame frame = new JFrame();
        frame.setSize(500, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(light1);
        frame.add(light2);

        cycle(5000, light1);
        cycle(7000, light2);

        frame.setVisible(true);
    }

    static void cycle(long milis, JLabel light) {
        new Thread(() -> {
            String[] colors = {"VERDE", "AMARILLO", "ROJO"};
            light.setText(colors[0]);
            int i = 0;

            while (true) {
                try {
                    Thread.sleep(milis);
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted");
                }
                light.setText(colors[++i % colors.length]);
            }
        }).start();
    }
}
