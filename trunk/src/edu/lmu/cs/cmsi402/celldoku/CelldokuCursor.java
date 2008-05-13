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


/**
 * @author mike
 * Representation of the cursor on the board.
 */
public class CelldokuCursor {
	private int _x;
	private int _y;

	public CelldokuCursor() {
		_x = _y = 0;
	}

	/**
	 * moves the cursor left
	 *
	 */
	public void moveLeft() {
		_x -= 1;
		ensureBounds();
	}

	/**
	 * moves the cursor right
	 *
	 */
	public void moveRight() {
		_x += 1;
		ensureBounds();
	}

	/**
	 * moves the cursor up
	 *
	 */
	public void moveUp() {
		_y -= 1;
		ensureBounds();
	}

	/**
	 * moves the cursor down
	 *
	 */
	public void moveDown() {
		_y += 1;
		ensureBounds();
	}

	/**
	 * Makes sure the cursor stays within the bounds
	 *
	 */
	private void ensureBounds() {
		if(_x == -1) {
			_x = 8;
		}
		if(_x == 9) {
			_x = 0;
		}
		if (_y == -1) {
			_y = 8;
		}
		if (_y == 9) {
			_y = 0;
		}
	}

	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}

	public int getXOffset() {
		return (_x/3);
	}

	public int getYOffset() {
		return (_y/3);
	}
}
