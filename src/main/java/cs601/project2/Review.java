package cs601.project2;
/**
 *
 * Contains fields for storing relevant information when reading the review text
 * file
 */
public class Review 
{
	private String reviewerID;
	private String asin;
	private String reviewerName;
	private int[] helpful; 
	private String reviewText;
	private double overall;
	private String summary;
	private String reviewTime;

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getReviewerName() {
		return reviewerName;
	}
	public int[] getHelpful() {
		return helpful;
	}

	public void setHelpful(int[] helpful) {
		this.helpful = helpful;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public long getUnixReviewTime() {
		return unixReviewTime;
	}

	public void setUnixReviewTime(long unixReviewTime) {
		this.unixReviewTime = unixReviewTime;
	}

	private long unixReviewTime;

	public void setReviewerID(String reviewerID) {
		this.reviewerID = reviewerID;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public void setOverall(double overall) {
		this.overall = overall;
	}
	public String getReviewerID() {
		return reviewerID;
	}

	public String getAsin() {
		return asin;
	}

	public String getReviewText() {
		return reviewText;
	}

	public double getOverall() {
		return overall;
	}
	public String toString()
	{
		return "{\"reviewerID\": \"" + this.reviewerID +"\", \"asin\": \""+this.asin+"\", \"reviewerName\": \""+this.reviewerName+"\", \"helpful\": \"["+this.helpful[0]+","+this.helpful[1]+"]\", \"reviewText\": \""+this.reviewText+"\", \"overall\": \""+this.overall+"\", \"summary\": \""+this.summary+"\", \"unixReviewTime\": \""+this.unixReviewTime+"\", \"reviewTime\": \""+this.reviewTime+"\"}";
	}
}
