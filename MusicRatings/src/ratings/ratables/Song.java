package ratings.ratables;

import java.util.ArrayList;

public class Song extends Ratable{
	private String _youTubeID;
	private String _artist;
	private String _title;
	private ArrayList<Double> ratings;

	public Song() {
		this._artist = "";
		this._title = "";
		this._youTubeID = "";
		this.ratings = new ArrayList<Double>();
	}

	public Song(String youTubeID, String artist, String title) {
		this._artist = artist;
		this._title = title;
		this._youTubeID = youTubeID;
		this.ratings = new ArrayList<Double>();
	}

	public String getID() {
		return this._youTubeID;
	}

	public String getLink() {
		String link = "https://www.youtube.com/watch?v=" + _youTubeID;
		return link;
	}

	public String getDescription() {
		String description = this._artist + " - " + this._title;
		return description;
	}

	public void addRating(double rating) {
		this.ratings.add(rating);
	}

	public ArrayList<Double> getRatings() {
		return this.ratings;
	}

	@Override
	public String toString() {
		return this._artist + " " + this._title;
	}
}
