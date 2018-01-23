package com.zafatar.adventofcode.y2017;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.MatchResult;
import com.zafatar.adventofcode.utils.FileReader;
import com.zafatar.adventofcode.y2017.day7.Tower;

/**
 * TODO: The structure to be revised. 
 * TODO: the solution of the part #2 should be fixed.
 *  
 * @author zafatar
 *
 */
public class Day7 implements Day {
	private String inputFile = "/2017/Day7/input";
	public HashMap<String, Tower> input = new HashMap<String, Tower>();
	
	public Day7() {
		super();
		this.prepareInput(this.inputFile);
	}

	@Override
	public void solvePart1() {
		String bottomProgramTag = this.getBottomProgramTag();
		System.out.println("Day #7 - Part #1: " + bottomProgramTag );
	}

	private String getBottomProgramTag() {
		// get the first tower key from the list.
		String firstKey = this.input.keySet().toArray()[0].toString(); 
		Boolean isThereParent = Boolean.TRUE;
		Tower t = this.input.get(firstKey);
		 
		// And search for the parent tower. 
		// Bottom program shouldn't have any parent.
		while(isThereParent) {
			if(t.parentTag != null) {
				firstKey = t.parentTag;
				t = this.input.get(firstKey);
			}
			else {
				isThereParent = Boolean.FALSE;
			}
		}
		
		return t.tag;
	}
	
	@Override
	public void solvePart2() {		
		String bottomProgramTag = this.getBottomProgramTag();
		
		int totalW = this.calculateTotalWeight(bottomProgramTag);
		
		this.controlTotalWeight(bottomProgramTag);
		
		this.printSubtowers(bottomProgramTag, 0);
		
		System.out.println("Day #7 - Part #2: " + totalW + " (TODO)");
	}

	private void printSubtowers(String parentTag, int level) {
		Tower t = this.input.get(parentTag);
		
		// String s = "L: " + level + " ";
		// for(int i = 0; i<level; i++){
		// 	s += "\t";
		// }
		//if (level <= 3)
		// System.out.println( s + parentTag + " (" + t.weight + ") - " + t.totalWeight );
		
		level++;
		for(String subtower: t.subtowers) {
			this.printSubtowers(subtower, level);
		}
		
	}
	
	private int calculateTotalWeight(String parentTag) {
		Tower t = this.input.get(parentTag);
		
		if (t.subtowers.size() > 0) {
			t.totalWeight = t.weight;
			for(String subtower: t.subtowers) {
				int w = this.calculateTotalWeight(subtower);
				t.totalWeight += w;
			}
		}
		else {
			t.totalWeight = t.weight;
		}
		
		return t.totalWeight;
	} 
	
	private void controlTotalWeight(String parentTag) {
		Tower t = this.input.get(parentTag);
		
		if (t.subtowers.size() > 0) {
			int w = -1;
			for(String subtower: t.subtowers) {
				Tower st = this.input.get(subtower);
				if (w == -1)
					w = st.totalWeight;
				
				if (w != st.totalWeight ){
					// System.out.println("Found: " + st.tag + " w: " + w + " | stw: " + st.totalWeight);
					// int diff = st.totalWeight - w;
					// System.out.println("Diff : " + diff);
					
					this.controlTotalWeight(st.tag);
				}			
			}
		}
		else {
			t.totalWeight = t.weight;
		}
	}
	
	@Override
	public void prepareInput(String filepath) {
		FileReader fr = new FileReader(filepath);

		String[] lines = fr.getContent().split("\n");
		for(String line: lines) {
			// Split the line by ->
			String[] parts = line.split("->");
			
			// No subtower for this tower, just create it.
			if (parts.length == 1) {
				this.readTagWeigthCreateTower(parts[0]);
			}
			// We have subtowers for this tower.
			else if (parts.length == 2) {
				Tower t = this.readTagWeigthCreateTower(parts[0]);
							
				// Add subtowers if any. 
				String[] subtowers = parts[1].split(",");
				for(String subtower: subtowers) {
					subtower = subtower.trim();
					this.input.get(t.tag).subtowers.add(subtower);
					
					if( this.input.get(subtower) != null) {
						this.input.get(subtower).parentTag = t.tag;
					}
					else {
						Tower ct = new Tower(subtower, 0); // we don't know the weight
						ct.parentTag = t.tag;
						this.input.put(subtower, ct);	
					}
				}
			}
			else {
				System.out.println("There shouldn't be such case");
			}
		}		
	}
	
	/**
	 * This method reads the string in format of xxxxxx (ddd)
	 * and creates a Tower object add it into input if not exists.
	 * 
	 * @return Tower object 
	 */
	private Tower readTagWeigthCreateTower(String parts0) {
		Scanner sc = new Scanner(parts0);
		sc.findInLine("([a-z]+) \\((\\d+)\\)");
		MatchResult mr = sc.match();
		sc.close();

		String tag = mr.group(1);
		int weight = Integer.parseInt(mr.group(2));
		
		// If it's created already, (because it's a subtower of another one)
		// Just add the weight.
		if(this.input.get(tag) != null) {
			this.input.get(tag).weight = weight;
		}
		else {					
			Tower t = new Tower( tag, weight );
			// Add tower to a mapping. Parenttag unknown
			this.input.put( tag, t);
		}
		
		return this.input.get(tag);
	}
}
