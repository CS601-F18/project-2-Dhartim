package cs601.project2;
/**
 * Interface handles all messages of logger.
 * @author dhartimadeka
 *
 */
public interface MsgLog 
{
	String invalidConfig = "Unable to find or issues in %s file";
	String stackMsg = "See below stack";
	String syncBrokerCalled = "Sync broker invoked";
	String validConfig = "%s configuration file loaded Sucessfully";
	String inValidArgs = "Invalid arguments supplied";
	String loadingConfigData = "loading configuration data";
	String readingPubhilsherData = "Reading Pubhilsher data from %s";
	String readingComplete = "Reading Data Completed from %s Succesfully";
	String errorOccuredReading = "Error occured while reading data from %s";
	String loadingDataNotifier = "Loading";
	String inValidBroker = "Invalid broker defiened";
	String writingFile = "File %s generated for writing";
	String dataWrittenSucess = "Data written succesfully into file %s";
	String unableToWriteLine = "Unable to write line '%s' in file %s";
	String unableToCloseWriter = "Unable to complete writing into file %s";
	String unableToCreateFile = "Unable to create output file %s";
	String subsrcibingUsers = "Subscribing Subscriber";
	String subscrtiptionComplete = "Subscription completed";
	String brokerInit = "%s broker initizialized";
	String pubhlisherStarted = "pubhilshier threads started";
	String pubhlisherSync = "pubhilshier threads running in sync";
	String pubhlisherSyncInterrupted = "pubhilihser running in sync interrupted";
	String pubhlisherDead = "pubhlisher %s is dead";
	String finishWritingNotifier = "Pubhilishers status %s %s finishing writing status %s";
	String subscriberCreated = "Subscriber created";
	String brokerRunnablestarted = "Async runnable called";
	String brokerInfo = "\nDefine any one broker from below list as\n1. SynchronousOrdered\n2. AsyncOrdered\n3. AsyncUnordered";
	String asyncDead = "Async thread %s dead";
	String asyncMethodStart = "Async Method started..";
	String asyncMethdEnd = "Async Method end";
	String asyncLoopBreak = "Async Method loop breaked";
	String insidePollMethod = "Blocking queue poll method started";
	String pollMethodEnded = "Blocking queue poll method ended";
	String returnNullPoll = "Size zero return item as null";
	String waitingInPoll = "waiting in poll method when size is zero";
	String asyncUnordeStart = "AsyncUnOrdered thread initizalised";
	String shuttingDownBroker = "Shutting down broker..";
	String errorShutingBroker = "Error While Shuting Down Broker";
	String shutdownSucc = "Broker Shutdown Successfully";
	String validateBrokerNameList = "SynchronousOrdered;AsyncOrdered;AsyncUnordered";
}
