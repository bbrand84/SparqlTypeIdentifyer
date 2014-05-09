package de.fraunhofer.fokus.sti;

import java.util.HashMap;

import javax.print.attribute.HashAttributeSet;


public class Start {

	public static void main(String[] args) {
//		CrawlerDBPedia crawler = new CrawlerDBPedia();
//		String[] list = crawler.extract("Microsoft");
//		
//		for(String x :list)
//		{
//			System.out.println(x);
//		}
//		
//		list = crawler.extract("Apple Inc.");
//		System.out.println();
//		
//		for(String x :list)
//		{
//			System.out.println(x);
//		}
		

			
		
		LabelList ll= new LabelList(new CrawlerDBPedia());
		String[] companies = new String[]{"Apple Inc.", "Microsoft","Asea Brown Boveri","Avex Group","Beiersdorf AG","British Steel"};
		String[] fruits = new String[]{"Banana","Apple","Kiwi","Peach","Pear","Avocado"};
		
		ll.getOrderedFrequencyList(companies);
		System.out.println("--------------");
		ll.getOrderedFrequencyList(fruits);

	}

}
