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
 * New subscriber -  handles and writes into new file 
 * implements subscriber interface
 * @author dhartimadeka
 *
 * @param <T>
 */
public class NewSubscribers<T> implements Subscriber<T> {
	private BufferedWriter writer;
	private String outPutFileName;
	private int count=0;
	private Broker<T> broker;
	private Logger logger = Logger.getLogger(NewSubscribers.class.getName());
	/**
	 * Constructor to initialize writer and create files.
	 * @param fileName - name of new file.
	 */
	public NewSubscribers(String fileName, Broker<T> broker) 
	{
		Path filepath = Paths.get(fileName);
		this.outPutFileName = fileName;
		this.broker = broker;
		///registering subscriber to broker
		this.broker.subscribe(this);
		//checks if files exist or not.
		if ((new File(fileName)).exists()) {
			(new File(fileName)).delete();
		}
		//creates a bufferedwriter object
		try {
			logger.log(Level.INFO, String.format(MsgLog.writingFile, fileName));
			writer = Files.newBufferedWriter(filepath);
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

			if (review.getUnixReviewTime() > 1362268800) {
				try {
					writer.write(review.toString());
					writer.newLine();
					count += 1;
				} catch (IOException e) {
					logger.log(Level.SEVERE, String.format(MsgLog.unableToWriteLine, review, outPutFileName), e);
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