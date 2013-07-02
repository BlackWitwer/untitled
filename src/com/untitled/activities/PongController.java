package com.untitled.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.example.untitled.R;
import com.untitled.need.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 21.05.13
 * Time: 22:51
 */
public class PongController extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Controller.getController().setActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.pong_controller);
		init();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Controller.getController().controllerClosed();
	}

	public void init() {
		(findViewById(R.id.buttonLeft)).setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
					Controller.getController().sendValue(0);
				} else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					Controller.getController().sendValue(1);
				}
				return false;
			}
		});
		(findViewById(R.id.buttonRight)).setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
					Controller.getController().sendValue(0);
				} else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					Controller.getController().sendValue(2);
				}
				return false;
			}
		});
	}

	public void onClickButtonLeft(View view) {
//		Controller.getController().sendValue(1);
	}

	public void onClickButtonRight(View view) {
//		Controller.getController().sendValue(2);
	}
}
