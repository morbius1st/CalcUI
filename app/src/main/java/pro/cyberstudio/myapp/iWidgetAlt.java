package pro.cyberstudio.myapp;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @author Jeff
 *         File:    iWidgetAlt
 *         Created: 10/1/2016 @ 10:22 AM
 *         Project: CalcUI
 */

interface iWidgetAlt {

	void setAttributes(Context context, AttributeSet attrs);

	int getFunctionCategory();

	void setFunctionCategory(int functionCategory);
}
