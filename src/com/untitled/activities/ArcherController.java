package com.untitled.activities;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.*;
import com.example.untitled.R;
import com.untitled.archer.ArcherMain;
import com.untitled.need.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 21.05.13
 * Time: 22:52
 */
public class ArcherController extends Activity {

	private static final int lenghtFaktor = 60;

	private SurfaceHolder holder;
	private Point p1;
	private Point p2;
	private Paint paint;
	private int pointerID1 = -1;
	private int pointerID2 = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Controller.getController().setActivity(this);
		setContentView(R.layout.archer_controller);

		p1 = new Point();
		p2 = new Point();
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(10);

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
				switch (motionEvent.getActionMasked()) {
					case MotionEvent.ACTION_DOWN:
						p1.set((int)motionEvent.getX(), (int)motionEvent.getY());
						pointerID1 = motionEvent.getPointerId(motionEvent.getActionIndex());
						break;

					case MotionEvent.ACTION_UP:
						pointerID1 = -1;
						pointerID2 = -1;
						break;

					case MotionEvent.ACTION_POINTER_DOWN:
						if (pointerID2 < 0) {
							pointerID2 = motionEvent.getPointerId(motionEvent.getActionIndex());
							p2.set((int)motionEvent.getX(motionEvent.findPointerIndex(pointerID2)), (int)motionEvent.getY(motionEvent.findPointerIndex(pointerID2)));
						}
						break;

					case MotionEvent.ACTION_POINTER_UP:
						if (motionEvent.getPointerId(motionEvent.getActionIndex()) == pointerID2
								|| motionEvent.getPointerId(motionEvent.getActionIndex()) == pointerID1) {
							pointerID2 = -1;
							pointerID1 = -1;
						}
						break;

					case MotionEvent.ACTION_MOVE:
						if (pointerID1 >= 0 && pointerID2 >= 0) {
							p1.set((int)motionEvent.getX(pointerID1), (int)motionEvent.getY(pointerID1));
							p2.set((int)motionEvent.getX(pointerID2), (int)motionEvent.getY(pointerID2));
						}
						break;
				}
				drawArrow();
				sendPoints();
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
					if (pointerID2 >= 0 && pointerID1 >= 0) {
						paint.setColor(calcColor());
						if (getLenght() > ArcherMain.MAX_SPEED *lenghtFaktor) {
							canvas.drawLine(p1.x, p1.y, getNormX(), getNormY(), paint);
						} else {
							canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
						}
					}
				}
			}
		} finally {
			if (canvas != null) {
				getCtrlHolder().unlockCanvasAndPost(canvas);
			}
		}
	}

	private int getNormX() {
		double diffX = p2.x-p1.x;
		return (int)(p1.x + diffX/getLenght()*ArcherMain.MAX_SPEED *lenghtFaktor);
	}

	private int getNormY() {
		double diffY = p2.y-p1.y;
		return (int)(p1.y + diffY/getLenght()*ArcherMain.MAX_SPEED *lenghtFaktor);
	}

	private double getLenght() {
		double diffX = Math.abs(p2.x-p1.x);
		double diffY = Math.abs(p2.y - p1.y);
		return Math.sqrt(diffX * diffX + diffY * diffY);
	}

	private int calcColor() {
		double lenght = Math.min(getLenght(), ArcherMain.MAX_SPEED *lenghtFaktor);
		return Color.rgb((int)(255.0/(ArcherMain.MAX_SPEED *lenghtFaktor)*lenght), (int)(255.0/(ArcherMain.MAX_SPEED *lenghtFaktor)*(ArcherMain.MAX_SPEED *lenghtFaktor-lenght)), 0);
	}

	private void sendPoints() {
		Controller.getController().sendValue(ArcherMain.P1_X_MASK + p1.x);
		Controller.getController().sendValue(ArcherMain.P1_Y_MASK + p1.y);
		Controller.getController().sendValue(ArcherMain.P2_X_MASK + p2.x);
		Controller.getController().sendValue(ArcherMain.P2_Y_MASK + p2.y);
	}
}
