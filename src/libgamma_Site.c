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


jlongArray Java_libgamma_Site_libgamma_1site_1create(JNIEnv *, jclass, jint, jstring);
void Java_libgamma_Site_libgamma_1site_1free(JNIEnv *, jclass, jlong);
jint Java_libgamma_Site_libgamma_1site_1restore(JNIEnv *, jclass, jlong);

