package pro.cyberstudio.myapp;

import org.apfloat.Apfloat;

import static pro.cyberstudio.myapp.UnitSpecialConversions.*;
import static pro.cyberstudio.myapp.UnitType.*;
import static pro.cyberstudio.myapp.Utilities.*;


/**
 * @author jeffs
 *         File:    UnitTest
 *         Created: 3/3/2017 @ 11:15 PM
 *         Project: CalcUI
 */

public class UnitTest {

	public static void main(String[] args) {

		boolean RunConversionTest = true;
		
		
		if (RunConversionTest) {
			TestUnitConversions tuc = new TestUnitConversions();
			
			tuc.RunTests();
			
		} else {
			logMsg("No tests selected");
		}
		
		System.exit(0);
		
		
//		logMsg(args[0]);
//
//		Apfloat zero1 = Apfloat.ZERO;
//		Apfloat zero2 = Apfloat.ZERO;
//		Apfloat num1 = new Apfloat("1", Unit.APFLOATPRECISION);
//		Apfloat num2 = new Apfloat("1234567890.0987654321", Unit.APFLOATPRECISION);
//
//		logMsg("zero1: " + zero1);
//		logMsg("zero2: " + zero2);
//		logMsg("num1:  " + num1);
//		logMsg("num2:  " + String.format("%#s", num2));
//
//		logMsg("equals z1 to z2: " + zero1.equals(zero2));
//		logMsg("equals z1 to n1: " + zero1.equals(num1));
//		logMsg("equals z1 to n2: " + zero1.equals(num2));
//
//		logMsg("TEMP_CELSIUS code is: " + TEMP_CELSIUS);
//		logMsg("TEMP_FAHRENHEIT code is: " + TEMP_FAHRENHEIT);
//		logMsg("ANGLE_DECIMAL code is: " + ANGLE_DECIMAL);
//		logMsg("ANGLE_RATIO code is: " + ANGLE_RATIO);
//
//		logMsg("AngleDecimal convert factor: " + String.format("%#s", ANGLEDECIMAL.getConvertFactor()));
//		logMsg("AngleDecimal convert factor: " + String.format("%#s", ANGLEDMS.getConvertFactor()));


	}
}
