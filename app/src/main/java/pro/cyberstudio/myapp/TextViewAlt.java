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

class TextViewAlt extends TextView {

	int functionCategory;



	public TextViewAlt(Context context) {
		super(context);

	}

	public TextViewAlt(Context context, AttributeSet attrs) {
		super(context, attrs);

		setAttributes(context, attrs);

	}

	public TextViewAlt(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		setAttributes(context, attrs);

	}

	private void setAttributes(Context context, AttributeSet attrs) {
		TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TextViewAlt);

		functionCategory = attributes.getInt(R.styleable.TextViewAlt_category_textview, UNDEFINED.getValue());

		attributes.recycle();
	}


	public int getFunctionCategory() {
		return functionCategory;
	}

	public void setFunctionCategory(int functionCategory) {
		this.functionCategory = functionCategory;
	}
}
