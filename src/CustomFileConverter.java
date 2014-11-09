
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class CustomFileConverter {

	private static final char BYTE_ORDER_MARK = '\uFEFF';
	
	public void createFile(String inputFile, String outputFile) throws IOException{
		System.out.println("Input : "+inputFile);
		System.out.println("Output : "+outputFile);
		FileInputStream	input = new FileInputStream(inputFile);
		
		InputStreamReader inputStreamReader = new InputStreamReader(input, "windows-1256");
		char[] data = new char[1024];
		int i = inputStreamReader.read(data);

        if(new File(outputFile).exists()){
           new File(outputFile).delete();
        }
		FileOutputStream output = new FileOutputStream(outputFile,true);
		Writer writer = new OutputStreamWriter(output,"UTF-8");

		String text = "";
		writer.write(BYTE_ORDER_MARK);
		while(i !=-1){
			String str = new String(data,0,i);
            text = text+str;
			i = inputStreamReader.read(data);

		}
        writer.append(text);

		writer.close();
		input.close();
	}
}
