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
 * A single gamma ramp.
 */
public abstract class Ramp
{
    /**
     * Constructor.
     * 
     * @param  address  The address of the native object.
     * @param  size     The size of the encoding axis of the gamma ramp.
     */
    Ramp(long address, int size)
    {
	this.size = size;
	this.address = address;
    }
    
    
    /**
     * The size of the encoding axis of the gamma ramp.
     */
    public final int size;
    
    /**
     * The address of the native object.
     */
    protected final long address;
    
    
    /**
     * Read the value of a stop in an 8-bit ramp.
     * 
     * @param   address  The address of the ramp.
     * @param   stop     The index of the stop.
     * @return           The value of the stop.
     */
    protected static native short libgamma_gamma_ramps8_get(long address, int stop);
    
    /**
     * Read the value of a stop in a 16-bit ramp.
     * 
     * @param   address  The address of the ramp.
     * @param   stop     The index of the stop.
     * @return           The value of the stop.
     */
    protected static native int libgamma_gamma_ramps16_get(long address, int stop);
    
    /**
     * Read the value of a stop in a 32-bit ramp.
     * 
     * @param   address  The address of the ramp.
     * @param   stop     The index of the stop.
     * @return           The value of the stop.
     */
    protected static native long libgamma_gamma_ramps32_get(long address, int stop);
    
    /**
     * Read the value of a stop in a 64-bit ramp.
     * 
     * @param   address  The address of the ramp.
     * @param   stop     The index of the stop.
     * @return           The value of the stop.
     */
    protected static native long libgamma_gamma_ramps64_get(long address, int stop);
    
    /**
     * Read the value of a stop in a single precision floating point ramp.
     * 
     * @param   address  The address of the ramp.
     * @param   stop     The index of the stop.
     * @return           The value of the stop.
     */
    protected static native float libgamma_gamma_rampsf_get(long address, int stop);
    
    /**
     * Read the value of a stop in a double precision floating point ramp.
     * 
     * @param   address  The address of the ramp.
     * @param   stop     The index of the stop.
     * @return           The value of the stop.
     */
    protected static native double libgamma_gamma_rampsd_get(long address, int stop);
    
    
    /**
     * Set the value of a stop in an 8-bit ramp.
     * 
     * @param  address  The address of the ramp.
     * @param  stop     The index of the stop.
     * @param  value    The value of the stop.
     */
    protected static native void libgamma_gamma_ramps8_set(long address, int stop, short value);
    
    /**
     * Set the value of a stop in a 16-bit ramp.
     * 
     * @param  address  The address of the ramp.
     * @param  stop     The index of the stop.
     * @param  value    The value of the stop.
     */
    protected static native void libgamma_gamma_ramps16_set(long address, int stop, int value);
    
    /**
     * Set the value of a stop in a 32-bit ramp.
     * 
     * @param  address  The address of the ramp.
     * @param  stop     The index of the stop.
     * @param  value    The value of the stop.
     */
    protected static native void libgamma_gamma_ramps32_set(long address, int stop, long value);
    
    /**
     * Set the value of a stop in a 64-bit ramp.
     * 
     * @param  address  The address of the ramp.
     * @param  stop     The index of the stop.
     * @param  value    The value of the stop.
     */
    protected static native void libgamma_gamma_ramps64_set(long address, int stop, long value);
    
    /**
     * Set the value of a stop in a single precision floating point ramp.
     * 
     * @param  address  The address of the ramp.
     * @param  stop     The index of the stop.
     * @param  value    The value of the stop.
     */
    protected static native void libgamma_gamma_rampsf_set(long address, int stop, float value);
    
    /**
     * Set the value of a stop in a double precision floating point ramp.
     * 
     * @param  address  The address of the ramp.
     * @param  stop     The index of the stop.
     * @param  value    The value of the stop.
     */
    protected static native void libgamma_gamma_rampsd_set(long address, int stop, double value);
    
}

