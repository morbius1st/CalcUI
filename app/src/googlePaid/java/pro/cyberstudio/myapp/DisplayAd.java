package pro.cyberstudio.myapp;

import android.content.Context;
import android.view.View;

/**
 * @author Jeff
 *         File:    DisplayAd
 *         Created: 9/11/2016 @ 8:25 PM
 *         Project: Test1
 *
 *         this is an empty routine for a paid app where there will
 *         not be a banner - this class will not place an ad
 *
 */

class DisplayAd extends aDisplayAd {



	DisplayAd(Context ctx, DisplayInformation di, View vPort, View vLand) {

		isPAID = true;
	}

	void placeAd() {}

	String getBannerName() {
		return "paid app";
	}

	boolean isFree() { return !isPAID;}

	boolean isPaid() { return isPAID; }

}
