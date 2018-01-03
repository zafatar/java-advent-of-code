package com.zafatar.adventofcode;

import com.zafatar.adventofcode.y2017.Day1;
import com.zafatar.adventofcode.y2017.Day2;
import com.zafatar.adventofcode.y2017.Day3;

public class Main {
	public static void main(String[] args) throws Exception {
		String separator = "----------------";

		// Year 2017 - Day #1.
		Day1 day1 = new Day1();
		day1.solvePart1();				// Day #1 - Part #1
		day1.solvePart2();				// Day #2 - Part #2
		System.out.println(separator);
		
		// Year 2017 - Day #2.
		Day2 day2 = new Day2();			
		day2.solvePart1();				// Day #2 - Part #1
		day2.solvePart2();				// Day #2 - Part #2
		System.out.println(separator);
		
		// Year 2017 - Day #3.
		Day3 day3 = new Day3();			
		day3.solvePart1();				// Day #3 - Part #1
		day3.solvePart2();				// Day #3 - Part #2		
		System.out.println(separator);
	}
}
