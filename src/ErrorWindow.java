import javax.swing.*;
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
        this.setModal(true);
        this.message.setText(message);
        add(this.message);
        close.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
