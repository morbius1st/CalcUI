package pro.cyberstudio.myapp;

import java.math.*;

import static pro.cyberstudio.myapp.UnitCategory.AREA;
import static pro.cyberstudio.myapp.UnitCategory.FOURTH;
import static pro.cyberstudio.myapp.UnitCategory.LENGTH;
import static pro.cyberstudio.myapp.UnitCategory.VOLUME;
import static pro.cyberstudio.myapp.UnitCategory2.*;

/**
 * @author jeffs
 *         File:    UnitType2
 *         Created: 3/4/2017 @ 8:41 AM
 *         Project: CalcUI
 */

public enum UnitType2 {

	// special UnitType - invalid
	INVALID		(VOID,		00, "", "", "", "", 0),

	// special UnitType - no unit i.e. scalar number or error
	SCALAR		(NOUNIT,	10, "", "", "", "", 0),

	// length UnitType
	// all length conversion factors are versus a millimeter
	// length UnitType codes must keep a 1 to 1 correspondence to area UnitType codes and to volume UnitType codes
	MIL			(DIMENSION,    10, "Mil", "Mils", "mil", "",						0.0000254),
	INCH		(DIMENSION,    20, "Inch", "Inches", "\"", "in",						 25.4),
	FOOT		(DIMENSION,    30, "Foot", "Feet", "\'", "ft",							304.8),
	FATHOM		(DIMENSION,    40, "Fathom", "Fathoms", "ftm", "",					   1828.8),
	YARD		(DIMENSION,    50, "Yard", "Yards", "yd", "",							914.4),
	MILE		(DIMENSION,    80, "Mile", "Miles", "mi", "",						1609344.0),
	NAUTICALMILE(DIMENSION,    90, "Nautical Mile", "Nautical Miles", "nmi", "",  1852000.0),

	NANOMETER	(DIMENSION,   520, "Nanometer", "Nanometers", "nm", "",				0.000001),
	MICROMETER	(DIMENSION,   530, "Micrometer", "Micrometers", "µm", "um",			0.001),		// same as micron
	MICRON		(DIMENSION,   532, "Micron", "Microns", "µm", "um",					0.001),		// same as micrometer
	MILLIMETER	(DIMENSION,   540, "Milimeter", "Milimeters", "mm", "",				1.0),
	CENTIMETER	(DIMENSION,   550, "Centimeter", "Centimeters", "cm", "",			   10.0),
	DECIMETER	(DIMENSION,   560, "Decimeter", "Decimeters", "dm", "",			  100.0),
	METER		(DIMENSION,   570, "Meter", "Meters", "m", "",						 1000.0),
	DECAMETER	(DIMENSION,   580, "Decameter", "Decameters", "dam", "",		    10000.0),
	HECTOMETER	(DIMENSION,   590, "Hectometer", "Hectometers", "hm", "",		   100000.0),
	KILOMETER	(DIMENSION,   600, "Kilometer", "Kilometers", "km", "",		  1000000.0),

	// area UnitType
	// all area conversion factors are versus a mm²
	// area UnitType codes must keep a 1 to 1 correspondence to length UnitType codes and to volume UnitType codes
	SQMIL		(DIMENSION,	    10, "Square mil", "Square mills", "mil²", "sq mil",				   0.00064516),
	SQINCH		(DIMENSION,	    20, "Square Inch", "Square Inches", "in²", "sq in",			     645.16),
	SQFOOT		(DIMENSION,	    30, "Square Foot", "Square Feet", "ft²", "sq ft",			   92903.04),
	SQYARD		(DIMENSION,	    50, "Square Yard", "Square Yards", "yd²", "sq yd",			  836127.36),
	SQUARE		(DIMENSION,	    60, "Square", "Squares", "sq", "",							 9290304.0),
	ACRE		(DIMENSION,      70, "Acre", "Acres", "ac", "",							  4046856422.4),
	SQMILE		(DIMENSION,	    80, "Square Mile", "Square Miles", "mi²", "sq mi",	   2589988110336.0),
	SECTION		(DIMENSION,	   100, "Section", "Sections", "", "",					   2589988110336.0),
	TOWNSHIP	(DIMENSION,	   110, "Township", "Townships", "twp", "",				  93239571972096.0),

	SQMILLIMETER (DIMENSION,	   540, "Square Milimeter", "Square Milimeters", "mm²", "",			    1.0),
	SQCENTIMETER (DIMENSION,	   550, "Square Centimeter", "Square Centimeters", "cm²", "",		  100.0),
	SQMETER		(DIMENSION,	   570, "Square Meter", "Square Meters", "m²", "",				  1000000.0),
	SQKILOMETER	(DIMENSION,	   600, "Square Kilometer", "Square Kilometers", "km²", "", 1000000000000.0),
	HECTARE		(DIMENSION,	   610, "Hectare", "Hectares", "ha", "",					  10000000000.0),

	// volume UnitType
	// all area conversion factors are versus a cm³
	// volume UnitType codes must keep a 1 to 1 correspondence to length UnitType codes and to area UnitType codes
	CUINCH		(DIMENSION,    20, "Cubic Inch", "Cubic Inches", "in³", "cu in",				  16.387064),
	CUFOOT		(DIMENSION,    30, "Cubic Foot", "Cubic Feet", "ft³", "cu ft",				   28316.846592),
	CUYARD		(DIMENSION,    50, "Cubic Yard", "Cubic Yards", "yd³", "cu yd",			  764554.857984),
	CUMILE		(DIMENSION,    80, "Cubic Mile", "Cubic Miles", "mi³", "cu mi",	4168181825440579.584),
	ACREFOOT	(DIMENSION,   120, "Acre-Foot", "Acre-Feet", "ac ft", "",				  1233481837.54752),
	FLOUNCE		(DIMENSION,   130, "Ounce", "Ounces","oz", "",									  29.5735295625),
	FLPINT		(DIMENSION,   140, "Pint", "Pints", "pt", "",									 473.176473),
	FLQUART		(DIMENSION,   150, "Quart", "Quarts", "qt", "",								 946.352946),
	FLGALLON	(DIMENSION,   160, "Gallon", "Gallon", "gal", "",								3785.411784),

	MICROLITER	(DIMENSION,   620, "Microliter", "Microliters", "µl", "µL",							   0.001),
	CUMILLIMETER (DIMENSION,  540, "Cubic Millimeter", "Cubic Millimeters", "mm³", "cu mm",			   0.001),
	CUCENTIMETER (DIMENSION,  550, "Cubic Centimeter", "Cubic Centimeters", "cm³", "cu cm",			   1.0),
	MILLILITER	(DIMENSION,   630, "Millileter", "Millilitters", "mL", "",								   1.0),
	LITER		(DIMENSION,   640, "Liter", "Liters", "L³", "",										1000.0),
	HECTOLITER	(DIMENSION,   650, "Hectoliter", "Hectoliters", "hl", "hL",						  100000.0),
	KILOLITER	(DIMENSION,   660, "Kiloliter", "Kiloliters", "kl", "kL",							 1000000.0),
	CUMETER		(DIMENSION,   570, "Cubic Meter", "Cubic Meters", "m³", "cu m",					 1000000.0),
	CUKILOMETER	(DIMENSION,   600, "Cubic Kilometer", "Cubic Kilometers", "km³", "cu mm",	1000000000000000.0),

	// fourth UnitType
	// all fourth conversion factors are versus in⁴
	MOMENTOFINERTIA (DIMENSION,  20, "Moment of Inertia", "Moment of Inertia", "in⁴", "",		1.0),	// in⁴

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

	private final UnitCategory2 utUnitCategory;
	private final int altUnits;
	private final String strCommonNameSingular;
	private final String strCommonNamePlural;
	private final String strCommonAbbr;
	private final String strAltAbbr;
	private final BigDecimal bdConvertFactor;

	private static final int SINGULAR = 1;
	private static final int PLURAL = 2;

	UnitType2(UnitCategory2 ucUC, int aU, String sCNS, String sCNP, String sCA, String sAA, double dCF) {

		utUnitCategory = ucUC;
		altUnits = aU;
		strCommonNameSingular = sCNS;
		strCommonNamePlural = sCNP;
		strCommonAbbr = sCA;
		strAltAbbr = sAA;
		bdConvertFactor = BigDecimal.valueOf(dCF);
		bdConvertFactor.setScale(14, RoundingMode.HALF_UP);
	}


	enum UnitDescription {

		// special UnitType - invalid
		INVALIDdesc		("", "", "", ""),

		// special UnitType - no unit i.e. scalar number or error
		SCALARdesc		("", "", "", ""),

		// length UnitType
		// all length conversion factors are versus a millimeter
		// length UnitType codes must keep a 1 to 1 correspondence to area UnitType codes and to volume UnitType codes
		MILdesc			("mil", "","Mil", "Mils"),
		INCHdesc		("\"", "in","Inch", "Inches"),
		FOOTdesc		("\'", "ft","Foot", "Feet"),
		FATHOMdesc		("ftm", "","Fathom", "Fathoms"),
		YARDdesc		("yd", "","Yard", "Yards"),
		MILEdesc		("mi", "","Mile", "Miles"),
		NAUTICALMILEdesc("nmi", "","Nautical Mile", "Nautical Miles"),
		NANOMETERdesc	("nm", "","Nanometer", "Nanometers"),
		MICROMETERdesc	("µm", "um","Micrometer", "Micrometers"),
		MICRONdesc		("µm", "um","Micron", "Microns"),
		MILLIMETERdesc	("mm", "","Milimeter", "Milimeters"),
		CENTIMETERdesc	("cm", "","Centimeter", "Centimeters"),
		DECIMETERdesc	("dm", "","Decimeter", "Decimeters"),
		METERdesc		("m", "","Meter", "Meters"),
		DECAMETERdesc	("dam", "","Decameter", "Decameters"),
		HECTOMETERdesc	("hm", "","Hectometer", "Hectometers"),
		KILOMETERdesc	("km", "","Kilometer", "Kilometers" ),

		// area UnitType
		// all area conversion factors are versus a mm²
		// area UnitType codes must keep a 1 to 1 correspondence to length UnitType codes and to volume UnitType codes
		SQMILdesc		("mil²", "sq mil" ,"Square mil", "Square mills"),
		SQINCHdesc		("in²", "sq in" ,"Square Inch", "Square Inches"),
		SQFOOTdesc		("ft²", "sq ft" ,"Square Foot", "Square Feet"),
		SQYARDdesc		("yd²", "sq yd" ,"Square Yard", "Square Yards"),
		SQUAREdesc		("sq", "" ,"Square", "Squares"),
		ACREdesc		("ac", "" ,"Acre", "Acres"),
		SQMILEdesc		("mi²", "sq mi" ,"Square Mile", "Square Miles"),
		SECTIONdesc		("section", "" ,"Section", "Sections"),
		TOWNSHIPdesc	("twp", "" ,"Township", "Townships"),
		SQMILLIMETERdesc("mm²", "" ,"Square Milimeter", "Square Milimeters"),
		SQCENTIMETERdesc("cm²", "","Square Centimeter", "Square Centimeters"),
		SQMETERdesc		("m²", "","Square Meter", "Square Meters"),
		SQKILOMETERdesc	("km²", "","Square Kilometer", "Square Kilometers"),
		HECTAREdesc		("ha", "" ,"Hectare", "Hectares"),

		// volume UnitType
		// all area conversion factors are versus a cm³
		// volume UnitType codes must keep a 1 to 1 correspondence to length UnitType codes and to area UnitType codes
		CUINCHdesc		("in³", "cu in" ,"Cubic Inch", "Cubic Inches"),
		CUFOOTdesc		("ft³", "cu ft" ,"Cubic Foot", "Cubic Feet"),
		CUYARDdesc		("yd³", "cu yd" ,"Cubic Yard", "Cubic Yards"),
		CUMILEdesc		("mi³", "cu mi" ,"Cubic Mile", "Cubic Miles"),
		ACREFOOTdesc	("ac ft", "" ,"Acre-Foot", "Acre-Feet"),
		FLOUNCEdesc		("oz", "" ,"Ounce", "Ounces"),
		FLPINTdesc		("pt", "" ,"Pint", "Pints"),
		FLQUARTdesc		("qt", "" ,"Quart", "Quarts"),
		FLGALLONdesc	("gal", "" ,"Gallon", "Gallon"),
		MICROLITERdesc	("µl", "µL" ,"Microliter", "Microliters"),
		CUMILLIMETERdesc("mm³", "cu mm" ,"Cubic Millimeter", "Cubic Millimeters"),
		CUCENTIMETERdesc("cm³", "cu cm" ,"Cubic Centimeter", "Cubic Centimeters"),
		MILLILITERdesc	("mL", "" ,"Millileter", "Millilitters"),
		LITERdesc		("L³", "" ,"Liter", "Liters"),
		HECTOLITERdesc	("hl", "hL" ,"Hectoliter", "Hectoliters"),
		KILOLITERdesc	("kl", "kL" ,"Kiloliter", "Kiloliters"),
		CUMETERdesc		("m³", "cu m" ,"Cubic Meter", "Cubic Meters"),
		CUKILOMETERdesc	("km³", "cu mm" ,"Cubic Kilometer", "Cubic Kilometers"),

		// fourth UnitType
		// all fourth conversion factors are versus in⁴
		MOMENTOFINERTIAdesc ("in⁴", "","Moment of Inertia", "Moment of Inertia"),	// in⁴

		// mass UnitType
		// all mass conversion factors are versus a milligram
		CARATdesc		("ct", "", "Carat", "Carats"),
		OUNCEdesc		("oz", "", "Ounce", "Ounces"),
		POUNDdesc		("lb", "", "Pound", "Pounds"),
		STONEdesc		("st", "", "Stone", "Stones"),
		KIPdesc			("kip", "", "Kip", "Kips"),
		TONSHORTdesc	("ton", "", "Ton", "Tons"),
		TONLONGdesc		("", "", "Long Ton", "Long Tons"),
		MILLIGRAMdesc	("mg", "", "Milligram", "Milligrams"),
		CENTIGRAMdesc	("cg", "", "Centigram", "Centigrams"),
		DECIGRAMdesc	("dg", "", "Decigram", "Decigrams"),
		GRAMdesc		("g", "", "Gram", "Grams"),
		DEKAGRAMdesc	("dkg", "", "Dekagram", "Dekagrams"),
		HECTOGRAMdesc	("hg", "", "Hectogram", "Hectograms"),
		KILOGRAMdesc	("kg", "", "Kilogram", "Kilograms"),
		TONNEdesc		("t", "","Tonne", "Tonnes"),

		// time UnitType
		// all time conversions are factors versus a second "s"
		MILLISECONDdesc	("ms", "", "Millisecond", "Milliseconds"),
		SECONDdesc		("s", "", "Second", "Seconds"),
		MINUTEdesc		("m", "", "Minute", "Minutes"),
		HOURdesc		("h", "", "Hour", "Hours"),
		DAYdesc			("d", "", "Day", "Days"),
		WEEKdesc		("w", "", "Week", "Weeks"),
		MONTHdesc		("m", "", "Month", "Months"),
		YEARdesc		("y", "", "Year", "Years"),

		// temperature UnitType
		// all temperature conversions are based on a degree kelvin "K"
		// temperature conversion factors have special conversion codes <0 & >-10
		CELSIUSdesc		("°C", "", "Celsius", "Celsius"),
		FAHRENHEITdesc	("°F", "", "Fahrenheit", "Fahrenheit"),
		NEWTONdesc		("°N", "", "Newton", "Newton"),
		KELVINdesc		("K", "", "Kelvin", "Kelvin");


		private final String strCommonNameSingular;
		private final String strCommonNamePlural;
		private final String strCommonAbbr;
		private final String strAltAbbr;

		UnitDescription(String cA, String aA, String cNms, String cNmp) {
			strCommonAbbr = cA;
			strAltAbbr = aA;
			strCommonNameSingular = cNms;
			strCommonNamePlural = cNmp;
		}

	}


}
