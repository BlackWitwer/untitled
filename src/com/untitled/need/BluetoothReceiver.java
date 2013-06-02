package com.untitled.need;

import android.bluetooth.BluetoothSocket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 12.05.13
 * Time: 18:23
 * To change this template use File | Settings | File Templates.
 */
public class BluetoothReceiver implements Runnable {

	private DataInputStream input;
	private Thread th;
	private boolean running;
	private Device device;

	public BluetoothReceiver(BluetoothSocket aSocket, Device aDevice) {
		try {
			this.input = new DataInputStream(aSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.running = true;
		this.device = aDevice;
		start();
	}

	public void start() {
		th = new Thread(this);
		th.start();
	}

	public void close() throws  IOException{
		this.running = false;
		input.close();
	}

	@Override
	public void run() {
		while(running) {
			try {
				int tmp = input.readInt();
				device.receiveInput(tmp);
			} catch (IOException e) {
				e.printStackTrace();
				device.disconnected();
			}
		}
	}

}
