/* See LICENSE file for copyright and license details. */
package libgamma;


/**
 * A single 16-bit gamma ramp
 */
public class Ramp16 extends Ramp
{
	/**
	 * Type initialiser
	 */
	static
	{
		Libgamma.initialise();
	}


	/**
	 * Constructor
	 * 
	 * @param  address  The address of the native object
	 * @param  size     The size of the encoding axis of the gamma ramp
	 */
	Ramp16(long address, int size)
	{
		super(address, size);
	}

	/**
	 * Read the value of a stop
	 * 
	 * @param   stop  The index of the stop
	 * @return        The value of the stop
	 */
	public int get(int stop)
	{
		return libgamma_gamma_ramps16_get(this.address, stop);
	}

	/**
	 * Read the value of a stop
	 * 
	 * @param   stop   The index of the stop
	 * @param   value  The new value of the stop
	 * @return         The new value of the stop
	 */
	public long set(int stop, long value)
	{
		libgamma_gamma_ramps16_set(this.address, stop, (int)value);
		return value;
	}

	/**
	 * Read the value of a stop
	 * 
	 * @param   stop   The index of the stop
	 * @param   value  The new value of the stop
	 * @return         The new value of the stop
	 */
	public int set(int stop, int value)
	{
		libgamma_gamma_ramps16_set(this.address, stop, value);
		return value;
	}

	/**
	 * Read the value of a stop
	 * 
	 * @param   stop   The index of the stop
	 * @param   value  The new value of the stop
	 * @return         The new value of the stop
	 */
	public short set(int stop, short value)
	{
		libgamma_gamma_ramps16_set(this.address, stop, value);
		return value;
	}
}
