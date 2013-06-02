package com.untitled.need;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import com.example.untitled.R;
import com.untitled.pong.Pong;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 23.05.13
 * Time: 17:42
 */
public class GameController {

//	private static Controller ctrl;
//
//	public static final UUID[] MY_UUIDs = {
//			UUID.fromString("8B449A87-E4BC-4036-89AD-9E8978AE723D"),
//			UUID.fromString("0e950aa0-be3c-11e2-9e96-0800200c9a66"),
//			UUID.fromString("d74e78f0-c3b4-11e2-8b8b-0800200c9a66"),
//			UUID.fromString("f01a4800-c3b4-11e2-8b8b-0800200c9a66")};
//
//	/**
//	 * Speichert den Index der benutzten Ids Bitweise
//	 */
//	private static int usedIds = 0;
//	private Activity activity;
//	private List<BluetoothSocket> socketList;
//	private List<BluetoothReceiver> receiverList;
//	private OutputStream out;
//
//	private Thread graphicThread;
//	private boolean isRunning;
//	private Canvas canvas;
//
//	private int drawHeight;
//	private int drawWidth;
//
//	private AcceptThread acceptThread;
//	private BluetoothGameIF game;
//	private int deviceCount;
//
//	private GameController() {
//		init();
//	}
//
//	private void init() {
//		acceptThread = new AcceptThread();
//	}
//
//	private void initThread() {
//		graphicThread = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (isRunning) {
//					if (game != null) {
//						canvas = null;
//						try {
//							canvas = getHolder().lockCanvas();
//							synchronized (getHolder()) {
//								if (canvas != null) {
//									game.onDraw(canvas);
//								}
//							}
//						} finally {
//							if (canvas != null) {
//								getHolder().unlockCanvasAndPost(canvas);
//							}
//						}
//					}
//				}
//			}
//		});
//
//		isRunning = true;
//	}
//
//	private void startGraphicThread() {
//		initThread();
//		graphicThread.start();
//	}
//
//	public void startGame() {
//		activity.setContentView(R.layout.game);
//		createBluetoothServer();
//	}
//
//	public void startController() {
//		activity.setContentView(R.layout.device_selector);
//	}
//
//	public void createBluetoothServer() {
//		acceptThread.start();
//		acceptThread.setWaitForDevice(true);
//		receiver = new BluetoothReceiver(socket, this, 1);
//		game = new Pong(this);
//		((Pong)game).setBotActive(true);
//		startGraphicThread();
//	}
//
//	public void createBluetoothServer2() {
//		socket = BluetoothConnector.getInstance().createServer("Server1", Controller.MY_UUID1);
//		receiver = new BluetoothReceiver(socket, this, 1);
//		socket2 = BluetoothConnector.getInstance().createServer("Server2", Controller.MY_UUID2);
//		receiver2 = new BluetoothReceiver(socket2, this, 2);
//		game = new Pong(this);
//		startGraphicThread();
//	}
//
//	public void receiveInput(final int aInput, int aId) {
//		if (game != null) {
//			if (aId == 1) {
//				game.inputPlayer1(aInput);
//			} else {
//				game.inputPlayer2(aInput);
//			}
//		}
//	}
//
//	public void close() {
//		isRunning = false;
//		for (BluetoothReceiver eachReceiver : receiverList) {
//			if (eachReceiver != null) {
//				eachReceiver.close();
//			}
//		}
//		try {
//			if (out != null) {
//				out.close();
//			}
//			for (BluetoothSocket eachSocket : socketList) {
//				if (eachSocket != null) {
//					eachSocket.close();
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public int getHeight() {
//		if (drawHeight <= 0) {
//			drawHeight = activity.getDrawViewHeight();
//		}
//		return drawHeight;
//	}
//
//	public int getWidth() {
//		if (drawWidth <= 0) {
//			drawWidth = activity.getDrawViewWidth();
//		}
//		return drawWidth;
//	}
//
//	public SurfaceHolder getHolder() {
//		return activity.getHolder();
//	}
//
//	public void setActivity(Activity aActivity) {
//		activity = aActivity;
//	}
//
//	public static Controller getController() {
//		if (ctrl == null) {
//			ctrl = new Controller();
//		}
//		return ctrl;
//	}
//
//	public void setIdUsed(UUID aId) {
//		for (int i = 0; i < MY_UUIDs.length; i++) {
//			if (aId.equals(MY_UUIDs[i])) {
//				usedIds |= (int)Math.pow(2, i);
//			}
//		}
//	}
//
//	public void setIdUnused(UUID aId) {
//		for (int i = 0; i < MY_UUIDs.length; i++) {
//			if (aId.equals(MY_UUIDs[i])) {
//				usedIds &= (int)Math.pow(2, MY_UUIDs.length)-1-(int)Math.pow(2, i);
//			}
//		}
//	}
//
//	public UUID getUnusedId() {
//		for (int i = 0; i < MY_UUIDs.length; i++) {
//			if ((usedIds & (int)Math.pow(2,i)) > 0) {
//				return MY_UUIDs[i];
//			}
//		}
//		return null;
//	}
//
//	public BluetoothGameIF getGame() {
//		return game;
//	}
//
//	public int getDeviceCount() {
//		return deviceCount;
//	}
//
//	public void addConnection(BluetoothSocket aSocket) {
//		socketList.add(aSocket);
//		receiverList.add(new BluetoothReceiver(aSocket, this, getDeviceCount()));
//		deviceCount++;
//	}
}
