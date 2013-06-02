package com.untitled.game;

import android.graphics.Canvas;

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
	private boolean visible;

	public GameObject (int aXPos, int aYPos, int aWidth, int aHeight) {
		this.xPos = aXPos;
		this.yPos = aYPos;
		this.width = aWidth;
		this.height = aHeight;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public double getxPos() {
		return xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

//	public void setHeight(int height) {
//		this.height = height;
//	}
//
//	public void setWidth(int width) {
//		this.width = width;
//	}
//
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public boolean checkCollision (GameObject anObject) {
		if (yPos < anObject.getyPos() + anObject.getHeight() && yPos+getHeight() > anObject.getyPos()
				&& xPos + getWidth() > anObject.getxPos() && xPos < anObject.getxPos() + anObject.getWidth()) {
			return true;
		}
		return false;
	}

	public void drawOnCanvas(Canvas c) {
		if (isVisible()) {
			c.drawRect((float)getxPos(), (float)getyPos(), (float)getxPos() + getWidth(), (float)getyPos() + getHeight(), null);
		}
	}
}
