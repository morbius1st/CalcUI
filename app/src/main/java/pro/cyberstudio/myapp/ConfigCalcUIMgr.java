package pro.cyberstudio.myapp;

import android.annotation.SuppressLint;
import android.view.*;
import android.widget.*;


import java.util.*;

import static pro.cyberstudio.myapp.ConfigCalcUIMgr.ViewType.*;
import static pro.cyberstudio.myapp.ConfigCalcUIMgr.ViewClass.*;
import static pro.cyberstudio.myapp.ConfigCalcUIMgr.ViewCategory.*;
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

	static final int VIEWS_MAX = 9;
	static final int COLUMNS_MAX = 7;
	static final int UNDEF = -1;

	static int idx = 0;

	private DisplayInformation DI;

	private GridLayoutGravity GLG = new GridLayoutGravity();

	// note that this must correspond 1 to 1 with the attrs.xml file for the view
	enum ViewCategory {
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

		ViewCategory(int value) {
			this.value = value;
		}

		static String toStringOrdinal(int ordinal) {

			return ViewCategory.values()[ordinal].name();
		}

		static String toStringValue(int value) {

			for (ViewCategory v : values())
				if (v.value == value)
					return v.toString();

			return null;
		}

		public int getValue() {
			return value;
		}

		static int count() {
			return UNDEFINED.ordinal();
		}

		static int getIndex(int fCategory) {

			if (fCategory < 0) {return -1;}

			for (ViewCategory fc : values()) {
				if (fCategory == fc.getValue())
					return fc.ordinal();
			}
			return -1;
		}
	}

	enum ViewClass {PRIME_FUNCT, SUB_FUNCT}

	enum ViewType {
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

		private ViewClass vFunct;
		private int gravity;
		private int arrayIndex;

		ViewType(int i, ViewClass vF, int g) {
			vFunct = vF;
			gravity = g;
			arrayIndex = i;
		}

		static ViewType findViewTypeByGravity(int gravity) {

			for (ViewType cvt : ViewType.values()) {
				if (cvt.getGravity() == gravity)
					return cvt;
			}
			return null;
		}

		static ViewType findViewTypeByView(View v) {
			if (v instanceof TextViewAlt)
				return TEXTVIEW;
			else if (v instanceof ButtonAlt)
				return BUTTON;
			else if (v instanceof ImageButtonAlt)
				return IMAGEBUTTON;
			else
				return null;
		}

		boolean isPrimeCategory() {
			return vFunct == PRIME_FUNCT;
		}

		boolean isSubCategory() {
			return vFunct == SUB_FUNCT;
		}

		int getGravity() {
			return gravity;
		}

		ViewClass getViewCategory() {
			return vFunct;
		}

		int getIndex() {
			return arrayIndex;
		}
	}

	private static CalculatorUI cui;

	private static boolean assigned = false;

	ConfigCalcUIMgr(DisplayInformation DI) {

		this.DI = DI;

		if (!assigned) {
			assigned = true;
			cui = new CalculatorUI();
		}
	}

	public void addView(View vw, int viewId) {
//		logMsg("config: add view: "+ vw.getTag().toString());
		ViewType ct = ViewType.findViewTypeByView(vw);

		if (ct == null) {return;}

		cui.add(new CellView(ct, viewId, vw));

		ViewParent vp = vw.getParent();

		if (vp instanceof GridLayout) {
			int i = ((GridLayout) vp).getChildCount();

			for (int j = 0; j < ((GridLayout) vp).getChildCount(); j++) {

				View vChild = ((GridLayout) vp).getChildAt(j);

				if (vChild.equals(vw)) {
					continue;
				}

				adjustChildView(vChild);

				ct = findViewTypeByGravity(GLG.getGravity(vChild));

				if (ct != null) {
					cui.add(new CellView(ct, vChild));
				}
			}
		}
	}

	void adjustChildView (View vChild) {

		if (vChild != null) {
			if (vChild instanceof TextViewAlt) {
				DI.adjustViewTextSize((TextView) vChild);
			}
		}
	}
//
//	boolean add(int row, int column, CellInfo ci) {
//
//		if (!verifyRowColumn(row, column)) {
//			return false;
//		}
//
//		if (cui.add(row, column, ci)) {
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

		for (int r = 0; r < ViewCategory.count(); r++) {
			c = 0;


//			for (int c = 0; c < COLUMNS_MAX; c++) {

			for (CellView cv : cui.calcArray[r]) {

//				ci = cui.getCell(r, c);
				s = cv.toString();

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
		String response[] = new String[ViewCategory.count()];
		int c;

		sBuf.append("\n<-- Start -->");

		for (int r = 0; r < ViewCategory.count(); r++) {

			c = 0;

//			for (int c = 0; c < COLUMNS_MAX; c++) {
			for (CellView cv : cui.calcArray[r]) {

//				ci = cui.getCell(r, c);
				s = cv.toString();

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

		for (int r = 0; r < ViewCategory.count(); r++) {
			c = 0;

//			for (int c = 0; c < COLUMNS_MAX; c++) {
			for (CellView cv : cui.calcArray[r]) {
//				ci = cui.getCell(r, c);

				// returns a string representation of the
				// cell info or null if nothing
				s = cv.toStringInitOnly();

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

		StringBuilder sBuild = new StringBuilder();
		String response[] = new String[ViewCategory.count()];

		int r = 0;

		// run through each row in the array
		for (RowInfo ri : cui.calcArray) {

			// note beginning of a row = a ViewCategory
			sBuild.append("<--- Begin function Type: ")
					.append(ViewCategory.toStringOrdinal(r))
					.append(" --->\n");

			StringBuilder sb = rowInfoToStringInitOnly(ri);

			if (sb.length() != 0) {
				sBuild.append(sb);
			} else {
				sBuild.append("\n   Category is Empty\n");
			}

			// row done, note end
			sBuild.append("\n<--- end function type --->\n\n");

			// add the row to the response array
			response[r++] = sBuild.toString();

			// clear the old response
			// apparently this is the best way to clear
			sBuild.delete(0, sBuild.length());
		}

		// return the response
		return response;
	}

	public ArrayList<StringBuilder> toStringInitOnlyArrayList() {

		ArrayList<StringBuilder> sbArray = new ArrayList<>(100);
		ArrayList<StringBuilder> sbA;
		String viewCat;

		int r = 0;

		for (RowInfo ri : cui.calcArray) {

			// indicate beginning of a row = ViewCategory

			viewCat = ViewCategory.toStringOrdinal(r);

			sbArray.add(new StringBuilder("<--- Begin view category: ")
					.append(viewCat).append(" --->\n"));

			sbA = rowInfoAsArrayList(ri);

			if (sbA.size() != 0) {
				sbArray.addAll(sbA);
			} else {
				sbArray.add(new StringBuilder("\n   Category is Empty\n"));
			}

			sbArray.add(new StringBuilder("<--- End view category: ")
					.append(viewCat).append(" --->\n"));
		}
		return sbArray;
	}



	// format a single row - no header or footer
	private StringBuilder rowInfoToStringInitOnly(RowInfo ri) {

		StringBuilder sBuild = new StringBuilder();

		// run through each view in the row
		for (CellView cv : ri) {

			// format the string
			String s = cv.toStringInitOnly();

			// append if not null
			if (s != null) {

				sBuild.append("\n <---start view --->");
				sBuild.append(s);
				sBuild.append("\n <--- end view --->\n");
			}
		}

		return sBuild;
	}

	private ArrayList<StringBuilder> rowInfoAsArrayList(RowInfo ri) {

		ArrayList<StringBuilder> sbArray = new ArrayList<>(10);
		StringBuilder sBuild = new StringBuilder();

		// run through each view in the row
		for (CellView cv : ri) {

			// format the string
			String s = cv.toStringInitOnly();

			// append if not null
			if (s != null) {

				sBuild.append("\n <---start view --->");
				sBuild.append(s);
				sBuild.append("\n <--- end view --->\n");

				sbArray.add(sBuild);

				sBuild = new StringBuilder();
			}
		}

		return sbArray;
	}



	// information a single view in a cell array
	static class CellView {

		ViewType eViewType;
		int cvID;
		String cvText;
		int cvTextColor;
		int cvTextSize;
		int cvBackground;
		View cvView;
		int cvCategory;

		CellView(ViewType eViewType, View vw) {
			setCellView(eViewType, UNDEF, "", UNDEF, UNDEF, UNDEF, vw);
		}

		CellView(ViewType eViewType, int cvID, View vw) {
			setCellView(eViewType, cvID, "", UNDEF, UNDEF, UNDEF, vw);
		}

		CellView(ViewType eViewType, int cvID, String cvText,
		         int cvTextColor, int cvTextSize, int cvBackground) {
			setCellView(eViewType, cvID, cvText, cvTextColor, cvTextSize, cvBackground, null);
		}

		CellView(ViewType eViewType, int cvID, String cvText,
		         int cvTextColor, int cvTextSize, int cvBackground, View vw) {
			setCellView(eViewType, cvID, cvText, cvTextColor, cvTextSize, cvBackground, vw);
		}


		CellView() {
			clear();
		}

		void setCellView(ViewType cvViewType, int cvID, String cvText,
		                 int cvTextColor, int cvTextSize, int cvBackground, View v) {

			this.eViewType = cvViewType;
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
						cvCategory = ViewCategory.UNDEFINED.getValue();
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

		public ViewClass getViewFunction() {
			return eViewType.getViewCategory();
		}

		public int getGravity() {
			return eViewType.getGravity();
		}

		public  int getIndex() {
			return eViewType.getIndex();
		}

		public ViewType getCellType() {
			return eViewType;
		}

		public void setCellViewType(ViewType cvView) {
			this.eViewType = cvView;
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
			int COLUMN = 20;

			String preface = "\n   ";

			StringBuilder sBuild = new StringBuilder();

			if (eViewType != null) {
				// view position and type
				sBuild.append(preface)
						.append(padRight("View function: ", COLUMN))
						.append(eViewType.getViewCategory().toString());

				sBuild.append(preface)
						.append(padRight("View type: ", COLUMN))
						.append(eViewType.ordinal())
						.append(" (")
						.append(eViewType.toString()).append(")");

				sBuild.append(preface)
						.append(padRight("ID: ", COLUMN))
						.append(cvID);

				sBuild.append(preface).append(padRight("Text:", COLUMN))
						.append("\"").append(cvText).append("\"");

				sBuild.append(preface).append(padRight("Text color:", COLUMN))
						.append(cvTextColor);

				sBuild.append(preface).append(padRight("Text size: ", COLUMN))
						.append(cvTextSize);

				sBuild.append(preface).append(padRight("Background color: ", COLUMN))
						.append(cvBackground);

				sBuild.append(preface).append(padRight("View cat: ", COLUMN))
						.append(ViewCategory.toStringValue(cvCategory));

				sBuild.append(preface).append(padRight("View tag: ", COLUMN))
						.append(formatTag(cvView));
			} else {
				sBuild.append("\n*** View: not initialized");
			}

			return sBuild.toString();
		}


		// provide a string representation of a cell view
		// provide null if not initalized (i.e. viewtype == null)
		public String toStringInitOnly() {

//			logMsg("@cv toString ");

			if (eViewType == null) {
//				logMsg("view type is null");
				return null;
			}

//			logMsg(" not null");

			return toString();
		}


//
//	// array of views in a single cell
//	// minimum is 0 maximum is Views_max
//	static class CellInfo {
//
//		CellView cvPrime;
//
//		CellView[] cvArray = new CellView[VIEWS_MAX];
//
//		CellInfo() {
//			clear();
//		}
//
//		boolean addPrime(CellView cv) {
//			if (cv == null || cv.getCellType().isSubCategory()) {return false;}
//
//			cvPrime = cv;
//
//			return true;
//		}
//
//		boolean addView(CellView cv) {
//			if (cv == null || cv.getCellType().isPrimeCategory()) { return false;}
//
//			cvArray[cv.getCellType().getIndex()] = cv;
//
//			return true;
//		}
//
//		void clear() {
//			for (int i = 0; i < VIEWS_MAX; i++) {
//				cvArray[i] = new CellView();
//			}
//		}
//
//		CellView getPrime() {
//			return cvPrime;
//		}
//
//		CellView getView(int index) {
//			if (index > VIEWS_MAX || index <0) {
//				return null;
//			}
//			return cvArray[index];
//		}
//
//		CellView getView(ViewType ct) {
//
//			if (ct.isPrimeCategory()) {
//				return cvPrime;
//			}
//
//			return cvArray[ct.getIndex()];
//		}
//
//		// provide toString for all of the views contained
//		// regardless whether the view is initialized or not
//		@Override
//		public String toString() {
//
//			StringBuffer sBuf = new StringBuffer("\n<--- start views --->   ");
//
//			for (int v = 0; v < VIEWS_MAX; v++) {
//				sBuf.append(cvArray[v].toString());
//			}
//
//			sBuf.append("\n<--- end views --->   ");
//
//			return sBuf.toString();
//		}
//
//		// provide toString for all initialized views contained
//		// return null if nothing is initalized
//		public String toStringInitOnly() {
//			StringBuffer sBuf = new StringBuffer();
//			String s;
//
////			logMsg("@ci toString: ");
//
//			s = cvPrime.toStringInitOnly();
//
//			if (s != null) {
//				sBuf.append(s);
//				for (CellView cv : cvArray) {
//
////					logMsg("@cv:");
//
//					s = cv.toStringInitOnly();
//					if (s != null)
//						sBuf.append(s);
//				}
//			}
//
//			if (sBuf.length() != 0) {
//				sBuf.insert(0, "\n\n<--- start views --->   ");
//				sBuf.append("\n\n<--- end views --->   ");
//				return sBuf.toString();
//			}
//
//			return null;
//		}
//

	}

	class RowInfo implements Iterable<CellView> {

		ArrayList<CellView> alCellView;

		RowInfo() {
			alCellView = new ArrayList<>(COLUMNS_MAX);
		}

		boolean add(CellView cv) {
//			logMsg("@row info: add cell: " + cv.getView().getTag().toString());

			return alCellView.add(cv);
		}

		CellView get(int index) {
			if (index > alCellView.size() || index <0)
				return null;

			return alCellView.get(index);
		}

		public Iterator<CellView> iterator() {return new RowInfoIterator(); }

		private class RowInfoIterator implements Iterator<CellView> {
			private int cursor;

			public RowInfoIterator() { this.cursor = 0;}

			@Override
			public boolean hasNext() { return this.cursor < alCellView.size(); }

			@Override
			public CellView next() {
				if (!this.hasNext()) {
					throw new NoSuchElementException();
				}
				return alCellView.get(cursor++);
			}

			@Override
			public void remove() {throw new UnsupportedOperationException(); }
		}
	}




//
//	// array of cells in a single row
//	// minimum is 0 max is columns_max
//	class RowInfo2 implements Iterable<CellInfo> {
//
//		ArrayList<CellInfo> alCellInfo;
//
////		CellInfo[] rowArray = new CellInfo[COLUMNS_MAX];
//
//		RowInfo2() {
//			alCellInfo = new ArrayList<>(COLUMNS_MAX);
//		}
//
//
//		boolean add(CellInfo ci) {
//			logMsg("row info add cell: " + ci.getPrime().getView().getTag().toString());
//			return alCellInfo.add(ci);
//		}
//
//		CellInfo getCell(int index) {
//			if (index > alCellInfo.size() || index < 0) {
//				return null;
//			}
//			return alCellInfo.get(index);
//		}
//
//		public Iterator<CellInfo> iterator() {
//			return new RowInfoIterator();
//		}
//
//		private class RowInfoIterator implements Iterator<CellInfo> {
//
//			private int cursor;
//
//			public RowInfoIterator() {
//				this.cursor = 0;
//			}
//
//			@Override
//			public boolean hasNext() {
//				return this.cursor < alCellInfo.size();
//			}
//
//			@Override
//			public CellInfo next() {
//				if (!this.hasNext()) {
//					throw new NoSuchElementException();
//				}
//				return alCellInfo.get(cursor++);
//			}
//
//			@Override
//			public void remove() {
//				throw new UnsupportedOperationException();
//			}
//		}
//
//	}
//


	// array of rows for the whole interface
	// minimum row number is 0 max is rows_max
	class CalculatorUI {

		RowInfo[] calcArray;
		private boolean assigned = false;

		CalculatorUI() {
			if (!assigned) {
				assigned = true;
				calcArray = new RowInfo[ViewCategory.count()];

				for (int i = 0; i < ViewCategory.count(); i++) {
					calcArray[i] = new RowInfo();
				}
			}
		}

		boolean add(CellView cv) {
			int fCategory;
			int row;

//			logMsg("calcui add: " + cv.getView().getTag().toString());

			View vw = cv.getView();

			fCategory = ((iWidgetAlt) vw).getFunctionCategory();

			row = getIndex(fCategory);

			if (row < 0) { return false;}

			if (calcArray[row].add(cv))
				return true;

			return false;
		}

//		boolean add(int row, int column, CellInfo cellInfo) {
//
//			if (!verifyRowColumn(row, column)) {
//				return false;
//			}
//
//			calcArray[row].add(column, cellInfo);
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
		if (row > ViewCategory.count() || row < 0) {
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
