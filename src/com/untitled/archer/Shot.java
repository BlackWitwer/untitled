package com.untitled.archer;

import com.untitled.game.MoveableGameObject;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 21.05.13
 * Time: 16:53
 */
public class Shot extends MoveableGameObject{

	public Shot(int aXPos, int aYPos, int aWidth, int aHeight) {
		super(aXPos, aYPos, aWidth, aHeight);
		setVisible(true);
	}
}
