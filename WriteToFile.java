package cs601.project2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile 
{
	public synchronized void writeToFile(String path, String lines) 
	{
		try {
			 BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
			    
		//BufferedWriter writer = Files.newBufferedWriter(path);
		writer.append(lines + "\n \n");
		System.out.println("File Writting to " + path + " finshed!");
		// writer.write("cool");
		writer.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
}
