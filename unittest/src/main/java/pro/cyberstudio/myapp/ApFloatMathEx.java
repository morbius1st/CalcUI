package pro.cyberstudio.myapp;

import org.apfloat.*;

import static pro.cyberstudio.myapp.Utilities.apfloat;

/**
 * @author jeffs
 *         File:    ApFloatMath
 *         Created: 3/5/2017 @ 8:26 PM
 *         Project: CalcUI
 */

class ApFloatMathEx {
	
	static Apfloat angleDegreesToPercent(Apfloat apDegrees) {
		return ApfloatMath.tan(apDegrees);
	}
	
	static Apfloat angleDegreesToRatio(Apfloat apDegrees) {
		return ApfloatMath.tan(apDegrees).multiply(apfloat("100.0"));
	}
	
	static Apfloat anglePercentToDegrees(Apfloat apPercent) {
		return ApfloatMath.atan(apPercent);
	}
	
	static Apfloat angleRatioToDegrees(Apfloat apRatio) {
		Apfloat apTemp = apRatio.divide(apfloat("100.0"));
		return ApfloatMath.atan(apTemp);
	}

}
