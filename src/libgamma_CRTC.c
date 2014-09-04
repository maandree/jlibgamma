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


jlongArray Java_libgamma_CRTC_libgamma_1crtc_1create(JNIEnv *, jclass, jlong, jint);
void Java_libgamma_CRTC_libgamma_1crtc_1free(JNIEnv *, jclass, jlong);
jint Java_libgamma_CRTC_libgamma_1crtc_1restore(JNIEnv *, jclass, jlong);
jobjectArray Java_libgamma_CRTC_libgamma_1get_1crtc_1information(JNIEnv *, jclass, jlong, jint);
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1ramps8(JNIEnv *, jclass, jlong, jlong);
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1ramps8(JNIEnv *, jclass, jlong, jlong);
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1ramps16(JNIEnv *, jclass, jlong, jlong);
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1ramps16(JNIEnv *, jclass, jlong, jlong);
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1ramps32(JNIEnv *, jclass, jlong, jlong);
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1ramps32(JNIEnv *, jclass, jlong, jlong);
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1ramps64(JNIEnv *, jclass, jlong, jlong);
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1ramps64(JNIEnv *, jclass, jlong, jlong);
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1rampsf(JNIEnv *, jclass, jlong, jlong);
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1rampsf(JNIEnv *, jclass, jlong, jlong);
jint Java_libgamma_CRTC_libgamma_1crtc_1get_1gamma_1rampsd(JNIEnv *, jclass, jlong, jlong);
jint Java_libgamma_CRTC_libgamma_1crtc_1set_1gamma_1rampsd(JNIEnv *, jclass, jlong, jlong);

