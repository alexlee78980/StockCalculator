package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//inspired on how to get images from https://www.youtube.com/watch?v=ntirmRhy6Fw
//splash screen that runs after login
public class SplashScreen extends FrameLayout implements ActionListener {
    private int count = 0;
    private JProgressBar progress;
    private String dataName;

    public SplashScreen(String dataName) {
        super();
        this.dataName = dataName;
        ImageIcon stock = new ImageIcon("data/stock.jpg");
        JLabel imageLabel = new JLabel(stock);
        this.setLayout(new FlowLayout());
        progress = new JProgressBar(0, 100);
        progress.setSize(new Dimension(10, 300));
        progress.setStringPainted(true);
        Timer time = new Timer(10, this);
        time.start();
        this.add(imageLabel);
        this.add(progress);
    }

    @Override
    //increase the count of timer by 1, if the count is 100 create new EnterStock and dispose of this object
    public void actionPerformed(ActionEvent e) {
        if (count == 100) {
            new EnterStock(dataName);
            dispose();
        }
        count++;
        progress.setValue(count);

    }
}


