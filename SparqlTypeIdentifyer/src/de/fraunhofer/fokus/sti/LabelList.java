package de.fraunhofer.fokus.sti;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;

public class LabelList implements Comparator {

	Crawler crawler;

	LabelList(Crawler crawler) {
		this.crawler = crawler;
	}

	private TreeMap frequencies = new TreeMap();

	public String[] getOrderedFrequencyList(String[] names) {
		frequencies = new TreeMap();
		int one = 1;
		for (String name : names) {
			String[] x = crawler.extract(name);
			for (String y : x) {
				if (frequencies.containsKey(y)) {
					frequencies.put(y, (Integer) frequencies.get(y) + 1);
				} else
					frequencies.put((String) y, (Integer) one);
			}
		}
		System.out.println();
		String[] result = new String[frequencies.size()];

		// sort frequencies (requires unsorted treemap to exist before, see
		// function compare)
		TreeMap frequenciesSorted = new TreeMap(this);
		frequenciesSorted.putAll(frequencies);
		frequencies = frequenciesSorted;
		frequenciesSorted = null;

		printTreeMap(frequencies);

		return result;
	}

	private void printTreeMap(TreeMap trmp) {
		String s1 = "";
		Iterator it = trmp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry me = (Map.Entry) it.next();
			int da = (Integer) me.getValue();
			s1 += me.getKey() + "  " + da + "\n";
		}
		System.out.println(s1);
	}

	public int compare(Object o1, Object o2) {
		int dd1 = (Integer) (frequencies.get(o1));
		int dd2 = (Integer) (frequencies.get(o2));
		return (dd1 < dd2) ? 1 : (dd1 > dd2) ? -1 : ((String) o1)
				.compareTo((String) o2);
	}

	// private String[] mapToOrderedStringArray(TreeMap<String, Integer> mp) {
	// String[] keys = new String[mp.size()];
	// String[] vals = new String[mp.size()];
	// Iterator it = mp.entrySet().iterator();
	//
	// while (it.hasNext()) {
	// Map.Entry pairs = (Map.Entry) it.next();
	// System.out.println(pairs.getKey() + " = " + pairs.getValue());
	// it.remove(); // avoids a ConcurrentModificationException
	// }
	// }

}
