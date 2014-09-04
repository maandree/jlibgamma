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
 * <p>
 *   Class of <tt>libgamma</tt> exceptions including
 *   native system exceptions.
 * </p>
 * <p>
 *   The class contains constants with <tt>libgamma</tt> error
 *   codes. It does however not contain system error codes.
 * </p>
 */
public class LibgammaException extends Exception
{
    /**
     * This error code is never used. It is only provided
     * so you know its error code if you plan to iterate
     * over all <tt>libgamma</tt> error codes.
     */
    public static final int LIBGAMMA_ERRNO_SET = -1;
    
    /**
     * The selected adjustment method does not exist
     * or has been excluded at compile-time.
     */
    public static final int LIBGAMMA_NO_SUCH_ADJUSTMENT_METHOD = -2;
    
    /**
     * The selected site does not exist.
     */
    public static final int LIBGAMMA_NO_SUCH_SITE = -3;
    
    /**
     * The selected partition does not exist.
     */
    public static final int LIBGAMMA_NO_SUCH_PARTITION = -4;
    
    /**
     * The selected CRTC does not exist.
     */
    public static final int LIBGAMMA_NO_SUCH_CRTC = -5;
    
    /**
     * Counter overflowed when counting the number
     * of available items.
     */
    public static final int LIBGAMMA_IMPOSSIBLE_AMOUNT = -6;
    
    /**
     * The selected connector is disabled, it does
     * not have a CRTC.
     */
    public static final int LIBGAMMA_CONNECTOR_DISABLED = -7;
    
    /**
     * The selected CRTC could not be opened,
     * reason unknown.
     */
    public static final int LIBGAMMA_OPEN_CRTC_FAILED = -8;
    
    /**
     * The CRTC information field is not supported
     * by the adjustment method.
     */
    public static final int LIBGAMMA_CRTC_INFO_NOT_SUPPORTED = -9;
    
    /**
     * Failed to read the current gamma ramps for
     * the selected CRTC, reason unknown.
     */
    public static final int LIBGAMMA_GAMMA_RAMP_READ_FAILED = -10;
    
    /**
     * Failed to write the current gamma ramps for
     * the selected CRTC, reason unknown.
     */
    public static final int LIBGAMMA_GAMMA_RAMP_WRITE_FAILED = -11;
    
    /**
     * The specified ramp sizes does not match the
     * ramps sizes returned by the adjustment methods
     * in response to the query/command.
     */
    public static final int LIBGAMMA_GAMMA_RAMP_SIZE_CHANGED = -12;
    
    /**
     * The specified ramp sizes are not identical
     * which is required by the adjustment method.
     * (Only returned in debug mode.)
     */
    public static final int LIBGAMMA_MIXED_GAMMA_RAMP_SIZE = -13;
    
    /**
     * The specified ramp sizes are not supported
     * by the adjustment method.
     * (Only returned in debug mode.)
     */
    public static final int LIBGAMMA_WRONG_GAMMA_RAMP_SIZE = -14;
    
    /**
     * The adjustment method reported that the gamma
     * ramps size is 1, or perhaps even zero or negative.
     */
    public static final int LIBGAMMA_SINGLETON_GAMMA_RAMP = -15;
    
    /**
     * The adjustment method failed to list
     * available CRTC:s, reason unknown.
     */
    public static final int LIBGAMMA_LIST_CRTCS_FAILED = -16;
    
    /**
     * Failed to acquire mode resources from the
     * adjustment method.
     */
    public static final int LIBGAMMA_ACQUIRING_MODE_RESOURCES_FAILED = -17;
    
    /**
     * The adjustment method reported that a negative
     * number of partitions exists in the site.
     */
    public static final int LIBGAMMA_NEGATIVE_PARTITION_COUNT = -18;
    
    /**
     * The adjustment method reported that a negative
     * number of CRTC:s exists in the partition.
     */
    public static final int LIBGAMMA_NEGATIVE_CRTC_COUNT = -19;
    
    /**
     * Device cannot be access becauses of
     * insufficient permissions.
     */
    public static final int LIBGAMMA_DEVICE_RESTRICTED = -20;
    
    /**
     * Device cannot be access, reason unknown.
     */
    public static final int LIBGAMMA_DEVICE_ACCESS_FAILED = -21;
    
    /**
     * Device cannot be access, membership of the
     * {@link #group_gid} (named by {@link #group_name}
     * (can be {@code null}, if so <tt>errno</tt> may have
     * been set to tell why)) is required.
     */
    public static final int LIBGAMMA_DEVICE_REQUIRE_GROUP = -22;
    
    /**
     * The graphics card appear to have been removed.
     */
    public static final int LIBGAMMA_GRAPHICS_CARD_REMOVED = -23;
    
    /**
     * The state of the requested information is unknown.
     */
    public static final int LIBGAMMA_STATE_UNKNOWN = -24;
    
    /**
     * Failed to determine which connector the
     * CRTC belongs to.
     */
    public static final int LIBGAMMA_CONNECTOR_UNKNOWN = -25;
    
    /**
     * The detected connector type is not listed
     * in this library and has to be updated.
     */
    public static final int LIBGAMMA_CONNECTOR_TYPE_NOT_RECOGNISED = -26;
    
    /**
     * The detected subpixel order is not listed
     * in this library and has to be updated.
     */
    public static final int LIBGAMMA_SUBPIXEL_ORDER_NOT_RECOGNISED = -27;
    
    /**
     * The length of the EDID does not match that
     * of any supported EDID structure revision.
     */
    public static final int LIBGAMMA_EDID_LENGTH_UNSUPPORTED = -28;
    
    /**
     * The magic number in the EDID does not match
     * that of any supported EDID structure revision.
     */
    public static final int LIBGAMMA_EDID_WRONG_MAGIC_NUMBER = -29;
    
    /**
     * The EDID structure revision used by the
     * mointor is not supported.
     */
    public static final int LIBGAMMA_EDID_REVISION_UNSUPPORTED = -30;
    
    /**
     * The gamma characteristics field in the EDID
     * is left unspecified.
     * (This could be considered a non-error.)
     */
    public static final int LIBGAMMA_GAMMA_NOT_SPECIFIED = -31;
    
    /**
     * The checksum in the EDID is incorrect, all
     * request information has been provided
     * by you cannot count on it.
     */
    public static final int LIBGAMMA_EDID_CHECKSUM_ERROR = -32;
    
    /**
     * Both of the errors {@link #LIBGAMMA_GAMMA_NOT_SPECIFIED}
     * and {@link #LIBGAMMA_EDID_CHECKSUM_ERROR} have occurred.
     */
    public static final int LIBGAMMA_GAMMA_NOT_SPECIFIED_AND_EDID_CHECKSUM_ERROR = -33;
    
    /**
     * Failed to query the gamma ramps size from the
     * adjustment method, reason unknown.
     */
    public static final int LIBGAMMA_GAMMA_RAMPS_SIZE_QUERY_FAILED = -34;
    
    /**
     * The selected partition could not be opened,
     * reason unknown.
     */
    public static final int LIBGAMMA_OPEN_PARTITION_FAILED = -35;
    
    /**
     * The selected site could not be opened,
     * reason unknown.
     */
    public static final int LIBGAMMA_OPEN_SITE_FAILED = -36;
    
    /**
     * Failed to query the adjustment method for
     * its protocol version, reason unknown.
     */
    public static final int LIBGAMMA_PROTOCOL_VERSION_QUERY_FAILED = -37;
    
    /**
     * The adjustment method's version of its
     * protocol is not supported.
     */
    public static final int LIBGAMMA_PROTOCOL_VERSION_NOT_SUPPORTED = -38;
    
    /**
     * The adjustment method failed to list
     * available partitions, reason unknown.
     */
    public static final int LIBGAMMA_LIST_PARTITIONS_FAILED = -39;
    
    /**
     * Partition exists by index, but the partition
     * at that index does not exist.
     */
    public static final int LIBGAMMA_NULL_PARTITION = -40;
    
    /**
     * There is not mointor connected to the
     * connector of the selected CRTC.
     */
    public static final int LIBGAMMA_NOT_CONNECTED = -41;
    
    /**
     * Data extraction from a reply from the
     * adjustment method failed, reason unknown.
     */
    public static final int LIBGAMMA_REPLY_VALUE_EXTRACTION_FAILED = -42;
    
    /**
     * No EDID property was found on the output.
     */
    public static final int LIBGAMMA_EDID_NOT_FOUND = -43;
    
    /**
     * Failed to list properties on the output,
     * reason unknown.
     */
    public static final int LIBGAMMA_LIST_PROPERTIES_FAILED = -44;
    
    /**
     * Failed to query a property's value from
     * the output, reason unknown.
     */
    public static final int LIBGAMMA_PROPERTY_VALUE_QUERY_FAILED = -45;
    
    /**
     * A request for information on an output
     * failed, reason unknown.
     */
    public static final int LIBGAMMA_OUTPUT_INFORMATION_QUERY_FAILED = -46;
    
    
    /**
     * The number of the libgamma error with the
     * lowest number. If this is lower than the
     * number your program thinks it should be sould
     * update your program for new errors.
     */
    public static final int LIBGAMMA_ERROR_MIN = -46;
    
    
    
    /**
     * Constructor.
     * 
     * @param  error_code  The error code.
     */
    public LibgammaException(int error_code)
    {
	this.error_code = error_code;
	if (error_code == LIBGAMMA_DEVICE_REQUIRE_GROUP)
	{   this.group_gid = libgamma_group_gid();
	    this.group_name = libgamma_group_name();
	}
	else
	{   this.group_gid = 0;
	    this.group_name = null;
	}
	if (error_code < 0)
	    this.string = name_of_error(error_code);
	else
	    this.string = strerror(error_code);
    }
    
    
    /**
     * The error code.
     * Cannot be {@link #LIBGAMMA_ERRNO_SET}.
     */
    public final int error_code;
    
    /**
     * Group that the user needs to be a member of if
     * {@link #error_code} is {@link #LIBGAMMA_DEVICE_REQUIRE_GROUP}.
     */
    public final int group_gid;
    
    /**
     * Group that the user needs to be a member of if
     * {@link #error_code} is {@link #LIBGAMMA_DEVICE_REQUIRE_GROUP},
     * {@code null} if the name of the group {@link #group_gid}
     * cannot be determined.
     */
    public final String group_name;
    
    /**
     * Name of textual description of the error.
     */
    public final String string;
    
    
    
    /**
     * Returns the name of the definition associated with
     * a <tt>libgamma</tt> error code.
     * 
     * @param   value  The error code.
     * @return         The name of the definition associated with the error code,
     *                 {@code null} if the error code does not exist.
     */
    public static native String name_of_error(int value);
    
    /**
     * Return the value of a <tt>libgamma</tt> error definition
     * refered to by name.
     * 
     * @param   name  The name of the definition associated with the error code.
     * @return        The error code, zero if the name is {@code null}
     *                or does not refer to a <tt>libgamma</tt> error.
     */
    public static native int value_of_error(String name);
    
    
    /**
     * Acquire the value that should go to {@link #group_gid}.
     * 
     * @return  The value that should go to {@link #group_gid}.
     */
    private static native int libgamma_group_gid();
    
    /**
     * Acquire the value that should go to {@link #group_name}.
     * 
     * @return  The value that should go to {@link #group_name}.
     */
    private static native String libgamma_group_name();
    
    /**
     * Get a textual description of a system error code.
     * 
     * @param   error_code  The error code.
     * @return              A textual description of the error code.
     */
    private static native String strerror(int error_code);
    
}

