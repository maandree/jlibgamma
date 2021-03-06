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
 * Library initialisation class.
 */
class Libgamma
{
    /**
     * Initialise the library.
     */
    static void initialise()
    {
	if (Libgamma.initialised)
	    return;
	Libgamma.initialised = true;
	
	try
	{   System.loadLibrary("gamma-java");
	}
	catch (Throwable err)
	{   throw new Error(err);
	}
    }
    
    
    /**
     * Whether {@link #initialise} has been invoked.
     */
    private static boolean initialised = false;
    
}

