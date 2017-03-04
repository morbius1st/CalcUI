package pro.cyberstudio.myapp;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author Jeff
 *         File:    Utilities
 *         Created: 8/20/2016 @ 1:40 PM
 *         Project: Test1
 */

public class Utilities {

	static final String n = "\n";

	public static void test() {}

	public static void printLn(String msg) {System.out.println(msg); }

	public static void logMsg(String msg) {
		Log.d("Test1", msg);
		}

	static void logMsg(String[] msg) {
		for (String aMsg : msg) {
			logMsg(msg);
		}
	}

	static void logMsg(ArrayList<StringBuilder> sbArray) {
		int count = 1;

		for (StringBuilder sb : sbArray) {
			logMsg(sb.toString());
		}
	}

	static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);
	}

}
