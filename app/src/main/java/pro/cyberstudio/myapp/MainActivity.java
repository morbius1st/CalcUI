package pro.cyberstudio.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

import static pro.cyberstudio.myapp.Utilities.*;


public class MainActivity extends AppCompatActivity {

	private DisplayInformation DI;

	private static int count = 0;

	public final static String EXTRA_MESSAGE = "pro.cyberstudio.myapplication.MESSAGE";

	private float HISTORY_PERCENT = 0.40f;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DI = new DisplayInformation(getApplicationContext());
		DisplayAd DA = new DisplayAd(getApplicationContext(), DI,
				findViewById(R.id.ad_banner_port),
				findViewById(R.id.ad_banner_land));

		TextView tv = (TextView) findViewById(R.id.tvHistoryMain);
		ImageButton ib = (ImageButton) findViewById(R.id.button403);
		TextView tvE = (TextView) findViewById(R.id.tv_entry);

		if (DA.isFree()) {
			tvE.setText("is free");
			HISTORY_PERCENT = 0.30f;
		}
		else {
			tvE.setText("is paid");
			HISTORY_PERCENT = 0.50f;
		}

		logMsg("app id: " + BuildConfig.APPLICATION_ID);
		logMsg("test: " + BuildConfig.VERSION_NAME);
		logMsg("test: " + BuildConfig.VERSION_CODE);

		String strDisplayInfo = "";

		strDisplayInfo += DI.toString();

		float factor = DI.getYScaleFactorElements();

		strDisplayInfo += n;

		strDisplayInfo += "\nResource folder: " + getString(R.string.resource_folder);

		strDisplayInfo += "\n\nFactor Used: " + factor;
		strDisplayInfo += "\nTextSize (pix): " + tv.getTextSize();
		strDisplayInfo += "\nTextSize (dp): " + (tv.getTextSize() / DI.getYDpFactorPhysical());
		strDisplayInfo += "\nResource folder: " + getString(R.string.resource_folder);

		DA.placeAd();
		//strDisplayInfo += "Banner: " + bannerName;

		updateTextSize();

		// make the textview scroll
		tv.setMovementMethod(new ScrollingMovementMethod());

		// display the information
		tv.setText(strDisplayInfo);

		int intPaddingLeft = (int) (ib.getPaddingLeft() * factor);
		int intPaddingRight = (int) (ib.getPaddingRight() * factor);

		ib.setPadding(intPaddingLeft,ib.getPaddingTop(), intPaddingRight, ib.getPaddingBottom());
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		return id == R.id.action_settings || super.onOptionsItemSelected(item);

	}

	private void updateTextSize() {

		if (DI.getRotation().isLandscape()) {

			// set the height of these buttons to 1/8th of the screen height

			int intTrYInDp= (int) (((float) DI.getDisplayYInDpActual()) * 0.125);

			setViewHeight(R.id.button301, intTrYInDp);
			setViewHeight(R.id.button302, intTrYInDp);
			setViewHeight(R.id.button305, intTrYInDp);
			setViewHeight(R.id.button805, intTrYInDp);
			setViewHeight(R.id.button201, intTrYInDp);
			setViewHeight(R.id.button202, intTrYInDp);
			setViewHeight(R.id.button303, intTrYInDp);
			setViewHeight(R.id.tv_entry, intTrYInDp);
		}

		// need to set a return type for generic to work
		TextView tvHist = getView(R.id.tvHistoryMain);

		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tvHist.getLayoutParams();
		lp.height = (int) (DI.getDisplayXPix() * HISTORY_PERCENT);
		tvHist.setLayoutParams(lp);



		getView(R.id.tv_entry);
		getView(R.id.textView301);

		getView(R.id.button201);
		getView(R.id.button202);

		getView(R.id.button301);
		getView(R.id.button302);
		getView(R.id.button303);
		getView(R.id.button304);
		getView(R.id.button305);

		getView(R.id.button400);
//		getView(R.id.button401);
//		getView(R.id.button402);
//		getView(R.id.button403);
//		getView(R.id.button404);
		getView(R.id.button405);
		getView(R.id.button406);

		getView(R.id.button501);
		getView(R.id.button502);
		getView(R.id.button503);
		getView(R.id.button504);
		getView(R.id.button505);

		getView(R.id.button601);
		getView(R.id.button602);
		getView(R.id.button603);
		getView(R.id.button604);
		getView(R.id.button605);

		getView(R.id.button701);
		getView(R.id.button702);
		getView(R.id.button703);
		getView(R.id.button704);
		getView(R.id.button705);

		getView(R.id.button801);
		getView(R.id.button802);
		getView(R.id.button803);
		getView(R.id.button804);
		getView(R.id.button805);

	}

	private void setViewHeight(int viewID, int intHeight) {
		View v;

		v = findViewById(viewID);
		ViewGroup.LayoutParams lp = v.getLayoutParams();
		lp.height = DI.convertYDpToPix(intHeight);
		v.setLayoutParams(lp);
	}

	private <T extends View> T getView(int viewId) {
		View vx = findViewById(viewId);

		if (vx instanceof TextView) {
			DI.adjustViewTextSize((TextView) vx);
		}

		// noinspection unchecked
		return (T) vx;
	}

	public void sendMessage(View view) {
		Intent intent = new Intent(this, Alt_Port11.class);
		TextView tv = (TextView) findViewById(R.id.tvHistoryMain);

		String message = tv.getText().toString() + " : " + ++count;

		intent.putExtra(EXTRA_MESSAGE, message);

		startActivityForResult(intent, 1);
	}

	public void sendMessagePort01(View view) {
		Intent intent = new Intent(this, Alt_Port01.class);
		TextView tv = (TextView) findViewById(R.id.tvHistoryMain);

		String message = tv.getText().toString() + " : " + ++count;

		intent.putExtra(EXTRA_MESSAGE, message);

		startActivityForResult(intent, 1);
	}

	public void sendMessagePort02(View view) {
		Intent intent = new Intent(this, Alt_Port02.class);
		TextView tv = (TextView) findViewById(R.id.tvHistoryMain);

		String message = tv.getText().toString() + " : " + ++count;

		intent.putExtra(EXTRA_MESSAGE, message);

		startActivityForResult(intent, 1);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		TextView tv = (TextView) findViewById(R.id.tvHistoryMain);

		switch (requestCode) {
			case (1) :
				if (resultCode == RESULT_OK) {
					tv.setText(String.format(Locale.US, "%s %s",
							data.getStringExtra(EXTRA_MESSAGE),
							getString(R.string.msg_recvd)));

				} else {

					tv.setText(R.string.result_not_ok);
				}
				break;
			default :
				tv.setText(R.string.default_return);

		}

		tv.invalidate();
	}


}
