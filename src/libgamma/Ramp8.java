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
 * A single 8-bit gamma ramp.
 */
public class Ramp8 extends Ramp
{
    /**
     * Constructor.
     * 
     * @param  address  The address of the native object.
     * @param  size     The size of the encoding axis of the gamma ramp.
     */
    Ramp8(long address, int size)
    {
	super(address, size);
    }
    
    
    /**
     * Read the value of a stop.
     * 
     * @param   stop  The index of the stop.
     * @return        The value of the stop.
     */
    public short get(short stop)
    {
	return libgamma_gamma_ramps8_get(this.address, stop);
    }
    
    
    /**
     * Read the value of a stop.
     * 
     * @param   stop   The index of the stop.
     * @param   value  The new value of the stop.
     * @return         The new value of the stop.
     */
    public long set(int stop, long value)
    {
	libgamma_gamma_ramps8_set(this.address, stop, (short)value);
	return value;
    }
    
    /**
     * Read the value of a stop.
     * 
     * @param   stop   The index of the stop.
     * @param   value  The new value of the stop.
     * @return         The new value of the stop.
     */
    public int set(int stop, int value)
    {
	libgamma_gamma_ramps8_set(this.address, stop, (short)value);
	return value;
    }
    
    /**
     * Read the value of a stop.
     * 
     * @param   stop   The index of the stop.
     * @param   value  The new value of the stop.
     * @return         The new value of the stop.
     */
    public short set(int stop, short value)
    {
	libgamma_gamma_ramps8_set(this.address, stop, value);
	return value;
    }
    
    /**
     * Read the value of a stop.
     * 
     * @param   stop   The index of the stop.
     * @param   value  The new value of the stop.
     * @return         The new value of the stop.
     */
    public byte set(int stop, byte value)
    {
	libgamma_gamma_ramps8_set(this.address, stop, value);
	return value;
    }
    
}

