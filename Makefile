# Copying and distribution of this file, with or without modification,
# are permitted in any medium without royalty provided the copyright
# notice and this notice are preserved.  This file is offered as-is,
# without any warranty.


# The package path prefix, if you want to install to another root, set DESTDIR to that root
PREFIX ?= /usr
# The library path excluding prefix
LIB ?= /lib
# The resource path excluding prefix
DATA ?= /share
# The library path including prefix
LIBDIR ?= $(PREFIX)$(LIB)
# The resource path including prefix
DATADIR ?= $(PREFIX)$(DATA)
# Java module path including prefix
JAVADIR ?= $(DATADIR)/java
# The generic documentation path including prefix
DOCDIR ?= $(DATADIR)/doc
# The info manual documentation path including prefix
INFODIR ?= $(DATADIR)/info
# The license base path including prefix
LICENSEDIR ?= $(DATADIR)/licenses

# The name of the package as it should be installed
PKGNAME = jlibgamma


# The Java compiler
JAVAC = javac
# The Java archive creator
JAR = jar
# The JNI header generator
JAVAH = javah


# The version of the library.
LIB_MAJOR = 1
LIB_MINOR = 0
LIB_VERSION = $(LIB_MAJOR).$(LIB_MINOR)

# The so in libgammamm.so as the library file is named on Linux
ifeq ($(PLATFORM),w32)
SO = dll
else
ifeq ($(PLATFORM),osx)
SO = dylib
else
SO = so
endif
endif

# Platform dependent flags
ifeq ($(PLATFORM),w32)
SHARED = -mdll
LDSO = -Wl,-soname,libgamma-java.$(SO).$(LIB_MAJOR)
PIC =
else
ifeq ($(PLATFORM),osx)
SHARED = -dynamiclib
LDSO =
PIC = -fPIC
else
SHARED = -shared
LDSO = -Wl,-soname,libgamma-java.$(SO).$(LIB_MAJOR)
PIC = -fPIC
endif
endif

# The C standard for C code compilation
STD = c99
# Optimisation settings for C code compilation
C_OPTIMISE ?= -Og -g
# Optimisation settings for Java code compilation
JAVA_OPTIMISE ?= -O

# Warning flags for C code, set to empty if you are not using GCC
C_WARN = -Wall -Wextra -pedantic -Wdouble-promotion -Wformat=2 -Winit-self -Wmissing-include-dirs  \
         -Wtrampolines -Wfloat-equal -Wshadow -Wmissing-prototypes -Wmissing-declarations          \
         -Wredundant-decls -Wnested-externs -Winline -Wno-variadic-macros -Wswitch-default         \
         -Wsync-nand -Wunsafe-loop-optimizations -Wcast-align -Wstrict-overflow                    \
         -Wdeclaration-after-statement -Wundef -Wbad-function-cast -Wcast-qual -Wwrite-strings     \
         -Wlogical-op -Waggregate-return -Wstrict-prototypes -Wold-style-definition -Wpacked       \
         -Wvector-operation-performance -Wunsuffixed-float-constants -Wnormalized=nfkc

# Warning flags for Java code
JAVA_WARN = -Xlint:all


# Addition flags to use when compiling C code with JNI support
CC_JNI_FLAGS = -I"$(JAVA_HOME)/include" -I"$(shell echo "$(JAVA_HOME)"/include/*/)"

# Addition flags to use when linking native objets with JNI support
LD_JNI_FLAGS =

# Flags to use when compiling C code
CC_FLAGS = -std=$(STD) $(C_OPTIMISE) $(PIC) $(C_WARN)

# Flags to use when linking native objects
LD_FLAGS = -lgamma -std=$(STD) $(C_OPTIMISE) $(C_WARN)

# Flags to use when compiling Java code
JAVAC_FLAGS = $(JAVACFLAGS) $(JAVA_OPTIMISE) $(JAVA_WARN)


# Java classes
JAVA_OBJ = AdjustmentMethod CRTC CRTCInformation GammaRamps Libgamma Partition Site         \
           AdjustmentMethodCapabilities ConnectorType LibgammaException Ramp SubpixelOrder  \
           Ramp16 Ramp32 Ramp64 Ramp8 Rampd Rampf

# Java classes with native functions
JAVA_H = AdjustmentMethod CRTC GammaRamps LibgammaException Partition Ramp Site

# .so files to create
SO_FILES = bin/libgamma-java.$(SO).$(LIB_VERSION) bin/libgamma-java.$(SO).$(LIB_MAJOR) bin/libgamma-java.$(SO)



.PHONY: all
all: lib test

.PHONY: lib
lib: jar so

.PHONY: so
so: $(SO_FILES)

.PHONY: jar
jar: bin/jlibgamma.jar

.PHONY: class
class: $(foreach O,$(JAVA_OBJ),obj/libgamma/$(O).class)

.PHONY: header
header: $(foreach H,$(JAVA_H),obj/libgamma_$(H).h)

.PHONY: test
test: bin/Test.class


bin/jlibgamma.jar: $(foreach O,$(JAVA_OBJ),obj/libgamma/$(O).class)
	@mkdir -p bin
	cd obj; $(JAR) cf jlibgamma.jar $(foreach O,$(JAVA_OBJ),libgamma/$(O).class)
	mv obj/jlibgamma.jar $@

obj/libgamma/%.class: src/libgamma/%.java
	@mkdir -p obj/libgamma
	$(JAVAC) $(JAVAC_FLAGS) -cp src -s src -d obj $<

obj/libgamma_%.h: obj/libgamma/%.class
	$(JAVAH) -classpath obj -jni -d obj \
	  $$(echo "$<" | sed -e 's:^obj/::' -e 's:.class$$::' | sed -e 's:/:.:g')

obj/libgamma_%.o: src/libgamma_%.c obj/libgamma_%.h
	$(CC) $(CC_JNI_FLAGS) $(CC_FLAGS) -iquote"obj" -c -o $@ $< $(CFLAGS) $(CPPFLAGS)

bin/libgamma-java.$(SO).$(LIB_VERSION): $(foreach O,$(JAVA_H),obj/libgamma_$(O).o)
	@mkdir -p bin
	$(CC) $(LD_JNI_FLAGS) $(LD_FLAGS) $(SHARED) $(LDSO) -o $@ $^ $(LDFLAGS)

bin/libgamma-java.$(SO).$(LIB_MAJOR):
	@mkdir -p bin
	ln -sf libgamma-java.$(SO).$(LIB_VERSION) $@

bin/libgamma-java.$(SO):
	@mkdir -p bin
	ln -sf libgamma-java.$(SO).$(LIB_VERSION) $@

bin/Test.class: src/Test.java bin/jlibgamma.jar $(SO_FILES)
	@mkdir -p bin
	$(JAVAC) $(JAVAC_FLAGS) -cp 'src:bin/jlibgamma.jar' -s src -d bin $<



.PHONY: install
install: install-base

.PHONY: install
install-all: install-base

.PHONY: install-base
install-base: install-lib install-jar install-copyright


.PHONY: install-lib
install-lib:
	install -dm755 -- "$(DESTDIR)$(LIBDIR)"
	install -m755 bin/libgamma-java.$(SO).$(LIB_VERSION) -- "$(DESTDIR)$(LIBDIR)/libgamma-java.$(SO).$(LIB_VERSION)"
	ln -sf libgamma-java.$(SO).$(LIB_VERSION) -- "$(DESTDIR)$(LIBDIR)/libgamma-java.$(SO).$(LIB_MAJOR)"
	ln -sf libgamma-java.$(SO).$(LIB_VERSION) -- "$(DESTDIR)$(LIBDIR)/libgamma-java.$(SO)"

.PHONY: install-jar
install-jar:
	install -dm755 -- "$(DESTDIR)$(JAVADIR)"
	install -m644 bin/jlibgamma.jar -- "$(DESTDIR)$(JAVADIR)/jlibgamma.jar"


.PHONY: install-copyright
install-copyright: install-copying install-license

.PHONY: install-copying
install-copying:
	install -dm755 -- "$(DESTDIR)$(LICENSEDIR)/$(PKGNAME)"
	install -m644 COPYING -- "$(DESTDIR)$(LICENSEDIR)/$(PKGNAME)/COPYING"

.PHONY: install-license
install-license:
	install -dm755 -- "$(DESTDIR)$(LICENSEDIR)/$(PKGNAME)"
	install -m644 LICENSE -- "$(DESTDIR)$(LICENSEDIR)/$(PKGNAME)/LICENSE"



.PHONY: uninstall
uninstall:
	-rm -- "$(DESTDIR)$(LICENSEDIR)/$(PKGNAME)/LICENSE"
	-rm -- "$(DESTDIR)$(LICENSEDIR)/$(PKGNAME)/COPYING"
	-rmdir -- "$(DESTDIR)$(LICENSEDIR)/$(PKGNAME)"
	-rm -- "$(DESTDIR)$(JAVADIR)/jlibgamma.jar"
	-rm -- "$(DESTDIR)$(LIBDIR)/libgamma-java.$(SO).$(LIB_VERSION)"
	-rm -- "$(DESTDIR)$(LIBDIR)/libgamma-java.$(SO).$(LIB_MAJOR)"
	-rm -- "$(DESTDIR)$(LIBDIR)/libgamma-java.$(SO)"



.PHONY: clean
clean:
	-rm -r obj bin



.PHONY: run-test
run-test: bin/Test.class
	@env LD_LIBRARY_PATH=bin java -cp bin:bin/jlibgamma.jar Test

