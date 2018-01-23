package com.zafatar.adventofcode.y2017.day8;


/**
 * This class represents the register in the instructions.
 * It's composed by a key and a number 
 * which holds the actual value of the register.
 *  
 * @author zafatar
 *
 */
public class Register {
	public String key;
	private int value = 0;  // "The registers all start at 0" 
	
	public Register(String key) {
		this.key = key;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
