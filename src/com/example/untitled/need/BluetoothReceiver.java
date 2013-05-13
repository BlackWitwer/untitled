package com.example.untitled.need;

import android.bluetooth.BluetoothSocket;

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

	private InputStream input;
	private Thread th;
	private boolean running;
	private Controller ctrl;
	private int id;

	public BluetoothReceiver(BluetoothSocket aSocket, Controller aController, int aId) {
		try {
			this.id = aId;
			this.input = aSocket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.running = true;
		this.ctrl = aController;
		start();
	}

	public void start() {
		th = new Thread(this);
		th.start();
	}

	public void close() {
		th.stop();
		this.running = false;
		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(running) {
			try {
				int tmp = input.read();
				ctrl.receiveInput(tmp, id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
