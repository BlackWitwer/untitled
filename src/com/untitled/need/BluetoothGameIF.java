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

	public void inputPlayer(int aInput, Device aDevice);

	public int getMinDeviceCount();

	public int getMaxDeviceCount();

	public void startGame();

	public void stopGame();

	public void pauseGame();

	public void newDevice(Device aDevice);

	public void disconnectDevice(Device aDevice);
}
