package cs601.project2;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PublisherSubscriber 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		NewSubscribers newsubscriber = new NewSubscribers(); //calling subscribers
		OldSubscriber oldSubscriber = new OldSubscriber();
		//creating objects
		SynchronousOrderedDispatchBroker<Review> syncbrokerthread1 = new SynchronousOrderedDispatchBroker<>();
		SynchronousOrderedDispatchBroker<Review> syncbrokerthread2 = new SynchronousOrderedDispatchBroker<>();
		//2 subscriber for publisher 1
		syncbrokerthread1.subscribe(oldSubscriber);
		syncbrokerthread1.subscribe(newsubscriber);
		//2 subscriber for publisher 2
		syncbrokerthread2.subscribe(oldSubscriber);
		syncbrokerthread2.subscribe(newsubscriber);
		Path filepath1  = Paths.get("sample.json");
		Path filepath2 = Paths.get("ktichen_sample.json");
		Thread thread1 = new Thread(new Publisher(filepath1, syncbrokerthread1));
		Thread thread2 = new Thread(new Publisher(filepath2, syncbrokerthread2));
		thread1.start();
		
		//System.out.println("thread 2....");
		thread2.start();
		//System.out.println("done with thread 2.... ");
		//read.readFile(filepath1, syncbroker);
		//read.readFile(filepath2, syncbroker);
	}
	
}
