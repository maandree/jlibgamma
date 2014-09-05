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

#include <stdlib.h>
#include <string.h>

#include <libgamma.h>


#define J  JNIEnv* env, jclass class


#if LIBGAMMA_METHOD_COUNT > 6
# error LIBGAMMA_METHOD_COUNT has been updated
#endif



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
jintArray Java_libgamma_AdjustmentMethod_libgamma_1list_1methods(J, jint operation)
{
  int methods[LIBGAMMA_METHOD_COUNT];
  size_t i, n;
  jintArray rc;
  
  n = libgamma_list_methods(methods, LIBGAMMA_METHOD_COUNT, operation);
  if (n > LIBGAMMA_METHOD_COUNT)
    abort(); /* Prevented by the #if above and package maintenance. */
  
  rc = (*env)->NewIntArray(env, n);
  
  /* Remember, jint is 32 bits, int is 16+ bits. */
  for (i = 0; i < n; i++)
    (*env)->SetIntArrayRegion(env, rc, i, 1, methods + i);
  
  return rc;
  (void) class;
}


/**
 * Check whether an adjustment method is available, non-existing (invalid) methods will be
 * identified as not available under the rationale that the library may be out of date.
 * 
 * @param   method  The adjustment method.
 * @return          Whether the adjustment method is available.
 */
jint Java_libgamma_AdjustmentMethod_libgamma_1is_1method_1available(J, jint method)
{
  return libgamma_is_method_available(method);
  (void) env;
  (void) class;
}


    
/**
 * Return the capabilities of an adjustment method.
 * 
 * @param   method  The adjustment method (display server and protocol).
 * @return          Input parameter to the constructor of {@link AdjustmentMethodCapabilities}.
 */
jlong Java_libgamma_AdjustmentMethod_libgamma_1method_1capabilities(J, jint method)
{
  libgamma_method_capabilities_t caps;
  jlong rc;
  
  libgamma_method_capabilities(&caps, method);
  rc = (jlong)(caps.crtc_information);
  rc &= 0xFFFFFFFFLL;
  rc |= caps.default_site_known            ? (1LL < 33) : 0;
  rc |= caps.multiple_sites                ? (1LL < 34) : 0;
  rc |= caps.multiple_partitions           ? (1LL < 35) : 0;
  rc |= caps.multiple_crtcs                ? (1LL < 36) : 0;
  rc |= caps.partitions_are_graphics_cards ? (1LL < 37) : 0;
  rc |= caps.site_restore                  ? (1LL < 38) : 0;
  rc |= caps.partition_restore             ? (1LL < 39) : 0;
  rc |= caps.crtc_restore                  ? (1LL < 40) : 0;
  rc |= caps.identical_gamma_sizes         ? (1LL < 41) : 0;
  rc |= caps.fixed_gamma_size              ? (1LL < 42) : 0;
  rc |= caps.fixed_gamma_depth             ? (1LL < 43) : 0;
  rc |= caps.real                          ? (1LL < 44) : 0;
  rc |= caps.fake                          ? (1LL < 45) : 0;
  return rc;
  (void) env;
  (void) class;
}


/**
 * Return the default site for an adjustment method.
 * 
 * @param   method  The adjustment method (display server and protocol.)
 * @return          The default site, {@code null} if it cannot be determined or
 *                  if multiple sites are not supported by the adjustment method.
 */
jstring Java_libgamma_AdjustmentMethod_libgamma_1method_1default_1site(J, jint method)
{
  /* It is really unlikely that `malloc` returns `NULL` here
   * and error handing makes this unnecessarily comples,
   * therefore we will simply skip it. */
  
  char* do_not_free_this = libgamma_method_default_site(method);
  char* this_will_be_freed;
  size_t n;
  if (do_not_free_this == NULL)
    return NULL;
  
  n = strlen(do_not_free_this) + 1;
  this_will_be_freed = malloc(n * sizeof(char));
  memcpy(this_will_be_freed, do_not_free_this, n * sizeof(char));
  
  return (*env)->NewStringUTF(env, this_will_be_freed);
  (void) class;
}


/**
 * Return the default variable that determines
 * the default site for an adjustment method.
 * 
 * @param   method  The adjustment method (display server and protocol.)
 * @return          The environ variables that is used to determine the
 *                  default site. {@code null} if there is none, that is,
 *                  if the method does not support multiple sites.
 */
jstring Java_libgamma_AdjustmentMethod_libgamma_1method_1default_1site_1variable(J, jint method)
{
  /* It is really unlikely that `malloc` returns `NULL` here
   * and error handing makes this unnecessarily comples,
   * therefore we will simply skip it. */
  
  const char* do_not_free_this = libgamma_method_default_site_variable(method);
  char* this_will_be_freed;
  size_t n;
  if (do_not_free_this == NULL)
    return NULL;
  
  n = strlen(do_not_free_this) + 1;
  this_will_be_freed = malloc(n * sizeof(char));
  memcpy(this_will_be_freed, do_not_free_this, n * sizeof(char));
  
  return (*env)->NewStringUTF(env, this_will_be_freed);
  (void) class;
}

