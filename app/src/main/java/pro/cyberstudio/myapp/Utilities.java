package pro.cyberstudio.myapp;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * @author Jeff
 *         File:    Utilities
 *         Created: 8/20/2016 @ 1:40 PM
 *         Project: Test1
 */

class Utilities {

	static final String n = "\n";

	static void logMsg(String msg) {
		Log.d("Test1", msg);
		}

	static void logMsg(String[] msg) {
		for (int i = 0; i < msg.length; i++) {
			Log.d("Test1", msg[i]);
		}
	}

	static void setViewHeight(Activity activity, DisplayInformation DI, int viewID, int intHeight) {
		View v;

		v = activity.findViewById(viewID);
		ViewGroup.LayoutParams lp = v.getLayoutParams();
		lp.height = DI.convertYDpToPix(intHeight);
		v.setLayoutParams(lp);
	}

	static <T extends View> T getView(Activity activity, DisplayInformation DI, int viewId) {
		View vx = activity.findViewById(viewId);

		if (vx instanceof TextView) {

			DI.adjustViewTextSize((TextView) vx);

		} else if (vx instanceof ImageButton) {

			ImageButton ib = (ImageButton) vx;

			float factor = DI.getYScaleFactorElements();

			int intPadTop = (Math.round(ib.getPaddingTop() * factor));
			int intPadLeft = (Math.round(ib.getPaddingLeft() * factor));
			int intPadRight = (Math.round(ib.getPaddingRight() * factor));
			int intPadBot = (Math.round(ib.getPaddingBottom() * factor));

			ib.setPadding(intPadLeft, intPadTop, intPadRight, intPadBot);

		}

		// noinspection unchecked
		return (T) vx;
	}




}
