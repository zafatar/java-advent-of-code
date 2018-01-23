package com.zafatar.adventofcode.y2017;

import static org.junit.Assert.*;

import org.junit.Test;

public class Day8Test {
	Day8 day8;
	
	@Test
	public void testSolvePart8With_1122() {
		day8 = new Day8();
		day8.solvePart1();

		assertTrue( day8.result == 1);
		assertFalse( day8.result == 888);
	}

	@Test
	public void testSolvePart2With_1212() {
		day8 = new Day8();
		day8.solvePart2();

		assertTrue( day8.result == 10);
		assertFalse( day8.result == 1000);
	}
}
