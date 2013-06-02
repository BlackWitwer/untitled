package com.untitled.activities;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import com.example.untitled.R;
import com.untitled.need.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 21.05.13
 * Time: 22:52
 */
public class ArcherController extends Activity {

	private SurfaceHolder holder;
	private Point p1;
	private Point p2;
	private Paint paint;
	private int pointerID = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Controller.getController().setActivity(this);
		setContentView(R.layout.controller2);

		p1 = new Point();
		p2 = new Point();
		paint = new Paint();
		paint.setColor(Color.WHITE);

		initMultitouch();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Controller.getController().controllerClosed();
	}

	private SurfaceHolder getCtrlHolder() {
		if (holder == null) {
			holder = ((SurfaceView) findViewById(R.id.controllerSurface)).getHolder();
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

	public void initMultitouch() {
		findViewById(R.id.controllerSurface).setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_POINTER_1_DOWN && pointerID == -1) {
					pointerID = motionEvent.getPointerId(motionEvent.getActionIndex());
				} else if (motionEvent.getAction() == MotionEvent.ACTION_POINTER_1_UP) {
					pointerID = -1;
				}

				p1.set((int)motionEvent.getX(), (int)motionEvent.getY());
				p2.set((int)motionEvent.getX(motionEvent.findPointerIndex(pointerID)), (int)motionEvent.getY(motionEvent.findPointerIndex(pointerID)));

				drawArrow();
				return true;
			}
		});
	}

	public void drawArrow() {
		Canvas canvas = null;
		try {
			canvas = getCtrlHolder().lockCanvas();
			synchronized (getCtrlHolder()) {
				if (canvas != null) {
					canvas.drawColor(Color.BLACK);
					canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
				}
			}
		} finally {
			if (canvas != null) {
				getCtrlHolder().unlockCanvasAndPost(canvas);
			}
		}
	}

	public static String actionToString(int action) {
		switch (action) {

			case MotionEvent.ACTION_DOWN: return "Down";
			case MotionEvent.ACTION_MOVE: return "Move";
			case MotionEvent.ACTION_POINTER_DOWN: return "Pointer Down";
			case MotionEvent.ACTION_UP: return "Up";
			case MotionEvent.ACTION_POINTER_UP: return "Pointer Up";
			case MotionEvent.ACTION_OUTSIDE: return "Outside";
			case MotionEvent.ACTION_CANCEL: return "Cancel";
		}
		return "";
	}
}
