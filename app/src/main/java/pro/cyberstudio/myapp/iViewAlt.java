package pro.cyberstudio.myapp;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @author Jeff
 *         File:    iViewAlt
 *         Created: 10/1/2016 @ 10:22 AM
 *         Project: CalcUI
 */

interface iViewAlt {

	void setAttributes(Context context, AttributeSet attrs);

	int getFunctionCategory();

	void setFunctionCategory(int functionCategory);

	Object getTag();

	void setTextColor(int color);

}
