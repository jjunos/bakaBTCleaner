package com.jjunos.manga.core.html.parser.wikipedia;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class WikipediaHtmlParser {

	public void test() {
		try {
			// File input = new File("/tmp/test.html");
			
			File createTempFile = File.createTempFile( "wikitmp",  "html" );
			Document doc = Jsoup.parse(createTempFile, "UTF-8", "http://en.wikipedia.org/wiki/List_of_Hajime_no_Ippo_manga_volumes");
			
			System.out.println("What did i get : " + doc.html() );
			
			Document doc2 = Jsoup.connect("http://en.wikipedia.org/wiki/List_of_Hajime_no_Ippo_manga_volumes").get();
			Elements newsHeadlines = doc2.select("[id^=Volumes");
			
			System.out.println("What are elements: " + newsHeadlines);
			
			for( Element e : newsHeadlines ) {
//				boolean finished = false;
//				while( finished ) {
//					
//				}
//				
//				System.out.println("E[1]:" + e.html() );
//				
//				System.out.println("E[2]:" + e.nextSibling() );
//				System.out.println("E[3]:" + e.parentNode() );
//
//				System.out.println("Element : " + e.getElementsByAttributeValueStarting("a href", "http") );
				

			}
			
			Document doc3 = Jsoup.connect("http://en.wikipedia.org/wiki/List_of_Hajime_no_Ippo_manga_volumes").get();
			Elements tests = doc3.select("h2 span[id=Volume_list]");
			
			Element foundList = null;
			for( Element e : tests ) {
				System.out.println( "Found new : " + e );
				foundList = e;
			}
			
			Node n = foundList.parentNode();
			
			System.out.println("Current node : " + n );
			
			Node parent = n.parent();
			
			System.out.println("What is super parent : " + parent.childNode( 14 ) );
			
			System.out.println("Child spot : " + n.siblingIndex() );
			System.out.println("Node : "  + n.childNodes() );
			
			
			
			for( Element e : foundList.siblingElements() ) {
				System.out.println("The world: " + e );
				Elements e2 = e.getAllElements();
				
				for( Element ine : e2 ) {
					System.out.println("Inner e : " + ine);
				}
				System.out.println("The world: " + e.getAllElements() );
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		WikipediaHtmlParser parser = new WikipediaHtmlParser();
		
		parser.test();
	}
}
