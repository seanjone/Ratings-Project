package ratings.comparators;

import java.util.Comparator;

import ratings.RatingsAnalyzer;
import ratings.ratables.Ratable;

public class BayesianAverageComparator implements Comparator<Ratable> {
	private int extraRatings;
	private double valueOfRatings;

	public BayesianAverageComparator(int num1, double num2) {
		this.extraRatings = num1;
		this.valueOfRatings = num2;
	}

	@Override
	public int compare(Ratable o1, Ratable o2) {
		if (RatingsAnalyzer.bayesianAverage(o1.getRatings(), this.extraRatings, this.valueOfRatings) > RatingsAnalyzer
				.bayesianAverage(o2.getRatings(), this.extraRatings, this.valueOfRatings)) {
			return -1;
		} else {
			return 1;
		}
	}

}
