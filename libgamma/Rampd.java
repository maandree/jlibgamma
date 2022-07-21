/* See LICENSE file for copyright and license details. */
package libgamma;


/**
 * A single double percision floating point gamma ramp
 */
public class Rampd extends Ramp
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
	Rampd(long address, int size)
	{
		super(address, size);
	}

	/**
	 * Read the value of a stop
	 * 
	 * @param   stop  The index of the stop
	 * @return        The value of the stop
	 */
	public double get(int stop)
	{
		return libgamma_gamma_rampsd_get(this.address, stop);
	}

	/**
	 * Read the value of a stop
	 * 
	 * @param   stop   The index of the stop
	 * @param   value  The new value of the stop
	 * @return         The new value of the stop
	 */
	public float set(int stop, float value)
	{
		libgamma_gamma_rampsd_set(this.address, stop, value);
		return value;
	}

	/**
	 * Read the value of a stop
	 * 
	 * @param   stop   The index of the stop
	 * @param   value  The new value of the stop
	 * @return         The new value of the stop
	 */
	public double set(int stop, double value)
	{
		libgamma_gamma_rampsd_set(this.address, stop, value);
		return value;
	}
}
