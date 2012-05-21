package com.exia.android;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.classes.projet.Livraison;
import com.classes.projet.Tournee;
import com.exia.maps.CustomItemizedOverlay;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

/**
 * Maps Activité permetant d'afficher la map
 * 
 * @author Benoit
 *
 */
/**
 * @author Benoit
 * 
 */
public class MapsActivity extends MapActivity {

	private MapView mv = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.android.maps.MapActivity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps);

		mv = (MapView) findViewById(R.id.mapView);

		drawAddressesToLocation(Tournee.getInstance().getListeLivraison());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.android.maps.MapActivity#isRouteDisplayed()
	 */
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	/**
	 * Affiche les différentes livraisons sur la map
	 * 
	 * @param livraisons
	 *            La liste des livraisons
	 */
	private void drawAddressesToLocation(ArrayList<Livraison> livraisons) {

		List<Overlay> mapOverlays = mv.getOverlays();
		mapOverlays.removeAll(mapOverlays);

		// On charge les différents marqueurs
		Drawable drawableBlue = this.getResources().getDrawable(
				R.drawable.marker);
		Drawable drawableRed = this.getResources().getDrawable(
				R.drawable.marker2);
		Drawable drawableGreen = this.getResources().getDrawable(
				R.drawable.marker3);
		Drawable drawableYellow = this.getResources().getDrawable(
				R.drawable.marker4);
		Drawable drawablePurple = this.getResources().getDrawable(
				R.drawable.marker5);
		Drawable drawableGrey = this.getResources().getDrawable(
				R.drawable.marker6);
		CustomItemizedOverlay itemizedoverlay = new CustomItemizedOverlay(
				drawableBlue, this);

		drawableRed.setBounds(0 - drawableRed.getIntrinsicWidth() / 2,
				0 - drawableRed.getIntrinsicHeight(),
				drawableRed.getIntrinsicWidth() / 2, 0);
		drawableGreen.setBounds(0 - drawableGreen.getIntrinsicWidth() / 2,
				0 - drawableGreen.getIntrinsicHeight(),
				drawableGreen.getIntrinsicWidth() / 2, 0);
		drawableYellow.setBounds(0 - drawableYellow.getIntrinsicWidth() / 2,
				0 - drawableYellow.getIntrinsicHeight(),
				drawableYellow.getIntrinsicWidth() / 2, 0);
		drawablePurple.setBounds(0 - drawablePurple.getIntrinsicWidth() / 2,
				0 - drawablePurple.getIntrinsicHeight(),
				drawablePurple.getIntrinsicWidth() / 2, 0);
		drawableGrey.setBounds(0 - drawableGrey.getIntrinsicWidth() / 2,
				0 - drawableGrey.getIntrinsicHeight(),
				drawableGrey.getIntrinsicWidth() / 2, 0);

		int latitude;
		int longitude;

		// Pour chaque livraisons on récupére les coordonnées
		for (int i = 0; i < livraisons.size(); i++) {
			if (livraisons.get(i).getDestinataire() == null) {
				latitude = (int) (livraisons.get(i).getExpediteur()
						.getCoordGPS().getLatitude() * 1000000);
				longitude = (int) (livraisons.get(i).getExpediteur()
						.getCoordGPS().getLongitude() * 1000000);
			} else {
				latitude = (int) (livraisons.get(i).getDestinataire()
						.getCoordGPS().getLatitude() * 1000000);
				longitude = (int) (livraisons.get(i).getDestinataire()
						.getCoordGPS().getLongitude() * 1000000);
			}

			// On charge le point à afficher sur la map
			GeoPoint point = new GeoPoint(latitude, longitude);

			// Et on ajout le point à la liste
			OverlayItem overlayitem = new OverlayItem(point,
					String.valueOf(i + 1), "");
			itemizedoverlay.addOverlay(overlayitem);

			// On modifie le marqueur pas défaut dans le cas d'une récupération
			// de colis, ou de livraisons déjà effectuées
			if (livraisons.get(i).getStatuts() == 4) {
				overlayitem.setMarker(addTextToMaker(drawableYellow,
						overlayitem.getTitle()));
			} else if (livraisons.get(i).getStatuts() > 0) {

				overlayitem.setMarker(addTextToMaker(drawableRed,
						overlayitem.getTitle()));
			} else if (livraisons.get(i).getDestinataire() == null) {

				overlayitem.setMarker(addTextToMaker(drawableGreen,
						overlayitem.getTitle()));
			} else {

				overlayitem.setMarker(addTextToMaker(drawableBlue,
						overlayitem.getTitle()));
			}
		}

		GeoPoint point = new GeoPoint(
				(int) (Utils.getLocationGPS(this)[0] * 1000000),
				(int) (Utils.getLocationGPS(this)[1] * 1000000));

		OverlayItem overlayitem = new OverlayItem(point, "C", "");

		overlayitem.setMarker(addTextToMaker(drawablePurple,
				overlayitem.getTitle()));

		itemizedoverlay.addOverlay(overlayitem);

		double latRepository = 49.49634;
		double lonRepository = 0.10849;

		point = new GeoPoint((int) (latRepository * 1000000),
				(int) (lonRepository * 1000000));

		overlayitem = new OverlayItem(point, "D", "");

		overlayitem.setMarker(addTextToMaker(drawableGrey,
				overlayitem.getTitle()));

		itemizedoverlay.addOverlay(overlayitem);

		mapOverlays.add(itemizedoverlay);
		MapController controller = mv.getController();
		// On défini le zoom et le centre de la map afin d'afficher tous les
		// points
		controller.zoomToSpan(itemizedoverlay.getLatSpanE6(),
				itemizedoverlay.getLonSpanE6());
		controller.setCenter(itemizedoverlay.getCenter());

		mv.invalidate();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 1) {
			if (resultCode == 1) {
				drawAddressesToLocation(Tournee.getInstance()
						.getListeLivraison());
				mv.invalidate();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

	/**
	 * 
	 * Permet d'ajouter le text au marquer
	 * 
	 * @param d
	 *            L'image de base du marqueur
	 * @param text
	 *            Le text à afficher
	 * @return Le nouveau marqueur
	 */
	public Drawable addTextToMaker(Drawable d, String text) {
		Bitmap bitmap = ((BitmapDrawable) d).getBitmap().copy(
				Bitmap.Config.ARGB_8888, true);
		Canvas c = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setTextSize(50);
		paint.setARGB(255, 255, 255, 255);
		c.drawText(text, bitmap.getWidth() / 2, 62, paint);
		Drawable ledrawable = new BitmapDrawable(getResources(), bitmap);
		ledrawable.setBounds(0 - ledrawable.getIntrinsicWidth() / 2,
				0 - ledrawable.getIntrinsicHeight(),
				ledrawable.getIntrinsicWidth() / 2, 0);
		return ledrawable;
	}

}
