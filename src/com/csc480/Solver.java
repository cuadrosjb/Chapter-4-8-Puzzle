package com.csc480;

import java.util.ArrayList;
import java.util.List;

public class Solver {

	private Board initState;
	private State state;
	
	private static int counter = 0;

	/**
	 * Empty constructor
	 */
	public Solver() {
		initState = new Board();
		state = new State(new Board());
	}

	/**
	 * @return the goal state or null
	 */
	public State findGoalState() {
		return findGoalState(state);
	}

	/**
	 * @param state
	 *            current state of the board
	 * @param limit
	 *            maximum amount of depth
	 * @return goal state or null
	 */
	public State findGoalState(State state) {
		counter++;
		if(state.getBoard().isGoalState()){
			System.out.println("counter: " + counter);
			return state;
		}else{

			List<Board> successors = possibleMoves(state.getBoard());
	
			for (Board child : successors) {
				child.getZeroLocation();
				state.getChildren().add(new State(child, (state.getHeight() + 1), state));
			}
			
			State best = null;
			
			for(State s : state.getChildren()){
				if(best ==null){
					best = s;
				}else{
					if(best.getHeuristic() >= s.getHeuristic()){
						best = s;
					}
				}
			}
			
			State result = findGoalState(best);
			
			if(null != result){
//				System.out.println("counter: " + counter);
				return result;
			}
			
			return null;
			
		}
		
//		return null;
	}

	/**
	 * @param board
	 * @return a deep copy of the passed board
	 */
	public List<List<Tile>> copyBoard(List<List<Tile>> board) {
		List<List<Tile>> copyBrd = new ArrayList<List<Tile>>();
		List<Tile> copyRow;

		for (List<Tile> lstT : board) {
			copyRow = new ArrayList<Tile>();
			for (Tile t : lstT) {
				copyRow.add(new Tile(t.getValue()));
			}
			copyBrd.add(copyRow);
		}
		return copyBrd;
	}

	/**
	 * @param board
	 *            current state of the board
	 * @return list of possible children from current board
	 */
	private List<Board> possibleMoves(Board board) {
		List<Board> move = new ArrayList<Board>();
		board.getZeroLocation();

		Board b;

		try {
			if (!board.getPrevAction().equals("down")) {
				int tempRow = board.getRow();
				int tempCol = board.getCol();
				String tempMoved = board.getPrevAction();
				b = new Board(copyBoard(board.getBoard()), tempRow, tempCol, tempMoved);

				b.swapUp();
				move.add(b);
				b = null;
			}
		} catch (Exception e) {
			b = null;
		}

		try {
			if (!board.getPrevAction().equals("up")) {
				int tempRow = board.getRow();
				int tempCol = board.getCol();
				String tempMoved = board.getPrevAction();
				b = new Board(copyBoard(board.getBoard()), tempRow, tempCol, tempMoved);
				b.swapDown();
				move.add(b);
				b = null;
			}
		} catch (Exception e) {
			b = null;
		}

		try {
			if (!board.getPrevAction().equals("right")) {
				int tempRow = board.getRow();
				int tempCol = board.getCol();
				String tempMoved = board.getPrevAction();
				b = new Board(copyBoard(board.getBoard()), tempRow, tempCol, tempMoved);
				b.getZeroLocation();
				b.swapLeft();
				move.add(b);
				b = null;
			}
		} catch (Exception e) {
			b = null;
		}

		try {
			if (!board.getPrevAction().equals("left")) {
				int tempRow = board.getRow();
				int tempCol = board.getCol();
				String tempMoved = board.getPrevAction();
				b = new Board(copyBoard(board.getBoard()), tempRow, tempCol, tempMoved);
				b.getZeroLocation();
				b.swapRight();
				move.add(b);
				b = null;
			}
		} catch (Exception e) {
			b = null;
		}

		return move;
	}

}
