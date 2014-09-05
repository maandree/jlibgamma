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
  jlongArray rc = (*env)->NewLongArray(env, 2);
  jlong e, z = 0;
  if ((error_code == LIBGAMMA_ERRNO_SET) || !error_code)
    error_code = errno;
  e = (jlong)error_code;
  (*env)->SetLongArrayRegion(env, rc, 0, 1, &z);
  (*env)->SetLongArrayRegion(env, rc, 1, 1, &e);
  return rc;
}


/**
 * Make a success-return.
 * 
 * @param   state  The native object.
 * @return         The object to return.
 */
static jlongArray ok(JNIEnv* env, void* state)
{
  jlong a = (jlong)(size_t)state, z = 0;
  jlongArray rc = (*env)->NewLongArray(env, 2);
  (*env)->SetLongArrayRegion(env, rc, 0, 1, &a);
  (*env)->SetLongArrayRegion(env, rc, 1, 1, &z);
  return rc;
}


/**
 * Create a CRTC state.
 * 
 * @param   partition  The partition state for the partition that the CRTC belongs to.
 * @param   crtc       The index of the CRTC within the partition.
 * @return             Element 0:  The value for {@link #address}.
 *                     Element 1:  Error code, zero on success.
 */
jlongArray Java_libgamma_CRTC_libgamma_1crtc_1create(J, jlong partition, jint crtc)
{
  libgamma_crtc_state_t* state = malloc(sizeof(libgamma_crtc_state_t));
  void* super = (void*)(size_t)partition;
  int r;
  if (state == NULL)
    return fail(env, 0);
  r = libgamma_crtc_initialise(state, super, crtc);
  if (r != 0)
    return fail(env, r);
  return ok(env, state);
  (void) class;
}


/**
 * Release all resources held by a CRTC state
 * and free the CRTC state pointer.
 * 
 * @param  address  The CRTC state.
 */
void Java_libgamma_CRTC_libgamma_1crtc_1free(J, jlong address)
{
  void* this = (void*)(size_t)address;
  libgamma_crtc_free(this);
  (void) env;
  (void) class;
}


/**
 * Restore the gamma ramps for a CRTC to the system settings for that CRTC.
 * 
 * @param   address  The CRTC state.
 * @return           Zero on success, and error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1restore(J, jlong address)
{
  void* this = (void*)(size_t)address;
  int r = libgamma_crtc_restore(this);
  if (r != 0)
    return r == LIBGAMMA_ERRNO_SET ? errno : r;
  return 0;
  (void) env;
  (void) class;
}


/**
 * Read information about a CRTC.
 * 
 * @param   crtc    The state of the CRTC whose information should be read.
 * @param   fields  OR:ed identifiers for the information about the CRTC that should be read.
 * @return          Input parameters for the constructor of {@link CRTCInformation}
 */
jobjectArray Java_libgamma_CRTC_libgamma_1get_1crtc_1information(J, jlong crtc, jint fields)
{
  void* this_voidp = (void*)(size_t)crtc;
  libgamma_crtc_state_t* this = this_voidp;
  jbyteArray edid = NULL;
  jstring connector_name = NULL;
  jclass class_of_jobject = (*env)->FindClass(env, "java/lang/Object");
  jintArray ints_ = (*env)->NewIntArray(env, 25);
  jfloatArray gamma_ = (*env)->NewFloatArray(env, 3);
  jobjectArray rc = (*env)->NewObjectArray(env, 4, class_of_jobject, NULL);
  libgamma_crtc_information_t info;
  jint ints[25];
  jfloat gamma[3];
  
  libgamma_get_crtc_information(&info, this, fields);
  
  if (info.edid != NULL)
    {
      edid = (*env)->NewByteArray(env, info.edid_length);
      (*env)->SetByteArrayRegion(env, edid, 0, info.edid_length, (const jbyte*)(info.edid));
      free(info.edid);
    }
  
  if (connector_name != NULL)
    connector_name = (*env)->NewStringUTF(env, info.connector_name);
  
  gamma[0] = (jfloat)info.gamma_red;
  gamma[1] = (jfloat)info.gamma_green;
  gamma[2] = (jfloat)info.gamma_blue;
  
  ints[0] = (jint)info.edid_error;
  ints[1] = (jint)info.width_mm;
  ints[2] = (jint)info.width_mm_error;
  ints[3] = (jint)info.height_mm;
  ints[4] = (jint)info.height_mm_error;
  ints[5] = (jint)info.width_mm_edid;
  ints[6] = (jint)info.width_mm_edid_error;
  ints[7] = (jint)info.height_mm_edid;
  ints[8] = (jint)info.height_mm_edid_error;
  ints[9] = (jint)info.red_gamma_size;
  ints[10] = (jint)info.green_gamma_size;
  ints[11] = (jint)info.blue_gamma_size;
  ints[12] = (jint)info.gamma_size_error;
  ints[13] = (jint)info.gamma_depth;
  ints[14] = (jint)info.gamma_depth_error;
  ints[15] = (jint)info.gamma_support;
  ints[16] = (jint)info.gamma_support_error;
  ints[17] = (jint)info.subpixel_order;
  ints[18] = (jint)info.subpixel_order_error;
  ints[19] = (jint)info.active;
  ints[20] = (jint)info.active_error;
  ints[21] = (jint)info.connector_name_error;
  ints[22] = (jint)info.connector_type;
  ints[23] = (jint)info.connector_type_error;
  ints[24] = (jint)info.gamma_error;
  
  (*env)->SetIntArrayRegion(env, ints_, 0, 25, ints);
  (*env)->SetFloatArrayRegion(env, gamma_, 0, 3, gamma);
  
  (*env)->SetObjectArrayElement(env, rc, 0, edid);
  (*env)->SetObjectArrayElement(env, rc, 1, connector_name);
  (*env)->SetObjectArrayElement(env, rc, 2, gamma_);
  (*env)->SetObjectArrayElement(env, rc, 3, ints_);
  
  return rc;
  (void) class;
}



/**
 * Get the current gamma ramps for a CRTC, 8-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to fill with the current values
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1ramps8(J, jlong address, jlong ramps)
{
  void* crtc_voidp = (void*)(size_t)address;
  libgamma_crtc_state_t* crtc = crtc_voidp;
  void* output_voidp = (void*)(size_t)ramps;
  libgamma_gamma_ramps8_t* output = output_voidp;
  int r = libgamma_crtc_get_gamma_ramps8(crtc, output);
  return r == LIBGAMMA_ERRNO_SET ? errno : r;
  (void) env;
  (void) class;
}


/**
 * Set the gamma ramps for a CRTC, 8-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to apply.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1ramps8(J, jlong address, jlong ramps)
{
  void* crtc_voidp = (void*)(size_t)address;
  libgamma_crtc_state_t* crtc = crtc_voidp;
  void* values_voidp = (void*)(size_t)ramps;
  libgamma_gamma_ramps8_t* values = values_voidp;
  int r = libgamma_crtc_set_gamma_ramps8(crtc, *values);
  return r == LIBGAMMA_ERRNO_SET ? errno : r;
  (void) env;
  (void) class;
}



/**
 * Get the current gamma ramps for a CRTC, 16-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to fill with the current values
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1ramps16(J, jlong address, jlong ramps)
{
  void* crtc_voidp = (void*)(size_t)address;
  libgamma_crtc_state_t* crtc = crtc_voidp;
  void* output_voidp = (void*)(size_t)ramps;
  libgamma_gamma_ramps16_t* output = output_voidp;
  int r = libgamma_crtc_get_gamma_ramps16(crtc, output);
  return r == LIBGAMMA_ERRNO_SET ? errno : r;
  (void) env;
  (void) class;
}


/**
 * Set the gamma ramps for a CRTC, 16-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to apply.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1ramps16(J, jlong address, jlong ramps)
{
  void* crtc_voidp = (void*)(size_t)address;
  libgamma_crtc_state_t* crtc = crtc_voidp;
  void* values_voidp = (void*)(size_t)ramps;
  libgamma_gamma_ramps16_t* values = values_voidp;
  int r = libgamma_crtc_set_gamma_ramps16(crtc, *values);
  return r == LIBGAMMA_ERRNO_SET ? errno : r;
  (void) env;
  (void) class;
}



/**
 * Get the current gamma ramps for a CRTC, 32-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to fill with the current values.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1ramps32(J, jlong address, jlong ramps)
{
  void* crtc_voidp = (void*)(size_t)address;
  libgamma_crtc_state_t* crtc = crtc_voidp;
  void* output_voidp = (void*)(size_t)ramps;
  libgamma_gamma_ramps32_t* output = output_voidp;
  int r = libgamma_crtc_get_gamma_ramps32(crtc, output);
  return r == LIBGAMMA_ERRNO_SET ? errno : r;
  (void) env;
  (void) class;
}


/**
 * Set the gamma ramps for a CRTC, 32-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to apply.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1ramps32(J, jlong address, jlong ramps)
{
  void* crtc_voidp = (void*)(size_t)address;
  libgamma_crtc_state_t* crtc = crtc_voidp;
  void* values_voidp = (void*)(size_t)ramps;
  libgamma_gamma_ramps32_t* values = values_voidp;
  int r = libgamma_crtc_set_gamma_ramps32(crtc, *values);
  return r == LIBGAMMA_ERRNO_SET ? errno : r;
  (void) env;
  (void) class;
}



/**
 * Get the current gamma ramps for a CRTC, 64-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to fill with the current values.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1ramps64(J, jlong address, jlong ramps)
{
  void* crtc_voidp = (void*)(size_t)address;
  libgamma_crtc_state_t* crtc = crtc_voidp;
  void* output_voidp = (void*)(size_t)ramps;
  libgamma_gamma_ramps64_t* output = output_voidp;
  int r = libgamma_crtc_get_gamma_ramps64(crtc, output);
  return r == LIBGAMMA_ERRNO_SET ? errno : r;
  (void) env;
  (void) class;
}


/**
 * Set the gamma ramps for a CRTC, 64-bit gamma-depth version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to apply.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1ramps64(J, jlong address, jlong ramps)
{
  void* crtc_voidp = (void*)(size_t)address;
  libgamma_crtc_state_t* crtc = crtc_voidp;
  void* values_voidp = (void*)(size_t)ramps;
  libgamma_gamma_ramps64_t* values = values_voidp;
  int r = libgamma_crtc_set_gamma_ramps64(crtc, *values);
  return r == LIBGAMMA_ERRNO_SET ? errno : r;
  (void) env;
  (void) class;
}



/**
 * Set the gamma ramps for a CRTC, single precision floating point version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to apply.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1rampsf(J, jlong address, jlong ramps)
{
  void* crtc_voidp = (void*)(size_t)address;
  libgamma_crtc_state_t* crtc = crtc_voidp;
  void* output_voidp = (void*)(size_t)ramps;
  libgamma_gamma_rampsf_t* output = output_voidp;
  int r = libgamma_crtc_get_gamma_rampsf(crtc, output);
  return r == LIBGAMMA_ERRNO_SET ? errno : r;
  (void) env;
  (void) class;
}


/**
 * Get the current gamma ramps for a CRTC, single precision floating point version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to fill with the current values.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1rampsf(J, jlong address, jlong ramps)
{
  void* crtc_voidp = (void*)(size_t)address;
  libgamma_crtc_state_t* crtc = crtc_voidp;
  void* values_voidp = (void*)(size_t)ramps;
  libgamma_gamma_rampsf_t* values = values_voidp;
  int r = libgamma_crtc_set_gamma_rampsf(crtc, *values);
  return r == LIBGAMMA_ERRNO_SET ? errno : r;
  (void) env;
  (void) class;
}



/**
 * Get the current gamma ramps for a CRTC, double precision floating point version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to fill with the current values.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1rampsd(J, jlong address, jlong ramps)
{
  void* crtc_voidp = (void*)(size_t)address;
  libgamma_crtc_state_t* crtc = crtc_voidp;
  void* output_voidp = (void*)(size_t)ramps;
  libgamma_gamma_rampsd_t* output = output_voidp;
  int r = libgamma_crtc_get_gamma_rampsd(crtc, output);
  return r == LIBGAMMA_ERRNO_SET ? errno : r;
  (void) env;
  (void) class;
}


/**
 * Set the gamma ramps for a CRTC, double precision floating point version.
 * 
 * @param   address  The CRTC state.
 * @param   ramps    The gamma ramps to apply.
 * @return           Zero on success, an error code on failure.
 */
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1rampsd(J, jlong address, jlong ramps)
{
  void* crtc_voidp = (void*)(size_t)address;
  libgamma_crtc_state_t* crtc = crtc_voidp;
  void* values_voidp = (void*)(size_t)ramps;
  libgamma_gamma_rampsd_t* values = values_voidp;
  int r = libgamma_crtc_set_gamma_rampsd(crtc, *values);
  return r == LIBGAMMA_ERRNO_SET ? errno : r;
  (void) env;
  (void) class;
}

