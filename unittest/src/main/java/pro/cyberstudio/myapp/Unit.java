package pro.cyberstudio.myapp;

import org.apfloat.Apfloat;

import static pro.cyberstudio.myapp.UnitType.*;

/**
 * Created by Jeff on 6/28/2014.
 * version 8.0
 *
 * ver 8.0:	maintains Unit's number mode - this, at the moment, limits
 * 			the Unit data type of Scalar & Foot / Inch numbers
 */

public class Unit {

	// the value of this Unit (will depend in the unit type)
	private Apfloat unitValue;		// this is the value of the unit in the unit type's base unit type
	private UnitType unitType;
	private int exponent;
	
	static final long APFLOATPRECISION = 81;
	static final long APFLOATPRECROUND = 80;

	
// constructors

	/** Construct a Unit, its UnitType, and its exponent
	 * @param unitIn The value of the Unit as an Apfloat
	 * @param utIn The Unit Type of the unit
	 * @param expIn the exponent for this Unit
	 */
	Unit(Apfloat unitIn, UnitType utIn, int expIn) {
		unitValue = unitIn;
		unitType = utIn;
		exponent = expIn;
	}
	
	/**
	 * Construct a unit except that the exponent is set as 1
	 * @param unitIn The value of the Unit as an Apfloat
	 * @param utIn The Unit Type of the unit
	 */
	Unit(Apfloat unitIn, UnitType utIn) {
		this(unitIn, utIn, 1);
	}
	
	/**
	 * Construct a Unit, its UnitType, and its exponent
	 * @param unitIn The value of the Unit as a string
	 * @param utIn The Unit Type of the unit
	 * @param expIn the exponent for this Unit
	 */
	Unit(String unitIn, UnitType utIn, int expIn) {
		this(new Apfloat(unitIn, APFLOATPRECISION), utIn, expIn);
	}
	
	/**
	 * Construct a unit except that the exponent is set as 1
	 * @param unitIn The value of the Unit as a string
	 * @param utIn The Unit Type of the unit
	 */
	Unit(String unitIn, UnitType utIn) {
		this(new Apfloat(unitIn, APFLOATPRECISION), utIn, 1);
	}
	
	/**
	 * Construct a unit with a value of zero and unit type of Scalar
	 * that is, a default value
	 */
	Unit() {
		this(new Apfloat("0.0", APFLOATPRECISION), SCALAR, 1);
	}

	/**
	 * Clone a unit
	 * @param uU the original Unit to clone
	 */
	public Unit clone(Unit uU) {
		return new Unit(uU.unitValue, uU.unitType, uU.exponent);
	}
	
	public UnitCategory getUnitCategory() {
		return unitType.getUnitCategory();
	}
	
	/**
	 * Gets the UnitType of the Unit
	 * @return UnitType (e.g. SCALAR, INCH, FOOT, etc.)
	 */
	public UnitType getUnitType() {
		return unitType;
	}
	
//	/**
//	 *
//	 * @param ValueInOrigUnit	the value of the unit in based on the unitType provided
//	 * @param inUnitType			the unitType of the value
//	 * @return					the equivalent value on the unitTypes base unit
//	 */
//	static Double baseValue(double ValueInOrigUnit, UnitType inUnitType) {
//
//		// if the inUnitType is already the base unit type
//		if (inUnitType.equals(inUnitType.getBaseUnitType())) {
//			return ValueInOrigUnit;
//		}
//
//		// value is not in the base units
//		// must convert the value to the base unitType
//	}
	

	/**
	 * Get the current Unit's value in its current unit type
	 */
	public Apfloat getValue() {
		return unitValue;
	}

//	/**
//	 * Get the current Unit's value in the unit type requested.
//	 * @param utOut The UnitType of the value to be returned
//	 */
//	public double getValue(UnitType utOut) {
//		if (unitType.equals(utOut))
//			// check - if requested out unit matches the current unit type,
//			// just provide the current value
//			return unitValue;
//		else {
//			// else, provide the current unit's value in the unit type requested
//			// note that ConvertValue will return NaN if the requested
//			// unit type output's unit category does not match the current
//			// unit's category
//			return ConvertValue(unitValue, unitType, utOut);
//		}
//	}

//	// assign a value to this unit - tell me the incoming unit type
//	public void setValue(double doubInUnit, UnitType utIn) {
//		unitValue = doubInUnit;
//		unitType = utIn;
//	}
//
//	// assign a value to this unit based on the passed unit
//	public void setValue(Unit unitIn) {
//		unitValue = unitIn.getValue();
//		unitType = unitIn.getUnitType();
//	}

}
