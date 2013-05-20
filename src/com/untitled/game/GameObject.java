package com.untitled.game;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 16.05.13
 * Time: 23:04
 */
public abstract class GameObject {

	private double xPos;
	private double yPos;
	private int width;
	private int height;

	public GameObject (int aXPos, int aYPos, int aWidth, int aHeight) {
		this.xPos = aXPos;
		this.yPos = aYPos;
		this.width = aWidth;
		this.height = aHeight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
}
