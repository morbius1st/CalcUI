package pro.cyberstudio.myapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import static pro.cyberstudio.myapp.ConfigCalcUIx.ViewCategory.UNDEFINED;

/**
 * @author Jeff
 *         File:    TextViewAltFunct
 *         Created: 9/25/2016 @ 3:15 PM
 *         Project: CalcUI
 */

public class TextViewAlt extends TextView  implements iViewAlt {

	private int functionCategory;

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

	public void setAttributes(Context context, AttributeSet attrs) {
		TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TextViewAlt);

		functionCategory = attributes.getInt(R.styleable.TextViewAlt_category_textview, UNDEFINED.getValue());

		attributes.recycle();
	}


	public int getViewCategory() {
		return functionCategory;
	}

	public void setViewCategory(int viewCategory) {
		this.functionCategory = viewCategory;
	}
}
