/* See LICENSE file for copyright and license details. */
#include "libgamma_Partition.h"
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
 * Make a success-return
 * 
 * @param   state  The native object
 * @param   count  The subelement count
 * @return         The object to return
 */
static jlongArray
ok(JNIEnv *env, void *state, size_t count)
{
	jlong a = (jlong)(uintptr_t)state;
	jlong b = (jlong)count, z = 0;
	jlongArray rc = (*env)->NewLongArray(env, 3);
	(*env)->SetLongArrayRegion(env, rc, 0, 1, &a);
	(*env)->SetLongArrayRegion(env, rc, 1, 1, &b);
	(*env)->SetLongArrayRegion(env, rc, 2, 1, &z);
	return rc;
}

/**
 * Create a partition state
 * 
 * @param   site       The site state for the site that the partition belongs to
 * @param   partition  The index of the partition within the site
 * @return             Element 0: The value for {@link #address}
 *                     Element 1: The value for {@link #crtcs_available}
 *                     Element 2: Error code, zero on success
 */
jlongArray
Java_libgamma_Partition_libgamma_1partition_1create(JNIEnv *env, jclass class, jlong site, jint partition)
{
	struct libgamma_partition_state *state = malloc(sizeof(*state));
	void *super = (void *)(uintptr_t)site;
	int r;
	if (state == NULL)
		return fail(env, 0);
	r = libgamma_partition_initialise(state, super, (size_t)partition);
	if (r)
		return fail(env, r);
	return ok(env, state, state->crtcs_available);
	(void) class;
}

/**
 * Release all resources held by a partition state
 * and free the partition state pointer
 * 
 * @param  address  The partition state
 */
void
Java_libgamma_Partition_libgamma_1partition_1free(JNIEnv *env, jclass class, jlong address)
{
	void *this = (void *)(uintptr_t)address;
	libgamma_partition_free(this);
	(void) env;
	(void) class;
}

/**
 * Restore the gamma ramps all CRTC:s within a partition to the system settings
 * 
 * @param   address  The partition state
 * @return           Zero on success, and error code on failure
 */
jint
Java_libgamma_Partition_libgamma_1partition_1restore(JNIEnv *env, jclass class, jlong address)
{
	void *this = (void *)(uintptr_t)address;
	int r = libgamma_partition_restore(this);
	if (r)
		return r == LIBGAMMA_ERRNO_SET ? errno : r;
	return 0;
	(void) env;
	(void) class;
}
