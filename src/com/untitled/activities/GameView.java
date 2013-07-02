package com.untitled.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import com.example.untitled.R;
import com.untitled.need.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 21.05.13
 * Time: 22:52
 */
public class GameView extends Activity {

	private SurfaceHolder holder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Controller.getController().setActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.game);
		while( findViewById(R.id.surfaceView) == null) {};
		Controller.getController().startGame();
	}



	@Override
	protected void onDestroy() {
		super.onDestroy();
		Controller.getController().gameClosed();
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

	public int getDrawViewHeight() {
		return findViewById(R.id.surfaceView).getHeight();
	}

	public int getDrawViewWidth() {
		return findViewById(R.id.surfaceView).getWidth();
	}
}
