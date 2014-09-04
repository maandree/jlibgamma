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
#include "libgamma_Ramp.h"

#include <libgamma.h>


#ifndef __GCC__
# ifndef __attribute__
#  define __attribute__(X)  /* empty */
# endif
#endif


#define J  JNIEnv* env, jclass __attribute__((unused)) class



/**
 * Read the value of a stop in an 8-bit ramp.
 * 
 * @param   address  The address of the ramp.
 * @param   stop     The index of the stop.
 * @return           The value of the stop.
 */
jshort Java_libgamma_Ramp_libgamma_1gamma_1ramps8_1get(J, jlong address, jint stop);


/**
 * Read the value of a stop in a 16-bit ramp.
 * 
 * @param   address  The address of the ramp.
 * @param   stop     The index of the stop.
 * @return           The value of the stop.
 */
jint Java_libgamma_Ramp_libgamma_1gamma_1ramps16_1get(J, jlong address, jint stop);


/**
 * Read the value of a stop in a 32-bit ramp.
 * 
 * @param   address  The address of the ramp.
 * @param   stop     The index of the stop.
 * @return           The value of the stop.
 */
jlong Java_libgamma_Ramp_libgamma_1gamma_1ramps32_1get(J, jlong address, jint stop);


/**
 * Read the value of a stop in a 64-bit ramp.
 * 
 * @param   address  The address of the ramp.
 * @param   stop     The index of the stop.
 * @return           The value of the stop.
 */
jlong Java_libgamma_Ramp_libgamma_1gamma_1ramps64_1get(J, jlong address, jint stop);


/**
 * Read the value of a stop in a single precision floating point ramp.
 * 
 * @param   address  The address of the ramp.
 * @param   stop     The index of the stop.
 * @return           The value of the stop.
 */
jfloat Java_libgamma_Ramp_libgamma_1gamma_1rampsf_1get(J, jlong address, jint stop);


/**
 * Read the value of a stop in a double precision floating point ramp.
 * 
 * @param   address  The address of the ramp.
 * @param   stop     The index of the stop.
 * @return           The value of the stop.
 */
jdouble Java_libgamma_Ramp_libgamma_1gamma_1rampsd_1get(J, jlong address, jint stop);



/**
 * Set the value of a stop in an 8-bit ramp.
 * 
 * @param  address  The address of the ramp.
 * @param  stop     The index of the stop.
 * @param  value    The value of the stop.
 */
void Java_libgamma_Ramp_libgamma_1gamma_1ramps8_1set(J, jlong address, jint stop, jshort value);


/**
 * Set the value of a stop in a 16-bit ramp.
 * 
 * @param  address  The address of the ramp.
 * @param  stop     The index of the stop.
 * @param  value    The value of the stop.
 */
void Java_libgamma_Ramp_libgamma_1gamma_1ramps16_1set(J, jlong address, jint stop, jint value);


/**
 * Set the value of a stop in a 32-bit ramp.
 * 
 * @param  address  The address of the ramp.
 * @param  stop     The index of the stop.
 * @param  value    The value of the stop.
 */
void Java_libgamma_Ramp_libgamma_1gamma_1ramps32_1set(J, jlong address, jint stop, jlong value);


/**
 * Set the value of a stop in a 64-bit ramp.
 * 
 * @param  address  The address of the ramp.
 * @param  stop     The index of the stop.
 * @param  value    The value of the stop.
 */
void Java_libgamma_Ramp_libgamma_1gamma_1ramps64_1set(J, jlong address, jint stop, jlong value);


/**
 * Set the value of a stop in a single precision floating point ramp.
 * 
 * @param  address  The address of the ramp.
 * @param  stop     The index of the stop.
 * @param  value    The value of the stop.
 */
void Java_libgamma_Ramp_libgamma_1gamma_1rampsf_1set(J, jlong address, jint stop, jfloat value);


/**
 * Set the value of a stop in a double precision floating point ramp.
 * 
 * @param  address  The address of the ramp.
 * @param  stop     The index of the stop.
 * @param  value    The value of the stop.
 */
void Java_libgamma_Ramp_libgamma_1gamma_1rampsd_1set(J, jlong address, jint stop, jdouble value);

