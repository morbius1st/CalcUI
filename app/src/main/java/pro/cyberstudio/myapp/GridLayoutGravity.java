package pro.cyberstudio.myapp;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.lang.reflect.Field;

import static pro.cyberstudio.myapp.Utilities.*;

/**
 * @author Jeff
 *         File:    GridLayoutGravity
 *         Created: 9/24/2016 @ 3:41 PM
 *         Project: CalcUI
 */

class GridLayoutGravity {

	GridLayout.Alignment udfAlignment = null;

	int gravityH = Gravity.NO_GRAVITY;
	int gravityV = Gravity.NO_GRAVITY;
	int gravity = Gravity.NO_GRAVITY;


	GridLayoutGravity() {
		Field[] fields = null;

		try {
			fields = GridLayout.class.getClass().getDeclaredFields();


			if (fields != null) {
				for (Field field : fields) {
					field.setAccessible(true);

					if (field.getName().equals("UNDEFINED_ALIGNMENT"))
						udfAlignment = (GridLayout.Alignment) field.get(field);
				}
			}
		} catch (Exception e) {
			logMsg(e.toString());
		}
	}

	int getGravityH() {
		return gravityH;
	}

	int getGravityV() {
		return gravityV;
	}

	int getGravity() {
		return gravity;
	}

	int getGravity(View vChild) {

		gravity = Gravity.NO_GRAVITY;

		if (!(vChild instanceof Button)) {
			GridLayout.LayoutParams lp = (GridLayout.LayoutParams) vChild.getLayoutParams();

			try {
				Field fieldH = lp.columnSpec.getClass().getDeclaredField("alignment");
				Field fieldV = lp.rowSpec.getClass().getDeclaredField("alignment");

				fieldH.setAccessible(true);
				fieldV.setAccessible(true);

				gravityH = getGravityValue((GridLayout.Alignment) fieldH.get(lp.columnSpec));
				gravityV = getGravityValue((GridLayout.Alignment) fieldV.get(lp.rowSpec));

				gravity = gravityH | gravityV;

			} catch (Exception e) {
				logMsg(e.toString());
			}
		}

		return gravity;
	}

	int getGravityValue(GridLayout.Alignment gridAlignment) {

		if (gridAlignment.equals(GridLayout.TOP)) {
			return Gravity.TOP;
		}else if (gridAlignment.equals(GridLayout.BOTTOM)) {
			return Gravity.BOTTOM;
		} else if (gridAlignment.equals(GridLayout.LEFT)) {
			return Gravity.LEFT;
		} else if (gridAlignment.equals(GridLayout.RIGHT)) {
			return Gravity.RIGHT;
		} else if (gridAlignment.equals(GridLayout.END)) {
			return Gravity.END;
		} else if (gridAlignment.equals(GridLayout.CENTER)) {
			return Gravity.CENTER;
		} else if (gridAlignment.equals(GridLayout.FILL)) {
			return Gravity.FILL;
		} else if (udfAlignment != null) {
			if (gridAlignment.equals(udfAlignment)) {
				return Gravity.NO_GRAVITY;
			}
		}
		return -1;
	}




}
