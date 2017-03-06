package pro.cyberstudio.myapp;

import org.apfloat.Apfloat;

/**
 * @author jeffs
 *         File:    pro.cyberstudio.myapp.Utilities
 *         Created: 3/4/2017 @ 5:20 AM
 *         Project: CalcUI
 */

class Utilities {

	static void logMsg(String msg) { System.out.println(msg);}
	
	static Apfloat apfloat(String valueIn) {
		return new Apfloat(valueIn, Unit.APFLOATPRECISION);
	}
	
}
