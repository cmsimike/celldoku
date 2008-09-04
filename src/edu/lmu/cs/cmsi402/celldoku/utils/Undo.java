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

package edu.lmu.cs.cmsi402.celldoku.utils;

public class Undo {
	private final int MAX = 100;
	private int _current;
	private UndoNode[] _lastMoves;
	private int _times = 0;

	public Undo() {
		_current = -1;
		_lastMoves = new UndoNode[MAX];
	}

	/**
	 * Pushes the change onto the stack
	 * @param n The cell that's changing
	 */
	public void push(UndoNode n) {
		_current++;
		ensureLimits();
		_lastMoves[_current] = n;
		_times++;
		ensureLimits();
	}

	/**
	 * Returns the previous board state
	 * @return The recent board change undo
	 */
	public UndoNode pop() {
		if (_current < 0 || _lastMoves[_current] == null)
			return null;
		UndoNode n = _lastMoves[_current];
		_lastMoves[_current] = null;
		_current--;
		ensureLimits();
		return n;
	}

	/**
	 * Makes sure the limits are maintained
	 *
	 */
	private void ensureLimits() {
		if (_current == -1)
			_current = 99;
		if(_current == MAX)
			_current = 0;
	}

	public int getTimes() {
		return _times;
	}
}
