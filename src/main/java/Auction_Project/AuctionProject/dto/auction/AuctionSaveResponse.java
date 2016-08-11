package Auction_Project.AuctionProject.dto.auction;

import java.util.Date;
import java.util.List;

import Auction_Project.AuctionProject.ws.category.Category;

public class AuctionSaveResponse {
	
private long id;
	
	private String name, description;
	
	private float currently, first_bid;
	
	private float buy_price;
	
	private Date started, ends, created;
	
	private long user_id;
	
	private List<Category> categoryList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getCurrently() {
		return currently;
	}

	public void setCurrently(float currently) {
		this.currently = currently;
	}

	public float getFirst_bid() {
		return first_bid;
	}

	public void setFirst_bid(float first_bid) {
		this.first_bid = first_bid;
	}

	public float getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(float buy_price) {
		this.buy_price = buy_price;
	}

	public Date getStarted() {
		return started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public Date getEnds() {
		return ends;
	}

	public void setEnds(Date ends) {
		this.ends = ends;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}