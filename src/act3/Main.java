package act3;

import javax.swing.*;

public class Main {
    private static volatile boolean paused = false;

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();

        JLabel labelThread1 = new JLabel("0");
        JLabel labelThread2 = new JLabel("0");

        labelThread1.setBounds(100, 20, 220, 50);
        labelThread2.setBounds(100, 100, 220, 50);

        JButton startThreadButton1 = new JButton("Start");
        startThreadButton1.setBounds(100, 60, 100, 30);
        JButton startThreadButton2 = new JButton("Start");
        startThreadButton2.setBounds(100, 140, 100, 30);

        frame.add(labelThread1);
        frame.add(labelThread2);
        frame.add(startThreadButton1);
        frame.add(startThreadButton2);

        Object stop = new Object();

        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                if (!paused) {
                    labelThread1.setText("" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Stop: " + Thread.currentThread().isInterrupted());
                    }
                } else {
                    synchronized (stop) {
                        try {
                            stop.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
            System.out.println("Finish");
        });

        // Hilos

        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                if (!paused) {
                    labelThread1.setText("" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Stop: " + Thread.currentThread().isInterrupted());
                    }
                } else {
                    synchronized (stop) {
                        try {
                            stop.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
            System.out.println("Finish");
        });



        startThreadButton1.addActionListener(e -> {
            if (startThreadButton1.getText().equals("Start")) {
                startThreadButton1.setText("Stop");
                paused = false;

                synchronized (stop) {
                    stop.notify();
                }

                if (!thread1.isAlive()) {
                    thread1.start();
                }

            } else {
                startThreadButton1.setText("Start");
                paused = true;
            }
        });

        frame.setSize(300, 250);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
