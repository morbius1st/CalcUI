package pro.cyberstudio.myapp;

/**
 * Created by Jeff on 6/28/2014.
 */

enum UnitCategory2 {
	VOID		(1, false),
	NOUNIT		(1, false),
	ANGLE		(1, false),
	MASS		(1, false),
	TIME		(1, false),
	TEMPERATURE	(1, false),
	LENGTH		(1, true),
	AREA		(2, true),
	VOLUME		(3, true),
	FOURTH		(4, true);

	private final int exponent;
	private final boolean hasExtensionUnits;

	UnitCategory2(int exp, boolean extUnit) {
		exponent = exp;
		hasExtensionUnits = extUnit;
	}

	boolean hasExtensionUnits() {
		return hasExtensionUnits;
	}

}