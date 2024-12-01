package ex3;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Item {
    private Set<Bid> bids = new HashSet<Bid>();
    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }
    public void addBid(Bid bid) {
        if(bid == null)
            throw new NullPointerException("bid is null");
        if(bid.getItem() != null)
            throw new IllegalArgumentException("bid is already added");
        bids.add(bid);
        bid.setItem(this);
    }
}



