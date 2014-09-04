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
#include "libgamma_GammaRamps.h"


#define J  JNIEnv* env, jclass class



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
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1ramps8_1create(J, jint red_size, jint green_size, jint blue_size);


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
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1ramps16_1create(J, jint red_size, jint green_size, jint blue_size);


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
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1ramps32_1create(J, jint red_size, jint green_size, jint blue_size);


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
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1ramps64_1create(J, jint red_size, jint green_size, jint blue_size);


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
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1rampsf_1create(J, jint red_size, jint green_size, jint blue_size);


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
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1rampsd_1create(J, jint red_size, jint green_size, jint blue_size);



/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_ramps8_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure.
 * 
 * @param  address  The gamma ramps.
 */
void Java_libgamma_GammaRamps_libgamma_1gamma_1ramps8_1free(J, jlong address);


/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_ramps16_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure.
 * 
 * @param  address  The gamma ramps.
 */
void Java_libgamma_GammaRamps_libgamma_1gamma_1ramps16_1free(J, jlong address);


/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_ramps32_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure.
 * 
 * @param  address  The gamma ramps.
 */
void Java_libgamma_GammaRamps_libgamma_1gamma_1ramps32_1free(J, jlong address);


/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_ramps64_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure.
 * 
 * @param  address  The gamma ramps.
 */
void Java_libgamma_GammaRamps_libgamma_1gamma_1ramps64_1free(J, jlong address);


/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_rampsf_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure.
 * 
 * @param  address  The gamma ramps.
 */
void Java_libgamma_GammaRamps_libgamma_1gamma_1rampsf_1free(J, jlong address);


/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_rampsd_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure.
 * 
 * @param  address  The gamma ramps.
 */
void Java_libgamma_GammaRamps_libgamma_1gamma_1rampsd_1free(J, jlong address);

