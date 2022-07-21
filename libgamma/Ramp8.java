/* See LICENSE file for copyright and license details. */
package libgamma;


/**
 * A single 8-bit gamma ramp
 */
public class Ramp8 extends Ramp
{
	/**
	 * Constructor
	 * 
	 * @param  address  The address of the native object
	 * @param  size     The size of the encoding axis of the gamma ramp
	 */
	Ramp8(long address, int size)
	{
		super(address, size);
	}

	/**
	 * Read the value of a stop
	 * 
	 * @param   stop  The index of the stop
	 * @return        The value of the stop
	 */
	public short get(short stop)
	{
		return libgamma_gamma_ramps8_get(this.address, stop);
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
		libgamma_gamma_ramps8_set(this.address, stop, (short)value);
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
		libgamma_gamma_ramps8_set(this.address, stop, (short)value);
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
		libgamma_gamma_ramps8_set(this.address, stop, value);
		return value;
	}

	/**
	 * Read the value of a stop
	 * 
	 * @param   stop   The index of the stop
	 * @param   value  The new value of the stop
	 * @return         The new value of the stop
	 */
	public byte set(int stop, byte value)
	{
		libgamma_gamma_ramps8_set(this.address, stop, value);
		return value;
	}
}
