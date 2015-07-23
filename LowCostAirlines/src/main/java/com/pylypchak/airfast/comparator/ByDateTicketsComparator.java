package com.pylypchak.airfast.comparator;

import java.util.Comparator;

import com.pylypchak.airfast.model.Ticket;

public class ByDateTicketsComparator implements Comparator<Ticket> {

	@Override
	public int compare(Ticket t1, Ticket t2) {
		if (t1.getDate().before(t2.getDate())){
			return 1;
		}
		if (t1.getDate().after(t2.getDate())){
			return -1;
		}
		return 0;
	}

}
