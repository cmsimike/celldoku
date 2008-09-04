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

/**
 * @author mike
 * Representation for each of the cells on a board.
 */
public class CelldokuBoardCell {
	private boolean _isStartingPiece;
	private String _currentValue;

	/**
	 * @return Returns the _currentValue.
	 */
	public String getCurrentValue() {
		return _currentValue;
	}

	/**
	 * @param value The _currentValue to set.
	 */
	public void setCurrentValue(String value) {
		_currentValue = value;
	}

	/**
	 * @return Returns the _startingPiece.
	 */
	public boolean isStartingPiece() {
		return _isStartingPiece;
	}

	/**
	 * @param piece The _startingPiece to set.
	 */
	public void setIsStartingPiece(boolean piece) {
		_isStartingPiece = piece;
	}
	/**
	 * Sets both values
	 * @param isStarting - the value
	 * @param value - is it part of the starting set?
	 */
	void setIsStartingPieceAndValue(boolean isStarting, String value) {
		_isStartingPiece = isStarting;
		_currentValue = value;
	}
}
