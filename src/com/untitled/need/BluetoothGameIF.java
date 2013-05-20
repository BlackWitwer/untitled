package com.untitled.need;

import android.graphics.Canvas;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 19.05.13
 * Time: 13:54
 */
public interface BluetoothGameIF {

	public String getName();

	public void onDraw(Canvas c);

	public void inputPlayer1(int aInput);

	public void inputPlayer2(int aInput);
}
