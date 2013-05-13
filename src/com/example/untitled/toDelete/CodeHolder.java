package com.example.untitled.toDelete;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 11.05.13
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
public class CodeHolder {
//	public static final UUID MY_UUID = UUID.fromString("8B449A87-E4BC-4036-89AD-9E8978AE723D");
//
//	private BluetoothSocket socket;
//
//	private AcceptThread serverThread;
//	private ConnectThread connectThread;
//
//	/**
//	 * Called when the activity is first created.
//	 */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		setContentView(R.layout.main);
//		if (!BluetoothConnector.getInstance().getAdapter().isEnabled()) {
//			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//			startActivityForResult(enableBtIntent, 1);
//		}
//	}
//
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//		if (serverThread != null) {
//			serverThread.stop();
//		}
//		if (connectThread != null) {
//			connectThread.stop();
//		}
//	}
//
//	public void onClick(View view) {
//		Button testButton = (Button) findViewById(R.id.button);
//		testButton.setText("Hallo");
//		TextView theTextView = (TextView) findViewById(R.id.textView);
//		theTextView.setText("");
//		ArrayList<BluetoothDevice> thePairedDevices = BluetoothConnector.getInstance().getPairedDevices();
//		for (BluetoothDevice eachDevice : thePairedDevices) {
//			theTextView.append(eachDevice.getName() + " : " + eachDevice.getAddress() + "\n");
//		}
//		logMessage("Start");
//	}
//
//	public void startServer(View view) {
//		serverThread = new AcceptThread(this);
//		serverThread.start();
//		logMessage("Server started");
//	}
//
//	public void connectClient(View view) {
//		connectThread = new ConnectThread(BluetoothConnector.getInstance().getPairedDevices().get(0), this);
//		connectThread.start();
//		logMessage("Client Started");
//	}
//
//	public void setSocket(BluetoothSocket aSocket) {
//		this.socket = aSocket;
//	}
//
//	public BluetoothSocket getSocket() {
//		return socket;
//	}
//
//	public void logMessage(String aMessage) {
//		TextView theTextView = (TextView) findViewById(R.id.textView);
//		theTextView.append(aMessage);
//	}
}
