package pro.cyberstudio.myapp;


import java.math.*;

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
	private double unitValue;		// this is the value of the unit in the unit type's base unit type
	private UnitType unitType;
	private int exponent;


	/** Assign the value of this Unit and the its UnitType
	 * this pre-assigns the exponent to 1
	 * @param inUnit The value of the Unit
	 * @param utIn The Unit Type of the unit
	 */
	Unit(double inUnit, UnitType utIn) {
		unitValue = inUnit;
		
		unitType = utIn;
	}

	/**
	 * Clone a unit
	 * @param uU the original Unit to clone
	 */
	public Unit clone(Unit uU) {
		Unit xU = defaultUnit();
		
		xU.unitValue = uU.unitValue;
		xU.unitType = uU.unitType;
		xU.exponent = uU.exponent;
		
		return xU;
	}

	static Unit defaultUnit() {
		return new Unit(0.0, SCALAR);
	}
	
	
	/**
	 *
	 * @param ValueInOrigUnit	the value of the unit in based on the unitType provided
	 * @param inUnitType			the unitType of the value
	 * @return					the equivilent value on the unitTypes base unit
	 */
	static Double baseValue(double ValueInOrigUnit, UnitType inUnitType) {
		
		// if the inUnitType is already the base unit type
		if (inUnitType.equals(inUnitType.getBaseUnitType())) {
			return ValueInOrigUnit;
		}
		
		// value is not in the base units
		// must convert the value to the base unitType
		
		
		
	}
	
	

	/**
	 * Get the current Unit's value in its current unit type
	 */
	public double getValue() {
		return unitValue;
	}

	/**
	 * Get the current Unit's value in the unit type requested.
	 * @param utOut The UnitType of the value to be returned
	 */
	public double getValue(UnitType utOut) {
		if (unitType.equals(utOut))
			// check - if requested out unit matches the current unit type,
			// just provide the current value
			return unitValue;
		else {
			// else, provide the current unit's value in the unit type requested
			// note that ConvertUnit will return NaN if the requested
			// unit type output's unit category does not match the current
			// unit's category
			return ConvertUnit(unitValue, unitType, utOut);
		}
	}

	// assign a value to this unit - tell me the incoming unit type
	public void setValue(double doubInUnit, UnitType utIn) {
		unitValue = doubInUnit;
		unitType = utIn;
	}

	// assign a value to this unit based on the passed unit
	public void setValue(Unit unitIn) {
		unitValue = unitIn.getValue();
		unitType = unitIn.getUnitType();
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
}
