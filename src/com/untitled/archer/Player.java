package com.untitled.archer;

import com.untitled.game.GameObject;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 21.05.13
 * Time: 16:52
 */
public class Player extends GameObject{

	private Shot shot;

	public Player(int aXPos, int aYPos, int aWidth, int aHeight) {
		super(aXPos, aYPos, aWidth, aHeight);

		shot = new Shot(0, 0, 0, 0);
	}

	public void resetShot() {
		shot.setVisible(false);
	}

	public void fireShot(double aHorSpeed, double aVerSpeed) {
		shot.setVisible(true);
		shot.setyPos(getyPos() + getHeight() /2);
		shot.setxPos(getxPos() + getWidth() /2);
		shot.setHorSpeed(aHorSpeed);
		shot.setVerSpeed(aVerSpeed);
	}

	public Shot getShot() {
		return shot;
	}
}
