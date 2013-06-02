package com.untitled.archer;

import android.graphics.Canvas;
import com.untitled.need.BluetoothGameIF;
import com.untitled.need.Controller;
import com.untitled.need.Device;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 21.05.13
 * Time: 16:53
 */
public class ArcherMain implements Runnable, BluetoothGameIF {

	private boolean isRunning;
	private Thread thread;
	private int sleepTime = 20;

	private Player player1;
	private Player player2;
	private boolean playerTurn;

	private double gravity = 0.1;

	private Controller ctrl;

	public ArcherMain(Controller aController) {
		this.ctrl = aController;
		initGame();
		start();
	}

	private void initGame() {
		player1 = new Player(getCtrl().getWidth()/4, 10, 100, 100);
		player2 = new Player(getCtrl().getWidth()/4*3, 10, 100, 100);
		playerTurn = (int)Math.random()*2 == 1;
	}

	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		long time;
		while (isRunning) {
			time = System.currentTimeMillis();

			if (getPlayerOnTurn().getShot().isVisible()) {
				getPlayerOnTurn().getShot().decVerSpeed(gravity);
				getPlayerOnTurn().getShot().move();
			}

			if (getPlayerOnTurn().getShot().checkCollision(getPlayerNotOnTurn().getShot())) {
				getPlayerOnTurn().resetShot();
				changePlayerTurn();
			} else if (getPlayerOnTurn().getShot().getyPos() > ctrl.getHeight() || getPlayerOnTurn().getShot().getxPos() > ctrl.getWidth()
					|| getPlayerOnTurn().getShot().getxPos() < 0) {
				getPlayerOnTurn().resetShot();
				changePlayerTurn();
			}

			try {
				Thread.sleep((System.currentTimeMillis()-time) > sleepTime ? 1 : sleepTime-(System.currentTimeMillis()-time));
			} catch (InterruptedException ex) {
			}
		}
	}

	@Override
	public String getName() {
		return "Archer";
	}

	@Override
	public void onDraw(Canvas c) {
		player1.drawOnCanvas(c);
		player2.drawOnCanvas(c);
		player1.getShot().drawOnCanvas(c);
		player2.getShot().drawOnCanvas(c);
	}

	@Override
	public void inputPlayer(int aInput, Device aDevice) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public int getMinDeviceCount() {
		return 2;
	}

	@Override
	public int getMaxDeviceCount() {
		return 2;
	}

	@Override
	public void startGame() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void stopGame() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void pauseGame() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void newDevice(Device aDevice) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void disconnectDevice(Device aDevice) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	public Controller getCtrl() {
		return ctrl;
	}

	public Player getPlayerOnTurn() {
		return playerTurn ? player1 : player2;
	}

	public Player getPlayerNotOnTurn() {
		return playerTurn ? player2 : player1;
	}

	public void changePlayerTurn() {
		playerTurn = !playerTurn;
	}
}
