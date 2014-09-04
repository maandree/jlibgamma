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
 * Capabilities of adjustment methods.
 */
public class AdjustmentMethodCapabilities
{
    /**
     * Type initialiser.
     */
    static
    {
	Libgamma.initialise();
    }
    
    
    
    /**
     * Constructor.
     * 
     * @param  data  Low half:   the value of {@link #crtc_information}.
     *               High half:  the values of the booleanic variables.
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
     * that may (but can fail) be read successfully.
     */
    public int crtc_information;
    
    /**
     * Whether the default site is known, if true the site is integrated
     * to the system or can be determined using environment variables.
     */
    public boolean default_site_known;
    
    /**
     * Whether the adjustment method supports multiple sites rather
     * than just the default site.
     */
    public boolean multiple_sites;
    
    /**
     * Whether the adjustment method supports multiple partitions
     * per site.
     */
    public boolean multiple_partitions;
    
    /**
     * Whether the adjustment method supports multiple CRTC:s
     * per partition per site.
     */
    public boolean multiple_crtcs;
    
    /**
     * Whether the partition to graphics card is a bijection.
     */
    public boolean partitions_are_graphics_cards;
    
    /**
     * Whether the adjustment method supports {@link Site#restore}.
     */
    public boolean site_restore;
    
    /**
     * Whether the adjustment method supports {@link Partition#restore}.
     */
    public boolean partition_restore;
    
    /**
     * Whether the adjustment method supports {@link CRTC#restore}.
     */
    public boolean crtc_restore;
    
    /**
     * Whether the {@link #red_gamma_size}, {@link #green_gamma_size} and
     * {@link #blue_gamma_size} fields in {@link CRTCInformation} will
     * always have the same values as each other for the adjustment method.
     */
    public boolean identical_gamma_sizes;
    
    /**
     * Whether the {@link #red_gamma_size}, {@link #green_gamma_size} and
     * {@link #blue_gamma_size} fields in {@link CRTCInformation} will
     * always be filled with the same value for the adjustment method.
     */
    public boolean fixed_gamma_size;
    
    /**
     * Whether the {@link #gamma_depth} field in {@link CRTCInformation}
     * will always be filled with the same value for the adjustment method.
     */
    public boolean fixed_gamma_depth;
    
    /**
     * Whether the adjustment method will actually perform adjustments.
     */
    public boolean real;
    
    /**
     * Whether the adjustment method is implement using a translation layer.
     */
    public boolean fake;
    
}

