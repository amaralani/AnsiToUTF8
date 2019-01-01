import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: maralani
 * Date: 11/9/2014
 * Time: 11:48 AM
 */
public class ErrorWindow extends JDialog implements ActionListener{

    public ErrorWindow(String message, Point Location){
        super();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        JLabel message1 = new JLabel();
        message1.setText(message);
        add(message1,BorderLayout.CENTER);
        JButton close = new JButton("Close");
        close.addActionListener(this);
        this.setLocation(this.getLocation());
        this.setSize(60, 60);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
