package pro.cyberstudio.myapp;

import android.annotation.SuppressLint;
import android.telephony.CellInfo;
import android.view.*;
import android.widget.*;


import java.util.*;

import static pro.cyberstudio.myapp.ConfigCalcUIMgr.CellType.*;
import static pro.cyberstudio.myapp.ConfigCalcUIMgr.ViewFunction.*;
import static pro.cyberstudio.myapp.ConfigCalcUIMgr.functionCategory.*;
import static pro.cyberstudio.myapp.Utilities.*;

//import static pro.cyberstudio.myapp.Utilities.*;

/**
 * @author Jeff
 *         File:    configCalcUI
 *         Created: 9/17/2016 @ 6:28 AM
 *         Project: Test1
 */
@SuppressLint("RtlHardcoded")
@SuppressWarnings("WeakerAccess UnusedDeclaration")

public class ConfigCalcUIMgr {

	static final int VIEWS_MAX = 7;
	static final int COLUMNS_MAX = 7;
	static final int UNDEF = -1;

	static int idx = 0;

	static DisplayInformation DI = Alt_Port02.DI;

	private GridLayoutGravity GLG = new GridLayoutGravity();

	// note that this must correspond 1 to 1 with the attrs.xml file for the view
	enum functionCategory {
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
		UNIT(901),
		UNDEFINED (-1); // must be last

		private int value;

		functionCategory(int value) {
			this.value = value;
		}

		static String toString(int idx) {

			for (functionCategory v : values())
				if (v.value == idx)
					return v.toString();

			return null;
		}

		public int getValue() {
			return value;
		}

		static int count() {
			return UNDEFINED.ordinal() -1;
		}

		static int getIndex(int fCategory) {

			if (fCategory < 0) {return -1;}

			for (functionCategory fc : values()) {
				if (fCategory == fc.getValue())
					return fc.ordinal();
			}
			return -1;
		}
	}

	enum ViewFunction {PRIME_FUNCT, SUB_FUNCT}

	enum CellType {
		BUTTON (-1, PRIME_FUNCT, Gravity.NO_GRAVITY),
		TEXTVIEW (-1, PRIME_FUNCT, Gravity.NO_GRAVITY),
		IMAGEBUTTON (-1, PRIME_FUNCT, Gravity.NO_GRAVITY),

		TOP_LEFT (idx++, SUB_FUNCT, Gravity.TOP | Gravity.LEFT),
		TOP_CENTER (idx++, SUB_FUNCT, Gravity.TOP | Gravity.CENTER),
		TOP_RIGHT (idx++, SUB_FUNCT, Gravity.TOP | Gravity.RIGHT),
		BOTTOM_LEFT (idx++, SUB_FUNCT, Gravity.BOTTOM | Gravity.LEFT),
		BOTTOM_CENTER (idx++, SUB_FUNCT, Gravity.BOTTOM | Gravity.CENTER),
		BOTTOM_RIGHT (idx++, SUB_FUNCT, Gravity.BOTTOM | Gravity.RIGHT),
		CENTER_LEFT (idx++, SUB_FUNCT, Gravity.CENTER | Gravity.LEFT),
		CENTER_RIGHT (idx++, SUB_FUNCT, Gravity.CENTER | Gravity.RIGHT);

		private ViewFunction vFunct;
		private int gravity;
		private int arrayIndex;

		CellType(int i, ViewFunction vF, int g) {
			vFunct = vF;
			gravity = g;
			arrayIndex = i;
		}

		static CellType findViewTypeByGravity(int gravity) {

			for (CellType cvt : CellType.values()) {
				if (cvt.getGravity() == gravity)
					return cvt;
			}
			return null;
		}

		static CellType findViewTypeByView(View v) {
			if (v instanceof TextViewAlt)
				return TEXTVIEW;
			else if (v instanceof ButtonAlt)
				return BUTTON;
			else if (v instanceof ImageButtonAlt)
				return IMAGEBUTTON;
			else
				return null;
		}

		boolean isPrime() {
			return vFunct == PRIME_FUNCT;
		}

		boolean isSubFunction() {
			return vFunct == SUB_FUNCT;
		}

		int getGravity() {
			return gravity;
		}

		ViewFunction getViewFunction() {
			return vFunct;
		}

		int getIndex() {
			return arrayIndex;
		}
	}

	private static CalculatorUI cui;

	private static boolean assigned = false;

	ConfigCalcUIMgr() {

		if (!assigned) {
			assigned = true;
			cui = new CalculatorUI();
			logMsg("number of categories: " + functionCategory.count());
		}

	}

	public void addView(View vw, int viewId) {
logMsg("config: add view: "+ vw.getTag().toString());
		CellType ct = CellType.findViewTypeByView(vw);

		if (ct == null) {return;}

		CellInfo ci = new CellInfo();

		ci.addPrime(new CellView(ct, viewId, vw));

		ViewParent vp = vw.getParent();

		if (vp instanceof GridLayout) {
			int i = ((GridLayout) vp).getChildCount();

			for (int j = 0; j < ((GridLayout) vp).getChildCount(); j++) {

				View vChild = ((GridLayout) vp).getChildAt(j);

				adjustChildView(vChild);

				ct = findViewTypeByGravity(GLG.getGravity(vChild));

				if (ct != null) {
					logMsg("adding child view: " + ct.toString());

					ci.addView(new CellView(ct, vChild));
				}
			}
		}
		// got a fully loaded CellInfo structure
		cui.addCell(ci);
	}

	void adjustChildView (View vChild) {

		if (vChild instanceof TextViewAlt) {
			DI.adjustViewTextSize((TextView) vChild);
		}

	}
//
//	boolean addCell(int row, int column, CellInfo ci) {
//
//		if (!verifyRowColumn(row, column)) {
//			return false;
//		}
//
//		if (cui.addCell(row, column, ci)) {
//			return true;
//		}
//
//		return false;
//	}

	@Override
	public String toString() {

		StringBuilder sBuf = new StringBuilder();
//		CellInfo ci;
		String s;

		int c;


		sBuf.append("\n<-- Start -->");

		for (int r = 0; r < functionCategory.count(); r++) {
			c = 0;


//			for (int c = 0; c < COLUMNS_MAX; c++) {

			for (CellInfo ci : cui.calcArray[r]) {

//				ci = cui.getCell(r, c);
				s = ci.toString();

				sBuf.append("\n r: " + r + " c: " + c++);
				sBuf.append(s);
				sBuf.append("\n<-- next -->");
			}
		}

		sBuf.append("\n<-- end -->");

		return sBuf.toString();
	}

	public String[] toStringArray() {

		StringBuffer sBuf = new StringBuffer();
//		CellInfo ci;
		String s;
		String response[] = new String[functionCategory.count()];
		int c;

		sBuf.append("\n<-- Start -->");

		for (int r = 0; r < functionCategory.count(); r++) {

			c = 0;

//			for (int c = 0; c < COLUMNS_MAX; c++) {
			for (CellInfo ci : cui.calcArray[r]) {

//				ci = cui.getCell(r, c);
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
//		CellInfo ci;
		String s;
		int c;

		sBuf.append("\n<-- Start -->");

		for (int r = 0; r < functionCategory.count(); r++) {
			c = 0;

//			for (int c = 0; c < COLUMNS_MAX; c++) {
			for (CellInfo ci : cui.calcArray[r]) {
//				ci = cui.getCell(r, c);

				// returns a string representation of the
				// cell info or null if nothing
				s = ci.toStringInitOnly();

				if (s != null) {
					sBuf.append("\n\nr: " + r + " c: " + c++);
					sBuf.append(s);
				}
			}
		}

		sBuf.append("\n<-- end -->");

		return sBuf.toString();
	}

	public String[] toStringInitOnlyArray() {

		StringBuffer sBuf = new StringBuffer();
		String s;
		String response[] = new String[functionCategory.count() +5];

		int r = 0;
		int c = 0;

		response[r++] = "\n<-- Start -->";

		for (RowInfo ri : cui.calcArray) {

			logMsg("<-- processing row: " + r);

			// ri = one row if ci's held in an arraylist

			for (CellInfo ci : ri) {

				logMsg("<-- processing col: " + c);

				s = ci.toStringInitOnly();

				if (s != null) {
					sBuf.append("\n\ncell: row: " + r + " column: " + c);
					sBuf.append(s);
				}

				c++;
			}

			response[r++] = sBuf.toString();

			// clear the buffer - this is the faster way
			sBuf.delete(0, sBuf.length());
		}

		response[r] = "\n<-- end -->";

		return response;
	}


	// information a single view in a cell array
	static class CellView {

		CellType cvViewType;
		int cvID;
		String cvText;
		int cvTextColor;
		int cvTextSize;
		int cvBackground;
		View cvView;
		int cvCategory;

		CellView(CellType cvViewType, View vw) {
			setCellView(cvViewType, UNDEF, "", UNDEF, UNDEF, UNDEF, vw);
		}

		CellView(CellType cvViewType, int cvID, View vw) {
			setCellView(cvViewType, cvID, "", UNDEF, UNDEF, UNDEF, vw);
		}

		CellView(CellType cvViewType, int cvID, String cvText,
		         int cvTextColor, int cvTextSize, int cvBackground) {
			setCellView(cvViewType, cvID, cvText, cvTextColor, cvTextSize, cvBackground, null);
		}

		CellView(CellType cvViewType, int cvID, String cvText,
		         int cvTextColor, int cvTextSize, int cvBackground, View vw) {
			setCellView(cvViewType, cvID, cvText, cvTextColor, cvTextSize, cvBackground, vw);
		}


		CellView() {
			clear();
		}

		void setCellView(CellType cvViewType, int cvID, String cvText,
		                 int cvTextColor, int cvTextSize, int cvBackground, View v) {

			this.cvViewType = cvViewType;
			this.cvID = cvID;
			this.cvText = cvText;
			this.cvTextColor = cvTextColor;
			this.cvTextSize = cvTextSize;
			this.cvBackground = cvBackground;
			this.cvView = v;

			String tag = "null";

			if (v != null) {

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
						cvCategory = functionCategory.UNDEFINED.getValue();
						break;
				}
			}

		}

		void clear() {
			setCellView(null, 0, null, 0, 0, 0, null);
		}

		public void setView(View v) {
			cvView = v;
		}

		public View getView() {
			return cvView;
		}

		public ViewFunction getViewFunction() {
			return cvViewType.getViewFunction();
		}

		public int getGravity() {
			return cvViewType.getGravity();
		}

		public  int getIndex() {
			return cvViewType.getIndex();
		}

		public CellType getCellType() {
			return cvViewType;
		}

		public void setCellViewType(CellType cvView) {
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
				sBuf.append("\nView cat: " + functionCategory.toString(cvCategory));
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

			logMsg("@cv toString ");

			if (cvViewType == null) {
				logMsg("view type is null");
				return null;
			}

			logMsg(" not null");

			return toString();
		}
	}


	// array of views in a single cell
	// minimum is 0 maximum is Views_max
	static class CellInfo {

		CellView cvPrime;

		CellView[] cvArray = new CellView[VIEWS_MAX];

		CellInfo() {
			clear();
		}

		boolean addPrime(CellView cv) {
			if (cv == null || cv.getCellType().isSubFunction()) {return false;}

			cvPrime = cv;

			return true;
		}

		boolean addView(CellView cv) {
			if (cv == null || cv.getCellType().isPrime()) { return false;}

			cvArray[cv.getCellType().getIndex()] = cv;

			return true;
		}

		void clear() {
			for (int i = 0; i < VIEWS_MAX; i++) {
				cvArray[i] = new CellView();
			}
		}

		CellView getPrime() {
			return cvPrime;
		}

		CellView getView(int index) {
			if (index > VIEWS_MAX || index <0) {
				return null;
			}
			return cvArray[index];
		}

		CellView getView(CellType ct) {

			if (ct.isPrime()) {
				return cvPrime;
			}

			return cvArray[ct.getIndex()];
		}

		// provide toString for all of the views contained
		// regardless whether the view is initialized or not
		@Override
		public String toString() {

			StringBuffer sBuf = new StringBuffer("\n<--- start views --->   ");

			for (int v = 0; v < VIEWS_MAX; v++) {
				sBuf.append(cvArray[v].toString());
			}

			sBuf.append("\n<--- end views --->   ");

			return sBuf.toString();
		}

		// provide toString for all initialized views contained
		// return null if nothing is initalized
		public String toStringInitOnly() {
			StringBuffer sBuf = new StringBuffer();
			String s;

			logMsg("@ci toString: ");

			s = cvPrime.toStringInitOnly();

			if (s != null) {
				sBuf.append(s);
				for (CellView cv : cvArray) {

					logMsg("@cv:");

					s = cv.toStringInitOnly();
					if (s != null)
						sBuf.append(s);
				}
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
	class RowInfo implements Iterable<CellInfo> {

		ArrayList<CellInfo> ra;


//		CellInfo[] rowArray = new CellInfo[COLUMNS_MAX];

		RowInfo() {

			ra = new ArrayList<>(COLUMNS_MAX);
		}

		boolean addCell(CellInfo ci) {
			logMsg("row info add cell: " + ci.getPrime().getView().getTag().toString());
			return ra.add(ci);
		}

		CellInfo getCell(int index) {
			if (index > ra.size() || index < 0) {
				return null;
			}
			return ra.get(index);
		}

		public Iterator<CellInfo> iterator() {
			return new RowInfoIterator();
		}

		private class RowInfoIterator implements Iterator<CellInfo> {

			private int cursor;

			public RowInfoIterator() {
				this.cursor = 0;
			}

			@Override
			public boolean hasNext() {
				return this.cursor < ra.size();
			}

			@Override
			public CellInfo next() {
				if (!this.hasNext()) {
					throw new NoSuchElementException();
				}
				return ra.get(cursor++);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}

	}



	// array of rows for the whole interface
	// minimum row number is 0 max is rows_max
	class CalculatorUI {

		RowInfo[] calcArray;
		private boolean assigned = false;

		CalculatorUI() {
			if (!assigned) {
				assigned = true;
				calcArray = new RowInfo[functionCategory.count()];

				for (int i = 0; i < functionCategory.count(); i++) {
					calcArray[i] = new RowInfo();
				}
			}
		}

		boolean addCell(CellInfo ci) {
			int fCategory;
			int row;

			logMsg("calcui addCell: " + ci.getPrime().getView().getTag().toString());

			View vw = ci.getPrime().getView();

			fCategory = ((iWidgetAlt) vw).getFunctionCategory();

			row = getIndex(fCategory);

			if (row < 0) { return false;}

			if (calcArray[row].addCell(ci))
				return true;

			return false;
		}

//		boolean addCell(int row, int column, CellInfo cellInfo) {
//
//			if (!verifyRowColumn(row, column)) {
//				return false;
//			}
//
//			calcArray[row].addCell(column, cellInfo);
//
//			return true;
//		}

//		CellInfo getCell(int row, int column) {
//
//			return calcArray[row].getCell(column);
//		}
	}

	static boolean verifyRowColumn(int row, int column) {
		if (!verifyRow(row) || !verifyColumn(column)) {
			return false;
		}

		return true;
	}

	static boolean verifyRow(int row) {
		if (row > functionCategory.count() || row < 0) {
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
