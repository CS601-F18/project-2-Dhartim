package cs601.project2;

import java.util.ArrayList;

public class SynchronousOrderedDispatchBroker<T> implements Broker<T>
{
	
	//Review reviewobj;
	ArrayList<Subscriber<T>> subscriberList = new ArrayList<>();
	@Override
	public synchronized void publish(T item) 
	{ 
		//reviewobj = item;
		//System.out.println("i got object..."+ item.toString());
		//registering each publisher with subscriber.
		System.out.println("before for loop....");
		//subscriberList = new ArrayList<>();
		for(Subscriber<T> subs: subscriberList)
		{
			System.out.println("inside for loop...");
			subs.onEvent(item);
		}
		System.out.println("after for loop....");
		// TODO Auto-generated method stub
		
	}
	@Override
	public void subscribe(Subscriber<T> subscriber) 
	{
		//System.out.println(subscriber);
		subscriberList.add(subscriber);
		//System.out.println(subscriberList);
		// TODO Auto-generated method stub	
	}
	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
	}
	
	
}