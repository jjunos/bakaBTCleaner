package com.jjunos.manga.core.html.parser.wikipedia;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class WikipediaHtmlParser {

	public void test() {
		try {
			
			Document wiki = Jsoup.connect("http://en.wikipedia.org/wiki/List_of_Hajime_no_Ippo_manga_volumes").get();
			Elements volumeHeader = wiki.select("h2 span[id=Volume_list]");
			
			/**
			 * Going to be honest, pretty sure there is a better way to do this, but will just do this for now.
			 */
			
			if( volumeHeader == null ) {
				return;
			}
			
			Elements select = wiki.select("h3 span[class=mw-headline]");
			
			Iterator i = select.iterator();
			
			while( i.hasNext() ) {
				Element e = (Element) i.next();
				System.out.println("Element pure = : " + e.parent() );
				System.out.println( "Element = : " + e.html() );
				
				Element p  = e.parent();
				Node div = p.nextSibling().nextSibling();
				
				System.out.println("DIV: " + div );
				Document parse = Jsoup.parse( div.toString() );
				
				String html =  parse.select( "a").attr( "href") ;
				System.out.println("DIV PARSE: " + parse.select( "a").attr( "href") );
				
				Connection connect = Jsoup.connect( "http://en.wikipedia.org" + html );
				
				System.out.println("Final: " + connect.get() );
				
//				Element parent = e.parent();
//				// search for the volume sub links
////				Elements select2 = e.parents().select( "a" );
////				
//				System.out.println( "What is this : " + parent.select( "a" ).attr( "href") );
				
//				System.out.println("What is this :  " + select2 );
			}
			
			
//			Element volumes = volumeHeader.first();
//			
//			Iterator<Element> iterator = volumeHeader.iterator();
//			while( iterator.hasNext() ) {
//				Element e = iterator.next();
//				
//				System.out.println( "Element : " + e );
//			}
			
//			System.out.println("Volume element : " + volumes );
//			Node wikiHolder = volumes.parentNode();
//			
//			System.out.println("Node holder : " + wikiHolder );
//			
//			Map<String, Element> allVols = new HashMap<String, Element>();
//			
//			Node sibling = null;
//			
//			int count = 0;
//			do {
//				sibling = wikiHolder.nextSibling();
//				System.out.println("Found sibling: " + sibling );
//				count++;
//				wikiHolder = wikiHolder.nextSibling(); 
//			} while( sibling != null && count < 6 );
//			
			
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
