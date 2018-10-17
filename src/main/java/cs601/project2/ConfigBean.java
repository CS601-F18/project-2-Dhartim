package cs601.project2;

/**
 * 
 * @author dhartimadeka
 *ConfigBean - Stores and handle config.json file data when read.
 */
public class ConfigBean {
	private String publisher1LocationFile;
	private String publisher2LocationFile;
	private String outputOldFile;
	private String outputNewFile;
	private String broker;

	public String getpublisher1LocationFile() {
		return publisher1LocationFile;
	}

	public void setpublisher1LocationFile(String publisher1LocationFile) {
		this.publisher1LocationFile = publisher1LocationFile;
	}

	public String getpublisher2LocationFile() {
		return publisher2LocationFile;
	}

	public void setpublisher2LocationFile(String publisher2LocationFile) {
		this.publisher2LocationFile = publisher2LocationFile;
	}

	public String getoutputOldFile() {
		return outputOldFile;
	}

	public void setoutputOldFile(String outputOldFile) {
		this.outputOldFile = outputOldFile;
	}

	public String getoutputNewFile() {
		return outputNewFile;
	}

	public void setoutputNewFile(String outputNewFile) {
		this.outputNewFile = outputNewFile;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

}
