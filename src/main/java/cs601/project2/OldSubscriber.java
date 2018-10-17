package cs601.project2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

/**
 * Old subscriber -  handles and writes into old file 
 * implements subscriber interface
 * @author dhartimadeka
 *
 * @param <T>
 */
public class OldSubscriber<T> implements Subscriber<T> {
	//private final long unixtime = 1362268800;

	private BufferedWriter writer;
	private String outPutFileName;
	private Gson gson;
	private Broker<T> broker;
	//LogHandler logHandle =  new LogHandler();
	//static boolean isWriting = true;
	private Logger logger = Logger.getLogger(OldSubscriber.class.getName());
	private int count;

	/**
	 * Constructor to initialize writer and create files.
	 * @param fileName - name of new file.
	 */

	public OldSubscriber(String fileName, Broker<T> broker) 
	{
		this.outPutFileName = fileName;
		this.broker =broker;
		//subscriber subscribing to broker
		broker.subscribe(this);
		gson = new Gson();
		//checks if files exist or not.
		Path filepath = Paths.get(fileName);
		if ((new File(fileName)).exists()) {
			(new File(fileName)).delete();
		}

		//creates a bufferedwriter object
		try {
			writer = Files.newBufferedWriter(filepath);
			logger.log(Level.INFO, String.format(MsgLog.writingFile, fileName));
		} catch (IOException e) {
			logger.log(Level.SEVERE, String.format(MsgLog.unableToCreateFile, outPutFileName), e);
			e.printStackTrace();
		}
	}

	/**
	 * Check's for item of review type and writes it into file.
	 * @param item - item to write into newFile.json
	 */
	@Override
	public synchronized void onEvent(T item) {
		if (item instanceof Review) {
			Review review = (Review) item;

			if (review.getUnixReviewTime() <= 1362268800) {
				String lines = gson.toJson(item);
				try {
					//System.out.println("old : " + lines);
					writer.write(lines);
					writer.newLine();
					count+=1;
				} catch (IOException e) {
					logger.log(Level.SEVERE, String.format(MsgLog.unableToWriteLine, lines, outPutFileName), e);
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * Finished - Called to close writer after shutting down broker.
	 */
	public void finished() {
		try {
			writer.close();
			logger.log(Level.INFO, String.format(MsgLog.dataWrittenSucess, outPutFileName));
		} catch (IOException e) {
			logger.log(Level.SEVERE, String.format(MsgLog.unableToCloseWriter, outPutFileName), e);
			e.printStackTrace();
		}
	}

	/**
	 * lineCount - return number of lines in file.
	 * @return
	 */
	public int lineCount()
	{
		return count;
	}
}
