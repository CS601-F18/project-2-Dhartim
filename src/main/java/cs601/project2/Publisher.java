package cs601.project2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

public class Publisher<T> implements Runnable 
{
	private static Logger logger = Logger.getLogger(Publisher.class.getName());
	//LogHandler logHandle = new LogHandler();
	private Path pathOfFile;
	private Broker<T> broker;
	private Class<T> type;
	private Gson gson = new Gson();
	private T t;

	public Publisher(Path pathOfFile, Broker<T> broker, Class<T> type) {
		this.pathOfFile = pathOfFile;
		this.broker = broker;
		this.type = type;
	}

	@Override
	public void run() 
	{
		String readLineFromFile;
		logger.log(Level.INFO, String.format(MsgLog.readingPubhilsherData, pathOfFile));
		try (BufferedReader br = Files.newBufferedReader(pathOfFile, Charset.forName("ISO-8859-1"))) {
			while ((readLineFromFile = br.readLine()) != null) 
			{
				t = gson.fromJson(readLineFromFile, type);
				broker.publish(t); // overloading publish method ....
			}
		} catch (IOException ioe) {
			logger.log(Level.SEVERE, String.format(MsgLog.errorOccuredReading, pathOfFile, ioe));
			ioe.getStackTrace();
		}
	
		logger.log(Level.INFO, String.format(MsgLog.readingComplete, pathOfFile));
	}
}