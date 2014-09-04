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
#include "libgamma_Ramp.h"


jshort Java_libgamma_Ramp_libgamma_1gamma_1ramps8_1get(JNIEnv *, jclass, jlong, jint);
jint Java_libgamma_Ramp_libgamma_1gamma_1ramps16_1get(JNIEnv *, jclass, jlong, jint);
jlong Java_libgamma_Ramp_libgamma_1gamma_1ramps32_1get(JNIEnv *, jclass, jlong, jint);
jlong Java_libgamma_Ramp_libgamma_1gamma_1ramps64_1get(JNIEnv *, jclass, jlong, jint);
jfloat Java_libgamma_Ramp_libgamma_1gamma_1rampsf_1get(JNIEnv *, jclass, jlong, jint);
jdouble Java_libgamma_Ramp_libgamma_1gamma_1rampsd_1get(JNIEnv *, jclass, jlong, jint);
void Java_libgamma_Ramp_libgamma_1gamma_1ramps8_1set(JNIEnv *, jclass, jlong, jint, jshort);
void Java_libgamma_Ramp_libgamma_1gamma_1ramps16_1set(JNIEnv *, jclass, jlong, jint, jint);
void Java_libgamma_Ramp_libgamma_1gamma_1ramps32_1set(JNIEnv *, jclass, jlong, jint, jlong);
void Java_libgamma_Ramp_libgamma_1gamma_1ramps64_1set(JNIEnv *, jclass, jlong, jint, jlong);
void Java_libgamma_Ramp_libgamma_1gamma_1rampsf_1set(JNIEnv *, jclass, jlong, jint, jfloat);
void Java_libgamma_Ramp_libgamma_1gamma_1rampsd_1set(JNIEnv *, jclass, jlong, jint, jdouble);

