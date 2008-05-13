/*This file is part of Celldoku.

Celldoku is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or
(at your option) any later version.

Foobar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Foobar.  If not, see <http://www.gnu.org/licenses/>.*/

package edu.lmu.cs.cmsi402.celldoku.stub;

/**
 * @author mike
 * @deprecated
 * Used to return a premade Sudoku
 */
public class CelldokuGeneration {
	public static String generateCelldoku() {
		return "725839641648152973319746825536478192197523468482691357963285714854917236271364589";
	}
	public static String getGameBoard(String answerBoard) {
		return "025830000000100000300006805000070102090000060402090000903200004000007000000064580";
	}
}
