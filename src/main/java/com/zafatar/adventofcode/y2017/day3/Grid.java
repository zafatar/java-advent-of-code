package com.zafatar.adventofcode.y2017.day3;

import java.util.HashMap;

/**
 * This class represents the Grid structure with a list of coordinates
 * attached to an integer value. The rotation of the grid, i.e. move, is
 * only counter-clockwise for now.
 * 
 * TODO: Design this class as parent class and CW / CWW Grids as child.
 *       All children will have different move method.      
 * 
 * @author zafatar
 *
 */
public class Grid {
	public HashMap<String, Coordinate> points;
	public Coordinate currentCoor;
	public String nextMove;

	public Grid() {
		this.points = new HashMap<String, Coordinate>();
		this.setNextMove(Move.RIGHT); // Starts with right move.
	}

	public void add(Coordinate coor, Integer value) {
		coor.setValue(value);
		this.points.put(coor.getCoordinateKey(), coor);
		this.currentCoor = coor;
	}

	public void addWithNeighborSum() {
		Coordinate coor = this.next();
		this.currentCoor = coor;
		this.currentCoor.setValue(this.neighbourSum());

		this.points.put(this.currentCoor.getCoordinateKey(), this.currentCoor);
	}

	public void add(Integer value) {
		// Create as next coordinate in grid and set its value.
		Coordinate coor = this.next();
		this.currentCoor = coor;
		this.currentCoor.setValue(value);

		// Map it to the grid with coordinates unique key.
		this.points.put(this.currentCoor.getCoordinateKey(), this.currentCoor);
	}

	public Coordinate next() {
		Coordinate next;

		// Initialize grid if the current coordinate is not defined.
		// and define the next move.
		if (this.currentCoor == null) {
			next = new Coordinate(0, 0);
			this.setNextMove(Move.RIGHT);
		}
		// Otherwise, define the next coordinate
		// - Based on Current coordinates.
		// - Prepare the next move.
		else {
			next = new Coordinate( this.currentCoor.getX(), this.currentCoor.getY() );

			if (this.nextMove == Move.RIGHT) {
				next.setX( next.getX() + 1 );

				// Only 2 options for CCW - 
				// if up is not defined yet, move towards here.
				Coordinate up = new Coordinate(next.getX(), next.getY()+1);
				if (this.points.get(up.getCoordinateKey()) == null) {
					this.setNextMove(Move.UP);
				}
				// Otherwise, keep moving to the right.
				else {
					this.setNextMove(Move.RIGHT);
				}
			}
			else if (this.nextMove == Move.LEFT) {
				next.setX( next.getX() - 1 );

				// Only 2 options for CCW - 
				// if down is not defined yet, move towards here.
				Coordinate down = new Coordinate(next.getX(), next.getY()-1);
				if (this.points.get(down.getCoordinateKey()) == null) {
					this.setNextMove(Move.DOWN);
				}
				// Otherwise, keep moving to the left.
				else {
					this.setNextMove(Move.LEFT);
				}
			}
			else if (this.nextMove == Move.UP) {
				next.setY( next.getY() + 1 );

				// Only 2 options for CCW - 
				// if left is not defined yet, move towards here.
				Coordinate left = new Coordinate(next.getX()-1, next.getY());
				if (this.points.get( left.getCoordinateKey() ) == null) {
					this.setNextMove(Move.LEFT);
				}
				// Otherwise, keep moving to the up.
				else {
					this.setNextMove(Move.UP);
				}
			}
			else if (this.nextMove == Move.DOWN) {
				next.setY( next.getY() - 1 );

				// Only 2 options for CCW - 
				// if right is not defined yet, move towards here.
				Coordinate right = new Coordinate(next.getX()+1, next.getY());
				if (this.points.get(right.getCoordinateKey()) == null) {
					this.setNextMove(Move.RIGHT);
				}
				// Otherwise, keep moving to the down.
				else {
					this.setNextMove(Move.DOWN);
				}
			}
		}

		return next;
	}

	public void setNextMove(String moveDirection) {
		this.nextMove = moveDirection;
	}

	/** 
	 * This method returns the sum of the 8 neighbors of 
	 * the current current coordinate.
	 * 
	 * @return sum of the values of the 8 neighbors of current coordinates.
	 */
	public int neighbourSum() {
		int sum = 0;

		if (this.currentCoor.getX() == 0 && this.currentCoor.getY() == 0 ) {
			sum = 1;
		}
		else {
			for ( int i=-1; i<=1; i++) {
				for ( int j=-1; j<=1; j++) {
					Coordinate neighbor = new Coordinate( this.currentCoor.getX() + i, this.currentCoor.getY() + j);
					// If the neighbor is defined and if it's not current coordinate (case for i=0,j=0) 
					if (this.points.get(neighbor.getCoordinateKey()) != null &&
					    this.currentCoor.getCoordinateKey() != neighbor.getCoordinateKey() ) {
						// add the value of the neighbor to the sum.
						sum += this.points.get(neighbor.getCoordinateKey()).getValue();
					}
				}						
			}
		}
		
		return sum;
	}

	public void reset() {
		this.points = new HashMap<String, Coordinate>();
		this.currentCoor = null;
		this.setNextMove(Move.RIGHT); // Starts with right move.
	}
}
