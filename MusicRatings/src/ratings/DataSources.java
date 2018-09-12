package ratings;

import java.util.ArrayList;

import java.util.HashMap;

import org.apache.http.client.fluent.Request;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import ratings.ratables.Product;
import ratings.ratables.Ratable;
import ratings.ratables.Song;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

public class DataSources {
	public static ArrayList<Song> readSongCSVFile(String file) {
		ArrayList<Song> songs = new ArrayList<Song>();
		HashMap<String, Song> songList = new HashMap<String, Song>();
		try {
			for (String line : Files.readAllLines(Paths.get(file))) {
				String[] temp = line.split(",");
				double j = new Double(temp[3]);
				if (songList.containsKey(temp[0])) {
					songList.get(temp[0]).addRating(j);
				} else {
					Song newSong = new Song(temp[0], temp[1], temp[2]);
					newSong.addRating(j);
					songs.add(newSong);
					songList.put(temp[0], newSong);
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return songs;
	}

	public static ArrayList<Ratable> readSongsAsRatables(String input) {
		ArrayList<Ratable> ratables = new ArrayList<>();
		ArrayList<Song> songs = new ArrayList<>();
		songs = readSongCSVFile(input);
		for (Song song : songs) {
			ratables.add(song);
		}
		return ratables;
	}

	public static ArrayList<Ratable> readProductCSVFile(String file) {
		ArrayList<Ratable> ratables = new ArrayList<>();
		HashMap<String, Ratable> ratableList = new HashMap<>();
		try {
			for (String line : Files.readAllLines(Paths.get(file))) {
				String[] temp = line.split(",");
				double j = new Double(temp[2]);
				if (ratableList.containsKey(temp[0])) {
					ratableList.get(temp[0]).addRating(j);
				} else {
					Product newProduct = new Product(temp[0]);
					newProduct.addRating(j);
					ratables.add(newProduct);
					ratableList.put(temp[0], newProduct);
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return ratables;
	}

	public static String getRequest(String url) {
		String response = "";

		try {
			response = Request.Get(url).execute().returnContent().asString();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return response;
	}

	public static ArrayList<Ratable> getSongsFromAPI() {
		ArrayList<Ratable> songs = new ArrayList<>();
		String jsonString = getRequest("https://fury.cse.buffalo.edu/api/musicRatings/getAllSongs");
		JsonValue jsonValue = Json.parse(jsonString);
		JsonArray jsonArray = jsonValue.asArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonValue jsonValue1 = jsonArray.get(i);
			JsonObject jsonObject = jsonValue1.asObject();
			JsonValue titleValue = jsonObject.get("title");
			String title = titleValue.asString();
			JsonValue youtubeValue = jsonObject.get("youtubeID");
			String youtubeID = youtubeValue.asString();
			JsonValue artistValue = jsonObject.get("artist");
			String artist = artistValue.asString();
			JsonValue ratingValue = jsonObject.get("ratings");
			JsonArray ratings = ratingValue.asArray();
			Ratable song = new Song(youtubeID, artist, title);
			for (int j = 0; j < ratings.size(); j++) {
				double ratingValue1 = ratings.get(j).asDouble();
				song.addRating(ratingValue1);
			}
			songs.add(song);
		}
		return songs;
	}
}
