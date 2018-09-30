package cs601.project2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import com.google.gson.Gson;

import cs601.project2.Review;
public class Publisher implements Runnable
{
	private Path pathOfFile;
	SynchronousOrderedDispatchBroker syncbroker; 
	
	public Publisher(Path pathOfFile, SynchronousOrderedDispatchBroker syncbroker) 
	{
		this.pathOfFile = pathOfFile;
		this.syncbroker = syncbroker;
		// TODO Auto-generated constructor stub
	}
	Review reviewobj;
	//read from file
		public void readFile(Path pathOfFile, SynchronousOrderedDispatchBroker syncbroker)
		{
			Gson gson = new Gson();
			try(
					BufferedReader br = Files.newBufferedReader(pathOfFile, Charset.forName("ISO-8859-1")))
			{
				String readLineFromFile; 
				while((readLineFromFile = br.readLine()) != null)
				{
					reviewobj = gson.fromJson(readLineFromFile, Review.class);
					System.out.println("OBJECT IS......"+reviewobj);
					syncbroker.publish(reviewobj);	
				}
			}
			catch (IOException ioe) 
			{
				// TODO: handle exception
				ioe.getStackTrace();
			}
		}
		@Override
		public void run() 
		{
			readFile(pathOfFile, syncbroker);
			// TODO Auto-generated method stub
		}
}
