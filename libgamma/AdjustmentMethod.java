/* See LICENSE file for copyright and license details. */
package libgamma;


/**
 * Class of adjustment methods
 */
public enum AdjustmentMethod
{
	/**
	 * The identifier for the dummy adjustment method.
	 * This method can be configured and is useful for
	 * testing your program's ability to handle errors.
	 */
	DUMMY(0),

	/**
	 * The identifier for the adjustment method with
	 * uses the RandR protocol under the X display server
	 */
	X_RANDR(1),

	/**
	 * The identifier for the adjustment method with
	 * uses the VidMode protocol under the X display server.
	 * This is an older alternative to RandR that can
	 * work on some drivers that are not supported by RandR,
	 * however it can only control the primary CRTC per
	 * screen (partition).
	 */
	X_VIDMODE(2),

	/**
	 * The identifier for the Direct Rendering Manager
	 * adjustment method that is available in Linux
	 * (built in to the Linux kernel with a userland
	 * library for access) and is a part of the
	 * Direct Rendering Infrastructure. This adjustment
	 * method all work when you are in non-graphical
	 * mode; however a display server cannnot be
	 * started while this is running, but it can be
	 * started while a display server is running.
	 */
	LINUX_DRM(3),

	/**
	 * The identifier for the Graphics Device Interface
	 * adjustment method that is available in Windows.
	 * This method is not well tested; it can be compiled
	 * to be available under X.org using a translation layer.
	 */
	W32_GDI(4),

	/**
	 * The identifier for the CoreGraphics adjustment
	 * method that is available in Mac OS X that can
	 * adjust gamma ramps under the Quartz display server.
	 * This method is not well tested; it can be compiled
	 * to be available under X.org using a translation layer.
	 */
	QUARTZ_CORE_GRAPHICS(5);


	/**
	 * Type initialiser
	 */
	static
	{
		Libgamma.initialise();
	}


	/**
	 * The index of the last gamma method, neither it
	 * nor any index before it may actually be supported
	 * as it could have been disabled at compile-time
	 */
	public static final int MAX = 5;

	/**
	 * The number adjustment methods provided by this library.
	 * Note however that this includes adjstment methods that
	 * have been removed at compile-time
	 */
	public static final int COUNT = MAX + 1;

	/**
	 * Adjustment methods by their numerical values
	 */
	public static AdjustmentMethod[] VALUES =
	{
		DUMMY, X_RANDR, X_VIDMODE, LINUX_DRM, W32_GDI, QUARTZ_CORE_GRAPHICS
	};


	/**
	 * Constructor
	 * 
	 * @param  value  The numerical value of the adjustment method
	 */
	private AdjustmentMethod(int value)
	{
		this.value = value;
	}


	/**
	 * The numerical value of the adjustment method
	 */
	public final int value;


	/**
	 * Check whether the adjustment method is available
	 * 
	 * @return  Whether the adjustment method is available
	 */
	public boolean is_available()
	{
		return libgamma_is_method_available(this.value) != 0;
	}

	/**
	 * Get the default site for the adjustment method
	 * 
	 * @return  The default site for the adjustment method
	 */
	public String get_default_site()
	{
		return libgamma_method_default_site(this.value);
	}

	/**
	 * Get the default variable that determines the default
	 * site for the adjustment method
	 * 
	 * @return  default  variable that determines the default
	 *                   site for the adjustment method
	 */
	public String get_default_site_variable()
	{
		return libgamma_method_default_site_variable(this.value);
	}

	/**
	 * Return the capabilities of the adjustment method
	 * 
	 * @return   The capabilities of the adjustment method
	 */
	public AdjustmentMethodCapabilities get_capabilities()
	{
		return new AdjustmentMethodCapabilities(libgamma_method_capabilities(this.value));
	}
    

	/**
	 * List available adjustment methods by their order of preference based on the environment
	 * 
	 * @param   operation  Allowed values:<br>
	 *                       0: Methods that the environment suggests will work, excluding fake<br>
	 *                       1: Methods that the environment suggests will work, including fake<br>
	 *                       2: All real non-fake methods<br>
	 *                       3: All real methods<br>
	 *                       4: All methods<br>
	 *                     Other values invoke undefined behaviour
	 * @return             List available adjustment methods by their order of preference
	 */
	public static AdjustmentMethod[] list_methods(int operation)
	{
		int[] methods = libgamma_list_methods(operation);
		AdjustmentMethod[] rc = new AdjustmentMethod[methods.length];
		for (int i = 0; i < methods.length; i++)
			rc[i] = VALUES[methods[i]];
		return rc;
	}


	/**
	 * List available adjustment methods by their order of preference based on the environment
	 * 
	 * @param   operation  Allowed values:<br>
	 *                       0: Methods that the environment suggests will work, excluding fake<br>
	 *                       1: Methods that the environment suggests will work, including fake<br>
	 *                       2: All real non-fake methods<br>
	 *                       3: All real methods<br>
	 *                       4: All methods<br>
	 *                     Other values invoke undefined behaviour
	 * @return             List available adjustment methods by their order of preference
	 */
	private static native int[] libgamma_list_methods(int operation);

	/**
	 * Check whether an adjustment method is available, non-existing (invalid) methods will
	 * be identified as not available under the rationale that the library may be out of date
	 * 
	 * @param   method  The adjustment method
	 * @return          Whether the adjustment method is available
	 */
	private static native int libgamma_is_method_available(int method);

	/**
	 * Return the capabilities of an adjustment method
	 * 
	 * @param   method  The adjustment method (display server and protocol)
	 * @return          Input parameter to the constructor of {@link AdjustmentMethodCapabilities}
	 */
	private static native long libgamma_method_capabilities(int method);

	/**
	 * Return the default site for an adjustment method
	 * 
	 * @param   method  The adjustment method (display server and protocol)
	 * @return          The default site, {@code null} if it cannot be determined or
	 *                  if multiple sites are not supported by the adjustment method
	 */
	private static native String libgamma_method_default_site(int method);

	/**
	 * Return the default variable that determines
	 * the default site for an adjustment method
	 * 
	 * @param   method  The adjustment method (display server and protocol)
	 * @return          The environ variables that is used to determine the
	 *                  default site; {@code null} if there is none, that is,
	 *                  if the method does not support multiple sites
	 */
	private static native String libgamma_method_default_site_variable(int method);
}
