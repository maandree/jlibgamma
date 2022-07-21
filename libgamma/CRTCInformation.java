/* See LICENSE file for copyright and license details. */
package libgamma;


/**
 * Cathode ray tube controller information data structure
 */
public class CRTCInformation
{
	/**
	 * Type initialiser
	 */
	static
	{
		Libgamma.initialise();
	}


	/**
	 * For a {@link CRTCInformation} fill in the values for {@link #edid}
	 * and {@link #edid_length} and report errors to {@link #edid_error}
	 */
	public static final int EDID = 1 << 0;

	/**
	 * For a {@link CRTCInformation} fill in the value for
	 * {@link #width_mm} and report errors to {@link #width_mm_error}
	 */
	public static final int WIDTH_MM = 1 << 1;

	/**
	 * For a {@link CRTCInformation} fill in the value for
	 * {@link #height_mm} and report errors to {@link #height_mm_error}
	 */
	public static final int HEIGHT_MM = 1 << 2;

	/**
	 * For a {@link CRTCInformation} fill in the value for {@link #width_mm_edid}
	 * and report errors to {@link #width_mm_edid_error}
	 */
	public static final int WIDTH_MM_EDID = 1 << 3;

	/**
	 * For a {@link CRTCInformation} fill in the value for
	 * {@link #height_mm_edid} and report errors to {@link #height_mm_edid_error}
	 */
	public static final int HEIGHT_MM_EDID = 1 << 4;

	/**
	 * For a {@link CRTCInformation} fill in the values for
	 * {@link #red_gamma_size}, {@link #green_gamma_size} and
	 * {@link #blue_gamma_size}, and report errors to
	 * {@link #gamma_size_error}
	 */
	public static final int GAMMA_SIZE = 1 << 5;

	/**
	 * For a {@link CRTCInformation} fill in the value for
	 * {@link #gamma_depth} and report errors to {@link #gamma_depth_error}
	 */
	public static final int GAMMA_DEPTH = 1 << 6;

	/**
	 * For a {@link CRTCInformation} fill in the value for
	 * {@link #gamma_support} and report errors to {@link #gamma_support_error}
	 */
	public static final int GAMMA_SUPPORT = 1 << 7;

	/**
	 * For a {@link CRTCInformation} fill in the value for
	 * {@link #subpixel_order} and report errors to {@link #subpixel_order_error}
	 */
	public static final int SUBPIXEL_ORDER = 1 << 8;

	/**
	 * For a {@link CRTCInformation} fill in the value for
	 * {@link #active} and report errors to {@link #active_error}
	 */
	public static final int ACTIVE = 1 << 9;

	/**
	 * For a {@link CRTCInformation} fill in the value
	 * for {@link #connector_name} and report errors to
	 * {@link #connector_name_error}
	 */
	public static final int CONNECTOR_NAME = 1 << 10;

	/**
	 * For a {@link CRTCInformation} fill in the
	 * value for {@link #connector_type} and report
	 * errors to {@link #connector_type_error}
	 */
	public static final int CONNECTOR_TYPE = 1 << 11;

	/**
	 * For a {@link CRTCInformation} fill in the values for
	 * {@link #gamma_red}, {@link #gamma_green} and
	 * {@link #gamma_blue} and report errors to {@link #gamma_error}
	 */
	public static final int GAMMA = 1 << 12;


	/**
	 * The number of {@link #*} values defined
	 */
	public static final int COUNT = 13;


	/**
	 * Macro for both {@link CRTCInformation} fields
	 * that can specify the size of the monitor's viewport
	 * as specified in the monitor's Extended Display
	 * Information Data
	 */
	public static final int MACRO_EDID_VIEWPORT = WIDTH_MM_EDID | HEIGHT_MM_EDID;

	/**
	 * Macro for all {@link CRTCInformation} fields
	 * that can be filled if the adjustment method have
	 * support for reading the monitors' Extended Display
	 * Information Data
	 */
	public static final int MACRO_EDID = EDID | MACRO_EDID_VIEWPORT | GAMMA;

	/**
	 * Macro for both {@link CRTCInformation} fields
	 * that can specify the size of the monitor's viewport
	 * as provided by the adjustment method without this
	 * library having to parse the monitor's Extended Display
	 * Information Data
	 */
	public static final int MACRO_VIEWPORT = WIDTH_MM | HEIGHT_MM;

	/**
	 * Macro for the {@link CRTCInformation} fields
	 * that specifies the CRTC's gamma ramp sizes and gamma
	 * ramp depth
	 */
	public static final int MACRO_RAMP = GAMMA_SIZE | GAMMA_DEPTH;

	/**
	 * Macro for the {@link CRTCInformation} fields
	 * that specifies the CRTC's connector type and the
	 * partition unique name of the connector
	 */
	public static final int MACRO_CONNECTOR = CONNECTOR_NAME | CONNECTOR_TYPE;

	/**
	 * Macro for the {@link CRTCInformation} fields
	 * that required there is a monitor attached to
	 * the connector, and that status itself
	 */
	public static final int MACRO_ACTIVE = MACRO_EDID | MACRO_VIEWPORT | SUBPIXEL_ORDER | ACTIVE;


	/**
	 * Constructor
	 * 
	 * @param  edid            The value for {@link edid}
	 * @param  connector_name  The value for {@link connector_name}
	 * @param  gamma           The values for {@link gamma_red},
	 *                         {@link gamma_green} and {@link gamma_blue}
	 * @param  ints            Values for the rest of the variables
	 */
	public CRTCInformation(byte[] edid, String connector_name, float[] gamma, int[] ints)
	{
		this.edid = edid;
		this.connector_name = connector_name;
		this.gamma_red   = gamma[0];
		this.gamma_green = gamma[1];
		this.gamma_blue  = gamma[2];

		this.edid_error           = make_error(ints[0]);
		this.width_mm             = ints[1];
		this.width_mm_error       = make_error(ints[2]);
		this.height_mm            = ints[3];
		this.height_mm_error      = make_error(ints[4]);
		this.width_mm_edid        = ints[5];
		this.width_mm_edid_error  = make_error(ints[6]);
		this.height_mm_edid       = ints[7];
		this.height_mm_edid_error = make_error(ints[8]);
		this.red_gamma_size       = ints[9];
		this.green_gamma_size     = ints[10];
		this.blue_gamma_size      = ints[11];
		this.gamma_size_error     = make_error(ints[12]);
		this.gamma_depth          = (short)(ints[13]);
		this.gamma_depth_error    = make_error(ints[14]);
		this.gamma_support        = ints[15] != 0;
		this.gamma_support_error  = make_error(ints[16]);
		this.subpixel_order       = SubpixelOrder.VALUES[ints[17]];
		this.subpixel_order_error = make_error(ints[18]);
		this.active               = ints[19] != 0;
		this.active_error         = make_error(ints[20]);
		this.connector_name_error = make_error(ints[21]);
		this.connector_type       = ConnectorType.VALUES[ints[22]];
		this.connector_type_error = make_error(ints[23]);
		this.gamma_error          = make_error(ints[24]);

		this.has_error = (this.edid_error           != null)
		              || (this.width_mm_error       != null)
		              || (this.height_mm_error      != null)
		              || (this.width_mm_edid_error  != null)
		              || (this.height_mm_edid_error != null)
		              || (this.gamma_size_error     != null)
		              || (this.gamma_depth_error    != null)
		              || (this.gamma_support_error  != null)
		              || (this.subpixel_order_error != null)
		              || (this.connector_name_error != null)
		              || (this.connector_type_error != null);
	}


	/**
	 * Create an {@link LibgammaException} from an error code
	 * 
	 * @param   error_code  The error code
	 * @return              An {@link LibgammaException} for the error,
	 *                      {@code null} if <tt>error_code</tt> is zero
	 */
	private static LibgammaException make_error(int error_code)
	{
		return error_code == 0 ? null : new LibgammaException(error_code);
	}


	/**
	 * The Extended Display Identification Data associated with
	 * the attached monitor. This is raw byte array that is usually
	 * 128 bytes long.
	 */
	public final byte[] edid;

	/**
	 * Error that occurred when fetching the value for
	 * {@link #edid}, {@code null} on success
	 */
	public final LibgammaException edid_error;


	/**
	 * The phyical width, in millimetres, of the viewport of the
	 * attached monitor, as reported by the adjustment method. This
	 * value may be incorrect, which is a known issue with the X
	 * server where it is the result of the X server attempting
	 * the estimate the size on its own.
	 * Zero means that its is not applicable, which is the case
	 * for projectors.
	 */
	public final int width_mm;

	/**
	 * Error that occurred when fetching the value for
	 * {@link #width_mm}, {@code null} on success
	 */
	public final LibgammaException width_mm_error;


	/**
	 * The phyical height, in millimetres, of the viewport of the
	 * attached monitor, as reported by the adjustment method. This
	 * value may be incorrect, which is a known issue with the X
	 * server where it is the result of the X server attempting
	 * the estimate the size on its own.
	 * Zero means that its is not applicable, which is the case
	 * for projectors.
	 */
	public final int height_mm;

	/**
	 * Error that occurred when fetching the value for
	 * {@link #height_mm}, {@code null} on success
	 */
	public final LibgammaException height_mm_error;


	/**
	 * The phyical width, in millimetres, of the viewport of the
	 * attached monitor, as reported by it the monitor's Extended
	 * Display Information Data. This value can only contain whole
	 * centimetres, which means that the result is always zero
	 * modulus ten. However, this could change with revisions of
	 * the EDID structure.
	 * Zero means that its is not applicable, which is the case
	 * for projectors.
	 */
	public final int width_mm_edid;

	/**
	 * Error that occurred when fetching the value for
	 * {@link #width_mm_edid}, {@code null} on success
	 */
	public final LibgammaException width_mm_edid_error;


	/**
	 * The phyical height, in millimetres, of the viewport of the
	 * attached monitor, as reported by it the monitor's Extended
	 * Display Information Data. This value can only contain whole
	 * centimetres, which means that the result is always zero
	 * modulus ten. However, this could change with revisions of
	 * the EDID structure.
	 * Zero means that its is not applicable, which is the case
	 * for projectors.
	 */
	public final int height_mm_edid;

	/**
	 * Error that occurred when fetching the value for
	 * {@link #height_mm_edid}, {@code null} on success
	 */
	public final LibgammaException height_mm_edid_error;


	/**
	 * The size of the encoding axis of the red gamma ramp
	 */
	public final int red_gamma_size;

	/**
	 * The size of the encoding axis of the green gamma ramp
	 */
	public final int green_gamma_size;

	/**
	 * The size of the encoding axis of the blue gamma ramp
	 */
	public final int blue_gamma_size;

	/**
	 * Error that occurred when fetching the values for
	 * {@link #red_gamma_size}, {@link #green_gamma_size}
	 * and {@link #blue_gamma_size}, {@code null} on success
	 */
	public final LibgammaException gamma_size_error;


	/**
	 * The bit-depth of the value axes of gamma ramps,
	 * -1 for single precision floating point, and -2 for
	 * double precision floating point
	 */
	public final short gamma_depth;

	/**
	 * Error that occurred when fetching the value for
	 * {@link #gamma_depth}, {@code null} on success
	 */
	public final LibgammaException gamma_depth_error;


	/**
	 * Whether gamma ramp adjustments are supported
	 */
	public final boolean gamma_support;

	/**
	 * Error that occurred when fetching the value for
	 * {@link #gamma_support}, {@code null} on success
	 */
	public final LibgammaException gamma_support_error;


	/**
	 * The layout of the subpixels
	 * <p>
	 * You cannot count on this value — especially for CRT:s —
	 * but it is provided anyway as a means of distinguishing monitors
	 */
	public final SubpixelOrder subpixel_order;

	/**
	 * Error that occurred when fetching the value for
	 * {@link #subpixel_order}, {@code null} on success
	 */
	public final LibgammaException subpixel_order_error;


	/**
	 * Whether there is a monitors connected to the CRTC
	 */
	public final boolean active;

	/**
	 * Error that occurred when fetching the value for
	 * {@link #active}, {@code null} on success
	 */
	public final LibgammaException active_error;


	/**
	 * The name of the connector as designated by the display
	 * server or as give by this library in case the display
	 * server lacks this feature
	 */
	public final String connector_name;

	/**
	 * Error that occurred when fetching the value for
	 * {@link #connector_name}, {@code null} on success
	 */
	public final LibgammaException connector_name_error;


	/**
	 * The type of the connector that is associated with the CRTC
	 */
	public final ConnectorType connector_type;

	/**
	 * Error that occurred when fetching the value for
	 * {@link #connector_type}, {@code null} on success
	 */
	public final LibgammaException connector_type_error;


	/**
	 * The gamma characteristics of the monitor as reported
	 * in its Extended Display Information Data. The value
	 * holds the value for the red channel. If you do not have
	 * and more accurate measurement of the gamma for the
	 * monitor this could be used to give a rought gamma
	 * correction; simply divide the value with 2.2 and use
	 * the result for the red channel in the gamma correction.
	 */
	public final float gamma_red;

	/**
	 * The gamma characteristics of the monitor as reported
	 * in its Extended Display Information Data. The value
	 * holds the value for the green channel. If you do not have
	 * and more accurate measurement of the gamma for the
	 * monitor this could be used to give a rought gamma
	 * correction; simply divide the value with 2.2 and use
	 * the result for the green channel in the gamma correction.
	 */
	public final float gamma_green;

	/**
	 * The gamma characteristics of the monitor as reported
	 * in its Extended Display Information Data. The value
	 * holds the value for the blue channel. If you do not have
	 * and more accurate measurement of the gamma for the
	 * monitor this could be used to give a rought gamma
	 * correction; simply divide the value with 2.2 and use
	 * the result for the blue channel in the gamma correction.
	 */
	public final float gamma_blue;

	/**
	 * Error that occurred when fetching the values for
	 * {@link #red_gamma}, {@link #green_gamma} and
	 * {@link #blue_gamma}, {@code null} on success
	 */
	public final LibgammaException gamma_error;


	/**
	 * Whether any of the error fields are non-{@code null}
	 */
	public final boolean has_error;


	/**
	 * Helper function for {@link #toString}
	 * 
	 * @param   error  An error, may be {@code null}
	 * @return         A textual representation of <tt>error</tt>
	 */
	private static String errorToString(LibgammaException error)
	{
		return error == null ? "<null>" : error.toString();
	}


	/**
	 * {@inheritDoc}
	 */
	public String toString()
	{
		String depth_str = Integer.toString(this.gamma_depth);
		if (this.gamma_depth == -1)  depth_str = "float";
		if (this.gamma_depth == -2)  depth_str = "double";

		return "<CRTCInformation: " + 
		       "edid = " + behex(this.edid) + ", " +
		       "edid_error = " + errorToString(this.edid_error) + ", " +
	               "width_mm = " + Integer.toString(this.width_mm) + ", " +
		       "width_mm_error = " + errorToString(this.width_mm_error) + ", " +
		       "height_mm = " + Integer.toString(this.height_mm) + ", " +
		       "height_mm_error = " + errorToString(this.height_mm_error) + ", " +
		       "width_mm_edid = " + Integer.toString(this.width_mm_edid) + ", " +
		       "width_mm_edid_error = " + errorToString(this.width_mm_edid_error) + ", " +
		       "height_mm_edid = " + Integer.toString(this.height_mm_edid) + ", " +
		       "height_mm_edid_error = " + errorToString(this.height_mm_edid_error) + ", " +
		       "red_gamma_size = " + Integer.toString(this.red_gamma_size) + ", " +
		       "green_gamma_size = " + Integer.toString(this.green_gamma_size) + ", " +
		       "blue_gamma_size = " + Integer.toString(this.blue_gamma_size) + ", " +
		       "gamma_size_error = " + errorToString(this.gamma_size_error) + ", " +
		       "gamma_depth = " + depth_str + ", " +
		       "gamma_depth_error = " + errorToString(this.gamma_depth_error) + ", " +
		       "gamma_support = " + Boolean.toString(this.gamma_support) + ", " +
		       "gamma_support_error = " + errorToString(this.gamma_support_error) + ", " +
		       "subpixel_order = " + this.subpixel_order.toString() + ", " +
		       "subpixel_order_error = " + errorToString(this.subpixel_order_error) + ", " +
		       "active = " + Boolean.toString(this.active) + ", " +
		       "active_error = " + errorToString(this.active_error) + ", " +
		       "connector_name = " + (this.connector_name == null ? "<null>" : this.connector_name) + ", " +
		       "connector_name_error = " + errorToString(this.connector_name_error) + ", " +
		       "connector_type = " + this.connector_type.toString() + ", " +
		       "connector_type_error = " + errorToString(this.connector_type_error) + ", " +
		       "gamma_red = " + Float.toString(this.gamma_red) + ", " +
		       "gamma_green = " + Float.toString(this.gamma_green) + ", " +
		       "gamma_blue = " + Float.toString(this.gamma_blue) + ", " +
		       "gamma_error = " + errorToString(this.gamma_error) + ", " +
		       "has_error = " + Boolean.toString(this.has_error) + ">";
	}


	/**
	 * Convert a raw representation of an EDID to a lowercase hexadecimal representation
	 * 
	 * @param   edid    The EDID in raw representation
	 * @return          The EDID in lowercase hexadecimal representation
	 */
	public static String behex(byte[] edid)
	{
		char[] rc = new char[edid.length * 2];
		for (int i = 0; i < edid.length; i++) {
			rc[i * 2 + 0] = "0123456789abcdef".charAt((edid[i] >> 4) & 15);
			rc[i * 2 + 1] = "0123456789abcdef".charAt((edid[i] >> 0) & 15);
		}
		return new String(rc);
	}

	/**
	 * Convert an hexadecimal representation of an EDID to a raw representation
	 * 
	 * @param   edid  The EDID in hexadecimal representation
	 * @return        The EDID in raw representation, it will be half the length
	 *                of <tt>edid</tt> (the input value)
	 */
	public static byte[] unhex(String edid)
	{
		byte[] rc = new byte[edid.length() / 2];
		edid = edid.toLowerCase();
		for (int i = 0; i < rc.length; i++) {
			rc[i]  = (byte)("0123456789abcdef".indexOf(edid.charAt(i * 2 + 0)) << 4);
			rc[i] |= (byte)("0123456789abcdef".indexOf(edid.charAt(i * 2 + 1)) << 0);
		}
		return rc;
	}
}
