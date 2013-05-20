package com.untitled.need;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import com.example.untitled.R;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 12.05.13
 * Time: 15:43
 */
public class Controller {

	private MyActivity activity;
	private BluetoothSocket socket;
	private BluetoothSocket socket2;
	private BluetoothReceiver receiver;
	private BluetoothReceiver receiver2;
	private OutputStream out;

	private Thread graphicThread;
	private boolean isRunning;
	private Canvas canvas;

	private int drawHeight;
	private int drawWidth;

	private BluetoothGameIF game;

	public Controller(MyActivity aActivity) {
		this.activity = aActivity;
	}

	private void initThread() {
		graphicThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (isRunning) {
					if (game != null) {
						canvas = null;
						try {
							canvas = getHolder().lockCanvas();
							synchronized (getHolder()) {
								if (canvas != null) {
									game.onDraw(canvas);
								}
							}
						} finally {
							if (canvas != null) {
								getHolder().unlockCanvasAndPost(canvas);
							}
						}
					}
				}
			}
		});

		isRunning = true;
	}

	private void startGraphicThread() {
		initThread();
		graphicThread.start();
	}

	public void startGame() {
		activity.setContentView(R.layout.game);
		createBluetoothServer();
	}

	public void startGame2() {
		activity.setContentView(R.layout.game);
		createBluetoothServer2();
	}

	public void startController() {
		activity.setContentView(R.layout.device_selector);
	}

	public void createBluetoothConnection(BluetoothDevice aDevice) {
		socket = BluetoothConnector.getInstance().createConnection(aDevice);
	}

	public void createBluetoothServer() {
		socket = BluetoothConnector.getInstance().createServer("Server1", MyActivity.MY_UUID1);
		receiver = new BluetoothReceiver(socket, this, 1);
		game = new Pong(this);
		((Pong)game).setBotActive(true);
		startGraphicThread();
	}

	public void createBluetoothServer2() {
		socket = BluetoothConnector.getInstance().createServer("Server1", MyActivity.MY_UUID1);
		receiver = new BluetoothReceiver(socket, this, 1);
		socket2 = BluetoothConnector.getInstance().createServer("Server2", MyActivity.MY_UUID2);
		receiver2 = new BluetoothReceiver(socket2, this, 2);
		game = new Pong(this);
		startGraphicThread();
	}

	public void receiveInput(final int aInput, int aId) {
		if (game != null) {
			if (aId == 1) {
				game.inputPlayer1(aInput);
			} else {
				game.inputPlayer2(aInput);
			}
		}
	}

	public void writeOnGameLabel(final String aMessage) {
		activity.runOnUiThread(new Runnable() {
			public void run() {
				activity.write(aMessage);
			}
		});
	}

	public void sendValue(int aValue) {
		try {
			if (out == null) {
				out = socket.getOutputStream();
			}
			out.write(aValue);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		isRunning = false;
		if (receiver != null) {
			receiver.close();
		}
		if (receiver2 != null) {
			receiver2.close();
		}
		try {
			if (out != null) {
				out.close();
			}
			if (socket != null) {
				socket.close();
			}
			if (socket2 != null) {
				socket2.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getHeight() {
		if (drawHeight <= 0) {
			drawHeight = activity.getDrawViewHeight();
		}
		return drawHeight;
	}

	public int getWidth() {
		if (drawWidth <= 0) {
			drawWidth = activity.getDrawViewWidth();
		}
		return drawWidth;
	}

	public SurfaceHolder getHolder() {
		return activity.getHolder();
	}
}
