package pro.cyberstudio.myapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.*;
import android.widget.TextView;

/**
 * @author Jeff
 *         File:    iViewAlt
 *         Created: 10/1/2016 @ 10:22 AM
 *         Project: CalcUI
 */

interface iViewAlt {

	void setAttributes(Context context, AttributeSet attrs);

	int getViewCategory();

	void setViewCategory(int viewCategory);

//	void setTextColor(int color);
//
//	void setText(CharSequence text);
//
//	void setTextSize(float size);

//	void setBackgroundColor(int color);



}
