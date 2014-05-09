package de.fraunhofer.fokus.sti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Crawler {

	URL url;

	abstract URL setURL(String label);
//	{
//		try {
//			url = new URL(
//					"http://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=prefix+foaf%3A++%3Chttp%3A%2F%2Fxmlns.com%2Ffoaf%2F0.1%2F%3E+%0D%0Aselect+distinct+%3Fy+where+%7B%0D%0A%3Fx+rdfs%3Alabel+%22"
//							+ label
//							+ "%22%40en.%0D%0A%3Fx+rdf%3Atype+%3Fy%0D%0A%7D&format=application%2Fsparql-results%2Bjson&timeout=30000&debug=on");
//			return url;
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}

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
			//ioe.printStackTrace();
			return null;
		} finally {
			if (is != null)
				is.close();
		}
	}

	/**
	 * Returns list of <i>rdfs:label</i> for parameter 'label'
	 * @param entity name to return <i>rdfs:label</i> s for
	 * @return list of <i>rdfs:label</i> for parameter 'label'
	 */
	public abstract String[] extract(String label);

}
