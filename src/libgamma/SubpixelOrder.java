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
 * Orders for subpixels. Currently the possible values are
 * very biased to LCD, Plasma and monochrome monitors.
 */
public enum SubpixelOrder
{
    /**
     * The adjustment method does not know the order of the subpixels.
     * (This could be considered an error.)
     */
    UNKNOWN(0),
    
    /**
     * There are no subpixels in the monitor.
     */
    NONE(1),
    
    /**
     * The subpixels are ordered red, green and then blue, from left to right.
     */
    HORIZONTAL_RGB(2),
    
    /**
     * The subpixels are ordered blue, green and then red, from left to right.
     */
    HORIZONTAL_BGR(3),
    
    /**
     * The subpixels are ordered red, green and then blue, from the top down.
     */
    VERTICAL_RGB(4),
    
    /**
     * The subpixels are ordered blue, green and then red, from the top down.
     */
    VERTICAL_BGR(5)
    
    ;
    
    
    /**
     * Type initialiser.
     */
    static
    {
	Libgamma.initialise();
    }
    
    
    
    /**
     * Subpixel orders by their numerical values.
     */
    public static SubpixelOrder[] VALUES =
    {
	UNKNOWN, NONE, HORIZONTAL_RGB, HORIZONTAL_BGR, VERTICAL_RGB, VERTICAL_BGR
    };
    
    
    /**
     * Constructor.
     * 
     * @param  value  The numerical value of the subpixel order.
     */
    private SubpixelOrder(int value)
    {
	this.value = value;
    }
    
    
    /**
     * The numerical value of the subpixel order.
     */
    public int value;
    
}

