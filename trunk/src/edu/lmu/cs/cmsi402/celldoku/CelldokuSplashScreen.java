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
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * @author mike
 * the splash screen for Celldoku
 */
public class CelldokuSplashScreen extends Canvas {
	private Image _splash;
	public CelldokuSplashScreen() throws IOException{
		super();
		_splash = Image.createImage("/splash.gif");
	}

	/**
	 * Paints the splash screen
	 */
	protected void paint(Graphics g) {
		//splash screen!
		g.drawImage(_splash, 0, 0, Graphics.LEFT | Graphics.TOP);
	}

}
