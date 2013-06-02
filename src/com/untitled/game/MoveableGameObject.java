package com.untitled.game;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 16.05.13
 * Time: 23:07
 */
public abstract class MoveableGameObject extends GameObject {

	private double horSpeed;
	private double verSpeed;

	public MoveableGameObject(int aXPos, int aYPos, int aWidth, int aHeight, double aHorSpeed, double aVerSpeed) {
		super(aXPos, aYPos, aWidth, aHeight);
		this.horSpeed = aHorSpeed;
		this.verSpeed = aVerSpeed;
	}

	public MoveableGameObject(int aXPos, int aYPos, int aWidth, int aHeight) {
		super(aXPos, aYPos, aWidth, aHeight);
		this.horSpeed = 0;
		this.verSpeed = 0;
	}

	public double getHorSpeed() {
		return horSpeed;
	}

	public void setHorSpeed(double horSpeed) {
		this.horSpeed = horSpeed;
	}

	public void incHorSpeed(double aValue) {
		this.horSpeed += aValue;
	}

	public void decHorSpeed(double aValue) {
		this.horSpeed -= aValue;
	}

	public double getVerSpeed() {
		return verSpeed;
	}

	public void setVerSpeed(double verSpeed) {
		this.verSpeed = verSpeed;
	}

	public void incVerSpeed(double aValue) {
		this.verSpeed += aValue;
	}

	public void decVerSpeed(double aValue) {
		this.verSpeed -= aValue;
	}

	public void move() {
		setxPos(getxPos() + getHorSpeed());
		setyPos(getyPos() + getVerSpeed());
	}

	public void moveReverse() {
		setxPos(getxPos() - getHorSpeed());
		setyPos(getyPos() - getVerSpeed());
	}
}
