package com.jjunos.manga.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.jjunos.manga.core.Manga;
import com.jjunos.manga.core.Volume;
import com.jjunos.manga.ippo.Ippo;

public class CopyUtils {

	public int findFirstNumber( String s ) {
		if( s == null ) {
			return -1;
		}
		
		Matcher matcher = Pattern.compile( "\\d+").matcher( s );
		
		boolean found = matcher.find();
		
		if( found ) {
			return Integer.valueOf( matcher.group() ); 
		}
		return -1;
	}
	
	public static void main(String[] args) {
		
		CopyUtils cutils = new CopyUtils();
		
		String s = "zip";
		System.out.println( "Find [" + s + " : " + cutils.findFirstNumber( s ) );
		
		cutils.moveManga( new Ippo(), "/tmp/ippo/",  "/tmp/ippo/final/" );
		
		System.out.println( "Extension: " + s + " : " + CopyUtils.getExtension( s ) );
		
		System.out.println("End");
	}
	
	public void moveManga( Manga manga, String sourceDir, String destDir ) {
		
		// Map<String, Volume> ippo = new HashMap<String, Volume>();
		
//		ippo.put( "81", new Volume( 768,  777 ) );
//		ippo.put( "82", new Volume( 778,  787 ) );
//		ippo.put( "83", new Volume( 788,  798 ) );
//		ippo.put( "84", new Volume( 799,  806 ) );
//		ippo.put( "85", new Volume( 807,  816 ) );
//		ippo.put( "86", new Volume( 817,  826 ) );
//		ippo.put( "87", new Volume( 827,  836 ) );
//		ippo.put( "88", new Volume( 837,  847 ) );
//		ippo.put( "89", new Volume( 848,  857 ) );
//		ippo.put( "90", new Volume( 858,  868 ) );
//		ippo.put( "91", new Volume( 869,  879 ) );
//		ippo.put( "92", new Volume( 880,  890 ) );
//		ippo.put( "93", new Volume( 891,  901 ) );
//		ippo.put( "94", new Volume( 902,  912 ) );
//		ippo.put( "95", new Volume( 913,  923 ) );
//		ippo.put( "96", new Volume( 924,  933 ) );
//		ippo.put( "97", new Volume( 934,  944 ) );
//		ippo.put( "98", new Volume( 945,  954 ) );
//		ippo.put( "99", new Volume( 955,  965 ) );
//		ippo.put( "100", new Volume( 966,  976 ) );
//		ippo.put( "101", new Volume( 977,  988 ) );
//		ippo.put( "102", new Volume( 989,  1000 ) );
//		ippo.put( "103", new Volume( 1001,  1011 ) );
//		ippo.put( "104", new Volume( 1012,  1021 ) );
//		ippo.put( "105", new Volume( 1022,  1032 ) );
//		ippo.put( "106", new Volume( 1033,  9999 ) );
//		
		String[] exts = {"zip","rar"};
		Collection<File> files = FileUtils.listFiles( new File( sourceDir ), exts,  false );
		
//		for( File f : files ) {
//			System.out.println("Found file : " + f.getName() );
//		}
//		
//		
//		try {
//			Thread.sleep( 100000 );
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
		Map<String, List<File> > finalManga = new HashMap<String, List<File>>();
		
		for( File f : files ) {
			System.out.println( "Found file : " + f.getName() );
			
			// attempt to find first number
			int chap = this.findFirstNumber( f.getName() );
			boolean found = false;
			
			System.out.println("Found Chapter : " + chap );
			if( chap > 0 ) {
				// find what range this chapter is in
				for( Volume v : manga.getVolumes() ) {
					
					if( v.getChapters().contains( chap ) ) {
						System.out.println( v );
						String volumeNumber = Integer.valueOf( v.getNumber() ).toString();
						
						System.out.println("Found volume number : " + volumeNumber );
						List<File> chapterFiles = finalManga.get( volumeNumber );
						
						if( chapterFiles == null ) {
							chapterFiles = new ArrayList<File>();
						}
						chapterFiles.add( f );
						found = true;
						
						finalManga.put( volumeNumber, chapterFiles );
						
						break;
					}
				}	
			}
			
			if( !found ) {
				System.out.println("This chapter was not found : " + f.getName() );
			}
		}
		
		// after we've loaded what we want to load, we move/copy files over to their appropriate directory
		for( String vol : finalManga.keySet() ) {
		
			List<File> chapterFiles = finalManga.get( vol );
			
			for( File f : chapterFiles ) {
				
				int chapter = this.findFirstNumber( f.getName() );
				File newFile = new File( destDir + manga.getFileSystemOutput() + "/" + manga.getFileSystemOutput() + "_V" + vol + "/" + manga.getFileSystemOutput() + "_c" + chapter + "." + CopyUtils.getExtension( f.getName() ) );
				try {
					FileUtils.copyFile(f, newFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
		
	}
	
	/**
	 * Will simply return the extension of this file.
	 * @param filename
	 * @return
	 */
	public static String getExtension( String filename ) {
		int i = filename.lastIndexOf('.');
		
		if( i >= 0 ) {
			return filename.substring( i+1,  filename.length() );
		}
		
		return null;
	}
}
