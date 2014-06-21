package com.jjunos.manga.core;

import org.apache.commons.lang3.Range;

public class Volume {
	
	private String name;
	private int number;
	private Range<Integer> chapters;

	public Volume( String name, int number, int firstChapter, int lastChapter ) {
		this.chapters = Range.between( firstChapter, lastChapter );
		this.name = name;
		this.number = number;
	}
	
	public Range<Integer> getChapters() {
		return chapters;
	}

	public void setChapters(Range<Integer> chapters) {
		this.chapters = chapters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Volume [name=" + name + ", number=" + number + ", chapters="
				+ chapters + "]";
	}
	
	
}