package com.untitled.need;

import android.bluetooth.BluetoothSocket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 23.05.13
 * Time: 17:51
 */
public class Device {

	public static interface OnInputListener {
		void onInput(int aInput, Device aDevice);
	}

	public static interface OnDisconnectListener {
		void onDisconnect(Device aDevice);
	}

	private BluetoothSocket socket;
	private BluetoothReceiver receiver;
	private DataOutputStream out;

	private ArrayList<OnInputListener> inputListener;
	private ArrayList<OnDisconnectListener> disconnectListener;

	public Device(BluetoothSocket aSocket) {
		this.socket = aSocket;
		receiver = new BluetoothReceiver(aSocket, this);
		inputListener = new ArrayList<OnInputListener>();
		disconnectListener = new ArrayList<OnDisconnectListener>();
		try {
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendValue (int aValue) throws IOException{
		out.writeInt(aValue);
		out.flush();
	}

	public void receiveInput(final int aInput) {
		for (OnInputListener eachListener : inputListener) {
			eachListener.onInput(aInput, this);
		}
	}

	public void disconnected () {
		for (OnDisconnectListener eachListener : disconnectListener) {
			eachListener.onDisconnect(this);
		}
	}

	public void addInputListener(OnInputListener aListener) {
		inputListener.add(aListener);
	}

	public void addOnDisconnectListener(OnDisconnectListener aListener) {
		disconnectListener.add(aListener);
	}

	public String getDeviceName() {
		return socket.getRemoteDevice().getName();
	}

	public String getDeviceAddress() {
		return socket.getRemoteDevice().getAddress();
	}

	public void close() throws IOException {
		if (out != null) out.close();
		if (receiver != null) receiver.close();
		if (socket != null) socket.close();
	}
}
