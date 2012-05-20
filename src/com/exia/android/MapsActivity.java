package com.exia.android;

import java.util.ArrayList;
import java.util.List;

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
		
		//On charge les différents marqueurs		
		Drawable drawableBlue = this.getResources().getDrawable(
				R.drawable.marker);
		Drawable drawableRed = this.getResources().getDrawable(
				R.drawable.marker2);
		Drawable drawableGreen = this.getResources().getDrawable(
				R.drawable.marker3);
		CustomItemizedOverlay itemizedoverlay = new CustomItemizedOverlay(
				drawableBlue, this);

		int latitude;
		int longitude;
		
		//Pour chaque livraisons on récupére les coordonnées
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
			
			//On charge le point à afficher sur la map
			GeoPoint point = new GeoPoint(latitude, longitude);

			//Et on ajout le point à la liste
			OverlayItem overlayitem = new OverlayItem(point,
					String.valueOf(i + 1), "");
			itemizedoverlay.addOverlay(overlayitem);

			//On modifie le marqueur pas défaut dans le cas d'une récupération de colis, ou de livraisons déjà effectuées
			if (livraisons.get(i).getCoordGPS() != null) {
				drawableRed.setBounds(0 - drawableRed.getIntrinsicWidth() / 2,
						0 - drawableRed.getIntrinsicHeight(),
						drawableRed.getIntrinsicWidth() / 2, 0);
				overlayitem.setMarker(drawableRed);
			} else if (livraisons.get(i).getDestinataire() == null) {
				drawableGreen.setBounds(
						0 - drawableRed.getIntrinsicWidth() / 2,
						0 - drawableRed.getIntrinsicHeight(),
						drawableRed.getIntrinsicWidth() / 2, 0);
				overlayitem.setMarker(drawableGreen);
			}
		}

		mapOverlays.add(itemizedoverlay);
		MapController controller = mv.getController();
		//On défini le zoom et le centre de la map afin d'afficher tous les points
		controller.zoomToSpan(itemizedoverlay.getLatSpanE6(),
				itemizedoverlay.getLonSpanE6());
		controller.setCenter(itemizedoverlay.getCenter());

		mv.invalidate();

	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}
