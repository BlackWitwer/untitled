package com.untitled.need;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.untitled.R;

import java.util.ArrayList;
import java.util.UUID;

public class MyActivity extends Activity {

//
//	private Controller ctrl;
//
//	private SurfaceHolder holder;
//	private SurfaceHolder ctrlHolder;


//	/**
//	 * Called when the activity is first created.
//	 */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		setContentView(R.layout.archer_controller);
//		if (!BluetoothConnector.getInstance().getAdapter().isEnabled()) {
//			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//			startActivityForResult(enableBtIntent, 1);
//		}
//
//		initMultitouch();
//		ctrl = new Controller(this);
//	}
//
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//		ctrl.close();
//	}

//	public void initMultitouch() {
//		findViewById(R.id.multitouchView).setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View view, MotionEvent motionEvent) {
//				((TextView)findViewById(R.id.multitouchView)).setText(motionEvent.getActionIndex()+"");
//				Canvas canvas = null;
//				try {
//					canvas = getCtrlHolder().lockCanvas();
//					synchronized (getHolder()) {
//						if (canvas != null) {
//
//						}
//					}
//				} finally {
//					if (canvas != null) {
//						getHolder().unlockCanvasAndPost(canvas);
//					}
//				}
//				return true;
//			}
//		});
//	}

//	public void changeContentView(int aID) {
//		setContentView(aID);
//		if (aID == R.layout.device_selector) {
//			initDeviceSelector();
//		}
//	}

//	public void initDeviceSelector() {
//		final ListView listview = (ListView) findViewById(R.id.deviceListView);
//		BluetoothConnector.getInstance().loadPairedDevices();
//		ArrayList<BluetoothDevice> theDevices = BluetoothConnector.getInstance().getPairedDevices();
//		final StableArrayAdapter adapter = new StableArrayAdapter(this,
//				android.R.layout.simple_list_item_1, theDevices, R.layout.listitem);
//		listview.setAdapter(adapter);
//
//		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, final View view,
//			                        int position, long id) {
//				final BluetoothDevice item = (BluetoothDevice) parent.getItemAtPosition(position);
//
//				ctrl.createBluetoothConnection(item);
//				changeContentView(R.layout.pong_controller);
//				adapter.notifyDataSetChanged();
//			}
//		});
//	}

//	public void reloadPairedDevices(View view) {
//		initDeviceSelector();
//	}

//	public void startGame(View view) {
//		ctrl.startGame();
//	}
//
//	public void startGame2(View view) {
//		ctrl.startGame2();
//	}
//
//	public void startController(View view) {
//		ctrl.startController();
//	}

//	public void onClickButtonLeft(View view) {
//		ctrl.sendValue(1);
//	}
//
//	public void onClickButtonRight(View view) {
//		ctrl.sendValue(2);
//	}

//	public int getDrawViewHeight() {
//		return findViewById(R.id.surfaceView).getHeight();
//	}
//
//	public int getDrawViewWidth() {
//		return findViewById(R.id.surfaceView).getWidth();
//	}

//	public SurfaceHolder getHolder() {
//		if (holder == null) {
//			holder = ((SurfaceView) findViewById(R.id.surfaceView)).getHolder();
//			holder.addCallback(new SurfaceHolder.Callback() {
//				@Override
//				public void surfaceCreated(SurfaceHolder surfaceHolder) {
//					//To change body of implemented methods use File | Settings | File Templates.
//				}
//
//				@Override
//				public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
//					//To change body of implemented methods use File | Settings | File Templates.
//				}
//
//				@Override
//				public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
//					//To change body of implemented methods use File | Settings | File Templates.
//				}
//			});
//		}
//		return holder;
//	}

//	private SurfaceHolder getCtrlHolder() {
//		if (ctrlHolder == null) {
//			ctrlHolder = ((SurfaceView) findViewById(R.id.controllerSurface)).getHolder();
//			ctrlHolder.addCallback(new SurfaceHolder.Callback() {
//				@Override
//				public void surfaceCreated(SurfaceHolder surfaceHolder) {
//					//To change body of implemented methods use File | Settings | File Templates.
//				}
//
//				@Override
//				public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
//					//To change body of implemented methods use File | Settings | File Templates.
//				}
//
//				@Override
//				public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
//					//To change body of implemented methods use File | Settings | File Templates.
//				}
//			});
//		}
//		return ctrlHolder;
//	}
}
