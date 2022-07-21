LIBEXT = so
OS_CFLAGS = -fPIC
OS_LDFLAGS = -shared -Wl,-soname,libgamma-java.so.$(LIB_MAJOR)
