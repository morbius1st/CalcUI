package pro.cyberstudio.myapp;

import android.annotation.SuppressLint;

import android.view.Gravity;
import android.view.View;

import static pro.cyberstudio.myapp.ConfigCalcUI.CellViewType.*;
import static pro.cyberstudio.myapp.ConfigCalcUI.ViewFunctions.*;
import static pro.cyberstudio.myapp.Utilities.*;



/**
 * @author Jeff
 *         File:    configCalcUI
 *         Created: 9/17/2016 @ 6:28 AM
 *         Project: Test1
 */
@SuppressLint("RtlHardcoded")
@SuppressWarnings("WeakerAccess UnusedDeclaration")

class ConfigCalcUI {

	static final int VIEWS_MAX = 11;
	static final int COLUMNS_MAX = 7;
	static final int ROWS_MAX = 9;
	static final int UNDEF = -1;

	static boolean clearFlag = true;

	static int idx = 0;

	// note that this must correspond 1 to 1 with the attrs.xml file for the view
	enum ViewCategory {
		UNDEFINED (-1),
		EDIT(101),
		SHIFT(102),
		ALPHA(103),
		CALCULATE(201),
		ANSWER(202),
		MEMORY(203),
		GROUPING(301),
		DIGIT(401),
		MARK(402),
		OPERATION(501),
		FUNCTION(502),
		CONVERSATION(503),
		CONSTANT(601),
		HISTORY(701),
		ENTRY(702),
		ALPHABET(801),
		UNIT(901);

		private int value;

		ViewCategory(int value) {
			this.value = value;
		}

		static String toString(int idx) {

			for (ViewCategory v : values())
				if (v.value == idx)
					return v.toString();

			return null;
		}

		public int getValue() {
			return value;
		}


	}

	enum ViewFunctions {PRIME_FUNCT, SUB_FUNCT}

	enum CellViewType {
		BUTTON (idx++, PRIME_FUNCT, Gravity.NO_GRAVITY),
		TEXTVIEW (idx++, PRIME_FUNCT, Gravity.NO_GRAVITY),
		IMAGEBUTTON (idx++, PRIME_FUNCT, Gravity.NO_GRAVITY),

		TOP_LEFT (idx++, SUB_FUNCT, Gravity.TOP | Gravity.LEFT),
		TOP_CENTER (idx++, SUB_FUNCT, Gravity.TOP | Gravity.CENTER),
		TOP_RIGHT (idx++, SUB_FUNCT, Gravity.TOP | Gravity.RIGHT),
		BOTTOM_LEFT (idx++, SUB_FUNCT, Gravity.BOTTOM | Gravity.LEFT),
		BOTTOM_CENTER (idx++, SUB_FUNCT, Gravity.BOTTOM | Gravity.CENTER),
		BOTTOM_RIGHT (idx++, SUB_FUNCT, Gravity.BOTTOM | Gravity.RIGHT),
		CENTER_LEFT (idx++, SUB_FUNCT, Gravity.CENTER | Gravity.LEFT),
		CENTER_RIGHT (idx++, SUB_FUNCT, Gravity.CENTER | Gravity.RIGHT);

		private ViewFunctions vFunct;
		private int gravity;
		private int arrayIndex;

		CellViewType(int i, ViewFunctions vF, int g) {
			vFunct = vF;
			gravity = g;
			arrayIndex = i;
		}

		static CellViewType findViewTypeByGravity(int gravity) {

			for (CellViewType cvt : CellViewType.values()) {
				if (cvt.getGravity() == gravity)
					return cvt;
			}
			return null;
		}

		static CellViewType findViewTypeByView(View v) {
			if (v instanceof TextViewAlt)
				return TEXTVIEW;
			else if (v instanceof ButtonAlt)
				return BUTTON;
			else if (v instanceof ImageButtonAlt)
				return IMAGEBUTTON;
			else
				return null;
		}

		boolean isButton() {
			return vFunct == PRIME_FUNCT;
		}

		boolean isSubFunction() {
			return vFunct == SUB_FUNCT;
		}

		int getGravity() {
			return gravity;
		}

		ViewFunctions getViewFunction() {
			return vFunct;
		}

		int getArrayIndex() {
			return arrayIndex;
		}
	}

	private static CalculatorUI cui;

	ConfigCalcUI() {
		cui = new CalculatorUI();
	}

	boolean addCell(int row, int column, CellInfo ci) {

		if (!verifyRowColumn(row, column)) {
			return false;
		}

		if (cui.addCell(row, column, ci)) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {

		StringBuilder sBuf = new StringBuilder();
		CellInfo ci;
		String s;

		sBuf.append("\n<-- Start -->");

		for (int r = 0; r < ROWS_MAX; r++) {
			for (int c = 0; c < COLUMNS_MAX; c++) {


				ci = cui.getCell(r, c);
				s = ci.toString();

				sBuf.append("\n r: " + r + " c: " + c);
				sBuf.append(s);
				sBuf.append("\n<-- next -->");
			}
		}

		sBuf.append("\n<-- end -->");

		return sBuf.toString();
	}

	public String[] toStringArray() {

		StringBuffer sBuf = new StringBuffer();
		CellInfo ci;
		String s;
		String response[] = new String[ROWS_MAX];

		sBuf.append("\n<-- Start -->");

		for (int r = 0; r < ROWS_MAX; r++) {
			for (int c = 0; c < COLUMNS_MAX; c++) {


				ci = cui.getCell(r, c);
				s = ci.toString();

				sBuf.append("\n r: " + r + " c: " + c);
				sBuf.append(s);
				sBuf.append("\n<-- next -->");
			}

			response[r] = sBuf.toString();

			// clear the buffer - this is the fastest way
			sBuf.delete(0, sBuf.length());

		}

		sBuf.append("\n<-- end -->");

		return response;
	}

	public String toStringInitOnly() {

		StringBuffer sBuf = new StringBuffer();
		CellInfo ci;
		String s;

		sBuf.append("\n<-- Start -->");

		for (int r = 0; r < ROWS_MAX; r++) {
			for (int c = 0; c < COLUMNS_MAX; c++) {

				ci = cui.getCell(r, c);

				// returns a string representation of the
				// cell info or null if nothing
				s = ci.toStringInitOnly();

				if (s != null) {
					sBuf.append("\n\nr: " + r + " c: " + c);
					sBuf.append(s);
				}
			}
		}

		sBuf.append("\n<-- end -->");

		return sBuf.toString();
	}

	public String[] toStringInitOnlyArray() {

		StringBuffer sBuf = new StringBuffer();
		CellInfo ci;
		String s;
		String response[] = new String[ROWS_MAX];

		sBuf.append("\n<-- Start -->");

		for (int r = 0; r < ROWS_MAX; r++) {

			sBuf.append("\nRow: " + r);

			for (int c = 0; c < COLUMNS_MAX; c++) {

				ci = cui.getCell(r, c);

				// returns a string representation of the
				// cell info or null if nothing
				s = ci.toStringInitOnly();

				if (s != null) {
					sBuf.append("\n\ncell: row: " + r + " column: " + c);
					sBuf.append(s);
				}
			}
			response[r] = sBuf.toString();

			// clear the buffer - this is the faster way
			sBuf.delete(0, sBuf.length());

		}

		sBuf.append("\n<-- end -->");

		return response;
	}


	// information a single view in a cell array
	static class CellView {

		CellViewType cvViewType;
		int cvID;
		String cvText;
		int cvTextColor;
		int cvTextSize;
		int cvBackground;
		View cvView;
		int cvCategory;

		CellView(CellViewType cvViewType, int cvID, String cvText,
				 int cvTextColor, int cvTextSize, int cvBackground) {

			clearFlag = true;
			setCellView(cvViewType, cvID, cvText, cvTextColor, cvTextSize, cvBackground, null);

		}

		CellView(CellViewType cvViewType, int cvID, String cvText,
				 int cvTextColor, int cvTextSize, int cvBackground, View vx) {

//			logMsg("setting cell view:  is null? " + (vx == null));

			clearFlag = false;
			setCellView(cvViewType, cvID, cvText, cvTextColor, cvTextSize, cvBackground, vx);
		}

		CellView() {
			clear();
		}

		void setCellView(CellViewType cvViewType, int cvID, String cvText,
						 int cvTextColor, int cvTextSize, int cvBackground) {

//			logMsg("setting cell view 2");

			clearFlag = true;
			setCellView(cvViewType, cvID, cvText, cvTextColor, cvTextSize, cvBackground, null);
		}

		void setCellView(CellViewType cvViewType, int cvID, String cvText,
						 int cvTextColor, int cvTextSize, int cvBackground, View v) {

			this.cvViewType = cvViewType;
			this.cvID = cvID;
			this.cvText = cvText;
			this.cvTextColor = cvTextColor;
			this.cvTextSize = cvTextSize;
			this.cvBackground = cvBackground;
			this.cvView = v;

			String tag = "null";

//			logMsg("setting ceil view 3: is null? " + (v == null) + " clearflag? " + !clearFlag);


			if (!clearFlag) {

//				if (v == null)
//					logMsg("view is null");
//				else
//					logMsg("view is not null");


				if (v != null) {
//
//					if (v.getTag() != null) {
//						tag = v.getTag().toString();
//					} else {
//						tag = "is null";
//					}

					switch (findViewTypeByView(v)) {
						case BUTTON:
							cvCategory = ((ButtonAlt) v).getFunctionCategory();
							break;
						case TEXTVIEW:
							cvCategory = ((TextViewAlt) v).getFunctionCategory();
							break;
						case IMAGEBUTTON:
							cvCategory = ((ImageButtonAlt) v).getFunctionCategory();
							break;
						default:
							cvCategory = ViewCategory.UNDEFINED.getValue();
							break;
					}
//					logMsg("cat: " + cvCategory + "  class: " + v.getClass().getSimpleName() + "  tag: " + tag);
				}
//				else
//					logMsg("** is null - tag: " + tag);
			}
		}

		void clear() {
			setCellView(null, 0, null, 0, 0, 0, null);
			clearFlag = true;
		}

		public void setView(View v) {
			cvView = v;
		}

		public View getView() {
			return cvView;
		}

		public ViewFunctions getViewFunction() {
			return cvViewType.getViewFunction();
		}

		public int getGravity() {
			return cvViewType.getGravity();
		}

		public  int getIndex() {
			return cvViewType.getArrayIndex();
		}

		public CellViewType getCellViewType() {
			return cvViewType;
		}

		public void setCellViewType(CellViewType cvView) {
			this.cvViewType = cvView;
		}

		public int getID() {
			return cvID;
		}

		public void setID(int cvID) {
			this.cvID = cvID;
		}

		public String getText() {
			return cvText;
		}

		public void setText(String cvText) {
			this.cvText = cvText;
		}

		public int getTextColor() {
			return cvTextColor;
		}

		public void setTextColor(int cvTextColor) {
			this.cvTextColor = cvTextColor;
		}

		public int getTextSize() {
			return cvTextSize;
		}

		public void setTextSize(int cvTextSize) {
			this.cvTextSize = cvTextSize;
		}

		public int getBackground() {
			return cvBackground;
		}

		public void setBackground(int cvBackground) {
			this.cvBackground = cvBackground;
		}

		@Override
		public String toString() {
			StringBuffer sBuf = new StringBuffer("");

			if (cvViewType != null) {
				// view position and type
				sBuf.append("\n\nView: " + cvViewType.ordinal() +
						" (" + cvViewType.toString() + ")");
				sBuf.append("\nID: " + cvID);

				sBuf.append("\nText: " + cvText);
				sBuf.append("\nText color: " + cvTextColor);
				sBuf.append("\nText size: " + cvTextSize);
				sBuf.append("\nBackground color: " + cvBackground);
				sBuf.append("\nView cat: " + ViewCategory.toString(cvCategory));
				sBuf.append("\nView tag: ");
				if (cvView != null) {

					Object tagX = cvView.getTag();

					if (tagX != null) {
						sBuf.append(tagX.toString());
					} else {
						sBuf.append("no tag");
					}

				} else {
					sBuf.append(" is null");
				}
			} else {
				sBuf.append("\n*** View: not initalized");
			}

			return sBuf.toString();
		}

		// provide a string representation of a cell view
		// provide null if not initalized (i.e. viewtype == null)
		public String toStringInitOnly() {

			if (cvViewType == null) {
				return null;
			}

			return toString();
		}
	}


	// array of views in a single cell
	// minimum is 0 maximum is Views_max
	static class CellInfo {

		CellView[] cellViewArray = new CellView[VIEWS_MAX];

		CellInfo() {
			clear();
		}

		boolean addView(CellView cv) {

			if (cv == null) {
				return false;
			}

			int index = cv.getIndex();

			if (index > VIEWS_MAX || index < 0) {
				return false;
			}

			if (index == 0 &&
					cv.getCellViewType().isSubFunction()) {
				return false;
			}

			cellViewArray[index] = cv;

			return true;
		}

		void clear() {
			for (int i = 0; i < VIEWS_MAX; i++) {
				cellViewArray[i] = new CellView();
			}
		}

		CellView getView(int index) {
			if (index > VIEWS_MAX || index <0) {
				return null;
			}

			return cellViewArray[index];
		}

		CellView getView(CellViewType cvs) {

			return cellViewArray[cvs.getArrayIndex()];
		}

		// provide toString for all of the views contained
		// regardless whether the view is initialized or not
		@Override
		public String toString() {

			StringBuffer sBuf = new StringBuffer("\n<--- start views --->   ");

			for (int v = 0; v < VIEWS_MAX; v++) {
				sBuf.append(cellViewArray[v].toString());
			}

			sBuf.append("\n<--- end views --->   ");

			return sBuf.toString();
		}

		// provide toString for all initialized views contained
		// return null if nothing is initalized
		public String toStringInitOnly() {
			StringBuffer sBuf = new StringBuffer();
			String s;

			for (int v = 0; v < VIEWS_MAX; v++) {

				CellView cv = cellViewArray[v];

				s = cv.toStringInitOnly();
				if (s != null)
					sBuf.append(s);
			}

			if (sBuf.length() != 0) {
				sBuf.insert(0, "\n\n<--- start views --->   ");
				sBuf.append("\n\n<--- end views --->   ");
				return sBuf.toString();
			}

			return null;
		}

	}

	// array of cells in a single row
	// minimum is 0 max is columns_max
	class RowInfo {

		CellInfo[] rowArray = new CellInfo[COLUMNS_MAX];

		RowInfo(int row) {
			for (int i = 0; i < COLUMNS_MAX; i++) {
				rowArray[i] = new CellInfo();
			}
		}

		boolean addCell(int column, CellInfo ci) {

			if (column > COLUMNS_MAX || column < 0) {
				return false;
			}

			rowArray[column] = ci;

			return true;
		}

		CellInfo getCell(int column) {
			if (column > COLUMNS_MAX || column < 0) {
				return null;
			}
			return rowArray[column];
		}
	}

	// array of rows for the whole interface
	// minimum row number is 0 max is rows_max
	class CalculatorUI {

		RowInfo[] calcArray = new RowInfo[ROWS_MAX];


		CalculatorUI() {
			for (int i = 0; i < ROWS_MAX; i++) {
				calcArray[i] = new RowInfo(i);
			}
		}

		boolean addCell(int row, int column, CellInfo cellInfo) {

			if (!verifyRowColumn(row, column)) {
				return false;
			}

			calcArray[row].addCell(column, cellInfo);

			return true;
		}

		CellInfo getCell(int row, int column) {

			return calcArray[row].getCell(column);
		}
	}

	static boolean verifyRowColumn(int row, int column) {
		if (!verifyRow(row) || !verifyColumn(column)) {
			return false;
		}

		return true;
	}

	static boolean verifyRow(int row) {
		if (row > ROWS_MAX || row < 0) {
			return false;
		}
		return true;
	}

	static boolean verifyColumn(int column) {
		if (column > COLUMNS_MAX || column < 0) {
			return false;
		}
		return true;
	}
}
