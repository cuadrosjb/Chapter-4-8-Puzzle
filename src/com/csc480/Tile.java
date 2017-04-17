package com.csc480;

/**
 * Tile class: object the holds one and only one value
 * 
 * @author Jeffrey B Cuadros
 *
 */
public class Tile {
	
	private int value;

	
	
	public Tile(){
		
	}
	
	public Tile(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
	

}
