package com.classes.projet;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

/**
 * 
 * Classe contenant les coordonnées GPS
 * 
 * @author Benoit
 * 
 */
public class CoordGPS {
	double latitude;
	double longitude;

	/**
	 * @param address
	 *            L'adresse de la personne
	 * @param context
	 *            Le context de l'activité
	 */
	public CoordGPS(String address, Context context) {
		Geocoder gc = new Geocoder(context, Locale.FRANCE);
		List<Address> addresses;
		try {
			addresses = gc.getFromLocationName(address, 5);
			if (addresses.size() > 0) {
				this.latitude = addresses.get(0).getLatitude();
				this.longitude = addresses.get(0).getLongitude();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param latitude
	 *            La latitude
	 * @param longitude
	 *            La longitude
	 */
	public CoordGPS(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
