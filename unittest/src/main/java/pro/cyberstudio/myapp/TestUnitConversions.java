package pro.cyberstudio.myapp;

import org.apfloat.*;

import java.math.RoundingMode;

import static pro.cyberstudio.myapp.Utilities.*;
import static pro.cyberstudio.myapp.UnitType.*;
import static pro.cyberstudio.myapp.Utilities.apfloat;

/**
 * @author jeffs
 *         File:    TestUnitConversions
 *         Created: 3/5/2017 @ 8:52 PM
 *         Project: CalcUI
 */

class TestUnitConversions {
	
	
	void Test(int testNumber, String testIn, UnitType utIn, String plannedOut, UnitType utOut ) {

		String format = "%#s";
		StringBuilder sb = new StringBuilder();
		
		Apfloat apValueIn = apfloat(testIn);
		Apfloat apPlannedOut = apfloat(plannedOut);
		
		Apfloat apActualOut = UnitType.ConvertValue(apValueIn, utIn, utOut);
		
		sb.append("Test #: ").append(testNumber);
		logMsg(sb.toString());
		sb = new StringBuilder();
		
		sb.append("in  planned ApFloat: ").append(formatApFloat(apValueIn));
		sb.append(" (").append(utIn.getName(apValueIn.doubleValue())).append(")");
		logMsg(sb.toString());
		sb = new StringBuilder();
		
		sb.append("out planned ApFloat: ").append(formatApFloat(apPlannedOut));
		sb.append(" (").append(utOut.getName(apPlannedOut.doubleValue())).append(")");
		logMsg(sb.toString());
		sb = new StringBuilder();
		
		sb.append("in   convert factor: ").append(String.format(format, utIn.getConvertFactor()));
		logMsg(sb.toString());
		sb = new StringBuilder();
		
		sb.append("out  convert factor: ").append(String.format(format, utOut.getConvertFactor()));
		logMsg(sb.toString());
		sb = new StringBuilder();
		
		if (apActualOut != null) {

			sb.append("out  actual ApFloat: ").append(formatApFloat(apActualOut));
			logMsg(sb.toString());
			sb = new StringBuilder();
			
			sb.append("  : matches: ").append(apPlannedOut.equals(apActualOut));
			logMsg(sb.toString());
		} else {
			sb.append("*** invalid conversion");
			logMsg(sb.toString());
		}
		
		logMsg("");
		
	}
	
	String formatApFloat(Apfloat apIn) {
		return String.format("%#s", ApfloatMath.round(apIn, Unit.APFLOATPRECROUND, RoundingMode.HALF_UP));
	}
	
	void RunTests() {
		
		// length
		Test(1000, "1.0", INCH, "1.0", INCH);
		Test(1010, "25.4", MILLIMETER, "1.0", INCH);
		Test(1010, "1.0", MILLIMETER, "0.03937007874015748031496062992125984251968503937007874015748031496062992125984252", INCH);
		Test(1020, "1.0", INCH, "25.4", MILLIMETER);
		Test(1030, "1.0", KILOMETER, "39370.07874015748031496062992125984251968503937007874015748031496062992125984252", INCH);
		Test(1099, "1.0", INCH, "1.0", SQINCH);	// invalid - different UnitTypes
		
		// area
		Test(2000, "1.0", SQINCH, "1.0", SQINCH);
		Test(2000, "645.16", SQMILLIMETER, "1.0", SQINCH);
	
		
	}
	
	
}
