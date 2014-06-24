package com.jjunos.manga.core.archiver.zip;

import java.io.File;

import org.junit.Test;


public class ZipManagerTest {

	@Test
	public void unzip() {
		System.out.println("Main");
		ZipManager.unzip( new File(""),  new File("") );
	}
}
