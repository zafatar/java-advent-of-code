package com.zafatar.adventofcode.y2017.day3;

public class Coordinate {
	private String coordinateKey;
	private int x; 
	private int y; 
	private int value;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
		this.setCoordinateKey( this.generateCoordinateKey() );
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
		this.setCoordinateKey( this.generateCoordinateKey() );
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
		this.setCoordinateKey( this.generateCoordinateKey() );
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public String getCoordinateKey() {
		return coordinateKey;
	}

	public void setCoordinateKey(String coordinateKey) {
		this.coordinateKey = coordinateKey;
	}
	
	private String generateCoordinateKey() {
		return this.getX() + "_" + this.getY();
	}	
}
