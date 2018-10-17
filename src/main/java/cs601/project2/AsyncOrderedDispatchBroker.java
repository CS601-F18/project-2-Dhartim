package cs601.project2;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 
 * @author dhartimadeka
 * AsyncOrdered Broker implements broker and runnable interface. Publisher puts data into blocking queue.
 * 
 * Broker passes data to subscriber from blocking queue.
 *
 * @param <T>
 */
public class AsyncOrderedDispatchBroker<T> implements Runnable, Broker<T> {

	private CS601BlockingQueue<T> blockingQueue = new CS601BlockingQueue<>(100);
	private ArrayList<Subscriber<T>> subscriberList = new ArrayList<>();
	private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private boolean stopThread = false;
	/**
	 * Publisher will put data into blocking queue.
	 *@param item - item to put in blocking queue
	 */
	@Override
	public synchronized void publish(T item) {
		blockingQueue.put(item);
	}
	/**
	 * Subscriber adds subscriber to list.
	 * @param subscriber - Subscriber to add in subcriber list
	 */
	@Override
	public void subscribe(Subscriber<T> subscriber) {
		subscriberList.add(subscriber);
	}
	/**
	 * Shutdown broker thread.
	 */
	@Override
	public void shutdown() 
	{
		stopThread = true;
	}

	/**
	 * Broker thread will poll out elements from blocking queue, passes to subscriber.
	 */
	@Override
	public void run() 
	{
		T t;
		System.out.println("asyncorder....");
		logger.log(Level.INFO, MsgLog.asyncMethodStart);
		while(!stopThread )
		{
			while ((t = blockingQueue.poll(50, TimeUnit.MILLISECONDS)) != null) 
			{
				for (Subscriber<T> subscriber : subscriberList) 
				{
					subscriber.onEvent(t);
				}
			}
		}
	}
}
