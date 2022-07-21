/* See LICENSE file for copyright and license details. */
package libgamma;


/**
 * A single 32-bit gamma ramp
 */
public class Ramp32 extends Ramp
{
	/**
	 * Constructor
	 * 
	 * @param  address  The address of the native object
	 * @param  size     The size of the encoding axis of the gamma ramp
	 */
	Ramp32(long address, int size)
	{
		super(address, size);
	}

	/**
	 * Read the value of a stop
	 * 
	 * @param   stop  The index of the stop
	 * @return        The value of the stop
	 */
	public long get(int stop)
	{
		return libgamma_gamma_ramps32_get(this.address, stop);
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
		libgamma_gamma_ramps32_set(this.address, stop, value);
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
		libgamma_gamma_ramps32_set(this.address, stop, value);
		return value;
	}
}
