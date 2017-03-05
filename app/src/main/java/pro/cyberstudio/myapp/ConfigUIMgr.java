package pro.cyberstudio.myapp;

import android.annotation.SuppressLint;
import android.view.*;
import android.widget.*;


import java.util.*;

import static pro.cyberstudio.myapp.ConfigUIMgr.ViewType.*;
import static pro.cyberstudio.myapp.ConfigUIMgr.ViewClass.*;
import static pro.cyberstudio.myapp.ConfigUIMgr.ViewCategory.*;
import static pro.cyberstudio.myapp.Utilities.*;

//import static pro.cyberstudio.myapp.Utilities.*;

/**
 * @author Jeff
 *         File:    configUI
 *         Created: 9/17/2016 @ 6:28 AM
 *         Project: Test1
 */
@SuppressLint("RtlHardcoded")
@SuppressWarnings("WeakerAccess UnusedDeclaration")

public class ConfigUIMgr {

	static final int VIEWS_MAX = 9;
	static final int COLUMNS_MAX = 7;
	static final int UNDEF = -1;

	static int idx = 0;

	private DisplayInformation DI;
	private GridLayoutGravity GLG = new GridLayoutGravity();
	private static ConfigUI cui;

	private static boolean assigned = false;

	ConfigUIMgr(DisplayInformation DI) {

		this.DI = DI;

		if (!assigned) {
			assigned = true;
			cui = new ConfigUI();
		}
	}

	public void addView(View vw, int viewID) {
		ViewType ct = ViewType.findViewTypeByView(vw);

		if (ct == null) {return;}

		cui.add(new ViewInfo(ct, vw, viewID));

		ViewParent vp = vw.getParent();

		if (vp instanceof GridLayout) {
			int i = ((GridLayout) vp).getChildCount();

			for (int j = 0; j < ((GridLayout) vp).getChildCount(); j++) {

				View vChild = ((GridLayout) vp).getChildAt(j);

				if (vChild.getId() == viewID) {
					continue;
				}

				adjustChildView(vChild);

				ct = findViewTypeByGravity(GLG.getGravity(vChild));

				if (ct != null) {
					cui.add(new ViewInfo(ct, vChild, viewID));
				}
			}
		}
	}


	RowInfo getViews(ViewCategory vc) {
		return cui.calcArray[vc.ordinal()];
	}



	boolean modifyView(int viewID, ViewType viewType,
	                   int textColor, int textSize, int background, String text) {

		return false;
	}

	void adjustChildView (View vChild) {

		if (vChild != null) {
			if (vChild instanceof TextViewAlt) {
				DI.adjustViewTextSize((TextView) vChild);
			}
		}
	}


	@Override
	public String toString() {

		ArrayList<StringBuilder> sbArray;
		StringBuilder sb = new StringBuilder("\nListing may be incomplete\n");

		sbArray = toStringArrayList();

		for (StringBuilder s : sbArray) {
			sb.append(s);
		}

		return sb.toString();
	}

	public ArrayList<StringBuilder> toStringArrayList() {

		ArrayList<StringBuilder> sbArray = new ArrayList<>(100);
		ArrayList<StringBuilder> sbA;
		String viewCat;

		int r = 0;

		for (RowInfo ri : cui.calcArray) {

			// indicate beginning of a row = ViewCategory

			viewCat = ViewCategory.toStringOrdinal(r++);

			sbArray.add(new StringBuilder("<--- Begin view category: ")
					.append(viewCat).append(" --->\n"));

			sbA = rowInfoToArrayList(ri);

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

	public ArrayList<StringBuilder> toStringRowInfo(int index) {
		return rowInfoToArrayList(cui.calcArray[index]);
	}

	private ArrayList<StringBuilder> rowInfoToArrayList(RowInfo ri) {

		ArrayList<StringBuilder> sbArray = new ArrayList<>(10);
		StringBuilder sBuild = new StringBuilder();

		// run through each view in the row
		for (ViewInfo cv : ri) {

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

	// information a single view
	static class ViewInfo {

		ViewType viewType;
		int viewID;
		String text;
		int textColor;
		int textSize;
		int background;
		iViewAlt view;
		int category;

		ViewInfo(ViewType viewType, View view, int viewID) {
			setCellView(viewType, view, viewID, UNDEF, UNDEF, UNDEF, "");
		}

		ViewInfo(ViewType viewType, int viewID, int textColor, int textSize, int background, String text) {
			setCellView(viewType, null, viewID, textColor, textSize, background, text);
		}

		ViewInfo(ViewType viewType, View view, int viewID, int parentID, int textColor, int textSize, int background, String text) {
			setCellView(viewType, view, viewID, textColor, textSize, background, text);
		}


		ViewInfo() {
			clear();
		}

		void setCellView(ViewType viewType, View view, int viewID, int textColor, int textSize, int background, String text) {

			this.viewType = viewType;

			this.viewID = viewID;
			this.textColor = textColor;
			this.textSize = textSize;
			this.background = background;
			this.text = text;

			String tag = "null";

			if (view != null) {

				switch (findViewTypeByView(view)) {
					case BUTTON:
					case TEXTVIEW:
					case IMAGEBUTTON:
						this.view = (iViewAlt) view;
						category = this.view.getFunctionCategory();
						break;
					default:
						category = ViewCategory.UNDEFINED.getValue();
						break;
				}
			}

		}

		void clear() {
			setCellView(null, null, UNDEF, UNDEF, UNDEF, UNDEF, null);
		}

		boolean isPrimeClass() {
			return viewType.isPrimeClass();
		}

		boolean isSubClass() {
			return viewType.isSubClass();
		}

		public iViewAlt setView(View view) {
			switch (findViewTypeByView(view)) {
				case BUTTON:
				case TEXTVIEW:
				case IMAGEBUTTON:
					this.view = (iViewAlt) view;
					return this.view;
				default:
					return null;
			}
		}

		public iViewAlt getView() {
			return view;
		}

		public ViewClass getViewFunction() {
			return viewType.getViewCategory();
		}

		public int getGravity() {
			return viewType.getGravity();
		}

		public  int getIndex() {
			return viewType.getIndex();
		}

		public ViewType getViewType() {
			return viewType;
		}

		public void setCellViewType(ViewType cvView) {
			this.viewType = cvView;
		}

		public int getViewID() {
			return viewID;
		}

		public void setViewID(int cvID) {
			this.viewID = cvID;
		}

		public String getText() {
			return text;
		}

		public void setText(String cvText) {
			this.text = cvText;
		}

		public int getTextColor() {
			return textColor;
		}

		public void setTextColor(int cvTextColor) {
			this.textColor = cvTextColor;
		}

		public int getTextSize() {
			return textSize;
		}

		public void setTextSize(int cvTextSize) {
			this.textSize = cvTextSize;
		}

		public int getBackground() {
			return background;
		}

		public void setBackground(int cvBackground) {
			this.background = cvBackground;
		}

		@Override
		public String toString() {
			int COLUMN = 20;

			String preface = "\n   ";

			StringBuilder sBuild = new StringBuilder();

			if (viewType != null) {
				// view position and type
				sBuild.append(preface)
						.append(padRight("View function: ", COLUMN))
						.append(viewType.getViewCategory().toString());

				sBuild.append(preface)
						.append(padRight("View type: ", COLUMN))
						.append(viewType.ordinal())
						.append(" (")
						.append(viewType.toString()).append(")");

				if (isPrimeClass()) {
					sBuild.append(preface)
							.append(padRight("primeID: ", COLUMN))
							.append(viewID);
				} else {
					sBuild.append(preface)
							.append(padRight("parentID: ", COLUMN))
							.append(viewID);
				}


				sBuild.append(preface).append(padRight("Text:", COLUMN))
						.append("\"").append(text).append("\"");

				sBuild.append(preface).append(padRight("Text color:", COLUMN))
						.append(textColor);

				sBuild.append(preface).append(padRight("Text size: ", COLUMN))
						.append(textSize);

				sBuild.append(preface).append(padRight("Background color: ", COLUMN))
						.append(background);

				sBuild.append(preface).append(padRight("View cat: ", COLUMN))
						.append(ViewCategory.toStringValue(category));

				sBuild.append(preface).append(padRight("View tag: ", COLUMN))
						.append(Functions.formatTag(view));
			} else {
				sBuild.append("\n*** View: not initialized");
			}

			return sBuild.toString();
		}


		// provide a string representation of a cell view
		// provide null if not initalized (i.e. viewtype == null)
		public String toStringInitOnly() {
			if (viewType == null) {
				return null;
			}
			return toString();
		}
	}

	class RowInfo implements Iterable<ViewInfo> {

		ArrayList<ViewInfo> alViewInfo;

		RowInfo() {
			alViewInfo = new ArrayList<>(COLUMNS_MAX);
		}

		boolean add(ViewInfo cv) {
			return alViewInfo.add(cv);
		}

		ViewInfo get(int index) {
			if (index > alViewInfo.size() || index <0)
				return null;

			return alViewInfo.get(index);
		}

		public Iterator<ViewInfo> iterator() {return new RowInfoIterator(); }

		private class RowInfoIterator implements Iterator<ViewInfo> {
			private int cursor;

			public RowInfoIterator() { this.cursor = 0;}

			@Override
			public boolean hasNext() { return this.cursor < alViewInfo.size(); }

			@Override
			public ViewInfo next() {
				if (!this.hasNext()) {
					throw new NoSuchElementException();
				}
				return alViewInfo.get(cursor++);
			}

			@Override
			public void remove() {throw new UnsupportedOperationException(); }
		}
	}

	// array of rows for the whole interface
	// minimum row number is 0 max is rows_max
	class ConfigUI {

		RowInfo[] calcArray;
		private boolean assigned = false;

		ConfigUI() {
			if (!assigned) {
				assigned = true;
				calcArray = new RowInfo[ViewCategory.count()];

				for (int i = 0; i < ViewCategory.count(); i++) {
					calcArray[i] = new RowInfo();
				}
			}
		}

		boolean add(ViewInfo cv) {
			int fCategory;
			int row;

			iViewAlt vw = cv.getView();

			fCategory = vw.getFunctionCategory();

			row = getIndex(fCategory);

			if (row < 0) { return false;}

			if (calcArray[row].add(cv))
				return true;

			return false;
		}
	}

	// note that this must correspond 1 to 1 with the attrs.xml file for the views
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

		private ViewClass functionClass;
		private int gravity;
		private int arrayIndex;

		ViewType(int i, ViewClass vF, int g) {
			functionClass = vF;
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

		boolean isPrimeClass() {
			return functionClass == PRIME_FUNCT;
		}

		boolean isSubClass() {
			return functionClass == SUB_FUNCT;
		}

		int getGravity() {
			return gravity;
		}

		ViewClass getViewCategory() {
			return functionClass;
		}

		int getIndex() {
			return arrayIndex;
		}
	}
}
