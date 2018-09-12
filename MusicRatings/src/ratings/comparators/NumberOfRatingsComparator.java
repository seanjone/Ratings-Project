package ratings.comparators;

import java.util.Comparator;

import ratings.ratables.Ratable;

public class NumberOfRatingsComparator implements Comparator<Ratable> {
	@Override
	public int compare(Ratable o1, Ratable o2) {
		if (o1.getRatings().size() > o2.getRatings().size()) {
			return -1;
		} else {
			return 1;
		}
	}

}
