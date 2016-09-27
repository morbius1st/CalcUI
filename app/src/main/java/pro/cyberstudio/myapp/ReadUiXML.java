package pro.cyberstudio.myapp;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;

import static java.lang.Integer.parseInt;


/**
 * @author Jeff
 *         File:    readUiXML
 *         Created: 9/15/2016 @ 10:30 PM
 *         Project: Test1
 */

class ReadUiXML {

	StringBuffer sBuf = new StringBuffer();


	StringBuffer readUiFromXML(Activity activity)
			throws XmlPullParserException, IOException
	{
		boolean contFlag = true;

		Resources res = activity.getResources();

		XmlResourceParser xP = res.getXml(R.xml.calculator_ui);

		xP.next();

		int eventType = xP.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {

			switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					sBuf.append("--- start xml ---");

					eventType = xP.next();

					if (eventType != XmlPullParser.START_TAG ||
							!xP.getName().toLowerCase().equals("calculatorui"))
						contFlag = false;

					sBuf.append("\nSTART_TAG: " + xP.getName());

					break;
				case XmlPullParser.START_TAG:
					sBuf.append("\nSTART_TAG: " + xP.getName());

					if (xP.getName().toLowerCase().equals("buttonrow")) {

						int RC = parseButtonRow(xP);

						if (RC > -1) {
							sBuf.append("\nrow - column: " + RC);
						} else {
							sBuf.append("\nincorrectly formed xml");

							skipToEndTag(xP, xP.getName());
						}
					}

					break;

				case XmlPullParser.END_TAG:
					sBuf.append("\nEND_TAG: " + xP.getName());
					break;

			}

			if (contFlag) { eventType = xP.next(); }
		}

		sBuf.append("\nincorectly formed xml");
		sBuf.append("\n--- End XML ---");

		sBuf.append("\n\nresource id for cell_num_zero: " +
				activity.getResources().getIdentifier("cell_num_zero", "id",
						activity.getPackageName()) +
				"\nversus: " + R.id.zero + "" +
				"\npackage name: " + activity.getPackageName());

		return sBuf;

	}

//
//						int i = xP.getAttributeCount() - 1;
//						sBuf.append("\ncount: " + xP.getAttributeCount());
//						sBuf.append("\ncolumn: " + xP.getAttributeValue(null, "column"));
//						sBuf.append("\n<--->");
//
//						while (i >= 0) {
//							String name = xP.getAttributeName(i).toLowerCase();
//
//							sBuf.append("\nname: " + name);
//
//							switch (name) {
//								case "column":
//									sBuf.append("\ncell column is: " +
//											xP.getAttributeValue(i));
//									break;
//								case "button_click":
//									sBuf.append("\nbutton click is: " +
//											xP.getAttributeValue(i));
//									break;
//								case "text":
//									sBuf.append("\nbutton text is: " +
//											activity.getString(xP.getAttributeResourceValue(i, 0)));
//									break;
//								case "textcolor":
//									sBuf.append("\nbutton text color is: " +
//											activity.getResources().getColor(xP.getAttributeResourceValue(i, 0)));
//									break;
//								case "id":
//									String tag;
//									View v = activity.findViewById(xP.getAttributeResourceValue(i, 0));
////									v = findViewById(R.id.cell_num_zero);
//									if (v != null)
//										tag = v.getTag().toString();
//									else
//										tag = "no tag";
//
//									sBuf.append("\nview is: " + tag);
//									sBuf.append("\nvalue: " + xP.getAttributeResourceValue(i, 0));
//									sBuf.append("\nid   : " + R.id.zero);
//
//							}

//							sBuf.append("\nname: " + xP.getAttributeName(i));
//							sBuf.append("\nvalue: " + xP.getAttributeValue(i));
//							sBuf.append("\nresourceValue: " + xP.getAttributeResourceValue(i, 0));
//
//							sBuf.append("\n<--->");
//
//							i--;
//						}
//
//						sBuf.append("\nattribute count: " + xP.getAttributeCount());
//						sBuf.append("\nname: " + xP.getAttributeName(0));
//						sBuf.append("\nvalue: " + xP.getAttributeValue(0));
//						sBuf.append("\nnamespace: " + xP.getAttributeNamespace(0));
//						sBuf.append("\nnameResource: " + xP.getAttributeNameResource(0));
//						sBuf.append("\nresourceValue: " + xP.getAttributeResourceValue(0, 0));
//						sBuf.append("\nid: " + xP.getAttributeValue(null, "android:id"));
//					}
//					else if (xP.getName().equals("rootelement1")) {
//						sBuf.append("\nattribute count: " + xP.getAttributeCount());
////	not supported		sBuf.append("\ninfo: " + xP.getNamespace(null));
//						sBuf.append("\ndepth: " + xP.getDepth());
//						sBuf.append("\nget text: " + xP.getText());
//						sBuf.append("\nclass attr: " + xP.getClassAttribute());
////	not supported		sBuf.append("\nget text: " + xP.getNamespacePrefix(0));
////	not supported		sBuf.append("\nget prefix: " + xP.getPrefix());
//						sBuf.append("\nget id attr: " + xP.getIdAttribute());
////	not supported		sBuf.append("\nget ns uri: " + xP.getNamespaceUri(0));
//						sBuf.append("\nget property: " + xP.getProperty("xmlns"));
//						sBuf.append("\nget property: " + xP.getProperty("xmlns:android"));
//						sBuf.append("\nget property: " + xP.getProperty("android"));
////	not supported 		sBuf.append("\nns count: " + xP.getNamespaceCount(0));
////
////						for (int i = 0; i < xP.getNamespaceCount(xP.getDepth()); i++) {
////							sBuf.append("\nname space " + i + " = " + xP.getAttributeNamespace(i));
////						}
//
//
//
////						sBuf.append("\nname: " + xP.getAttributeName(0));
////						sBuf.append("\nvalue: " + xP.getAttributeValue(0));
////						sBuf.append("\nnamespace: " + xP.getAttributeNamespace(0));
////						sBuf.append("\nnameResource: " + xP.getAttributeNameResource(0));
////						sBuf.append("\nresourceValue: " + xP.getAttributeResourceValue(0, 0));
////						sBuf.append("\nxmlns: " + xP.getAttributeValue(null, "android"));
////						sBuf.append("\nxmlns: " + xP.getAttributeValue(null, "xmlns:android"));
////						sBuf.append("\nxmlns: " + xP.getAttributeValue("xmlns", "android"));
//				}



	private int parseButtonRow(XmlResourceParser xP) {
		int i = -1;
		int j = -1;

		try {

			sBuf.append("\natt count: " + xP.getAttributeCount());

			if (xP.getAttributeCount() == 0) return -1;

			sBuf.append("\nindex: " + xP.getAttributeValue(null, "index"));


			i = parseInt(xP.getAttributeValue(null, "index"));

			int eventType = xP.next();

			if (eventType != XmlPullParser.START_TAG) return -1;

			j = parseButtonColumn(xP);

			if (j < 0) {i = 0;}

		} catch ( Exception e) {
		}

		return i * 100 + j;


	}


	private int parseButtonColumn(XmlResourceParser xP) {
		int i;

		sBuf.append("\nSTART_TAG: " + xP.getName());
		sBuf.append("\natt count: " + xP.getAttributeCount());

		if (xP.getAttributeCount() == 0) return -1;

		sBuf.append("\nindex: " + xP.getAttributeValue(null, "index"));

		try {

			i = parseInt(xP.getAttributeValue(null, "index"));

		} catch ( Exception e) {
			i = -1;
		}

		return i;

	}

	private boolean parseButton(XmlResourceParser xP) {
		int i = xP.getAttributeCount();

		sBuf.append("\ntag name: " + xP.getName());
		sBuf.append("\natt count: " + i);

		if (i == 0) return false;

		return true;

	}

	private boolean parseSubFunction(XmlResourceParser xP) {
		int i = xP.getAttributeCount();

		sBuf.append("\ntag name: " + xP.getName());
		sBuf.append("\natt count: " + i);

		if (i == 0) return false;

		return true;


	}

	private boolean skipToEndTag(XmlResourceParser xP, String endTag) {

		try {
			int eventType = xP.next();

			while (eventType != XmlPullParser.END_DOCUMENT) {

				if (eventType == XmlPullParser.END_TAG) {
					if (xP.getName().equals(endTag)) {
						sBuf.append("\nfound: " + xP.getName());
						return true;
					}
				}

				sBuf.append("\nskipping: " + xP.getName());

				eventType = xP.next();
			}

		} catch (Exception e) {
		}
		return false;
	}

}
