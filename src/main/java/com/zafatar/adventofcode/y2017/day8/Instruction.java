package com.zafatar.adventofcode.y2017.day8;

/**
 * This class represents the instruction object 
 * given as a raw string in the format: 
 * 		"b inc 5 if a > 1"
 * - the register to modify, 
 * - whether to increase or decrease that register's value, 
 * - the amount by which to increase or decrease it, 
 * - and a condition.
 * 
 * @author zafatar
 *
 */
public class Instruction {
	public String rawInstruction;
	// Elements of the instruction.
	public Register register;
	public String incOrDec;
	public int amountForIncOrDec;
	public String registerKeyForCondition;
	public String operatorForCondition;
	public int registerValueForCondition;
	// Defined Operators 
	public final Operators operators = new Operators();
	
	public Instruction(String rawInstruction) {
		this.rawInstruction = rawInstruction;
		
		try {
			this.init();
		}
		catch (InvalidInstructionException iie){
			System.out.println(iie.getMessage());
		}
	}
	
	private void init() throws InvalidInstructionException {
		// Split the rawInstruction by if, 
		String[] parts = this.rawInstruction.split(" if ");
		
		// We need to get 2 parts, action and condition. 
		if (parts.length == 2) {
			// action part.
			String action    = parts[0];
			String[] actionParts = action.split(" "); // Split by space.
			
			if (actionParts.length == 3) {
				// TODO: Add more control for this assignments.
				this.register = new Register(actionParts[0]);
				this.incOrDec = actionParts[1];
				this.amountForIncOrDec = Integer.parseInt(actionParts[2]);
			}
			else {
				throw new InvalidInstructionException("Instruction action part is not well-formatted: " + action);
			}
			
			String condition = parts[1];
			String[] conditionParts = condition.split(" "); // Split by space.
			
			if (conditionParts.length == 3) {
				this.registerKeyForCondition = conditionParts[0];
				this.operatorForCondition = conditionParts[1];
				this.registerValueForCondition = Integer.parseInt(conditionParts[2]);
			}
			else {
				throw new InvalidInstructionException("Instruction condition part is not well-formatted: " + condition);
			}
		} 
		else {
			throw new InvalidInstructionException("Cannot split into condition and action blocks: " + this.rawInstruction);
		}
	}
	
	/**
	 * This method makes the comparison of the condition. 
	 * 
	 * @param valueToCheck value to be compared
	 * @param operator compare operator listed in the operators class
	 * @param conditionValue condition value to be used.
	 * @return
	 */
	public boolean conditionCheck(int valueToCheck, String operator, int conditionValue) {
		boolean ret = false;		
		try {
			ret = this.operators.get(operator).compare(valueToCheck, conditionValue);
		} catch (UndefinedOperatorException uoe) {
			System.out.println(uoe.getMessage());
		}		
		
		return ret;
	}
}
