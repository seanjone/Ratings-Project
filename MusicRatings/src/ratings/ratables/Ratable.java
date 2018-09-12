package ratings.ratables;

import java.util.ArrayList;

public abstract class Ratable {
	private ArrayList<Double> ratings = new ArrayList<>();

	public Ratable() {

	}

	public abstract String getID();

	public abstract String getLink();

	public abstract String getDescription();

	public void addRating(double rating) {
		this.ratings.add(rating);
	}

	public ArrayList<Double> getRatings() {
		return this.ratings;
	}

}
