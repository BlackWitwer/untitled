package com.example.untitled.toDelete;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.example.untitled.need.BluetoothConnector;
import com.example.untitled.need.MyActivity;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 10.05.13
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */
public class ConnectThread extends Thread {
	private final BluetoothSocket mmSocket;
	private final BluetoothDevice mmDevice;
	private MyActivity activity;

	public ConnectThread(BluetoothDevice device, MyActivity aMyActivity) {
		this.activity = aMyActivity;
		// Use a temporary object that is later assigned to mmSocket,
		// because mmSocket is final.
		BluetoothSocket tmp = null;
		mmDevice = device;

		// Get a BluetoothSocket to connect with the given BluetoothDevice.
		try {
			// MY_UUID is the app’s UUID string, also used by the server code.
			tmp = device.createRfcommSocketToServiceRecord(MyActivity.MY_UUID);
		} catch (IOException e) { }
		mmSocket = tmp;
	}

	public void run() {
		// Cancel discovery because it will slow down the connection.
		BluetoothConnector.getInstance().getAdapter().cancelDiscovery();

		try {
			// Connect the device through the socket. This will block
			// until it succeeds or throws an exception.
			mmSocket.connect();
		} catch (IOException connectException) {
			// Unable to connect; close the socket and get out.
			try {
				mmSocket.close();
			} catch (IOException closeException) { }
			return;
		}

		// Do work to manage the connection (in a separate thread).
		manageConnectedSocket(mmSocket);
	}

	private void manageConnectedSocket(BluetoothSocket mmSocket) {
//		activity.setSocket(mmSocket);
	}

	/** Will cancel an in-progress connection, and close the socket. */
	public void cancel() {
		try {
			mmSocket.close();
		} catch (IOException e) { }
	}
}
