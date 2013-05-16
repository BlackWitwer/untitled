package com.example.untitled.need;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.untitled.R;

import java.util.ArrayList;
import java.util.UUID;

public class MyActivity extends Activity {

	public static final UUID MY_UUID1 = UUID.fromString("8B449A87-E4BC-4036-89AD-9E8978AE723D");
	public static final UUID MY_UUID2 = UUID.fromString("0e950aa0-be3c-11e2-9e96-0800200c9a66");

	private Controller ctrl;

	private DrawView draw;

	private SurfaceHolder holder;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		setContentView(R.layout.game);
		setContentView(R.layout.main);
		if (!BluetoothConnector.getInstance().getAdapter().isEnabled()) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, 1);
		}

		ctrl = new Controller(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ctrl.close();
	}

	public void changeContentView(int aID) {
		setContentView(aID);
		if (aID == R.layout.device_selector) {
			initDeviceSelector();
		}
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

				ctrl.createBluetoothConnection(item);
				changeContentView(R.layout.controller);
				adapter.notifyDataSetChanged();
//				view.setAlpha(1);
			}
		});
	}

	public void reloadPairedDevices(View view) {
		initDeviceSelector();
	}

	public void startGame(View view) {
		ctrl.startGame();
	}

	public void startGame2(View view) {
		ctrl.startGame2();
	}

	public void startController(View view) {
		ctrl.startController();
	}

	public void write(String aValue) {
		((TextView) findViewById(R.id.textTest)).setText(aValue);
	}

	public void onClickButtonLeft(View view) {
		ctrl.sendValue(1);
	}

	public void onClickButtonRight(View view) {
		ctrl.sendValue(2);
	}

	public void drawBitmap(Bitmap aBitmap) {
		getDrawView().drawBitmap(aBitmap);
		getDrawView().invalidate();
	}

	public int getDrawViewHeight() {
//		return getDrawView().getHeight();
		return findViewById(R.id.surfaceView).getHeight();
	}

	public int getDrawViewWidth() {
//		return getDrawView().getWidth();
		return findViewById(R.id.surfaceView).getWidth();
	}

	public DrawView getDrawView() {
		if (draw == null) {
//			draw = ((DrawView) findViewById(R.id.drawView));
		}
		return draw;
	}

	public SurfaceHolder getHolder() {
		if (holder == null) {
			holder = ((SurfaceView) findViewById(R.id.surfaceView)).getHolder();
			holder.addCallback(new SurfaceHolder.Callback() {
				@Override
				public void surfaceCreated(SurfaceHolder surfaceHolder) {
					//To change body of implemented methods use File | Settings | File Templates.
				}

				@Override
				public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
					//To change body of implemented methods use File | Settings | File Templates.
				}

				@Override
				public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
					//To change body of implemented methods use File | Settings | File Templates.
				}
			});
		}
		return holder;
	}
}
