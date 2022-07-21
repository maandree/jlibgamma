/* See LICENSE file for copyright and license details. */
package libgamma;


/**
 * Partition state
 * <p>
 * Probably the majority of display server only one partition
 * per site. However, X.org can, and traditional used to have
 * on multi-headed environments, multiple partitions per site.
 * In X.org partitions are called 'screens'. It is not to be
 * confused with monitor. A screen is a collection of monitors,
 * and the mapping from monitors to screens is a surjection.
 * On hardware-level adjustment methods, such as Direct
 * Rendering Manager, a partition is a graphics card.
 */
public class Partition
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
	 * @param  site       The site this partition belongs to
	 * @param  partition  The index of the partition
	 */
	public Partition(Site site, int partition) throws LibgammaException
	{
		this.site = site;
		this.partition = partition;
		long[] r = libgamma_partition_create(site.address, partition);
		this.address = r[0];
		this.crtcs_available = (int)(r[1]);
		if (r[2] != 0)
			throw new LibgammaException((int)(r[2]));
	}


	/**
	 * The site this partition belongs to
	 */
	public final Site site;

	/**
	 * The index of the partition
	 */
	public final int partition;

	/**
	 * The number of CRTC:s that are available under this
	 * partition. Note that the CRTC:s are not necessarily
	 * online.
	 */
	public final int crtcs_available;

	/**
	 * The address of the native object
	 */
	final long address;


	/**
	 * Release resources
	 */
	public void close()
	{
		libgamma_partition_free(this.address);
	}

	/**
	 * Restore the gamma ramps all CRTC:s within the partition to the system settings
	 */
	public void restore() throws LibgammaException
	{
		int r = libgamma_partition_restore(this.address);
		if (r != 0)
			throw new LibgammaException(r);
	}


	/**
	 * {@inheritDoc}
	 */
	public String toString()
	{
		return "<Partition: site = " + this.site.toString() + ", " +
		       "partition = " + Integer.toString(this.partition) + ", " +
		       "crtcs_available = " + Integer.toString(this.crtcs_available) + ">";
	}


	/**
	 * Create a partition state
	 * 
	 * @param   site       The site state for the site that the partition belongs to
	 * @param   partition  The index of the partition within the site
	 * @return             Element 0: The value for {@link #address}<br>
	 *                     Element 1: The value for {@link #crtcs_available}<br>
	 *                     Element 2: Error code, zero on success
	 */
	private static native long[] libgamma_partition_create(long site, int partition);

	/**
	 * Release all resources held by a partition state
	 * and free the partition state pointer
	 * 
	 * @param  address  The partition state
	 */
	private static native void libgamma_partition_free(long address);

	/**
	 * Restore the gamma ramps all CRTC:s within a partition to the system settings
	 * 
	 * @param   address  The partition state
	 * @return           Zero on success, and error code on failure
	 */
	private static native int libgamma_partition_restore(long address);
}
