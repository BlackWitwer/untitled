package com.untitled.archer;

import android.graphics.Canvas;
import android.graphics.Color;
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

	public static final int MAX_SPEED = 8;
	public static final int P1_X_MASK = 11000000;
	public static final int P1_Y_MASK = 12000000;
	public static final int P2_X_MASK = 21000000;
	public static final int P2_Y_MASK = 22000000;


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
		init();
	}

	private void init() {
		player1 = new Player(0, 10, 100, 100);
		player1.setColor(Color.RED);
		player2 = new Player(0, 10, 100, 100);
		player2.setColor(Color.BLUE);
	}

	private void initGame() {
		player1.setxPos(getCtrl().getWidth()/4);
		player2.setxPos(getCtrl().getWidth()/4*3);
		playerTurn = (int)Math.random()*2 == 1;
	}

	private void start() {
		initGame();
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
		int theMask = (aInput/1000000)*1000000;
		int theValue = aInput%1000000;
		if (aDevice != null && aDevice.equals(player1.getDevice())) {
			switch (theMask) {
				case P1_X_MASK:
					player1.getP1().set(theValue, player1.getP1().y);
					break;

				case P1_Y_MASK:
					player1.getP1().set(player1.getP1().x, theValue);
					break;

				case P2_X_MASK:
					player1.getP2().set(theValue, player1.getP2().y);
					break;

				case P2_Y_MASK:
					player1.getP2().set(player1.getP2().y, theValue);
					break;
			}
		} else if (aDevice != null && aDevice.equals(player2.getDevice())) {
			switch (theMask) {
				case P1_X_MASK:
					player2.getP1().set(theValue, player1.getP1().y);
					break;

				case P1_Y_MASK:
					player2.getP1().set(player1.getP1().x, theValue);
					break;

				case P2_X_MASK:
					player2.getP2().set(theValue, player1.getP2().y);
					break;

				case P2_Y_MASK:
					player2.getP2().set(player1.getP2().y, theValue);
					break;
			}
		}
	}

	@Override
	public int getMinDeviceCount() {
		return 1;
	}

	@Override
	public int getMaxDeviceCount() {
		return 2;
	}

	@Override
	public void startGame() {
		start();
	}

	@Override
	public void stopGame() {
	}

	@Override
	public void pauseGame() {
	}

	@Override
	public void newDevice(Device aDevice) {
		if (player1.getDevice() == null) {
			player1.setDevice(aDevice);
		} else if (player2.getDevice() == null) {
			player2.setDevice(aDevice);
		}
	}

	@Override
	public void disconnectDevice(Device aDevice) {
		if (aDevice != null && aDevice.equals(player1.getDevice())) {
			player1.setDevice(null);
		} else if (aDevice != null && aDevice.equals(player2.getDevice())) {
			player2.setDevice(null);
		}
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
