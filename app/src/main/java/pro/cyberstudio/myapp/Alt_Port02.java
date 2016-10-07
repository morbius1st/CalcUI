package pro.cyberstudio.myapp;

import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import static pro.cyberstudio.myapp.ConfigCalcUIx.*;
import static pro.cyberstudio.myapp.ConfigCalcUIx.CellViewType.*;
import static pro.cyberstudio.myapp.Utilities.*;
//import static pro.cyberstudio.myapp.Utilities.*;

public class Alt_Port02 extends AppCompatActivity {

	private DisplayInformation DI;

	private float HISTORY_PERCENT = 0.25f; // 30%
	private static final float MARK_IMAGE_MAX_PERCENT = 0.70f; // 70%

	private static final int LAYOUT_ID = R.id.tbl_alt_port02;

	private static final int TV_HISTORY = R.id.tvHistory02;

//	private ConfigCalcUIx CUI = new ConfigCalcUIx();
	private ConfigCalcUIMgr CCM;

	private GridLayoutGravity GLG = new GridLayoutGravity();

	private int idx = 0;

//	Object udfAlignment = null;


	View alt_port02_layout;
	gLayoutListner gL;

//	private Context ctx;
//
//	private View portView;
//	private View landView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alt_port02);

		String message = "";

//		Intent intent = getIntent();

		DI = new DisplayInformation(getApplicationContext());
		DisplayAd DA = new DisplayAd(getApplicationContext(), DI,
				findViewById(R.id.ad_banner_port), null);

		TextView tvE = (TextView) findViewById(R.id.tv_entry);

		logMsg("init configcalcui");

		CCM = new ConfigCalcUIMgr(DI);

		if (DA.isFree()) {
			tvE.setText("is free");
			HISTORY_PERCENT = 0.25f;
		}
		else {
			tvE.setText("is paid");
			HISTORY_PERCENT = 0.30f;
		}

		DA.placeAd();

//		CUI.testConfigUI(this);

		setupViews();

//		logMsg(CUI.toStringInitOnlyArray());

		logMsg(CCM.toStringInitOnlyArray());

		initHistoryView(message);

//		initHistory();



		viewData[] vData = new viewData[5];

		vData[0] = new viewData("degree mark", R.id.mark_degree);
		vData[1] = new viewData("foot mark", R.id.mark_foot);
		vData[2] = new viewData("inch mark", R.id.mark_inch);
		vData[3] = new viewData("space mark", R.id.mark_space);
		vData[4] = new viewData("fraction mark", R.id.mark_frac);

		gL = new gLayoutListner(vData);

		alt_port02_layout = findViewById(LAYOUT_ID);

		alt_port02_layout.getViewTreeObserver().addOnGlobalLayoutListener(gL);

	}

	class viewData {

		String Name;
		int Id;

		viewData(String viewName, int viewId) {
			Name = viewName;
			Id = viewId;
		}
	}


	void initHistory() {
		TextView tv = (TextView) findViewById(TV_HISTORY);

		String strDisplayInfo = "";

		strDisplayInfo += DI.toString();

		getResources().getString(R.string.num_zero);

		float factor = DI.getYScaleFactorElements();

		strDisplayInfo += "\n\nFactor Used: " + factor;
		strDisplayInfo += "\nTextSize (pix): " + tv.getTextSize();
		strDisplayInfo += "\nTextSize (dp): " + (tv.getTextSize() / DI.getYDpFactorPhysical());
		strDisplayInfo += "\nResource folder: " + getString(R.string.resource_folder);

		initHistoryView(strDisplayInfo);
	}


	// this routine allows the adjustment to the size and location of an image in
	// an imagebutton - this must be preformed here as this will get the
	// actual size of the image button after it has been drawn
	class gLayoutListner implements ViewTreeObserver.OnGlobalLayoutListener {

		viewData[] vData;

		@Override
		public void onGlobalLayout() {

			// this routine applies to all views identified in the
			//vData array
			if (vData == null || vData.length == 0) {
				return;
			}

			// process every element in the vData array
			for (viewData vElement: vData) {

				// get the view
				ImageButton iB = (ImageButton) findViewById(vElement.Id);

				// get the view's actual, on screen height
				int ibW = iB.getWidth();
				int ibH = iB.getHeight();

				// get the internal image's image matrix
				Matrix mX = iB.getImageMatrix();

				// get the base drawable (image)
				// this is before it has been scaled
				Drawable dW = iB.getDrawable();

				// get the base drawable's width and height
				int intrW = dW.getIntrinsicWidth();
				int intrH = dW.getIntrinsicHeight();

				// determine the smallest size (should be square)
				float intrMin = Math.min(intrW, intrH);

				// determine the prefered image size
				float preferMin = Math.min(ibW, ibH) * MARK_IMAGE_MAX_PERCENT;

				// determine the new scale factor
				float newScale = preferMin / intrMin;

				// view width = ibW & height = ibH
				// image (scaled) width = imageW & height imageH

				// determine the images new width and height
				int imageW = Math.round(intrW * newScale);
				int imageH = Math.round(intrH * newScale);

				// determine the net imagebutton's size (within the padding)
				int netW = ibW - iB.getPaddingLeft() - iB.getPaddingRight();
				int netH = ibH - iB.getPaddingTop() - iB.getPaddingBottom();

				// determine the needed adjustment to move the image
				// into the correct location
				float translateW = (netW - imageW) / 2;
				float translateH = (netH - imageH) / 2;

				// set the new scale
				mX.setScale(newScale, newScale);

				// set the new location
				mX.postTranslate(translateW, translateH);

				// update the image
				iB.setImageMatrix(mX);

			}
			alt_port02_layout.getViewTreeObserver().removeOnGlobalLayoutListener(gL);
		}

		gLayoutListner(viewData[] vData) {
			this.vData = vData;
		}
	}

	public void returnResult(View v) {


		Intent intent = new Intent();

		intent.putExtra(MainActivity.EXTRA_MESSAGE,"\nmessage being returned\n");

		setResult(RESULT_OK, intent);

		finish();

	}

	void initHistoryView(String message) {
		TextView tvHist = (TextView) findViewById(TV_HISTORY);

		// make the textview scroll
		tvHist.setMovementMethod(new ScrollingMovementMethod());

		// display the information
		tvHist.setText(message);
	}

	// adjust the height of the history textview
	private void adjustHistHeight(TextView tv) {

		// get the layout parameters
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tv.getLayoutParams();

		// set the view to the height percentage
		lp.height = (int) (DI.getAppDisplayYPix() * HISTORY_PERCENT);

		// update the view
		tv.setLayoutParams(lp);
	}
//
//	<T extends View> T getView(int viewId) {
//
//		return (Utilities.getView(this, DI, viewId));
//	}


	// the field bogus is only to create a unique signature
	// for this method
	<T extends View> T getView(int viewId) {
		T v = Utilities.getView(this, DI, viewId);

		CCM.addView(v, viewId);

		return v;
	}


//
//	// for initial setup only - this will wipe out existing cell views
//	// this deals with the primary views (the buttons)
//	<T extends View> T getView(int row, int column, int viewId) {
//		CellViewType cvt;
//
//		T v = (Utilities.getView(this, DI, viewId));
//
//		if (verifyRowColumn(row, column)) {
//			CellInfo ci = new CellInfo();
//
//			// setup the basic cell view with just the type, id, and view reference
//			// the other settings are based on the XML layout
//
//			CellView cv = null;
////
////			if (v != null) {
////				logMsg("v: " + v.getClass().getSimpleName());
////			} else {
////				logMsg("v: is null");
////			}
//
//			switch (findViewTypeByView(v)) {
//				case BUTTON:
////					logMsg("type is button");
//					 cv = new CellView(BUTTON, viewId, "", UNDEF, UNDEF, UNDEF, v);
//					break;
//				case TEXTVIEW:
////					logMsg("type is textview");
//					cv = new CellView(TEXTVIEW, viewId, "", UNDEF, UNDEF, UNDEF, v);
//					break;
//				case IMAGEBUTTON:
////					logMsg("type is imagebutton");
//					cv = new CellView(IMAGEBUTTON, viewId, "", UNDEF, UNDEF, UNDEF, v);
//					break;
////				default:
////					logMsg("type is undefined");
//			}
//
//			if (cv != null) {
//				ci.addView(cv);
//
//				ViewParent vp = v.getParent();
//
//				if (vp.getClass() == GridLayout.class) {
//					int i = ((GridLayout) vp).getChildCount();
//
//					for (int j = 0; j < i; j++) {
//						View vChild = ((GridLayout) vp).getChildAt(j);
//
//						adjustChildView(vChild);
//
//						cvt = findViewTypeByGravity(GLG.getGravity(vChild));
//						if (cvt != null) {
////							logMsg("add child view");
//							cv = new CellView(cvt, UNDEF, "", UNDEF, UNDEF, UNDEF, vChild);
//							ci.addView(cv);
//						}
//					}
//				}
//				CUI.addCell(row, column, ci);
//			}
//		}
//		return v;
//	}

	public String test() {
		return "this is a test";
	}


	public void setupViews() {
		int row;

//
//		Method t = null;
//
//		try {
//			t = this.getClass().getMethod("test", null);
//		} catch( Exception e) {
//			logMsg("exception: " + e.toString());
//		}
//
//		try {
//
//			if (t != null) {
//				logMsg("t = " + t.invoke(this, null));
//			}
//		} catch (Exception e) {}
//

		// row n/a
		// the banner ad

		// row 0
		row = 0;
		TextView tvHist = getView(TV_HISTORY);

		// adjust the history textview height
		adjustHistHeight(tvHist);

		// row 1
		row = 1;

		getView(R.id.grp_pren_begin);
		getView(R.id.grp_pren_end);
		getView(R.id.tv_entry);

		// row 2
		row = 2;
		getView(R.id.ctrl_shift);
		getView(R.id.memory_store).setOnClickListener(oclTest);
		getView(R.id.memory_recall).setOnClickListener(oclTest);
		getView(R.id.funct_sqrt);
		getView(R.id.funct_square);

		// row 3
		row = 3;
		getView(R.id.convert_deg_to_decimal);
		getView(R.id.const_pi);
		getView(R.id.funct_sin);
		getView(R.id.funct_cos);
		getView(R.id.funct_tan);

		// row 4
		row = 4;
		getView(R.id.mark_degree);
		getView(R.id.mark_foot);
		getView(R.id.mark_inch);
		getView(R.id.mark_space);
		getView(R.id.mark_frac);
		getView(R.id.edit_backspace);
		getView(R.id.edit_ce_ca);

		// row 5
		row = 5;
		getView(R.id.num_seven);
		getView(R.id.num_eight);
		getView(R.id.num_nine);
		getView(R.id.funct_integer);
		getView(R.id.funct_fraction);

		// row 6
		row = 6;
		getView(R.id.num_four);
		getView(R.id.num_five);
		getView(R.id.num_six);
		getView(R.id.opp_multiply);
		getView(R.id.opp_divide);

		// row 7
		row = 7;
		getView(R.id.num_one);
		getView(R.id.num_two);
		getView(R.id.num_three);
		getView(R.id.opp_add);
		getView(R.id.opp_subtract);

		// row 8
		row = 8;
		getView(R.id.num_zero);
		getView(R.id.num_decimal_pt);
		getView(R.id.funct_change_sign);
		getView(R.id.funct_answer).setOnClickListener(oclTest);
		getView(R.id.opp_calculate).setOnClickListener(oclTest);


		// scan through all of the table layout's children
		// all of these should be a tablerow
		// find each child view of the proper type and update
		// the view per the adjusted display parameters
//		TableLayout v = (TableLayout) findViewById(LAYOUT_ID);
//
//		int j = v.getChildCount();
//
//		for (int i = 0; i < j; i++) {
//			View vTblChild = v.getChildAt(i);
//
//			if (vTblChild instanceof TableRow) {
//				// the tablelayout's child is a tablerow
//
//				int k = ((TableRow) vTblChild).getChildCount();
//
//				// scan through all of the children of the table row
//				for (int l = 0; l < k; l++) {
//
//					View vTrChild = ((TableRow) vTblChild).getChildAt(l);
//
//					if (vTrChild instanceof GridLayout) {
//
//						GridLayout gridView = (GridLayout) vTrChild;
//
//						int m = gridView.getChildCount();
//
//						// scan through all of the children of the Grid Layout
//						for (int n = 0; n < m; n++) {
//
//							adjustChildView(gridView.getChildAt(n));
//						}
//
//					} else if (vTrChild instanceof android.support.v7.widget.GridLayout) {
//
//						android.support.v7.widget.GridLayout gridView =
//								(android.support.v7.widget.GridLayout) vTrChild;
//
//						int m = gridView.getChildCount();
//
//						// scan through all of the children of the Grid Layout
//						for (int n = 0; n < m; n++) {
//							adjustChildView(gridView.getChildAt(n));
//						}
//
//					}
//				}
//			}
//		}
	}

	private OnClickListener oclTest = new OnClickListener() {
		@Override
		public void onClick(View view) {

			clickTest(view);

		}
	};

	public void clickTest(View view) {

		if (((Button) view).getText() != null) {

			String tagString = ((Button) view).getText().toString();

//			logMsg("view clicked: " + tagString);

			Toast.makeText(getApplicationContext(), tagString + "  Clicked", Toast.LENGTH_SHORT).show();
		}

	}

	void adjustChildView (View vChild) {

		if (vChild instanceof TextViewAlt) {
			DI.adjustViewTextSize((TextView) vChild);
		}

	}
}
