package com.classes.projet;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

public class CoordGPS {
	int latitude;
	int longitude;

	public CoordGPS(String address, Context context) {
		Geocoder gc = new Geocoder(context,
				Locale.FRANCE);
		List<Address> addresses;
		try {
			addresses = gc.getFromLocationName(address, 5);
			if (addresses.size() > 0) {
				this.latitude = (int) (addresses.get(0).getLatitude() * 1000000);
				this.longitude = (int) (addresses.get(0).getLongitude() * 1000000);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
}
