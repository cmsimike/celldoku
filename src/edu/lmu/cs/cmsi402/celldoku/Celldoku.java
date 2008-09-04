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

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;

/**
 * @author mike
 * The main game class for Celldoku.
 */
public class Celldoku extends MIDlet implements CommandListener, DisplayInterface {
	private CelldokuCanvas _gameBoard;
	private final Command _exitCmd;
	private final List _difficultySelection;
	private final Command _goBack;
	private CelldokuSplashScreen _splash;

	public Celldoku() {
		//setup the menu options
		_goBack = new Command("OK", Command.BACK, 0);
		_exitCmd = new Command("Quit", Command.EXIT, 0);
		String difficulty[] = {"Easy", "Medium", "Hard", "Evil"};
		_difficultySelection = new List("Select Your Skill Level", Choice.IMPLICIT, difficulty, null);

		//cleanup whatever was running before
		Runtime.getRuntime().gc();

		//setup the difficulty selection menu
		_difficultySelection.addCommand(_exitCmd);
		_difficultySelection.setCommandListener(this);
		try {
			_splash = new CelldokuSplashScreen();
			_splash.addCommand(_goBack);
			_splash.addCommand(_exitCmd);


			_splash.setCommandListener(this);
			Display.getDisplay(this).setCurrent(_splash);
		} catch (IOException e) {
			displaySelectionScreen();
		}
	}

	/**
	 * Inits the board with the given difficulty.
	 * @param difficulty
	 */
	private void setupBoard(int difficulty) {
		//setup the game board
		try {
			_gameBoard = new CelldokuCanvas(difficulty, this);
			_gameBoard.addCommand(_exitCmd);
			_gameBoard.setCommandListener(this);

			Display.getDisplay(this).setCurrent(_gameBoard);
		} catch (IOException e) {
			loadCrash("UPAckage is corrupt. Please reinstall.", "Opps!");
		}
	}

	public void startApp() {

	}

	public void pauseApp() {

	}

	public void destroyApp(boolean unconditional) {

	}

	public void commandAction(Command c, Displayable s) {
		if(c.getCommandType() == Command.EXIT) {
			destroyApp(true);
			notifyDestroyed();
        } else if (c.getCommandType() == Command.BACK) {
        	displaySelectionScreen();
        } else {
        	List display = (List)Display.getDisplay(this).getCurrent();
        	setupBoard(display.getSelectedIndex());
        }
	}

	private void loadCrash(String crashString, String crashTitle){
		Alert alert = new Alert(crashTitle, crashString, null, AlertType.ERROR);
		alert.setTimeout(Alert.FOREVER);
		alert.addCommand(_exitCmd);
		alert.setCommandListener(this);
		Display.getDisplay(this).setCurrent(alert);
	}

	public void showEndGameScreen(String message) {
		Alert alert = new Alert("End Game Resault", message, null, AlertType.INFO);
		alert.setTimeout(Alert.FOREVER);
		alert.addCommand(_goBack);
		alert.addCommand(_exitCmd);
		alert.setCommandListener(this);
		Display.getDisplay(this).setCurrent(alert);
	}

	public void displaySelectionScreen() {
		if (!(Display.getDisplay(this).getCurrent() == _difficultySelection))
			Display.getDisplay(this).setCurrent(_difficultySelection);
	}
}
