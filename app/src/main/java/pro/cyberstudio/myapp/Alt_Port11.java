package pro.cyberstudio.myapp;

import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import static pro.cyberstudio.myapp.Utilities.*;


public class Alt_Port11 extends AppCompatActivity {

	private DisplayInformation DI;
	private DisplayAd DA;

	private static final float HISTORY_PERCENT = 0.30f; // 30%
	private static final float MARK_IMAGE_MAX_PERCENT = 0.70f; // 70%

	View alt_port11_layout;
	gLayoutListner gL;

//	private Context ctx;
//
//	private View portView;
//	private View landView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alt_port11);

		Intent intent = getIntent();

		DI = new DisplayInformation(getApplicationContext());
		DA = new DisplayAd(getApplicationContext(), DI,
				findViewById(R.id.ad_banner_port), null);

		TextView tv = (TextView) findViewById(R.id.tvHistory11);

		String strDisplayInfo = "";

		strDisplayInfo += DI.toString();

		float factor = DI.getYScaleFactorElements();

		strDisplayInfo += "\n\nFactor Used: " + factor;
		strDisplayInfo += "\nTextSize (pix): " + tv.getTextSize();
		strDisplayInfo += "\nTextSize (dp): " + (tv.getTextSize() / DI.getYDpFactorPhysical());
		strDisplayInfo += "\nResource folder: " + getString(R.string.resource_folder);

		DA.placeAd();
//		strDisplayInfo += "\n\nBanner: " + DA.getBannerName();
//		initHistoryView(intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "\nmessage being sent")) );
		initHistoryView(strDisplayInfo);

		updateTextSize();

		viewData[] vData = new viewData[5];

		vData[0] = new viewData("degree mark", R.id.mark_degree);
		vData[1] = new viewData("foot mark", R.id.mark_foot);
		vData[2] = new viewData("inch mark", R.id.mark_inch);
		vData[3] = new viewData("space mark", R.id.mark_space);
		vData[4] = new viewData("fraction mark", R.id.mark_frac);

		gL = new gLayoutListner(vData);

		alt_port11_layout = findViewById(R.id.tbl_alt_port11);

		alt_port11_layout.getViewTreeObserver().addOnGlobalLayoutListener(gL);

	}

	class viewData {

		String Name;
		int Id;

		viewData(String viewName, int viewId) {
			Name = viewName;
			Id = viewId;
		}
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
			alt_port11_layout.getViewTreeObserver().removeOnGlobalLayoutListener(gL);
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
		TextView tvHist = (TextView) findViewById(R.id.tvHistory11);

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

	<T extends View> T getView(int viewId) {

		return (Functions.getView(this, DI, viewId));
	}



	private void updateTextSize() {

		// row 1
		TextView tvHist = getView(R.id.tvHistory11);

		Button b;

		// adjust the history textview height
		adjustHistHeight(tvHist);

		// row 2
		getView(R.id.tv_entry);
		getView(R.id.ctrl_shift);

		// row 3

		getView(R.id.memory_store).setOnClickListener(oclTest);
		getView(R.id.memory_recall).setOnClickListener(oclTest);
		getView(R.id.const_pi);

		getView(R.id.funct_sqrt);
		getView(R.id.funct_square);

		// row 4
		// not mark buttons - only edit buttons
		getView(R.id.button405);
		getView(R.id.button406);

		// row 5
		getView(R.id.button501);
		getView(R.id.button502);
		getView(R.id.button503);
		getView(R.id.button504);
		getView(R.id.button505);

		// row 6
		getView(R.id.button601);
		getView(R.id.button602);
		getView(R.id.button603);
		getView(R.id.button604);
		getView(R.id.button605);

		// row 7
		getView(R.id.button701);
		getView(R.id.button702);
		getView(R.id.button703);
		getView(R.id.button704);
		getView(R.id.button705);

		// row 8
		getView(R.id.button801);
		getView(R.id.button802);
		getView(R.id.button803);
		getView(R.id.button804);
		getView(R.id.button805);

		TableLayout v = (TableLayout) findViewById(R.id.tbl_alt_port11);

		int j = v.getChildCount();

		// scan through all of the table layout's children
		// all of these should be a tablerow
		for (int i = 0; i < j; i++) {
			View vTblChild = v.getChildAt(i);

			if (vTblChild instanceof TableRow) {
				// the tablelayout's child is a tablerow

				int k = ((TableRow) vTblChild).getChildCount();

				// scan through all of the children of the table row
				for (int l = 0; l < k; l++) {

					View vTrChild = ((TableRow) vTblChild).getChildAt(l);

//					logMsg("is grid? " + vTrChild.getClass().getName() + "  is instance of: " + (vTrChild instanceof GridLayout));

					if (vTrChild instanceof GridLayout) {

						GridLayout gridView = (GridLayout) vTrChild;

						int m = gridView.getChildCount();

						// scan through all of the children of the Grid Layout
						for (int n = 0; n < m; n++) {
//
//							View vGridLayoutChild =  gridView.getChildAt(n);

							adjustChildView(gridView.getChildAt(n));
//
//							Object tag = vGridLayoutChild.getTag();
//
//							if (tag != null &&
//									(tag == getString(R.string.tag_shift) ||
//										tag == getString(R.string.tag_alpha))) {
//								DI.adjustViewTextSize((TextView) vGridLayoutChild);
//							}
						}

					} else if (vTrChild instanceof android.support.v7.widget.GridLayout) {

						android.support.v7.widget.GridLayout gridView =
								(android.support.v7.widget.GridLayout) vTrChild;

						int m = gridView.getChildCount();

						// scan through all of the children of the Grid Layout
						for (int n = 0; n < m; n++) {
							adjustChildView(gridView.getChildAt(n));
						}

					}
				}

			}
		}
	}

	private OnClickListener oclTest = new OnClickListener() {
		@Override
		public void onClick(View view) {

			clickTest(view);

		}
	};

	public void clickTest(View view) {

		String tagString = view.getTag().toString();

		logMsg("view clicked: "+ tagString);

		Toast.makeText(getApplicationContext(), tagString + "  Clicked", Toast.LENGTH_SHORT).show();

	}

	void adjustChildView (View viewChild) {

		Object tag = viewChild.getTag();

		if (tag != null &&
				(tag == getString(R.string.tag_shift) ||
						tag == getString(R.string.tag_alpha))) {
			DI.adjustViewTextSize((TextView) viewChild);
		}
	}
}
