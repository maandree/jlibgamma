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
#include "libgamma_LibgammaException.h"

#include <libgamma.h>


#ifndef __GCC__
# ifndef __attribute__
#  define __attribute__(X)  /* empty */
# endif
#endif


#define J  JNIEnv* env, jclass __attribute__((unused)) class



/**
 * Returns the name of the definition associated with
 * a <tt>libgamma</tt> error code.
 * 
 * @param   value  The error code.
 * @return         The name of the definition associated with the error code,
 *                 {@code null} if the error code does not exist.
 */
jstring Java_libgamma_LibgammaException_name_1of_1error(J, jint value);


/**
 * Return the value of a <tt>libgamma</tt> error definition
 * refered to by name.
 * 
 * @param   name  The name of the definition associated with the error code.
 * @return        The error code, zero if the name is {@code null}
 *                or does not refer to a <tt>libgamma</tt> error.
 */
jint Java_libgamma_LibgammaException_value_1of_1error(J, jstring name);


/**
 * Acquire the value that should go to {@link #group_gid}.
 * 
 * @return  The value that should go to {@link #group_gid}.
 */
jint Java_libgamma_LibgammaException_libgamma_1group_1gid(J);


/**
 * Acquire the value that should go to {@link #group_name}.
 * 
 * @return  The value that should go to {@link #group_name}.
 */
jstring Java_libgamma_LibgammaException_libgamma_1group_1name(J);


/**
 * Get a textual description of a system error code.
 * 
 * @param   error_code  The error code.
 * @return              A textual description of the error code.
 */
jstring Java_libgamma_LibgammaException_strerror(J, jint error_code);

