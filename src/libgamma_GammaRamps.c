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

#include <stdlib.h>
#include <errno.h>

#include <libgamma.h>


#define J  JNIEnv* env, jclass class



/**
 * Make a failure-return.
 * 
 * @param   error_code  The error code returned from the failing function or zero to read `errno`.
 * @return              The object to return.
 */
static jlongArray fail(JNIEnv* env, int error_code)
{
  jlongArray rc = (*env)->NewLongArray(env, 5);
  jlong e, z = 0;
  if ((error_code == LIBGAMMA_ERRNO_SET) || !error_code)
    error_code = errno;
  e = (jlong)error_code;
  (*env)->SetLongArrayRegion(env, rc, 0, 1, &z);
  (*env)->SetLongArrayRegion(env, rc, 1, 1, &z);
  (*env)->SetLongArrayRegion(env, rc, 2, 1, &z);
  (*env)->SetLongArrayRegion(env, rc, 3, 1, &z);
  (*env)->SetLongArrayRegion(env, rc, 4, 1, &e);
  return rc;
}


/**
 * Make a success-return.
 * 
 * @param   ramps  The native object.
 * @param   red    The red gamma ramp.
 * @param   green  The green gamma ramp.
 * @param   blue   The blue gamma ramp.
 * @return         The object to return.
 */
static jlongArray ok(JNIEnv* env, void* ramps, void* red, void* green, void* blue)
{
  jlong a = (jlong)(size_t)ramps;
  jlong b = (jlong)(size_t)red;
  jlong c = (jlong)(size_t)green;
  jlong d = (jlong)(size_t)blue, z = 0;
  jlongArray rc = (*env)->NewLongArray(env, 5);
  (*env)->SetLongArrayRegion(env, rc, 0, 1, &a);
  (*env)->SetLongArrayRegion(env, rc, 1, 1, &b);
  (*env)->SetLongArrayRegion(env, rc, 2, 1, &c);
  (*env)->SetLongArrayRegion(env, rc, 3, 1, &d);
  (*env)->SetLongArrayRegion(env, rc, 4, 1, &z);
  return rc;
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
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1ramps8_1create(J, jint red_size, jint green_size, jint blue_size)
{
  libgamma_gamma_ramps8_t* ramps = malloc(sizeof(libgamma_gamma_ramps8_t));
  int r;
  if (ramps == NULL)
    return fail(env, 0);
  ramps->red_size = red_size;
  ramps->green_size = green_size;
  ramps->blue_size = blue_size;
  r = libgamma_gamma_ramps8_initialise(ramps);
  if (r != 0)
    return fail(env, r);
  return ok(env, ramps, ramps->red, ramps->green, ramps->blue);
  (void) class;
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
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1ramps16_1create(J, jint red_size, jint green_size, jint blue_size)
{
  libgamma_gamma_ramps16_t* ramps = malloc(sizeof(libgamma_gamma_ramps16_t));
  int r;
  if (ramps == NULL)
    return fail(env, 0);
  ramps->red_size = red_size;
  ramps->green_size = green_size;
  ramps->blue_size = blue_size;
  r = libgamma_gamma_ramps16_initialise(ramps);
  if (r != 0)
    return fail(env, r);
  return ok(env, ramps, ramps->red, ramps->green, ramps->blue);
  (void) class;
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
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1ramps32_1create(J, jint red_size, jint green_size, jint blue_size)
{
  libgamma_gamma_ramps32_t* ramps = malloc(sizeof(libgamma_gamma_ramps32_t));
  int r;
  if (ramps == NULL)
    return fail(env, 0);
  ramps->red_size = red_size;
  ramps->green_size = green_size;
  ramps->blue_size = blue_size;
  r = libgamma_gamma_ramps32_initialise(ramps);
  if (r != 0)
    return fail(env, r);
  return ok(env, ramps, ramps->red, ramps->green, ramps->blue);
  (void) class;
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
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1ramps64_1create(J, jint red_size, jint green_size, jint blue_size)
{
  libgamma_gamma_ramps64_t* ramps = malloc(sizeof(libgamma_gamma_ramps64_t));
  int r;
  if (ramps == NULL)
    return fail(env, 0);
  ramps->red_size = red_size;
  ramps->green_size = green_size;
  ramps->blue_size = blue_size;
  r = libgamma_gamma_ramps64_initialise(ramps);
  if (r != 0)
    return fail(env, r);
  return ok(env, ramps, ramps->red, ramps->green, ramps->blue);
  (void) class;
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
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1rampsf_1create(J, jint red_size, jint green_size, jint blue_size)
{
  libgamma_gamma_rampsf_t* ramps = malloc(sizeof(libgamma_gamma_rampsf_t));
  int r;
  if (ramps == NULL)
    return fail(env, 0);
  ramps->red_size = red_size;
  ramps->green_size = green_size;
  ramps->blue_size = blue_size;
  r = libgamma_gamma_rampsf_initialise(ramps);
  if (r != 0)
    return fail(env, r);
  return ok(env, ramps, ramps->red, ramps->green, ramps->blue);
  (void) class;
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
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1rampsd_1create(J, jint red_size, jint green_size, jint blue_size)
{
  libgamma_gamma_rampsd_t* ramps = malloc(sizeof(libgamma_gamma_rampsd_t));
  int r;
  if (ramps == NULL)
    return fail(env, 0);
  ramps->red_size = red_size;
  ramps->green_size = green_size;
  ramps->blue_size = blue_size;
  r = libgamma_gamma_rampsd_initialise(ramps);
  if (r != 0)
    return fail(env, r);
  return ok(env, ramps, ramps->red, ramps->green, ramps->blue);
  (void) class;
}



/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_ramps8_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure.
 * 
 * @param  address  The gamma ramps.
 */
void Java_libgamma_GammaRamps_libgamma_1gamma_1ramps8_1free(J, jlong address)
{
  void* this = (void*)(size_t)address;
  libgamma_gamma_ramps8_free(this);
  (void) env;
  (void) class;
}


/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_ramps16_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure.
 * 
 * @param  address  The gamma ramps.
 */
void Java_libgamma_GammaRamps_libgamma_1gamma_1ramps16_1free(J, jlong address)
{
  void* this = (void*)(size_t)address;
  libgamma_gamma_ramps16_free(this);
  (void) env;
  (void) class;
}


/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_ramps32_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure.
 * 
 * @param  address  The gamma ramps.
 */
void Java_libgamma_GammaRamps_libgamma_1gamma_1ramps32_1free(J, jlong address)
{
  void* this = (void*)(size_t)address;
  libgamma_gamma_ramps32_free(this);
  (void) env;
  (void) class;
}


/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_ramps64_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure.
 * 
 * @param  address  The gamma ramps.
 */
void Java_libgamma_GammaRamps_libgamma_1gamma_1ramps64_1free(J, jlong address)
{
  void* this = (void*)(size_t)address;
  libgamma_gamma_ramps64_free(this);
  (void) env;
  (void) class;
}


/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_rampsf_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure.
 * 
 * @param  address  The gamma ramps.
 */
void Java_libgamma_GammaRamps_libgamma_1gamma_1rampsf_1free(J, jlong address)
{
  void* this = (void*)(size_t)address;
  libgamma_gamma_rampsf_free(this);
  (void) env;
  (void) class;
}


/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_rampsd_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure.
 * 
 * @param  address  The gamma ramps.
 */
void Java_libgamma_GammaRamps_libgamma_1gamma_1rampsd_1free(J, jlong address)
{
  void* this = (void*)(size_t)address;
  libgamma_gamma_rampsd_free(this);
  (void) env;
  (void) class;
}

