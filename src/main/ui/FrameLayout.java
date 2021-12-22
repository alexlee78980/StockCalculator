package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//abstract class that creates the JFrame layout and print log when window closed
//windowClosed inspired from https://stackoverflow.com/questions/12601004/do-something-before-window-closes-after-user-presses-x
public class FrameLayout extends JFrame {
    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 600;

    public FrameLayout() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                for (Event next : EventLog.getInstance()) {
                    System.out.println(next.toString());
                }
                System.exit(1);
            }
        });
        setTitle("CPSC 210: Stock Calculator");
        this.pack();
        this.setVisible(true);
        this.setSize(WIDTH, HEIGHT);
    }
}
