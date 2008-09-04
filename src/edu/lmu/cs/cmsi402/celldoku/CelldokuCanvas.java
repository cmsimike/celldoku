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

package edu.lmu.cs.cmsi402.celldoku;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 * @author mike
 * The object used to set the Display to.
 */
public class CelldokuCanvas extends Canvas {
	private CelldokuBoard _board;
	private DisplayInterface _displayInterface;

	public CelldokuCanvas(int difficulty, DisplayInterface displayInterface) throws IOException {
		super();

		_board = new CelldokuBoard(this.getWidth(), this.getHeight(), difficulty);
		_displayInterface = displayInterface;
	}

	/**
	 * Paint method
	 */
	protected void paint(Graphics g) {
		if(!_board.isGameOver()) {
			_board.paintBoard(g);
		} else {
			_displayInterface.showEndGameScreen("Congratulations! You have beat this puzzle!");
		}
	}

	/**
	 * The keypress actions.
	 */
	protected void keyPressed(int keyCode) {
		switch(keyCode) {
		case (Canvas.KEY_NUM0):
			_board.addToBoard("0");
			break;
		case (Canvas.KEY_NUM1):
			_board.addToBoard("1");
			break;
		case (Canvas.KEY_NUM2):
			_board.addToBoard("2");
			break;
		case (Canvas.KEY_NUM3):
			_board.addToBoard("3");
			break;
		case (Canvas.KEY_NUM4):
			_board.addToBoard("4");
			break;
		case (Canvas.KEY_NUM5):
			_board.addToBoard("5");
			break;
		case (Canvas.KEY_NUM6):
			_board.addToBoard("6");
			break;
		case (Canvas.KEY_NUM7):
			_board.addToBoard("7");
			break;
		case (Canvas.KEY_NUM8):
			_board.addToBoard("8");
			break;
		case (Canvas.KEY_NUM9):
			_board.addToBoard("9");
			break;
		case (Canvas.KEY_POUND):
			_board.showError();
			break;
		case (Canvas.KEY_STAR):
			_board.pop();
			break;
		default:
			switch(getGameAction(keyCode)) {
			case (Canvas.UP):
				_board.moveCursor(Canvas.UP);
				break;
			case (Canvas.DOWN):
				_board.moveCursor(Canvas.DOWN);
				break;
			case (Canvas.LEFT):
				_board.moveCursor(Canvas.LEFT);
				break;
			case (Canvas.RIGHT):
				_board.moveCursor(Canvas.RIGHT);
				break;
			}
		}
		repaint();
	}
}
