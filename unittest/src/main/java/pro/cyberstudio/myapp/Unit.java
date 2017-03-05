package pro.cyberstudio.myapp;

import static pro.cyberstudio.myapp.UnitType.*;
import static pro.cyberstudio.myapp.Utilities.*;

/**
 * Created by Jeff on 6/28/2014.
 * version 8.0
 *
 * ver 8.0:	maintains Unit's number mode - this, at the moment, limits
 * 			the Unit data type of Scalar & Foot / Inch numbers
 */

public class Unit {

	// the value of this Unit (will depend in the unit type)
	private double doubUnit = 0.0;
	private UnitType utUnitType;


	/** Assign the value of this Unit and the its UnitType
	 * @param doubInUnit The value of the Unit
	 * @param utIn The Unit Type of the unit
	 */
	public Unit(double doubInUnit, UnitType utIn) {
		doubUnit = doubInUnit;
		utUnitType = utIn;
	}

	/**
	 * Duplicate Unit constructor
	 * @param uU the original Unit to duplicate
	 */
	public  Unit(Unit uU) {
		this.doubUnit = uU.doubUnit;
		this.utUnitType = uU.utUnitType;
	}

	public static Unit DefaultUnit() {
		return new Unit(0.0,SCALAR);
	}

	/**
	 * Get the current Unit's value in its current unit type
	 */
	public double getValue() {
		return doubUnit;
	}

	/**
	 * Get the current Unit's value in the unit type requested.
	 * @param utOut The UnitType of the value to be returned
	 */
	public double getValue(UnitType utOut) {
		if (utUnitType.equals(utOut))
			// check - if requested out unit matches the current unit type,
			// just provide the current value
			return doubUnit;
		else {
			// else, provide the current unit's value in the unit type requested
			// note that ConvertUnit will return NaN if the requested
			// unit type output's unit category does not match the current
			// unit's category
			return ConvertUnit(doubUnit, utUnitType, utOut);
		}
	}

	// assign a value to this unit - tell me the incoming unit type
	public void setValue(double doubInUnit, UnitType utIn) {
		doubUnit = doubInUnit;
		utUnitType = utIn;
	}

	// assign a value to this unit based on the passed unit
	public void setValue(Unit unitIn) {
		doubUnit = unitIn.getValue();
		utUnitType = unitIn.getUnitType();
	}

	public UnitCategory getUnitCategory() {
		return utUnitType.getUnitCategory();
	}

	/**
	 * Gets the UnitType of the Unit
	 * @return UnitType (e.g. SCALAR, INCH, FOOT, etc.)
	 */
	public UnitType getUnitType() {
		return utUnitType;
	}
}
