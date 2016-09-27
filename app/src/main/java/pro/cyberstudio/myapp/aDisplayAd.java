package pro.cyberstudio.myapp;

import android.content.Context;
import android.view.View;

/**
 * @author Jeff
 *         File:    iDisplayAd
 *         Created: 9/15/2016 @ 9:07 PM
 *         Project: Test1
 */

abstract class aDisplayAd {

	static boolean isPAID;

	abstract void placeAd();

	abstract String getBannerName();

	abstract boolean isFree();

	abstract boolean isPaid();
}
