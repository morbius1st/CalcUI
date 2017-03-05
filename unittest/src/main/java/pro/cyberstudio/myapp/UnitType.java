package pro.cyberstudio.myapp;

import java.math.*;

import static pro.cyberstudio.myapp.UnitCategory.*;
import static pro.cyberstudio.myapp.UnitDescription.*;
import static pro.cyberstudio.myapp.UnitType.SpecialUnitConversions.*;

/**
 * @author jeffs
 *         File:    UnitType2
 *         Created: 3/4/2017 @ 8:41 AM
 *         Project: CalcUI
 */

// note that ranking comparison is based on the ordinal value of the units along with the
// ordinal value of the unitCategory

public enum UnitType {

	// special UnitType - invalid
	INVALID(VOID, INVALIDdesc, 0),

	// special UnitType - no unit i.e. scalar number or error
	SCALAR(NOUNIT, SCALARdesc, 0),

	// length UnitType
	// all length conversion factors are versus a millimeter
	// length UnitType codes must keep a 1 to 1 correspondence to area UnitType codes and to volume UnitType codes
	MIL(LENGTH, MILdesc, 0.0000254), // mil
	INCH(LENGTH, INCHdesc, 25.4),
	FOOT(LENGTH, FOOTdesc, 304.8),
	FATHOM(LENGTH, FATHOMdesc, 1828.8),
	YARD(LENGTH, YARDdesc, 914.4),
	MILE(LENGTH, MILEdesc, 1609344.0),
	NAUTICALMILE(LENGTH, NAUTICALMILEdesc, 1852000.0),
	NANOMETER(LENGTH, NANOMETERdesc, 0.000001),
	MICROMETER(LENGTH, MICROMETERdesc, 0.001),        // same as micron
	MICRON(LENGTH, MICRONdesc, 0.001),        // same as micrometer
	MILLIMETER(LENGTH, MILLIMETERdesc, 1.0),
	CENTIMETER(LENGTH, CENTIMETERdesc, 10.0),
	DECIMETER(LENGTH, DECIMETERdesc, 100.0),
	METER(LENGTH, METERdesc, 1000.0),
	DECAMETER(LENGTH, DECAMETERdesc, 10000.0),
	HECTOMETER(LENGTH, HECTOMETERdesc, 100000.0),
	KILOMETER(LENGTH, KILOMETERdesc, 1000000.0),

	// area UnitType
	// all area conversion factors are versus a mm²
	SQMIL(AREA, SQMILEdesc, 0.00064516),
	SQINCH(AREA, SQINCHdesc, 645.16),
	SQFOOT(AREA, SQFOOTdesc, 92903.04),
	SQYARD(AREA, SQYARDdesc, 836127.36),
	SQUARE(AREA, SQUAREdesc, 9290304.0),
	ACRE(AREA, ACREdesc, 4046856422.4),
	SQMILE(AREA, SQMILEdesc, 2589988110336.0),
	SECTION(AREA, SECTIONdesc, 2589988110336.0),
	TOWNSHIP(AREA, TOWNSHIPdesc, 93239571972096.0),
	SQMILLIMETER(AREA, SQMILLIMETERdesc, 1.0),
	SQCENTIMETER(AREA, SQCENTIMETERdesc, 100.0),
	SQMETER(AREA, SQMETERdesc, 1000000.0),
	HECTARE(AREA, HECTAREdesc, 10000000000.0),
	SQKILOMETER(AREA, SQKILOMETERdesc, 1000000000000.0),

	// volume UnitType
	// all volume conversion factors are versus a cm³
	CUINCH(VOLUME, CUINCHdesc, 16.387064),
	CUFOOT(VOLUME, CUFOOTdesc, 28316.846592),
	CUYARD(VOLUME, CUYARDdesc, 764554.857984),
	CUMILE(VOLUME, CUMILEdesc, 4168181825440579.584),
	ACREFOOT(VOLUME, ACREFOOTdesc, 1233481837.54752),
	FLOUNCE(VOLUME, FLOUNCEdesc, 29.5735295625),
	FLPINT(VOLUME, FLPINTdesc, 473.176473),
	FLQUART(VOLUME, FLQUARTdesc, 946.352946),
	FLGALLON(VOLUME, FLGALLONdesc, 3785.411784),
	MICROLITER(VOLUME, MICROLITERdesc, 0.001),
	CUMILLIMETER(VOLUME, CUMILLIMETERdesc, 0.001),
	CUCENTIMETER(VOLUME, CUCENTIMETERdesc, 1.0),
	MILLILITER(VOLUME, MILLILITERdesc, 1.0),
	LITER(VOLUME, LITERdesc, 1000.0),
	HECTOLITER(VOLUME, HECTOLITERdesc, 100000.0),
	KILOLITER(VOLUME, KILOLITERdesc, 1000000.0),
	CUMETER(VOLUME, CUMETERdesc, 1000000.0),
	CUKILOMETER(VOLUME, CUKILOMETERdesc, 1000000000000000.0),

	// fourth UnitType
	// all fourth conversion factors are versus in⁴
	MOMENTOFINERTIA(FOURTH, MOMENTOFINERTIAdesc, 1.0),    // in⁴

	// mass UnitType
	// all mass conversion factors are versus a milligram
	CARAT(MASS, CARATdesc, 200.0),
	OUNCE(MASS, OUNCEdesc, 28349.523125),
	POUND(MASS, POUNDdesc, 453592.37),
	STONE(MASS, STONEdesc, 6350293.18),
	KIP(MASS, KIPdesc, 453592370.0),
	TONSHORT(MASS, TONSHORTdesc, 907184740.0),
	TONLONG(MASS, TONLONGdesc, 1016046908.8),
	MILLIGRAM(MASS, MILLIGRAMdesc, 1.0),
	CENTIGRAM(MASS, CENTIGRAMdesc, 10.0),
	DECIGRAM(MASS, DECIGRAMdesc, 100.0),
	GRAM(MASS, GRAMdesc, 1000.0),
	DEKAGRAM(MASS, DEKAGRAMdesc, 10000.0),
	HECTOGRAM(MASS, HECTOGRAMdesc, 100000.0),
	KILOGRAM(MASS, KILOGRAMdesc, 1000000.0),
	TONNE(MASS, TONNEdesc, 1000000000.0),

//	// density UnitType
//	// all density conversions are factors versus kg/m³
//	OUNCEPERCUFOOT		(DENSITY,	  10, "Ounce per Cubic Foot", "Ounce per Cubic Feet", "oz/ft³", "",			   1.00115396087251),
//	OUNCEPERCUINCH		(DENSITY,	  20, "Ounce per Cubic Inch", "Ounce per Cubic Inches", "oz/in³", "",		1729.9940443877),
//	OUNCEPERGALLON		(DENSITY,	  30, "Ounce per Gallon", "Ounce per Gallons", "oz/gal", "",				   7.48915170730604),
//	POUNDPERCUFOOT		(DENSITY,	  40, "Pound per Cubic Foot", "Pound per Cubic Feet", "lb/ft³", "",			  16.0184633739601),
//	POUNDPERCUINCH		(DENSITY,	  50, "Pound per Cubic Inch", "Pound per Cubic Inches", "lb/in³", "",	   27679.9047102031),
//	POUNDPERGALLON		(DENSITY,	  60, "Pound per Gallon", "Pound per Gallons", "lb/gal", "",				 119.826427316897),
//	GRAMPERMILLILITER	(DENSITY,	 510, "Gram per Milliliter", "Gram per Milliliters", "g/mL", "",			1000.0),
//	KILOGRAMPERCUMETER	(DENSITY,	 520, "Kilogram per Cubic Meter", "Kilogram per Cubic Meters", "kg/m³", "",	   1.0),
//	KILOGRAMPERLITER	(DENSITY,	 530, "Kilogram per Liter", "Kilogram per Liters", "kg/L", "",				1000.0),

	// time UnitType
	// all time conversions are factors versus a second "s"
	MILLISECOND(TIME, MILLISECONDdesc, 0.001),
	SECOND(TIME, SECONDdesc, 1.0),
	MINUTE(TIME, MINUTEdesc, 60.0),
	HOUR(TIME, HOURdesc, 3600.0),
	DAY(TIME, DAYdesc, 86400.0),
	WEEK(TIME, WEEKdesc, 604800.0),
	MONTH(TIME, MONTHdesc, 2628000.0),
	YEAR(TIME, YEARdesc, 31536000.0),

	// temperature UnitType
	// all temperature conversions are based on a degree kelvin "K"
	// temperature conversion factors have special conversion codes <0 & >-9
	CELSIUS			(TEMPERATURE, CELSIUSdesc, TEMP_CELSUS.code),
	FAHRENHEIT		(TEMPERATURE, FAHRENHEITdesc, TEMP_FAHRENHEIT.code),
	NEWTON			(TEMPERATURE, NEWTONdesc, TEMP_NEWTON.code),
	KELVIN			(TEMPERATURE, KELVINdesc, TEMP_KELVIN.code),
	
	// angle unitType
	// all angle conversions are based on decimal degrees
	// angles conversion factors have special conversion codes <-9 & >-19
	ANGLEDECIMAL	(ANGLE,	ANGLEDECIMALdesc, 	ANGLE_DECIMAL.code),
	ANGLEDMS		(ANGLE,	ANGLEDMSdesc, 		ANGLE_DMS.code),
	ANGLEPERCENT	(ANGLE,	ANGLEPERCENTdesc, 	ANGLE_PERCENT.code),
	ANGLERATIO		(ANGLE,	ANGLERATIOdesc, 	ANGLE_RATIO.code),
	ANGLERADIANS	(ANGLE,	ANGLERADIANSdesc, 	ANGLE_RADIANS.code);

	private final UnitCategory unitCategory;
	private final UnitDescription unitDesc;
	private final BigDecimal convertFactor;

	UnitType(UnitCategory uUC, UnitDescription uUD, double dCF) {

		unitCategory = uUC;
		unitDesc = uUD;
		convertFactor = BigDecimal.valueOf(dCF);
		convertFactor.setScale(14, RoundingMode.HALF_UP);
	}
	
	public static final double SPECIAL_CONVERT_TEMP_START = -1.0;
	public static double SPECIAL_CONVERT_TEMP_END = SPECIAL_CONVERT_TEMP_START;
	
	public static final double SPECIAL_CONVERT_ANGLE_START = -10.0;
	public static double SPECIAL_CONVERT_ANGLE_END = SPECIAL_CONVERT_ANGLE_START;
	
	enum SpecialUnitConversions {
		TEMP_CELSUS			(SPECIAL_CONVERT_TEMP_END--),
		TEMP_FAHRENHEIT		(SPECIAL_CONVERT_TEMP_END--),
		TEMP_NEWTON			(SPECIAL_CONVERT_TEMP_END--),
		TEMP_KELVIN			(SPECIAL_CONVERT_TEMP_END--),
		ANGLE_DECIMAL		(SPECIAL_CONVERT_ANGLE_END--),
		ANGLE_DMS			(SPECIAL_CONVERT_ANGLE_END--),
		ANGLE_PERCENT		(SPECIAL_CONVERT_ANGLE_END--),
		ANGLE_RATIO			(SPECIAL_CONVERT_ANGLE_END--),
		ANGLE_RADIANS		(SPECIAL_CONVERT_ANGLE_END--);
		
		private final double code;
		
		SpecialUnitConversions(double code) {
			this.code = code;
		}
	}
	
	// compare one unit type to the another
	// comparison is based on the ordinal sequence of the unitCategory and the ordinal
	// sequence of the unitType
	int CompareTo(UnitType test) {
		int RANK_FACTOR = 10000;
		
		int current_rank = (unitCategory.ordinal() * RANK_FACTOR) + ordinal();
		int test_rank = (test.unitCategory.ordinal() * RANK_FACTOR) + test.ordinal();
		
		return test_rank - current_rank;
	}
	
	// compare one unit type to another to determine if they are equal
	// comparison is determined if they both have the same ordinal value
	boolean equals(UnitType test) { return test.ordinal() == ordinal(); }
	
//	UnitCategory getUnitCategory() { return unitCategory; }

//	UnitType getBaseUnitType() { return unitCategory.getBaseUnitType(); }
	
//	boolean hasExtensionUnits() { return unitCategory.hasExtensionUnits(); }
	
//	int getExponent() { return unitCategory.getExponent(); }
	
//	UnitDescription getDescription() { return unitDesc; }
	
	String getAbbreviation() {return unitDesc.getAbbreviation(); }
	String getAltAbbreviation() {return unitDesc.getAltAbbreviation(); }
	String getName(double qty) {return unitDesc.getCommonName(qty); }

	BigDecimal getConvertFactor() { return convertFactor; }

	// convert the provided valueIn from the unittypein to the unittype out
	// assuming that they are in the same unit category
	static double ConvertUnit(double valueIn, UnitType uTypeIn, UnitType uTypeOut) {
		
		// if both are the same unit type - no conversion is needed
		if (uTypeIn.equals(uTypeOut)) {
			return valueIn;
		}
		
	}
	


	
	
	
}
