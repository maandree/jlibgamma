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
#include "libgamma_AdjustmentMethod.h"

#include <libgamma.h>


#ifndef __GCC__
# ifndef __attribute__
#  define __attribute__(X)  /* empty */
# endif
#endif


#define J  JNIEnv* env, jclass __attribute__((unused)) class



/**
 * List available adjustment methods by their order of preference based on the environment.
 * 
 * @param   operation  Allowed values:
 *                       0: Methods that the environment suggests will work, excluding fake.
 *                       1: Methods that the environment suggests will work, including fake.
 *                       2: All real non-fake methods.
 *                       3: All real methods.
 *                       4: All methods.
 *                     Other values invoke undefined behaviour.
 * @return             List available adjustment methods by their order of preference.
 */
jintArray Java_libgamma_AdjustmentMethod_libgamma_1list_1methods(J, jint operation);


/**
 * Check whether an adjustment method is available, non-existing (invalid) methods will be
 * identified as not available under the rationale that the library may be out of date.
 * 
 * @param   method  The adjustment method.
 * @return          Whether the adjustment method is available.
 */
jint Java_libgamma_AdjustmentMethod_libgamma_1is_1method_1available(J, jint method);


    
/**
 * Return the capabilities of an adjustment method.
 * 
 * @param   method  The adjustment method (display server and protocol).
 * @return          Input parameter to the constructor of {@link AdjustmentMethodCapabilities}.
 */
jlong Java_libgamma_AdjustmentMethod_libgamma_1method_1capabilities(J, jint method);


/**
 * Return the default site for an adjustment method.
 * 
 * @param   method  The adjustment method (display server and protocol.)
 * @return          The default site, {@code null} if it cannot be determined or
 *                  if multiple sites are not supported by the adjustment method.
 */
jstring Java_libgamma_AdjustmentMethod_libgamma_1method_1default_1site(J, jint method);


/**
 * Return the default variable that determines
 * the default site for an adjustment method.
 * 
 * @param   method  The adjustment method (display server and protocol.)
 * @return          The environ variables that is used to determine the
 *                  default site. {@code null} if there is none, that is,
 *                  if the method does not support multiple sites.
 */
jstring Java_libgamma_AdjustmentMethod_libgamma_1method_1default_1site_1variable(J, jint method);

