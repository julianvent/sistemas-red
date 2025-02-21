package act3;

import javax.swing.*;

public class Main {

    private static void setActionListeners(CounterThread counter, JButton button) {
        button.addActionListener(e -> {
            if (!counter.isAlive()) {
                counter.start();
                button.setText("Stop");
            } else if (!counter.isPaused()) {
                counter.pause();
                button.setText("Start");
            } else {
                counter.resume();
                button.setText("Stop");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        CounterThread counter1 = new CounterThread();
        counter1.getValueLabel().setBounds(100, 20, 220, 50);

        CounterThread counter2 = new CounterThread();
        counter2.getValueLabel().setBounds(100, 100, 220, 50);


        JButton startThreadButton1 = new JButton("Start");
        startThreadButton1.setBounds(100, 60, 100, 30);

        JButton startThreadButton2 = new JButton("Start");
        startThreadButton2.setBounds(100, 140, 100, 30);

        frame.add(counter1.getValueLabel());
        frame.add(counter2.getValueLabel());
        frame.add(startThreadButton1);
        frame.add(startThreadButton2);

        // Acciones
        setActionListeners(counter1, startThreadButton1);
        setActionListeners(counter2, startThreadButton2);

        frame.setSize(300, 250);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
