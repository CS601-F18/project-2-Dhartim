package cs601.project2;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author dhartimadeka
 *Synchronous broker passes data from publisher to subcriber
 * extended to broker interface
 *
 * @param <T>
 */
public class SynchronousOrderedDispatchBroker<T> implements Broker<T> 
{
	private ArrayList<Subscriber<T>> subscriberList = new ArrayList<>();
	private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	//constructor of synchronous.
	public SynchronousOrderedDispatchBroker() 
	{
		// TODO Auto-generated constructor stub
		logger.log(Level.INFO, MsgLog.syncBrokerCalled);
	}
	
	/**
	 * Publish - overrides method of broker Interface. Calls onEvent to pass data to subscriber.
	 * @param item - Object to be passed to subscriber
	 */
	@Override
	public synchronized void publish(T item) {
		for (Subscriber<T> subs : subscriberList) 
		{
			subs.onEvent(item);
		}
	}
	
	/**
	 * Subscriber - Adds subscriber in subscriber List
	 * @param subscriber - subscriber to be added in subcriberList.
	 */ 
	@Override
	public void subscribe(Subscriber<T> subscriber) {
		subscriberList.add(subscriber);
	}

	/**
	 * Shutdown - Call it to close thread.
	 */
	@Override
	public void shutdown() {
	}

}