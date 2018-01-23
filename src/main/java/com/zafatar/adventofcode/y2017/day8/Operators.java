package com.zafatar.adventofcode.y2017.day8;

import java.util.HashMap;
import java.util.Map;

/** 
 * This class contains the list of operators.
 * Operators can be defined by String.
 * 
 * @author zafatar
 *
 */
public final class Operators {
	private final Map<String, Operator> ops = new HashMap<String, Operator>();
	
	public Operators() {
		// Operator ==
		this.ops.put("==", new Operator() {
			@Override
			public boolean compare(int a, int b) {
				return a == b;
			}	
		});
		
		// Operator != 
		this.ops.put("!=", new Operator() {
			@Override
			public boolean compare(int a, int b) {
				return a != b;
			}	
		});
	
		// Operator >=
		this.ops.put(">=", new Operator() {
			@Override
			public boolean compare(int a, int b) {
				return a >= b;
			}	
		});
		
		// Operator <=
		this.ops.put("<=", new Operator() {
			@Override
			public boolean compare(int a, int b) {
				return a <= b;
			}	
		});
		
		// Operator >
		this.ops.put(">", new Operator() {
			@Override
			public boolean compare(int a, int b) {
				return a > b;
			}	
		});
		
		// Operator >
		this.ops.put("<", new Operator() {
			@Override
			public boolean compare(int a, int b) {
				return a < b;
			}	
		});
	}
	
	public Operator get(String operatorKey) throws UndefinedOperatorException {
		if(this.ops.get(operatorKey) == null) {
			throw new UndefinedOperatorException("undefined operator: " + operatorKey);
		}
		
		return this.ops.get(operatorKey);
	}
}
