package com.jjunos.manga.core.html.parser.wikipedia;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/**
 * Represents the base volume page for a manga.
 * 
 * Should be structured:
 * 
 * <h2> Volume List </h2> 			--> Selector : h2 span[id=Volume_list] 
 * <h3> Volume Information </h3> 	--> Selector : h3 span[class=mw-headline]
 * <div> Volume Link </div>  		--> Selector : a href
 * 
 * Note: Volume List should be numbers separated by a - (dash)
 * 
 * @author jlee
 *
 */
public class WikiBaseVolumePage {

	public void parse() {
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
				
//				Connection connect = Jsoup.connect( "http://en.wikipedia.org" + html );
//				
//				System.out.println("Final: " + connect.get() );
				
//				Element parent = e.parent();
//				// search for the volume sub links
////				Elements select2 = e.parents().select( "a" );
////				
//				System.out.println( "What is this : " + parent.select( "a" ).attr( "href") );
				
//				System.out.println("What is this :  " + select2 );
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getFirstNumberSeparatedByDash( String s ) {
		int i = s.indexOf( '-' );
		if( i < 0 ) {
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		WikiBaseVolumePage b = new WikiBaseVolumePage();
		
		b.parse() ;
	}
}
