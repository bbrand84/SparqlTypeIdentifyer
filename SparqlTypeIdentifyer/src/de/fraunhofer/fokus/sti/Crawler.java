package de.fraunhofer.fokus.sti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Extend this class if you want to connect to an sparql enpoint like
 * dbpedia.org/sparql.
 * 
 * @author BB
 * 
 */
public abstract class Crawler {

	URL url;

	Crawler() {

	}

	/**
	 * Overwrite this function to change the class variable "url".
	 * See class "CrawlerDBPedia"
	 * @param label string to look up
	 * @return URL or null should be returned
	 */
	abstract URL setURL(String label);

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public JSONObject getJSON(String label) throws IOException, JSONException {
		setURL(label);

		InputStream is = null;
		try {
			is = url.openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} catch (IOException ioe) {
			// ioe.printStackTrace();
			return null;
		} finally {
			if (is != null)
				is.close();
		}
	}

	/**
	 * Returns list of <i>rdfs:label</i> for parameter 'label'
	 * 
	 * @param entity
	 *            name to return <i>rdfs:label</i> s for
	 * @return list of <i>rdfs:label</i> for parameter 'label'
	 */
	public abstract String[] extract(String label);

}
