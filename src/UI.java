import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;


public class UI extends JFrame implements ActionListener{


	private static final long serialVersionUID = 1L;

	JButton openButton = new JButton("Choose a file");
	JButton directoryButton = new JButton("Choose a Directory");
	JLabel label = new JLabel("");
	JPanel mainPanel = new JPanel();
	JPanel goPanel = new JPanel();
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
			mainPanel.add(directoryButton,BorderLayout.NORTH);
			mainPanel.add(openButton, BorderLayout.SOUTH);
			add(mainPanel);
			goPanel.add(goButton,BorderLayout.CENTER);
            label.setText("File #");
			goPanel.add(label , BorderLayout.SOUTH);
			add(goPanel);
			directoryButton.addActionListener(this);
			openButton.addActionListener(this);
            goButton.addActionListener(this);
            add(goButton);
		}
		public static void main(String[] args){
			UI m = new UI();
			m.setLocation(200, 200);
			m.setSize(300, 100);
			m.setVisible(true);
		}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == goButton){
			if (file == null){
				ErrorWindow errorWindow = new ErrorWindow("Please choose a file.");
                errorWindow.setLocation(this.getLocation());
                errorWindow.setSize(60,60);
                errorWindow.setVisible(true);
                return;
			}
			
			parentDir = new File(file.getParent()+"/new");
            if(!parentDir.exists()){
                parentDir.mkdir();
            }
            System.out.println(file);
			System.out.println(parentDir);
            if(file.isDirectory()){
            	processDirectory(file.getPath() , parentDir + "/"+file.getName());
            }else{
            	processFile(file.getPath() , parentDir + "/"+file.getName());
            }
            ErrorWindow errorWindow = new ErrorWindow("Success!");
            errorWindow.setLocation(this.getLocation());
            errorWindow.setSize(80,80);
            errorWindow.setVisible(true);
            
		}else if(ae.getSource() == openButton){
			chooser.setFileFilter(filter);
			chooser.setMultiSelectionEnabled(false);
			int result = chooser.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION){
				file = chooser.getSelectedFile();
				label.setText("File  : "+ file.getAbsolutePath());
				
			}
		}else if (ae.getSource() == directoryButton) {
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
	
	public void processFile(String source , String destination){
		System.out.println("Current File : "+source);
		CustomFileConverter converter = new CustomFileConverter();
        
        try {
			converter.createFile(source, destination);
			
        } catch (IOException e) {
        	ErrorWindow errorWindow = new ErrorWindow(e.getMessage());
            errorWindow.setLocation(this.getLocation());
            errorWindow.setSize(60,60);
            errorWindow.setVisible(true);
		}
	}

	public void processDirectory(String source , String destination){
		File file = new File(source);
		System.out.println("Is directory : "+file.isDirectory());
		if(!file.isDirectory()){
			processFile(source,destination);
		}else{
			for(String fileName : file.list()){
				//System.out.println(file.getPath()+"/"+fileName);
				processFile(file.getPath()+ "/"+fileName,destination);
			}
		}
	}
	
}
