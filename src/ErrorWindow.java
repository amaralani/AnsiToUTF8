import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: maralani
 * Date: 11/9/2014
 * Time: 11:48 AM
 */
public class ErrorWindow extends JDialog implements ActionListener{

    JLabel message = new JLabel();
    JButton close = new JButton("Close");
    public ErrorWindow(String message){
        super();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.message.setText(message);
        add(this.message,BorderLayout.CENTER);
        close.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
