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
#include "libgamma_GammaRamps.h"


jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1ramps8_1create(JNIEnv *, jclass, jint, jint, jint);
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1ramps16_1create(JNIEnv *, jclass, jint, jint, jint);
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1ramps32_1create(JNIEnv *, jclass, jint, jint, jint);
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1ramps64_1create(JNIEnv *, jclass, jint, jint, jint);
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1rampsf_1create(JNIEnv *, jclass, jint, jint, jint);
jlongArray Java_libgamma_GammaRamps_libgamma_1gamma_1rampsd_1create(JNIEnv *, jclass, jint, jint, jint);
void Java_libgamma_GammaRamps_libgamma_1gamma_1ramps8_1free(JNIEnv *, jclass, jlong);
void Java_libgamma_GammaRamps_libgamma_1gamma_1ramps16_1free(JNIEnv *, jclass, jlong);
void Java_libgamma_GammaRamps_libgamma_1gamma_1ramps32_1free(JNIEnv *, jclass, jlong);
void Java_libgamma_GammaRamps_libgamma_1gamma_1ramps64_1free(JNIEnv *, jclass, jlong);
void Java_libgamma_GammaRamps_libgamma_1gamma_1rampsf_1free(JNIEnv *, jclass, jlong);
void Java_libgamma_GammaRamps_libgamma_1gamma_1rampsd_1free(JNIEnv *, jclass, jlong);

