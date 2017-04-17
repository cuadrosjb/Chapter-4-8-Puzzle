package com.csc480;

import java.util.ArrayList;
import java.util.List;

/**
 * Board class: sets a 3x3 board for the 8-puzzle
 * 
 * @author Jeffrey B Cuadros
 * 
 *
 *
 */
public class Board {

	private List<List<Tile>> board;
	private List<List<Tile>> goal;

	private int row;
	private int col;

	private String prevAction = "none";
	
	
	

	/**
	 * Class empty constructor
	 */
	public Board() {
		this.board = new ArrayList<List<Tile>>();
		initBoard();
		initGoalState();
		getZeroLocation();
	}

	/**
	 * @param board copies the list inside the Board object
	 */
	public Board(List<List<Tile>> board) {
		this.board = board;
		initGoalState();
		getZeroLocation();
	}

	/**
	 * @param board copies the instance of another Board
	 */
	public Board(Board board) {
		this.board = board.getBoard();
		this.row = board.getRow();
		this.col = board.getCol();
		initGoalState();
		getZeroLocation();
	}

	/**
	 * @param board sets List from an Board instance
	 * @param row sets row from an Board instance
	 * @param col sets col from an Board instance
	 * @param prevAction sets prevAction from an Board instance
	 */
	public Board(List<List<Tile>> board, int row, int col, String prevAction) {
		this.board = board;
		this.row = row;
		this.col = col;
		this.prevAction = prevAction;
		initGoalState();
		getZeroLocation();
	}

	/**
	 * @param board deep copies an Board instance, create a new one.
	 * @return
	 */
	public static Board copy(Board board) {
		Board cBoard = new Board();
		cBoard.setBoard(board.getBoard());
		cBoard.setRow(board.getRow());
		cBoard.setCol(board.getCol());

		cBoard.getZeroLocation();
		cBoard.initGoalState();
		return cBoard;
	}

	/**
	 * Initializes the board to the initial state
	 */
	private void initBoard() {
		List<Tile> row1 = new ArrayList<Tile>();
		row1.add(new Tile(7));
		row1.add(new Tile(2));
		row1.add(new Tile(4));
		board.add(row1);
		row1 = new ArrayList<Tile>();
		row1.add(new Tile(5));
		row1.add(new Tile(0));
		row1.add(new Tile(6));
		board.add(row1);
		row1 = new ArrayList<Tile>();
		row1.add(new Tile(8));
		row1.add(new Tile(3));
		row1.add(new Tile(1));
		board.add(row1);
	}

	/**
	 * Initializes the goal state
	 */
	private void initGoalState() {
		this.goal = new ArrayList<List<Tile>>();
		List<Tile> row1 = new ArrayList<Tile>();
		row1.add(new Tile(0));
		row1.add(new Tile(1));
		row1.add(new Tile(2));
		goal.add(row1);
		row1 = new ArrayList<Tile>();
		row1.add(new Tile(3));
		row1.add(new Tile(4));
		row1.add(new Tile(5));
		goal.add(row1);
		row1 = new ArrayList<Tile>();
		row1.add(new Tile(6));
		row1.add(new Tile(7));
		row1.add(new Tile(8));
		goal.add(row1);
	}

	/**
	 * Print the current state
	 */
	public void print() {
		for (List<Tile> lstT : board) {
			for (Tile t : lstT) {
				System.out.print(t.getValue() + "\t");
			}
			System.out.println("\r");
		}
	}

	/**
	 * Prints the goal State
	 */
	public void printGoal() {
		for (List<Tile> lstT : goal) {
			for (Tile t : lstT) {
				System.out.print(t.getValue() + "\t");
			}
			System.out.println("\r");
		}
	}

	/**
	 * @return true if the state is the goal state
	 */
	public boolean isGoalState() {
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.get(i).size(); j++) {
				if (board.get(i).get(j).getValue() != goal.get(i).get(j).getValue()) {
					return false;
				}
			}
		}
		return true;
	}

	public List<List<Tile>> getBoard() {
		return board;
	}

	public void setBoard(List<List<Tile>> board) {
		this.board = board;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * sets the location of the Zero or spacer
	 */
	public void getZeroLocation() {
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.get(i).size(); j++) {
				if (board.get(i).get(j).getValue() == 0) {
					row = i;
					col = j;
					return;
				}

			}
		}
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * swaps places with the value on top
	 */
	public void swapUp() {
		int temp = row - 1;
		int value = board.get(temp).get(col).getValue();
		board.get(temp).get(col).setValue(0);
		board.get(row).get(col).setValue(value);
		prevAction = "up";
	}

	/**
	 * swaps places with the value under
	 */
	public void swapDown() {
		int temp = row + 1;
		int value = board.get(temp).get(col).getValue();
		board.get(temp).get(col).setValue(0);
		board.get(row).get(col).setValue(value);
		prevAction = "down";
	}

	/**
	 * swaps places with the value on the left
	 */
	public void swapLeft() {
		int temp = col - 1;
		int value = board.get(row).get(temp).getValue();
		board.get(row).get(temp).setValue(0);
		board.get(row).get(col).setValue(value);
		prevAction = "left";
	}

	/**
	 * swaps places with the value on the right
	 */
	public void swapRight() {
		int temp = col + 1;
		int value = board.get(row).get(temp).getValue();
		board.get(row).get(temp).setValue(0);
		board.get(row).get(col).setValue(value);
		prevAction = "right";
	}

	public String getPrevAction() {
		return prevAction;
	}

	public void setPrevAction(String prevAction) {
		this.prevAction = prevAction;
	}

	public List<List<Tile>> getGoal() {
		return goal;
	}

	public void setGoal(List<List<Tile>> goal) {
		this.goal = goal;
	}
	
	
	

}
