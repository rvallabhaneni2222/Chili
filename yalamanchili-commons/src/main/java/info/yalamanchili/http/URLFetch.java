package info.yalamanchili.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class URLFetch {
	public static String getResponseString(String urlString) {
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(urlString);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String line;
			while ((line = reader.readLine()) != null)
				sb.append(line).append("\n");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(new StringBuilder()
					.append("URL Fetch failed for URI:").append(urlString)
					.toString(), e);
		}

		return sb.toString();
	}

	public static BufferedReader getResponseAsStream(String urlString) {
		BufferedReader reader;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(new StringBuilder()
					.append("URL Fetch failed for URI:").append(urlString)
					.toString(), e);
		}

		return reader;
	}
}