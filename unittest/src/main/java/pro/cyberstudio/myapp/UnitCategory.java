package pro.cyberstudio.myapp;

import static pro.cyberstudio.myapp.UnitType.*;

/**
 * Created by Jeff on 6/28/2014.
 */

// note that the ordinal order of the below determines the CompareTo / ordering sequence

enum UnitCategory {
	VOID		(1, INVALID, false),
	NOUNIT		(1, SCALAR, false),
	ANGLE		(1, ANGLEDECIMAL, false),
	MASS		(1, MILLIGRAM, false),
	TIME		(1, SECOND, false),
	TEMPERATURE	(1, KELVIN, false),
	LENGTH		(1, MILLIMETER, true),
	AREA		(2, SQMILLIMETER, true),
	VOLUME		(3, CUCENTIMETER, true),
	FOURTH		(4, MOMENTOFINERTIA, true);

	private final int exponent;
	private final boolean hasExtensionUnits;
	private final UnitType baseUnitType;

	UnitCategory(int exp, UnitType uT, boolean extUnit) {
		exponent = exp;
		baseUnitType = uT;
		hasExtensionUnits = extUnit;
	}
	
	int CompareTo(UnitCategory test) { return test.ordinal() - ordinal(); }
	
	boolean equals(UnitCategory test) { return test.ordinal() == ordinal(); }

	boolean hasExtensionUnits() {
		return hasExtensionUnits;
	}
	
	UnitType getBaseUnitType () {
		return baseUnitType;
	}
	
	int getExponent() { return exponent; }

}