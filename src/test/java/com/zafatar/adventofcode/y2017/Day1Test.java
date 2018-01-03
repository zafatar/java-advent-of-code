package com.zafatar.adventofcode.y2017;

import static org.junit.Assert.*;

import org.junit.Test;

public class Day1Test {
	Day1 day1;
	
	@Test
	public void testSolvePart1With_1122() {
		day1 = new Day1();
		day1.solvePart1();

		assertTrue( day1.result == 3);
		assertFalse( day1.result == 5);
	}

	@Test
	public void testSolvePart2With_1212() {
		day1 = new Day1("/2017/Day1/input2");
		day1.solvePart2();

		assertTrue( day1.result == 6);
		assertFalse( day1.result == 5);
	}
}
