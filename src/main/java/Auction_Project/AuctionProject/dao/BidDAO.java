package Auction_Project.AuctionProject.dao;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import Auction_Project.AuctionProject.ws.auction.Auction;
import Auction_Project.AuctionProject.ws.bid.Bid;

@Transactional
public interface BidDAO extends CrudRepository<Bid, Long>{
	
	public List<Bid> findByAuctionId(Auction auction_id);
}
