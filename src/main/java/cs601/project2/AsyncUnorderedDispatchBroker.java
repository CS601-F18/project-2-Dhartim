package cs601.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author dhartimadeka
 * Async Unordered Broker takes data from publisher. Helping threads will pass data to subscriber.
 * 
 *
 * @param <T>
 */
public class AsyncUnorderedDispatchBroker<T> implements Broker<T> {

	private List<Subscriber<T>> subscriberList = new ArrayList<Subscriber<T>>();
	private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private ExecutorService executor;
	/**
	 * Constructor to initialize thread pool and logger.
	 */
	public AsyncUnorderedDispatchBroker() 
	{
		executor = Executors.newFixedThreadPool(5);
		logger.log(Level.INFO, MsgLog.asyncUnordeStart);
	}
	/**
	 * Publish - overrides broker's publish method.
	 * @param item - item to handled by helper threads and passed to subscriber.
	 */
	@Override
	public void publish(T item) 
	{
		//		Runnable task = () -> {
		//			for (Subscriber<T> subs : subscriberList) {
		//					subs.onEvent(item);
		//			}
		//		};
		//		executor.submit(task);
		executor.execute(new Runnable() 
		{
			@Override
			public void run() 
			{
				// TODO Auto-generated method stub
				for (Subscriber<T> subs : subscriberList) 
				{
					subs.onEvent(item);	
				}
			}
		});
	}

	/**
	 * Subscriber - subscriber is passed into list.
	 * @param subscriber - Subscriber to be added into subscriber list.
	 */
	@Override
	public void subscribe(Subscriber<T> subscriber) {
		subscriberList.add(subscriber);
	}

	/**
	 * shutdown - to shutdown threadpool. 
	 * After shutting down broker, finished method is called to close subscriber files.
	 * 
	 */
	@Override
	public void shutdown() 
	{
		logger.log(Level.INFO, MsgLog.shuttingDownBroker);
		executor.shutdown();
		try {
			System.out.println("hello");
			while(!executor.awaitTermination(50, TimeUnit.MILLISECONDS)) {}
		} catch (InterruptedException e) {
			logger.log(Level.INFO, MsgLog.errorShutingBroker);
			e.printStackTrace();
		}
		logger.log(Level.INFO, MsgLog.shutdownSucc);
		for (Subscriber<T> subs : subscriberList) {
			subs.finished();
		}
	}

}