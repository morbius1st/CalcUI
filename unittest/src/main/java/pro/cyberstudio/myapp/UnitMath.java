package pro.cyberstudio.myapp;

import static pro.cyberstudio.myapp.UnitType.*;

public class UnitMath {

	private static final UnitType[] DIMENSIONGROUP = {SCALAR, INCH, SQINCH, CUINCH, MOMENTOFINERTIA};

	private static int intErrorCode = -1;
	private static int intErrorMsgShort = 0;
	private static int intErrorMsgLong = 0;

	public int getErrorCode() {
		return intErrorCode;
	}

	public int getErrorMsgShort() {
		return intErrorMsgShort;
	}

	public int getErrorMsgLong() {
		return intErrorMsgLong;
	}

	private static void setErrorData(int iEC, int iEMS, int iEML) {
		intErrorCode = iEC;
		intErrorMsgShort = iEMS;
		intErrorMsgLong = iEML;
	}
//
//	/**
//	 * Add two Units together<br>
//	 * Operand 1 is the primary operand:<br>
//	 *		Operand 1 sets the Unit's unit type<br>
//	 *		Operand 2 is added TO Operand 1<br>
//	 * @param unitOp1 The primary operand
//	 * @param unitOp2 The secondary operand
//	 * @return a "Unit" with the result<br>
//	 *  returns an "invalid" Unit for an invalid operation or if either
//	 *  Unit passed is "invalid" or null
//	 */
//	public static Unit UnitAdd(Unit unitOp1, Unit unitOp2) {
//		Unit unitResult = new Unit(Double.NaN, INVALID);
//
//		if ((unitOp1 == null) || (unitOp1.getUnitType() == INVALID) ||
//				(unitOp2 == null) || (unitOp2.getUnitType() == INVALID)) {
//			setErrorData(R.integer.mathEC_genInfoNoGood,
//					R.string.mathEM_genInfoNoGoodShort, R.string.mathEM_genInfoNoGoodLong);
//			return unitResult;		// return the "invalid" unit
//		}
//
//		UnitType utFinal;
//		double doubResult;
//
//		if (!unitOp1.getUnitCategory().equals(unitOp2.getUnitCategory())) {
//			if (unitOp1.getValue() != 0.0 && unitOp2.getValue() != 0.0) {
//				setErrorData(R.integer.mathEC_addBadCat, R.string.mathEM_addBadCatShort, R.string.mathEM_addBadCatLong);
//				return unitResult;
//			}
//
//		}
//
//		// add values based on the Unit Type of Op1
//		doubResult = unitOp1.getValue() + unitOp2.getValue(unitOp1.getUnitType());
//
//		// if math results in a NaN - return "Invalid" unit
//		if (Double.isNaN(doubResult)) {
//			setErrorData(R.integer.mathEC_genNaN, R.string.mathEM_genNaNShort, R.string.mathEM_genNaNShort);
//			return unitResult;
//		}
//
//		utFinal = unitOp1.getUnitType();
//
//		unitResult.setValue(doubResult, utFinal);
//		return unitResult;
//	}
//
//	/**
//	 * subtract a Unit from another<br>
//	 * return an "invalid" Unit for any problems<br>
//	 * Unit's must both be the same Unit Category<br>
//	 * 	&#9Operand 1 is the primary operand:<br>
//	 * 	&#9Operand 2 is subtracted FROM Operand 1<\t><br>
//	 * 	&#9Operand 1 sets the Unit's unit type<br>
//	 * @param unitOp1 a Unit
//	 * @param unitOp2 a Unit
//	 * @return a Unit = result of the subtraction
//	 */
//	public static Unit UnitSubtract(Unit unitOp1, Unit unitOp2) {
//		Unit unitResult = new Unit(Double.NaN, INVALID);
//
//		if ((unitOp1 == null) || (unitOp1.getUnitType() == INVALID) ||
//				(unitOp2 == null || (unitOp2.getUnitType() == INVALID))) {
//			setErrorData(R.integer.mathEC_genInfoNoGood, R.string.mathEM_genInfoNoGoodShort, R.string.mathEM_genInfoNoGoodLong);
//			return unitResult;		// return the "invalid" unit
//		}
//
//		UnitType utFinal;
//		double doubResult;
//
//		if (!unitOp1.getUnitCategory().equals(unitOp2.getUnitCategory())) {
//
//			if (unitOp1.getValue() != 0.0 && unitOp2.getValue() != 0.0) {
//				setErrorData(R.integer.mathEC_subBadCat, R.string.mathEM_subBadCatShort, R.string.mathEM_subBadCatLong);
//				return unitResult;
//			}
//		}
//
//		doubResult = unitOp1.getValue() - unitOp2.getValue(unitOp1.getUnitType());
//
//		// if math results in a NaN - return "Invalid" unit
//		if (Double.isNaN(doubResult)) {
//			setErrorData(R.integer.mathEC_genNaN, R.string.mathEM_genNaNShort, R.string.mathEM_genNaNShort);
//			return unitResult;
//		}
//
//		utFinal = unitOp1.getUnitType();
//
//		unitResult.setValue(doubResult, utFinal);
//		return unitResult;
//	}
//
//	/**
//	 * Multiply a Unit with a Unit and return a Unit with the result.<br>
//	 * Returns an "invalid" Unit for any problems
//	 * Operand 1 is the primary operand:
//	 *		Operand 1 is multiplied by Operand 2
//	 *		Operand 1 sets the Unit's unit type
//	 * Allowable conditions:
//	 * 1) both operands may be scalar - result will be scalar
//	 * 2) one operand may be non-scalar and the other may be scalar - result is the UnitType of the non-scalar Unit
//	 * 3) both operands may be non-scalar but only if Operand 1 can be promoted by the UnitType of operand 2
//	 * @param unitOp1 primary Unit - determines final UnitType
//	 * @param unitOp2 secondary Unit - unitOp1 is multiplied by unitOp2
//	 * @param Strict strict operation - does / does not respect unit promotion / demotion
//	 * @return Unit = result of the multiplication
//	 */
//	public static Unit UnitMultiply(Unit unitOp1, Unit unitOp2, boolean Strict) {
//		Unit unitResult = new Unit(Double.NaN, INVALID);
//
//		if ((unitOp1 == null) || (unitOp1.getUnitType() == INVALID) ||
//				(unitOp2 == null) || (unitOp2.getUnitType() == INVALID)) {
//			setErrorData(R.integer.mathEC_genInfoNoGood, R.string.mathEM_genInfoNoGoodShort, R.string.mathEM_genInfoNoGoodLong);
//			return unitResult;		// return the "invalid" unit
//		}
//
//		Unit unitTemp;
//
//		double doubOp1 = unitOp1.getValue();
//		double doubOp2 = unitOp2.getValue();
//
//		double doubResult;
//
//		UnitType utFinal;
//		UnitType utIN = null;
//
//		if (Strict) {
//			// determine the final UnitType
//			utFinal = UnitType.getPromoteUnitType(unitOp1.getUnitType(), unitOp2.getUnitType());
//
//			// if first attempt to get a promoted UnitType fails, swap Unit's
//			// and try again
//			if (utFinal == INVALID) {
//				unitTemp = unitOp1;
//				unitOp1 = unitOp2;
//				unitOp2 = unitTemp;
//				// determine the final UnitType
//				utFinal = UnitType.getPromoteUnitType(unitOp1.getUnitType(), unitOp2.getUnitType());
//				if (utFinal == INVALID) {
//					setErrorData(R.integer.mathEC_cannotPromote,
//							R.string.mathEM_cannotPromoteShort, R.string.mathEM_cannotPromoteLong);
//					return unitResult;  // pre-set as invalid
//				}
//			}
//
//			UnitCategory ucOp1 = unitOp1.getUnitCategory();
//			UnitCategory ucOp2 = unitOp2.getUnitCategory();
//
//			// if both are not Scalar - determine unit type conversion
//			if ((unitOp1.getUnitType() != SCALAR) && (unitOp2.getUnitType() != SCALAR)) {
//				// both are not are Scalar - determine the values to multiply
//				// and apply the need unit conversion
//				// first - if both operands are the same UnitCat
//				//		convert operand 2 into matching UnitType and proceed
//				// Alt: Unit is being promoted - need to work out a common UnitType
//				//		in order to be able to multiply the numbers
//				if (ucOp1 == ucOp2) {
//					// values are in the same UnitCat - get values in the UnitType
//					// of operand 1 and proceed
//					doubOp1 = unitOp1.getValue();
//					doubOp2 = unitOp2.getValue(unitOp1.getUnitType());
//				} else {
//					// we are promoting the values to a new UnitType - need to work out
//					// a common UnitType for the calculation: (inch / sq inch / cu inch / moment of inertia)
//					// at the moment, this can only be the Dimension (Length/Area/Volume/MomentOfInertia) group
//					doubOp1 = unitOp1.getValue(DIMENSIONGROUP[ucOp1.getLevelCode()]);
//					doubOp2 = unitOp2.getValue(DIMENSIONGROUP[ucOp2.getLevelCode()]);
//					utIN = DIMENSIONGROUP[Math.abs(ucOp1.getLevelCode() + ucOp2.getLevelCode())];
//				}
//			}
//		} else {
//			// for non-strict - determine the final unit type = unit of Op1 except that
//			// if unit type of Op1 == SCALAR, use Op2
//			utFinal = unitOp1.getUnitType();
//			if (utFinal == SCALAR)
//				utFinal = unitOp2.getUnitType();
//		}
//
//		// perform the math
//		doubResult = doubOp1 * doubOp2;
//
//		// if math results in a NaN - return "Invalid" unit
//		if (Double.isNaN(doubResult)) {
//			setErrorData(R.integer.mathEC_genNaN, R.string.mathEM_genNaNShort, R.string.mathEM_genNaNShort);
//			return unitResult;	// pre-set to invalid
//		}
//
//		if ((utIN != null) && (utIN != INVALID)) {
//			doubResult = ConvertUnit(doubResult, utIN, utFinal);
//		}
//
//		unitResult.setValue(doubResult, utFinal);
//		return unitResult;
//	}
//
//	/**
//	 * Multiply two Units together<br>
//	 * using a non-strict operation (does not respect Unit promotion / demotion)
//	 *
//	 * @param unitOp1 a Unit
//	 * @param unitOp2 a Unit
//	 * @return Unit = result of the multiplication
//	 */
//	public static Unit UnitMultiply(Unit unitOp1, Unit unitOp2) {
//		return UnitMultiply(unitOp1, unitOp2, false);
//	}
//
//	/**
//	 * Multiply two Units together<br>
//	 * using a strict operation (does respect Unit promotion / demotion)
//	 *
//	 * @param unitOp1 a Unit
//	 * @param unitOp2 a Unit
//	 * @return Unit = result of the multiplication
//	 */
//	public static Unit UnitMultiplyStrict(Unit unitOp1, Unit unitOp2) {
//		return UnitMultiply(unitOp1, unitOp2, true);
//	}
//
//	/**
//	 * Divide a Unit by a Unit and return a Unit with the result.<br>
//	 * Returns an "invalid" Unit for any problems
//	 * Operand 1 is the primary operand:
//	 *		Operand 1 is divided by Operand 2
//	 *		Operand 1 sets the Unit's unit type
//	 * Allowable conditions:
//	 * 1) both operands may be scalar - result will be scalar
//	 * 2) Operand 1 may be non-scalar and Operand 2 may be scalar (but not visa versa)- result is the UnitType of Operand 1
//	 * 3) both operands may be non-scalar but only if:
//	 *		a) both are of the same Unit Category, and
//	 *		b) Operand 1 has a demote UnitType
//	 * @param unitOp1 primary Unit - determines final UnitType
//	 * @param unitOp2 secondary Unit - unitOp1 is multiplied by unitOp2
//	 * @param Strict strict operation - does / does not respect unit promotion / demotion
//	 * @return Unit =  result of the division
//	 */
//	public static Unit UnitDivide(Unit unitOp1, Unit unitOp2, boolean Strict) {
//		Unit unitResult = new Unit(Double.NaN, INVALID);
//
//		if ((unitOp1 == null) || (unitOp1.getUnitType() == INVALID) ||
//				(unitOp2 == null) || (unitOp2.getUnitType() == INVALID)) {
//			setErrorData(R.integer.mathEC_genInfoNoGood, R.string.mathEM_genInfoNoGoodShort, R.string.mathEM_genInfoNoGoodLong);
//			return unitResult;		// return the "invalid" unit
//		}
//
//		UnitType utFinal;
//		UnitType utIN = null;
//
//		double doubOp1 = unitOp1.getValue();
//		double doubOp2 = unitOp2.getValue();
//
//		double doubResult;
//
//		if (Strict) {
//			// determine the final UnitType
//			utFinal = UnitType.getDemoteUnitType(unitOp1.getUnitType(), unitOp2.getUnitType());
//
//			if (utFinal == INVALID) {
//				setErrorData(R.integer.mathEC_cannotPromote,
//						R.string.mathEM_cannotPromoteShort, R.string.mathEM_cannotPromoteLong);
//				return unitResult;  // pre-set as invalid
//			}
//
//			UnitCategory ucOp1 = unitOp1.getUnitCategory();
//			UnitCategory ucOp2 = unitOp2.getUnitCategory();
//
//			// First - check for one of the operands being Scalar and the other not
//			if ((unitOp1.getUnitType() != SCALAR) && (unitOp2.getUnitType() == SCALAR)) {
//				// Operand2 is Scalar and Operand1 is not - OK - no unit conversion and final result is the
//				// unit type of Operand1
//				utFinal = unitOp1.getUnitType();
//			} else if ((unitOp1.getUnitType() == SCALAR) && (unitOp2.getUnitType() != SCALAR )) {
//					// operand1 is Scalar and Operand2 is not - NoGood
//				setErrorData(R.integer.mathEC_divByNonScalar,
//						R.string.mathEM_divByNonScalarShort, R.string.mathEM_divByNonScalarLong);
//				return unitResult;  // pre-set as invalid
//				} else {
//					// determine the values to divide and apply the needed unit conversion (when applies)
//
//					if (ucOp1 == ucOp2) {
//						// both in the same UnitCategory - get values based on the UnitType
//						// of operand1
//						doubOp1 = unitOp1.getValue();
//						doubOp2 = unitOp2.getValue(unitOp1.getUnitType());
//					} else {
//						// we are demoting the values of a new UnitType - need to work out
//						// a common UnitType for the calculation (inch / sq inch / cu inch / moment of inertia)
//						// at the moment, this can only be the Dimension (Length/Area/Volume/MomentOfInertia) group
//						doubOp1 = unitOp1.getValue(DIMENSIONGROUP[ucOp1.getLevelCode()]);
//						doubOp2 = unitOp2.getValue(DIMENSIONGROUP[ucOp2.getLevelCode()]);
//						utIN = DIMENSIONGROUP[Math.abs(ucOp1.getLevelCode() - ucOp2.getLevelCode())];
//					}
//			}
//		} else {
//			// for non-strict - determine the final unit type = unit of Op1 except that
//			// if unit type of Op1 == SCALAR, use Op2
//			utFinal = unitOp1.getUnitType();
//			if (utFinal == SCALAR)
//				utFinal = unitOp2.getUnitType();
//		}
//
//		// extra check - no dividing by 0
//		if (doubOp2 == 0) {
//			setErrorData(R.integer.mathEC_divByZero, R.string.mathEM_divByZeroShort, R.string.mathEM_divByZeroLong);
//			return unitResult;		// pre-set to invalid
//		}
//
//		// perform the math
//		doubResult = doubOp1 / doubOp2;
//
//		// if math results in a NaN - return "Invalid" unit
//		if (Double.isNaN(doubResult)) {
//			setErrorData(R.integer.mathEC_genNaN, R.string.mathEM_genNaNShort, R.string.mathEM_genNaNShort);
//			return unitResult;	// pre-set to invalid
//		}
//
//		if ((utIN != null) && (utIN != INVALID)) {
//			doubResult = ConvertUnit(doubResult, utIN, utFinal);
//		}
//
//		unitResult.setValue(doubResult, utFinal);
//		return unitResult;
//	}
//
//	/**
//	 * Divide a Unit by another Unit<br>
//	 * using a non-strict operation (does not respect Unit promotion / demotion)
//	 *
//	 * @param unitOp1 a Unit
//	 * @param unitOp2 a Unit
//	 * @return Unit = result of the division
//	 */
//	public static Unit UnitDivide(Unit unitOp1, Unit unitOp2) {
//		return UnitDivide(unitOp1, unitOp2, false);
//	}
//
//	/**
//	 * Divide a Unit by another Unit<br>
//	 * using a strict operation (does respect Unit promotion / demotion)
//	 *
//	 * @param unitOp1 a Unit
//	 * @param unitOp2 a Unit
//	 * @return Unit = result of the division
//	 */
//	public static Unit UnitDivideStrict(Unit unitOp1, Unit unitOp2) {
//		return UnitDivide(unitOp1, unitOp2, true);
//	}
//
//	/**
//	 * Calculate the square root if a Unit<br>
//	 * Returns an "invalid" Unit if there is a problem.
//	 * if Operand 1 is Scalar - normal math operation is performed.
//	 *
//	 * operation varies depending on Strict
//	 * When strict:
//	 * The square root of Operand 1 can only be performed if there
//	 * is a demote UnitType for the UnitType of Operand 1
//	 *
//	 * When not strict:
//	 * Always performs a simple math operation regardless
//	 * of the UniType of the Unit passed
//	 *
//	 * @param unitOp1 a Unit
//	 * @param Strict a boolean
//	 * @param Strict strict operation - does / does not respect unit promotion / demotion
//	 * @return returns a Unit with the square root of the unit passed
//	 * or Invalid of operation failed
//	 */
//	public static Unit UnitSqrt(Unit unitOp1, boolean Strict) {
//		Unit unitResult = new Unit(Double.NaN, INVALID);
//
//		if ((unitOp1 == null) || (unitOp1.getUnitType() == INVALID)) {
//			setErrorData(R.integer.mathEC_genInfoNoGood, R.string.mathEM_genInfoNoGoodShort, R.string.mathEM_genInfoNoGoodLong);
//			return unitResult;		// return the "invalid" unit
//		}
//
//		double doubOp1;
//		double doubResult;
//		double doubUtLevel;
//
//		UnitType utFinal = unitOp1.getUnitType();
//
//		if ((Strict) && (utFinal != SCALAR)) {
//			// determine the final UnitType - for Sqrt - this is a demote of 1/2
//			// of the level of the *current* UnitType
//			doubUtLevel = (double) unitOp1.getUnitCategory().getLevelCode() / 2;
//
//			if (doubUtLevel % 1 == 0) {
//				// no fraction - check for a valid demote unit
//				utFinal = UnitType.getDemoteUnitType(unitOp1.getUnitType(), 1);
//			} else {
//				// has a fraction - no such unit
//				setErrorData(R.integer.mathEC_cannotDemote,
//						R.string.mathEM_cannotDemoteShort, R.string.mathEM_cannotDemoteLong);
//				return unitResult;  // pre-set as invalid
//			}
//		}
//
//		doubOp1 = unitOp1.getValue();
//		doubResult = Math.sqrt(doubOp1);
//
//		// if math results in a NaN - return "Invalid" unit
//		if (Double.isNaN(doubResult)) {
//			setErrorData(R.integer.mathEC_genNaN, R.string.mathEM_genNaNShort, R.string.mathEM_genNaNShort);
//			return unitResult;	// pre-set to invalid
//		}
//
//		unitResult.setValue(doubResult, utFinal);
//
//		return unitResult;
//	}
//
//	/**
//	 * Calculate the square root if a Unit<br>
//	 * using a non-strict operation (does not respect Unit promotion / demotion)
//	 *
//	 * @param unitOp1 a Unit
//	 * @return Unit = the result of the square root
//	 */
//	public static Unit UnitSqrt(Unit unitOp1) {
//		return UnitSqrt(unitOp1, false);
//	}
//
//	/**
//	 * Calculate the square root if a Unit<br>
//	 * using a strict operation (does respect Unit promotion / demotion)
//	 *
//	 * @param unitOp1 a Unit
//	 * @return Unit = the result of the square root
//	 */
//	public static Unit UnitSqrtStrict(Unit unitOp1) {
//		return UnitSqrt(unitOp1, true);
//	}
//
//	/**
//	 * Returns a Unit raised to the pow of a Unit.<br>
//	 * Returns an "invalid" Unit for any problems
//	 * Operand 1 is the primary operand:
//	 *		Operand 1 is raised to the power of Operand 2
//	 *		Operand 1 sets the Unit's unit type
//	 * Operand 2 must be scalar
//	 * if Operand 1 is Scalar - a normal math operation is performed
//	 * Otherwise:
//	 * Operand 1 is a unit and,
//	 * Operand 1 can only be raised to the power of 0, 1, 2, or 3 (at the most)
//	 * Operand 1 can be raised by 0 or 1 and the UnitType is the UnitType of Operand 1
//	 * Operand 1 can only be raised by 2 if there is a promote UnitType for the UnitType of Operand 1
//	 * Operand 1 can only be raised by 3 if there is a promote UnitType for the Promote UnitType of Operand 1
//	 *
//	 * @param unitOp1 the base
//	 * @param unitOp2 the exponent
//	 * @param Strict strict operation - does / does not respect unit promotion / demotion
//	 */
//	public static Unit UnitPow(Unit unitOp1, Unit unitOp2, boolean Strict) {
//		Unit unitResult = new Unit(Double.NaN, INVALID);
//
//		if ((unitOp1 == null) || (unitOp1.getUnitType() == INVALID) ||
//				(unitOp2 == null) || (unitOp2.getUnitType() == INVALID)) {
//			setErrorData(R.integer.mathEC_genInfoNoGood, R.string.mathEM_genInfoNoGoodShort, R.string.mathEM_genInfoNoGoodLong);
//			return unitResult;		// return the "invalid" unit
//		}
//
//		UnitType utFinal;
//
//		double doubResult;
//
//		int intPower;
//		int intLevel;
//		int intUtLevelAdjust;
//
//		double doubOp1 = unitOp1.getValue();
//		double doubOp2 = unitOp2.getValue();	// power
//
//		if (Strict) {
//			utFinal = INVALID;
//			// all UnitTypes can only be raised by a scalar number
//			if (unitOp2.getUnitType() != SCALAR) {
//				setErrorData(R.integer.mathEC_powInvalidPower,
//						R.string.mathEM_powInvalidPowerShort, R.string.mathEM_powInvalidPowerLong);
//				return unitResult;
//			}
//
//			// get the values for the math operation
//			intLevel = unitOp1.getUnitCategory().getLevelCode();	// level
//
//			// is operand 1 Scalar?
//			if (unitOp1.getUnitType() == SCALAR) {
//				// yes - both operands are Scalar - just proceed with numbers
//				// as is.
//				utFinal = SCALAR;
//			} else {
//				// operand 1 is not Scalar - therefore
//				// a. operand 2 must be a whole number
//				// b. there must be a promote UnitType associated with the proposed power
//				// c. the amount of promotion or demotion
//
//				if ((doubOp2 -  IntDouble(doubOp2)) == 0) {
//					intPower = (int) doubOp2;
//					// note: utFinal is pre-defined as Invalid
//					// operand 2 is a whole number
//					// need to allow for (3) cases - Op2 could be negative, zero, or positive
//					// zero or 1: utFinal = operand 1 UnitType
//					// positive: get a promote UnitType
//					// negative: get a demote UnitType
//					if ((doubOp2 == 0) || (doubOp2 == 1)) {
//						// zero or 1
//						utFinal = unitOp1.getUnitType();
//					} else if (doubOp2 > 0) {
//						// positive
//						// promote the UnitType by ((power * level) - level)
//						intUtLevelAdjust = (intPower * intLevel) - intLevel;
//						// utFinal will be invalid of the proposed level adjustment does not work
//						utFinal = UnitType.getPromoteUnitType(unitOp1.getUnitType(), intUtLevelAdjust);
//						if (utFinal == INVALID) {
//							setErrorData(R.integer.mathEC_cannotPromote,
//									R.string.mathEM_cannotPromoteShort, R.string.mathEM_cannotPromoteLong);
//							return unitResult;		// pre-set to invalid
//						}
//					} else {
//						// negative
//						// demote the UnitType by (level - (power * level))
//						intUtLevelAdjust = intLevel - (intPower * intLevel);
//						// utFinal will be invalid of the proposed level adjustment does not work
//						utFinal = UnitType.getDemoteUnitType(unitOp1.getUnitType(), intUtLevelAdjust);
//						if (utFinal == INVALID) {
//							setErrorData(R.integer.mathEC_cannotDemote,
//									R.string.mathEM_cannotDemoteShort, R.string.mathEM_cannotDemoteLong);
//							return unitResult;		// pre-set to invalid
//						}
//					}
//				}
//			}
//		} else {
//			utFinal = unitOp1.getUnitType();
//		}
//
//		doubResult = Math.pow(doubOp1, doubOp2);
//
//		// if math results in a NaN - return "Invalid" unit
//		if (Double.isNaN(doubResult)) {
//			setErrorData(R.integer.mathEC_genNaN, R.string.mathEM_genNaNShort, R.string.mathEM_genNaNShort);
//			return unitResult;	// pre-set to invalid
//		}
//
//		unitResult.setValue(doubResult, utFinal);
//		return unitResult;
//	}
//
//	/**
//	 * Calculate a Unit raised to the power of a Unit<br>
//	 * using a non-strict operation (does not respect Unit promotion / demotion)
//	 *
//	 * @param unitOp1 a Unit
//	 * @param unitOp2 a Unit
//	 * @return Unit = the result of calculation
//	 */
//	public static Unit UnitPow(Unit unitOp1, Unit unitOp2) {
//		return UnitPow(unitOp1, unitOp2, false);
//	}
//
//	/**
//	 * Calculate a Unit raised to the power of a Unit<br>
//	 * using a strict operation (does respect Unit promotion / demotion)
//	 *
//	 * @param unitOp1 a Unit
//	 * @param unitOp2 a Unit
//	 * @return Unit = the result of calculation
//	 */
//	public static Unit UnitPowStrict(Unit unitOp1, Unit unitOp2) {
//		return UnitPow(unitOp1, unitOp2, true);
//	}
//
//	/**
//	 * Calculate the Reciprocal of a Unit
//	 * @param unitOp1 The Unit to calculate the reciprocal
//	 * @param Strict strict operation - does / does not respect unit promotion / demotion
//	 * @return A Unit with the result or an "Invalid" Unit
//	 * if the calculation is invalid (incorrect UnitTypes or Divide by zero)
//	 */
//	public static Unit UnitReciprocal(Unit unitOp1, boolean Strict) {
//		Unit unitResult = new Unit(Double.NaN, INVALID);
//
//		if ((unitOp1 == null) || (unitOp1.getUnitType() == INVALID)) {
//			setErrorData(R.integer.mathEC_genInfoNoGood, R.string.mathEM_genInfoNoGoodShort, R.string.mathEM_genInfoNoGoodLong);
//			return unitResult;		// return the "invalid" unit
//		}
//
//		Unit unitNumerator = new Unit(1, SCALAR);
//
//		unitResult = UnitDivide(unitNumerator, unitOp1, Strict);
//
//		return unitResult;
//	}
//
//	/**
//	 * Calculate the Reciprocal of a Unit<br>
//	 * using a non-strict operation (does not respect Unit promotion / demotion)
//	 *
//	 * @param unitOp1 a Unit
//	 * @return Unit = the result of calculation
//	 */
//	public static Unit UnitReciprocal(Unit unitOp1) {
//		return UnitReciprocal(unitOp1, false);
//	}
//
//	/**
//	 * Calculate the Reciprocal of a Unit<br>
//	 * using a strict operation (does respect Unit promotion / demotion)
//	 *
//	 * @param unitOp1 a Unit
//	 * @return Unit = the result of calculation
//	 */
//	public static Unit UnitReciprocalStrict(Unit unitOp1) {
//		return UnitReciprocal(unitOp1, true);
//	}
//
//	/**
//	 * Change the sign of a Unit
//	 */
//	public static Unit UnitChgSign(Unit unitOp1) {
//		Unit unitResult = new Unit(Double.NaN, INVALID);
//
//		if ((unitOp1 == null) || (unitOp1.getUnitType() == INVALID)) {
//			setErrorData(R.integer.mathEC_genInfoNoGood, R.string.mathEM_genInfoNoGoodShort, R.string.mathEM_genInfoNoGoodLong);
//			return unitResult;		// return the "invalid" unit
//		}
//
//		double doubOp1;
//		doubOp1 = unitOp1.getValue();
//		if (doubOp1 == 0) {
//			return unitOp1;
//		}
//		doubOp1 *= -1;
//		unitResult.setValue(doubOp1, unitOp1.getUnitType());
//		return unitResult;
//	}
//
//	/**
//	 * Returns a Unit with the value of PI as returned by Math.PI
//	 */
//	public static Unit UnitPI() {
//		return new Unit(Math.PI, SCALAR);
//	}
//
//
//	/**
//	 * return the absolute value of a Unit
//	 */
//	public static Unit UnitAbs(Unit unitOp1) {
//		Unit unitResult = new Unit(Double.NaN, INVALID);
//
//		if ((unitOp1 == null) || (unitOp1.getUnitType() == INVALID)) {
//			setErrorData(R.integer.mathEC_genInfoNoGood, R.string.mathEM_genInfoNoGoodShort, R.string.mathEM_genInfoNoGoodLong);
//			return unitResult;		// return the "invalid" unit
//		}
//
//		double doubOp1 = unitOp1.getValue();
//
//		if (doubOp1 >= 0) {
//			return unitOp1;
//		}
//		doubOp1 *= -1;
//		unitResult.setValue(doubOp1, unitOp1.getUnitType());
//		return unitResult;
//	}
//
//	/**
//	 * return the integer portion of a unit
//	 * returns an "invalid" unit if the function does not work ("invalid" unit passed)
//	 * or if the unit passed is null
//	 */
//	public static Unit UnitInteger(Unit unitOp1) {
//		Unit unitResult = new Unit(Double.NaN, INVALID);
//
//		if ((unitOp1 == null) || (unitOp1.getUnitType() == INVALID)) {
//			setErrorData(R.integer.mathEC_genInfoNoGood, R.string.mathEM_genInfoNoGoodShort, R.string.mathEM_genInfoNoGoodLong);
//			return unitResult;
//		}
//		unitResult.setValue(IntDouble(unitOp1.getValue()), unitOp1.getUnitType());
//		return unitResult;
//	}
//
//	/**
//	 * return the fractional portion of a unit
//	 * returns an "invalid" unit if the function does not work ("invalid" unit passed)
//	 * or if the unit passed is null
//	 */
//	public static Unit UnitFraction(Unit unitOp1) {
//		Unit unitResult = new Unit(Double.NaN, INVALID);
//
//		if ((unitOp1 == null) || (unitOp1.getUnitType() == INVALID)) {
//			setErrorData(R.integer.mathEC_genInfoNoGood, R.string.mathEM_genInfoNoGoodShort, R.string.mathEM_genInfoNoGoodLong);
//			return unitResult;
//		}
//
//		unitResult.setValue(unitOp1.getValue() - IntDouble(unitOp1.getValue()),
//				unitOp1.getUnitType());
//		return unitResult;
//	}
//
//
//	private static boolean unitTrigOk(Unit unitOp1) {
//		return ((unitOp1 == null) || (unitOp1.getUnitType() == INVALID) || (unitOp1.getUnitType() != SCALAR));
//	}
//
//	enum trigOp {SIN, COS, TAN, ASIN, ACOS, ATAN}
//
//	/**
//	 * return the sin of a unit
//	 * @param unitOp1 - a scalar number representing an angle in degrees
//	 * @return the trigonometric sin of the number or an
//	 * "invalid" unit
//	 */
//	public static Unit UnitSin(Unit unitOp1) {
//		return UnitTrig(unitOp1, SIN);
//	}
//
//	/**
//	 * return the cos of a unit
//	 * @param unitOp1 - a scalar number representing an angle in degrees
//	 * @return the trigonometric sin of the number or an
//	 * "invalid" unit
//	 */
//	public static Unit UnitCos(Unit unitOp1) {
//		return UnitTrig(unitOp1, COS);
//	}
//
//	/**
//	 * return the tan of a unit
//	 * @param unitOp1 - a scalar number representing an angle in degrees
//	 * @return the trigonometric sin of the number or an
//	 * "invalid" unit
//	 */
//	public static Unit UnitTan(Unit unitOp1) {
//		return UnitTrig(unitOp1, TAN);
//	}
//
//	/**
//	 * return the arcsin of a unit
//	 * @param unitOp1 - a scalar number representing an angle in degrees
//	 * @return the trigonometric sin of the number or an
//	 * "invalid" unit
//	 */
//	public static Unit UnitASin(Unit unitOp1) {
//		return UnitTrig(unitOp1, ASIN);
//	}
//
//	/**
//	 * return the arccos of a unit
//	 * @param unitOp1 - a scalar number representing an angle in degrees
//	 * @return the trigonometric sin of the number or an
//	 * "invalid" unit
//	 */
//	public static Unit UnitACos(Unit unitOp1) {
//		return UnitTrig(unitOp1, ACOS);
//	}
//
//	/**
//	 * return the arctan of a unit
//	 * @param unitOp1 - a scalar number representing an angle in degrees
//	 * @return the trigonometric sin of the number or an
//	 * "invalid" unit
//	 */
//	public static Unit UnitATan(Unit unitOp1) {
//		return UnitTrig(unitOp1, ATAN);
//	}
//
//	/**
//	 * return a trig function of a unit
//	 * unit must be a scalar number and is considered an angle in degrees
//	 * returns the trig value of the number if valid
//	 * returns an "invalid" unit if the number provided is not scalar
//	 * returns an "invalid" unit if the result is invalid
//	 * returns an "invalid" unit if the unit provided is null
//	 */
//	private static Unit UnitTrig(Unit unitOp1, trigOp tOP) {
//		// create the local unit and assign as an invalid unit
//		Unit unitResult = new Unit(Double.NaN, INVALID);
//
//		if (unitTrigOk(unitOp1)) {
//			setErrorData(R.integer.mathEC_trigNonScalar, R.string.mathEM_trigNonScalarShort, R.string.mathEM_trigNonScalarLong);
//			return unitResult;
//		}
//
//		double result = 0.0;
//
//		if (tOP == SIN || tOP == COS || tOP == TAN) {
//			result = Math.toRadians(unitOp1.getValue());
//		}
//
//		switch (tOP) {
//			case SIN:
//				result = Math.sin(result);
//				break;
//			case COS:
//				result = Math.cos(result);
//				break;
//			case TAN:
//				result = Math.tan(result);
//				break;
//			case ASIN:
//				result = Math.asin(unitOp1.getValue());
//				break;
//			case ACOS:
//				result = Math.acos(unitOp1.getValue());
//				break;
//			case ATAN:
//				result = Math.atan(unitOp1.getValue());
//				break;
//			default:
//				return unitResult;
//		}
//
//		if (Double.isNaN(result) || Double.isInfinite(result)) {
//			setErrorData(R.integer.mathEC_genNaN, R.string.mathEM_genNaNShort, R.string.mathEM_genNaNLong);
//			return unitResult;
//		}
//
//		if (tOP == ASIN || tOP == ACOS || tOP == ATAN) {
//			result = Math.toDegrees(result);
//		}
//
//		unitResult.setValue(result, unitOp1.getUnitType());
//
//		return unitResult;
//	}


}
