package pro.cyberstudio.myapp;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author Jeff
 *         File:    Functions
 *         Project: Test1
 */

public class Functions {
	
	static final String n = "\n";

	static void setViewHeight(Activity activity, DisplayInformation DI, int viewID, int intHeight) {
		View v;

		v = activity.findViewById(viewID);
		ViewGroup.LayoutParams lp = v.getLayoutParams();
		lp.height = DI.convertYDpToPix(intHeight);
		v.setLayoutParams(lp);
	}

	static <T extends View> T getView(Activity activity, DisplayInformation DI, int viewId) {
		View vx = activity.findViewById(viewId);

		// process based on the type fo view
		if (vx instanceof TextView) {

			// covers both textviews and buttons
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

	static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);
	}

	static String formatTag(iViewAlt v) {

		Object oTag;

		if (v == null) {
			return "view is null";
		} else {

			 oTag = v.getTag();

			if (oTag == null) {
				return "tag is null";
			}
		}
		return oTag.toString();
	}


}
