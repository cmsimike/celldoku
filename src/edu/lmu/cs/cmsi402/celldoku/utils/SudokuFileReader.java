/*This file is part of Celldoku.

Celldoku is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Foobar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Celldoku.  If not, see <http://www.gnu.org/licenses/>.*/

package edu.lmu.cs.cmsi402.celldoku.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author mike
 * File reader responsible for reading in Sudoku
 * off the phone
 */
public class SudokuFileReader  {
	private String _answer;
	private String _startingSet;
	private InputStream is = null;

	/**
	 * Opens the file and reads in the sudoku puzzle
	 * @param difiiculty  The difficulty of the puzzle to be read
	 */
	public SudokuFileReader(int difiiculty) throws IOException {
		is = this.getClass().getResourceAsStream("/"+ difiiculty + ".txt");
		loadSolution();
		loadStartingSet();
		is.close();
	}

	/**
	 * Reads in the first line (solution board)
	 * @throws IOException
	 */
	private void loadSolution() throws IOException {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 81; i++) {
			sb.append(new Character((char)is.read()).toString());
		}
		_answer = sb.toString();
	}

	/**
	 * Reads in the second line (Starting set)
	 * @throws IOException
	 */
	private void loadStartingSet() throws IOException {
		is.read();
		is.read();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 81; i++) {
			sb.append(new Character((char)is.read()).toString());
		}
		_startingSet = sb.toString();
	}

	public String getAnswer() {
		return _answer;
	}

	public String getStartingSet() {
		return _startingSet;
	}
}
