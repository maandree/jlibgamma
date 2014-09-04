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
#include "libgamma_Partition.h"


/**
 * Create a partition state.
 * 
 * @param   site       The site state for the site that the partition belongs to.
 * @param   partition  The index of the partition within the site.
 * @return             Element 0:  The value for {@link #address}.
 *                     Element 1:  The value for {@link #crtcs_available}
 *                     Element 2:  Error code, zero on success.
 */
jlongArray Java_libgamma_Partition_libgamma_1partition_1create(JNIEnv *, jclass, jlong, jint);


/**
 * Release all resources held by a partition state
 * and free the partition state pointer.
 * 
 * @param  address  The partition state.
 */
void Java_libgamma_Partition_libgamma_1partition_1free(JNIEnv *, jclass, jlong);


/**
 * Restore the gamma ramps all CRTC:s within a partition to the system settings.
 * 
 * @param   address  The partition state.
 * @return           Zero on success, and error code on failure.
 */
jint Java_libgamma_Partition_libgamma_1partition_1restore(JNIEnv *, jclass, jlong);

