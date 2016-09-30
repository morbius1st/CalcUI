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

public class ButtonAlt extends Button {

	int functionCategory;

	public ButtonAlt(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ButtonAlt);

		functionCategory = attributes.getInt(R.styleable.ButtonAlt_category_button, UNDEFINED.getValue());

		attributes.recycle();
	}

	private void setAttributes(Context context, AttributeSet attrs) {
		TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ButtonAlt);

		functionCategory = attributes.getInt(R.styleable.ButtonAlt_category_button, UNDEFINED.getValue());

		attributes.recycle();
	}


	public int getFunctionCategory() {
		return functionCategory;
	}

	public void setFunctionCategory(int functionCategory) {
		this.functionCategory = functionCategory;
	}
}
