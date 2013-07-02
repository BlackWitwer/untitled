package com.untitled.need;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import com.untitled.activities.*;
import com.untitled.archer.ArcherMain;
import com.untitled.pong.Pong;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 12.05.13
 * Time: 15:43
 */
public class Controller {

	public static final int CONTROLLER_PONG = 1;
	public static final int CONTROLLER_ARCHER = 2;

	private static Controller ctrl;

	private Activity activity;
	private ArrayList<Device> devices;

	private Thread graphicThread;
	private boolean isRunning;
	private Canvas canvas;

	private int drawHeight;
	private int drawWidth;

	private BluetoothGameIF game;
	private AcceptThread acceptThread;

	private Controller() {
		init();
	}

	private void init() {
		devices = new ArrayList<Device>();
		acceptThread = new AcceptThread();
		acceptThread.start();
	}

	private void initThread() {
		graphicThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (isRunning) {
					if (game != null && getHolder() != null) {
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

	public boolean startGame() {
		if (getDeviceCount() >= getGame().getMinDeviceCount()) {
			getGame().startGame();
			startGraphicThread();
			return true;
		}
		return false;
	}

	public void createBluetoothConnection(BluetoothDevice aDevice) {
		BluetoothSocket theSocket = BluetoothConnector.getInstance().createConnection(aDevice);
		if (theSocket != null) {
			addConnection(theSocket);
		} else {
			//TODO Fehlermeldung einfügen
		}
	}

	public void sendValue(int aValue) {
		try {
			for (Device eachDevice : devices) {
				eachDevice.sendValue(aValue);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		isRunning = false;
		for (Device device : devices) {
			try {
				device.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		devices.clear();
	}

	public int getHeight() {
		if (activity instanceof GameView) {
			if (drawHeight <= 0) {
				drawHeight = ((GameView)activity).getDrawViewHeight();
			}
			return drawHeight;
		}
		return -1;
	}

	public int getWidth() {
		if (activity instanceof  GameView) {
			if (drawWidth <= 0) {
				drawWidth = ((GameView)activity).getDrawViewWidth();
			}
			return drawWidth;
		}
		return -1;
	}

	public SurfaceHolder getHolder() {
		if (activity instanceof GameView) {
			return ((GameView)activity).getHolder();
		}
		return null;
	}

	public void setActivity(Activity aActivity) {
		activity = aActivity;
	}

	public static Controller getController() {
		if (ctrl == null) {
			ctrl = new Controller();
		}
		return ctrl;
	}

	public BluetoothGameIF getGame() {
		return game;
	}

	public int getDeviceCount() {
		return devices.size();
	}

	public void addConnection(BluetoothSocket aSocket) {
		Device theDevice = new Device(aSocket);
		theDevice.addOnDisconnectListener( new Device.OnDisconnectListener() {
			@Override
			public void onDisconnect(Device aDevice) {
				if (game != null) {
					game.disconnectDevice(aDevice);
				}
				try {
					aDevice.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				devices.remove(aDevice);
			}
		});
		theDevice.addInputListener(new Device.OnInputListener() {
			@Override
			public void onInput(int aInput, Device aDevice) {
				if (game != null) {
					game.inputPlayer(aInput, aDevice);
				}
				if (aInput/10 == 123456789) {
					changeController(aInput);
				}
			}
		});
		devices.add(theDevice);

		if (game != null) {
			game.newDevice(theDevice);
		}

		if (activity instanceof BluetoothLobby || activity instanceof GameView) {
			try {
				int value = 1234567890 + getGameId();
				theDevice.sendValue(value);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		updateLobby();
	}

	private void changeController(int aInput) {
		if (activity instanceof DeviceChoose) {
			switch(aInput-1234567890) {
				case CONTROLLER_PONG:
					((DeviceChoose)activity).changeActivity(PongController.class);
					break;

				case CONTROLLER_ARCHER:
					((DeviceChoose)activity).changeActivity(ArcherController.class);
					break;
			}
		}
	}

	public int getGameId() {
		if (game instanceof ArcherMain) {
			return CONTROLLER_ARCHER;
		}
		if (game instanceof Pong) {
			return CONTROLLER_PONG;
		}
		return 0;
	}

	public void startWaitForDevice() {
		acceptThread.setWaitForDevice(true);
	}

	public void stopWaitForDevice() {
		acceptThread.setWaitForDevice(false);
	}

	public ArrayList<Device> getDevices() {
		return devices;
	}

	public void gameClosed() {
		isRunning = false;
		updateLobby();
	}

	public void controllerClosed() {
		close();
	}

	public void lobbyClosed() {
		close();
		stopWaitForDevice();
	}

	private void updateLobby() {
		if (activity instanceof BluetoothLobby) {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					((BluetoothLobby)activity).initDeviceSelector();
				}
			});
		}
	}

	public void setGame(int aGameId) {
		switch (aGameId) {
			case CONTROLLER_PONG:
				game = new Pong(this);
				break;
			case CONTROLLER_ARCHER:
				game = new ArcherMain(this);
				break;
		}
	}

	public boolean isStartable() {
		return getDeviceCount() >= getGame().getMinDeviceCount();
	}
}
