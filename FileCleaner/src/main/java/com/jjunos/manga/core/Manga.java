package com.jjunos.manga.core;

import java.util.ArrayList;
import java.util.List;

public class Manga {

	private String name;
	private List<Volume> volumes;
	
	private boolean underscoreOutput = true;
	
	public Manga( String name, Volume... volumes ) {
		this.name = name;
		this.volumes = new ArrayList<Volume>();
		
		for( Volume v : volumes ) {
			this.volumes.add( v );
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Volume> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<Volume> volumes) {
		this.volumes = volumes;
	}
	
	public String getFileSystemOutput() {
		if( underscoreOutput ) {
			return this.getName().replaceAll(" ",  "_" );
		}
		return this.getName();
	}
	
	@Override
	public String toString() {
		return "Manga [name=" + name + ", volumes=" + volumes + "]";
	}
	
	
}
