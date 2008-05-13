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
 * Interface to the Displayable component higher up so that
 * we can re-set the currently displayed board to the
 * end game screen.
 */
public interface DisplayInterface {
	public void showEndGameScreen(String message);
}
