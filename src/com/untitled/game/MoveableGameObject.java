package com.untitled.game;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 16.05.13
 * Time: 23:07
 */
public abstract class MoveableGameObject extends GameObject {

	private double horPart;
	private double verPart;
	private double speed;

	public MoveableGameObject(int aXPos, int aYPos, int aWidth, int aHeight, double aHorPart, double aVerPart, double aSpeed) {
		super(aXPos, aYPos, aWidth, aHeight);
		this.horPart = aHorPart;
		this.verPart = aVerPart;
		this.speed = aSpeed;
	}

	public double getHorPart() {
		return horPart;
	}

	public void setHorPart(double horPart) {
		this.horPart = horPart;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getVerPart() {
		return verPart;
	}

	public void setVerPart(double verPart) {
		this.verPart = verPart;
	}
}
