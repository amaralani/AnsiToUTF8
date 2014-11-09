import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class UI extends JFrame implements ActionListener{

		String source;
		String destination;
		JButton openButton = new JButton("Browse...");
		JButton goButton = new JButton("Change the encoding in a magical way!");
		JFileChooser chooser = new JFileChooser();
		File file;
		
		FileFilter filter = new FileFilter(){
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
			CustomFileConverter customFileConverter = new CustomFileConverter();
			try {
				customFileConverter.createFile("a","b");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(ae.getSource() == openButton){
			//chooser.addChoosableFileFilter( filter);
			chooser.setMultiSelectionEnabled(false);
			int result = chooser.showOpenDialog(this);
			if(result == chooser.APPROVE_OPTION){
				file = chooser.getSelectedFile();
				
				System.out.println(file.getParent());
				File parentDir = new File(file.getParent()+"/new");
				if(!parentDir.exists()){
					parentDir.mkdir();
				}
				
				CustomFileConverter converter = new CustomFileConverter();
				try {
					converter.createFile(file.getPath(), parentDir+"/"+file.getName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	
}
