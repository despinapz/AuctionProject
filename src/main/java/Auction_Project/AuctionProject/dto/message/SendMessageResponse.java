package Auction_Project.AuctionProject.dto.message;

public class SendMessageResponse {

	private long to, from;
	private String text, title;
	
	
	public long getTo() {
		return to;
	}
	public void setTo(long to) {
		this.to = to;
	}
	public long getFrom() {
		return from;
	}
	public void setFrom(long from) {
		this.from = from;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
