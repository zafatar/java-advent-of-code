package com.zafatar.adventofcode.y2017;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.zafatar.adventofcode.utils.FileReader;

public class Day6 implements Day {
	private String inputFile = "/2017/Day6/input";
	public ArrayList<Integer> input;
	public HashMap<String, Integer> seenMap;
	
	public Day6() {
		super();
		this.init();
	}
	
	@Override
	public void init() {
		this.input = new ArrayList<Integer>();
		this.seenMap = new HashMap<String, Integer>();
		
		this.prepareInput(this.inputFile);
	}
	
	@Override
	public void solvePart1() {
		this.init();
		
		int noCycles = this.calculateNoCycles();
		
		System.out.println("Day #6 - Part #1: " + noCycles);
	}

	@Override
	public void solvePart2() {
		this.init();

		int noCycles = this.calculateNoCycles();
		
		// signature of last list of banks (repeating one).
		String lastSignature = this.generateSignature();         
	
		// check when was the last seen this exact signature.
		int noCyclePrevSeen  = this.seenMap.get(lastSignature);  
		int totalCycles = noCycles - noCyclePrevSeen;
		
		System.out.println("Day #6 - Part #2: " + totalCycles);
	}

	/**
	 * This method calculates the number of cycles run through 
	 * until a repeating list of banks s
	 * 
	 * @return
	 */
	private int calculateNoCycles() {
		int noCycles = 0;
		// Cycles -run while the banks are not seen before.
		while(!this.seenBefore(noCycles)) {
			// Find the max and its index in this cycle
			int max = Collections.max(this.input);
			int maxIndex = this.input.indexOf(max); // returns the smallest in tie.
			
			// Set max bank to 0.
			this.input.set(maxIndex, 0);
			
			// Distribute the blocks of the max bank blocks, 
			// starting from max bank index+1.
			// TODO: we might need to distance, just use max effectively. 
			int distance = 1;
			while(max > 0) {
				int nextIndex = ( maxIndex + distance ) % this.input.size(); // mod to array size.
				this.input.set( nextIndex, this.input.get(nextIndex) + 1);   // increase, +1.
				
				distance++;
				max--;
			}
		
			// Count the number of cycle.
			noCycles++;
		}
		
		return noCycles;
	}
	
	@Override
	public void prepareInput(String filepath) {
		FileReader fr = new FileReader(filepath);

		String[] lines = fr.getContent().split("\n");
		for(String line: lines) {
			String[] numbers = line.split("\t");
			for(String number: numbers) {
				this.input.add(Integer.parseInt(number));				
			}
		}
	}
	
	/**
	 * This method checks if the list of banks with the stored number 
	 * of blocks are seen before. If yes, it stores the number of cycle 
	 * at which the list of banks has been seen.
	 * This will be used in the second part of the day.
	 * 
	 * @param noCycles
	 * @return
	 */
	private Boolean seenBefore(int noCycles) {
		String signature = this.generateSignature(); 
		Boolean ret = Boolean.FALSE;
		
		if (seenMap.containsKey(signature)) {
			ret = Boolean.TRUE;
		}
		else {
			seenMap.put(signature, noCycles); // we are keeping the number 
		}
		
		return ret;
	}

	/**
	 * This method generates a unique signature for each list of banks. 
	 * For ex. 1 3 14 => 1_3_14. In this way, it generates the key of 
	 * seen maps for banks in the cycles.
	 * 
	 * @return the generated unique string.
	 */
	private String generateSignature() {
		return String.join("_", this.input.toString());
	}
}

/**
 * --- Day 6: Memory Reallocation ---
 * 
 * A debugger program here is having an issue: it is trying to repair a memory
 * reallocation routine, but it keeps getting stuck in an infinite loop.
 * 
 * In this area, there are sixteen memory banks; each memory bank can hold any
 * number of blocks. The goal of the reallocation routine is to balance the
 * blocks between the memory banks.
 * 
 * The reallocation routine operates in cycles. In each cycle, it finds the
 * memory bank with the most blocks (ties won by the lowest-numbered memory
 * bank) and redistributes those blocks among the banks. To do this, it removes
 * all of the blocks from the selected bank, then moves to the next (by index)
 * memory bank and inserts one of the blocks. It continues doing this until it
 * runs out of blocks; if it reaches the last memory bank, it wraps around to
 * the first one.
 * 
 * The debugger would like to know how many redistributions can be done before a
 * blocks-in-banks configuration is produced that has been seen before.
 * 
 * For example, imagine a scenario with only four memory banks:
 * 
 * - The banks start with 0, 2, 7, and 0 blocks. The third bank has the 
 *   most blocks, so it is chosen for redistribution. 
 * - Starting with the next bank (the fourth bank) and then continuing to 
 *   the first bank, the second bank, and so on, the 7 blocks are spread 
 *   out over the memory banks. The fourth, first, and second banks get two 
 *   blocks each, and the third bank gets one back. The final result looks 
 *   like this: 2 4 1 2. 
 * - Next, the second bank is chosen because it contains the most blocks 
 *   (four). Because there are four memory banks, each gets one block. The 
 *   result is: 3 1 2 3.
 * - Now, there is a tie between the first and fourth memory banks, both of 
 *   which have three blocks. The first bank wins the tie, and its three 
 *   blocks are distributed evenly over the other three banks, leaving it 
 *   with none: 0 2 3 4. 
 * - The fourth bank is chosen, and its four blocks are distributed such 
 *   that each of the four banks receives one: 1 3 4 1. 
 * - The third bank is chosen, and the same thing happens: 2 4 1 2.
 * 
 * At this point, we've reached a state we've seen before: 2 4 1 2 was already
 * seen. The infinite loop is detected after the fifth block redistribution
 * cycle, and so the answer in this example is 5.
 * 
 * Given the initial block counts in your puzzle input, how many redistribution
 * cycles must be completed before a configuration is produced that has been
 * seen before?
 * 
 * Your puzzle answer was ***. 
 * 
 * --- Part Two ---
 * 
 * Out of curiosity, the debugger would also like to know the size of the loop:
 * starting from a state that has already been seen, how many block
 * redistribution cycles must be performed before that same state is seen again?
 * 
 * In the example above, 2 4 1 2 is seen again after four cycles, and so the
 * answer in that example would be 4.
 * 
 * How many cycles are in the infinite loop that arises from the configuration
 * in your puzzle input?
 * 
 * Your puzzle answer was ***.
 * 
 */