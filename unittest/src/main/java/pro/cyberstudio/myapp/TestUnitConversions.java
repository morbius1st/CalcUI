package pro.cyberstudio.myapp;

import org.apfloat.Apfloat;

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
		
		String plannedResult = String.format(format, apPlannedOut);
		String ActualResult = String.format(format, apActualOut);
		
		sb.append("Test #: ").append(testNumber);
		logMsg(sb.toString());
		sb = new StringBuilder();
		
		sb.append("in  planned ApFloat: ").append(String.format(format, apValueIn));
		logMsg(sb.toString());
		sb = new StringBuilder();
		
		sb.append("out planned ApFloat: ").append(String.format(format, apPlannedOut));
		logMsg(sb.toString());
		sb = new StringBuilder();
		
		sb.append("out  actual ApFloat: ").append(String.format(format, apActualOut));
		logMsg(sb.toString());
		sb = new StringBuilder();
		
		sb.append("in   convert factor: ").append(String.format(format, utIn.getConvertFactor()));
		logMsg(sb.toString());
		sb = new StringBuilder();
		
		sb.append("out  convert factor: ").append(String.format(format, utOut.getConvertFactor()));
		logMsg(sb.toString());
		sb = new StringBuilder();
		
		sb.append("  : matches: ").append(apPlannedOut.equals(apActualOut));
		
		logMsg(sb.toString());
	}
	
	void RunTests() {
		
		//
		Test(1000, "1.0", INCH, "1.0", INCH);
		Test(1010, "25.4", MILLIMETER, "1.0", INCH);
		Test(1020, "1.0", INCH, "25.4", MILLIMETER);
		
		
	}
	
	
}
