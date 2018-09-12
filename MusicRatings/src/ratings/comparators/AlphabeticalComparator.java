package ratings.comparators;

import java.util.Comparator;

import ratings.ratables.Ratable;

public class AlphabeticalComparator implements Comparator<Ratable>{
	@Override
	public int compare(Ratable arg0, Ratable arg1) {
		return arg0.getDescription().compareToIgnoreCase(arg1.getDescription());
	}
	
	
}
