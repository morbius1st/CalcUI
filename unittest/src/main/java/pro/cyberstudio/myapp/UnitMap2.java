package pro.cyberstudio.myapp;

import static pro.cyberstudio.myapp.UnitType2.*;

/**
 * @author jeffs
 *         File:    UnitMap
 *         Created: 3/4/2017 @ 8:41 AM
 *         Project: CalcUI
 */

enum UnitMap2 {
	// length
	MILex				(MIL, SQMIL, null),
	INCHex				(INCH, SQINCH, CUINCH),
	FOOTex				(FOOT, SQFOOT, CUFOOT),
	YARDex				(YARD, SQYARD, CUYARD),
	MILEex				(MILE, SQMILE, CUMILE),

	// area
	SQUAREex			(FOOT, SQUARE, null),
	ACREex				(FOOT, ACRE, null),
	SECTIONex			(FOOT, SECTION, null),
	TOWNSHIPex			(FOOT, TOWNSHIP, null),

	// volume
	FLOUNCEex			(INCH, CUINCH, FLOUNCE),
	FLPINTex			(INCH, CUINCH, FLPINT),
	FLQUARTex			(INCH, CUINCH, FLQUART),
	FLGALLONex			(INCH, CUINCH, FLGALLON),


	// length
	MILLIMETERex		(MILLIMETER, SQMILLIMETER, CUMILLIMETER),
	CENTIMETERex		(CENTIMETER, SQCENTIMETER, CUCENTIMETER),
	METERex				(METER, SQMETER, CUMETER),
	KILOMETERex			(KILOMETER, SQKILOMETER, CUKILOMETER),

	// area
	HECTAREex			(METER, HECTARE, null),

	// VOLUME
	MICROLITERex		(MILLIMETER, SQMILLIMETER, MICROLITER),
	MILLILITERex		(MILLIMETER, SQMILLIMETER, MILLILITER),
	LITERex				(MILLIMETER, SQMILLIMETER, LITER),
	HECTOLITERex		(MILLIMETER, SQMILLIMETER, HECTOLITER),
	KILOLITERex			(MILLIMETER, SQMILLIMETER, KILOLITER);


	private static UnitType2[] specialUnitTypes = {CUINCH, MOMENTOFINERTIA, ACREFOOT};

	private final UnitType2[] uMap = new UnitType2[3];

	UnitMap2(UnitType2 ut1, UnitType2 ut2, UnitType2 ut3) {
		uMap[0] = ut1;
		uMap[1] = ut2;
		uMap[2] = ut3;
	}

}



