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

#include <errno.h>
#include <stdlib.h>

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
  jlongArray rc = (*env)->NewLongArray(env, 3);
  jlong e, z = 0;
  if ((error_code == LIBGAMMA_ERRNO_SET) || !error_code)
    error_code = errno;
  e = (jlong)error_code;
  (*env)->SetLongArrayRegion(env, rc, 0, 1, &z);
  (*env)->SetLongArrayRegion(env, rc, 1, 1, &z);
  (*env)->SetLongArrayRegion(env, rc, 2, 1, &e);
  return rc;
}


/**
 * Make a success-return.
 * 
 * @param   state  The native object.
 * @param   count  The subelement count.
 * @return         The object to return.
 */
static jlongArray ok(JNIEnv* env, void* state, size_t count)
{
  jlong a = (jlong)(size_t)state;
  jlong b = (jlong)count, z = 0;
  jlongArray rc = (*env)->NewLongArray(env, 3);
  (*env)->SetLongArrayRegion(env, rc, 0, 1, &a);
  (*env)->SetLongArrayRegion(env, rc, 1, 1, &b);
  (*env)->SetLongArrayRegion(env, rc, 2, 1, &z);
  return rc;
}


/**
 * Create a partition state.
 * 
 * @param   site       The site state for the site that the partition belongs to.
 * @param   partition  The index of the partition within the site.
 * @return             Element 0:  The value for {@link #address}.
 *                     Element 1:  The value for {@link #crtcs_available}
 *                     Element 2:  Error code, zero on success.
 */
jlongArray Java_libgamma_Partition_libgamma_1partition_1create(J, jlong site, jint partition)
{
  libgamma_partition_state_t* state = malloc(sizeof(libgamma_partition_state_t));
  void* super = (void*)(size_t)site;
  int r;
  if (state == NULL)
    return fail(env, 0);
  r = libgamma_partition_initialise(state, super, partition);
  if (r != 0)
    return fail(env, r);
  return ok(env, state, state->crtcs_available);
  (void) class;
}


/**
 * Release all resources held by a partition state
 * and free the partition state pointer.
 * 
 * @param  address  The partition state.
 */
void Java_libgamma_Partition_libgamma_1partition_1free(J, jlong address)
{
  void* this = (void*)(size_t)address;
  libgamma_partition_free(this);
  (void) env;
  (void) class;
}


/**
 * Restore the gamma ramps all CRTC:s within a partition to the system settings.
 * 
 * @param   address  The partition state.
 * @return           Zero on success, and error code on failure.
 */
jint Java_libgamma_Partition_libgamma_1partition_1restore(J, jlong address)
{
  void* this = (void*)(size_t)address;
  int r = libgamma_partition_restore(this);
  if (r != 0)
    return r == LIBGAMMA_ERRNO_SET ? errno : r;
  return 0;
  (void) env;
  (void) class;
}

