package pro.cyberstudio.apcalc;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

import java.util.logging.LogManager;

public class apCalc {
	
	public static void main(String[] args) {
		
		Apfloat num = new Apfloat(2,20);
		
		logMsg(ApfloatMath.sqrt(num).toString());
		
	}
	
	static void logMsg(String msg) {
		System.out.println(msg);
	}
}
