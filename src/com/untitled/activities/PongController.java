package com.untitled.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
		setContentView(R.layout.controller);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Controller.getController().controllerClosed();
	}

	public void onClickButtonLeft(View view) {
		Controller.getController().sendValue(1);
	}

	public void onClickButtonRight(View view) {
		Controller.getController().sendValue(2);
	}
}
