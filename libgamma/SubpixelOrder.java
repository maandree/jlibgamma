/* See LICENSE file for copyright and license details. */
package libgamma;


/**
 * Orders for subpixels. Currently the possible values are
 * very biased to LCD, Plasma and monochrome monitors
 */
public enum SubpixelOrder
{
	/**
	 * The adjustment method does not know the order of the subpixels
	 * <p>
	 * (This could be considered an error)
	 */
	UNKNOWN(0),

	/**
	 * There are no subpixels in the monitor
	 */
	NONE(1),

	/**
	 * The subpixels are ordered red, green and then blue, from left to right
	 */
	HORIZONTAL_RGB(2),

	/**
	 * The subpixels are ordered blue, green and then red, from left to right
	 */
	HORIZONTAL_BGR(3),

	/**
	 * The subpixels are ordered red, green and then blue, from the top down
	 */
	VERTICAL_RGB(4),

	/**
	 * The subpixels are ordered blue, green and then red, from the top down
	 */
	VERTICAL_BGR(5);


	/**
	 * Type initialiser
	 */
	static
	{
		Libgamma.initialise();
	}


	/**
	 * Subpixel orders by their numerical values
	 */
	public static SubpixelOrder[] VALUES =
	{
		UNKNOWN, NONE, HORIZONTAL_RGB, HORIZONTAL_BGR, VERTICAL_RGB, VERTICAL_BGR
	};


	/**
	 * Constructor
	 * 
	 * @param  value  The numerical value of the subpixel order
	 */
	private SubpixelOrder(int value)
	{
		this.value = value;
	}


	/**
	 * The numerical value of the subpixel order
	 */
	public int value;
}
