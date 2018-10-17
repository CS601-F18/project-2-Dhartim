package cs601.project2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
/**
 * 
 * @author dhartimadeka
 *Main method . It will check configure file condition and call other methods.
 */
public class AmazonReview {
	private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static void main(String[] args) 
	{
		if(args.length == 0)
		{
			System.out.println("please mention correct filename");
			System.exit(0);
		}
		String ConfigFileName = args[0];
		ConfigBean config = new ConfigBean();
		Gson gson = new Gson();
		AmazonReview cHandler = new AmazonReview();
		PublisherSubscriber publisherSubscriber;
		//setting up log
		try {
			LogManager.setup();
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		//length of arguments checked
		if (args.length != 1) 
		{
			logger.log(Level.SEVERE, MsgLog.inValidArgs);
			System.exit(0);
		}
		logger.log(Level.INFO, MsgLog.loadingConfigData);
		
		//reading json file
		try {
			JsonReader reader = new JsonReader(new FileReader(ConfigFileName));
			config = gson.fromJson(reader, ConfigBean.class);
		} catch (FileNotFoundException e) 
		{
			logger.log(Level.SEVERE, String.format(MsgLog.invalidConfig, ConfigFileName, e));
			System.exit(0);
		}
		
		logger.log(Level.INFO, String.format(MsgLog.validConfig, ConfigFileName));

		//checking broker definition
		if (!cHandler.validate(config)) {
			logger.log(Level.SEVERE, MsgLog.inValidBroker);
			logger.log(Level.WARNING, MsgLog.brokerInfo);
			System.exit(0);
		}
		//passing config data into publishersubscriber and calling a method
		publisherSubscriber = new PublisherSubscriber(config);
		publisherSubscriber.process();
	}
	
	/**
	 * Validate will pass configbean object and check whether broker type is correctly defined
	 * 
	 * @param config - it will pass Object of configBean file
	 * @return boolean value
	 */
	public boolean validate(ConfigBean config) {
		String validBroker = MsgLog.validateBrokerNameList;
		if (!validBroker.contains(config.getBroker())) {
			return false;
		}
		return true;
	}
	
}
