package pro.cyberstudio.myapp;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

/**
 * @author Jeff
 *         File:    DisplayAdAlt
 *         Created: 8/20/2016 @ 3:47 PM
 *         Project: Test1
 */

class DisplayAd extends aDisplayAd {
	private DisplayInformation DI;
	private Context ctx;

	private View portView;
	private View landView = null;

	private String bannerName;

	// screen size constants
	private static final int HT_XLARGE_PORT = 900;
	private static final int HT_LARGE_PORT = 720;
	private static final int WT_XLARGE = 750;
	private static final int WT_LARGE = 475;

	private static final int HT_LARGE_LAND = 600;
	private static final int HT_FULL_BANNER = 60;

	private static final int WT_MINIMUM_LAND = 567;
	private static final int WT_MINIMUM_PORT = 328;

	private static final int WT_MIN_AD = 330;
	private static final int WT_MIN_SCREEN_LAND = 660;

	DisplayAd(Context ctx, DisplayInformation di, View vPort, View vLand) {
		DI = di;
		this.ctx = ctx;
		portView = vPort;
		landView = vLand;
		isPAID = false;
	}


	// process the request to place an ad
	// verify that it is possible and display the ad
	void placeAd() {

		AdSize bannerSize;

		// precess depending on screen orientation
		// portrait orientation, the view will be a LinearLayout
		if (DI.getRotation().isPortrait()) {

			// just an extra check - should never happen
			if (portView == null) return;

			// determine the correct banner size
			bannerSize = getBannerSizePort();

			// if the determined banner size is null (won't fit),
			// don't place the ad
			if (bannerSize == null) return;

			// everything ready, show the ad
			displayAd(((TableRow) portView), bannerSize, 25);

		} else {

			// extra check
			if (landView == null) return;

			// determine the banner size
			bannerSize = getBannerSizeLand();

			// if the banner will not fit, no banner - return
			if (bannerSize == null) return;

			// for landscape, we may need to make a minor adjustment
			// to the left table width to fit the banner
			adjustTableWidthLand();

			// everything ready, show the ad
			displayAd(((TableRow) landView), bannerSize, 20);
		}

	}

	String getBannerName() { return bannerName; }

	boolean isFree() { return !isPAID;}

	boolean isPaid() { return isPAID; }

	private void adjustTableWidthLand() {

		// sometimes the left side is a bit too narrow for the banner
		// if the left side (1/2 the whole screen width) if less than
		// the minimum space for an ad to fit, need to adjust the left
		// table to make it a bit wider
		if (DI.getDisplayXInDpGroup()/2 <= WT_MIN_AD) {
			// get the table layout

			TableLayout tableLeft = (TableLayout) landView.getParent();

			// start with the existing table layout, layout params
			ViewGroup.LayoutParams tlLprams = tableLeft.getLayoutParams();

			// modify the layout param's width - apparently, this uses the
			// whole screen width and then applies the weight factor (which for
			// the left side is 1/2 (.5 actually)
			tlLprams.width = DI.convertXDpToPix(WT_MIN_SCREEN_LAND);

			// assign the updated layout params to the table layout
			tableLeft.setLayoutParams(tlLprams);

		}
	}



	private AdSize getBannerSizePort() {
		// check if we have enough width for the smallest width
		// banner WT_MINIMUM_PORT
		if (DI.getDisplayXInDpGroup() < WT_MINIMUM_PORT) {return null;}

		//determine the correct banner type to use
		// depending on screen size
		if (DI.getDisplayYInDpGroup() >= HT_XLARGE_PORT) {
			if (DI.getDisplayXInDpGroup() > WT_XLARGE) {
				// 728 x 90 banner
				bannerName = "1P Leaderboard";
				return AdSize.LEADERBOARD;
			} else if (DI.getDisplayXInDpGroup() > WT_LARGE) {
				// 468 x 60 banner
				bannerName = "1P Full Banner";
				return AdSize.FULL_BANNER;
			} else {
				// 300 x 100 banner
				bannerName = "1P Large Banner";
				return AdSize.LARGE_BANNER;
			}
		} else if (DI.getDisplayYInDpGroup() >= HT_LARGE_PORT) {
			if (DI.getDisplayXInDpGroup() > WT_LARGE) {
				// 468 x 60 banner
				bannerName = "2P Full Banner";
				return AdSize.FULL_BANNER;
			} else {
				// 320 x 50 banner
				bannerName = "2P Banner";
				return AdSize.BANNER;
			}
		} else {
			// 320 x 50 banner
			bannerName = "3P Banner";
			return AdSize.BANNER;
		}
	}

	private AdSize getBannerSizeLand() {
		if (DI.getDisplayXInDpGroup() < WT_MINIMUM_LAND) {
			return null;
		}

		// determine the correct banner type to use
		// depending on screen size
		if (DI.getDisplayYInDpGroup() >= HT_LARGE_LAND) {
			if (DI.getDisplayYInDpGroup()/2 > WT_XLARGE) {
				// 728 x 90 banner
				bannerName = "1L Leaderboard";
				return AdSize.LEADERBOARD;
			} else if (DI.getDisplayXInDpGroup()/2 > WT_LARGE) {
				// 468 x 60 banner
				bannerName = "1L Full Banner";
				return AdSize.FULL_BANNER;
			} else {
				// 300 x 100 banner
				bannerName = "1L Large Banner";
				return AdSize.LARGE_BANNER;
			}
		} else if (DI.getDisplayYInDpGroup()/6 >= HT_FULL_BANNER) {
			// either full banner or banner
			if (DI.getDisplayXInDpGroup()/2 > WT_LARGE) {
				// 468 x 60 banner
				bannerName = "2L Full Banner";
				return AdSize.FULL_BANNER;
			} else {
				// 320 x 50 banner
				bannerName = "2L Banner";
				return AdSize.BANNER;
			}
		} else {
			// 320 x 50 banner
			bannerName = "3L Banner";
			return AdSize.BANNER;
		}

	}


	private void displayAd(LinearLayout ll, AdSize adHeight) {
		if (ll == null || adHeight == null) {return;}

		Resources r = ctx.getResources();

		// set up the ad
		// make a new ad
		AdView adView = new AdView(ctx);

		// set the ad height per the above
		adView.setAdSize(adHeight);

		// set the ad's unit id
		adView.setAdUnitId(r.getString(R.string.banner_ad_unit_id));

		// insure visibility
		adView.setVisibility(View.VISIBLE);

		// add the ad to the linear layout
		ll.addView(adView);

		// setup the ad request
		AdRequest adRequest = new AdRequest.Builder()
				.setRequestAgent("android_studio:ad_template").build();

		// load the ad with the request
		adView.loadAd(adRequest);


	}

	private void displayAd(TableRow tr, AdSize adHeight, int spanCount) {

		if (tr == null || adHeight == null) {return;}

		Resources r = ctx.getResources();

		// get the layout params - set the basic height and width
		TableRow.LayoutParams trLprams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

		// set the span for the ad
		trLprams.span = spanCount;

		// set up the ad
		// make a new ad
		AdView adView = new AdView(ctx);

		// set the ad height per the above
		adView.setAdSize(adHeight);

		// set the ad's unit id
		adView.setAdUnitId(r.getString(R.string.banner_ad_unit_id));

		// insure visibility
		adView.setVisibility(View.VISIBLE);

		// add the ad to the table row
		tr.addView(adView, trLprams);

		// setup the ad request
		AdRequest adRequest = new AdRequest.Builder()
				.setRequestAgent("android_studio:ad_template").build();

		// load the ad with the request
		adView.loadAd(adRequest);
	}




}
