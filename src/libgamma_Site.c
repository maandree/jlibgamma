/**
 * jlibgamma -- Display server abstraction layer for gamma ramp and Java
 * Copyright (C) 2014  Mattias Andrée (maandree@member.fsf.org)
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
#include "libgamma_Site.h"

#include <libgamma.h>


#define J  JNIEnv* env, jclass class



/**
 * Create a site state.
 * 
 * @param   method  The adjustment method (display server and protocol.)
 * @param   site    The site identifier.
 * @return          Element 0:  The value for {@link #address}.
 *                  Element 1:  The value for {@link #partitions_available}
 *                  Element 2:  Error code, zero on success.
 */
jlongArray Java_libgamma_Site_libgamma_1site_1create(J, jint method, jstring site);


/**
 * Release all resources held by a site state
 * and free the site state pointer.
 * 
 * @param  address  The site state.
 */
void Java_libgamma_Site_libgamma_1site_1free(J, jlong address);


/**
 * Restore the gamma ramps all CRTC:s within a site to the system settings.
 * 
 * @param   address  The site state.
 * @return           Zero on success, and error code on failure.
 */
jint Java_libgamma_Site_libgamma_1site_1restore(J, jlong address);

