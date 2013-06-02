package com.untitled.need;

import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 10.05.13
 * Time: 22:35
 *
 * Ist eine multiple UUID überhaupt notwendig? Oder reicht eine mit immer dem selben ServerSocket.
 */
public class AcceptThread extends Thread {
	private final BluetoothServerSocket mmServerSocket;

	private static final String NAME = "Name";
	private boolean waitForDevice;
	private boolean isRunning;

	public AcceptThread() {
		isRunning = true;
		waitForDevice = false;
		BluetoothServerSocket tmp = null;
		try {
			tmp = BluetoothConnector.getInstance().getAdapter().listenUsingRfcommWithServiceRecord(NAME, UUID.fromString("f01a4800-c3b4-11e2-8b8b-0800200c9a66"));
		} catch (IOException e) { }
		mmServerSocket = tmp;
	}

	public void run() {
		BluetoothSocket socket = null;
		while (isRunning) {
			if (Controller.getController().getGame() != null && Controller.getController().getGame().getMaxDeviceCount() > Controller.getController().getDeviceCount() && waitForDevice) {
				try {
					socket = mmServerSocket.accept();
					if (socket != null) {
						Controller.getController().addConnection(socket);
					}
				} catch (IOException e) {
					try {
						mmServerSocket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException ex) {

			}
		}
	}

	public void setWaitForDevice(boolean waitForDevice) {
		this.waitForDevice = waitForDevice;
	}

	/** Will cancel the listening socket, and cause the thread to finish. */
	public void cancel() {
		try {
			mmServerSocket.close();
			isRunning = false;
		} catch (IOException e) { }
	}
}
