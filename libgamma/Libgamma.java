/* See LICENSE file for copyright and license details. */
package libgamma;


/**
 * Library initialisation class
 */
class Libgamma
{
	/**
	 * Initialise the library
	 */
	static void initialise()
	{
		if (Libgamma.initialised)
			return;
		Libgamma.initialised = true;

		try {
			System.loadLibrary("gamma-java");
		} catch (Throwable err) {
			throw new Error(err);
		}
	}

	/**
	 * Whether {@link #initialise} has been invoked
	 */
	private static boolean initialised = false;
}
