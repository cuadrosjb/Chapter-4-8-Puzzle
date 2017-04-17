package com.csc480;

/**
 * Helper class to run the application
 * 
 * @author Jeffrey B Cuadros
 *
 */
public class Environment {

	public static void main(String[] args) {
		Solver s = new Solver();
		State b = s.findGoalState();
		b.getBoard().print();

	}

}
