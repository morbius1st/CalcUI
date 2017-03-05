package pro.cyberstudio.myapp;

/**
 * Created by Jeff on 6/28/2014.
 */


// enum for the types of units
// the unit category is used to organize the units -
// Length / Area / Volume are interrelated in that
// a Length * Length = Area
// an Area * Length = Volume
// a Volume * Length = unit^4
// a Unit^4 * Length = unit^5 - etc.
// given the above, this could go on forever but, all
// of these units above Volume will just be the
// base unit raised to the number of times it has
// ben multiplied - therefore we cannot have any
// unit categories above Volume (10300) because the next
// level (10400) is just the base unit to the fourth power
// e.g. ft⁴ (which, for feet, is nonsensical of course)


// the promote / demote code is the cat code of the "base" unit
//					Cat Code	level code	Promote Cat		Demote Cat
//								zero based		code			code
public enum Unit1Category {
	VOID				(0,			1,			-1,				-1),
	NOUNIT				(0,			1,			 0,				 0),
	ANGLE				(0,			1,			-1,				 0),
	MASS			 (2000,			1,			-1,				 0),
	DENSITY			 (3000,			1,			-1,				 0),
	TIME			 (4000,			1,			-1,				 0),
	TEMPERATURE		 (5000,			1,			-1,				 0),
	LENGTH			(11000,			1,		 11000,			 11000),
	AREA			(12000,			2,		 11000,			 11000),
	VOLUME			(13000,			3,		 11000,			 11000),
	FOURTH			(14000,			4,		 11000,			 11000);		// moment of inertia (only so far)

	public static final int LEVELSEPARATION = 1000;
	private static final int MAXUNITTYPES = 5;

	private final int intCategoryCode;			// the category code (which gets added to the unit code)
	private final int intLevelCode;				// the level code (used to determine promotion / demotion of units)
	private final int intPromoteCategoryCode;	// the
	private final int intDemoteCategoryCode;

	private Unit1Category(int iCC, int iLC, int iPCC, int iDCC) {
		intCategoryCode = iCC;
		intLevelCode = iLC;
		intPromoteCategoryCode = iPCC;
		intDemoteCategoryCode = iDCC;
	}

	public int getCategoryCode() {
		return intCategoryCode;
	}

	// this provides the UnitCategory for when (2) Unit are multiplied
	public int getPromoteCategoryCode() {
		return intPromoteCategoryCode;
	}

	// this provides the UnitCategory for when (2) Unit are divided
	public int getDemoteCategoryCode() {
		return intDemoteCategoryCode;
	}

	public int getLevelCode() {
		return intLevelCode;
	}
}
