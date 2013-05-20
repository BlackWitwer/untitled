package com.untitled.need;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 13.05.13
 * Time: 20:33
 */
public class Pong implements Runnable, BluetoothGameIF {

	public static final int CONTROLLER_LEFT = 1;
	public static final int CONTROLLER_RIGHT = 2;
	public static final int CONTROLLER_NONE = 0;

	private Controller ctrl;
	private Thread thread;
	private boolean running;
	private final int sleepTime = 20;

	private Paint paint = new Paint();

	private int speedPong;
	private int heightPong;
	private int widthPong;
	private int horPart;
	private int verPart;
	private double xPos;
	private double yPos;

	private int speed;
	private int height;
	private int width;

	private int inputPlayer1;
	private int p1PosY;
	private int p1Points = 0;

	private int inputPlayer2;
	private int p2PosY;
	private int p2Points = 0;

	private boolean botActive = false;

	public Pong(Controller aCtrl) {
		this.ctrl = aCtrl;
		init();
		start();
	}

	private void init() {
		speed = 7;
		height = 100;
		width = 20;
		p1PosY = ctrl.getHeight()/2 - height/2;
		p2PosY = ctrl.getHeight()/2 - height/2;
		speedPong = 16;
		heightPong = 10;
		widthPong = 10;
		horPart = (int)Math.pow(-1, (int)((Math.random()*4)+1));
		verPart = (int)Math.pow(-1, (int)((Math.random()*4)+1));
		xPos = ctrl.getWidth()/2;
		yPos = ctrl.getHeight()/2;
		inputPlayer1 = CONTROLLER_NONE;
		inputPlayer2 = CONTROLLER_NONE;

		initDrawZeugs();
	}

	public void start() {
		thread = new Thread(this);
		running = true;
		thread.start();
	}

	@Override
	public void run() {
		long time;
		long timenow;
		while (running) {
			time = System.currentTimeMillis();

			xPos += calcHorSpeed();
			yPos += calcVerSpeed();

			if (p1PosY < 0 && inputPlayer1 == 2 || p1PosY+height > ctrl.getHeight() && inputPlayer1 == 1) {
				inputPlayer1 = CONTROLLER_NONE;
			}
			if (p2PosY < 0 && inputPlayer2 == 2 || p2PosY+height > ctrl.getHeight() && inputPlayer2 == 1) {
				inputPlayer2 = CONTROLLER_NONE;
			}

			if (inputPlayer1 == CONTROLLER_LEFT) {
				p1PosY += speed;
			} else if (inputPlayer1 == CONTROLLER_RIGHT) {
				p1PosY -= speed;
			}
			if (inputPlayer2 == CONTROLLER_LEFT) {
				p2PosY += speed;
			} else if (inputPlayer2 == CONTROLLER_RIGHT) {
				p2PosY -= speed;
			}

			if (botActive) {
				if (yPos > p2PosY+height/2) {
					inputPlayer2 = CONTROLLER_LEFT;
				} else {
					inputPlayer2 = CONTROLLER_RIGHT;
				}
			}

			if (yPos < p1PosY+height && yPos+heightPong > p1PosY && xPos+widthPong > 10 && xPos < 10+width) {
				double xDiff = xPos - (10+width);
				xPos = 10+width;
				yPos += xDiff / (Math.abs(horPart) / Math.abs(verPart));

				horPart = 1;
			} else if (yPos < p2PosY+height && yPos+heightPong > p2PosY && xPos+widthPong > ctrl.getWidth()-10-width && xPos < ctrl.getWidth()-10){
				horPart = -1;

				double xDiff = xPos - (ctrl.getWidth()-10-width);
				xPos = ctrl.getWidth()-10-width-widthPong;
				yPos += xDiff / (Math.abs(horPart) / Math.abs(verPart));
			} else if (xPos < 0) {
				init();
				p2Points++;
				ctrl.writeOnGameLabel(p1Points + " : " + p2Points);
			} else if (xPos+widthPong > ctrl.getWidth()) {
				init();
				p1Points++;
				ctrl.writeOnGameLabel(p1Points + " : " + p2Points);
			}

			if (yPos < 0) {
				verPart = 1;
			} else if (yPos+heightPong > ctrl.getHeight()) {
				verPart = -1;
			}

			try {
				timenow = System.currentTimeMillis();
				Thread.sleep((timenow-time) > sleepTime ? 1 : sleepTime-(timenow-time));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
//
//	public void draw() {
//		if (ctrl.getHeight() > 0 && ctrl.getWidth() > 0) {
//			bitmapCanvas = null;
//			try {
//				bitmapCanvas = ctrl.getHolder().lockCanvas();
//				synchronized (ctrl.getHolder()) {
//					if (bitmapCanvas != null) {
//
//					}
//				}
//			} finally {
//				if (bitmapCanvas != null) {
//					ctrl.getHolder().unlockCanvasAndPost(bitmapCanvas);
//				}
//			}
//		}
//	}

	public void initDrawZeugs() {
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
	}

	private double calcHorSpeed() {
		double ges = Math.abs(horPart) + Math.abs(verPart);
		return speedPong*horPart/ges;
	}

	private double calcVerSpeed() {
		double ges = Math.abs(horPart) + Math.abs(verPart);
		return speedPong*verPart/ges;
	}

	public void setBotActive(boolean botActive) {
		this.botActive = botActive;
	}

	@Override
	public String getName() {
		return "Pong";
	}

	@Override
	public void onDraw(Canvas c) {
		c.drawColor(Color.BLACK);
		c.drawRect(10, p1PosY, 10+width, p1PosY + height, paint);
		c.drawRect(ctrl.getWidth()-10-width, p2PosY, ctrl.getWidth()-10, p2PosY + height, paint);
		c.drawRect((int)xPos, (int)yPos, widthPong + (int)xPos, heightPong + (int)yPos, paint);
	}

	@Override
	public void inputPlayer1(int aInput) {
		this.inputPlayer1 = aInput;
	}

	@Override
	public void inputPlayer2(int aInput) {
		this.inputPlayer2 = aInput;
	}
}
