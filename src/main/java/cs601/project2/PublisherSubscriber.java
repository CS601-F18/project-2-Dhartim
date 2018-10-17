package cs601.project2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author dhartimadeka
 * PublisherSubscriber creates publisher threads and calls publisher method of respective brokers.
 *
 */
public class PublisherSubscriber {
	private ConfigBean config;
	private boolean flagAsyncOrd = false;
	//flagAsyncUnOrd = false;
	//LogHandler logHandle = new LogHandler();
	//private static Logger logger = Logger.getLogger(PublisherSubscriber.class.getName());
	private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public PublisherSubscriber(ConfigBean config) 
	{
		this.config = config;
	}

	/**
	 * Process creates publisher, subscriber threads and calls publisher method.
	 */
	public void process() {

		//boolean finishWriting = false, dataPublished1 = true, dataPublished2 = true;
		Broker<Review> broker = null;
		Thread asyncThread = null;
		long startTime, endTime, totalTime;

		logger.log(Level.INFO, MsgLog.subscriberCreated);

		// creating objects of broker specified.
		switch (config.getBroker()) {
		case "AsyncOrdered":
			this.flagAsyncOrd = true;
			System.out.println("Async Ordered Broker:-");
			logger.log(Level.INFO, String.format(MsgLog.brokerInit, config.getBroker()));
			broker = new AsyncOrderedDispatchBroker<Review>();
			break;
		case "AsyncUnordered":
			System.out.println("Async Unordered Broker:-");
			//this.flagAsyncUnOrd = true;
			logger.log(Level.INFO, String.format(MsgLog.brokerInit, config.getBroker()));
			broker = new AsyncUnorderedDispatchBroker<Review>();
			break;
		default:
			System.out.println("Synchronous Broker:-");
			logger.log(Level.INFO, String.format(MsgLog.brokerInit, config.getBroker()));
			broker = new SynchronousOrderedDispatchBroker<Review>();
		}
		// declaring subscribers
		NewSubscribers<Review> newsubscriber = new NewSubscribers<Review>(config.getoutputNewFile(), broker);
		OldSubscriber<Review> oldSubscriber = new OldSubscriber<Review>(config.getoutputOldFile(), broker);
		//adding subscriber to broker.
		logger.log(Level.INFO, MsgLog.subsrcibingUsers);
//		broker.subscribe(oldSubscriber);
//		broker.subscribe(newsubscriber);
		logger.log(Level.INFO, MsgLog.subscrtiptionComplete);

		// file path of both files
		Path filePath1 = Paths.get(config.getpublisher1LocationFile());
		Path filePath2 = Paths.get(config.getpublisher2LocationFile());

		//passing broker, filepath and type of class to broker.
		Publisher<Review> publisher1 = new Publisher<Review>(filePath1, broker, Review.class);
		Publisher<Review> publihser2 = new Publisher<Review>(filePath2, broker, Review.class);

		//creating publisher thread
		Thread publisherThread1 = new Thread(publisher1);
		Thread publisherThread2 = new Thread(publihser2);

		//start timer with threads
		startTime = System.currentTimeMillis();
		publisherThread1.start();
		publisherThread2.start();
		//start async thread if true;
		if (flagAsyncOrd) 
		{
			//long asynctime = System.currentTimeMillis();
			asyncThread = new Thread((AsyncOrderedDispatchBroker<Review>) broker);
			asyncThread.start();
			//long asyncendtime = System.currentTimeMillis();
			//System.out.println(asyncendtime - asynctime);
			//logger.log(Level.INFO, MsgLog.brokerRunnablestarted);
		}
		//join threads
		logger.log(Level.INFO, MsgLog.pubhlisherStarted);
		//if (!flagAsyncOrd || !flagAsyncUnOrd) {
		try {
			logger.log(Level.INFO, MsgLog.pubhlisherSync);
			publisherThread1.join();
			publisherThread2.join();
			//asyncThread.join();
		} catch (InterruptedException e) {
			logger.log(Level.INFO, MsgLog.pubhlisherSyncInterrupted);
			e.printStackTrace();
		}
		long publishtime = System.currentTimeMillis();
		System.out.println("publisher time" + (publishtime - startTime));
		long brokertime = System.currentTimeMillis();
		broker.shutdown();
		endTime = System.currentTimeMillis();

		System.out.println("broker" +(endTime - brokertime));
		totalTime = endTime - startTime;
		System.out.println("Time took for execution :- " + totalTime);
		System.out.println("old file line count:- " + oldSubscriber.lineCount());
		System.out.println("newfile line count:- " + newsubscriber.lineCount());
		//}
	}
}
