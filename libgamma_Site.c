/* See LICENSE file for copyright and license details. */
#include "libgamma_Site.h"
#include <errno.h>
#include <stdlib.h>
#include <string.h>

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
 * Create a site state
 * 
 * @param   method  The adjustment method (display server and protocol)
 * @param   site    The site identifier
 * @return          Element 0: The value for {@link #address}
 *                  Element 1: The value for {@link #partitions_available}
 *                  Element 2: Error code, zero on success
 */
jlongArray
Java_libgamma_Site_libgamma_1site_1create(JNIEnv *env, jclass class, jint method, jstring site)
{
	struct libgamma_site_state *state = malloc(sizeof(*state));
	const char *site_chars;
	char *site_;
	int r;
	int saved_errno = 0;
	size_t n;
	if (site) {
		site_chars = (*env)->GetStringUTFChars(env, site, NULL);
		n = strlen(site_chars) + 1;
		site_ = malloc(n * sizeof(char));
		if (!site_)
			saved_errno = errno;
		else
			memcpy(site_, site_chars, n * sizeof(char));
		(*env)->ReleaseStringUTFChars(env, site, site_chars);
		if (saved_errno)
			return fail(env, errno);
	} else {
		site_ = NULL;
	}
	if (!state)
		return fail(env, 0);
	r = libgamma_site_initialise(state, method, site_);
	if (r)
		return fail(env, r);
	return ok(env, state, state->partitions_available);
	(void) class;
}

/**
 * Release all resources held by a site state
 * and free the site state pointer
 * 
 * @param  address  The site state
 */
void
Java_libgamma_Site_libgamma_1site_1free(JNIEnv *env, jclass class, jlong address)
{
	void *this = (void *)(uintptr_t)address;
	libgamma_site_free(this);
	(void) env;
	(void) class;
}

/**
 * Restore the gamma ramps all CRTC:s within a site to the system settings
 * 
 * @param   address  The site state
 * @return           Zero on success, and error code on failure
 */
jint
Java_libgamma_Site_libgamma_1site_1restore(JNIEnv *env, jclass class, jlong address)
{
	void *this = (void *)(uintptr_t)address;
	int r = libgamma_site_restore(this);
	if (r)
		return r == LIBGAMMA_ERRNO_SET ? errno : r;
	return 0;
	(void) env;
	(void) class;
}
