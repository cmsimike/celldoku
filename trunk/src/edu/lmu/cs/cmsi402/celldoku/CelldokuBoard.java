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
along with Foobar.  If not, see <http://www.gnu.org/licenses/>.*/

package edu.lmu.cs.cmsi402.celldoku;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import edu.lmu.cs.cmsi402.celldoku.utils.CelldokuConstants;
import edu.lmu.cs.cmsi402.celldoku.utils.Undo;
import edu.lmu.cs.cmsi402.celldoku.utils.UndoNode;
/**
 * @author mike
 * The Celldoku game board.
 */
public class CelldokuBoard {
	private Image _boardImage;
	private CelldokuCursor _cursor;
	private CelldokuBoardCell[][] _cells;
	private String _solution;
	private int _width;
	private int _height;
	private boolean _isGameOver;
	private Undo _undo;
	private boolean _showError =  false;
	private boolean[] _globalErrors;
	private Image _tempimage;

	public CelldokuBoard(int width, int height, int difficulty) throws IOException {
		_undo = new Undo();
		_isGameOver = false;
		_boardImage = Image.createImage("/board_v3.gif");
		_tempimage = Image.createImage("/test.gif");
		_cursor = new CelldokuCursor();
		_cells = new CelldokuBoardCell[9][9];
		for(int i = 0; i < _cells.length; i++){
			for(int j = 0; j < _cells[i].length; j++){
				_cells[j][i] = new CelldokuBoardCell();
			}
		}
		SudokuPuzzle puzzle = new SudokuPuzzle(difficulty);
		_solution = puzzle.getSolution();
		populateCells(_solution, puzzle.getStartingSet());
		_width = width;
		_height = height;
	}

	/**
	 * Knows how to paint itself.
	 * @param g
	 */
	protected void paintBoard(Graphics g) {
		//clear the screen
		g.setColor(CelldokuConstants.BLACK);
		g.fillRect(0, 0, _width, _height);

		//draw the board
		if(_boardImage != null);
			g.drawImage(_boardImage, 0, 0, Graphics.LEFT | Graphics.TOP);

//			now we display the errors
			if(_showError) {
				for(int i = 0; i < _globalErrors.length; i++) {
					if (_globalErrors[i] == true) {
						if(i == 0) {
							g.drawImage(_tempimage, 2, 2, Graphics.LEFT | Graphics.TOP);
						} else if (i == 1) {
							g.drawImage(_tempimage, 60, 2, Graphics.LEFT | Graphics.TOP);
						} else if (i == 2) {
							g.drawImage(_tempimage, 118, 2, Graphics.LEFT | Graphics.TOP);
						} else if (i == 3) {
							g.drawImage(_tempimage, 2, 60, Graphics.LEFT | Graphics.TOP);
						} else if (i == 4) {
							g.drawImage(_tempimage, 60, 60, Graphics.LEFT | Graphics.TOP);
						} else if (i == 5) {
							g.drawImage(_tempimage, 118, 60, Graphics.LEFT | Graphics.TOP);
						} else if (i == 6) {
							g.drawImage(_tempimage, 2, 118, Graphics.LEFT | Graphics.TOP);
						} else if (i == 7) {
							g.drawImage(_tempimage, 60, 118, Graphics.LEFT | Graphics.TOP);
						} else if (i == 8) {
							g.drawImage(_tempimage, 118, 118, Graphics.LEFT | Graphics.TOP);
						}
					}
				}
			}
		//draw the cursor
		g.setColor(CelldokuConstants.BLUE);
		//draw the cursor
		g.fillRect(3 + (19 * _cursor.getX()) + _cursor.getXOffset(), 3 + (19 * _cursor.getY()) + _cursor.getYOffset(), 15, 15);


		//draw the numbers
		g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_LARGE));
		for(int i = 0; i < _cells.length; i++) {
			for(int j = 0; j < _cells[i].length; j++) {
				if(_cells[j][i].isStartingPiece()) {
					g.setColor(CelldokuConstants.RED);
					g.drawString(_cells[j][i].getCurrentValue(), (8 + (18 * j)) + ((j/3) * 4), 2 + (19 * i) + (i/3), Graphics.TOP | Graphics.LEFT );
				} else{

					if(!_cells[j][i].getCurrentValue().equals("0")){
						g.setColor(CelldokuConstants.BLACK);
						g.drawString(_cells[j][i].getCurrentValue(), (8 + (18 * j)) + ((j/3) * 4), 2 + (19 * i) + (i/3), Graphics.TOP | Graphics.LEFT );
					}
				}
			}
		}
	}

	/**
	 * Adds an entry to the board
	 * @param string
	 */
	public void addToBoard(String string) {
		if (!_cells[_cursor.getX()][_cursor.getY()].isStartingPiece()) {
			_undo.push(new UndoNode(_cursor.getX(), _cursor.getY(), _cells[_cursor.getX()][_cursor.getY()].getCurrentValue()));
			_cells[_cursor.getX()][_cursor.getY()].setCurrentValue(string);
			isCorrectAnswer();
		}
	}

	/**
	 * Fills the cells with the game
	 * @param solution
	 * @param startingSet
	 */
	private void populateCells(String solution, String startingSet) {
		for(int i =0; i < _cells.length; i++) {
			for(int j = 0; j < _cells[i].length; j++) {
				if(startingSet.charAt(((i*9) + j)) == '0')
					_cells[j][i].setIsStartingPieceAndValue(false, "0");
				else
					_cells[j][i].setIsStartingPieceAndValue(true, new Character(solution.charAt(((i * 9) + j))).toString());
			}
		}
	}

	/**
	 * Checks for end game state
	 *
	 */
	private void isCorrectAnswer() {
		for(int i = 0; i < _cells.length; i++) {
			for (int j = 0; j < _cells[i].length; j++) {
				if(!_cells[j][i].getCurrentValue().equals(_solution.substring((9 * i) + j, (9 * i) + j + 1))) {
					return;
				}
			}
		}
		_isGameOver = true;
	}

	/**
	 * the movement of the cursor
	 * @param direction
	 */
	public void moveCursor(int direction) {
		switch(direction) {
		case (Canvas.UP):
			_cursor.moveUp();
			break;
		case (Canvas.DOWN):
			_cursor.moveDown();
			break;
		case (Canvas.LEFT):
			_cursor.moveLeft();
			break;
		case (Canvas.RIGHT):
			_cursor.moveRight();
			break;
		}
		_showError = false;
	}

	public boolean isGameOver() {
		return _isGameOver;
	}

	/**
	 * Pops the last change from the stack
	 *
	 */
	public void pop() {
		UndoNode node = _undo.pop();
		if (node != null) {
			int x = node.getX();
			int y = node.getY();
			String number = node.getValue();
			_cells[x][y].setCurrentValue(number);
		}
	}

	public void showError() {
		_globalErrors = calculateErrors();
		_showError = !_showError;
	}

	/**
	 * Figures out the subgrids with errors in them
	 * @return The errors
	 */
	private boolean[] calculateErrors() {
		boolean[] sections = new boolean[_cells.length];
		for(int i = 0; i < sections.length; i++) {
			sections[i] = false;
		}
		for(int i = 0; i < _cells.length; i++) {
			for (int j = 0; j < _cells[i].length; j++) {
				if(!_cells[j][i].getCurrentValue().equals(_solution.substring((9 * i) + j, (9 * i) + j + 1))) {
					if(!_cells[j][i].getCurrentValue().equals("0")) {
					//first row of 3x3 subgrids
						if((i%_cells.length == 0) || (i%_cells.length == 1) || (i%_cells.length == 2)) {
							if((j%_cells[i].length == 0) || (j%_cells[i].length == 1) || (j%_cells[i].length == 2)) {
	//							first column of 3x3 subgrids
								sections[0]= true;
							} else if ((j%_cells[i].length == 3) || (j%_cells[i].length == 4) || (j%_cells[i].length == 5)) {
	//							second column of 3x3 subgrids
								sections[1]= true;
							} else if ((j%_cells[i].length == 6) || (j%_cells[i].length == 7) || (j%_cells[i].length == 8)) {
	//							third column of 3x3 subgrids
								sections[2]= true;
							}
							//second row of 3x3 subgrids
						} else if ((i%_cells.length == 3) || (i%_cells.length == 4) || (i%_cells.length == 5)) {
							if((j%_cells[i].length == 0) || (j%_cells[i].length == 1) || (j%_cells[i].length == 2)) {
	//							first column of 3x3 subgrids
								sections[3]= true;
							} else if ((j%_cells[i].length == 3) || (j%_cells[i].length == 4) || (j%_cells[i].length == 5)) {
	//							second column of 3x3 subgrids
								sections[4]= true;
							} else if ((j%_cells[i].length == 6) || (j%_cells[i].length == 7) || (j%_cells[i].length == 8)) {
	//							third column of 3x3 subgrids
								sections[5]= true;
							}
							//thrid row of 3x3 subgrids
						} else if ((i%_cells.length == 6) || (i%_cells.length == 7) || (i%_cells.length == 8)) {
							if((j%_cells[i].length == 0) || (j%_cells[i].length == 1) || (j%_cells[i].length == 2)) {
	//							first column of 3x3 subgrids
								sections[6]= true;
							} else if ((j%_cells[i].length == 3) || (j%_cells[i].length == 4) || (j%_cells[i].length == 5)) {
	//							second column of 3x3 subgrids
								sections[7]= true;
							} else if ((j%_cells[i].length == 6) || (j%_cells[i].length == 7) || (j%_cells[i].length == 8)) {
	//							third column of 3x3 subgrids
								sections[8]= true;
							}
						}
					}
				}
			}
		}
		return sections;
	}
}
