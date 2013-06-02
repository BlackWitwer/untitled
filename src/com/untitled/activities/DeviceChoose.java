package com.untitled.activities;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.untitled.R;
import com.untitled.need.BluetoothConnector;
import com.untitled.need.Controller;
import com.untitled.need.StableArrayAdapter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 21.05.13
 * Time: 22:53
 */
public class DeviceChoose extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Controller.getController().setActivity(this);
		setContentView(R.layout.device_selector);
	}

	public void initDeviceSelector() {
		final ListView listview = (ListView) findViewById(R.id.deviceListView);
		BluetoothConnector.getInstance().loadPairedDevices();
		ArrayList<BluetoothDevice> theDevices = BluetoothConnector.getInstance().getPairedDevices();
		final StableArrayAdapter adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, theDevices, R.layout.listitem);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
			                        int position, long id) {
				final BluetoothDevice item = (BluetoothDevice) parent.getItemAtPosition(position);
				Controller.getController().createBluetoothConnection(item);
				adapter.notifyDataSetChanged();
			}
		});
	}

	public void changeActivity(Class<? extends Activity> aClass) {
		Intent nextScreen = new Intent(getApplicationContext(), aClass);
		startActivity(nextScreen);
	}

	public void reloadPairedDevices(View view) {
		initDeviceSelector();
	}

}
