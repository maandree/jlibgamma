/* See LICENSE file for copyright and license details. */
package libgamma;


/**
 * Capabilities of adjustment methods
 */
public class AdjustmentMethodCapabilities
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
	 * @param  data  Low half:  the value of {@link #crtc_information}<br>
	 *               High half: the values of the booleanic variables
	 */
	AdjustmentMethodCapabilities(long data)
	{
		this.crtc_information = (int)data;

		this.default_site_known            = (data & (1L << 33L)) != 0;
		this.multiple_sites                = (data & (1L << 34L)) != 0;
		this.multiple_partitions           = (data & (1L << 35L)) != 0;
		this.multiple_crtcs                = (data & (1L << 36L)) != 0;
		this.partitions_are_graphics_cards = (data & (1L << 37L)) != 0;
		this.site_restore                  = (data & (1L << 38L)) != 0;
		this.partition_restore             = (data & (1L << 39L)) != 0;
		this.crtc_restore                  = (data & (1L << 40L)) != 0;
		this.identical_gamma_sizes         = (data & (1L << 41L)) != 0;
		this.fixed_gamma_size              = (data & (1L << 42L)) != 0;
		this.fixed_gamma_depth             = (data & (1L << 43L)) != 0;
		this.real                          = (data & (1L << 44L)) != 0;
		this.fake                          = (data & (1L << 45L)) != 0;
	}


	/**
	 * OR of the CRTC information fields in {@link CRTCInformation}
	 * that may (but can fail) be read successfully
	 */
	public int crtc_information;

	/**
	 * Whether the default site is known, if true the site is integrated
	 * to the system or can be determined using environment variables
	 */
	public boolean default_site_known;

	/**
	 * Whether the adjustment method supports multiple sites rather
	 * than just the default site
	 */
	public boolean multiple_sites;

	/**
	 * Whether the adjustment method supports multiple partitions
	 * per site
	 */
	public boolean multiple_partitions;

	/**
	 * Whether the adjustment method supports multiple CRTC:s
	 * per partition per site
	 */
	public boolean multiple_crtcs;

	/**
	 * Whether the partition to graphics card is a bijection
	 */
	public boolean partitions_are_graphics_cards;

	/**
	 * Whether the adjustment method supports {@link Site#restore}
	 */
	public boolean site_restore;

	/**
	 * Whether the adjustment method supports {@link Partition#restore}
	 */
	public boolean partition_restore;

	/**
	 * Whether the adjustment method supports {@link CRTC#restore}
	 */
	public boolean crtc_restore;

	/**
	 * Whether the {@link #red_gamma_size}, {@link #green_gamma_size} and
	 * {@link #blue_gamma_size} fields in {@link CRTCInformation} will
	 * always have the same values as each other for the adjustment method
	 */
	public boolean identical_gamma_sizes;

	/**
	 * Whether the {@link #red_gamma_size}, {@link #green_gamma_size} and
	 * {@link #blue_gamma_size} fields in {@link CRTCInformation} will
	 * always be filled with the same value for the adjustment method
	 */
	public boolean fixed_gamma_size;

	/**
	 * Whether the {@link #gamma_depth} field in {@link CRTCInformation}
	 * will always be filled with the same value for the adjustment method
	 */
	public boolean fixed_gamma_depth;

	/**
	 * Whether the adjustment method will actually perform adjustments
	 */
	public boolean real;

	/**
	 * Whether the adjustment method is implement using a translation layer
	 */
	public boolean fake;


	/**
	 * {@inheritDoc}
	 */
	public String toString()
	{
		return "<AdjustmentMethodCapabilities: " + 
		       "information = " + Integer.toString(this.crtc_information) + ", " +
		       "default_site_known = " + Boolean.toString(this.default_site_known) + ", " +
		       "multiple_sites = " + Boolean.toString(this.multiple_sites) + ", " +
		       "multiple_partitions = " + Boolean.toString(this.multiple_partitions) + ", " +
		       "multiple_crtcs = " + Boolean.toString(this.multiple_crtcs) + ", " +
		       "partitions_are_graphics_cards = " + Boolean.toString(this.partitions_are_graphics_cards) + ", " +
		       "site_restore = " + Boolean.toString(this.site_restore) + ", " +
		       "partition_restore = " + Boolean.toString(this.partition_restore) + ", " +
		       "crtc_restore = " + Boolean.toString(this.crtc_restore) + ", " +
		       "identical_gamma_sizes = " + Boolean.toString(this.identical_gamma_sizes) + ", " +
		       "fixed_gamma_size = " + Boolean.toString(this.fixed_gamma_size) + ", " +
		       "fixed_gamma_depth = " + Boolean.toString(this.fixed_gamma_depth) + ", " +
		       "real = " + Boolean.toString(this.real) + ", " +
		       "fake = " + Boolean.toString(this.fake) + ">";
	}
}
