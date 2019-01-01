import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;


public class UI extends JFrame implements ActionListener {


    private static final long serialVersionUID = 1L;

    private JButton openButton = new JButton("Choose a file");
    private JButton directoryButton = new JButton("Choose a Directory");
    private JLabel label = new JLabel("");
    private JButton goButton = new JButton("Go!");
    private JFileChooser chooser = new JFileChooser();
    private File file;

    private FileFilter filter = new FileFilter() {
        public boolean accept(File f) {
            return f.getName().endsWith(".srt") || f.getName().endsWith(".txt");
        }

        public String getDescription() {
            return "Text";
        }
    };

    public UI() {
        super("مبدل");
        openButton.setSize(150, 150);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.add(directoryButton, BorderLayout.NORTH);
        mainPanel.add(openButton, BorderLayout.SOUTH);
        add(mainPanel);
        JPanel goPanel = new JPanel();
        goPanel.add(goButton, BorderLayout.CENTER);
        label.setText("File #");
        goPanel.add(label, BorderLayout.SOUTH);
        add(goPanel);
        directoryButton.addActionListener(this);
        openButton.addActionListener(this);
        goButton.addActionListener(this);
        add(goButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == goButton) {
            if (file == null) {
                new ErrorWindow("Please choose a file.",this.getLocation());
                return;
            }

            File parentDir = new File(file.getParent() + "/new");
            if (!parentDir.exists()) {
                parentDir.mkdir();
            }
            System.out.println(file);
            System.out.println(parentDir);
            if (file.isDirectory()) {
                processDirectory(file.getPath(), parentDir + "/" + file.getName());
            } else {
                processFile(file.getPath(), parentDir + "/" + file.getName());
            }
            new ErrorWindow("Success!",this.getLocation());
        } else if (ae.getSource() == openButton) {
            chooser.setFileFilter(filter);
            chooser.setMultiSelectionEnabled(false);
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();
                label.setText("File  : " + file.getAbsolutePath());

            }
        } else if (ae.getSource() == directoryButton) {
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setMultiSelectionEnabled(false);
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {

                file = new File(chooser.getSelectedFile().getAbsolutePath());
                label.setText("File # : " + file.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith(".srt") || name.endsWith(".txt");
                    }
                }).length);
            }
        }

    }

    private void processFile(String source, String destination) {
        try {
            CustomFileConverter.createFile(source, destination);
        } catch (IOException e) {
            new ErrorWindow(e.getMessage(),this.getLocation());
        }
    }

    private void processDirectory(String source, String destination) {
        File file = new File(source);
        if (!file.isDirectory()) {
            processFile(source, destination);
        } else {
            for (String fileName : file.list()) {
                processFile(file.getPath() + "/" + fileName, destination);
            }
        }
    }

}
