package com.zafatar.adventofcode.utils;

import java.io.InputStream;
import java.util.Scanner;

public class FileReader {
	private String filepath;
	private String content;
	
	public FileReader(String filepath) {
		this.setFilepath(filepath);
		this.readContent();
	}
	
	private void readContent() {
		InputStream in = getClass().getResourceAsStream(this.getFilepath());
		
		StringBuilder sb = new StringBuilder();
		try (Scanner scanner = new Scanner(in)) {
			while (scanner.hasNextLine()) {				
				String line = scanner.nextLine();
				sb.append(line);
				sb.append("\n");
			}
		}
		
		this.setContent(sb.toString());
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	} 	
}
