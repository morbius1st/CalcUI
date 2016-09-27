package pro.cyberstudio.myapp;

import static pro.cyberstudio.myapp.Utilities.*;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * @author Jeff
 *         File:    DisplayInformation
 *         Created: 9/5/2015 @ 10:26 AM
 *         Project: Test1
 */

// X direction is left / right (i.e. width)
// Y direction is up / down (i.e. height)
@SuppressWarnings("unused")
class DisplayInformation {

	// constants
	private static final int BASE_DPI = 160;

	// base values are based on a 320dp x 640dp screen
	private static final int BASE_WIDTH_DP = 320;
	private static final int BASE_HEIGHT_DP = 640;

	// default value must match the above Width and Height orientation
	private ScreenOrientation Rotation = ScreenOrientation.PORTRAIT;

	// "real" display size in pixels (provided)
	private ScreenSize screenSize;

	private DisplayMetrics dm = new DisplayMetrics();

	// adjustment factor between the physical density and the
	// reported density - device scales based on the reported density
	// and these factors must be adjusted to compensate for this
	private float C_densityFactorAdjustment;

	// display physical density pixels per inch
	// provided by the Device
	private float D_displayXPixPerInPhysical;
	private float D_displayYPixPerInPhysical;

	// display density group DENSITY_LOW (120), DENSITY_MEDIUM (160), DENSITY_HIGH (240), etc.
	// provided by the Device
	private float D_displayDensityDpiGroup;

	// display size in dp (calculated) for text adjustments
	private int C_displaySizeXInDpPhysical;
	private int C_displaySizeYInDpPhysical;

	// display size in dp (calculated) for screen comparisons
	private int C_displaySizeXInDpGroup;
	private int C_displaySizeYInDpGroup;

	// the ratio of the reported density (DPI) versus the base density
	// this is the conversion factor between pixels and Dp
	private float C_displayXDpRatioPhysical;
	private float C_displayYDpRatioPhysical;

	// the ratio of the reported density group versus the base density
	// this is the conversion factor between pixels and Dp
	private float C_displayXDpRatioGroup;
	private float C_displayYDpRatioGroup;

	// the ratio that elements get scaled
	// this ratio is based on a standard display
	// that is BASE_WIDTH_DP high by BASE_HEIGHT_DP wide
	// ie. base text height * factor = correct height for the actual display
	private float C_displayXScaleFactorElement;
	private float C_displayYScaleFactorElement;

	// same value but based on density group
	private float C_displayXScaleFactorGroup;
	private float C_displayYScaleFactorGroup;


	DisplayInformation(Context ctx) {

//		int dx = DisplayMetrics.DENSITY_MEDIUM;
//		dx = DisplayMetrics.DENSITY_HIGH;
//		dx = DisplayMetrics.DENSITY_XHIGH;

		// get access to the application resources
		Resources r = ctx.getResources();

		// get access to the window manager
		WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		screenSize = new ScreenSize(display);

		display.getMetrics(dm);

		// hold the base width and height for later comparisons
		int intXBaseDp = BASE_WIDTH_DP;
		int intYBaseDp = BASE_HEIGHT_DP;

		// determine the display rotation
		Rotation = getScreenOrientation(wm);

		// set values depending on screen orientation
		if (Rotation.isLandscape()) {
			// swap values when landscape

			//noinspection SuspiciousNameCombination
			intXBaseDp = BASE_HEIGHT_DP;
			//noinspection SuspiciousNameCombination
			intYBaseDp = BASE_WIDTH_DP;
		}

		// per density group

		// get the display density group for the display in DPI
		D_displayDensityDpiGroup = r.getDisplayMetrics().densityDpi;

		// calculate the ratio of the density group versus the base density
		// this is how the Dp can be calculated:
		// the physical number of pixels / this ratio = the size in Dp  - or
		// the element size in Dp * this ratio = the size in pixels
		C_displayXDpRatioGroup = (D_displayDensityDpiGroup / BASE_DPI);
		C_displayYDpRatioGroup = (D_displayDensityDpiGroup / BASE_DPI);

		// calculate the size adjustment for the density group - x first
		C_displaySizeXInDpGroup = (int) (((float) screenSize.x()) / C_displayXDpRatioGroup);
		C_displayXScaleFactorGroup = ((float) C_displaySizeXInDpGroup / ((float) intXBaseDp));

		// calculate the size adjustment for the density group - then y
		C_displaySizeYInDpGroup = (int) (((float) screenSize.y()) / C_displayYDpRatioGroup);
		C_displayYScaleFactorGroup = ((float) C_displaySizeYInDpGroup / ((float) intYBaseDp));


		// per physical size

		// the exact physical pixel density as provided by the device
		D_displayXPixPerInPhysical = r.getDisplayMetrics().xdpi;
		D_displayYPixPerInPhysical = r.getDisplayMetrics().ydpi;

		// calculate the ratio of screen density versus the base density
		// this is how the Dp can be calculated:
		// the physical number of pixels / this ratio = the size in Dp  - or
		// the element size in Dp * this ratio = the size in pixels
		C_displayXDpRatioPhysical = (D_displayXPixPerInPhysical / BASE_DPI);
		C_displayYDpRatioPhysical = (D_displayYPixPerInPhysical / BASE_DPI);

		// calculate the adjustment factor between the physical density and the reported density
		C_densityFactorAdjustment = D_displayYPixPerInPhysical / D_displayDensityDpiGroup;

		// calculate the size adjustment for elements - x first
		C_displaySizeXInDpPhysical = (int) (((float) screenSize.x()) / C_displayXDpRatioPhysical);
		C_displayXScaleFactorElement = ((float) C_displaySizeXInDpPhysical / ((float) intXBaseDp)) * C_densityFactorAdjustment;

		// calculate the size adjustment for elements - then y
		C_displaySizeYInDpPhysical = (int) (((float) screenSize.y()) / C_displayYDpRatioPhysical);
		C_displayYScaleFactorElement = ((float) C_displaySizeYInDpPhysical / ((float) intYBaseDp)) * C_densityFactorAdjustment;

	}


	public float getXScaleFactorElements() {
		return C_displayXScaleFactorElement;
	}

	public float getYScaleFactorElements() {
		return C_displayYScaleFactorElement;
	}

	public int getDisplayXPix() {
		return screenSize.x();
	}

	public int getDisplayYPix() {
		return screenSize.y();
	}

	public int getAppDisplayXPix() {return dm.widthPixels;}

	public int getAppDisplayYPix() {return dm.heightPixels;}

	public float getDisplayDensityDpiGroup() { return D_displayDensityDpiGroup; }

	public float getXPixPerInPhysical() {
		return D_displayXPixPerInPhysical;
	}

	public float getYPixPerInPhysical() {
		return D_displayYPixPerInPhysical;
	}

	public int getDisplayXInDpActual() {
		return C_displaySizeXInDpPhysical;
	}

	public int getDisplayYInDpActual() {
		return C_displaySizeYInDpPhysical;
	}

	public int getDisplayXInDpGroup() {
		return C_displaySizeXInDpGroup;
	}

	public int getDisplayYInDpGroup() {
		return C_displaySizeYInDpGroup;
	}

	public int getXDpMin() {
		return (C_displaySizeXInDpPhysical < C_displaySizeXInDpGroup ? C_displaySizeXInDpPhysical : C_displaySizeXInDpGroup);
	}

	public int getYDpMin() {
		return (C_displaySizeYInDpPhysical < C_displaySizeYInDpGroup ? C_displaySizeYInDpPhysical : C_displaySizeYInDpGroup);
	}

	public float getXDpFactorPhysical() {
		return C_displayXDpRatioPhysical;
	}

	public float getYDpFactorPhysical() {
		return C_displayYDpRatioPhysical;
	}

	public float getXDpFactorGroup() {
		return C_displayXDpRatioGroup;
	}

	public float getYDpFactorGroup() {
		return C_displayYDpRatioGroup;
	}

	public ScreenOrientation getRotation() {
		return Rotation;
	}

	@Override
	public String toString() {

		String n = "\n";
		return 	"Version F" + n
				+ "Rotation: " + Rotation.name() + n + n

				+ "Display Y (pix) physical: " + screenSize.y() + n
				+ "Display X (pix) physical: " + screenSize.x() + n + n

				+ "Base Screen Size in Dp Y: " + BASE_HEIGHT_DP + n
				+ "Base Screen Size in Dp X: " + BASE_WIDTH_DP + n + n

				+ "Calc'd Screen Size in Dp Y physical: " + C_displaySizeYInDpPhysical + n
				+ "Calc'd Screen Size in Dp X physical: " + C_displaySizeXInDpPhysical + n + n

				+ "Calc'd Screen Size in Dp Y density group: " + C_displaySizeYInDpGroup + n
				+ "Calc'd Screen Size in Dp X density group: " + C_displaySizeXInDpGroup + n + n

				+ "dm: density: " + dm.density + n
				+ "dm: density Dpi: " + dm.densityDpi + n
				+ "dm: height in Pix: " + dm.heightPixels + n
				+ "dm: width in Pix: " + dm.widthPixels + n
				+ "dm: text scl factor: " + dm.scaledDensity + n
				+ "dm: Y ppi: " + dm.ydpi + n
				+ "dm: X ppi: " + dm.xdpi + n + n

				+ "Reported ppi Y physical: " + D_displayYPixPerInPhysical + n
				+ "Reported ppi X physical: " + D_displayXPixPerInPhysical + n
				+ "Reported DPI (density group): " + D_displayDensityDpiGroup + n
				+ "Adj fact phys. vs. reported density: " + C_densityFactorAdjustment + n + n

				+ "pix to Dp Fact Y physical: " + C_displayYDpRatioPhysical + n
				+ "Factor Y element **: " + C_displayYScaleFactorElement + n + n

				+ "pix to Dp Fact X physical: " + C_displayXDpRatioPhysical + n
				+ "Factor X element: " + C_displayXScaleFactorElement + n + n

				+ "pix to Dp Fact Y group: " + C_displayYDpRatioGroup + n
				+ "Factor Y group: " + C_displayYScaleFactorGroup + n + n

				+ "pix to Dp Fact X group: " + C_displayXDpRatioGroup + n
				+ "Factor X group: " + C_displayXScaleFactorGroup
				;
	}

	<T extends TextView> void adjustViewTextSize(T v) {
		v.setTextSize(TypedValue.COMPLEX_UNIT_PX, v.getTextSize() * C_displayYScaleFactorElement);
	}

	public int convertXDpToPix(int Dp) {
		return ((int) (((float) Dp) * C_displayXDpRatioGroup));
	}

	public int convertYDpToPix(int Dp) {
		return ((int) (((float) Dp) * C_displayYDpRatioGroup));
	}

	/**
	 * Get the screen's current real orientation
	 * @param wm	a WindowsManager
	 * @return		a ScreenOrientation
	 */
	private ScreenOrientation getScreenOrientation(WindowManager wm) {
		int rotation = wm.getDefaultDisplay().getRotation();

		ScreenOrientation orientation;

		if (((rotation == Surface.ROTATION_0
				|| rotation == Surface.ROTATION_180) && screenSize.y() > screenSize.x()) ||
				((rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270)
						&& screenSize.x() > screenSize.y())) {
			switch (rotation) {
				case Surface.ROTATION_0:
					orientation = ScreenOrientation.PORTRAIT;
					break;
				case Surface.ROTATION_90:
					orientation = ScreenOrientation.LANDSCAPE;
					break;
				case Surface.ROTATION_180:
					orientation = ScreenOrientation.REVERSE_PORTRAIT;
					break;
				case Surface.ROTATION_270:
					orientation = ScreenOrientation.REVERSE_LANDSCAPE;
					break;
				default:
					logMsg("Unknown screen orientation.  Defaulting to portrait.");
					orientation = ScreenOrientation.PORTRAIT;
					break;
			}
		} else {
			switch (rotation) {
				case Surface.ROTATION_0:
					orientation = ScreenOrientation.LANDSCAPE;
					break;
				case Surface.ROTATION_90:
					orientation = ScreenOrientation.PORTRAIT;
					break;
				case Surface.ROTATION_180:
					orientation = ScreenOrientation.REVERSE_LANDSCAPE;
					break;
				case Surface.ROTATION_270:
					orientation = ScreenOrientation.REVERSE_PORTRAIT;
					break;
				default:
					logMsg("Unknown screen orientation.  Defaulting to landscape.");
					orientation = ScreenOrientation.LANDSCAPE;
					break;
			}
		}
		return orientation;
	}

	public enum ScreenOrientation {
		PORTRAIT			(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT),
		LANDSCAPE			(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE),
		REVERSE_PORTRAIT	(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT),
		REVERSE_LANDSCAPE	(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE)
		;

		private final  int direction;

		ScreenOrientation(int o) {
			this.direction = o;
		}

		boolean isPortrait() {
			return (this == PORTRAIT || this == REVERSE_PORTRAIT);
		}

		boolean isLandscape() {
			return (this == LANDSCAPE || this == REVERSE_LANDSCAPE);
		}
	}


	/**
	 * Get the actual, real, screen size in pixels
	 */
	private class ScreenSize {

		// the x dimension of the screen (left to right)
		private int x;
		// the y dimension of the screen (top to bottom)
		private int y;

		ScreenSize(Display display) {
			// a point
			Point size = new Point();

			// get the default screen size (< sdk = 14)
			display.getSize(size);
			x = size.x;
			y = size.y;

			if (Build.VERSION.SDK_INT >= 17){
				display.getRealSize(size);
				x = size.x;
				y = size.y;

			} else if (Build.VERSION.SDK_INT >= 14) {
				try {
					Display.class.getMethod("getRealSize", Point.class).invoke(display, size);
					x = size.x;
					y = size.y;
				} catch (Exception ignore) {
				}
			}

		}

		int x() {
			return x;
		}

		int y() {
			return y;
		}

	}
}
