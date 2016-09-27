package pro.cyberstudio.myapp;

import android.app.Application;
import android.content.Context;

/**
 * @author Jeff
 *         File:    Ctx_port02
 *         Created: 9/25/2016 @ 8:51 AM
 *         Project: CalcUI
 */

public class AppContext extends  Application {

	// keep a reference for the application context
	private static Context ctxContext;

	@Override
	public void onCreate() {
		super.onCreate();
		ctxContext = getApplicationContext();
	}

	/**
	 * return the application context
	 */
	public static Context getContext() {
		return ctxContext;
	}

}
