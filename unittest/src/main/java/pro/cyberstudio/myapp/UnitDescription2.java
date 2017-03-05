package pro.cyberstudio.myapp;

/**
 * @author jeffs
 *         File:    UnitDescription
 *         Created: 3/4/2017 @ 8:41 AM
 *         Project: CalcUI
 */

public enum UnitDescription2 {

	// special UnitType - invalid
	INVALIDdesc		("", "", "", ""),

	// special UnitType - no unit i.e. scalar number or error
	SCALARdesc		("", "", "", ""),

	// length UnitType
	// all length conversion factors are versus a millimeter
	// length UnitType codes must keep a 1 to 1 correspondence to area UnitType codes and to volume UnitType codes
	MILdesc			("mil", "","Mil", "Mils"),
	INCHdesc		("\"", "in","Inch", "Inches"),
	FOOTdesc		("\'", "ft","Foot", "Feet"),
	FATHOMdesc		("ftm", "","Fathom", "Fathoms"),
	YARDdesc		("yd", "","Yard", "Yards"),
	MILEdesc		("mi", "","Mile", "Miles"),
	NAUTICALMILEdesc("nmi", "","Nautical Mile", "Nautical Miles"),
	NANOMETERdesc	("nm", "","Nanometer", "Nanometers"),
	MICROMETERdesc	("µm", "um","Micrometer", "Micrometers"),
	MICRONdesc		("µm", "um","Micron", "Microns"),
	MILLIMETERdesc	("mm", "","Milimeter", "Milimeters"),
	CENTIMETERdesc	("cm", "","Centimeter", "Centimeters"),
	DECIMETERdesc	("dm", "","Decimeter", "Decimeters"),
	METERdesc		("m", "","Meter", "Meters"),
	DECAMETERdesc	("dam", "","Decameter", "Decameters"),
	HECTOMETERdesc	("hm", "","Hectometer", "Hectometers"),
	KILOMETERdesc	("km", "","Kilometer", "Kilometers" ),

	// area UnitType
	// all area conversion factors are versus a mm²
	// area UnitType codes must keep a 1 to 1 correspondence to length UnitType codes and to volume UnitType codes
	SQMILdesc		("mil²", "sq mil" ,"Square mil", "Square mills"),
	SQINCHdesc		("in²", "sq in" ,"Square Inch", "Square Inches"),
	SQFOOTdesc		("ft²", "sq ft" ,"Square Foot", "Square Feet"),
	SQYARDdesc		("yd²", "sq yd" ,"Square Yard", "Square Yards"),
	SQUAREdesc		("sq", "" ,"Square", "Squares"),
	ACREdesc		("ac", "" ,"Acre", "Acres"),
	SQMILEdesc		("mi²", "sq mi" ,"Square Mile", "Square Miles"),
	SECTIONdesc		("section", "" ,"Section", "Sections"),
	TOWNSHIPdesc	("twp", "" ,"Township", "Townships"),
	SQMILLIMETERdesc("mm²", "" ,"Square Milimeter", "Square Milimeters"),
	SQCENTIMETERdesc("cm²", "","Square Centimeter", "Square Centimeters"),
	SQMETERdesc		("m²", "","Square Meter", "Square Meters"),
	SQKILOMETERdesc	("km²", "","Square Kilometer", "Square Kilometers"),
	HECTAREdesc		("ha", "" ,"Hectare", "Hectares"),

	// volume UnitType
	// all area conversion factors are versus a cm³
	// volume UnitType codes must keep a 1 to 1 correspondence to length UnitType codes and to area UnitType codes
	CUINCHdesc		("in³", "cu in" ,"Cubic Inch", "Cubic Inches"),
	CUFOOTdesc		("ft³", "cu ft" ,"Cubic Foot", "Cubic Feet"),
	CUYARDdesc		("yd³", "cu yd" ,"Cubic Yard", "Cubic Yards"),
	CUMILEdesc		("mi³", "cu mi" ,"Cubic Mile", "Cubic Miles"),
	ACREFOOTdesc	("ac ft", "" ,"Acre-Foot", "Acre-Feet"),
	FLOUNCEdesc		("oz", "" ,"Ounce", "Ounces"),
	FLPINTdesc		("pt", "" ,"Pint", "Pints"),
	FLQUARTdesc		("qt", "" ,"Quart", "Quarts"),
	FLGALLONdesc	("gal", "" ,"Gallon", "Gallon"),
	MICROLITERdesc	("µl", "µL" ,"Microliter", "Microliters"),
	CUMILLIMETERdesc("mm³", "cu mm" ,"Cubic Millimeter", "Cubic Millimeters"),
	CUCENTIMETERdesc("cm³", "cu cm" ,"Cubic Centimeter", "Cubic Centimeters"),
	MILLILITERdesc	("mL", "" ,"Millileter", "Millilitters"),
	LITERdesc		("L³", "" ,"Liter", "Liters"),
	HECTOLITERdesc	("hl", "hL" ,"Hectoliter", "Hectoliters"),
	KILOLITERdesc	("kl", "kL" ,"Kiloliter", "Kiloliters"),
	CUMETERdesc		("m³", "cu m" ,"Cubic Meter", "Cubic Meters"),
	CUKILOMETERdesc	("km³", "cu mm" ,"Cubic Kilometer", "Cubic Kilometers"),

	// fourth UnitType
	// all fourth conversion factors are versus in⁴
	MOMENTOFINERTIAdesc ("in⁴", "","Moment of Inertia", "Moment of Inertia"),	// in⁴

	// mass UnitType
	// all mass conversion factors are versus a milligram
	CARATdesc		("ct", "", "Carat", "Carats"),
	OUNCEdesc		("oz", "", "Ounce", "Ounces"),
	POUNDdesc		("lb", "", "Pound", "Pounds"),
	STONEdesc		("st", "", "Stone", "Stones"),
	KIPdesc			("kip", "", "Kip", "Kips"),
	TONSHORTdesc	("ton", "", "Ton", "Tons"),
	TONLONGdesc		("", "", "Long Ton", "Long Tons"),
	MILLIGRAMdesc	("mg", "", "Milligram", "Milligrams"),
	CENTIGRAMdesc	("cg", "", "Centigram", "Centigrams"),
	DECIGRAMdesc	("dg", "", "Decigram", "Decigrams"),
	GRAMdesc		("g", "", "Gram", "Grams"),
	DEKAGRAMdesc	("dkg", "", "Dekagram", "Dekagrams"),
	HECTOGRAMdesc	("hg", "", "Hectogram", "Hectograms"),
	KILOGRAMdesc	("kg", "", "Kilogram", "Kilograms"),
	TONNEdesc		("t", "","Tonne", "Tonnes"),

	// time UnitType
	// all time conversions are factors versus a second "s"
	MILLISECONDdesc	("ms", "", "Millisecond", "Milliseconds"),
	SECONDdesc		("s", "", "Second", "Seconds"),
	MINUTEdesc		("m", "", "Minute", "Minutes"),
	HOURdesc		("h", "", "Hour", "Hours"),
	DAYdesc			("d", "", "Day", "Days"),
	WEEKdesc		("w", "", "Week", "Weeks"),
	MONTHdesc		("m", "", "Month", "Months"),
	YEARdesc		("y", "", "Year", "Years"),

	// temperature UnitType
	// all temperature conversions are based on a degree kelvin "K"
	// temperature conversion factors have special conversion codes <0 & >-10
	CELSIUSdesc		("°C", "", "Celsius", "Celsius"),
	FAHRENHEITdesc	("°F", "", "Fahrenheit", "Fahrenheit"),
	NEWTONdesc		("°N", "", "Newton", "Newton"),
	KELVINdesc		("K", "", "Kelvin", "Kelvin");


	private final String strCommonNameSingular;
	private final String strCommonNamePlural;
	private final String strCommonAbbr;
	private final String strAltAbbr;

	UnitDescription2(String cA, String aA, String cNms, String cNmp) {
		strCommonAbbr = cA;
		strAltAbbr = aA;
		strCommonNameSingular = cNms;
		strCommonNamePlural = cNmp;
	}

}
