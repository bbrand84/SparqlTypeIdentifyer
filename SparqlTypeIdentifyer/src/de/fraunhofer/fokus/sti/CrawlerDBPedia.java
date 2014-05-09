package de.fraunhofer.fokus.sti;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CrawlerDBPedia extends Crawler {


	public String[] extract(String label) {
		JSONObject jsonObject;
		String[] result = {};
		try {

			jsonObject = getJSON(label);
			// System.out.println(jsonObject.toString());
			if (jsonObject != null) {
				JSONArray ja = jsonObject.getJSONObject("results")
						.getJSONArray("bindings");
				result = new String[ja.length()];
				for (int i = 0; i < ja.length(); i++) {
					result[i] = ja.getJSONObject(i).getJSONObject("y")
							.getString("value");
				}
			}
			return result;

		} catch (IOException e) {

			e.printStackTrace();
			return result;
		} catch (JSONException e) {

			e.printStackTrace();
			return result;
		}
	}

	@Override
	URL setURL(String label) {
		{
			try {
				label = URLEncoder.encode(label, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				url = new URL(
						"http://dbpedia.org/sparql?query=select+distinct+%3Fy+where+%7B%3Fx+rdfs%3Alabel+%22"
								+ label
								+ "%22%40en.%0D%0A%3Fx+rdf%3Atype+%3Fy%7D+LIMIT+100&format=json");
				return url;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			}

		}

	}
}
