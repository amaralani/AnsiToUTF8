import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;


public class UI extends JFrame implements ActionListener{


		JButton openButton = new JButton("Browse...");
    JLabel label = new JLabel("");
		JButton goButton = new JButton("Go!");
		JFileChooser chooser = new JFileChooser();
		File file;
    File parentDir;
		
		FileFilter filter = new FileFilter() {
            public boolean accept (File f){
                if (f.getName().endsWith(".srt") || f.getName().endsWith(".txt"))
                    return true;

                return false;
            }

            public String getDescription() {

                return "Text";
            }
        };
		
		public UI(){
			super("مبدل");
			openButton.setSize(150, 150);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setLayout(new FlowLayout());
			add(openButton);
			openButton.addActionListener(this);
            goButton.addActionListener(this);
            add(goButton);
            add(label , BorderLayout.SOUTH);
		}
		public static void main(String[] args){
			UI m = new UI();
			m.setLocation(200, 200);
			m.setSize(200, 200);
			m.setVisible(true);
		}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == goButton){
            File parentDir = new File(file.getParent()+"/new");
            if(!parentDir.exists()){
                parentDir.mkdir();
            }
            for(String filename : file.list()){
                   processFile(filename);
            }
            ErrorWindow errorWindow = new ErrorWindow("Success!");
            errorWindow.setLocation(this.getLocation());
            errorWindow.setSize(100,100);
            errorWindow.setVisible(true);
		}else if(ae.getSource() == openButton){
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setMultiSelectionEnabled(false);
			int result = chooser.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION){
				file = new File(chooser.getSelectedFile().getAbsolutePath());
                label.setText("File # : "+String.valueOf(file.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith(".srt") || name.endsWith(".txt");
                    }
                }).length));
			}
		}
		
	}
    private Boolean processFile(String filename){
        if(new File(filename).isDirectory()){
            for(String childFile : new File(filename).list()){
                processFile(childFile);
            }
        }else if(filename.endsWith(".srt")) {
            CustomFileConverter converter = new CustomFileConverter();
            try {
                converter.createFile(file.getPath(), parentDir + "/" + file.getName());
                return true;
            } catch (IOException e) {
                ErrorWindow errorWindow = new ErrorWindow(e.getMessage());
                return false;
            }
        }
        return false;
    }

	
}
