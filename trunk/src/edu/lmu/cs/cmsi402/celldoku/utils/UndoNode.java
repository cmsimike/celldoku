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

public class UndoNode {
	private int _x;
	private int _y;
	private String _value;

	public UndoNode(int x, int y, String value) {
		_x = x;
		_y = y;
		_value = value;
	}

	/**
	 * @return Returns the _value.
	 */
	public String getValue() {
		return _value;
	}

	/**
	 * @return Returns the _x.
	 */
	public int getX() {
		return _x;
	}

	/**
	 * @return Returns the _y.
	 */
	public int getY() {
		return _y;
	}
}
