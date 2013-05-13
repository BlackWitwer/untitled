package com.example.untitled.need;

import android.R;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: Black
 * Date: 13.05.13
 * Time: 19:49
 * To change this template use File | Settings | File Templates.
 */
public class DrawView extends View {
	Bitmap bitmap;
	Canvas bitmapCanvas;
	boolean isInitialized;
	Paint paint = new Paint();

	public DrawView(Context context) {
		this(context, null);
	}

	public DrawView(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.buttonStyle);
	}

	public DrawView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setFocusable(true);
		setFocusableInTouchMode(true);
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);

		isInitialized = false;
	}

	private void init() {
		bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);

		bitmapCanvas = new Canvas();
		bitmapCanvas.setBitmap(bitmap);
		bitmapCanvas.drawColor(Color.BLACK);

		isInitialized = true;
	}

	public void drawBitmap(Bitmap aBitmap) {
		bitmapCanvas.drawBitmap(aBitmap, 0, 0, paint);
	}

	@Override
	public void onDraw(Canvas canvas) {
		if (!isInitialized)
			init();

		canvas.drawBitmap(bitmap, 0, 0, paint);
	}
}
