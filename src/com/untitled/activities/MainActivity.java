package com.untitled.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.example.untitled.R;
import com.untitled.need.BluetoothConnector;
import com.untitled.need.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 21.05.13
 * Time: 22:51
 */
public class MainActivity extends Activity {

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		if (!BluetoothConnector.getInstance().getAdapter().isEnabled()) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, 1);
		}
		Controller.getController().setActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void startGame(View view) {
		setContentView(R.layout.game_choose);
	}

	public void startController(View view) {
		Intent controllerScreen = new Intent(getApplicationContext(), DeviceChoose.class);
		startActivity(controllerScreen);
	}

	public void onClickPong(View view) {
		Controller.getController().setGame(Controller.CONTROLLER_PONG);
		Intent gameScreen = new Intent(getApplicationContext(), BluetoothLobby.class);
		startActivity(gameScreen);
	}

	public void onClickArcher(View view) {
		Controller.getController().setGame(Controller.CONTROLLER_ARCHER);
		Intent gameScreen = new Intent(getApplicationContext(), BluetoothLobby.class);
		startActivity(gameScreen);
	}
}
