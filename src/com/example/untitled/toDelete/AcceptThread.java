package com.example.untitled.toDelete;

import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import com.example.untitled.need.BluetoothConnector;
import com.example.untitled.need.MyActivity;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 10.05.13
 * Time: 22:35
 * To change this template use File | Settings | File Templates.
 */
public class AcceptThread extends Thread {
	private final BluetoothServerSocket mmServerSocket;

	private static final String NAME = "Name";
	private MyActivity activity;

	public AcceptThread(MyActivity aActivity) {
		this.activity = aActivity;
		// Use a temporary object that is later assigned to mmServerSocket,
		// because mmServerSocket is final.
		BluetoothServerSocket tmp = null;
		try {
			// MY_UUID is the app’s UUID string, also used by the client code.
			tmp = BluetoothConnector.getInstance().getAdapter().listenUsingRfcommWithServiceRecord(NAME, MyActivity.MY_UUID1);
		} catch (IOException e) { }
		mmServerSocket = tmp;
	}

	public void run() {
		BluetoothSocket socket = null;
		// Keep listening until exception occurs or a socket is returned.
		while (true) {
			try {
				socket = mmServerSocket.accept();
				// if a connection was accepted
				if (socket != null) {
					// Do work to manage the connection (in a separate thread).
					manageConnectedSocket(socket);
					mmServerSocket.close();
					break;
				}
			} catch (IOException e) {
				break;
			}
		}
	}

	private void manageConnectedSocket(BluetoothSocket socket) {
//		activity.setSocket(socket);
	}

	/** Will cancel the listening socket, and cause the thread to finish. */
	public void cancel() {
		try {
			mmServerSocket.close();
		} catch (IOException e) { }
	}
}
