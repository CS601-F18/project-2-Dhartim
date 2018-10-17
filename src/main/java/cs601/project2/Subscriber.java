package cs601.project2;

public interface Subscriber<T> 
{

	/**
	 * Called by the Broker when a new item
	 * has been published.
	 * @param item
	 */
	
	public void onEvent(T item);
	/**
	 * Called by the Broker to close file writer
	 * when item has been subscribed.
	 *
	 */
	public void finished();
	
}