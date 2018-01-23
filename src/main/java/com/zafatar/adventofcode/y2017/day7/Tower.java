package com.zafatar.adventofcode.y2017.day7;

import java.util.ArrayList;

public class Tower {
	public String tag; 
	public int weight;
	public String parentTag;
	public ArrayList<String> subtowers = new ArrayList<String>();
	// Total weight of the tower program. including all subtowers.
	public int totalWeight = 0;
	
	public Tower(String tag, int weight) {
		this.tag = tag;
		this.weight = weight;
	}
}
