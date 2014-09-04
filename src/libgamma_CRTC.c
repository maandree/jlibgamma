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
#include "libgamma_CRTC.h"


#define J  JNIEnv* env, jclass class



/**
 * Create a CRTC state.
 * 
 * @param   partition  The partition state for the partition that the CRTC belongs to.
 * @param   crtc       The index of the CRTC within the partition.
 * @return             Element 0:  The value for {@link #address}.
 *                     Element 1:  Error code, zero on success.
 */
jlongArray Java_libgamma_CRTC_libgamma_1crtc_1create(J, jlong partition, jint crtc);


/**
 * Release all resources held by a CRTC state
 * and free the CRTC state pointer.
 * 
 * @param  address  The CRTC state.
 */
void Java_libgamma_CRTC_libgamma_1crtc_1free(J, jlong address);


/**
 * Restore the gamma ramps for a CRTC to the system settings for that CRTC.
 * 
 * @param   address  The CRTC state.
 * @return           Zero on success, and error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1restore(J, jlong address);


/**
 * Read information about a CRTC.
 * 
 * @param   crtc    The state of the CRTC whose information should be read.
 * @param   fields  OR:ed identifiers for the information about the CRTC that should be read.
 * @return          Input parameters for the constructor of {@link CRTCInformation}
 */
jobjectArray Java_libgamma_CRTC_libgamma_1get_1crtc_1information(J, jlong crtc, jint fields);



/**
 * Get the current gamma ramps for a CRTC, 8-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to fill with the current values
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1ramps8(J, jlong address, jlong ramps);


/**
 * Set the gamma ramps for a CRTC, 8-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to apply.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1ramps8(J, jlong address, jlong ramps);



/**
 * Get the current gamma ramps for a CRTC, 16-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to fill with the current values
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1ramps16(J, jlong address, jlong ramps);


/**
 * Set the gamma ramps for a CRTC, 16-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to apply.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1ramps16(J, jlong address, jlong ramps);



/**
 * Get the current gamma ramps for a CRTC, 32-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to fill with the current values.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1ramps32(J, jlong address, jlong ramps);


/**
 * Set the gamma ramps for a CRTC, 32-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to apply.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1ramps32(J, jlong address, jlong ramps);



/**
 * Get the current gamma ramps for a CRTC, 64-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to fill with the current values.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1ramps64(J, jlong address, jlong ramps);


/**
 * Set the gamma ramps for a CRTC, 64-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to apply.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1ramps64(J, jlong address, jlong ramps);



/**
 * Set the gamma ramps for a CRTC, single precision floating point version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to apply.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1rampsf(J, jlong address, jlong ramps);


/**
 * Get the current gamma ramps for a CRTC, single precision floating point version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to fill with the current values.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1rampsf(J, jlong address, jlong ramps);



/**
 * Get the current gamma ramps for a CRTC, double precision floating point version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to fill with the current values.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1rampsd(J, jlong address, jlong ramps);


/**
 * Set the gamma ramps for a CRTC, double precision floating point version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to apply.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1rampsd(J, jlong address, jlong ramps);

