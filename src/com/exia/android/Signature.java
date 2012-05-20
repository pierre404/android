package com.exia.android;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

// classe comprennant les différantes fonctions et methodes permettant la signature du client

public class Signature extends SurfaceView implements SurfaceHolder.Callback {

	float oldcx = 0;
	float oldcy = 0;
	private Bitmap buffer;
	private static Canvas surface;
	private static Paint paint;
	SurfaceHolder holder;

	// constructeur 
	public Signature(Context context) {

		super(context);
		holder = getHolder();
		holder.addCallback(this);

	}
	// constructeur
	public Signature(Context context, AttributeSet attrs) {
		super(context, attrs);
		holder = getHolder();
		holder.addCallback(this);

	}
	// constructeur
	public Signature(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		holder = getHolder();
		holder.addCallback(this);

	}

	// Permet le tracé de la signature quand l'utilisateur agit sur l'écran. 
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float cx = event.getX();
		float cy = event.getY();

		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			break;

		case MotionEvent.ACTION_MOVE:

			paint.setStrokeWidth(5);
			paint.setColor(0xff000000);
			surface.drawLine(cx, cy, oldcx, oldcy, paint);
			break;

		}

		oldcx = cx;
		oldcy = cy;

		this.invalidate();
		return true;
	}

	// permet de signaler que la surface a été redessiné
	@Override
	public void invalidate() {

		if (holder != null) {
			Canvas c = holder.lockCanvas();
			if (c != null) {

				c.drawBitmap(buffer, 0, 0, null);
				holder.unlockCanvasAndPost(c);

			}
		}
	}

	// création de l'espace et la surface ou la signature va être effectuée
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		buffer = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		surface = new Canvas(buffer);
		paint = new Paint();
		paint.setColor(0xffffffff);
		surface.drawPaint(paint);
	}
	
	// création de l'espace et la surface ou la signature va être effectuée
	public void surfaceCreated(SurfaceHolder holder) {
		buffer = Bitmap.createBitmap(this.getWidth(), this.getHeight(),
				Config.ARGB_8888);
		surface = new Canvas(buffer);
		paint = new Paint();
		paint.setColor(0xffffffff);
		surface.drawPaint(paint);
		this.invalidate();

	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}
	
	// permet de réinisialiser la surface de dessin de la signature (permet d'éffacer la signature)
	public void resetSurface() {
		paint.setColor(0xffffffff);
		surface.drawPaint(paint);
		this.invalidate();
	}
	
	
	// permet de sauvegarder la signature
	public void saveBitmap(Context context, String numColis) {
		
		     File myDir= new File("/sdcard/signature");
		     myDir.mkdirs();
		     String fname = "sign-"+ numColis +".PNG";
		     File file = new File (myDir, fname);
		     if (file.exists ()) file.delete ();
		    
		     try {
		    	 
		            FileOutputStream out = new FileOutputStream(file);
		            buffer.compress(Bitmap.CompressFormat.PNG, 100, out);
		            out.flush();
		            out.close();
		            
		     } catch (Exception e) {
		            e.printStackTrace();
		     }
		     
		     resetSurface();
 
    }

	
}