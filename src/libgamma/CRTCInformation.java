/**
 * jlibgamma — Display server abstraction layer for gamma ramp and Java
 * Copyright © 2014  Mattias Andrée (maandree@member.fsf.org)
 * 
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 */
package libgamma;


/**
 * Cathode ray tube controller information data structure.
 */
public class CRTCInformation
{
    /**
     * For a {@link CRTCInformation} fill in the values for {@link #edid}
     * and {@link #edid_length} and report errors to {@link #edid_error}.
     */
    public static final int EDID = 1 << 0;
    
    /**
     * For a {@link CRTCInformation} fill in the value for
     * {@link #width_mm} and report errors to {@link #width_mm_error}.
     */
    public static final int WIDTH_MM = 1 << 1;
    
    /**
     * For a {@link CRTCInformation} fill in the value for
     * {@link #height_mm} and report errors to {@link #height_mm_error}.
     */
    public static final int HEIGHT_MM = 1 << 2;
    
    /**
     * For a {@link CRTCInformation} fill in the value for {@link #width_mm_edid}
     * and report errors to {@link #width_mm_edid_error}.
     */
    public static final int WIDTH_MM_EDID = 1 << 3;
    
    /**
     * For a {@link CRTCInformation} fill in the value for
     * {@link #height_mm_edid} and report errors to {@link #height_mm_edid_error}.
     */
    public static final int HEIGHT_MM_EDID = 1 << 4;
    
    /**
     * For a {@link CRTCInformation} fill in the values for
     * {@link #red_gamma_size}, {@link #green_gamma_size} and
     * {@link #blue_gamma_size}. and report errors to
     * {@link #gamma_size_error}.
     */
    public static final int GAMMA_SIZE = 1 << 5;
    
    /**
     * For a {@link CRTCInformation} fill in the value for
     * {@link #gamma_depth} and report errors to {@link #gamma_depth_error}.
     */
    public static final int GAMMA_DEPTH = 1 << 6;
    
    /**
     * For a {@link CRTCInformation} fill in the value for
     * {@link #gamma_support} and report errors to {@link #gamma_support_error}.
     */
    public static final int GAMMA_SUPPORT = 1 << 7;
    
    /**
     * For a {@link CRTCInformation} fill in the value for
     * {@link #subpixel_order} and report errors to {@link #subpixel_order_error}.
     */
    public static final int SUBPIXEL_ORDER = 1 << 8;
    
    /**
     * For a {@link CRTCInformation} fill in the value for
     * {@link #active} and report errors to {@link #active_error}.
     */
    public static final int ACTIVE = 1 << 9;
    
    /**
     * For a {@link CRTCInformation} fill in the value
     * for {@link #connector_name} and report errors to
     * {@link #connector_name_error}.
     */
    public static final int CONNECTOR_NAME = 1 << 10;
    
    /**
     * For a {@link CRTCInformation} fill in the
     * value for {@link #connector_type} and report
     * errors to {@link #connector_type_error}.
     */
    public static final int CONNECTOR_TYPE = 1 << 11;
    
    /**
     * For a {@link CRTCInformation} fill in the values for
     * {@link #gamma_red}, {@link #gamma_green} and
     * {@link #gamma_blue} and report errors to {@link #gamma_error}.
     */
    public static final int GAMMA = 1 << 12;
    
    
    /**
     * The number of {@link #*} values defined.
     */
    public static final int COUNT = 13;
    
    
    /**
     * Macro for both {@link CRTCInformation} fields
     * that can specify the size of the monitor's viewport
     * as specified in the monitor's Extended Display
     * Information Data.
     */
    public static final int MACRO_EDID_VIEWPORT = WIDTH_MM_EDID | HEIGHT_MM_EDID;
    
    /**
     * Macro for all {@link CRTCInformation} fields
     * that can be filled if the adjustment method have
     * support for reading the monitors' Extended Display
     * Information Data.
     */
    public static final int MACRO_EDID = EDID | MACRO_EDID_VIEWPORT | GAMMA;
    
    /**
     * Macro for both {@link CRTCInformation} fields
     * that can specify the size of the monitor's viewport
     * as provided by the adjustment method without this
     * library having to parse the monitor's Extended Display
     * Information Data.
     */
    public static final int MACRO_VIEWPORT = WIDTH_MM | HEIGHT_MM;
    
    /**
     * Macro for the {@link CRTCInformation} fields
     * that specifies the CRTC's gamma ramp sizes and gamma
     * ramp depth.
     */
    public static final int MACRO_RAMP = GAMMA_SIZE | GAMMA_DEPTH;
    
    /**
     * Macro for the {@link CRTCInformation} fields
     * that specifies the CRTC's connector type and the
     * partition unique name of the connector.
     */
    public static final int MACRO_CONNECTOR = CONNECTOR_NAME | CONNECTOR_TYPE;
    
    /**
     * Macro for the {@link CRTCInformation} fields
     * that required there is a monitor attached to
     * the connector, and that status itself.
     */
    public static final int MACRO_ACTIVE = MACRO_EDID | MACRO_VIEWPORT | SUBPIXEL_ORDER | ACTIVE;
    
    
    
    /**
     * Constructor.
     * 
     * @param  edid            The value for {@link edid}.
     * @param  connector_name  The value for {@link connector_name}.
     * @param  gamma           The values for {@link gamma_red},
     *                         {@link gamma_green} and {@link gamma_blue}.
     * @param  ints            Values for the rest of the variables.
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
	this.gamma_support        = ints[15];
	this.gamma_support_error  = make_error(ints[16]);
	this.subpixel_order       = SubpixelOrder.VALUES[ints[17]];
	this.subpixel_order_error = make_error(ints[18]);
	this.active               = ints[19];
	this.active_error         = make_error(ints[20]);
	this.connector_name_error = make_error(ints[21]);
	this.connector_type       = ConnectorType.VALUES[ints[22]];
	this.connector_type_error = make_error(ints[23]);
	this.gamma_error          = make_error(ints[24]);
    }
    
    
    /**
     * Create an {@link LibgammaException} from an error code.
     * 
     * @param   error_code  The error code.
     * @return              An {@link LibgammaException} for the error,
     *                      {@code null} if <tt>error_code</tt> is zero.
     */
    private static LibgammaException make_error(int error_code)
    {
	if (error_code == 0)
	    return null;
	else
	    return new LibgammaException(error_code);
    }
    
    
    
    /**
     * The Extended Display Identification Data associated with
     * the attached monitor. This is raw byte array that is usually
     * 128 bytes long.
     */
    public final byte[] edid;
    
    /**
     * Error that occurred when fetching the value for
     * {@link #edid}, {@code null} on success.
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
     * {@link #width_mm}, {@code null} on success.
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
     * {@link #height_mm}, {@code null} on success.
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
     * {@link #width_mm_edid}, {@code null} on success.
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
     * {@link #height_mm_edid}, {@code null} on success.
     */
    public final LibgammaException height_mm_edid_error;
    
    
    /**
     * The size of the encoding axis of the red gamma ramp.
     */
    public final int red_gamma_size;
    
    /**
     * The size of the encoding axis of the green gamma ramp.
     */
    public final int green_gamma_size;
    
    /**
     * The size of the encoding axis of the blue gamma ramp.
     */
    public final int blue_gamma_size;
    
    /**
     * Error that occurred when fetching the values for
     * {@link #red_gamma_size}, {@link #green_gamma_size}
     * and {@link #blue_gamma_size}, {@code null} on success.
     */
    public final LibgammaException gamma_size_error;
    
    
    /**
     * The bit-depth of the value axes of gamma ramps,
     * -1 for single precision floating point, and -2 for
     * double precision floating point.
     */
    public final short gamma_depth;
    
    /**
     * Error that occurred when fetching the value for
     * {@link #gamma_depth}, {@code null} on success.
     */
    public final LibgammaException gamma_depth_error;
    
    
    /**
     * Non-zero gamma ramp adjustments are supported.
     */
    public final int gamma_support;
    
    /**
     * Error that occurred when fetching the value for
     * {@link #gamma_support}, {@code null} on success.
     */
    public final LibgammaException gamma_support_error;
    
    
    /**
     * The layout of the subpixels.
     * You cannot count on this value --- especially for CRT:s ---
     * but it is provided anyway as a means of distinguishing monitors.
     */
    public final SubpixelOrder subpixel_order;
    
    /**
     * Error that occurred when fetching the value for
     * {@link #subpixel_order}, {@code null} on success.
     */
    public final LibgammaException subpixel_order_error;
    
    
    /**
     * Whether there is a monitors connected to the CRTC.
     */
    public final int active;
    
    /**
     * Error that occurred when fetching the value for
     * {@link #active}, {@code null} on success.
     */
    public final LibgammaException active_error;
    
    
    /**
     * The name of the connector as designated by the display
     * server or as give by this library in case the display
     * server lacks this feature.
     */
    public final String connector_name;
    
    /**
     * Error that occurred when fetching the value for
     * {@link #connector_name}, {@code null} on success.
     */
    public final LibgammaException connector_name_error;
    
    
    /**
     * The type of the connector that is associated with the CRTC.
     */
    public final ConnectorType connector_type;
    
    /**
     * Error that occurred when fetching the value for
     * {@link #connector_type}, {@code null} on success.
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
     * {@link #blue_gamma}, {@code null} on success.
     */
    public final LibgammaException gamma_error;
    
}

