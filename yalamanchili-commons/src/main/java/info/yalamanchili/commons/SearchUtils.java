package info.yalamanchili.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchUtils {
	public String getSearchQuery(String searchText, String... fields) {

		return null;
	}

	public List<String> splitSearchString(String searchText, char seperator) {
		List<String> words = new ArrayList<String>();
		Scanner searchTextScanner = new Scanner(searchText);
		while (searchTextScanner.hasNext()) {
			words.add(searchTextScanner.next());
		}
		return words;
	}
}
