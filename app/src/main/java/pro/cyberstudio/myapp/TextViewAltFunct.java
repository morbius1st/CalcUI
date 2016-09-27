package pro.cyberstudio.myapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author Jeff
 *         File:    TextViewAltFunct
 *         Created: 9/25/2016 @ 3:15 PM
 *         Project: CalcUI
 */

class TextViewAltFunct extends TextView {

	int altFunctionCategory;


	TextViewAltFunct (Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TextViewAltFunct);

		altFunctionCategory = attributes.getInt(R.styleable.TextViewAltFunct_category, -1);

		attributes.recycle();
	}

	public int getAltFunctionCategory() {
		return altFunctionCategory;
	}

	public void setAltFunctionCategory(int altFunctionCategory) {
		this.altFunctionCategory = altFunctionCategory;
	}
}
