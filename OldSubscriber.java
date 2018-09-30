package cs601.project2;

public class OldSubscriber implements Subscriber<Review>
{
	final long unixtime = 1362268800;
	private String filename = "oldfile.json";
	//NewSubscribers newsubscriber = new NewSubscribers() ;
	WriteToFile wtf =  new WriteToFile();
	@Override
	public void onEvent(Review item) 
	{
		if(item.getUnixReviewTime() <= unixtime)
		{
			wtf.writeToFile(filename, item.toString());
		}
		//System.out.println("subscriber got object" + item.toString());
		// TODO Auto-generated method stub
		
	}
}
