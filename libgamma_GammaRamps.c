/* See LICENSE file for copyright and license details. */
#include "libgamma_GammaRamps.h"
#include <errno.h>
#include <stdlib.h>

#include <libgamma.h>


/**
 * Make a failure-return
 * 
 * @param   error_code  The error code returned from the failing function or zero to read `errno`
 * @return              The object to return
 */
static jlongArray
fail(JNIEnv *env, int error_code)
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
 * Make a success-return
 * 
 * @param   ramps  The native object
 * @param   red    The red gamma ramp
 * @param   green  The green gamma ramp
 * @param   blue   The blue gamma ramp
 * @return         The object to return
 */
static jlongArray
ok(JNIEnv *env, void *ramps, void *red, void *green, void *blue)
{
	jlong a = (jlong)(uintptr_t)ramps;
	jlong b = (jlong)(uintptr_t)red;
	jlong c = (jlong)(uintptr_t)green;
	jlong d = (jlong)(uintptr_t)blue, z = 0;
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
 * methods to read from and write to it without causing segmentation violation
 * 
 * @param   red_size    The size of the encoding axis of the red gamma ramp
 * @param   green_size  The size of the encoding axis of the green gamma ramp
 * @param   blue_size   The size of the encoding axis of the blue gamma ramp
 * @return              Element 0:  The address of the native object
 *                      Element 1:  The address of the red gamma ramp
 *                      Element 2:  The address of the green gamma ramp
 *                      Element 3:  The address of the blue gamma ramp
 *                      Element 4:  Zero on success, an error code on error
 */
jlongArray
Java_libgamma_GammaRamps_libgamma_1gamma_1ramps8_1create(JNIEnv *env, jclass class, jint red_size, jint green_size, jint blue_size)
{
	struct libgamma_gamma_ramps8 *ramps = malloc(sizeof(*ramps));
	int r;
	if (!ramps)
		return fail(env, 0);
	ramps->red_size = (size_t)red_size;
	ramps->green_size = (size_t)green_size;
	ramps->blue_size = (size_t)blue_size;
	r = libgamma_gamma_ramps8_initialise(ramps);
	if (r)
		return fail(env, r);
	return ok(env, ramps, ramps->red, ramps->green, ramps->blue);
	(void) class;
}

/**
 * Create and initialise a gamma ramp in the proper way that allows all adjustment
 * methods to read from and write to it without causing segmentation violation
 * 
 * @param   red_size    The size of the encoding axis of the red gamma ramp
 * @param   green_size  The size of the encoding axis of the green gamma ramp
 * @param   blue_size   The size of the encoding axis of the blue gamma ramp
 * @return              Element 0: The address of the native object
 *                      Element 1: The address of the red gamma ramp
 *                      Element 2: The address of the green gamma ramp
 *                      Element 3: The address of the blue gamma ramp
 *                      Element 4: Zero on success, an error code on error
 */
jlongArray
Java_libgamma_GammaRamps_libgamma_1gamma_1ramps16_1create(JNIEnv *env, jclass class, jint red_size, jint green_size, jint blue_size)
{
	struct libgamma_gamma_ramps16 *ramps = malloc(sizeof(*ramps));
	int r;
	if (!ramps)
		return fail(env, 0);
	ramps->red_size = (size_t)red_size;
	ramps->green_size = (size_t)green_size;
	ramps->blue_size = (size_t)blue_size;
	r = libgamma_gamma_ramps16_initialise(ramps);
	if (r)
		return fail(env, r);
	return ok(env, ramps, ramps->red, ramps->green, ramps->blue);
	(void) class;
}

/**
 * Create and initialise a gamma ramp in the proper way that allows all adjustment
 * methods to read from and write to it without causing segmentation violation
 * 
 * @param   red_size    The size of the encoding axis of the red gamma ramp
 * @param   green_size  The size of the encoding axis of the green gamma ramp
 * @param   blue_size   The size of the encoding axis of the blue gamma ramp
 * @return              Element 0: The address of the native object
 *                      Element 1: The address of the red gamma ramp
 *                      Element 2: The address of the green gamma ramp
 *                      Element 3: The address of the blue gamma ramp
 *                      Element 4: Zero on success, an error code on error
 */
jlongArray
Java_libgamma_GammaRamps_libgamma_1gamma_1ramps32_1create(JNIEnv *env, jclass class, jint red_size, jint green_size, jint blue_size)
{
	struct libgamma_gamma_ramps32 *ramps = malloc(sizeof(*ramps));
	int r;
	if (!ramps)
		return fail(env, 0);
	ramps->red_size = (size_t)red_size;
	ramps->green_size = (size_t)green_size;
	ramps->blue_size = (size_t)blue_size;
	r = libgamma_gamma_ramps32_initialise(ramps);
	if (r)
		return fail(env, r);
	return ok(env, ramps, ramps->red, ramps->green, ramps->blue);
	(void) class;
}

/**
 * Create and initialise a gamma ramp in the proper way that allows all adjustment
 * methods to read from and write to it without causing segmentation violation
 * 
 * @param   red_size    The size of the encoding axis of the red gamma ramp
 * @param   green_size  The size of the encoding axis of the green gamma ramp
 * @param   blue_size   The size of the encoding axis of the blue gamma ramp
 * @return              Element 0: The address of the native object
 *                      Element 1: The address of the red gamma ramp
 *                      Element 2: The address of the green gamma ramp
 *                      Element 3: The address of the blue gamma ramp
 *                      Element 4: Zero on success, an error code on error
 */
jlongArray
Java_libgamma_GammaRamps_libgamma_1gamma_1ramps64_1create(JNIEnv *env, jclass class, jint red_size, jint green_size, jint blue_size)
{
	struct libgamma_gamma_ramps64 *ramps = malloc(sizeof(*ramps));
	int r;
	if (!ramps)
		return fail(env, 0);
	ramps->red_size = (size_t)red_size;
	ramps->green_size = (size_t)green_size;
	ramps->blue_size = (size_t)blue_size;
	r = libgamma_gamma_ramps64_initialise(ramps);
	if (r)
		return fail(env, r);
	return ok(env, ramps, ramps->red, ramps->green, ramps->blue);
	(void) class;
}

/**
 * Create and initialise a gamma ramp in the proper way that allows all adjustment
 * methods to read from and write to it without causing segmentation violation
 * 
 * @param   red_size    The size of the encoding axis of the red gamma ramp
 * @param   green_size  The size of the encoding axis of the green gamma ramp
 * @param   blue_size   The size of the encoding axis of the blue gamma ramp
 * @return              Element 0: The address of the native object
 *                      Element 1: The address of the red gamma ramp
 *                      Element 2: The address of the green gamma ramp
 *                      Element 3: The address of the blue gamma ramp
 *                      Element 4: Zero on success, an error code on error
 */
jlongArray
Java_libgamma_GammaRamps_libgamma_1gamma_1rampsf_1create(JNIEnv *env, jclass class, jint red_size, jint green_size, jint blue_size)
{
	struct libgamma_gamma_rampsf *ramps = malloc(sizeof(*ramps));
	int r;
	if (!ramps)
		return fail(env, 0);
	ramps->red_size = (size_t)red_size;
	ramps->green_size = (size_t)green_size;
	ramps->blue_size = (size_t)blue_size;
	r = libgamma_gamma_rampsf_initialise(ramps);
	if (r)
		return fail(env, r);
	return ok(env, ramps, ramps->red, ramps->green, ramps->blue);
	(void) class;
}

/**
 * Create and initialise a gamma ramp in the proper way that allows all adjustment
 * methods to read from and write to it without causing segmentation violation
 * 
 * @param   red_size    The size of the encoding axis of the red gamma ramp
 * @param   green_size  The size of the encoding axis of the green gamma ramp
 * @param   blue_size   The size of the encoding axis of the blue gamma ramp
 * @return              Element 0: The address of the native object
 *                      Element 1: The address of the red gamma ramp
 *                      Element 2: The address of the green gamma ramp
 *                      Element 3: The address of the blue gamma ramp
 *                      Element 4: Zero on success, an error code on error
 */
jlongArray
Java_libgamma_GammaRamps_libgamma_1gamma_1rampsd_1create(JNIEnv *env, jclass class, jint red_size, jint green_size, jint blue_size)
{
	struct libgamma_gamma_rampsd *ramps = malloc(sizeof(*ramps));
	int r;
	if (!ramps)
		return fail(env, 0);
	ramps->red_size = (size_t)red_size;
	ramps->green_size = (size_t)green_size;
	ramps->blue_size = (size_t)blue_size;
	r = libgamma_gamma_rampsd_initialise(ramps);
	if (r)
		return fail(env, r);
	return ok(env, ramps, ramps->red, ramps->green, ramps->blue);
	(void) class;
}

/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_ramps8_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure
 * 
 * @param  address  The gamma ramps
 */
void
Java_libgamma_GammaRamps_libgamma_1gamma_1ramps8_1free(JNIEnv *env, jclass class, jlong address)
{
	void *this = (void *)(uintptr_t)address;
	libgamma_gamma_ramps8_free(this);
	(void) env;
	(void) class;
}

/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_ramps16_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure
 * 
 * @param  address  The gamma ramps
 */
void
Java_libgamma_GammaRamps_libgamma_1gamma_1ramps16_1free(JNIEnv *env, jclass class, jlong address)
{
	void *this = (void *)(uintptr_t)address;
	libgamma_gamma_ramps16_free(this);
	(void) env;
	(void) class;
}

/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_ramps32_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure
 * 
 * @param  address  The gamma ramps
 */
void
Java_libgamma_GammaRamps_libgamma_1gamma_1ramps32_1free(JNIEnv *env, jclass class, jlong address)
{
	void *this = (void *)(uintptr_t)address;
	libgamma_gamma_ramps32_free(this);
	(void) env;
	(void) class;
}

/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_ramps64_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure
 * 
 * @param  address  The gamma ramps
 */
void
Java_libgamma_GammaRamps_libgamma_1gamma_1ramps64_1free(JNIEnv *env, jclass class, jlong address)
{
	void *this = (void *)(uintptr_t)address;
	libgamma_gamma_ramps64_free(this);
	(void) env;
	(void) class;
}

/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_rampsf_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure
 * 
 * @param  address  The gamma ramps
 */
void
Java_libgamma_GammaRamps_libgamma_1gamma_1rampsf_1free(JNIEnv *env, jclass class, jlong address)
{
	void *this = (void *)(uintptr_t)address;
	libgamma_gamma_rampsf_free(this);
	(void) env;
	(void) class;
}

/**
 * Release resources that are held by a gamma ramp strcuture that
 * has been allocated by {@link #libgamma_gamma_rampsd_create} or
 * otherwise initialised in the proper manner, as well as release
 * the pointer to the structure
 * 
 * @param  address  The gamma ramps
 */
void
Java_libgamma_GammaRamps_libgamma_1gamma_1rampsd_1free(JNIEnv *env, jclass class, jlong address)
{
	void *this = (void *)(uintptr_t)address;
	libgamma_gamma_rampsd_free(this);
	(void) env;
	(void) class;
}
