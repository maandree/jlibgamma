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
 * Site state.
 * 
 * On operating systems that integrate a graphical environment
 * there is usually just one site. However, one systems with
 * pluggable graphics, like Unix-like systems such as GNU/Linux
 * and the BSD:s, there can usually be any (feasible) number of
 * sites. In X.org parlance they are called displays.
 */
public class Site
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
     * @param  method  The adjustmet method.
     * @param  site    The site identifier.
     */
    public Site(AdjustmentMethod method, String site) throws LibgammaException
    {
	this.method = method;
	this.site = site;
	long[] r = libgamma_site_create(method.value, site);
	this.address = r[0];
	this.partitions_available = (int)(r[1]);
	if (r[2] != 0)
	    throw new LibgammaException((int)(r[2]));
    }
    
    
    /**
     * This field specifies, for the methods if this library,
     * which adjustment method (display server and protocol)
     * is used to adjust the gamma ramps.
     */
    public final AdjustmentMethod method;
    
    /**
     * The site identifier. It can either be {@code null} or
     * a string. {@code null} indicates the default site. On
     * systems like the Unix-like systems, where the graphics
     * are pluggable, this is usually resolved by an environment
     * variable, such as "DISPLAY" for X.org.
     */
    public final String site;
    
    /**
     * The number of partitions that is available on this site.
     * Probably the majority of display server only one partition
     * per site. However, X.org can, and traditional used to have
     * on multi-headed environments, multiple partitions per site.
     * In X.org partitions are called 'screens'. It is not to be
     * confused with monitor. A screen is a collection of monitors,
     * and the mapping from monitors to screens is a surjection.
     * On hardware-level adjustment methods, such as Direct
     * Rendering Manager, a partition is a graphics card.
     */
    public final int partitions_available;
    
    /**
     * The address of the native object.
     */
    final long address;
    
    
    /**
     * Release resources.
     */
    public void close()
    {
	libgamma_site_free(this.address);
    }
    
    /**
     * Restore the gamma ramps all CRTC:s within the site to the system settings.
     */
    public void restore() throws LibgammaException
    {
	int r = libgamma_site_restore(this.address);
	if (r != 0)
	    throw new LibgammaException(r);
    }
    
    
    /**
     * {@inheritDoc}
     */
    public String toString()
    {
	return "<Site: method = " + this.method.toString() + ", " +
	                "site = " + (this.site == null ? "<null>" : this.site) + ", " +
        "partitions_available = " + Integer.toString(this.partitions_available) + ">";
    }
    
    
    
    
    /**
     * Create a site state.
     * 
     * @param   method  The adjustment method (display server and protocol.)
     * @param   site    The site identifier.
     * @return          Element 0:  The value for {@link #address}.
     *                  Element 1:  The value for {@link #partitions_available}
     *                  Element 2:  Error code, zero on success.
     */
    private static native long[] libgamma_site_create(int method, String site);
    
    /**
     * Release all resources held by a site state
     * and free the site state pointer.
     * 
     * @param  address  The site state.
     */
    private static native void libgamma_site_free(long address);
    
    /**
     * Restore the gamma ramps all CRTC:s within a site to the system settings.
     * 
     * @param   address  The site state.
     * @return           Zero on success, and error code on failure.
     */
    private static native int libgamma_site_restore(long address);
    
}

