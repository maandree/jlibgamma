/* See LICENSE file for copyright and license details. */
#include "libgamma_Ramp.h"
#include <libgamma.h>


/**
 * Read the value of a stop in an 8-bit ramp
 * 
 * @param   address  The address of the ramp
 * @param   stop     The index of the stop
 * @return           The value of the stop
 */
jshort
Java_libgamma_Ramp_libgamma_1gamma_1ramps8_1get(JNIEnv *env, jclass class, jlong address, jint stop)
{
	void *this_voidp = (void *)(size_t)address;
	uint8_t *this = this_voidp;
	return (jshort)(this[stop]);
	(void) env;
	(void) class;
}

/**
 * Read the value of a stop in a 16-bit ramp
 * 
 * @param   address  The address of the ramp
 * @param   stop     The index of the stop
 * @return           The value of the stop
 */
jint
Java_libgamma_Ramp_libgamma_1gamma_1ramps16_1get(JNIEnv *env, jclass class, jlong address, jint stop)
{
	void *this_voidp = (void *)(size_t)address;
	uint16_t *this = this_voidp;
	return (jint)(this[stop]);
	(void) env;
	(void) class;
}

/**
 * Read the value of a stop in a 32-bit ramp
 * 
 * @param   address  The address of the ramp
 * @param   stop     The index of the stop
 * @return           The value of the stop
 */
jlong
Java_libgamma_Ramp_libgamma_1gamma_1ramps32_1get(JNIEnv *env, jclass class, jlong address, jint stop)
{
	void *this_voidp = (void *)(size_t)address;
	uint32_t *this = this_voidp;
	return (jlong)(this[stop]);
	(void) env;
	(void) class;
}

/**
 * Read the value of a stop in a 64-bit ramp
 * 
 * @param   address  The address of the ramp
 * @param   stop     The index of the stop
 * @return           The value of the stop
 */
jlong
Java_libgamma_Ramp_libgamma_1gamma_1ramps64_1get(JNIEnv *env, jclass class, jlong address, jint stop)
{
	void *this_voidp = (void *)(size_t)address;
	uint64_t *this = this_voidp;
	return (jlong)(this[stop]);
	(void) env;
	(void) class;
}

/**
 * Read the value of a stop in a single precision floating point ramp
 * 
 * @param   address  The address of the ramp
 * @param   stop     The index of the stop
 * @return           The value of the stop
 */
jfloat
Java_libgamma_Ramp_libgamma_1gamma_1rampsf_1get(JNIEnv *env, jclass class, jlong address, jint stop)
{
	void *this_voidp = (void *)(size_t)address;
	float *this = this_voidp;
	return (jfloat)(this[stop]);
	(void) env;
	(void) class;
}

/**
 * Read the value of a stop in a double precision floating point ramp
 * 
 * @param   address  The address of the ramp
 * @param   stop     The index of the stop
 * @return           The value of the stop
 */
jdouble
Java_libgamma_Ramp_libgamma_1gamma_1rampsd_1get(JNIEnv *env, jclass class, jlong address, jint stop)
{
	void *this_voidp = (void *)(size_t)address;
	double *this = this_voidp;
	return (jdouble)(this[stop]);
	(void) env;
	(void) class;
}

/**
 * Set the value of a stop in an 8-bit ramp
 * 
 * @param  address  The address of the ramp
 * @param  stop     The index of the stop
 * @param  value    The value of the stop
 */
void
Java_libgamma_Ramp_libgamma_1gamma_1ramps8_1set(JNIEnv *env, jclass class, jlong address, jint stop, jshort value)
{
	void *this_voidp = (void *)(size_t)address;
	uint8_t *this = this_voidp;
	this[stop] = (uint8_t)value;
	(void) env;
	(void) class;
}

/**
 * Set the value of a stop in a 16-bit ramp
 * 
 * @param  address  The address of the ramp
 * @param  stop     The index of the stop
 * @param  value    The value of the stop
 */
void
Java_libgamma_Ramp_libgamma_1gamma_1ramps16_1set(JNIEnv *env, jclass class, jlong address, jint stop, jint value)
{
	void *this_voidp = (void *)(size_t)address;
	uint16_t *this = this_voidp;
	this[stop] = (uint16_t)value;
	(void) env;
	(void) class;
}

/**
 * Set the value of a stop in a 32-bit ramp
 * 
 * @param  address  The address of the ramp
 * @param  stop     The index of the stop
 * @param  value    The value of the stop
 */
void
Java_libgamma_Ramp_libgamma_1gamma_1ramps32_1set(JNIEnv *env, jclass class, jlong address, jint stop, jlong value)
{
	void *this_voidp = (void *)(size_t)address;
	uint32_t *this = this_voidp;
	this[stop] = (uint32_t)value;
	(void) env;
	(void) class;
}

/**
 * Set the value of a stop in a 64-bit ramp
 * 
 * @param  address  The address of the ramp
 * @param  stop     The index of the stop
 * @param  value    The value of the stop
 */
void
Java_libgamma_Ramp_libgamma_1gamma_1ramps64_1set(JNIEnv *env, jclass class, jlong address, jint stop, jlong value)
{
	void *this_voidp = (void *)(size_t)address;
	uint64_t *this = this_voidp;
	this[stop] = (uint64_t)value;
	(void) env;
	(void) class;
}


/**
 * Set the value of a stop in a single precision floating point ramp
 * 
 * @param  address  The address of the ramp
 * @param  stop     The index of the stop
 * @param  value    The value of the stop
 */
void
Java_libgamma_Ramp_libgamma_1gamma_1rampsf_1set(JNIEnv *env, jclass class, jlong address, jint stop, jfloat value)
{
	void *this_voidp = (void *)(size_t)address;
	float *this = this_voidp;
	this[stop] = (float)value;
	(void) env;
	(void) class;
}


/**
 * Set the value of a stop in a double precision floating point ramp
 * 
 * @param  address  The address of the ramp
 * @param  stop     The index of the stop
 * @param  value    The value of the stop
 */
void
Java_libgamma_Ramp_libgamma_1gamma_1rampsd_1set(JNIEnv *env, jclass class, jlong address, jint stop, jdouble value)
{
	void *this_voidp = (void *)(size_t)address;
	double *this = this_voidp;
	this[stop] = (double)value;
	(void) env;
	(void) class;
}
