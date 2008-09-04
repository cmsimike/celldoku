/*This file is part of Celldoku.

Celldoku is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Celldoku is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Celldoku.  If not, see <http://www.gnu.org/licenses/>.*/

package edu.lmu.cs.cmsi402.celldoku;

import java.io.IOException;
import java.util.Random;

import edu.lmu.cs.cmsi402.celldoku.utils.SudokuFileReader;

/**
 *
 * @author mike
 * Internal representation of a Sudoku.
 * Responsible for randomization.
 */
public class SudokuPuzzle {
	private String _answer;
	private String _startingSet;

	/**
	 * Makes a new puzzle.
	 * @param difficulty The difficulty
	 */
	public SudokuPuzzle(int difficulty) throws IOException {
		SudokuFileReader _reader = new SudokuFileReader(difficulty);
		_answer = _reader.getAnswer();
		_startingSet = _reader.getStartingSet();
		randomizePuzzle();
		randomizeBoard();
	}

	/**
	 * Randomizes the puzzle	 for the char->number translation
	 */
	private void randomizePuzzle() {
		char[] letters = new char[]{'a','b','c','d','e','f','g','h','i'};
		Random rnd = new Random();
		for (int i = 0; i < letters.length; i++) {
			int switchWith = (Math.abs(rnd.nextInt()) % letters.length);
			char temp = letters[i];
			letters[i] = letters[switchWith];
			letters[switchWith] = temp;
		}
		//now replace the letters with the newly generated numbers
		for (int i = 0; i < letters.length; i++) {
			_answer = _answer.replace(letters[i],Integer.toString(i+1).charAt(0));
		}
	}

	/**
	 * Moves the peices of the board around
	 */
	private void randomizeBoard() {
		char[] startingSet = _startingSet.toCharArray();
		char[] answer = _answer.toCharArray();
		Random rnd = new Random();
		//randomize the columns
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++){
				int switchWith = (Math.abs(rnd.nextInt()) % 3);
				for(int k = 0; k < 81; k+=9){
					char temp = startingSet[(3*i)+j+k];
					char tempAnswer = answer[(3*i)+j+k];
					startingSet[(3*i)+j+k] = startingSet[(3*i)+switchWith+k];
					answer[(3*i)+j+k] = answer[(3*i)+switchWith+k];
					startingSet[(3*i)+switchWith+k] = temp;
					answer[(3*i)+switchWith+k] = tempAnswer;
				}
			}
		}
		//randomize the rows
		for(int i = 0; i < 72; i+=27) {
			for(int j = 0; j < 18; j+=9){
				int switchWith = ((Math.abs(rnd.nextInt()) % 3) * 9) + i;
				for(int k = 0; k < 9; k++){
					char temp = startingSet[i+j+k];
					char tempAnswer = answer[i+j+k];
					startingSet[i+j+k] = startingSet[switchWith+k];
					answer[i+j+k] = answer[switchWith+k];
					startingSet[switchWith+k] = temp;
					answer[switchWith+k] = tempAnswer;
				}
			}
		}
		_startingSet = new String(startingSet);
		_answer = new String(answer);
	}

	/**
	 * Returns the solution
	 * @return The answer board
	 */
	public String getSolution() {
		return _answer;
	}

	/**
	 * Returns the starting set
	 * @return The starting set.
	 */
	public String getStartingSet() {
		return _startingSet;
	}
}
