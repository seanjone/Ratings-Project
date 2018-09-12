package ratings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ratings.ratables.Ratable;
import ratings.ratables.Song;

public class RatingsAnalyzer {
	public static double bayesianAverage(ArrayList<Double> ratings, int additionalSongs, double additionalAverage) {
		double average = 0;
		for (Double num : ratings) {
			average += num;
		}
		average = (average + (additionalSongs * additionalAverage)) / (additionalSongs + ratings.size());
		return average;
	}

	public static Song bestSong(ArrayList<Song> songList, int additionalReviews, double additionalRatings) {
		Song bestSong = songList.get(0);
		for (Song newSong : songList) {
			if (bayesianAverage(bestSong.getRatings(), additionalReviews,
					additionalRatings) <= bayesianAverage(newSong.getRatings(), additionalReviews, additionalRatings)) {
				bestSong = newSong;
			}
		}
		return bestSong;
	}

	public static Song mostReviews(ArrayList<Song> songList) {
		int num = Integer.MIN_VALUE;
		Song song = new Song();
		for (Song newSong : songList) {
			int numR = newSong.getRatings().size();
			if (numR > num) {
				num = numR;
				song = newSong;
			}
		}
		return song;
	}

	public static Ratable bestRatable(ArrayList<Ratable> ratableList, int additionalReviews, double additionalRatings) {
		Ratable bestProduct = ratableList.get(0);
		for (Ratable ratable : ratableList) {
			if (bayesianAverage(ratable.getRatings(), additionalReviews, additionalRatings) >= bayesianAverage(
					bestProduct.getRatings(), additionalReviews, additionalRatings)) {
				bestProduct = ratable;
			}
		}
		return bestProduct;
	}

	public static ArrayList<Ratable> getTopK(ArrayList<Ratable> inputList, int k,
			Comparator<Ratable> specifiedComparator) {
		ArrayList<Ratable> topK = new ArrayList<>();
		Collections.sort(inputList, specifiedComparator);
		for (int i = 0; i < k; i++) {
			topK.add(inputList.get(i));
		}
		return topK;
	}
}
