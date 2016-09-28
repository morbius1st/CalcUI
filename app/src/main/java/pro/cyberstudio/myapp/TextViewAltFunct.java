package pro.cyberstudio.myapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import static pro.cyberstudio.myapp.ConfigCalcUI.ViewCategory.UNDEFINED;

/**
 * @author Jeff
 *         File:    TextViewAltFunct
 *         Created: 9/25/2016 @ 3:15 PM
 *         Project: CalcUI
 */

class TextViewAltFunct extends TextView {

	int functionCategory;



	public TextViewAltFunct (Context context) {
		super(context);

	}

	public TextViewAltFunct (Context context, AttributeSet attrs) {
		super(context, attrs);

		setAttributes(context, attrs);

	}

	public TextViewAltFunct (Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		setAttributes(context, attrs);

	}

	private void setAttributes(Context context, AttributeSet attrs) {
		TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TextViewAltFunct);

		functionCategory = attributes.getInt(R.styleable.TextViewAltFunct_category_textview, UNDEFINED.getValue());

		attributes.recycle();
	}


	public int getFunctionCategory() {
		return functionCategory;
	}

	public void setFunctionCategory(int functionCategory) {
		this.functionCategory = functionCategory;
	}
}
