package com.untitled.need;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 10.05.13
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
public class BluetoothConnector {

	private static BluetoothConnector connector;

	private BluetoothAdapter adapter;
	private ArrayList<BluetoothDevice> pairedDevices;

	private BluetoothConnector() {
		adapter = BluetoothAdapter.getDefaultAdapter();
	}

	public static BluetoothConnector getInstance() {
		if (connector == null) {
			connector = new BluetoothConnector();
		}
		return connector;
	}

	public BluetoothAdapter getAdapter() {
		return adapter;
	}

	public void loadPairedDevices() {
		Set<BluetoothDevice> thePairedDevices = getAdapter().getBondedDevices();
		pairedDevices = new ArrayList<BluetoothDevice>();
		// wenn gepaarte Geräte existieren
		if (thePairedDevices.size() > 0) {
			// Durchgehen der gepaarten Geräte
			for (BluetoothDevice device : thePairedDevices) {
				// einem Array die Addresse und den Namen der Geräte hinzufügen
				pairedDevices.add(device);
			}
		}
	}

	public ArrayList<BluetoothDevice> getPairedDevices() {
		if (pairedDevices == null) {
			loadPairedDevices();
		}
		return pairedDevices;
	}

	public BluetoothSocket createConnection(BluetoothDevice aDevice) {
		BluetoothSocket theSocket = null;
		try {
			theSocket = aDevice.createRfcommSocketToServiceRecord(MyActivity.MY_UUID1);
//			getInstance().getAdapter().cancelDiscovery();
			theSocket.connect();
		} catch (IOException e) {
			try {
				theSocket.close();
				theSocket = null;
				theSocket = aDevice.createRfcommSocketToServiceRecord(MyActivity.MY_UUID2);
				theSocket.connect();
			} catch (IOException e1) {
				try {
					theSocket.close();
					return null;
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return theSocket;
	}

	public BluetoothSocket createServer (String aName, UUID aUUID) {
		BluetoothServerSocket mmServerSocket;
		BluetoothSocket socket = null;

		try {
			mmServerSocket = getInstance().getAdapter().listenUsingRfcommWithServiceRecord(aName, aUUID);
			socket = mmServerSocket.accept();
			if (socket != null) {
				mmServerSocket.close();
				return socket;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
