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
 * Cathode ray tube controller state.
 * 
 * The CRTC controls the gamma ramps for the
 * monitor that is plugged in to the connector
 * that the CRTC belongs to.
 */
public class CRTC
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
     * @param  partition  The partition this CRTC belongs to.
     * @param  crtc       The index of the CRTC within its partition.
     */
    public CRTC(Partition partition, int crtc) throws LibgammaException
    {
	this.partition = partition;
	this.crtc = crtc;
	long[] r = libgamma_crtc_create(partition.address, crtc);
	this.address = r[0];
	if (r[1] != 0)
	    throw new LibgammaException((int)(r[1]));
    }
    
    
    /**
     * The partition this CRTC belongs to.
     */
    public final Partition partition;
  
    /**
     * The index of the CRTC within its partition.
     */
    public final int crtc;
    
    /**
     * The address of the native object.
     */
    final long address;
    
    
    /**
     * Release resources.
     */
    public void close()
    {
	libgamma_crtc_free(this.address);
    }
    
    /**
     * Restore the gamma ramps for the CRTC to the system settings.
     */
    public void restore() throws LibgammaException
    {
	int r = libgamma_crtc_restore(this.address);
	if (r != 0)
	    throw new LibgammaException(r);
    }
    
    /**
     * Read information about the CRTC.
     * 
     * @param   fields  OR:ed identifiers for the information about the CRTC that should be read.
     * @return          Information about the CRTC.
     */
    public CRTCInformation get_information(int fields)
    {
	Object[] r = libgamma_get_crtc_information(this.address, fields);
	byte[] edid = (byte[])(r[0]);
	String connector_name = (String)(r[1]);
	float[] gamma = (float[])(r[2]);
	int[] ints = (int[])(r[3]);
	return new CRTCInformation(edid, connector_name, gamma, ints);
    }
    
    /**
     * Get the current gamma ramps for the CRTC.
     * 
     * @param  output  The gamma ramp structure to fill.
     */
    public <T extends Ramp> void get_gamma(GammaRamps<T> output) throws LibgammaException
    {
	int r = 0;
	if      (output.depth ==  8)  r = libgamma_crtc_get_gamma_ramps8(this.address, output.address);
	else if (output.depth == 16)  r = libgamma_crtc_get_gamma_ramps16(this.address, output.address);
	else if (output.depth == 32)  r = libgamma_crtc_get_gamma_ramps32(this.address, output.address);
	else if (output.depth == 64)  r = libgamma_crtc_get_gamma_ramps64(this.address, output.address);
	else if (output.depth == -1)  r = libgamma_crtc_get_gamma_rampsf(this.address, output.address);
	else if (output.depth == -2)  r = libgamma_crtc_get_gamma_rampsd(this.address, output.address);
	if (r != 0)
	    throw new LibgammaException(r);
    }
    
    /**
     * Set the gamma ramps for the CRTC.
     * 
     * @param  values  The gamma ramps to apply.
     */
    public <T extends Ramp> void set_gamma(GammaRamps<T> values) throws LibgammaException
    {
	int r = 0;
	if      (values.depth ==  8)  r = libgamma_crtc_set_gamma_ramps8(this.address, values.address);
	else if (values.depth == 16)  r = libgamma_crtc_set_gamma_ramps16(this.address, values.address);
	else if (values.depth == 32)  r = libgamma_crtc_set_gamma_ramps32(this.address, values.address);
	else if (values.depth == 64)  r = libgamma_crtc_set_gamma_ramps64(this.address, values.address);
	else if (values.depth == -1)  r = libgamma_crtc_set_gamma_rampsf(this.address, values.address);
	else if (values.depth == -2)  r = libgamma_crtc_set_gamma_rampsd(this.address, values.address);
	if (r != 0)
	    throw new LibgammaException(r);
    }
    
    
    /**
     * {@inheritDoc}
     */
    public String toString()
    {
	return "<Site: partition = " + this.partition.toString() + ", " +
	                   "crtc = " + Integer.toString(this.crtc) + ">";
    }
    
    
    
    /**
     * Create a CRTC state.
     * 
     * @param   partition  The partition state for the partition that the CRTC belongs to.
     * @param   crtc       The index of the CRTC within the partition.
     * @return             Element 0:  The value for {@link #address}.
     *                     Element 1:  Error code, zero on success.
     */
    private static native long[] libgamma_crtc_create(long partition, int crtc);
    
    /**
     * Release all resources held by a CRTC state
     * and free the CRTC state pointer.
     * 
     * @param  address  The CRTC state.
     */
    private static native void libgamma_crtc_free(long address);
    
    /**
     * Restore the gamma ramps for a CRTC to the system settings for that CRTC.
     * 
     * @param   address  The CRTC state.
     * @return           Zero on success, and error code on failure.
     */
    private static native int libgamma_crtc_restore(long address);
    
    /**
     * Read information about a CRTC.
     * 
     * @param   crtc    The state of the CRTC whose information should be read.
     * @param   fields  OR:ed identifiers for the information about the CRTC that should be read.
     * @return          Input parameters for the constructor of {@link CRTCInformation}
     */
    private static native Object[] libgamma_get_crtc_information(long crtc, int fields);
    
    
    /**
     * Get the current gamma ramps for a CRTC, 8-bit gamma-depth version.
     * 
     * @param   address  The CRTC state.
     * @param   ramps    The gamma ramps to fill with the current values
     * @return           Zero on success, an error code on failure.
     */
    private static native int libgamma_crtc_get_gamma_ramps8(long address, long ramps);
    
    /**
     * Set the gamma ramps for a CRTC, 8-bit gamma-depth version.
     * 
     * @param   address  The CRTC state.
     * @param   ramps    The gamma ramps to apply.
     * @return           Zero on success, an error code on failure.
     */
    private static native int libgamma_crtc_set_gamma_ramps8(long address, long ramps);
    
    
    /**
     * Get the current gamma ramps for a CRTC, 16-bit gamma-depth version.
     * 
     * @param   address  The CRTC state.
     * @param   ramps    The gamma ramps to fill with the current values
     * @return           Zero on success, an error code on failure.
     */
    private static native int libgamma_crtc_get_gamma_ramps16(long address, long ramps);
    
    /**
     * Set the gamma ramps for a CRTC, 16-bit gamma-depth version.
     * 
     * @param   address  The CRTC state.
     * @param   ramps    The gamma ramps to apply.
     * @return           Zero on success, an error code on failure.
     */
    private static native int libgamma_crtc_set_gamma_ramps16(long address, long ramps);
    
    
    /**
     * Get the current gamma ramps for a CRTC, 32-bit gamma-depth version.
     * 
     * @param   address  The CRTC state.
     * @param   ramps    The gamma ramps to fill with the current values.
     * @return           Zero on success, an error code on failure.
     */
    private static native int libgamma_crtc_get_gamma_ramps32(long address, long ramps);
    
    /**
     * Set the gamma ramps for a CRTC, 32-bit gamma-depth version.
     * 
     * @param   address  The CRTC state.
     * @param   ramps    The gamma ramps to apply.
     * @return           Zero on success, an error code on failure.
     */
    private static native int libgamma_crtc_set_gamma_ramps32(long address, long ramps);
    
    
    /**
     * Get the current gamma ramps for a CRTC, 64-bit gamma-depth version.
     * 
     * @param   address  The CRTC state.
     * @param   ramps    The gamma ramps to fill with the current values.
     * @return           Zero on success, an error code on failure.
     */
    private static native int libgamma_crtc_get_gamma_ramps64(long address, long ramps);
    
    /**
     * Set the gamma ramps for a CRTC, 64-bit gamma-depth version.
     * 
     * @param   address  The CRTC state.
     * @param   ramps    The gamma ramps to apply.
     * @return           Zero on success, an error code on failure.
     */
    private static native int libgamma_crtc_set_gamma_ramps64(long address, long ramps);
    
    
    /**
     * Set the gamma ramps for a CRTC, single precision floating point version.
     * 
     * @param   address  The CRTC state.
     * @param   ramps    The gamma ramps to apply.
     * @return           Zero on success, an error code on failure.
     */
    private static native int libgamma_crtc_set_gamma_rampsf(long address, long ramps);
    
    /**
     * Get the current gamma ramps for a CRTC, single precision floating point version.
     * 
     * @param   address  The CRTC state.
     * @param   ramps    The gamma ramps to fill with the current values.
     * @return           Zero on success, an error code on failure.
     */
    private static native int libgamma_crtc_get_gamma_rampsf(long address, long ramps);
    
    
    /**
     * Get the current gamma ramps for a CRTC, double precision floating point version.
     * 
     * @param   address  The CRTC state.
     * @param   ramps    The gamma ramps to fill with the current values.
     * @return           Zero on success, an error code on failure.
     */
    private static native int libgamma_crtc_get_gamma_rampsd(long address, long ramps);
    
    /**
     * Set the gamma ramps for a CRTC, double precision floating point version.
     * 
     * @param   address  The CRTC state.
     * @param   ramps    The gamma ramps to apply.
     * @return           Zero on success, an error code on failure.
     */
    private static native int libgamma_crtc_set_gamma_rampsd(long address, long ramps);
    
}

