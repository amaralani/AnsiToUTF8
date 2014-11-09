import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class CustomFileConverter {

	private static final char BYTE_ORDER_MARK = '\uFEFF';
	
	public void createFile(String inputFile, String outputFile) throws IOException{
		FileInputStream	input = new FileInputStream(inputFile);
		
		InputStreamReader inputStreamReader = new InputStreamReader(input, "windows-1256");
		char[] data = new char[1024];
		int i = inputStreamReader.read(data);
		
		FileOutputStream output = new FileOutputStream(outputFile,true);
		Writer writer = new OutputStreamWriter(output,"UTF-8");
		
		String text = "";
		writer.write(BYTE_ORDER_MARK);
		while(i !=-1){
			String str = new String(data,0,i);
			text = text+str;
			writer.write(data,0,i);
			System.out.println(str);
			System.out.println('*');
			i = inputStreamReader.read(data);
			
		}
		//outputbuffer.write(text.getBytes());
		output.close();
		input.close();		
	}
}
