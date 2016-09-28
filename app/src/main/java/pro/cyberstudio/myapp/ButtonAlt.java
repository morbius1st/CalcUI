package pro.cyberstudio.myapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import static pro.cyberstudio.myapp.ConfigCalcUI.ViewCategory.UNDEFINED;

/**
 * @author Jeff
 *         File:    ButtonAlt
 *         Created: 9/27/2016 @ 7:29 PM
 *         Project: CalcUI
 */

class ButtonAlt extends Button {

	int functionCategory;

	ButtonAlt(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ButtonAltFunct);

		functionCategory = attributes.getInt(R.styleable.ButtonAltFunct_category_button, UNDEFINED.getValue());

		attributes.recycle();
	}

	public int getFunctionCategory() {
		return functionCategory;
	}

	public void setFunctionCategory(int functionCategory) {
		this.functionCategory = functionCategory;
	}
}
