package com.jjunos.manga.core;

import java.io.File;

import org.apache.commons.lang.ArrayUtils;

import com.jjunos.manga.ippo.Ippo;
import com.jjunos.manga.utils.CopyUtils;

/**
 * Simple Manga name/file cleaner.
 * 
 * This will simply take all files in a given directory, and attempt to standardize names.
 * 
 * Todo:
 * 1. Deal with directories that contain volume zips
 * 2. Deal with special chapters
 * 3. Deal with naming conventions
 * 4. Move interal pages
 * 5. Remove artwork (Sometimes pages starting with ZZZ, others with b selections. ie. chap1_p23.jpg vs. chap1_p23b
 * 6. Make option to enforce compression standard to zip or leave as is.
 * 
 * @author jlee
 *
 */
public class Runner {

	
	public static void main(String[] args) {
		CopyUtils cutils = new CopyUtils();
		
		String target = "/tmp/ippo";
		String dest = "/tmp/ippo/final";
		
		if( ArrayUtils.isEmpty( args ) ) {
			System.out.println("Please enter parameters: <target> <Destintion>"); 
			System.out.println("Using default target : " + target );
			System.out.println("Destination : " + dest );
		}
		
		
		cutils.moveManga( new Ippo(), target,  dest );
		
		System.out.println("End");
	}
}
