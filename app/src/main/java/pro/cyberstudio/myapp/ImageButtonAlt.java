package pro.cyberstudio.myapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageButton;

import static pro.cyberstudio.myapp.ConfigCalcUIx.ViewCategory.UNDEFINED;

/**
 * @author Jeff
 *         File:    ImageButtonAlt
 *         Created: 10/1/2016 @ 11:33 AM
 *         Project: CalcUI
 */

public class ImageButtonAlt extends ImageButton implements iViewAlt {

	private int functionCategory;

	public ImageButtonAlt(Context context, AttributeSet attrs) {
		super(context, attrs);

		setAttributes(context, attrs);
	}

	public void setAttributes(Context context, AttributeSet attrs) {
		TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ImageButtonAlt);

		functionCategory = attributes.getInt(R.styleable.ImageButtonAlt_category_imagebutton, UNDEFINED.getValue());

		attributes.recycle();
	}

	public int getFunctionCategory() {
		return functionCategory;
	}

	public void setFunctionCategory(int functionCategory) {
		this.functionCategory = functionCategory;
	}

	public void setTextColor(int c) {}
}

