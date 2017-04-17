package com.csc480;

import java.util.ArrayList;
import java.util.List;

public class State {

	private Board board;
	private int height;
	private int manhattan;
	private int heuristic;

	private final static Board GOAL = new Board(new Board().getGoal());

	private State parent;
	private List<State> children;
	
	
	public State(Board board, int height, int manhattan, int heuristic, State parent, List<State> children) {
		
		this.board = board;
		this.height = height;
		this.manhattan = manhattan;
		this.heuristic = heuristic;
		this.parent = parent;
		this.children = children;
	}

	public State(Board board, int height, int heuristic, State parent) {
		this.board = board;
		this.height = height;
		this.heuristic = heuristic;
		this.parent = parent;
		this.children = new ArrayList<State>();
	}
	
	public State(Board board) {
		this.board = board;
		this.parent = null;
		this.children = new ArrayList<State>();
		height = 0;
		calculateHeuristic();
		
	}
	public State(Board board, int height) {
		this.board = board;
		this.height = height;
		this.parent = null;
		this.children = new ArrayList<State>();
	}
	public State(Board board, int height, State parent) {
		this.board = board;
		this.height = height;
		this.parent = parent;
		this.children = new ArrayList<State>();
		calculateHeuristic();
	}
	

	public void calculateHeuristic() {
		calculateManhattan();
		heuristic = height + manhattan;
	}

	private void calculateManhattan() {
		for (int i = 0; i < board.getBoard().size(); i++) {
			for (int j = 0; j < board.getBoard().get(i).size(); j++) {
				if (board.getBoard().get(i).get(j).getValue() != 0) {
					manhattan += findValueInGoalState(board.getBoard().get(i).get(j).getValue(), i, j);
				}
			}
		}
	}

	private int findValueInGoalState(int value, int row, int col) {
		for (int i = 0; i < GOAL.getBoard().size(); i++) {
			for (int j = 0; j < GOAL.getBoard().get(i).size(); j++) {
				if (GOAL.getBoard().get(i).get(j).getValue() == value) {
					return Math.abs(row - i) + Math.abs(col - j);
				}
			}
		}
		return 0;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getManhattan() {
		return manhattan;
	}

	public void setManhattan(int manhattan) {
		this.manhattan = manhattan;
	}

	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	public State getParent() {
		return parent;
	}

	public void setParent(State parent) {
		this.parent = parent;
	}

	public List<State> getChildren() {
		return children;
	}

	public void setChildren(List<State> children) {
		this.children = children;
	}
	
	

}
