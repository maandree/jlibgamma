.POSIX:

CONFIGFILE = config.mk
include $(CONFIGFILE)

OS = linux
include mk/$(OS).mk


LIB_MAJOR = 1
LIB_MINOR = 0
LIB_VERSION = $(LIB_MAJOR).$(LIB_MINOR)


JPKGDIR = libgamma
JPKG = libgamma

CLASS =\
	$(JPKGDIR)/AdjustmentMethod.class\
	$(JPKGDIR)/AdjustmentMethodCapabilities.class\
	$(JPKGDIR)/ConnectorType.class\
	$(JPKGDIR)/CRTC.class\
	$(JPKGDIR)/CRTCInformation.class\
	$(JPKGDIR)/GammaRamps.class\
	$(JPKGDIR)/Libgamma.class\
	$(JPKGDIR)/LibgammaException.class\
	$(JPKGDIR)/Partition.class\
	$(JPKGDIR)/Ramp.class\
	$(JPKGDIR)/Ramp8.class\
	$(JPKGDIR)/Ramp16.class\
	$(JPKGDIR)/Ramp32.class\
	$(JPKGDIR)/Ramp64.class\
	$(JPKGDIR)/Rampd.class\
	$(JPKGDIR)/Rampf.class\
	$(JPKGDIR)/Site.class\
	$(JPKGDIR)/SubpixelOrder.class

OBJ =\
	libgamma_AdjustmentMethod.o\
	libgamma_CRTC.o\
	libgamma_GammaRamps.o\
	libgamma_LibgammaException.o\
	libgamma_Partition.o\
	libgamma_Ramp.o\
	libgamma_Site.o

JAVA_HDR = $(OBJ:.o=.h)
JAVA_SRC = $(CLASS:.class=.java)


all: libgamma.jar libgamma-java.so

$(OBJ): $(JAVA_HDR)

$(CLASS) $(JAVA_HDR): $(JAVA_SRC)
	@set -e;\
	if $(JAVAH) -version 2>/dev/null >/dev/null; then\
		printf '%s\n' "$(JAVAH) -jni -d . -cp . $(JPKG).EQNative";\
		$(JAVAH) -jni -d . -cp . $(JPKG).EQNative;\
		printf '%s\n' "$(JAVAC) '-Xlint:all' -O -cp . -d . $(JAVA_SRC)";\
		$(JAVAC) '-Xlint:all' -O -cp . -d . $(JAVA_SRC);\
	else\
		printf '%s\n' "$(JAVAC) '-Xlint:all' -O -h . -cp . -d . $(JAVA_SRC)";\
		$(JAVAC) '-Xlint:all' -O -h . -cp . -d . $(JAVA_SRC);\
	fi

Test.class: Test.java
	$(JAVAC) '-Xlint:all' -cp . -d . Test.java

libgamma.jar: $(CLASS)
	$(JAR) cf $@ $(CLASS)

libgamma-java.$(LIBEXT): $(OBJ)
	$(CC) $(LD_FLAGS) $(SHARED) $(LDSO) -o $@ $(OBJ) $(LDFLAGS)

.c.o:
	$(CC) -c -o $@ $< $(CFLAGS)

install: libgamma.jar libgamma-java.$(LIBEXT)
	mkdir -p -- "$(DESTDIR)$(PREFIX)/lib"
	mkdir -p -- "$(DESTDIR)$(JAVADIR)"
	cp -- libgamma-java.$(LIBEXT) "$(DESTDIR)$(PREFIX)/lib"
	cp -- libgamma.jar "$(DESTDIR)$(JAVADIR)"

uninstall: 
	-rm -f -- "$(DESTDIR)$(PREFIX)/lib/libgamma-java.so"
	-rm -f -- "$(DESTDIR)$(JAVADIR)/libgamma.jar"

run-test: Test.class ./libgamma.jar ./libgamma-java.so
	@env LD_LIBRARY_PATH=. java -cp .:./libgamma.jar Test

clean:
	-rm -f -- libgamma_*.h *.o *.class *.so *.so.* *.dll *.dylib *.$(LIBEXT)

.SUFFIXES:
.SUFFIXES: .o .c

.PHONY: all install uninstall check run-test clean
