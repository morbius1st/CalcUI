package pro.cyberstudio.myapp;

import org.apfloat.*;

import java.math.RoundingMode;
import java.util.*;

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
	
	
	void testASample(int testNumber, boolean shouldPass, String testIn, UnitType utIn, String plannedOut, UnitType utOut) {

		String format = "%#s";
		StringBuilder sb = new StringBuilder();
		
		Apfloat apValueIn = apfloat(testIn);
		Apfloat apPlannedOut = apfloat(plannedOut);
		
		Apfloat apActualOut = UnitType.ConvertValue(apValueIn, utIn, utOut);
		
		Formatter formatter = new Formatter(Locale.US);
		
		sb.append("testASample #: ").append(testNumber);
		logMsg(sb.toString());
		sb = new StringBuilder();
		
		sb.append("in  planned ApFloat: ").append(formatApFloat(apValueIn));
		sb.append(" (").append(utIn.getName(apValueIn.doubleValue())).append(")");
		sb.append(" precision: ").append(apValueIn.precision());
		
		logMsg(sb.toString());
		sb = new StringBuilder();
		
		sb.append("out planned ApFloat: ").append(formatApFloat(apPlannedOut));
		sb.append(" (").append(utOut.getName(apPlannedOut.doubleValue())).append(")");
		sb.append(" precision: ").append(apPlannedOut.precision());
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
		String temp = "";
		
		try {
			temp = String.format("%#s", ApfloatMath.round(apIn, Unit.APFLOATPRECROUND, RoundingMode.HALF_UP));
		} catch (Exception e) {
			Println("error: " + e.getMessage());
			Println("value: " + apIn);
		}
		
		return temp;
	}
	
	void RunTests() {
		
		// length
		testASample(1000, true, "1.0", INCH, "1.0", INCH);
		testASample(1010, true, "25.4", MILLIMETER, "1.0", INCH);
		testASample(1010, true, "1.0", MILLIMETER, "0.03937007874015748031496062992125984251968503937007874015748031496062992125984252", INCH);
		testASample(1020, true, "1.0", INCH, "25.4", MILLIMETER);
		testASample(1030, true, "1.0", KILOMETER, "39370.07874015748031496062992125984251968503937007874015748031496062992125984252", INCH);
		testASample(1099, true, "1.0", INCH, "1.0", SQINCH);	// invalid - different UnitTypes
		
		// area
		testASample(2000, true, "1.0", SQINCH, "1.0", SQINCH);
		testASample(2000, true, "645.16", SQMILLIMETER, "1.0", SQINCH);
		
		// Series 1000 - Length
		// Series 10x0 - from Inch
		testASample(1010, true, "7.0", INCH, "7000000", MIL);
		testASample(1020, true, "7.0", INCH, "7", INCH);
		testASample(1030, true, "7.0", INCH, "0.194444444444", YARD);
		testASample(1040, true, "7.0", INCH, "0.000110479798", MILE);
		testASample(1050, true, "7.0", INCH, "177800000", NANOMETER);
		testASample(1060, true, "7.0", INCH, "177.8", MILLIMETER);
		testASample(1070, true, "7.0", INCH, "0.1778", METER);
		testASample(1080, true, "7.0", INCH, "0.0001778", KILOMETER);
		
		// Series 11x0 - from Foot
		testASample(1110, true, "9.0", FOOT, "108000000", MIL);
		testASample(1120, true, "9.0", FOOT, "108", INCH);
		testASample(1130, true, "9.0", FOOT, "3", YARD);
		testASample(1140, true, "9.0", FOOT, "0.001704545455", MILE);
		testASample(1150, true, "9.0", FOOT, "2743.2", MILLIMETER);
		testASample(1160, true, "9.0", FOOT, "0.27432", DECAMETER);
		
		// Series 12x0 - from Fathom
		testASample(1210, true, "11", FATHOM, "792000000", MIL);
		testASample(1220, true, "11", FATHOM, "792", INCH);
		testASample(1230, true, "11", FATHOM, "22", YARD);
		testASample(1240, true, "11", FATHOM, "0.0125", MILE);
		testASample(1250, true, "11", FATHOM, "2011.68", CENTIMETER);
		testASample(1260, true, "11", FATHOM, "20.1168", METER);
		testASample(1270, true, "11", FATHOM, "0.201168", HECTOMETER);
		
		// Serise 13x0 - from Yard
		testASample(1310, true, "13", YARD, "468000000", MIL);
		testASample(1320, true, "13", YARD, "468", INCH);
		testASample(1330, true, "13", YARD, "0.007386363636", MILE);
		testASample(1340, true, "13", YARD, "11887200000", NANOMETER);
		testASample(1350, true, "13", YARD, "11887200", MICRON);
		testASample(1360, true, "13", YARD, "11887200", MICROMETER);
		
		// Series 14x0 - from Mile
		testASample(1410, true, "15", MILE, "950400000000", MIL);
		testASample(1420, true, "15", MILE, "950400", INCH);
		testASample(1430, true, "15", MILE, "15", MILE);
		testASample(1440, true, "15", MILE, "13.03464362851", NAUTICALMILE);
		testASample(1450, true, "15", MILE, "24140160000000", NANOMETER);
		testASample(1460, true, "15", MILE, "241401.6", DECIMETER);
		testASample(1460, true, "15", MILE, "24.14016", KILOMETER);
		
		// from Nautical Mile
		testASample(1510, true, "17", NAUTICALMILE, "1239527559055.1182", MIL);
		testASample(1520, true, "17", NAUTICALMILE, "1239527.5590551181", INCH);
		testASample(1530, true, "17", NAUTICALMILE, "19.5632506164", MILE);
		testASample(1540, true, "17", NAUTICALMILE, "3148400", CENTIMETER);
		
		// From Nanometer
		testASample(1550, true, "19", NANOMETER, "0.748031496063", MIL);
		testASample(1560, true, "19", NANOMETER, "0.000000019", METER);
		
		// From Micron
		testASample(1570, true, "21", MICRON, "826.771653543307", MIL);
		testASample(1580, true, "21", MICRON, "0.000000021", KILOMETER);
		
		// From Micrometer
		testASample(1590, true, "23", MICROMETER, "905.511811023622", MIL);
		testASample(1600, true, "23", MICROMETER, "0.000000023", KILOMETER);
		
		// From Milimeter
		testASample(1610, true, "25", MILLIMETER, "0.984251968504", INCH);
		testASample(1620, true, "25", MILLIMETER, "0.025", METER);
		
		// From Centimeter
		testASample(1630, true, "27", CENTIMETER, "10.629921259842", INCH);
		testASample(1640, true, "27", CENTIMETER, "0.27", METER);
		
		// From Decimeter
		testASample(1650, true, "29", DECIMETER, "114.173228346457", INCH);
		testASample(1660, true, "29", DECIMETER, "2.9", METER);
		
		// From Meter
		testASample(1670, true, "31", METER, "1220.472440944882", INCH);
		testASample(1680, true, "31", METER, "31", METER);
		
		// From Decameter
		testASample(1690, true, "33", DECAMETER, "12992.125984251968", INCH);
		testASample(1700, true, "33", DECAMETER, "330", METER);
		
		// From Hectometer
		testASample(1710, true, "35", HECTOMETER, "137795.27559055117", INCH);
		testASample(1720, true, "35", HECTOMETER, "3500", METER);
		
		// From Kilometer
		testASample(1730, true, "37", KILOMETER, "1456692.9133858269", INCH);
		testASample(1740, true, "37", KILOMETER, "37000", METER);
		
		
		// Series 2000, "2100", 2200 - Area
		// From SqMil
		testASample(2010, true, "5", SQMIL, "0.000000034722", SQFOOT);
		testASample(2020, true, "5", SQMIL, "0.000000003226", SQMETER);
		
		// From SqInch
		testASample(2030, true, "7", SQINCH, "0.048611111111", SQFOOT);
		testASample(2040, true, "7", SQINCH, "0.00451612", SQMETER);
		
		// From SqFoot
		testASample(2050, true, "9", SQFOOT, "9", SQFOOT);
		testASample(2060, true, "9", SQFOOT, "0.83612736", SQMETER);
		
		// From SqYd
		testASample(2070, true, "11", SQYARD, "99", SQFOOT);
		testASample(2080, true, "11", SQYARD, "9.19740096", SQMETER);
		
		// From Square
		testASample(2090, true, "13", SQUARE, "1300", SQFOOT);
		testASample(2100, true, "13", SQUARE, "120.773952", SQMETER);
		
		// From Acre
		testASample(2110, true, "15", ACRE, "653400", SQFOOT);
		testASample(2120, true, "15", ACRE, "60702.846336", SQMETER);
		
		// From SqMile
		testASample(2130, true, "17", SQMILE, "473932800", SQFOOT);
		testASample(2140, true, "17", SQMILE, "44029797.875712", SQMETER);
		
		// From Section
		testASample(2150, true, "19.1", SECTION, "532477440", SQFOOT);
		testASample(2160, true, "19.1", SECTION, "49468772.9074176", SQMETER);
		
		// From Township
		testASample(2170, true, "21", TOWNSHIP, "21076070400", SQFOOT);
		testASample(2180, true, "21", TOWNSHIP, "1958031011.414016", SQMETER);
		
		// From SqMilimeter
		testASample(2190, true, "23", SQMILLIMETER, "0.00024756994", SQFOOT);
		testASample(2200, true, "23", SQMILLIMETER, "0.000023", SQMETER);
		
		// From SqCentimeter
		testASample(2210, true, "25", SQCENTIMETER, "0.026909776042", SQFOOT);
		testASample(2220, true, "25", SQCENTIMETER, "0.0025", SQMETER);
		
		// From SqMeter
		testASample(2230, true, "27", SQMETER, "290.625581251162", SQFOOT);
		testASample(2240, true, "27", SQMETER, "27", SQMETER);
		
		// From SqKilometer
		testASample(2250, true, "29", SQKILOMETER, "312153402.084582", SQFOOT);
		testASample(2260, true, "29", SQKILOMETER, "29000000", SQMETER);
		
		// From Hectare
		testASample(2270, true, "31.12345678901", HECTARE, "3350101.007352397", SQFOOT);
		testASample(2280, true, "31.12345678901", HECTARE, "311234.5678901", SQMETER);
		
		// Series 2300, "2400", 2500, & 2600 - Volume
		// From CuInch
		testASample(2310, true, "5.012345", CUINCH, "0.000082137618", KILOLITER);
		
		// From CuFoot
		testASample(2330, true, "-7.012345", CUFOOT, "-0.198567497615", KILOLITER);
		
		// From CuYard
		testASample(2350, true, "9.012345", CUYARD, "6.890432151578", KILOLITER);
		
		// From CuMile
		testASample(2370, true, "-11.012345", CUMILE, "-45901456284.48144", KILOLITER);
		
		// From AcreFoot
		testASample(2390, true, "13.012345", ACREFOOT, "16050.491221402284", KILOLITER);
		
		// From FlOunce
		testASample(2410, true, "-15.012345", FLOUNCE, "-0.000443968029", KILOLITER);
		
		// From FlPint
		testASample(2430, true, "17.012345", FLPINT, "0.008049841405", KILOLITER);
		
		// From FlQuart
		testASample(2450, true, "-19.012345", FLQUART, "-0.017992388701", KILOLITER);
		
		// From FlGallon
		testASample(2470, true, "21.012345", FLGALLON, "0.079540378372", KILOLITER);
		
		
		// From MicroLiter
		testASample(2490, true, "-23.012345", MICROLITER, "-0.000006079218", FLGALLON);
		
		// From CuMilliMeter
		testASample(2510, true, "25.012345", CUMILLIMETER, "0.000006607562", FLGALLON);
		
		// From CuCentiMeter
		testASample(2530, true, "-27.012345", CUCENTIMETER, "-0.007135906618", FLGALLON);
		
		// From MilliLiter
		testASample(2550, true, "29.012345", MILLILITER, "0.007664250722", FLGALLON);
		
		// From Liter
		testASample(2570, true, "-31.012345", LITER, "-8.192594827089", FLGALLON);
		
		// From HectoLiter
		testASample(2590, true, "33.012345", HECTOLITER, "872.093893180526", FLGALLON);
		
		// From KiloLiter
		testASample(2600, true, "-35.012345", KILOLITER, "-9249.283036521556", FLGALLON);
		
		// From CuMeter
		testASample(2610, true, "37.012345", CUMETER, "9777.627141237854", FLGALLON);
		
		// From CuKiloMeter
		testASample(2620, true, "39.012345", CUKILOMETER, "10305971245954.15", FLGALLON);
		
		
		// Series 2700, "2800", & 2900 - Mass
		// From Carat
		testASample(2710, true, "-7.012345", CARAT, "-1.402469", GRAM);
		
		// From Ounce
		testASample(2720, true, "-7.012345", OUNCE, "-198.796636737978", GRAM);
		
		// From Pound
		testASample(2730, true, "-7.012345", POUND, "-3180.74618780765", GRAM);
		
		// From Stone
		testASample(2740, true, "-7.012345", STONE, "-44530.4466293071", GRAM);
		
		// From Kip
		testASample(2750, true, "-7.012345", KIP, "-3180746.18780765", GRAM);
		
		// From Short Ton
		testASample(2760, true, "-7.012345", TONSHORT, "-6361492.3756153", GRAM);
		
		// From Long Ton
		testASample(2770, true, "-7.012345", TONLONG, "-7124871.460689136", GRAM);
		
		
		// From Milligram
		testASample(2810, true, "-7.012345", MILLIGRAM, "-0.000247353191", OUNCE);
		
		// From Centigram
		testASample(2820, true, "-7.012345", CENTIGRAM, "-0.002473531907", OUNCE);
		
		// From Decigram
		testASample(2830, true, "-7.012345", DECIGRAM, "-0.024735319071", OUNCE);
		
		// From Gram
		testASample(2840, true, "-7.012345", GRAM, "-0.247353190707", OUNCE);
		
		// From Dekagram
		testASample(2850, true, "-7.012345", DEKAGRAM, "-2.473531907073", OUNCE);
		
		// From Hectogram
		testASample(2860, true, "-7.012345", HECTOGRAM, "-24.735319070733", OUNCE);
		
		// From Kilogram
		testASample(2870, true, "-7.012345", KILOGRAM, "-247.35319070733", OUNCE);
		
		// From Tonne
		testASample(2880, true, "-7.012345", TONNE, "-247353.19070733045", OUNCE);
		
		// Series 3200 - time
		// From MilliSecond
		testASample(3210, true, "37.0123456789", MILLISECOND, "0.000616872428", MINUTE);
		
		// From Second
		testASample(3220, true, "37.0123456789", SECOND, "0.616872427982", MINUTE);
		
		// From Minute
		testASample(3230, true, "37.0123456789", MINUTE, "2220.740740734", SECOND);
		
		// From Hour
		testASample(3240, true, "37.0123456789", HOUR, "133244.44444404", SECOND);
		
		// From Day
		testASample(3250, true, "37.0123456789", DAY, "3197866.66665696", SECOND);
		
		// From Week
		testASample(3260, true, "37.0123456789", WEEK, "22385066.66659872", SECOND);
		
		// From Month
		testASample(3270, true, "37.0123456789", MONTH, "97268444.4441492", SECOND);
		
		// From Year
		testASample(3280, true, "37.0123456789", YEAR, "1167221333.3297904", SECOND);
		
		
		// Series 3300, 3400 - temperature
		// this test series does all of the conversions
		// From Celsius
		testASample(3300, true, "37.0123456789", CELSIUS, "37.0123456789", CELSIUS);
		
		// From Celsius
		testASample(3310, true, "37.0123456789", CELSIUS, "98.62222222202", FAHRENHEIT);
		
		// From Celsius
		testASample(3320, true, "37.0123456789", CELSIUS, "12.214074074037", NEWTON);
		
		// From Celsius
		testASample(3330, true, "37.0123456789", CELSIUS, "310.1623456789", KELVIN);
		
		
		// From Fahrenheit
		testASample(3350, true, "37.0123456789", FAHRENHEIT, "2.784636488278", CELSIUS);
		
		// From Fahrenheit
		testASample(3360, true, "37.0123456789", FAHRENHEIT, "37.0123456789", FAHRENHEIT);
		
		// From Fahrenheit
		testASample(3370, true, "37.0123456789", FAHRENHEIT, "0.918930041132", NEWTON);
		
		// From Fahrenheit
		testASample(3380, true, "37.0123456789", FAHRENHEIT, "275.934636488278", KELVIN);
		
		
		// From Newton
		testASample(3400, true, "37.0123456789", NEWTON, "112.158623269394", CELSIUS);
		
		// From Newton
		testASample(3410, true, "37.0123456789", NEWTON, "233.885521884909", FAHRENHEIT);
		
		// From Newton
		testASample(3420, true, "37.0123456789", NEWTON, "37.0123456789", NEWTON);
		
		// From Newton
		testASample(3430, true, "37.0123456789", NEWTON, "385.308623269394", KELVIN);
		
		
		// From Kelvin
		testASample(3450, true, "37.0123456789", KELVIN, "-236.1376543211", CELSIUS);
		
		// From Kelvin
		testASample(3460, true, "37.0123456789", KELVIN, "-393.04777777798", FAHRENHEIT);
		
		// From Kelvin
		testASample(3470, true, "37.0123456789", KELVIN, "-77.925425925963", NEWTON);
		
		// From Kelvin
		testASample(3480, true, "37.0123456789", KELVIN, "37.0123456789", KELVIN);
		
		// Series 8000 Scalar
		// this test series does all of the conversions
		testASample(8000, true, "37.0123456789", SCALAR, "37.0123456789", SCALAR);
		
		
		Println("\n--- Fail Tests ---");
		//testASample(9010, false, "1245", KILOMETER, "0", KILOLITER);	// invalid conversion
		testASample(9020, false, "123.456", SCALAR, "0", MILLIMETER);		// invalid conversion
		
		
		
		Println("\n--- Tests Completed ---\n");
	
		
	}
	
	
}
