package com.untitled.archer;

import android.graphics.Point;
import com.untitled.game.GameObject;
import com.untitled.need.Device;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 21.05.13
 * Time: 16:52
 */
public class Player extends GameObject{

	private Shot shot;
	private Device device;

	private Point p1;
	private Point p2;

	public Player(int aXPos, int aYPos, int aWidth, int aHeight) {
		super(aXPos, aYPos, aWidth, aHeight);
		setVisible(true);
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

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Point getP1() {
		if (p1 == null) {
			p1 = new Point();
		}
		return p1;
	}

	public Point getP2() {
		if (p2 == null) {
			p2 = new Point();
		}
		return p2;
	}
}
