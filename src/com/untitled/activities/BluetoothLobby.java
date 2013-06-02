package com.untitled.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.untitled.R;
import com.untitled.need.*;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 21.05.13
 * Time: 22:53
 */
public class BluetoothLobby extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Controller.getController().setActivity(this);
		Controller.getController().startWaitForDevice();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.bluetooth_lobby);
		initDeviceSelector();
		((TextView)findViewById(R.id.lobbyText)).setText(Controller.getController().getGame().getName());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Controller.getController().lobbyClosed();
	}

	public void initDeviceSelector() {
		final ListView listview = (ListView) findViewById(R.id.devicesListView);
		ArrayList<Device> theDevices = Controller.getController().getDevices();
		final StableDeviceArrayAdapter adapter = new StableDeviceArrayAdapter(this,
				android.R.layout.simple_list_item_1, theDevices, R.layout.listitem);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
			                        int position, long id) {
			}
		});
	}

	public void startGame(View view) {
		Intent gameView = new Intent(getApplicationContext(), GameView.class);
		startActivity(gameView);
	}
}
