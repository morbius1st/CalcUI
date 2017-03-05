package pro.cyberstudio.myapp;

import static pro.cyberstudio.myapp.Unit1Category.*;
import java.math.*;

/**
 * Created by Jeff on 6/28/2014.
 * version 3.1
 * This enum provides the information for UnitType's and their conversion
 */

public enum Unit1Type {
	// special UnitType - invalid
	INVALID		(VOID,		00, "", "", "", "", 0),

	// special UnitType - no unit i.e. scalar number or error
	SCALAR		(NOUNIT,	10, "", "", "", "", 0),

	// length UnitType
	// all length conversion factors are versus a millimeter
	// length UnitType codes must keep a 1 to 1 correspondence to area UnitType codes and to volume UnitType codes
	MIL			(LENGTH,    10, "Mil", "Mils", "mil", "",							  0.0000254),
	INCH		(LENGTH,    20, "Inch", "Inches", "\"", "in",						 25.4),
	FOOT		(LENGTH,    30, "Foot", "Feet", "\'", "ft",							304.8),
	FATHOM		(LENGTH,    40, "Fathom", "Fathoms", "ftm", "",					   1828.8),
	YARD		(LENGTH,    50, "Yard", "Yards", "yd", "",							914.4),
	MILE		(LENGTH,    80, "Mile", "Miles", "mi", "",						1609344.0),
	NAUTICALMILE(LENGTH,    90, "Nautical Mile", "Nautical Miles", "nmi", "",  1852000.0),

	NANOMETER	(LENGTH,   520, "Nanometer", "Nanometers", "nm", "",				0.000001),
	MICROMETER	(LENGTH,   530, "Micrometer", "Micrometers", "µm", "um",			0.001),		// same as micron
	MICRON		(LENGTH,   532, "Micron", "Microns", "µm", "um",					0.001),		// same as micrometer
	MILLIMETER	(LENGTH,   540, "Milimeter", "Milimeters", "mm", "",				1.0),
	CENTIMETER	(LENGTH,   550, "Centimeter", "Centimeters", "cm", "",			   10.0),
	DECIMETER	(LENGTH,   560, "Decimeter", "Decimeters", "dm", "",			  100.0),
	METER		(LENGTH,   570, "Meter", "Meters", "m", "",						 1000.0),
	DECAMETER	(LENGTH,   580, "Decameter", "Decameters", "dam", "",		    10000.0),
	HECTOMETER	(LENGTH,   590, "Hectometer", "Hectometers", "hm", "",		   100000.0),
	KILOMETER	(LENGTH,   600, "Kilometer", "Kilometers", "km", "",		  1000000.0),

	// area UnitType
	// all area conversion factors are versus a mm²
	// area UnitType codes must keep a 1 to 1 correspondence to length UnitType codes and to volume UnitType codes
	SQMIL		(AREA,	    10, "Square mil", "Square mills", "mil²", "sq mil",				   0.00064516),
	SQINCH		(AREA,	    20, "Square Inch", "Square Inches", "in²", "sq in",			     645.16),
	SQFOOT		(AREA,	    30, "Square Foot", "Square Feet", "ft²", "sq ft",			   92903.04),
	SQYARD		(AREA,	    50, "Square Yard", "Square Yards", "yd²", "sq yd",			  836127.36),
	SQUARE		(AREA,	    60, "Square", "Squares", "sq", "",							 9290304.0),
	ACRE		(AREA,      70, "Acre", "Acres", "ac", "",							  4046856422.4),
	SQMILE		(AREA,	    80, "Square Mile", "Square Miles", "mi²", "sq mi",	   2589988110336.0),
	SECTION		(AREA,	   100, "Section", "Sections", "", "",					   2589988110336.0),
	TOWNSHIP	(AREA,	   110, "Township", "Townships", "twp", "",				  93239571972096.0),

	SQMILLIMETER (AREA,	   540, "Square Milimeter", "Square Milimeters", "mm²", "",			    1.0),
	SQCENTIMETER (AREA,	   550, "Square Centimeter", "Square Centimeters", "cm²", "",		  100.0),
	SQMETER		(AREA,	   570, "Square Meter", "Square Meters", "m²", "",				  1000000.0),
	SQKILOMETER	(AREA,	   600, "Square Kilometer", "Square Kilometers", "km²", "", 1000000000000.0),
	HECTARE		(AREA,	   610, "Hectare", "Hectares", "ha", "",					  10000000000.0),

	// volume UnitType
	// all area conversion factors are versus a cm³
	// volume UnitType codes must keep a 1 to 1 correspondence to length UnitType codes and to area UnitType codes
	CUINCH		(VOLUME,    20, "Cubic Inch", "Cubic Inches", "in³", "cu in",				  16.387064),
	CUFOOT		(VOLUME,    30, "Cubic Foot", "Cubic Feet", "ft³", "cu ft",				   28316.846592),
	CUYARD		(VOLUME,    50, "Cubic Yard", "Cubic Yards", "yd³", "cu yd",			  764554.857984),
	CUMILE		(VOLUME,    80, "Cubic Mile", "Cubic Miles", "mi³", "cu mi",	4168181825440579.584),
	ACREFOOT	(VOLUME,   120, "Acre-Foot", "Acre-Feet", "ac ft", "",				  1233481837.54752),
	FLOUNCE		(VOLUME,   130, "Ounce", "Ounces","oz", "",									  29.5735295625),
	FLPINT		(VOLUME,   140, "Pint", "Pints", "pt", "",									 473.176473),
	FLQUART		(VOLUME,   150, "Quart", "Quarts", "qt", "",								 946.352946),
	FLGALLON	(VOLUME,   160, "Gallon", "Gallon", "gal", "",								3785.411784),

	MICROLITER	(VOLUME,   620, "Microliter", "Microliters", "µl", "µL",							   0.001),
	CUMILLIMETER (VOLUME,  540, "Cubic Millimeter", "Cubic Millimeters", "mm³", "cu mm",			   0.001),
	CUCENTIMETER (VOLUME,  550, "Cubic Centimeter", "Cubic Centimeters", "cm³", "cu cm",			   1.0),
	MILLILITER	(VOLUME,   630, "Millileter", "Millilitters", "mL", "",								   1.0),
	LITER		(VOLUME,   640, "Liter", "Liters", "L³", "",										1000.0),
	HECTOLITER	(VOLUME,   650, "Hectoliter", "Hectoliters", "hl", "hL",						  100000.0),
	KILOLITER	(VOLUME,   660, "Kiloliter", "Kiloliters", "kl", "kL",							 1000000.0),
	CUMETER		(VOLUME,   570, "Cubic Meter", "Cubic Meters", "m³", "cu m",					 1000000.0),
	CUKILOMETER	(VOLUME,   600, "Cubic Kilometer", "Cubic Kilometers", "km³", "cu mm",	1000000000000000.0),

	// fourth UnitType
	// all fourth conversion factors are versus in⁴
	MOMENTOFINERTIA (FOURTH,  20, "Moment of Inertia", "Moment of Inertia", "in⁴", "",		1.0),	// in⁴

	// mass UnitType
	// all mass conversion factors are versus a milligram
	CARAT		(MASS,	    10, "Carat", "Carats", "ct", "",					  200.0),
	OUNCE		(MASS,	    20, "Ounce", "Ounces", "oz", "",					28349.523125),
	POUND		(MASS,	    30, "Pound", "Pounds", "lb", "",				   453592.37),
	STONE		(MASS,	    40, "Stone", "Stones", "st", "",				  6350293.18),
	KIP			(MASS,	    50, "Kip", "Kips", "kip", "",					453592370.0),
	TONSHORT	(MASS,	    60, "Ton", "Tons", "ton", "",					907184740.0),
	TONLONG		(MASS,	    70, "Long Ton", "Long Tons", "", "",		   1016046908.8),

	MILLIGRAM	(MASS,	   540, "Milligram", "Milligrams", "mg", "",				1.0),
	CENTIGRAM	(MASS,	   550, "Centigram", "Centigrams", "cg", "",			   10.0),
	DECIGRAM	(MASS,	   560, "Decigram", "Decigrams", "dg", "",				  100.0),
	GRAM		(MASS,	   570, "Gram", "Grams", "g", "",						 1000.0),
	DEKAGRAM	(MASS,	   580, "Dekagram", "Dekagrams", "dkg", "",				10000.0),
	HECTOGRAM	(MASS,	   590, "Hectogram", "Hectograms", "hg", "",		   100000.0),
	KILOGRAM	(MASS,	   600, "Kilogram", "Kilograms", "kg", "",			  1000000.0),
	TONNE		(MASS,	   670, "Tonne", "Tonnes", "t", "",				   1000000000.0),

	// density UnitType
	// all density conversions are factors versus kg/m³
	OUNCEPERCUFOOT		(DENSITY,	  10, "Ounce per Cubic Foot", "Ounce per Cubic Feet", "oz/ft³", "",			   1.00115396087251),
	OUNCEPERCUINCH		(DENSITY,	  20, "Ounce per Cubic Inch", "Ounce per Cubic Inches", "oz/in³", "",		1729.9940443877),
	OUNCEPERGALLON		(DENSITY,	  30, "Ounce per Gallon", "Ounce per Gallons", "oz/gal", "",				   7.48915170730604),
	POUNDPERCUFOOT		(DENSITY,	  40, "Pound per Cubic Foot", "Pound per Cubic Feet", "lb/ft³", "",			  16.0184633739601),
	POUNDPERCUINCH		(DENSITY,	  50, "Pound per Cubic Inch", "Pound per Cubic Inches", "lb/in³", "",	   27679.9047102031),
	POUNDPERGALLON		(DENSITY,	  60, "Pound per Gallon", "Pound per Gallons", "lb/gal", "",				 119.826427316897),
	GRAMPERMILLILITER	(DENSITY,	 510, "Gram per Milliliter", "Gram per Milliliters", "g/mL", "",			1000.0),
	KILOGRAMPERCUMETER	(DENSITY,	 520, "Kilogram per Cubic Meter", "Kilogram per Cubic Meters", "kg/m³", "",	   1.0),
	KILOGRAMPERLITER	(DENSITY,	 530, "Kilogram per Liter", "Kilogram per Liters", "kg/L", "",				1000.0),

	// time UnitType
	// all time conversions are factors versus a second "s"
	MILLISECOND	(TIME,	 10, "Millisecond", "Milliseconds", "ms", "",		   0.001),
	SECOND		(TIME,	 20, "Second", "Seconds", "", "s",					   1.0),
	MINUTE		(TIME,	 30, "Minute", "Minutes", "", "m",					  60.0),
	HOUR		(TIME,	 40, "Hour", "Hours", "", "h",						3600.0),
	DAY			(TIME,	 50, "Day", "Days", "", "d",					   86400.0),
	WEEK		(TIME,	 60, "Week", "Weeks", "", "w",					  604800.0),
	MONTH		(TIME,	 70, "Month", "Months", "", "m",				 2628000.0),
	YEAR		(TIME,	 80, "Year", "Years", "", "y",					31536000.0),

	// temperature UnitType
	// all temperature conversions are based on a degree kelvin "K"
	// temperature conversion factors have special conversion codes <0 & >-10
	CELSIUS		(TEMPERATURE,	 10, "Celsius", "Celsius", "°C", "",			-1.0),
	FAHRENHEIT	(TEMPERATURE,	 20, "Fahrenheit", "Fahrenheit", "°F", "",		-2.0),
	NEWTON		(TEMPERATURE,	 30, "Newton", "Newton", "°N", "",				-3.0),
	KELVIN		(TEMPERATURE,	 40, "Kelvin", "Kelvin", "K", "",				-4.0);

	private final Unit1Category utUnit1Category;
	private final int intUnitTypeCode;
	private final String strCommonNameSingular;
	private final String strCommonNamePlural;
	private final String strCommonAbbr;
	private final String strAltAbbr;
	private final BigDecimal bdConvertFactor;

	private static final int SINGULAR = 1;
	private static final int PLURAL = 2;

	Unit1Type(Unit1Category ucUC, int iUTCode, String sCNS, String sCNP, String sCA, String sAA, double dCF) {

		utUnit1Category = ucUC;
//		intUnitTypeCode = signum(iUTCode) * (Math.abs(iUTCode) + ucUC.getCategoryCode());
		intUnitTypeCode = iUTCode + ucUC.getCategoryCode();
		strCommonNameSingular = sCNS;
		strCommonNamePlural = sCNP;
		strCommonAbbr = sCA;
		strAltAbbr = sAA;
		bdConvertFactor = BigDecimal.valueOf(dCF);
		bdConvertFactor.setScale(14, RoundingMode.HALF_UP);
	}

	// get the UnitType's category
	public Unit1Category getUnitCategory() {
		return utUnit1Category;
	}

	// get the UnitType's full name - valid for all unit types
	public String getName(int intQty) {
		if (intQty == 1)
			return strCommonNameSingular;
		else
			return strCommonNamePlural;
	}

	// get the UnitType's common abbreviation - valid for all unit types
	public String getAbbr() {
		return strCommonAbbr;
	}

	/**
	 * Return the UnitType's alternate abbreviation - valid for all unit types
	 */
	public String getAltAbbr() {
		return strAltAbbr;
	}

	/**
	 * Return the UnitTypes's conversion factor as a big decimal
	 */
	private BigDecimal getConvertFactor() {
		return bdConvertFactor;
	}

	/**
	 * Private: Return the UnitType Code
	 */
	private int getUnitTypeCode() {
		return intUnitTypeCode;
	}

	/**
	 * method to get the UnitType of a promoted UnitType <>
	 * e.g. if the UnitType is Foot the promoted UnitType would be Square Foot. <>
	 * Returns UnitType == Invalid for no Promote UnitType
	 */
	private static Unit1Type getPromoteUnitTypeInternal(Unit1Type utUT1, int intPromoteLevelAmount) {
		int intPromoteUnitTypeCode;		// the calculated UnitTypeCode

		int intPromoteCatCode = utUT1.getUnitCategory().getPromoteCategoryCode();
		int intPrimaryCatCode = utUT1.getUnitCategory().getCategoryCode();
		int intPrimaryLevelCode = utUT1.getUnitCategory().getLevelCode();
		int intPrimaryUnitTypeCode = utUT1.getUnitTypeCode();

		// calc the proposed UnitTypeCode
		intPromoteUnitTypeCode = (intPrimaryUnitTypeCode - intPrimaryCatCode) +
				(((intPrimaryLevelCode + intPromoteLevelAmount) - 1 ) * Unit1Category.LEVELSEPARATION) +
				intPromoteCatCode;

		// this will return the promoted UnitType or, if one does not exist, "invalid"
		return Unit1Type.getMemberByCode(intPromoteUnitTypeCode);
	}

	/**
	 * method to get the UnitType of a promoted UnitType <>
	 * e.g. if the UnitType is Foot the promoted UnitType would be Square Foot. <>
	 * Returns UnitType == Invalid for no Promote UnitType
	 * provide a primary UnitType and <u>secondary UnitType</u>
	 * Note that the UnitCategories of both must be the same if the PromoteCatCode > 0
	 */
	public static Unit1Type getPromoteUnitType(Unit1Type utUT1, Unit1Type utUT2) {

		int intPromoteCatCode1 = utUT1.getUnitCategory().getPromoteCategoryCode();
		int intPromoteCatCode2 = utUT2.getUnitCategory().getPromoteCategoryCode();

		// process simple promotes
		if (intPromoteCatCode1 == 0) {
			return utUT2;
		}
		if (intPromoteCatCode2 == 0) {
			return utUT1;
		}
		if ((intPromoteCatCode1 < 0) || (intPromoteCatCode1 != intPromoteCatCode2)) {
			return INVALID;
		}

		return getPromoteUnitTypeInternal(utUT1, utUT2.getUnitCategory().getLevelCode());
	}

	/**
	 * method to get the UnitType of a promoted UnitType <>
	 * e.g. if the UnitType is Foot the promoted UnitType would be Square Foot. <>
	 * Returns UnitType == Invalid for no Promote UnitType
	 * provide a primary UnitType and the <u>Level increase</u>
	 */
	public static Unit1Type getPromoteUnitType(Unit1Type utUT1, int intLC) {

		int intPromoteCatCode = utUT1.getUnitCategory().getPromoteCategoryCode();

		if(intPromoteCatCode == 0) {
			return SCALAR;
		} if (intPromoteCatCode < 0) {
			return INVALID;
		}

		return getPromoteUnitTypeInternal(utUT1, intLC);
	}

	/**method to get the UnitType of a Demoted UnitType -
	 * for example, if the UnitType is Square Foot is demoted one level
	 * the UnitType would be Foot. <>
	 * Returns UnitType == Invalid for no Demote UnitType
	 * @param utUT1 The UnitType to be demoted
	 * @param intDemoteLevelAmount The amount decrease the current level
	 */
	public static Unit1Type getDemoteUnitType(Unit1Type utUT1, int intDemoteLevelAmount) {
		int intDemoteUnitTypeCode;		// calculated UnitTypeCode

		int intPrimaryLevelCode = utUT1.getUnitCategory().getLevelCode();

		intDemoteUnitTypeCode = intPrimaryLevelCode - intDemoteLevelAmount;

		if ((intDemoteUnitTypeCode == 0) || (utUT1 == SCALAR)) {
			return SCALAR;
		} else if (intDemoteUnitTypeCode < 0) {
			return INVALID;
		}

		int intDemoteCatCode = utUT1.getUnitCategory().getDemoteCategoryCode();
		int intPrimaryCatCode = utUT1.getUnitCategory().getCategoryCode();
		int intPrimaryUnitTypeCode = utUT1.getUnitTypeCode();

		// get here when the Demote level is greater than 0
		// calculate the final UnitTypeCode
		intDemoteUnitTypeCode = (intPrimaryUnitTypeCode - intPrimaryCatCode) +
				((intDemoteUnitTypeCode - 1) * Unit1Category.LEVELSEPARATION) +
				intDemoteCatCode;

		// this will return the promoted UnitType or, if one does not exist, "invalid"
		return Unit1Type.getMemberByCode(intDemoteUnitTypeCode);
	}


	/**method to get the UnitType of a Demoted UnitType -
	 * that is, if the UnitType is Square Foot
	 * the demoted UnitType would be Foot. <>
	 * Returns UnitType == Invalid for no Demote UnitType
	 */
	public static Unit1Type getDemoteUnitType(Unit1Type utUT1, Unit1Type utUT2) {

		// process simple demotes
		if (utUT2 == SCALAR) {
			return utUT1;
		}
		if (utUT1.getUnitCategory().getCategoryCode() ==
				utUT2.getUnitCategory().getCategoryCode()) {
			return SCALAR;
		}
		if ((utUT1.getUnitCategory().getDemoteCategoryCode() <= 0) ||
				(utUT2.getUnitCategory().getDemoteCategoryCode() <= 0)) {
			return INVALID;
		}
		return getDemoteUnitType(utUT1, utUT2.getUnitCategory().getLevelCode());
	}


	// determine if 'String' is a valid unit type
	// don't convert to upper case as there are
	// some units in which the upper case and lower case
	// forms are different units
	public static boolean isMemberByName(String s) {
		for (Unit1Type e : Unit1Type.values()) {
			if ((s.equals(e.getAbbr())) ||
					(s.equalsIgnoreCase(e.getName(SINGULAR))) ||
					(s.equalsIgnoreCase(e.getName(PLURAL))) ||
					(s.equals(e.getAltAbbr()))) {
				return true;
			}
		}
		return false;
	}

	// return the enum member given a 'String'
	// don't convert to upper case as there are
	// some units in which the upper case and lower case
	// forms are different units
	public static Unit1Type getMemberByName(String s) {
		for (Unit1Type e : Unit1Type.values()) {
			if ((s.equals(e.getAbbr())) ||
					(s.equalsIgnoreCase(e.getName(SINGULAR))) ||
					(s.equalsIgnoreCase(e.getName(PLURAL))) ||
					(s.equals(e.getAltAbbr()))) {
				return e;
			}
		}
		return INVALID;
	}

	public static Unit1Type getMemberByCode(int c) {
		for (Unit1Type u : Unit1Type.values()) {
			if ((c == u.getUnitTypeCode()))
				return u;
		}
		return INVALID;
	}

	/**Convert a double from one UnitType to a different UnitType
	 * Both inUnitType and outUnitType must be of the same UnitCategory
	 *
	 * @param valueIn the Value to convert
	 * @param unitIN the inUnitType (the UnitType of the Value)
	 * @param unitOUT the outUnitType (the desired UnitType)
	 * @return A double converted to the outUnitType or NaN if the conversion is invalid
	 */
	public static double ConvertUnit(double valueIn, Unit1Type unitIN, Unit1Type unitOUT) {
		BigDecimal bdTemp;

		if (unitIN.getUnitCategory() == unitOUT.getUnitCategory()) {
			// get here when both in and out units are of the same type
			// do both units have a simple conversion factor?
			if (( unitIN.getConvertFactor().signum() > 0) && (unitOUT.getConvertFactor().signum() > 0)) {
				//return (valueIn * (unitIN.getConvertFactor()/unitOUT.getConvertFactor()));

				bdTemp = unitIN.getConvertFactor().divide(unitOUT.getConvertFactor(), 14, RoundingMode.HALF_UP);

				bdTemp = BigDecimal.valueOf(valueIn).multiply(bdTemp);

				return bdTemp.doubleValue();

			} else {
				// get here when at least one of the numbers does not have a simple
				// conversion factor
				// later add code to deal with the special conversion factors

				if (!(unitIN.getConvertFactor().equals(unitOUT.getConvertFactor()))) {
					// if the convert factors do not match - we need to process the
					// conversion - if they do match - the in and out units are the
					// same so no conversion need take place - special case

					// first, process the in conversion to the common value
					switch (unitIN.getConvertFactor().intValue()) {
						case -1:
							// in unit is temperature:Celsius - convert TO Kelvin
							valueIn += 273.15;
							break;
						case -2:
							// in unit is temperature:Fahrenheit - convert TO Kelvin
							valueIn = (valueIn + 459.67) * (5./9.);
							break;
						case -3:
							// in unit is temperature:Newton
							valueIn = (valueIn * (100./33.)) + 273.15;
							break;
						case -4:
							break;

					}

					// last, process to the requested out value
					switch (unitOUT.getConvertFactor().intValue()) {
						case -1:
							// out unit is temperature:Celsius - convert FROM Kelvin
							valueIn -= 273.15;
							break;
						case -2:
							// out unit is temperature:Fahrenheit - convert FROM Kelvin
							valueIn = (valueIn * (9./5.)) - 459.67;
							break;
						case -3:
							// out unit is temperature:Newton - convert FROM Kelvin
							valueIn = (33./100.) * (valueIn - 273.15);
							break;
						case -4:
							break;
					}
				}

				return valueIn;
			}
		} else {
			// get here when IN unit class does not match OUT unit class

			// special case - if valueIn is 0 (zero), conversion does not make sense,
			// return 0
			if (valueIn == 0.0)
				return valueIn;


			return Double.NaN;
		}
	}
}