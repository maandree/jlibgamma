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

#include <stdlib.h>
#include <string.h>
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
 * Create a site state.
 * 
 * @param   method  The adjustment method (display server and protocol.)
 * @param   site    The site identifier.
 * @return          Element 0:  The value for {@link #address}.
 *                  Element 1:  The value for {@link #partitions_available}
 *                  Element 2:  Error code, zero on success.
 */
jlongArray Java_libgamma_Site_libgamma_1site_1create(J, jint method, jstring site)
{
  libgamma_site_state_t* state = malloc(sizeof(libgamma_site_state_t));
  const char* site_chars;
  char* site_;
  int r;
  if (site != NULL)
    {
      int saved_errno = 0;
      size_t n;
      site_chars = (*env)->GetStringUTFChars(env, site, NULL);
      n = strlen(site_chars) + 1;
      site_ = malloc(n * sizeof(char));
      if (site_ == NULL)
	saved_errno = errno;
      else
	memcpy(site_, site_chars, n * sizeof(char));
      (*env)->ReleaseStringUTFChars(env, site, site_chars);
      if (saved_errno)
	return fail(env, errno);
    }
  else
    site_ = NULL;
  if (state == NULL)
    return fail(env, 0);
  r = libgamma_site_initialise(state, method, site_);
  if (r != 0)
    return fail(env, r);
  return ok(env, state, state->partitions_available);
  (void) class;
}


/**
 * Release all resources held by a site state
 * and free the site state pointer.
 * 
 * @param  address  The site state.
 */
void Java_libgamma_Site_libgamma_1site_1free(J, jlong address)
{
  void* this = (void*)(size_t)address;
  libgamma_site_free(this);
  (void) env;
  (void) class;
}


/**
 * Restore the gamma ramps all CRTC:s within a site to the system settings.
 * 
 * @param   address  The site state.
 * @return           Zero on success, and error code on failure.
 */
jint Java_libgamma_Site_libgamma_1site_1restore(J, jlong address)
{
  void* this = (void*)(size_t)address;
  int r = libgamma_site_restore(this);
  if (r != 0)
    return r == LIBGAMMA_ERRNO_SET ? errno : r;
  return 0;
  (void) env;
  (void) class;
}

