package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//user inputs the data name that they want to load their stocks from or to save their stocks into
public class Login extends FrameLayout implements ActionListener {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 100;
    private JButton login;
    private JTextField dataName;

    public Login() {
        super();
        this.setLayout(new GridLayout(1, 3));
        JLabel dataNameLabel = new JLabel("Data Name");
        dataName = new JTextField();
        dataName.setPreferredSize(new Dimension(200, 40));
        login = new JButton("Login");
        login.addActionListener(this);
        this.add(dataNameLabel);
        this.add(dataName);
        this.add(login);
        this.setSize(WIDTH, HEIGHT);
    }

    //EFFECT: if login button clicked, set dataName text to "StockList" if its text value is empty, then
    // create new SplashScreen Object with parameter value of  dataName.getText()

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            if (dataName.getText().isEmpty()) {
                dataName.setText("StockList");
            }
            new SplashScreen(dataName.getText());
        }

    }
}
