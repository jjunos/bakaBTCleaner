package com.jjunos.manga.core.archiver.zip;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class ZipManager {

	/**
	 * 
	 * @param zipfile
	 * @param destination This should be a directory
	 */
	public static void unzip( File zipfile, File destination ) {
		try {
			ZipFile zf = new ZipFile( zipfile );
			
			Enumeration<? extends ZipEntry> entries = zf.entries();
			
			while( entries.hasMoreElements() ) {
				ZipEntry nextElement = entries.nextElement();
				System.out.println("Found this : " + nextElement.getName() );
			}
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void zip() {
		
	}
}
