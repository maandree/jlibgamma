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
 * Gamma ramp structure.
 * 
 * @param  <T>  The ramp class, should be select in accordance
 *              with the <tt>depth</tt> parameter of the constructor:
 *              <p>
 *               8:  Ramp8<br/>
 *              16:  Ramp16<br/>
 *              32:  Ramp32<br/>
 *              64:  Ramp64<br/>
 *              -1:  Rampf<br/>
 *              -2:  Rampd<br/>
 *              </p>
 */
public class GammaRamps<T extends Ramp>
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
     * @param  red_size    The size of the encoding axis of the red gamma ramp.
     * @param  green_size  The size of the encoding axis of the green gamma ramp.
     * @param  blue_size   The size of the encoding axis of the blue gamma ramp.
     * @param  depth       The bit-depth of the value axes of gamma ramps,
     *                     -1 for single precision floating point, and -2 for
     *                     double precision floating point.
     */
    @SuppressWarnings("unchecked")
    public GammaRamps(int red_size, int green_size, int blue_size, short depth) throws LibgammaException
    {
	long[] r;
	if      (depth ==  8)  r = libgamma_gamma_ramps8_create (red_size, green_size, blue_size);
	else if (depth == 16)  r = libgamma_gamma_ramps16_create(red_size, green_size, blue_size);
	else if (depth == 32)  r = libgamma_gamma_ramps32_create(red_size, green_size, blue_size);
	else if (depth == 64)  r = libgamma_gamma_ramps64_create(red_size, green_size, blue_size);
	else if (depth == -1)  r = libgamma_gamma_rampsf_create (red_size, green_size, blue_size);
	else if (depth == -2)  r = libgamma_gamma_rampsd_create (red_size, green_size, blue_size);
	else
	    throw new IllegalArgumentException("depth must be either of: 8, 16, 32, 64, -1, -2.");
	
	if (r[4] != 0)
	    throw new LibgammaException((int)(r[4]));
	
	this.address = r[0];
	this.depth = depth;
	
	Ramp red   = null;
	Ramp green = null;
	Ramp blue  = null;
	
	if      (depth ==  8)  red   = (Ramp)(new Ramp8 (r[1],   red_size));
	else if (depth == 16)  red   = (Ramp)(new Ramp16(r[1],   red_size));
	else if (depth == 32)  red   = (Ramp)(new Ramp32(r[1],   red_size));
	else if (depth == 64)  red   = (Ramp)(new Ramp64(r[1],   red_size));
	else if (depth == -1)  red   = (Ramp)(new Rampf (r[1],   red_size));
	else if (depth == -2)  red   = (Ramp)(new Rampd (r[1],   red_size));
	
	if      (depth ==  8)  green = (Ramp)(new Ramp8 (r[2], green_size));
	else if (depth == 16)  green = (Ramp)(new Ramp16(r[2], green_size));
	else if (depth == 32)  green = (Ramp)(new Ramp32(r[2], green_size));
	else if (depth == 64)  green = (Ramp)(new Ramp64(r[2], green_size));
	else if (depth == -1)  green = (Ramp)(new Rampf (r[2], green_size));
	else if (depth == -2)  green = (Ramp)(new Rampd (r[2], green_size));
	
	if      (depth ==  8)  blue  = (Ramp)(new Ramp8 (r[3],  blue_size));
	else if (depth == 16)  blue  = (Ramp)(new Ramp16(r[3],  blue_size));
	else if (depth == 32)  blue  = (Ramp)(new Ramp32(r[3],  blue_size));
	else if (depth == 64)  blue  = (Ramp)(new Ramp64(r[3],  blue_size));
	else if (depth == -1)  blue  = (Ramp)(new Rampf (r[3],  blue_size));
	else if (depth == -2)  blue  = (Ramp)(new Rampd (r[3],  blue_size));
	
	this.red   = (T)red;
	this.green = (T)green;
	this.blue  = (T)blue;
    }
    
    
    /**
     * The gamma ramp for the red channel.
     */
    public final T red;
    
    /**
     * The gamma ramp for the green channel.
     */
    public final T green;
    
    /**
     * The gamma ramp for the blue channel.
     */
    public final T blue;
    
    /**
     * The bit-depth of the value axes of gamma ramps,
     * -1 for single precision floating point, and -2 for
     * double precision floating point.
     */
    public final short depth;
    
    /**
     * The address of the native object.
     */
    final long address;
    
    
    /**
     * Release resources.
     */
    public void close()
    {
	if      (this.depth ==  8)  libgamma_gamma_ramps8_free(this.address);
	else if (this.depth == 16)  libgamma_gamma_ramps16_free(this.address);
	else if (this.depth == 32)  libgamma_gamma_ramps32_free(this.address);
	else if (this.depth == 64)  libgamma_gamma_ramps64_free(this.address);
	else if (this.depth == -1)  libgamma_gamma_rampsf_free(this.address);
	else if (this.depth == -2)  libgamma_gamma_rampsd_free(this.address);
    }
    
    
    /**
     * {@inheritDoc}
     */
    public String toString()
    {
	String depth_str = Integer.toString(this.depth);
	if (this.depth == -1)  depth_str = "float";
	if (this.depth == -1)  depth_str = "double";
	
	return "<GammaRamps: depth = " + depth_str + ", " +
	                      "red = " + this.red.toString() +
	                    "green = " + this.green.toString() +
	                     "blue = " + this.blue.toString() + ">";
    }
    
    
    /**
     * Create and initialise a gamma ramp in the proper way that allows all adjustment
     * methods to read from and write to it without causing segmentation violation.
     * 
     * @param   red_size    The size of the encoding axis of the red gamma ramp.
     * @param   green_size  The size of the encoding axis of the green gamma ramp.
     * @param   blue_size   The size of the encoding axis of the blue gamma ramp.
     * @return              Element 0:  The address of the native object.
     *                      Element 1:  The address of the red gamma ramp.
     *                      Element 2:  The address of the green gamma ramp.
     *                      Element 3:  The address of the blue gamma ramp.
     *                      Element 4:  Zero on success, an error code on error.
     */
    private static native long[] libgamma_gamma_ramps8_create(int red_size, int green_size, int blue_size);
    
    /**
     * Create and initialise a gamma ramp in the proper way that allows all adjustment
     * methods to read from and write to it without causing segmentation violation.
     * 
     * @param   red_size    The size of the encoding axis of the red gamma ramp.
     * @param   green_size  The size of the encoding axis of the green gamma ramp.
     * @param   blue_size   The size of the encoding axis of the blue gamma ramp.
     * @return              Element 0:  The address of the native object.
     *                      Element 1:  The address of the red gamma ramp.
     *                      Element 2:  The address of the green gamma ramp.
     *                      Element 3:  The address of the blue gamma ramp.
     *                      Element 4:  Zero on success, an error code on error.
     */
    private static native long[] libgamma_gamma_ramps16_create(int red_size, int green_size, int blue_size);
    
    /**
     * Create and initialise a gamma ramp in the proper way that allows all adjustment
     * methods to read from and write to it without causing segmentation violation.
     * 
     * @param   red_size    The size of the encoding axis of the red gamma ramp.
     * @param   green_size  The size of the encoding axis of the green gamma ramp.
     * @param   blue_size   The size of the encoding axis of the blue gamma ramp.
     * @return              Element 0:  The address of the native object.
     *                      Element 1:  The address of the red gamma ramp.
     *                      Element 2:  The address of the green gamma ramp.
     *                      Element 3:  The address of the blue gamma ramp.
     *                      Element 4:  Zero on success, an error code on error.
     */
    private static native long[] libgamma_gamma_ramps32_create(int red_size, int green_size, int blue_size);
    
    /**
     * Create and initialise a gamma ramp in the proper way that allows all adjustment
     * methods to read from and write to it without causing segmentation violation.
     * 
     * @param   red_size    The size of the encoding axis of the red gamma ramp.
     * @param   green_size  The size of the encoding axis of the green gamma ramp.
     * @param   blue_size   The size of the encoding axis of the blue gamma ramp.
     * @return              Element 0:  The address of the native object.
     *                      Element 1:  The address of the red gamma ramp.
     *                      Element 2:  The address of the green gamma ramp.
     *                      Element 3:  The address of the blue gamma ramp.
     *                      Element 4:  Zero on success, an error code on error.
     */
    private static native long[] libgamma_gamma_ramps64_create(int red_size, int green_size, int blue_size);
    
    /**
     * Create and initialise a gamma ramp in the proper way that allows all adjustment
     * methods to read from and write to it without causing segmentation violation.
     * 
     * @param   red_size    The size of the encoding axis of the red gamma ramp.
     * @param   green_size  The size of the encoding axis of the green gamma ramp.
     * @param   blue_size   The size of the encoding axis of the blue gamma ramp.
     * @return              Element 0:  The address of the native object.
     *                      Element 1:  The address of the red gamma ramp.
     *                      Element 2:  The address of the green gamma ramp.
     *                      Element 3:  The address of the blue gamma ramp.
     *                      Element 4:  Zero on success, an error code on error.
     */
    private static native long[] libgamma_gamma_rampsf_create(int red_size, int green_size, int blue_size);
    
    /**
     * Create and initialise a gamma ramp in the proper way that allows all adjustment
     * methods to read from and write to it without causing segmentation violation.
     * 
     * @param   red_size    The size of the encoding axis of the red gamma ramp.
     * @param   green_size  The size of the encoding axis of the green gamma ramp.
     * @param   blue_size   The size of the encoding axis of the blue gamma ramp.
     * @return              Element 0:  The address of the native object.
     *                      Element 1:  The address of the red gamma ramp.
     *                      Element 2:  The address of the green gamma ramp.
     *                      Element 3:  The address of the blue gamma ramp.
     *                      Element 4:  Zero on success, an error code on error.
     */
    private static native long[] libgamma_gamma_rampsd_create(int red_size, int green_size, int blue_size);
    
    
    /**
     * Release resources that are held by a gamma ramp strcuture that
     * has been allocated by {@link #libgamma_gamma_ramps8_create} or
     * otherwise initialised in the proper manner, as well as release
     * the pointer to the structure.
     * 
     * @param  address  The gamma ramps.
     */
    private static native void libgamma_gamma_ramps8_free(long address);
    
    /**
     * Release resources that are held by a gamma ramp strcuture that
     * has been allocated by {@link #libgamma_gamma_ramps16_create} or
     * otherwise initialised in the proper manner, as well as release
     * the pointer to the structure.
     * 
     * @param  address  The gamma ramps.
     */
    private static native void libgamma_gamma_ramps16_free(long address);
    
    /**
     * Release resources that are held by a gamma ramp strcuture that
     * has been allocated by {@link #libgamma_gamma_ramps32_create} or
     * otherwise initialised in the proper manner, as well as release
     * the pointer to the structure.
     * 
     * @param  address  The gamma ramps.
     */
    private static native void libgamma_gamma_ramps32_free(long address);
    
    /**
     * Release resources that are held by a gamma ramp strcuture that
     * has been allocated by {@link #libgamma_gamma_ramps64_create} or
     * otherwise initialised in the proper manner, as well as release
     * the pointer to the structure.
     * 
     * @param  address  The gamma ramps.
     */
    private static native void libgamma_gamma_ramps64_free(long address);
    
    /**
     * Release resources that are held by a gamma ramp strcuture that
     * has been allocated by {@link #libgamma_gamma_rampsf_create} or
     * otherwise initialised in the proper manner, as well as release
     * the pointer to the structure.
     * 
     * @param  address  The gamma ramps.
     */
    private static native void libgamma_gamma_rampsf_free(long address);
    
    /**
     * Release resources that are held by a gamma ramp strcuture that
     * has been allocated by {@link #libgamma_gamma_rampsd_create} or
     * otherwise initialised in the proper manner, as well as release
     * the pointer to the structure.
     * 
     * @param  address  The gamma ramps.
     */
    private static native void libgamma_gamma_rampsd_free(long address);
    
}

